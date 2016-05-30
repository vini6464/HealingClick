package com.vinod.model;

import java.sql.Timestamp;

public class Message {

	int chatId;
	String reply;
	int replierId;
	Timestamp creationDate;

	

	@Override
	public String toString() {
		return "Message [chatId=" + chatId + ", reply=" + reply
				+ ", replierId=" + replierId + ", creationDate=" + creationDate
				+ "]";
	}



	public int getChatId() {
		return chatId;
	}



	public void setChatId(int chatId) {
		this.chatId = chatId;
	}



	public String getReply() {
		return reply;
	}



	public void setReply(String reply) {
		this.reply = reply;
	}



	public int getReplierId() {
		return replierId;
	}



	public void setReplierId(int replierId) {
		this.replierId = replierId;
	}



	public Timestamp getCreationDate() {
		return creationDate;
	}



	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}



	public Message(int chatId, String reply, int replierId,
			Timestamp creationDate) {
		super();
		this.chatId = chatId;
		this.reply = reply;
		this.replierId = replierId;
		this.creationDate = creationDate;
	}



	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
