package com.vinod.comparator;

import java.util.Comparator;

import com.vinod.model.Symptom;

public class SymptomComparator implements Comparator<Symptom> {

	@Override
	public int compare(Symptom o1, Symptom o2) {
		
		return o1.getName().compareToIgnoreCase(o2.getName());
	}

}
