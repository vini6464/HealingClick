package com.vinod.model;

import java.sql.Timestamp;
import java.util.Date;

public class User {

	long id;
	String firstName;
	String lastName;
	String userName;
	String image;
	String pharmacyName;
	int type;
	Timestamp lastActive;
	Date creationDate;
	String mail;
	long mobile;
	int view;
	
	
	
	
	public int getView() {
		return view;
	}


	public void setView(int view) {
		this.view = view;
	}


	public Date getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


	public long getMobile() {
		return mobile;
	}


	public void setMobile(long mobile) {
		this.mobile = mobile;
	}


	public Timestamp getLastActive() {
		return lastActive;
	}


	public void setLastActive(Timestamp lastActive) {
		this.lastActive = lastActive;
	}


	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public String getPharmacyName() {
		return pharmacyName;
	}


	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}


	public User(long id, String firstName, String lastName, String userName,
			String image, int type) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.image = image;
		this.type = type;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
	
	
}
