package com.vinod.comparator;

import java.util.Comparator;


import com.vinod.model.Post;

public class PostComparator implements Comparator<Post> {
	@Override
	public int compare(Post r1, Post r2) {
		return r2.getCreationDate().compareTo(r1.getCreationDate());
	}

}
