package com.vinod.model;

import java.sql.Timestamp;
import java.util.List;

public class Prescription {

	
	long id;
	String doctorName;
	int doctorId;
	String patientName;
	int patientId;
	int s1;
	String sName1;
	int s2;
	String sName2;
	int s3;
	String sName3;
	int s4;
	String sName4;
	int s5;
	String sName5;
	int d1;
	String dName1;
	int d2;
	String dName2;
	int d3;
	String dName3;
	int d4;
	String dName4;
	int d5;
	String dName5;
	int m1;
	int m2;
	public Prescription(int id, String doctorName, int doctorId,
			String patientName, int patientId, int s1, String sName1, int s2,
			String sName2, int s3, String sName3, int s4, String sName4,
			int s5, String sName5, int d1, String dName1, int d2,
			String dName2, int d3, String dName3, int d4, String dName4,
			int d5, String dName5, int m1, int m2, int m3, int m4, int m5,
			String prescriptionPath, String suggestion,
			List<Medicine> medicines, Timestamp prescribedDate, int checkup,
			Timestamp updatedtime, String bp, double sugar, double cholesterol,
			double bmi) {
		super();
		this.id = id;
		this.doctorName = doctorName;
		this.doctorId = doctorId;
		this.patientName = patientName;
		this.patientId = patientId;
		this.s1 = s1;
		this.sName1 = sName1;
		this.s2 = s2;
		this.sName2 = sName2;
		this.s3 = s3;
		this.sName3 = sName3;
		this.s4 = s4;
		this.sName4 = sName4;
		this.s5 = s5;
		this.sName5 = sName5;
		this.d1 = d1;
		this.dName1 = dName1;
		this.d2 = d2;
		this.dName2 = dName2;
		this.d3 = d3;
		this.dName3 = dName3;
		this.d4 = d4;
		this.dName4 = dName4;
		this.d5 = d5;
		this.dName5 = dName5;
		this.m1 = m1;
		this.m2 = m2;
		this.m3 = m3;
		this.m4 = m4;
		this.m5 = m5;
		this.prescriptionPath = prescriptionPath;
		this.suggestion = suggestion;
		this.medicines = medicines;
		this.prescribedDate = prescribedDate;
		this.checkup = checkup;
		this.updatedtime = updatedtime;
		this.bp = bp;
		this.sugar = sugar;
		this.cholesterol = cholesterol;
		this.bmi = bmi;
	}
	int m3;
	int m4;
	int m5;
	String prescriptionPath;
	String suggestion;
	List<Medicine> medicines;
	Timestamp prescribedDate;
	int checkup;
	
	
	Timestamp updatedtime;
	
	
	
	
	public int getCheckup() {
		return checkup;
	}


	public void setCheckup(int checkup) {
		this.checkup = checkup;
	}
	String bp;
	double sugar;
	double cholesterol;
	double bmi;
	
	String bpStatus;
	String sugarStatus;
	String cholesterolStatus;
	String bmiStatus;
	

	
	

	public String getBp() {
		return bp;
	}


