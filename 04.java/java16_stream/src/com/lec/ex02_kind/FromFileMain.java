package com.lec.ex02_kind;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FromFileMain {

	public static void main(String[] args) throws IOException {
		// 4. 파일로 부터 Stream을 얻기
		Stream<String> stream = null;
		
		// 1) 파일경로
		// D:\lec\04.java\java16_stream\src\com\lec\ex02_kind\data.txt
		Path path = Paths.get("D:/lec/04.java/java16_stream/src/com/lec/ex02_kind/data.txt");
		System.out.println(path);
		System.out.println();
		
		// 2) 파일읽기(1) - java.nio.file.Files.lines()
		stream = Files.lines(path, Charset.defaultCharset());
		stream.forEach(System.out :: println);
		System.out.println();
				
		// 3) 파일읽기(2) - java.io.BufferedReader.line()
		File file = path.toFile();
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		stream = br.lines();
		stream.forEach(System.out :: println);
		System.out.println();
		br.close();
		fr.close();
	}

}