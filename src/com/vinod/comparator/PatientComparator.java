package com.vinod.comparator;

import java.util.Comparator;

import com.vinod.model.Patient;

public class PatientComparator implements Comparator<Patient> {

	@Override
	public int compare(Patient o1, Patient o2) {
		// TODO Auto-generated method stub
		return o1.getFirstName().compareToIgnoreCase(o2.getFirstName());
	}

}
