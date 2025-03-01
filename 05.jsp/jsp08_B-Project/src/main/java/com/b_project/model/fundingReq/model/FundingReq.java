package com.b_project.model.fundingReq.model;

import java.util.Date;

public class FundingReq {

	private int reqNo;
	private String subject;
	private String id;
	private Date wrtDate;
	private Date mod_date;
	private String content;
	private String file;
	
	
	public int getReqNo() {
		return reqNo;
	}
	public void setReqNo(int reqNo) {
		this.reqNo = reqNo;
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
	public Date getWrtDate() {
		return wrtDate;
	}
	public void setWrtDate(Date wrtDate) {
		this.wrtDate = wrtDate;
	}
	public Date getMod_date() {
		return mod_date;
	}
	public void setMod_date(Date mod_date) {
		this.mod_date = mod_date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
}
