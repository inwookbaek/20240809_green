package com.lec.ex06_file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriterMain {

	public static void main(String[] args) throws Exception {
		
		File file = new File("d:/temp/함숨.txt");
		FileWriter fw = new FileWriter(file, false);
		
		fw.write("숨을 크게 쉬어봐요\n");
		fw.write("하늘은 파랗게\n");
		fw.write("구름은 하얗게\n");
		fw.flush();
		fw.close();
		System.out.println("파일이 성공적으로 저장이 되었습니다!");

	}

}
