package com.vinod.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.vinod.exception.DaoException;
import com.vinod.model.Comment;
import com.vinod.model.ForumPost;
import com.vinod.model.Like;
import com.vinod.model.Login;
import com.vinod.model.Notification;
import com.vinod.model.Post;
import com.vinod.util.DBUtil;

public class NotificationDao {
	final static Logger logger = Logger.getLogger(NotificationDao.class);
	public List<Notification> getNotifications(Login login) throws DaoException {
		
		List<Notification> notifications = new ArrayList<Notification>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select * from notification where recipient_id=? and type=? ORDER BY creationdate DESC LIMIT 10";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1, login.getId());
			ps.setLong(2, login.getType());
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Notification notification = new Notification();
				notification.setNotificationId(rs.getInt("notification_id"));
				notification.setSenderId(rs.getInt("sender_id"));
				notification.setRecipientId(rs.getInt("recipient_id"));
				notification.setType(rs.getInt("type"));
				notification.setHasRead(rs.getInt("is_read"));
				notification.setContent(rs.getString("content"));
				notification.setCreationDate(rs.getTimestamp("creationdate"));
				notification.setImage(rs.getString("image"));
				notifications.add(notification);
			}
		}catch (SQLException e) {
			
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
		return notifications;
	}

	public void clearNotification(Login login) throws DaoException {
		
		Connection con = null;
		PreparedStatement ps = null;
		String query="update notification set is_read=1 where recipient_id=? and type=? ";

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setLong(1, login.getId());
			ps.setInt(2, login.getType());
			
		    ps.executeUpdate();	
		   
		}catch (SQLException e) {
			
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}

	
	public void clearRequest(Login login) throws DaoException {
        
		String whereClause="";
		if(login.getType()==1)
		{
			whereClause="doctorid=? and doctoraccepted=0";
			clearDoctorRequest(login);
		}
		if(login.getType()==2)
		{
			whereClause="patientid=? and patientaccepted=0";
		}
		
		Connection con = null;
		PreparedStatement ps = null;
		String query="update patientdoctor set view=1 where "+whereClause;

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setLong(1, login.getId());
			
			
		    ps.executeUpdate();	
		   
		}catch (SQLException e) {
			
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}

	private void clearDoctorRequest(Login login) throws DaoException {	
		Connection con = null;
		PreparedStatement ps = null;
		String query="update doctordoctor set view=1 where doctorid1=? or doctorid2=? ";

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setLong(1, login.getId());
			ps.setLong(2, login.getId());
			
			
		    ps.executeUpdate();	
		   
		}catch (SQLException e) {
			
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}

	public int checkMobileExistOrNot(Long mobile, int type) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		String query = "";
		if(type==1)
		{
			query = query+"select id from doctor where MobileNumber=?";
		}
		if(type==2)
		{
			query = query+"select id from patient where MobileNumber=?";
		}
		if(type==3)
		{
			query = query+"select id from pharmacy where MobileNumber=?";
		}
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1, mobile);
		
			rs= ps.executeQuery();
			while(rs.next())
			{
				i = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return i;
	}

	public int checkEmailExistOrNot(String email, int type) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		String query = "";
		if(type==1)
		{
			query = query+"select id from doctor where EmailId=?";
		}
		if(type==2)
		{
			query = query+"select id from patient where EmailId=?";
		}
		if(type==3)
		{
			query = query+"select id from pharmacy where EmailId=?";
		}
		
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1,email);
		
			rs= ps.executeQuery();
			while(rs.next())
			{
				i = rs.getInt(1);
			}
			
		}catch (SQLException e) {
			
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return i;
	}

	public void setResetPassword(int id, String randomUUIDString , int type) throws DaoException {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "insert into resetpassword(id,type,uniqueid) values (?,?,?)";

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ps.setInt(2, type);
			ps.setString(3,randomUUIDString);
		
			ps.executeUpdate();
			
			
		}catch (SQLException e) {
			
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
	}

	public Notification checkUIDExistOrNot(String uid) throws DaoException {
		Notification notification = new Notification();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select * from resetpassword where uniqueid=?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, uid);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				
				notification.setNotificationId(rs.getInt("id"));
				notification.setType(rs.getInt("type"));
				notification.setCreationDate(rs.getTimestamp("creationdate"));
				
			}
		}catch (SQLException e) {
			
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return notification;
	}

	public void deleteUID(String uid, long notificationId) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "delete from resetpassword where uniqueid=? and id=?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, uid);
			ps.setLong(2, notificationId);
			ps.executeUpdate();
			
		}catch (SQLException e) {
			
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
	}

	public void saveSupport(ForumPost post) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		String query="insert into support (otherid,type,content)values (?,?,?)";

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setLong(1, post.getOtherId());
			ps.setInt(2, post.getType());
			ps.setString(3, post.getContent());
		    int i=ps.executeUpdate();	
		    System.out.println("Successfully support question inseeted : "+i);
		}catch (SQLException e) {
			
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}

	public void savePrivacy(Login login, int privacyValue) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		String query="";
		String whereClause="";
		if(login.getType()==1)
		{
			query="update doctor set privacy=? where id=?";
			
		}
		if(login.getType()==2)
		{
			query="update patient set privacy=? where id=?";
		}
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			
			ps.setInt(1, privacyValue);
			ps.setLong(2, login.getId());
			
		    int i=ps.executeUpdate();	
		  
		}catch (SQLException e) {
			
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}

	public List<Post> getAllPublicPosts(Login login) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Post> posts = new ArrayList<Post>();
		String query = "select * from post where privacy = 2 and doctorid <> ? and communityid=0 and isdeleted=0";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, login.getId());
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Post post = new Post();
				post.setId(rs.getInt("id"));
				post.setDoctorId(rs.getInt("doctorid"));
				post.setStatus(rs.getString("status"));
				post.setPostType(rs.getInt("posttype"));
				post.setFilePath(rs.getString("filepath"));
				post.setPrivacy(rs.getInt("privacy"));
				post.setCreationDate(rs.getTimestamp("createdon"));
				
				posts.add(post);
			}
		} 
		 catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return posts;
	}

	public void getAllFriendPosts(List<Post> posts, Login login) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "select post.id , post.doctorid, post.status , post.posttype , post.filepath , post.privacy , post.createdon from post,doctordoctor where post.privacy = 1 and (post.doctorid = doctordoctor.doctorid1 or post.doctorid = doctordoctor.doctorid2) and (doctordoctor.doctorid1=? or doctordoctor.doctorid2=?)and post.doctorid <> ? and doctordoctor.doctorid1Accepted=1 and doctordoctor.doctorid2Accepted=1 and post.communityid=0 and post.isdeleted=0";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, login.getId());
			ps.setInt(2, login.getId());
			ps.setInt(3, login.getId());
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Post post = new Post();
				post.setId(rs.getInt("id"));
				post.setDoctorId(rs.getInt("doctorid"));
				post.setStatus(rs.getString("status"));
				post.setPostType(rs.getInt("posttype"));
				post.setFilePath(rs.getString("filepath"));
				post.setPrivacy(rs.getInt("privacy"));
				post.setCreationDate(rs.getTimestamp("createdon"));
				
				posts.add(post);
			}
		} 
		 catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}

	public List<Comment> getAllPostCommentsById(int id) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Comment> comments = new ArrayList<Comment>();
		String query = "select * from comments where postid=? and isdeleted=0";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Comment comment = new Comment();
				comment.setForumPostid(rs.getInt("postid"));
				comment.setDoctorId(rs.getInt("doctorid"));
				comment.setComment(rs.getString("comment"));
				comment.setCreated(rs.getTimestamp("commentedon"));
				comment.setId(rs.getInt("commentid"));
				
				comments.add(comment);
			}
		} 
		 catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return comments;
	}

	public List<Like> getAllPostLikes(int id) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Like> comments = new ArrayList<Like>();
		String query = "select * from likes where postid=?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Like comment = new Like();
				comment.setForumPostid(rs.getInt("postid"));
				comment.setDoctorId(rs.getInt("doctorid"));
				comments.add(comment);
			}
		} 
		 catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return comments;
	}

	public void getAllMyPosts(List<Post> posts, Login login) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "select * from post where doctorid = ? and communityid=0 and isdeleted=0";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, login.getId());
			
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Post post = new Post();
				post.setId(rs.getInt("id"));
				post.setDoctorId(rs.getInt("doctorid"));
				post.setStatus(rs.getString("status"));
				post.setPostType(rs.getInt("posttype"));
				post.setFilePath(rs.getString("filepath"));
				post.setPrivacy(rs.getInt("privacy"));
				post.setCreationDate(rs.getTimestamp("createdon"));
				
				posts.add(post);
			}
		} 
		 catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}

	public void saveLike(Login login, int postId) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query ="";
		
			query = "insert into likes (postid,doctorid) values (?,?)";
		

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, postId);
			ps.setInt(2, login.getId());
			
			ps.executeUpdate();
			
		} 
		 catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}

	public void removeLike(Login login, int postId) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query ="";
		
		query = "delete from likes where postid=? and doctorid=? ";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, postId);
			ps.setInt(2, login.getId());
			
			ps.executeUpdate();
			
		} 
		 catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}

	public int saveMessage(int postId, String message, Login login) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i= 0;
		String query ="";
		
			query = "insert into comments (postid,doctorid,comment,commentedon) values (?,?,?,?)";
		

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, postId);
			ps.setInt(2, login.getId());
			ps.setString(3, message);
			ps.setTimestamp(4, new Timestamp(new Date().getTime()));
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			
			while(rs.next())
			{
				i = rs.getInt(1);
			}
			
		} 
		 catch (SQLException e) {
			 e.printStackTrace();
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return i;
		
	}

	public List<Post> getAllCommunityPosts(int communityId) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Post> posts = new ArrayList<Post>();
		String query = "select * from post where communityid=? and isdeleted=0";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, communityId);
			
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Post post = new Post();
				post.setId(rs.getInt("id"));
				post.setDoctorId(rs.getInt("doctorid"));
				post.setStatus(rs.getString("status"));
				post.setPostType(rs.getInt("posttype"));
				post.setFilePath(rs.getString("filepath"));
				post.setPrivacy(rs.getInt("privacy"));
				post.setCreationDate(rs.getTimestamp("createdon"));
				
				posts.add(post);
			}
		} 
		 catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return posts;
	}

	public void deletePost(int postId , Login login) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query ="";
		
		query = "update post set isdeleted=1 where id=? ";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, postId);
			
			ps.executeUpdate();
			
		} 
		 catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}

	public int deleteComment(int postId, int commentId) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query ="";
		
		query = "update comments set isdeleted=1 where postid=? and commentid=? ";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, postId);
			ps.setInt(2, commentId);
			
			ps.executeUpdate();
			
		} 
		 catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return commentId;
		
	}

	public List<Post> getAllDoctorPosts(int doctorId) throws DaoException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Post> posts = new ArrayList<Post>();
		String query = "select * from post where doctorid = ? and communityid=0 and isdeleted=0";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, doctorId);
			
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Post post = new Post();
				post.setId(rs.getInt("id"));
				post.setDoctorId(rs.getInt("doctorid"));
				post.setStatus(rs.getString("status"));
				post.setPostType(rs.getInt("posttype"));
				post.setFilePath(rs.getString("filepath"));
				post.setPrivacy(rs.getInt("privacy"));
				post.setCreationDate(rs.getTimestamp("createdon"));
				
				posts.add(post);
			}
		} 
		 catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	
		return posts;
	}

	public Post getPostById(int postId) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Post post = new Post();
		String query = "select * from post where id = ?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, postId);
			
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				
				post.setId(rs.getInt("id"));
				post.setDoctorId(rs.getInt("doctorid"));
				post.setStatus(rs.getString("status"));
				post.setPostType(rs.getInt("posttype"));
				post.setFilePath(rs.getString("filepath"));
				post.setPrivacy(rs.getInt("privacy"));
				post.setCreationDate(rs.getTimestamp("createdon"));
				
				
			}
		} 
		 catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return post;
	}
}
