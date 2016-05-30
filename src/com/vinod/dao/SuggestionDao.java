package com.vinod.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.vinod.exception.DaoException;
import com.vinod.model.Comment;
import com.vinod.model.ForumPost;
import com.vinod.model.Login;
import com.vinod.model.Suggestion;
import com.vinod.util.DBUtil;

public class SuggestionDao {

	public List<Suggestion> getAllSuggestionForDoctorById(int id) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Suggestion> posts = new ArrayList<Suggestion>();
		String query = "select * from suggestion where doctorid=?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Suggestion post = new Suggestion();
				post.setId(rs.getInt("id"));
				post.setDoctorId(rs.getInt("doctorid"));
				post.setPatientId(rs.getInt("patientid"));
				post.setName(rs.getString("name"));
				post.setContent(rs.getString("content"));
				post.setCreationDate(rs.getTimestamp("createdon"));
				
				posts.add(post);
			}
		} 
		 catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return posts;
	}

	public List<Suggestion> getAllSuggestionForPatientById(int id) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Suggestion> posts = new ArrayList<Suggestion>();
		String query = "select * from suggestion where patientid=?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Suggestion post = new Suggestion();
				post.setId(rs.getInt("id"));
				post.setDoctorId(rs.getInt("doctorid"));
				post.setPatientId(rs.getInt("patientid"));
				post.setName(rs.getString("name"));
				post.setContent(rs.getString("content"));
				post.setCreationDate(rs.getTimestamp("createdon"));
				
				posts.add(post);
			}
		} 
		 catch (SQLException e) {
			
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return posts;
	}

	public List<Comment> getAllCommentsBySuggestionId(int id) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Comment> comments = new ArrayList<Comment>();
		String query = "select * from suggestioncomment where suggestionid=?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Comment comment = new Comment();
				comment.setForumPostid(rs.getInt("suggestionid"));
				comment.setDoctorId(rs.getInt("doctorid"));
				comment.setPatientId(rs.getInt("patientid"));
				comment.setComment(rs.getString("comment"));
				comment.setCreated(rs.getTimestamp("commentedon"));
				comment.setId(rs.getInt("commentid"));
				
				comments.add(comment);
			}
		} 
		 catch (SQLException e) {
			
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return comments;
	}

	public void saveDoctorSuggestionComment(int postId, int id, String parameter) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		String query = "insert into suggestioncomment(commentid,suggestionid,doctorid,comment,commentedon) values(0,?,?,?,?)";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, postId);
			ps.setInt(2, id);
			ps.setString(3, parameter);
			ps.setTimestamp(4, new Timestamp(new Date().getTime()));
			ps.executeUpdate();
			
					} 
		 catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}

	public void savePatientSuggestionComment(int postId, int id,
			String parameter) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		String query = "insert into suggestioncomment(commentid,suggestionid,patientid,comment,commentedon) values(0,?,?,?,?)";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, postId);
			ps.setInt(2, id);
			ps.setString(3, parameter);
			ps.setTimestamp(4, new Timestamp(new Date().getTime()));
			ps.executeUpdate();
			
					} 
		 catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}

	public Suggestion getSuggestionById(int postId) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Suggestion post = new Suggestion();
		String query = "select * from suggestion where id=?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, postId);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				
				post.setId(rs.getInt("id"));
				post.setDoctorId(rs.getInt("doctorid"));
				post.setPatientId(rs.getInt("patientid"));
				post.setName(rs.getString("name"));
				post.setContent(rs.getString("content"));
				post.setCreationDate(rs.getTimestamp("createdon"));
				post.setForumImage(rs.getString("postimage"));
				
				
				
			}
		} 
		 catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return post;
	}

	public int saveSuggestion(ForumPost post) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i=0;
		
		String query = "insert into suggestion (name,content,postimage,doctorid,patientid,createdon) values (?,?,?,?,?,?)";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1,post.getName());
			ps.setString(2, post.getContent());
			ps.setString(3, post.getForumImage());
			ps.setInt(4, post.getDoctorId());
			ps.setInt(5, post.getPatientId());
			ps.setTimestamp(6, new Timestamp(new Date().getTime()));
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			
			while(rs.next())
			{
				i = rs.getInt(1);
			}
		} 
		 catch (SQLException e) {
			
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return i;
	}

	public void writeNotification(int id) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		String query = "update suggestion set seen=0 where Id=?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();
			
			
			
		} 
		 catch (SQLException e) {
			
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}

	public void closeNotification(int postId, Login login) throws DaoException {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		String query = "update suggestion set seen=1 where id=?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, postId);
			ps.executeUpdate();
			
			
			
		} 
		 catch (SQLException e) {
			
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
	}

}
