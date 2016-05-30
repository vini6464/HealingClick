package com.vinod.model;

import java.sql.Timestamp;
import java.util.List;

public class Post {

	int id;
	int postType;
	int privacy;
	String status;
	int doctorId;
	String doctorName;
	int likes;
	int comments;
	int liked;
	List<Comment> comment;
	Timestamp creationDate;
	String filePath;
	
	String image;
	
	List<Like> like;
	int communityId;
	public int getCommunityId() {
		return communityId;
	}



	public void setCommunityId(int communityId) {
		this.communityId = communityId;
	}



	public String getImage() {
		return image;
	}



	public List<Like> getLike() {
		return like;
	}



	public void setLike(List<Like> like) {
		this.like = like;
	}



	public void setImage(String image) {
		this.image = image;
	}



	public Post(int id, int postType, int privacy, String status, int doctorId,
			String doctorName, int likes, int comments, int liked,
			List<Comment> comment, Timestamp creationDate, String filePath) {
		super();
		this.id = id;
		this.postType = postType;
		this.privacy = privacy;
		this.status = status;
		this.doctorId = doctorId;
		this.doctorName = doctorName;
		this.likes = likes;
		this.comments = comments;
		this.liked = liked;
		this.comment = comment;
		this.creationDate = creationDate;
		this.filePath = filePath;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getPostType() {
		return postType;
	}



	public void setPostType(int postType) {
		this.postType = postType;
	}



	public int getPrivacy() {
		return privacy;
	}



	public void setPrivacy(int privacy) {
		this.privacy = privacy;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
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



	public String getFilePath() {
		return filePath;
	}



	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}



	public Post() {
		// TODO Auto-generated constructor stub
	}

}