	public void setBp(String bp) {
		this.bp = bp;
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

	public void setBmi(double bmi) {
		this.bmi = bmi;
	}
	public double getBmi() {
		return bmi;
	}
	
	public Timestamp getUpdatedtime() {
		return updatedtime;
	}
	public void setUpdatedtime(Timestamp updatedtime) {
		this.updatedtime = updatedtime;
	}
	
	public Timestamp getPrescribedDate() {
		return prescribedDate;
	}
	public void setPrescribedDate(Timestamp prescribedDate) {
		this.prescribedDate = prescribedDate;
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
	public Prescription( int doctorId, int patientId, int s1, int s2,
			int s3, int s4, int s5, int d1, int d2, int d3, int d4, int d5,
			int m1, int m2, int m3, int m4, int m5, String prescriptionPath,
			String suggestion) {
		super();
		this.doctorId = doctorId;
		this.patientId = patientId;
		this.s1 = s1;
		this.s2 = s2;
		this.s3 = s3;
		this.s4 = s4;
		this.s5 = s5;
		this.d1 = d1;
		this.d2 = d2;
		this.d3 = d3;
		this.d4 = d4;
		this.d5 = d5;
		this.m1 = m1;
		this.m2 = m2;
		this.m3 = m3;
		this.m4 = m4;
		this.m5 = m5;
		this.prescriptionPath = prescriptionPath;
		this.suggestion = suggestion;
		
	}
	public Prescription() {
		super();
		
	}
	
	
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public int getS1() {
		return s1;
	}
	public void setS1(int s1) {
		this.s1 = s1;
	}
	public int getS2() {
		return s2;
	}
	public void setS2(int s2) {
		this.s2 = s2;
	}
	public int getS3() {
		return s3;
	}
	public void setS3(int s3) {
		this.s3 = s3;
	}
	public int getS4() {
		return s4;
	}
	public void setS4(int s4) {
		this.s4 = s4;
	}
	public int getS5() {
		return s5;
	}
	public void setS5(int s5) {
		this.s5 = s5;
	}
	public int getD1() {
		return d1;
	}
	public void setD1(int d1) {
		this.d1 = d1;
	}
	public int getD2() {
		return d2;
	}
	public void setD2(int d2) {
		this.d2 = d2;
	}
	public int getD3() {
		return d3;
	}
	public void setD3(int d3) {
		this.d3 = d3;
	}
	public int getD4() {
		return d4;
	}
	public void setD4(int d4) {
		this.d4 = d4;
	}
	public int getD5() {
		return d5;
	}
	public void setD5(int d5) {
		this.d5 = d5;
	}
	public int getM1() {
		return m1;
	}
	public void setM1(int m1) {
		this.m1 = m1;
	}
	public int getM2() {
		return m2;
	}
	public void setM2(int m2) {
		this.m2 = m2;
	}
	public int getM3() {
		return m3;
	}
	public void setM3(int m3) {
		this.m3 = m3;
	}
	public int getM4() {
		return m4;
	}
	public void setM4(int m4) {
		this.m4 = m4;
	}
	public int getM5() {
		return m5;
	}
	public void setM5(int m5) {
		this.m5 = m5;
	}
	public String getPrescriptionPath() {
		return prescriptionPath;
	}
	public void setPrescriptionPath(String prescriptionPath) {
		this.prescriptionPath = prescriptionPath;
	}
	public String getSuggestion() {
		return suggestion;
	}
	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
	public List<Medicine> getMedicines() {
		return medicines;
	}
	public void setMedicines(List<Medicine> medicines) {
		this.medicines = medicines;
	}
	public String getsName1() {
		return sName1;
	}
	public void setsName1(String sName1) {
		this.sName1 = sName1;
	}
	public String getsName2() {
		return sName2;
	}
	public void setsName2(String sName2) {
		this.sName2 = sName2;
	}
	public String getsName3() {
		return sName3;
	}
	public void setsName3(String sName3) {
		this.sName3 = sName3;
	}
	public String getsName4() {
		return sName4;
	}
	public void setsName4(String sName4) {
		this.sName4 = sName4;
	}
	public String getsName5() {
		return sName5;
	}
	public void setsName5(String sName5) {
		this.sName5 = sName5;
	}
	public String getdName1() {
		return dName1;
	}
	public void setdName1(String dName1) {
		this.dName1 = dName1;
	}
	public String getdName2() {
		return dName2;
	}
	public void setdName2(String dName2) {
		this.dName2 = dName2;
	}
	public String getdName3() {
		return dName3;
	}
	public void setdName3(String dName3) {
		this.dName3 = dName3;
	}
	public String getdName4() {
		return dName4;
	}
	public void setdName4(String dName4) {
		this.dName4 = dName4;
	}
	public String getdName5() {
		return dName5;
	}
	public void setdName5(String dName5) {
		this.dName5 = dName5;
	}


	@Override
	public String toString() {
		return "Prescription [id=" + id + ", doctorName=" + doctorName
				+ ", doctorId=" + doctorId + ", patientName=" + patientName
				+ ", patientId=" + patientId + ", s1=" + s1 + ", sName1="
				+ sName1 + ", s2=" + s2 + ", sName2=" + sName2 + ", s3=" + s3
				+ ", sName3=" + sName3 + ", s4=" + s4 + ", sName4=" + sName4
				+ ", s5=" + s5 + ", sName5=" + sName5 + ", d1=" + d1
				+ ", dName1=" + dName1 + ", d2=" + d2 + ", dName2=" + dName2
				+ ", d3=" + d3 + ", dName3=" + dName3 + ", d4=" + d4
				+ ", dName4=" + dName4 + ", d5=" + d5 + ", dName5=" + dName5
				+ ", m1=" + m1 + ", m2=" + m2 + ", m3=" + m3 + ", m4=" + m4
				+ ", m5=" + m5 + ", prescriptionPath=" + prescriptionPath
				+ ", suggestion=" + suggestion + ", medicines=" + medicines
				+ ", prescribedDate=" + prescribedDate + ", checkup=" + checkup
				+ ", updatedtime=" + updatedtime + ", bp=" + bp + ", sugar="
				+ sugar + ", cholesterol=" + cholesterol + ", bmi=" + bmi + "]";
	}
	
	
	
	
}
