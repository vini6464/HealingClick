package com.vinod.service;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;

import org.apache.coyote.Response;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;
import com.vinod.comparator.CommentComparator;
import com.vinod.comparator.NotificationComparator;
import com.vinod.comparator.PostComparator;
import com.vinod.dao.NotificationDao;
import com.vinod.exception.DaoException;
import com.vinod.model.Comment;
import com.vinod.model.ForumPost;
import com.vinod.model.Like;
import com.vinod.model.Login;
import com.vinod.model.Notification;
import com.vinod.model.Post;


public class NotificationService {

	NotificationDao dao = new NotificationDao();

	public List<Notification> getNotifications(Login login) throws DaoException {
		List<Notification> notifications = dao.getNotifications(login);
		Collections.sort(notifications, new NotificationComparator());
		return notifications;
	}

	public void clearNotification(Login login) throws DaoException {
		dao.clearNotification(login);
		
	}

	public void clearRequest(Login login) throws DaoException {
		dao.clearRequest(login);
		
		
	}

	public int checkMobileExistOrNot(Long mobile, int type) throws DaoException {
		int i = dao.checkMobileExistOrNot(mobile,type);
		return i;
		
	}

	public int checkEmailExistOrNot(String email, int type) throws DaoException {
		int i = dao.checkEmailExistOrNot(email,type);
		return i;
	}

	public Long sendOTP(Long mobile) {
		Long i = (long) 0;
		return i;
		
	}

	public void sendResetLink(String mail, String message) {
		
		SendGrid sendgrid = new SendGrid("azure_b70238a8cfd725c530123a06fda3b07b@azure.com", "44Gf1ajMqRtY7FM");

		SendGrid.Email email = new SendGrid.Email();
		email.addTo(mail);
		email.setFrom("healingclick@gmail.com");
		email.setSubject("Password Reset");
		email.setText(message);

		try {
		  SendGrid.Response response = sendgrid.send(email);
		}
		catch (SendGridException e) {
			e.printStackTrace();
			}

		
	    }


	public void setResetPassword(int id, String randomUUIDString ,int type) throws DaoException {
		dao.setResetPassword(id , randomUUIDString ,type);
		
	}

	public Notification checkUIDExistOrNot(String uid) throws DaoException {
		Notification notification = dao.checkUIDExistOrNot(uid);
		return notification;
	}

	public void deleteUID(String uid, long notificationId) throws DaoException {
		dao.deleteUID(uid , notificationId);
		
	}

	public void saveSupport(ForumPost post) throws DaoException {
		dao.saveSupport(post);
	}

	public void savePrivacy(Login login, int privacyValue) throws DaoException {
		dao.savePrivacy(login , privacyValue);
		
	}

	public List<Post> getAllPosts(Login login) throws DaoException {
		List<Post> posts = dao.getAllPublicPosts(login);
		dao.getAllFriendPosts(posts, login);
		dao.getAllMyPosts(posts, login);
		Collections.sort(posts, new PostComparator());
		return posts;
	}

	public List<Comment> getAllPostCommentsById(int id) throws DaoException {
		List<Comment> comments = dao.getAllPostCommentsById(id);
		Collections.sort(comments, new CommentComparator());
		return comments;
	}

	public List<Like> getAllPostLikes(int id) throws DaoException {
		List<Like> like = dao.getAllPostLikes(id);
		return like;
	}

	public void saveLike(Login login, int postId) throws DaoException {
		dao.saveLike(login , postId);
		
	}

	public void removeLike(Login login, int postId) throws DaoException {
		dao.removeLike(login , postId);
		
	}

	public int saveMessage(int postId, String message, Login login) throws DaoException {
		int cId=dao.saveMessage(postId , message , login );
		return cId;
		
	}

	public List<Post> getAllCommunityPosts(int communityId) throws DaoException {
		List<Post> posts = dao.getAllCommunityPosts(communityId);
		
		Collections.sort(posts, new PostComparator());
		return posts;
	}

	public void deletePost(int postId, Login login) throws DaoException {
		dao.deletePost(postId, login);
		
	}

	public int deleteComment(int postId, int commentId) throws DaoException {
		int id = dao.deleteComment(postId , commentId);
		return id;
		
	}

	public List<Post> getAllDoctorPosts(int doctorId) throws DaoException {
		List<Post> posts = dao.getAllDoctorPosts(doctorId);
		
		Collections.sort(posts, new PostComparator());
		return posts;
	}

	public Post getPostById(int postId) throws DaoException {
		Post post = dao.getPostById(postId);
		return post;
	}

	public void refundMoney(String transactionId , double cost) {
		
		   
		   
		    String myURL = "";
			try {
				myURL = "https://test.payumoney.com/payment/op/getPaymentResponse?"
						+ "merchantKey="+URLEncoder.encode("wH4MFlem", "UTF-8")
						+ "&merchantTransactionIds="+URLEncoder.encode(transactionId, "UTF-8");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    StringBuilder sb = new StringBuilder();
			URLConnection urlConn = null;
			InputStreamReader in = null;
			try {
				URL url = new URL(myURL);
				urlConn = url.openConnection();
				if (urlConn != null)
					urlConn.setReadTimeout(60 * 1000);
				if (urlConn != null && urlConn.getInputStream() != null) {
					in = new InputStreamReader(urlConn.getInputStream(),
							Charset.defaultCharset());
					BufferedReader bufferedReader = new BufferedReader(in);
					if (bufferedReader != null) {
						int cp;
						while ((cp = bufferedReader.read()) != -1) {
							sb.append((char) cp);
						}
						bufferedReader.close();
					}
					System.out.println(sb.toString());
				}
			in.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Exception while calling URL:"+ myURL, e);
			} 
	}
		
	

	
	
	
}
