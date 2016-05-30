package com.vinod.model;


import java.sql.Timestamp;
import java.util.Date;

public class Patient {
	int id;
	String userName;
	String passWord;
	String firstName;
	String lastName;
	String image; 
	int gender;
	Date dob;
	String address1;
	String address2;
	String city;
	String state;
	String country;
	long pinCode;
	String landMark;
	int bloodGroup;
	long landLine;
	long mobile;
	long emergency1;
	long emergency2;
	String emailId;
	String aboutMe;
	Timestamp creationDate;
	int connect;
	
	String bp;
	double sugar;
	double cholesterol;
	double bmi;
	
	String bpStatus;
	String sugarStatus;
	String cholesterolStatus;
	String bmiStatus;
	
	int view;
	
    Timestamp lastActive;
	
    int verified ;
    int deleted;
    
    int privacy;
	
	
	
	public int getPrivacy() {
		return privacy;
	}






	public String getBpStatus() {
		return bpStatus;
	}






	public void setBpStatus(String bpStatus) {
		this.bpStatus = bpStatus;
	}






	public String getSugarStatus() {
		return sugarStatus;
	}






	public void setSugarStatus(String sugarStatus) {
		this.sugarStatus = sugarStatus;
	}






	public String getCholesterolStatus() {
		return cholesterolStatus;
	}






	public void setCholesterolStatus(String cholesterolStatus) {
		this.cholesterolStatus = cholesterolStatus;
	}






	public String getBmiStatus() {
		return bmiStatus;
	}






	public void setBmiStatus(String bmiStatus) {
		this.bmiStatus = bmiStatus;
	}






	public void setPrivacy(int privacy) {
		this.privacy = privacy;
	}
	
	public int getVerified() {
		return verified;
	}






	public void setVerified(int verified) {
		this.verified = verified;
	}






	public int getDeleted() {
		return deleted;
	}






	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}






	public Timestamp getLastActive() {
		return lastActive;
	}






	public void setLastActive(Timestamp lastActive) {
		this.lastActive = lastActive;
	}
	
	
	public int getView() {
		return view;
	}


	public void setView(int view) {
		this.view = view;
	}


	public String getBp() {
		return bp;
	}


	public void setBp(String bp) {
		this.bp = bp;
	}


	public double getSugar() {
		return sugar;
	}


	public void setSugar(double sugar) {
		this.sugar = sugar;
	}


	public double getCholesterol() {
		return cholesterol;
	}

	public void setCholesterol(double cholesterol) {
		this.cholesterol = cholesterol;
	}

	public double getBmi() {
		return bmi;
	}






	public void setBmi(double bmi) {
		this.bmi = bmi;
	}






	public int getConnect() {
		return connect;
	}






	public void setConnect(int connect) {
		this.connect = connect;
	}
	/**
	 * @return the creationDate
	 */
	public Timestamp getCreationDate() {
		return creationDate;
	}
	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}
	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Patient(int id, String userName, String passWord, String firstName,
			String lastName, String image, int gender, Date dob,
			String address1, String address2, String city, String state,
			String country, long pinCode, String landMark, int bloodGroup,
			long landLine, long mobile, long emergency1, long emergency2,
			String emailId, String aboutMe, Timestamp creationDate) {
		super();
		this.id = id;
		this.userName = userName;
		this.passWord = passWord;
		this.firstName = firstName;
		this.lastName = lastName;
		this.image = image;
		this.gender = gender;
		this.dob = dob;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pinCode = pinCode;
		this.landMark = landMark;
		this.bloodGroup = bloodGroup;
		this.landLine = landLine;
		this.mobile = mobile;
		this.emergency1 = emergency1;
		this.emergency2 = emergency2;
		this.emailId = emailId;
		this.aboutMe = aboutMe;
		this.creationDate = creationDate;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the passWord
	 */
	public String getPassWord() {
		return passWord;
	}
	/**
	 * @param passWord the passWord to set
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * @return the gender
	 */
	public int getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(int gender) {
		this.gender = gender;
	}
	/**
	 * @return the dob
	 */
	public Date getDob() {
		return dob;
	}
	/**
	 * @param dob the dob to set
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}
	/**
	 * @return the address1
	 */
	public String getAddress1() {
		return address1;
	}
	/**
	 * @param address1 the address1 to set
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	/**
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}
	/**
	 * @param address2 the address2 to set
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the pinCode
	 */
	public long getPinCode() {
		return pinCode;
	}
	/**
	 * @param pinCode the pinCode to set
	 */
	public void setPinCode(long pinCode) {
		this.pinCode = pinCode;
	}
	/**
	 * @return the landMark
	 */
	public String getLandMark() {
		return landMark;
	}
	/**
	 * @param landMark the landMark to set
	 */
	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}
	/**
	 * @return the bloodGroup
	 */
	public int getBloodGroup() {
		return bloodGroup;
	}
	/**
	 * @param i the bloodGroup to set
	 */
	public void setBloodGroup(int i) {
		this.bloodGroup = i;
	}
	/**
	 * @return the landLine
	 */
	public long getLandLine() {
		return landLine;
	}
	/**
	 * @param landLine the landLine to set
	 */
	public void setLandLine(long landLine) {
		this.landLine = landLine;
	}
	/**
	 * @return the mobile
	 */
	public long getMobile() {
		return mobile;
	}
	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	/**
	 * @return the emergency1
	 */
	public long getEmergency1() {
		return emergency1;
	}
	/**
	 * @param emergency1 the emergency1 to set
	 */
	public void setEmergency1(long emergency1) {
		this.emergency1 = emergency1;
	}
	/**
	 * @return the emergency2
	 */
	public long getEmergency2() {
		return emergency2;
	}
	/**
	 * @param emergency2 the emergency2 to set
	 */
	public void setEmergency2(long emergency2) {
		this.emergency2 = emergency2;
	}
	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}
	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	/**
	 * @return the aboutMe
	 */
	public String getAboutMe() {
		return aboutMe;
	}
	/**
	 * @param aboutMe the aboutMe to set
	 */
	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Patient [userName=" + userName + ", passWord=" + passWord
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", image=" + image + ", gender=" + gender + ", dob=" + dob
				+ ", address1=" + address1 + ", address2=" + address2
				+ ", city=" + city + ", state=" + state + ", country="
				+ country + ", pinCode=" + pinCode + ", landMark=" + landMark
				+ ", bloodGroup=" + bloodGroup + ", landLine=" + landLine
				+ ", mobile=" + mobile + ", emergency1=" + emergency1
				+ ", emergency2=" + emergency2 + ", emailId=" + emailId
				+ ", aboutMe=" + aboutMe + "]";
	}



}
