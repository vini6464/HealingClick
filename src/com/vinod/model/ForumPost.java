package com.vinod.model;

import java.sql.Timestamp;
import java.util.List;

public class ForumPost {

	/**
	 * 
	 */
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
	int type;
	List<Comment> comment;
	Timestamp creationDate;
	String forumImage;
	
	int otherId;
	String otherName;
	
	
	
	
	public String getOtherName() {
		return otherName;
	}
	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}
	public int getOtherId() {
		return otherId;
	}
	public void setOtherId(int otherId) {
		this.otherId = otherId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
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
	public ForumPost(int id, String name, String content, int doctorId,
			String doctorName, int patientId, String patientName,
			int pharmacyId, String pharmacyName, int likes, int comments,
			List<Comment> comment, Timestamp creationDate) {
		super();
		this.id = id;
		this.name = name;
		this.content = content;
		this.doctorId = doctorId;
		this.doctorName = doctorName;
		this.patientId = patientId;
		this.patientName = patientName;
		this.pharmacyId = pharmacyId;
		this.pharmacyName = pharmacyName;
		this.likes = likes;
		this.comments = comments;
		this.comment = comment;
		this.creationDate = creationDate;
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
	public ForumPost() {
		super();
		// TODO Auto-generated constructor stub
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
