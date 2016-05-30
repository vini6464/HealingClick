package com.vinod.comparator;

import java.util.Comparator;

import com.vinod.model.Doctor;

public class DoctorComparator implements Comparator<Doctor> {

	@Override
	public int compare(Doctor o1, Doctor o2) {
		
		return o1.getFirstName().compareToIgnoreCase(o2.getFirstName());
	}

}
