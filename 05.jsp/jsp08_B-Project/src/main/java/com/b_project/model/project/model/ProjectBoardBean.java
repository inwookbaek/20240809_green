package com.b_project.model.project.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProjectBoardBean {

	private int projectNo;
	private String subject;
	private Date wrtDate;
	private Date modDate;
	private Date endDate;
	private String content;
	private int objFund;
	private int nowFund;
	private int supporterNo;
	private String category;
	private int readCnt;
	private String attachedFile;
	private String creator;
	
	public int getProjectNo() {
		return projectNo;
	}
	public void setProjectNo(int projectNo) {
		this.projectNo = projectNo;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
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
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getObjFund() {
		return objFund;
	}
	public void setObjFund(int objFund) {
		this.objFund = objFund;
	}
	public int getNowFund() {
		return nowFund;
	}
	public void setNowFund(int nowFund) {
		this.nowFund = nowFund;
	}
	public int getSupporterNo() {
		return supporterNo;
	}
	public void setSupporterNo(int supporterNo) {
		this.supporterNo = supporterNo;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getReadCnt() {
		return readCnt;
	}
	public void setReadCnt(int readCnt) {
		this.readCnt = readCnt;
	}
	public String getAttachedFile() {
		return attachedFile;
	}
	public void setAttachedFile(String attachedFile) {
		this.attachedFile = attachedFile;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
}
