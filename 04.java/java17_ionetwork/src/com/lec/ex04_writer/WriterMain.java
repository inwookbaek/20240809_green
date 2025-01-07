package com.lec.ex04_writer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class WriterMain {

	public static void main(String[] args) throws Exception {
		
		Writer writer = new FileWriter("src/com/lec/ex04_writer/test1.txt");
		
		// 1. write(int c)
		char[] data = "대한민국만세".toCharArray();
		for(int i=0;i<data.length;i++) {
			writer.write(data[i]);
		}
		writer.flush();
		writer.close();
		
		// 2. write(char cbuf[], int off, int len)
		writer = new FileWriter("src/com/lec/ex04_writer/test2.txt");
		data = "우리나라만세".toCharArray();
		writer.write(data, 1, 2);
		writer.flush();
		writer.close();
	}
}
