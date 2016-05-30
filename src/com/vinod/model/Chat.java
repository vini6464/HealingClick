package com.vinod.model;

import java.sql.Timestamp;

public class Chat {

	
	int chatId;
	long doctorId;
	String doctorName;
	long patientId;
	String patientName;
	long pharmacyId;
	String pharmacyName;
	long otherDoctorId;
	String otherDoctorName;
	long otherPatientId;
	String otherPatientName;
	long otherPharmacyId;
	String otherPharmacyName;
	String doctorImage;
	String otherDoctorImage;
	String patientImage;
	String otherPatientImage;
	String pharmacyImage;
	String otherPharmacyImage;
	Timestamp lastActive;
	
	
	
	public Timestamp getLastActive() {
		return lastActive;
	}




	public void setLastActive(Timestamp lastActive) {
		this.lastActive = lastActive;
	}




	public String getDoctorImage() {
		return doctorImage;
	}




	public void setDoctorImage(String doctorImage) {
		this.doctorImage = doctorImage;
	}




	public String getOtherDoctorImage() {
		return otherDoctorImage;
	}




	public void setOtherDoctorImage(String otherDoctorImage) {
		this.otherDoctorImage = otherDoctorImage;
	}




	public String getPatientImage() {
		return patientImage;
	}




	public void setPatientImage(String patientImage) {
		this.patientImage = patientImage;
	}




	public String getOtherPatientImage() {
		return otherPatientImage;
	}




	public void setOtherPatientImage(String otherPatientImage) {
		this.otherPatientImage = otherPatientImage;
	}




	public String getPharmacyImage() {
		return pharmacyImage;
	}




	public void setPharmacyImage(String pharmacyImage) {
		this.pharmacyImage = pharmacyImage;
	}




	public String getOtherPharmacyImage() {
		return otherPharmacyImage;
	}




	public void setOtherPharmacyImage(String otherPharmacyImage) {
		this.otherPharmacyImage = otherPharmacyImage;
	}




	public String getDoctorName() {
		return doctorName;
	}




	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}




	public String getPatientName() {
		return patientName;
	}




	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}




	public String getPharmacyName() {
		return pharmacyName;
	}




	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}




	public String getOtherDoctorName() {
		return otherDoctorName;
	}




	public void setOtherDoctorName(String otherDoctorName) {
		this.otherDoctorName = otherDoctorName;
	}




	public String getOtherPatientName() {
		return otherPatientName;
	}




	public void setOtherPatientName(String otherPatientName) {
		this.otherPatientName = otherPatientName;
	}




	public String getOtherPharmacyName() {
		return otherPharmacyName;
	}




	public void setOtherPharmacyName(String otherPharmacyName) {
		this.otherPharmacyName = otherPharmacyName;
	}




	public int getChatId() {
		return chatId;
	}




	public void setChatId(int chatId) {
		this.chatId = chatId;
	}




	public long getDoctorId() {
		return doctorId;
	}




	public void setDoctorId(long doctorId) {
		this.doctorId = doctorId;
	}




	public long getPatientId() {
		return patientId;
	}




	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}




	public long getPharmacyId() {
		return pharmacyId;
	}




	public void setPharmacyId(long pharmacyId) {
		this.pharmacyId = pharmacyId;
	}




	public long getOtherDoctorId() {
		return otherDoctorId;
	}

	
	



	public void setOtherDoctorId(long otherDoctorId) {
		this.otherDoctorId = otherDoctorId;
	}




	public long getOtherPatientId() {
		return otherPatientId;
	}




	public void setOtherPatientId(long otherPatientId) {
		this.otherPatientId = otherPatientId;
	}




	public long getOtherPharmacyId() {
		return otherPharmacyId;
	}




	public void setOtherPharmacyId(long otherPharmacyId) {
		this.otherPharmacyId = otherPharmacyId;
	}




	@Override
	public String toString() {
		return "Chat [chatId=" + chatId + ", doctorId=" + doctorId
				+ ", patientId=" + patientId + ", pharmacyId=" + pharmacyId
				+ ", otherDoctorId=" + otherDoctorId + ", otherPatientId="
				+ otherPatientId + ", otherPharmacyId=" + otherPharmacyId + "]";
	}




	public Chat() {
		// TODO Auto-generated constructor stub
	}

}
