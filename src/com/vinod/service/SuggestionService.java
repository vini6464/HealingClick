package com.vinod.service;

import java.util.Collections;
import java.util.List;


import com.vinod.comparator.CommentComparator;
import com.vinod.comparator.SuggestionComparator;
import com.vinod.dao.SuggestionDao;
import com.vinod.exception.DaoException;
import com.vinod.model.Comment;
import com.vinod.model.ForumPost;
import com.vinod.model.Login;
import com.vinod.model.Suggestion;

public class SuggestionService {
	SuggestionDao dao = new SuggestionDao();

	public List<Suggestion> getAllSuggestionForDoctorById(int id) throws DaoException {
		List<Suggestion> posts = dao.getAllSuggestionForDoctorById(id);
		Collections.sort(posts, new SuggestionComparator());
		return posts;
	}

	public List<Suggestion> getAllSuggestionForPatientById(int id) throws DaoException {
		List<Suggestion> posts = dao.getAllSuggestionForPatientById(id);
		Collections.sort(posts, new SuggestionComparator());
		return posts;
	}

	public List<Comment> getAllCommentsBySuggestionId(int id) throws DaoException {
		List<Comment> comments = dao.getAllCommentsBySuggestionId(id);
		Collections.sort(comments, new CommentComparator());
		return comments;
	}

	

	public void saveDoctorSuggestionComment(int postId, int id, String parameter, int fees) throws DaoException {
		dao.saveDoctorSuggestionComment(postId,id,parameter,fees);
		
	}

	public Suggestion getSuggestionById(int postId) throws DaoException {
		Suggestion post = dao.getSuggestionById(postId);
		return post;
	}

	public void savePatientSuggestionComment(int postId, int id,
			String parameter) throws DaoException {
		dao.savePatientSuggestionComment(postId,id,parameter);
		
	}

	public int saveSuggestion(ForumPost post) throws DaoException {
		int i = dao.saveSuggestion(post);
		
		return i;
	}

	public void writeNotification(int id) throws DaoException {
		dao.writeNotification(id);
		
	}

	public void closeNotification(int postId, Login login) throws DaoException {
		dao.closeNotification(postId,login);
		
	}

	public int saveSuggestionCommentPayment(int postId, int commentId, String txnId) throws DaoException {
		
		int i = dao.saveSuggestionCommentPayment(postId,commentId,txnId);
		return i;
		
		
	}

	

	
}
