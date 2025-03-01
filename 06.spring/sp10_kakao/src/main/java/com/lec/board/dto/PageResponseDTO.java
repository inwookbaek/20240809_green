package com.lec.board.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageResponseDTO<E> {

	private int page;
	private int size;
	private int total;
	
	private int start; // 시작페이지번호
	private int end;   // 끝페이지번호
	
	private boolean prev; // 이전페이지존재여부
	private boolean next; // 다음페이지존재여부
	
	private List<E> dtoList;
	
	@Builder(builderMethodName = "withAll") // Builder()대신에 withAll()
	public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total) {
		
		if(total <= 0) {
			return;
		}
		
		this.page = pageRequestDTO.getPage();
		this.size = pageRequestDTO.getSize();
		
		this.total = total;
		this.dtoList = dtoList;
		
		this.end = (int)(Math.ceil(this.page / 10.0)) * 10;
		this.start = this.end - 9;
		int last = (int)(Math.ceil((total / (double)size)));
		this.end = end > last ? last : end;
		this.prev = this.start > 1;
		this.next = total > this.end * this.size;
	}
}














