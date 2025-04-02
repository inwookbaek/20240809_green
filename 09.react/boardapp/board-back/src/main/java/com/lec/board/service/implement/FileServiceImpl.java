package com.lec.board.service.implement;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lec.board.service.FileService;

@Service
public class FileServiceImpl implements FileService {
	
	@Value("${upload.path}")
	private String filePath;
	
	@Value("${upload.url}")
	private String fileUrl;
	
	@Override
	public String upload(MultipartFile file) {
		if(file.isEmpty()) return null;
		
		String originalFileName = file.getOriginalFilename();
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
		String uuid = UUID.randomUUID().toString();
		String saveFileName = uuid + extension;
		String savePath = filePath + saveFileName;
		
		try {
			file.transferTo(new File(savePath));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		String url = fileUrl + saveFileName;
		return url;
	}
	/*
		다양한 리소스(파일, 클래스패스 리소스, URL 리소스 등)에 대한 추상화된 접근 방법을 제공하는 인터페이스
		[ 주요 구현 클래스 ]
		UrlResource				URL로 접근 가능한 리소스 (http:, ftp:, file: 등)
		ClassPathResource		클래스패스 상의 리소스
		FileSystemResource		파일 시스템의 리소스
		ServletContextResource	웹 애플리케이션 컨텍스트 내의 리소스
		InputStreamResource		입력 스트림으로부터 생성되는 리소스
		ByteArrayResource		바이트 배열로부터 생성되는 리소스
	*/
	@Override
	public Resource getImage(String fileName) {

		Resource resource = null;
		
		try {
			resource = new UrlResource("file:" + filePath + fileName);
		} catch (Exception e) {
			e.printStackTrace();
			return null;			
		}
		
		return resource;
	}

}
