package com.lec.ex07_auxstream;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BufferedInputStreamMain {

	public static void main(String[] args) throws Exception {
		
		long start, end;
		// 1. non-buffer
		start = System.nanoTime();
		
		FileInputStream fis1 = new FileInputStream("src/com/lec/ex06_file/blackpink_02.jpg");
		while(fis1.read() != -1) {}
		
		end = System.nanoTime();
		System.out.println("버퍼를 사용하지 않았을 경우 소요시간 = " + (end-start) + "ns");
		
		
		// 2. buffer
		start = System.nanoTime();
		FileInputStream fis2 = new FileInputStream("src/com/lec/ex06_file/blackpink_02.jpg");
		BufferedInputStream bis = new BufferedInputStream(fis2);
		
		while(bis.read() != -1) {}

		end = System.nanoTime();
		System.out.println("버퍼를 사용했을 경우 소요시간 = " + (end-start) + "ns");
		
		fis1.close();
		fis2.close();
		bis.close();

	}

}
