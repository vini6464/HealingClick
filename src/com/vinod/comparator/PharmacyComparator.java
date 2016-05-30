package com.vinod.comparator;

import java.util.Comparator;

import com.vinod.model.Pharmacy;

public class PharmacyComparator implements Comparator<Pharmacy> {

	@Override
	public int compare(Pharmacy o1, Pharmacy o2) {
		// TODO Auto-generated method stub
		return o1.getPharmacyName().compareToIgnoreCase(o2.getPharmacyName());
	}

}
