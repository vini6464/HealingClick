package com.vinod.comparator;

import java.util.Comparator;

import com.vinod.model.Suggestion;



public class SuggestionComparator implements Comparator<Suggestion>{

	@Override
	public int compare(Suggestion r1, Suggestion r2) {
		return r2.getCreationDate().compareTo(r1.getCreationDate());
	}

}
