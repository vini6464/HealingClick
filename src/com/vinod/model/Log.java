package com.vinod.model;

import java.sql.Timestamp;

public class Log {

	int id;
	int type;
	String content;
	Timestamp creationDate;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}
	public Log(int id, int type, String content, Timestamp creationDate) {
		super();
		this.id = id;
		this.type = type;
		this.content = content;
		this.creationDate = creationDate;
	}
	
	public Log(int id, int type, String content) {
		super();
		this.id = id;
		this.type = type;
		this.content = content;
	}
	public Log() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Log [id=" + id + ", type=" + type + ", content=" + content
				+ ", creationDate=" + creationDate + "]";
	}
	
	
	
	
}
