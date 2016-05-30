package com.vinod.model;

import java.sql.Timestamp;
import java.util.List;

public class Order {
	long id;
	int orderType;
	int doctorId;
	String doctorName;
	int patientId;
	String patientName;
	int pharmacyId;
	String pharmacyName;
	
	int supplierPharmacyId;
	String supplierPharmacyName;
	
	List<Medicine> medicines;
	double totalCost;
	String address1;
	String address2;
	String city;
	String state;
	String country;
	long pinCode;
	String landMark;
	int cashType;
	int pharmacyApproved;
	long phoneNumber;
	String email;
	Timestamp createdOn;
	Timestamp processedOn;
	Timestamp deliveredOn;
	int status;
	String image;
	String comment;
	
	String transactionId;
	
	
	
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * @return the supplierPharmacyId
	 */
	public int getSupplierPharmacyId() {
		return supplierPharmacyId;
	}
	/**
	 * @param supplierPharmacyId the supplierPharmacyId to set
	 */
	public void setSupplierPharmacyId(int supplierPharmacyId) {
		this.supplierPharmacyId = supplierPharmacyId;
	}
	/**
	 * @return the supplierPharmacyName
	 */
	public String getSupplierPharmacyName() {
		return supplierPharmacyName;
	}
	/**
	 * @param supplierPharmacyName the supplierPharmacyName to set
	 */
	public void setSupplierPharmacyName(String supplierPharmacyName) {
		this.supplierPharmacyName = supplierPharmacyName;
	}
	
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getOrderType() {
		return orderType;
	}
	public void setOrderType(int orderType) {
		this.orderType = orderType;
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
	public List<Medicine> getMedicines() {
		return medicines;
	}
	public void setMedicines(List<Medicine> medicines) {
		this.medicines = medicines;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public long getPinCode() {
		return pinCode;
	}
	public void setPinCode(long pinCode) {
		this.pinCode = pinCode;
	}
	public String getLandMark() {
		return landMark;
	}
	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}
	public int getCashType() {
		return cashType;
	}
	public void setCashType(int cashType) {
		this.cashType = cashType;
	}
	public int getPharmacyApproved() {
		return pharmacyApproved;
	}
	public void setPharmacyApproved(int pharmacyApproved) {
		this.pharmacyApproved = pharmacyApproved;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Timestamp getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}
	public Timestamp getProcessedOn() {
		return processedOn;
	}
	public void setProcessedOn(Timestamp processedOn) {
		this.processedOn = processedOn;
	}
	public Timestamp getDeliveredOn() {
		return deliveredOn;
	}
	public void setDeliveredOn(Timestamp deliveredOn) {
		this.deliveredOn = deliveredOn;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", orderType=" + orderType + ", doctorId="
				+ doctorId + ", doctorName=" + doctorName + ", patientId="
				+ patientId + ", patientName=" + patientName + ", pharmacyId="
				+ pharmacyId + ", pharmacyName=" + pharmacyName
				+ ", supplierPharmacyId=" + supplierPharmacyId
				+ ", supplierPharmacyName=" + supplierPharmacyName
				+ ", medicines=" + medicines + ", totalCost=" + totalCost
				+ ", address1=" + address1 + ", address2=" + address2
				+ ", city=" + city + ", state=" + state + ", country="
				+ country + ", pinCode=" + pinCode + ", landMark=" + landMark
				+ ", cashType=" + cashType + ", pharmacyApproved="
				+ pharmacyApproved + ", phoneNumber=" + phoneNumber
				+ ", email=" + email + ", createdOn=" + createdOn
				+ ", processedOn=" + processedOn + ", deliveredOn="
				+ deliveredOn + ", status=" + status + ", image=" + image
				+ ", comment=" + comment + "]";
	}
	
	

}
