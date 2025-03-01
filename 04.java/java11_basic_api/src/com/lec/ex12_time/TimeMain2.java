package com.lec.ex12_time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeMain2 {

	public static void main(String[] args) {
		
		LocalDateTime now = LocalDateTime.now();
		System.out.println("현재일시 = " + now);
		
		String strDateTime = now.getYear() + "년 ";
		strDateTime += now.getMonthValue() + "월 ";
		strDateTime += now.getMonth() + "월 ";
		strDateTime += now.getDayOfMonth() + "일 ";
		strDateTime += now.getDayOfWeek() + " ";
		strDateTime += now.getHour() + "시 ";
		strDateTime += now.getMinute() + "분 ";
		strDateTime += now.getSecond() + "초 ";
		strDateTime += now.getNano() + "ns ";
		System.out.println("현재일시 = " + strDateTime);

		// 윤년/평균
		LocalDate nowDate = LocalDate.now();
		System.out.println("현재일시 = " + nowDate);
		if(nowDate.isLeapYear()) {
			System.out.println("현재년도는 윤년입니다!");
		} else {
			System.out.println("현재년도는 평년입니다!");			
		}
		
		LocalTime nowTime = now.toLocalTime();
		System.out.println("현재시간 = " + nowTime);
		
		// time zone
		ZonedDateTime seoul = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
		ZoneId seoulZonId = seoul.getZone();
		System.out.println("서울시간 = " + seoul);
		System.out.println("서울시간ZoneId = " + seoulZonId);
		
	}

}


















