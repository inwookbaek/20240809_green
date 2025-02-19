package com.b_project.model.question.model;

import java.util.Date;

public class Answer {

	private int qNo;
	private Date wrtDate;
	private Date modDate;
	private String content;
	public int getqNo() {
		return qNo;
	}
	public void setqNo(int qNo) {
		this.qNo = qNo;
	}
	public Date getWrtDate() {
		return wrtDate;
	}
	public void setWrtDate(Date wrtDate) {
		this.wrtDate = wrtDate;
	}
	public Date getModDate() {
		return modDate;
	}
	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
