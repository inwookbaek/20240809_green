package com.b_project.model.funding.model;

import java.util.Date;

public class FundingBean {

	private int fundNo;
	private String id;
	private Date fundDate;
	private int money;
	private int projectNo;
	
	
	public int getFundNo() {
		return fundNo;
	}
	public void setFundNo(int fundNo) {
		this.fundNo = fundNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getFundDate() {
		return fundDate;
	}
	public void setFundDate(Date fundDate) {
		this.fundDate = fundDate;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getProjectNo() {
		return projectNo;
	}
	public void setProjectNo(int projectNo) {
		this.projectNo = projectNo;
	}

}
