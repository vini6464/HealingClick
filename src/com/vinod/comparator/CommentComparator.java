package com.vinod.comparator;

import java.util.Comparator;

import com.vinod.model.Comment;



public class CommentComparator implements Comparator<Comment>{

	@Override
	public int compare(Comment r1, Comment r2) {
		return r2.getCreated().compareTo(r1.getCreated());
	}

}
