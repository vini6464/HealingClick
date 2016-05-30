package com.vinod.model;

import java.sql.Timestamp;

public class Review {

	long pharmacyId;
	long reviewerId;
	String reviewerName;
	int type ;
	String comment;
	double rating;
	int deleted;
	Timestamp creationDate;
	
	public Review(long pharmacyId, long reviewerId, int type, String comment,
			double rating) {
		super();
		this.pharmacyId = pharmacyId;
		this.reviewerId = reviewerId;
		this.type = type;
		this.comment = comment;
		this.rating = rating;
	}

	public Review() {
		super();
		
	}

	public String getReviewerName() {
		return reviewerName;
	}

	public void setReviewerName(String reviewerName) {
		this.reviewerName = reviewerName;
	}

	public long getPharmacyId() {
		return pharmacyId;
	}

	public void setPharmacyId(long pharmacyId) {
		this.pharmacyId = pharmacyId;
	}

	public long getReviewerId() {
		return reviewerId;
	}

	public void setReviewerId(long reviewerId) {
		this.reviewerId = reviewerId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}
	
	
	
	
	
}
