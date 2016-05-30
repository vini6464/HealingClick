package com.vinod.comparator;

import java.util.Comparator;

import com.vinod.model.Prescription;

public class PrescriptionComparator implements Comparator<Prescription> {

	@Override
	public int compare(Prescription o1, Prescription o2) {
		
		return o2.getPrescribedDate().compareTo(o1.getPrescribedDate());
	}

}
