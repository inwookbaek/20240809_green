package com.lec.board.vo;

public class ActionForward {

	private boolean isRedirect = false;
	private String path = null;
	
	public boolean isRedirect() {
		return isRedirect;
	}
	
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	
	public String getPath() {
		return path;  // boardList.do?p=10&w=작성자&q=제목&fn=파일명
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	

}
