package com.b_project.model.question.model;

import java.util.Date;

public class Question {

	private int qNo;
	private String subject;
	private String id;
	private String name;
	private Date wrtDate;
	private Date modDate;
	private String content;
	private int readCNT;
	private boolean faq;
	private boolean cantChange;
	
	
	public int getqNo() {
		return qNo;
	}
	public void setqNo(int qNo) {
		this.qNo = qNo;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public int getReadCNT() {
		return readCNT;
	}
	public void setReadCNT(int readCNT) {
		this.readCNT = readCNT;
	}
	public boolean isFaq() {
		return faq;
	}
	public void setFaq(boolean faq) {
		this.faq = faq;
	}
	public boolean isCantChange() {
		return cantChange;
	}
	public void setCantChange(boolean cantChange) {
		this.cantChange = cantChange;
	}
	
	
}
