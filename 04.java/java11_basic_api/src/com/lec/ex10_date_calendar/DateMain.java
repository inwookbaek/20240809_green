package com.lec.ex10_date_calendar;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateMain {

	public static void main(String[] args) {
	
		Date now = new Date();
		System.out.println("now.toString() = " + now.toString());
		System.out.println("now.toGMTString() = " + now.toGMTString());
		System.out.println("now.toLocaleString() = " + now.toLocaleString());
		System.out.println();
		
		// 날짜형식 - SimpleDateFormat
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy년 MM월 dd일 hh시 mm분 ss초");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
		System.out.println(sdf1.format(now));
		System.out.println(sdf2.format(now));
		System.out.println(sdf1.getCalendar());
	}

}
