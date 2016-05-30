package com.vinod.comparator;

import java.util.Comparator;

import com.vinod.model.Review;


public class ReviewComparator implements Comparator<Review> {

	@Override
	public int compare(Review r1, Review r2) {
		return r2.getCreationDate().compareTo(r1.getCreationDate());
	}

}
