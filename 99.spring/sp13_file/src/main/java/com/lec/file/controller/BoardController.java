package com.lec.file.controller;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lec.file.dto.BoardDTO;
import com.lec.file.dto.BoardListAllDTO;
import com.lec.file.dto.PageRequestDTO;
import com.lec.file.dto.PageResponseDTO;
import com.lec.file.service.BoardService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

	// p675 start
    @Value("${path.upload}") // import 시에 springframework으로 시작하는 Value
    // @Value("${com.lec.upload.path}") // import 시에 springframework으로 시작하는 Value
    private String uploadPath;
	// p675 end
	
    private final BoardService boardService;

// 댓글처리로직추가를 위해 주석처리, 아래 로직으로 대체    
//    @GetMapping("/list")
//    public String list(PageRequestDTO pageRequestDTO, Model model){
//        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);
//        log.info(responseDTO);
//        model.addAttribute("responseDTO", responseDTO);
//        return "board/list";
//    }
        
//    @GetMapping("/list")
//    public void list(PageRequestDTO pageRequestDTO, Model model){
//
//        //PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);
//        PageResponseDTO<BoardListReplyCountDTO> responseDTO =
//                boardService.listWithReplyCount(pageRequestDTO);
//        log.info(responseDTO);
//        model.addAttribute("responseDTO", responseDTO);
//    }   
    
    // p662 
    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){
    	PageResponseDTO<BoardListAllDTO> responseDTO =
    			boardService.listWithAll(pageRequestDTO);
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

    // p660 - 게시글등록처리   
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
    
//    @PostMapping("/remove")
//    public String remove(@RequestParam("bno") Long bno, RedirectAttributes redirectAttributes) {
//
//        log.info("remove post.. " + bno);
//        boardService.remove(bno);
//        redirectAttributes.addFlashAttribute("result", "removed");
//        return "redirect:/board/list";
//    }    
    
    // p675 start
    @PostMapping("/remove")
    public String remove(BoardDTO boardDTO, RedirectAttributes redirectAttributes) {

        Long bno  = boardDTO.getBno();
        log.info("remove post.. " + bno);

        boardService.remove(bno);

        //게시물이 삭제되었다면 첨부 파일 삭제
        log.info(boardDTO.getFileNames());
        List<String> fileNames = boardDTO.getFileNames();
        if(fileNames != null && fileNames.size() > 0){
            removeFiles(fileNames);
        }

        redirectAttributes.addFlashAttribute("result", "removed");
        return "redirect:/board/list";
    }  
    
    public void removeFiles(List<String> files){

        for (String fileName:files) {

            Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);
            String resourceName = resource.getFilename();


            try {
                String contentType = Files.probeContentType(resource.getFile().toPath());
                resource.getFile().delete();

                //섬네일이 존재한다면
                if (contentType.startsWith("image")) {
                    File thumbnailFile = new File(uploadPath + File.separator + "s_" + fileName);
                    thumbnailFile.delete();
                }

            } catch (Exception e) {
                log.error(e.getMessage());
            }

        }//end for
    }
    // p675 end
}
