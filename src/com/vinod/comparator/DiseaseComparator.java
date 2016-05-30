package com.vinod.comparator;

import java.util.Comparator;

import com.vinod.model.Disease;

public class DiseaseComparator implements Comparator<Disease> {

	@Override
	public int compare(Disease o1, Disease o2) {
		
		return o1.getName().compareToIgnoreCase(o2.getName());
	}

}
