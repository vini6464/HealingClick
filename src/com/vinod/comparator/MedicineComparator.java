package com.vinod.comparator;

import java.util.Comparator;

import com.vinod.model.Medicine;

public class MedicineComparator implements Comparator<Medicine> {

	@Override
	public int compare(Medicine o1, Medicine o2) {
		
		return o1.getName().compareToIgnoreCase(o2.getName());
	}

}
