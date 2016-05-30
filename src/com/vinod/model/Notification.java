package com.vinod.model;

import java.sql.Timestamp;

public class Notification {

	long notificationId;
	long senderId;
	String content;
	long recipientId;
	int hasRead;
	int type;
	String image;
	Timestamp creationDate;
	
	




	public String getImage() {
		return image;
	}




	public void setImage(String image) {
		this.image = image;
	}




	public Timestamp getCreationDate() {
		return creationDate;
	}




	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}






	public Notification(long notificationId, long senderId, String content,
			long recipientId, int hasRead, int type) {
		super();
		this.notificationId = notificationId;
		this.senderId = senderId;
		this.content = content;
		this.recipientId = recipientId;
		this.hasRead = hasRead;
		this.type = type;
	}




	public int getType() {
		return type;
	}




	public void setType(int type) {
		this.type = type;
	}




	public long getNotificationId() {
		return notificationId;
	}




	public void setNotificationId(long notificationId) {
		this.notificationId = notificationId;
	}




	public long getSenderId() {
		return senderId;
	}




	public void setSenderId(long senderId) {
		this.senderId = senderId;
	}




	@Override
	public String toString() {
		return "Notification [notificationId=" + notificationId + ", senderId="
				+ senderId + ", content=" + content + ", recipientId="
				+ recipientId + ", hasRead=" + hasRead + "]";
	}




	public String getContent() {
		return content;
	}




	public void setContent(String content) {
		this.content = content;
	}




	public long getRecipientId() {
		return recipientId;
	}




	public void setRecipientId(long recipientId) {
		this.recipientId = recipientId;
	}




	public int getHasRead() {
		return hasRead;
	}




	public void setHasRead(int hasRead) {
		this.hasRead = hasRead;
	}




	public Notification() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
