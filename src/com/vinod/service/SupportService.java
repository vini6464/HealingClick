package com.vinod.service;

import java.util.Collections;
import java.util.List;

import com.vinod.comparator.CommentComparator;
import com.vinod.comparator.ForumPostComparator;
import com.vinod.dao.SupportDao;
import com.vinod.exception.DaoException;
import com.vinod.model.Comment;
import com.vinod.model.ForumPost;
import com.vinod.model.Login;

public class SupportService {

	SupportDao dao = new SupportDao();
	
	public List<ForumPost> getAllQuestions(Login login) throws DaoException {
		List<ForumPost> posts = dao.getAllQuestions(login);
		Collections.sort(posts, new ForumPostComparator());
		return posts;
	}

	public List<Comment> getAllQuestionCommentsById(int id) throws DaoException {
		List<Comment> comments = dao.getAllQuestionCommentsById(id);
		Collections.sort(comments, new CommentComparator());
		return comments;
	}

	public ForumPost getSupportById(int postId , int type) throws DaoException {
		ForumPost post = dao.getSupportById(postId , type);
		return post;
	}

	public List<Comment> getAllSupportCommentsById(int id) throws DaoException {
		List<Comment> comments = dao.getAllSupportCommentsById(id);
		Collections.sort(comments, new CommentComparator());
		return comments;
	}

	public void saveSupportComment(int postId, int id, String comment) throws DaoException {
		dao.saveSupportComment(postId,id,comment);
		
	}

}
