package com.lec.file.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lec.file.dto.upload.UploadFileDTO;
import com.lec.file.dto.upload.UploadResultDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;

@RestController
@Log4j2
public class UpDownController {

    // @Value("${path.upload}") // import 시에 springframework으로 시작하는 Value
	@Value("${com.lec.upload.path}") // import 시에 springframework으로 시작하는 Value
    private String uploadPath;
    
    @Operation(summary = "Upload POST", description = "POST 방식으로 파일 등록")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
     public List<UploadResultDTO> upload(@RequestBody UploadFileDTO uploadFileDTO){
//    public String changeUpload(@Valid @RequestBody 
//    		@Parameter(content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)) 
//    			UploadFileDTO uploadFileDTO) {
    	
        log.info(uploadFileDTO);

        if(uploadFileDTO.getFiles() != null){

            final List<UploadResultDTO> list = new ArrayList<>();

            uploadFileDTO.getFiles().forEach(multipartFile -> {

                String originalName = multipartFile.getOriginalFilename();
                log.info(originalName);

                String uuid = UUID.randomUUID().toString();

                Path savePath = Paths.get(uploadPath, uuid+"_"+ originalName);

                boolean image = false;

                try {
                    multipartFile.transferTo(savePath);

                    //이미지 파일의 종류라면
                    if(Files.probeContentType(savePath).startsWith("image")){

                        image = true;

                        File thumbFile = new File(uploadPath, "s_" + uuid+"_"+ originalName);

                        Thumbnailator.createThumbnail(savePath.toFile(), thumbFile, 200,200);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

                list.add(UploadResultDTO.builder()
                        .uuid(uuid)
                        .fileName(originalName)
                        .img(image).build()
                );

            });//end each

            return list;
        }//end if

        return null;
    }
    
    @Operation(summary = "view 파일", description = "GET방식으로 첨부파일 조회")   
    @GetMapping("/view/{fileName}")
    public ResponseEntity<Resource> viewFileGET(@RequestBody @PathVariable("fileName") String fileName){

        Resource resource = new FileSystemResource(uploadPath+File.separator + fileName);
        String resourceName = resource.getFilename();
        HttpHeaders headers = new HttpHeaders();

        try{
            headers.add("Content-Type", Files.probeContentType( resource.getFile().toPath() ));
        } catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().headers(headers).body(resource);
    } 
    
    @Operation(summary = "remove 파일", description = "DELETE 방식으로 파일 삭제")  
    @DeleteMapping("/remove/{fileName}")
    public Map<String,Boolean> removeFile(@RequestBody @PathVariable("fileName") String fileName){

        Resource resource = new FileSystemResource(uploadPath+File.separator + fileName);
        String resourceName = resource.getFilename();

        Map<String, Boolean> resultMap = new HashMap<>();
        boolean removed = false;

        try {
            String contentType = Files.probeContentType(resource.getFile().toPath());
            removed = resource.getFile().delete();

            //섬네일이 존재한다면
            if(contentType.startsWith("image")){
                File thumbnailFile = new File(uploadPath+File.separator +"s_" + fileName);
                thumbnailFile.delete();
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }

        resultMap.put("result", removed);

        return resultMap;
    }    
}
