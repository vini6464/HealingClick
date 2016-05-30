package com.vinod.model;


import java.sql.Timestamp;
import java.util.Date;

public class Pharmacy {
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
	String pharmacyName;
	String licensedTo;
	long landLine;
	long mobile;
	String proprietorName;
	String proprietorAddress;
	long proprietorContact;
    String proprietorEmail;
	String emailId;
	String aboutMe;
	Timestamp creationDate;
	int connect;
	Double deliveryCharge;
	Double discount;
	double rating;
	
	
	
	 public double getRating() {
		return rating;
	}






	public void setRating(double rating) {
		this.rating = rating;
	}
	int verified ;
	    int deleted;
	    
	    
		
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
	
	
	
public Double getDeliveryCharge() {
		return deliveryCharge;
	}






	public void setDeliveryCharge(Double deliveryCharge) {
		this.deliveryCharge = deliveryCharge;
	}






	public Double getDiscount() {
		return discount;
	}






	public void setDiscount(Double discount) {
		this.discount = discount;
	}
Timestamp lastActive;
	
	
	public Timestamp getLastActive() {
		return lastActive;
	}






	public void setLastActive(Timestamp lastActive) {
		this.lastActive = lastActive;
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
	public Pharmacy() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Pharmacy(int id, String userName, String passWord, String firstName,
			String lastName, String image, int gender, Date dob,
			String address1, String address2, String city, String state,
			String country, long pinCode, String landMark, int bloodGroup,
			String pharmacyName, String licensedTo, long landLine, long mobile,
			String proprietorName, String proprietorAddress,
			long proprietorContact, String proprietorEmail, String emailId,
			String aboutMe, Timestamp creationDate) {
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
		this.pharmacyName = pharmacyName;
		this.licensedTo = licensedTo;
		this.landLine = landLine;
		this.mobile = mobile;
		this.proprietorName = proprietorName;
		this.proprietorAddress = proprietorAddress;
		this.proprietorContact = proprietorContact;
		this.proprietorEmail = proprietorEmail;
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
	 * @return the pharmacyName
	 */
	public String getPharmacyName() {
		return pharmacyName;
	}
	/**
	 * @param pharmacyName the pharmacyName to set
	 */
	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}
	/**
	 * @return the licensedTo
	 */
	public String getLicensedTo() {
		return licensedTo;
	}
	/**
	 * @param licensedTo the licensedTo to set
	 */
	public void setLicensedTo(String licensedTo) {
		this.licensedTo = licensedTo;
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
	 * @return the proprietorName
	 */
	public String getProprietorName() {
		return proprietorName;
	}
	/**
	 * @param proprietorName the proprietorName to set
	 */
	public void setProprietorName(String proprietorName) {
		this.proprietorName = proprietorName;
	}
	/**
	 * @return the proprietorAddress
	 */
	public String getProprietorAddress() {
		return proprietorAddress;
	}
	/**
	 * @param proprietorAddress the proprietorAddress to set
	 */
	public void setProprietorAddress(String proprietorAddress) {
		this.proprietorAddress = proprietorAddress;
	}
	/**
	 * @return the proprietorContact
	 */
	public long getProprietorContact() {
		return proprietorContact;
	}
	/**
	 * @param proprietorContact the proprietorContact to set
	 */
	public void setProprietorContact(long proprietorContact) {
		this.proprietorContact = proprietorContact;
	}
	/**
	 * @return the proprietorEmail
	 */
	public String getProprietorEmail() {
		return proprietorEmail;
	}
	/**
	 * @param proprietorEmail the proprietorEmail to set
	 */
	public void setProprietorEmail(String proprietorEmail) {
		this.proprietorEmail = proprietorEmail;
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
		return "Pharmacy [userName=" + userName + ", passWord=" + passWord
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", image=" + image + ", gender=" + gender + ", dob=" + dob
				+ ", address1=" + address1 + ", address2=" + address2
				+ ", city=" + city + ", state=" + state + ", country="
				+ country + ", pinCode=" + pinCode + ", landMark=" + landMark
				+ ", bloodGroup=" + bloodGroup + ", pharmacyName="
				+ pharmacyName + ", licensedTo=" + licensedTo + ", landLine="
				+ landLine + ", mobile=" + mobile + ", proprietorName="
				+ proprietorName + ", proprietorAddress=" + proprietorAddress
				+ ", proprietorContact=" + proprietorContact
				+ ", proprietorEmail=" + proprietorEmail + ", emailId="
				+ emailId + ", aboutMe=" + aboutMe + "]";
	}
	
	

}
