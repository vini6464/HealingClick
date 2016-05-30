package com.vinod.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vinod.exception.DaoException;
import com.vinod.model.Comment;
import com.vinod.model.ForumPost;
import com.vinod.model.Login;
import com.vinod.util.DBUtil;

public class SupportDao {

	public List<ForumPost> getAllQuestions(Login login) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		List<ForumPost> posts = new ArrayList<ForumPost>();
		String query = "select * from support where otherid=? and type=?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, login.getId());
			ps.setInt(2, login.getType());
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				ForumPost post = new ForumPost();
				post.setId(rs.getInt("id"));
				post.setOtherId(rs.getInt("otherid"));
				post.setContent(rs.getString("content"));
				post.setCreationDate(rs.getTimestamp("createdon"));
				post.setType(rs.getInt("type"));
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

	public List<Comment> getAllQuestionCommentsById(int id) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Comment> comments = new ArrayList<Comment>();
		String query = "select * from supportcomment where supportid=?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Comment comment = new Comment();
				comment.setForumPostid(rs.getInt("supportid"));
				comment.setOtherId(rs.getInt("id"));
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

	public ForumPost getSupportById(int postId , int type) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		ForumPost post = new ForumPost();
		String query = "select * from support where id=? and type=? ";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, postId);
			ps.setInt(2, type);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				
				post.setId(rs.getInt("id"));
				post.setOtherId(rs.getInt("otherid"));
				post.setContent(rs.getString("content"));
				post.setCreationDate(rs.getTimestamp("createdon"));
				post.setType(rs.getInt("type"));
				
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

	public List<Comment> getAllSupportCommentsById(int id) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Comment> comments = new ArrayList<Comment>();
		String query = "select * from supportcomment where supportid=?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Comment comment = new Comment();
				comment.setForumPostid(rs.getInt("supportid"));
				comment.setOtherId(rs.getInt("id"));
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

	public void saveSupportComment(int postId, int id, String comment) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		String query = "insert into supportcomment(supportid,id,comment) values(?,?,?)";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, postId);
			ps.setInt(2, id);
			ps.setString(3, comment);
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
