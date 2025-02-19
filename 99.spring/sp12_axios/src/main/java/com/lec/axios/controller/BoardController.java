package com.lec.axios.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lec.axios.dto.BoardDTO;
import com.lec.axios.dto.BoardListReplyCountDTO;
import com.lec.axios.dto.PageRequestDTO;
import com.lec.axios.dto.PageResponseDTO;
import com.lec.axios.service.BoardService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

// 댓글처리로직추가를 위해 주석처리, 아래 로직으로 대체    
//    @GetMapping("/list")
//    public String list(PageRequestDTO pageRequestDTO, Model model){
//        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);
//        log.info(responseDTO);
//        model.addAttribute("responseDTO", responseDTO);
//        return "board/list";
//    }
        
    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){

        //PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);
        PageResponseDTO<BoardListReplyCountDTO> responseDTO =
                boardService.listWithReplyCount(pageRequestDTO);
        log.info(responseDTO);
        model.addAttribute("responseDTO", responseDTO);
    }   
    
    @GetMapping({"/read", "/modify"})
    public void read(@RequestParam("bno") Long bno, PageRequestDTO pageRequestDTO, Model model) {
    	BoardDTO boardDTO = boardService.readOne(bno);
    	log.info("return(x) ==> " + boardDTO);
    	model.addAttribute("dto", boardDTO);  
    	// return "board/read";
    }     
     
    @GetMapping("/register")
    public void registerGET(){
    	
    }    
    
    @PostMapping("/register")
    public String registerPost(@Valid BoardDTO boardDTO, BindingResult bindingResult, 
    		RedirectAttributes redirectAttributes){

        log.info("board POST register.......");

        if(bindingResult.hasErrors()) {
            log.info("has errors.......");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors() );
            return "redirect:/board/register";
        }

        log.info(boardDTO);

        Long bno  = boardService.register(boardDTO);

        redirectAttributes.addFlashAttribute("result", bno);

        return "redirect:/board/list";
    } 

    @PostMapping("/modify")
    public String modify( PageRequestDTO pageRequestDTO,
                          @Valid BoardDTO boardDTO,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes){

        log.info("board modify post......." + boardDTO);

        if(bindingResult.hasErrors()) {
            log.info("has errors.......");

            String link = pageRequestDTO.getLink();
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors() );
            redirectAttributes.addAttribute("bno", boardDTO.getBno());

            return "redirect:/board/modify?"+link;
        }

        boardService.modify(boardDTO);
        redirectAttributes.addFlashAttribute("result", "modified");
        redirectAttributes.addAttribute("bno", boardDTO.getBno());

        return "redirect:/board/read";
    } 
    
    @PostMapping("/remove")
    public String remove(@RequestParam("bno") Long bno, RedirectAttributes redirectAttributes) {

        log.info("remove post.. " + bno);
        boardService.remove(bno);
        redirectAttributes.addFlashAttribute("result", "removed");
        return "redirect:/board/list";
    }    
}
