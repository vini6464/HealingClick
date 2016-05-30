package com.vinod.service;

import java.util.Collections;
import java.util.List;

import com.vinod.comparator.CommentComparator;
import com.vinod.comparator.ForumPostComparator;
import com.vinod.dao.ForumDao;
import com.vinod.exception.DaoException;
import com.vinod.model.Comment;
import com.vinod.model.Doctor;
import com.vinod.model.ForumPost;
import com.vinod.model.Login;


public class ForumService {
ForumDao dao = new ForumDao();
	public List<ForumPost> getAllDoctorPost(int id) throws DaoException {
		List<ForumPost> posts = dao.getAllDoctorPost(id);
		dao.getGroupMemberPost(id , posts);
		Collections.sort(posts, new ForumPostComparator());
		return posts;
	}
	public List<Comment> getAllDoctorCommentsById(int id) throws DaoException {
		List<Comment> comments = dao.getAllDoctorCommentsById(id);
		Collections.sort(comments, new CommentComparator());
		return comments;
	}
	public int checkDoctorLikedOrNot(int postId, int doctorId) throws DaoException {
		int i = dao.checkDoctorLikedOrNot(postId,doctorId);
		return i;
	}
	public int saveDoctorPost(ForumPost post) throws DaoException {
		int i=dao.saveDoctorPost(post);
		return i;
	}

	
	
	public List<ForumPost> getAllPatientPost() throws DaoException {
		List<ForumPost> posts = dao.getAllPatientPost();
		Collections.sort(posts, new ForumPostComparator());
		return posts;
	}
	public List<Comment> getAllPatientCommentsById(int id) throws DaoException {
		List<Comment> comments = dao.getAllPatientCommentsById(id);
		Collections.sort(comments, new CommentComparator());
		return comments;
	}
	public int checkPatientLikedOrNot(int postId, int patientId) throws DaoException {
		int i = dao.checkPatientLikedOrNot(postId,patientId);
		return i;
	}
	public int savePatientPost(ForumPost post) throws DaoException {
		int i=dao.savePatientPost(post);
		return i;
	}
	
	
	public List<ForumPost> getAllPharmacyPost() throws DaoException {
		List<ForumPost> posts = dao.getAllPharmacyPost();
		Collections.sort(posts, new ForumPostComparator());
		return posts;
	}
	public List<Comment> getAllPharmacyCommentsById(int id) throws DaoException {
		List<Comment> comments = dao.getAllPharmacyCommentsById(id);
		Collections.sort(comments, new CommentComparator());
		return comments;
	}
	public int checkPharmacyLikedOrNot(int postId, int pharmacyId) throws DaoException {
		int i = dao.checkPharmacyLikedOrNot(postId,pharmacyId);
		return i;
	}
	public int savePharmacyPost(ForumPost post) throws DaoException {
		int i=dao.savePharmacyPost(post);
		return i;
	}
	public ForumPost getDoctorPostById(int postId) throws DaoException {
		ForumPost post = dao.getDoctorPostById(postId);
		return post;
	}
	public ForumPost getPatientPostById(int postId) throws DaoException {
		ForumPost post = dao.getPatientPostById(postId);
		return post;
	}
	public ForumPost getPharmacyPostById(int postId) throws DaoException {
		ForumPost post = dao.getPharmacyPostById(postId);
		return post;
	}
	public void saveDoctorComment(int postId, int id, String comment) throws DaoException {
		dao.saveDoctorComment(postId,id,comment);
		
	}
	public void savePharmacyComment(int postId, int id, String comment) throws DaoException {
		dao.savePharmacyComment(postId,id,comment);
		
	}
	public void savePatientComment(int postId, int id, String comment) throws DaoException {
		dao.savePatientComment(postId,id,comment);
		
	}
	public void deleteForum(Login login, int postId) throws DaoException {
		dao.deleteForum(login,postId);
		
	}
	public void addDoctorLikePost(int postId, int id,Login login) throws DaoException {
		dao.addDoctorLikePost(postId,id,login);
		
	}
	public void updateDoctorLike(int postId, int count,Login login) throws DaoException {
		dao.updateDoctorLike(postId,count,login);
		
	}
	public void removeDoctorLikePost(int postId, int id,Login login) throws DaoException {
		dao.removeDoctorLikePost(postId,id,login);
		
	}
	public void saveMembers(int postId, String doctorId) throws DaoException {
		dao.saveMembers(postId , doctorId);
		
	}
	public List<Doctor> getGroupMembersByPostId(int postId) throws DaoException {
		List<Doctor> doctors = dao.getGroupMembersByPostId(postId);
		return doctors;
	}
}
