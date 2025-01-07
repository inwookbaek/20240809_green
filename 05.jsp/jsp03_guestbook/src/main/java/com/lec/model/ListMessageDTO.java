package com.lec.model;

import java.util.List;

public class ListMessageDTO {
	
	private int messageTotalCount;
	private int currentPageNumber;
	private List<MessageDTO> listMessage;
	private int pageTotalCount;
	private int messageCountPerPage;
	private int firstRow;
	private int endRow;
	
	public ListMessageDTO(List<MessageDTO> listMessage,
			int messageTotalCount,
			int currentPageNumber,
			int messageCountPerPage,
			int firstRow,
			int endRow
			) {
		this.listMessage = listMessage;
		this.messageTotalCount = messageTotalCount;
		this.currentPageNumber = currentPageNumber;
		this.messageCountPerPage = messageCountPerPage;
		this.firstRow = firstRow;
		this.endRow = endRow;
		
		calculatePageTotalCount();
	}

	private void calculatePageTotalCount() {
		if(messageTotalCount == 0) {
			pageTotalCount = 0;
		} else {
			pageTotalCount = messageTotalCount / messageCountPerPage;
			if(messageTotalCount % messageCountPerPage > 0) pageTotalCount++;
		}
	}
	
	public boolean isEmpty() {
		return messageTotalCount == 0;
	}

	public int getMessageTotalCount() {
		return messageTotalCount;
	}

	public int getCurrentPageNumber() {
		return currentPageNumber;
	}

	public List<MessageDTO> getListMessage() {
		return listMessage;
	}

	public int getPageTotalCount() {
		return pageTotalCount;
	}

	public int getMessageCountPerPage() {
		return messageCountPerPage;
	}

	public int getFirstRow() {
		return firstRow;
	}

	public int getEndRow() {
		return endRow;
	}
	
	
}
