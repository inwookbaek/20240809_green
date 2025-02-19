package com.lec.ex05_console;

import java.io.IOException;
import java.io.InputStream;

public class SystemInMain {

	public static void main(String[] args) throws Exception {
		
		InputStream is = System.in;
		
		byte[] names = new byte[100];
		System.out.println("이름 => ");
		int nameBytes = is.read(names);
		String name = new String(names, 0, nameBytes-2);
		System.out.println(String.format("입력된 이름은 %s 입니다!", name));
	}

}
