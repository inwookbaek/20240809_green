package com.lec.ex12_time;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class TimeMain3 {

	public static void main(String[] args) {
		// 1. 시간연산
		LocalDateTime now = LocalDateTime.now();
		System.out.println("현재시간 = " + now);
		
		LocalDateTime targetDateTime = now
				.plusYears(1)
				.minusMonths(2)
				.plusDays(3)
				.plusHours(4)
				.plusMinutes(5)
				.plusSeconds(6);
		System.out.println("연산시간 = " + targetDateTime);
		
		// 2. 시간변경
		// 1) 절대변경
		targetDateTime = now
				.withYear(2025)
				.withMonth(12)
				.withDayOfMonth(25)
				.withHour(12)
				.withMinute(10)
				.withSecond(10);
		System.out.println("직접변경시간 = " + targetDateTime);
			
		// 2) 상대변경 - 년
		targetDateTime = now.with(TemporalAdjusters.firstDayOfYear());
		System.out.println("금년도 첫 번째일 = " + targetDateTime);
		targetDateTime = now.with(TemporalAdjusters.lastDayOfYear());
		System.out.println("금년도 마지막일 = " + targetDateTime);
		targetDateTime = now.with(TemporalAdjusters.firstDayOfNextYear());
		System.out.println("익년도 첫 번째일 = " + targetDateTime);
		System.out.println("익년도 마지막일 = " + now.with(TemporalAdjusters.firstDayOfNextYear()));
		System.out.println();
		
		// 3) 상대변경 - 월
		System.out.println("당월 첫 번째일 = " + now.with(TemporalAdjusters.firstDayOfMonth()));
		System.out.println("당월 마지막일 = " + now.with(TemporalAdjusters.lastDayOfMonth()));
		System.out.println("익월 첫 번째일 = " + now.with(TemporalAdjusters.firstDayOfNextMonth()));
		System.out.println();
		
		// 4) 상대변경 - 요일
		System.out.println("당월 첫 번째 월요일 = " + now.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)));
		System.out.println("돌아오는 일요일 = " + now.with(TemporalAdjusters.next(DayOfWeek.MONDAY)));
		System.out.println("지난 월요일 = " + now.with(TemporalAdjusters.previous(DayOfWeek.MONDAY)));
		System.out.println();
		
		// 3. 날자와 시간 비교하기
		LocalDateTime startDateTime = LocalDateTime.of(2023,1,1,9,0,0);
		LocalDateTime finishDateTime = LocalDateTime.of(2024,12,31,0,0,0);
		System.out.println("시작일 = " + startDateTime + " ~ 종료일 = " + finishDateTime );
		
		if(startDateTime.isBefore(finishDateTime)) {
			System.out.println("현재작업은 진행중입니다!");
		} else if(startDateTime.isEqual(finishDateTime)) {
			System.out.println("작업이 종료중 입니다!");
		} else if(startDateTime.isAfter(finishDateTime)){
			System.out.println("작업이 종료되었습니다!");
		}
		
		System.out.println("[종료까지 남은 시간]");
		long remainYear = startDateTime.until(finishDateTime, ChronoUnit.YEARS);
		long remainMonth = startDateTime.until(finishDateTime, ChronoUnit.MONTHS);
		long remainDay = startDateTime.until(finishDateTime, ChronoUnit.DAYS);
		long remainHour = startDateTime.until(finishDateTime, ChronoUnit.HOURS);
		long remainMinute = startDateTime.until(finishDateTime, ChronoUnit.MINUTES);
		long remainSecond = startDateTime.until(finishDateTime, ChronoUnit.SECONDS);
		
		System.out.println("남은 년 = " + remainYear);
		System.out.println("남은 월 = " + remainMonth);
		System.out.println("남은 일 = " + remainDay);
		System.out.println("남은 시 = " + remainHour);
		System.out.println("남은 분 = " + remainMinute);
		System.out.println("남은 초 = " + remainSecond);
		System.out.println();
		
		remainYear = ChronoUnit.YEARS.between(startDateTime, finishDateTime);
		remainMonth = ChronoUnit.MONTHS.between(startDateTime, finishDateTime);
		remainDay = ChronoUnit.DAYS.between(startDateTime, finishDateTime);
		remainHour = ChronoUnit.HOURS.between(startDateTime, finishDateTime);
		remainMinute = ChronoUnit.MINUTES.between(startDateTime, finishDateTime);
		remainSecond = ChronoUnit.SECONDS.between(startDateTime, finishDateTime);
		
		System.out.println("남은 년 = " + remainYear);
		System.out.println("남은 월 = " + remainMonth);
		System.out.println("남은 일 = " + remainDay);
		System.out.println("남은 시 = " + remainHour);
		System.out.println("남은 분 = " + remainMinute);
		System.out.println("남은 초 = " + remainSecond);
		System.out.println();
		
		System.out.println("[종료까지 남은 기간]");
		Period period = Period.between(startDateTime.toLocalDate(), finishDateTime.toLocalDate());
		System.out.println("남은 기간 = " + period.getYears() + "년");
		System.out.println("남은 기간 = " + period.getMonths() + "개월");
		System.out.println("남은 기간 = " + period.getDays() + "일");
		
		Duration duration = Duration.between(startDateTime.toLocalTime(), finishDateTime.toLocalTime());
		System.out.println("남은 기간 = " + duration.getSeconds() + "초");
		System.out.println();
		
		// 4. 파싱과 포매팅하기
		DateTimeFormatter formatter;
		LocalDate localDate;
		
		// localDate = LocalDate.parse("2024.09.27"); (x)
		localDate = LocalDate.parse("2024-09-27");
		System.out.println(localDate);
		
		formatter = DateTimeFormatter.ISO_LOCAL_DATE;
		localDate = LocalDate.parse("2024-09-27", formatter);
		System.out.println(localDate);
		
		formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		localDate = LocalDate.parse("2024/09/27", formatter);
		System.out.println(localDate);
		
		formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
		localDate = LocalDate.parse("2024.09.27", formatter);
		System.out.println(localDate);
		
		LocalDateTime now1 = LocalDateTime.now();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 a hh시 mm분 ss초");
		String nowString = now1.format(dateTimeFormatter);
		System.out.println(nowString);
	}
}