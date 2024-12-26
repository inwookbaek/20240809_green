package com.lec.ex12_time;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

/*
	java.time패키지
	
	java7이전 까지는 Date와 Calendar클래스를 이용하여 날짜와 시간정보를 얻을 수 있었다.
	하지만 Date클래스의 대부분의 메서드는 deprecated되었고 Date의 용도는 단순히 특정시점의
	날짜정보만 저장하는 역할만 한다. Calendar클래스는 날짜와 시간정보를 얻기에는 충분하지만
	날짜와 시간을 조작하거나 비교하기에는 불충분하다.
	
	그래서 java8버전부터는 날짜와 시간을 나타내는 여러가지 API를 추가했다. 이 API는 java.util
	패키지에는 없고 별도의 java.time패키지로 제공된다.
*/

public class TimeMain1 {

	public static void main(String[] args) throws InterruptedException {
		// 날짜와 시간객체생성
		// LocalDate, LocalTime, LocalDateTime, ZonedDateTime, Instant(특정시점의 Time Stamp)
		
		// 1. 날짜
		LocalDate currDate = LocalDate.now();
		System.out.println("현재일자 = " + currDate);
		LocalDate targetDate = LocalDate.of(2024, 12, 31);
		System.out.println("목표일자 = " + targetDate);
		
		// 2. 시간
		LocalDateTime currDateTime = LocalDateTime.now();
		System.out.println("현재일시 = " + currDateTime);
		LocalDateTime targetDateTime = LocalDateTime.of(2024, 12, 31, 10, 17, 20);
		System.out.println("목표일시 = " + targetDateTime);

		// 3. TimeZone vs UTC(협정세계시)
		ZonedDateTime utcDateTime = ZonedDateTime.now(ZoneId.of("UTC"));
		System.out.println("세계협정일시 = " + utcDateTime);
		ZonedDateTime seoulDateTime = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
		System.out.println("서울존일시 = " + seoulDateTime);
		
		// 4. 특정시점의 타임스탬프
		Instant instant1 = Instant.now();
		Thread.sleep(10); // 10초간정지
		
		Instant instant2 = Instant.now();
		
		if(instant1.isBefore(instant2)) {
			System.out.println("instant1시간이 instan2시간보다 빠릅니다!!");
		} else if(instant1.isAfter(instant2)) {
			System.out.println("instant1시간이 instan2시간보다 늦습니다!!");			
		} else {
			System.out.println("instant1시간과 instan2시간이 같습니다!!");						
		}
		System.out.println("시간차이(nano) = " + instant1.until(instant2, ChronoUnit.NANOS));
	}

}



















