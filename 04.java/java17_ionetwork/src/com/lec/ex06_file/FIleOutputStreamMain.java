package com.lec.ex06_file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class FIleOutputStreamMain {

	public static void main(String[] args) throws Exception {
		
		String org_img = "src/com/lec/ex06_file/blackpink_02.jpg";
		String new_img = "d:/temp/blackpink_copy.jpg";
		
		FileInputStream fis = new FileInputStream(org_img);
		FileOutputStream fos = new FileOutputStream(new_img);
		
		int readByte;
		byte[] readBytes = new byte[100];
		
		while((readByte = fis.read(readBytes)) != -1) {
			fos.write(readBytes, 0, readByte);
		}
		fos.flush();
		fos.close();
		fis.close();
		System.out.println("파일이 성공적으로 다운로드가 되었습니다!!");
	}

}
