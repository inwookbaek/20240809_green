package com.b_project.model.review.model;

import java.sql.Date;

public class ReviewBoardBean {

	private int reviewNo;
	private String reviewId;
	private String reviewSubject;
	private String reviewContent;
	private int reviewReadCount;
	private Date reviewDate;
	private String reviewCategory;
	private String reviewFile;
	

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
		
	}

	public void setReviewSubject(String reviewSubject) {
		this.reviewSubject = reviewSubject;
		
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
		
	}

	public void setReviewReadCount(int reviewReadCount) {
		this.reviewReadCount = reviewReadCount;
		
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
		
	}

	public void setReviewCategory(String reviewCategory) {
		this.reviewCategory = reviewCategory;
		
	}

	public void setReviewFile(String reviewFile) {
		this.reviewFile = reviewFile;
	}

	public String getReviewId() {
		return reviewId;
	}

	public void setReviewId(String reviewId) {
		this.reviewId = reviewId;
	}

	public int getReviewNo() {
		return reviewNo;
	}
	
	public String getReviewFile() {
		return reviewFile;
	}


	public String getReviewSubject() {
		return reviewSubject;
	}

	public String getReviewContent() {
		return reviewContent;
	}

	public int getReviewReadCount() {
		return reviewReadCount;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public String getReviewCategory() {
		return reviewCategory;
	}


}
