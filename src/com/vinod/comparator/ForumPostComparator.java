package com.vinod.comparator;

import java.util.Comparator;

import com.vinod.model.ForumPost;

public class ForumPostComparator implements Comparator<ForumPost> {

	@Override
	public int compare(ForumPost r1, ForumPost r2) {
		return r2.getCreationDate().compareTo(r1.getCreationDate());
	}

}
