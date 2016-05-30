package com.vinod.model;

import java.sql.Timestamp;

public class Comment {

	int id;
	int forumPostid;
	int doctorId;
	String doctorName;
	int patientId;
	String patientName;
	int pharmacyId;
	String pharmacyName;
	String comment;
	int like;
	Timestamp created;
	String image;
	int otherId;
	
	
	
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getOtherId() {
		return otherId;
	}
	public void setOtherId(int otherId) {
		this.otherId = otherId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getForumPostid() {
		return forumPostid;
	}
	public void setForumPostid(int forumPostid) {
		this.forumPostid = forumPostid;
	}
	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public int getPharmacyId() {
		return pharmacyId;
	}
	public void setPharmacyId(int pharmacyId) {
		this.pharmacyId = pharmacyId;
	}
	public String getPharmacyName() {
		return pharmacyName;
	}
	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	
	
	
	
}
