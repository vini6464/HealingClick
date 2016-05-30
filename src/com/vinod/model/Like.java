package com.vinod.model;

import java.sql.Timestamp;

public class Like {

	int forumPostid;
	int doctorId;
	String doctorName;
	int patientId;
	String patientName;
	int pharmacyId;
	String pharmacyName;
	Timestamp created;
	public Like() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getForumPostid() {
		return forumPostid;
	}
	public void setForumPostid(int forumPostid) {
		this.forumPostid = forumPostid;
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
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	
	
	
}
