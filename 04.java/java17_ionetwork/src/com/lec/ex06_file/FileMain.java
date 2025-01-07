package com.lec.ex06_file;

import java.io.File;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
	파일입출력
	
	File클래스
	
	IO패키지에서 제공하는 File클래스는 파일크기, 속성, 이름등의 정보를 읽는 기능과 파일생성
	삭제등 관련 기능을 제공한다. 또한 디렉토리를 생성하고 디렉토리에 있는 파일목록을 읽는 
	기능도 제공한다.
	
	하지만, 파일의 데이터를 읽거나 쓰는 기능을 지원하지 않는다. 파일에 데이터를 입출력하고자
	할 경우에는 File입출력스트림을 사용해야 한다.
	
	디렉토리구분자는 윈도우에서는 '\' or '/' linux에서는 '/'을 사용한다. 만약에 '\'를 사용할
	경우에는 이스케이프문자('\\')로 사용해야 한다.
	
	File관련 주요메서드
	
	1. createNewFile() : 새로운 파일을 생성
	2. mkdir() : dir생성
	3. mkdirs(): 경로상에 없는 하위디렉토리 모두 생성
	4. delete(): 파일 or 디렉토리 삭제
	
	파일 or 디렉토리가 존재할 경우 관련 메서드
	
	1. canExecute() : 실행할 수 있는 파일인지 여부
	2. canRead() : 읽을 수 있는 파일여부
	3. canWrite  : 쓰기가장파일여부
	4. getName() : 파일이름리턴
	5. getParent() : 부모디렉토리 리턴
	6. getParentFile() : 부모디렉토리를 File객체로 생성한 후 리턴
	7. getPath() : 전체경로리턴
	8. isDirectory() : 디렉토리인지여부를 리턴
	9. isFile() : 파일인지여부를 리턴
	10. isHidden() : 숨김파일여부
	11. lastModified() : 파일최종수정일시
	12. length() : 파일크기를 리턴
	13. list() : 디렉토리에 포하묀 파일 및 서브디렉토리 목록을 String[]배열로 리턴
	14. list(FilenameFilter filter) : list()와 동일하지만 FilenameFilter에 맞는 것만 String[]배열로 리턴
	15. listFiles() : 디렉토리에 포함된 파일 및 서브디렉토리 목록들을 File[]배열로 리턴
	16. listFiles(FilenameFilter filter) : listFiles()와 동이하지만 filter에 맞는 것만 String[]배열로 리턴
*/
public class FileMain {

	public static void main(String[] args) throws Exception {
		File dir = new File("d:/temp/dir");
		File file1 = new File("d:/temp/file1.txt");
		File file2 = new File("d:/temp/file2.txt");
		File file3 = new File(new URI("file:///d:/temp/file3.txt")); // URI로 생성하기

		// 생성하기
		if(!dir.exists()) dir.mkdir();
		if(!file1.exists()) file1.createNewFile();
		if(!file2.exists()) file2.createNewFile();
		if(!file3.exists()) file3.createNewFile();
		
		// 디렉토리목록
		File temp = new File("d:/lec/04.java");
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd     a     hh:mm:ss");
		File[] fileLists = temp.listFiles();
		System.out.println("-".repeat(50));
		for(File file:fileLists) {
			System.out.print(sdf.format(new Date(file.lastModified())));
			if(file.isDirectory()) {
				System.out.println("\t<dir>\t" + file.getName());
			} else {
				System.out.println("\t\t" + file.getName() + "\t\t" + file.length() + " bytes");
			}
		}
	}

}