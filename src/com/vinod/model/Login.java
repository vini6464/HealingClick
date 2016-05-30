package com.vinod.model;

public class Login {

	int type;
	int id;
	String userName;
	String email;
	String password;
	
	public Login(int type, int id, String userName, String email,
			String password) {
		super();
		this.type = type;
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Login [type=" + type + ", id=" + id + ", userName=" + userName
				+ ", email=" + email + ", password=" + password + "]";
	}
	
	
	
}
