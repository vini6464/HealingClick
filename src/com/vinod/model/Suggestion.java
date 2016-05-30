package com.vinod.model;

import java.sql.Timestamp;
import java.util.List;

public class Suggestion {

	int id;
	String name;
	String content;
	int doctorId;
	String doctorName;
	int patientId;
	String patientName;
    int pharmacyId;
	String pharmacyName;
	int likes;
	int comments;
	int liked;
	List<Comment> comment;
	Timestamp creationDate;
	String forumImage;
	
	
	
	
	public Suggestion() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getForumImage() {
		return forumImage;
	}
	public void setForumImage(String forumImage) {
		this.forumImage = forumImage;
	}
	public int getLiked() {
		return liked;
	}
	public void setLiked(int liked) {
		this.liked = liked;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public int getComments() {
		return comments;
	}
	public void setComments(int comments) {
		this.comments = comments;
	}
	public List<Comment> getComment() {
		return comment;
	}
	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}
	public Timestamp getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}
	
	@Override
	public String toString() {
		return "ForumPost [id=" + id + ", name=" + name + ", content="
				+ content + ", doctorId=" + doctorId + ", doctorName="
				+ doctorName + ", patientId=" + patientId + ", patientName="
				+ patientName + ", pharmacyId=" + pharmacyId
				+ ", pharmacyName=" + pharmacyName + ", likes=" + likes
				+ ", comments=" + comments + ", liked=" + liked + ", comment="
				+ comment + ", creationDate=" + creationDate + ", forumImage="
				+ forumImage + "]";
	}
}
