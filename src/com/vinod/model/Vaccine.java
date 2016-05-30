package com.vinod.model;

public class Vaccine {

	long patientId;
	int BCG;
	int DPT_Polio_and_Hepatitis_B_1;
	int DPT_Polio_and_Hepatitis_B_2;
	int DPT_Polio_and_Hepatitis_B_3;
	int measels;
	int MMR;
	int DPT_Polio_and_Hepatitis_B_booster_1;
	int DPT_Polio_and_Hepatitis_B_booster_2;
	int DT;
	int dose_of_tetanus;
	int seasonal;
	
	
	
	
	public long getPatientId() {
		return patientId;
	}




	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}




	public int getBCG() {
		return BCG;
	}




	public void setBCG(int bCG) {
		BCG = bCG;
	}




	public int getDPT_Polio_and_Hepatitis_B_1() {
		return DPT_Polio_and_Hepatitis_B_1;
	}




	public void setDPT_Polio_and_Hepatitis_B_1(int dPT_Polio_and_Hepatitis_B_1) {
		DPT_Polio_and_Hepatitis_B_1 = dPT_Polio_and_Hepatitis_B_1;
	}




	public int getDPT_Polio_and_Hepatitis_B_2() {
		return DPT_Polio_and_Hepatitis_B_2;
	}




	public void setDPT_Polio_and_Hepatitis_B_2(int dPT_Polio_and_Hepatitis_B_2) {
		DPT_Polio_and_Hepatitis_B_2 = dPT_Polio_and_Hepatitis_B_2;
	}




	public int getDPT_Polio_and_Hepatitis_B_3() {
		return DPT_Polio_and_Hepatitis_B_3;
	}




	public void setDPT_Polio_and_Hepatitis_B_3(int dPT_Polio_and_Hepatitis_B_3) {
		DPT_Polio_and_Hepatitis_B_3 = dPT_Polio_and_Hepatitis_B_3;
	}




	




	public int getMeasels() {
		return measels;
	}




	public void setMeasels(int measels) {
		this.measels = measels;
	}




	public int getMMR() {
		return MMR;
	}




	public void setMMR(int mMR) {
		MMR = mMR;
	}




	public int getDPT_Polio_and_Hepatitis_B_booster_1() {
		return DPT_Polio_and_Hepatitis_B_booster_1;
	}




	public void setDPT_Polio_and_Hepatitis_B_booster_1(
			int dPT_Polio_and_Hepatitis_B_booster_1) {
		DPT_Polio_and_Hepatitis_B_booster_1 = dPT_Polio_and_Hepatitis_B_booster_1;
	}




	public int getDPT_Polio_and_Hepatitis_B_booster_2() {
		return DPT_Polio_and_Hepatitis_B_booster_2;
	}




	public void setDPT_Polio_and_Hepatitis_B_booster_2(
			int dPT_Polio_and_Hepatitis_B_booster_2) {
		DPT_Polio_and_Hepatitis_B_booster_2 = dPT_Polio_and_Hepatitis_B_booster_2;
	}




	public int getDT() {
		return DT;
	}




	public void setDT(int dT) {
		DT = dT;
	}




	public int getDose_of_tetanus() {
		return dose_of_tetanus;
	}




	public void setDose_of_tetanus(int dose_of_tetanus) {
		this.dose_of_tetanus = dose_of_tetanus;
	}




	public int getSeasonal() {
		return seasonal;
	}




	public void setSeasonal(int seasonal) {
		this.seasonal = seasonal;
	}




	public Vaccine(int bCG, int dPT_Polio_and_Hepatitis_B_1,
			int dPT_Polio_and_Hepatitis_B_2, int dPT_Polio_and_Hepatitis_B_3,
			int measels, int mMR, int dPT_Polio_and_Hepatitis_B_booster_1,
			int dPT_Polio_and_Hepatitis_B_booster_2, int dT,
			int dose_of_tetanus, int seasonal) {
		super();
		BCG = bCG;
		DPT_Polio_and_Hepatitis_B_1 = dPT_Polio_and_Hepatitis_B_1;
		DPT_Polio_and_Hepatitis_B_2 = dPT_Polio_and_Hepatitis_B_2;
		DPT_Polio_and_Hepatitis_B_3 = dPT_Polio_and_Hepatitis_B_3;
		measels = measels;
		MMR = mMR;
		DPT_Polio_and_Hepatitis_B_booster_1 = dPT_Polio_and_Hepatitis_B_booster_1;
		DPT_Polio_and_Hepatitis_B_booster_2 = dPT_Polio_and_Hepatitis_B_booster_2;
		DT = dT;
		this.dose_of_tetanus = dose_of_tetanus;
		this.seasonal = seasonal;
	}




	public Vaccine() {
		
	}




	@Override
	public String toString() {
		return "Vaccine [patientId=" + patientId + ", BCG=" + BCG
				+ ", DPT_Polio_and_Hepatitis_B_1="
				+ DPT_Polio_and_Hepatitis_B_1
				+ ", DPT_Polio_and_Hepatitis_B_2="
				+ DPT_Polio_and_Hepatitis_B_2
				+ ", DPT_Polio_and_Hepatitis_B_3="
				+ DPT_Polio_and_Hepatitis_B_3 + ", Measels=" + measels
				+ ", MMR=" + MMR + ", DPT_Polio_and_Hepatitis_B_booster_1="
				+ DPT_Polio_and_Hepatitis_B_booster_1
				+ ", DPT_Polio_and_Hepatitis_B_booster_2="
				+ DPT_Polio_and_Hepatitis_B_booster_2 + ", DT=" + DT
				+ ", dose_of_tetanus=" + dose_of_tetanus + ", seasonal="
				+ seasonal + "]";
	}

	
}
