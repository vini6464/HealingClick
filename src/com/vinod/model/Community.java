package com.vinod.model;

import java.sql.Timestamp;
import java.util.List;

public class Community {

	int id;
	String title;
	String description;
	int doctorId;
	String doctorName;
	String doctorImage;
	int likes;
	int comments;
	int liked;
	List<Doctor> doctors;
	Timestamp creationDate;
	String filePath;
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
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


	public int getLiked() {
		return liked;
	}


	public void setLiked(int liked) {
		this.liked = liked;
	}


	public List<Doctor> getDoctors() {
		return doctors;
	}


	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}


	public Timestamp getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}


	public String getFilePath() {
		return filePath;
	}


	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


	public String getDoctorImage() {
		return doctorImage;
	}


	public void setDoctorImage(String doctorImage) {
		this.doctorImage = doctorImage;
	}


	public Community() {
		
	}

}
