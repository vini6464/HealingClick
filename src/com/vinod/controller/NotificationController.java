package com.vinod.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vinod.service.CommunityService;
import com.google.gson.Gson;
import com.vinod.model.Comment;
import com.vinod.model.Doctor;
import com.vinod.model.ForumPost;
import com.vinod.model.Like;
import com.vinod.model.Log;
import com.vinod.model.Login;
import com.vinod.model.Medicine;
import com.vinod.model.Notification;
import com.vinod.model.Order;
import com.vinod.model.Patient;
import com.vinod.model.Pharmacy;
import com.vinod.model.Post;
import com.vinod.model.Review;
import com.vinod.model.Symptom;
import com.vinod.model.User;
import com.vinod.service.DoctorService;
import com.vinod.service.ForumService;
import com.vinod.service.LoginService;
import com.vinod.service.NotificationService;
import com.vinod.service.OrderService;
import com.vinod.service.PharmacyService;

/**
 * Servlet implementation class NotificationController
 */
@WebServlet("*.notification")
public class NotificationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NotificationController() {
		super();
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	private void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		NotificationService service = new NotificationService();
		LoginService lService = new LoginService();
		ForumService fService = new ForumService();
		DoctorService dService = new DoctorService();
		OrderService oService = new OrderService();
		String uri=request.getRequestURI();

		System.out.println(uri);
		if(uri.endsWith("checkLogin.notification"))
		{
			
			PrintWriter pw;
			
			pw = response.getWriter();
			response.setContentType("text/html");
			String msg = "";
				try {
					
					
					
					Login login=(Login) request.getSession().getAttribute("login");
					int id=login.getId();
					if(login.getType() == 4)
					{
						msg="";
					}
					if(login.getType() == 1)
					{
						msg="doctor.home";
					}
					if(login.getType() == 2)
					{
						msg="patient.home";
					}
					if(login.getType() == 3)
					{
						msg="pharmacy.home";
					}
					
					
					pw.print(msg);
				} catch (Exception e) {
					pw.print(msg);
				}
				
			
		}
		
		if(uri.endsWith("resendOTP.notification"))
		{
			
			PrintWriter pw;
			
			pw = response.getWriter();
			response.setContentType("text/html");
			String msg = "";
				try {
					 Long mobile = Long.parseLong(request.getParameter("mobile"));
					
					 Random rn = new Random();
					 Long OTP = (long) (100000 + rn.nextInt(900000));
					 HttpSession session = request.getSession();
				     session.setAttribute("OTP", OTP);
				     
				     String message = "Your resent OTP is "+OTP;
				     
				     lService.sendMessage(mobile ,message);
					
					 
					 pw.print(msg);
				} catch (Exception e) {
					System.out.println("\n In controller Error:"+e.getMessage());
					pw.print(msg);
				}
				
			
		}
		
		if(uri.endsWith("checkOTP.notification"))
		{
			
			PrintWriter pw;
			
			pw = response.getWriter();
			response.setContentType("text/html");
			int msg = 0;
				try {
					
					
					 Long mobile = Long.parseLong(request.getParameter("mobile"));
					 
					 Long otp = Long.parseLong(request.getParameter("otp"));
					 Long OTP = (Long) request.getSession().getAttribute("OTP");
					 
					 if(otp.equals(OTP))
					 {
						 msg = 1;
						 
					 }
					 pw.print(msg);
				} catch (Exception e) {
					System.out.println("\n In controller Error:"+e.getMessage());
					pw.print(msg);
				}
				
			
		}
		
		if(uri.endsWith("changePassword.notification"))
		{
			String target = "";
				try {
					 
					 int type = Integer.parseInt(request.getParameter("type"));
					 Long mobile = Long.parseLong(request.getParameter("mobile"));
					 
					 int id = service.checkMobileExistOrNot(mobile,type);
					 if(id>0)
					 {
						 request.setAttribute("id", id);
						 request.setAttribute("type", type);
						 target = "resetPassword.jsp";
					 }
					 else
					 {
						 target = "home.jsp";
					 }
					 
					 
				} catch (Exception e) {
					System.out.println("\n In controller Error:"+e.getMessage());
					
				}
				
				request.getRequestDispatcher(target).forward(request, response);
		}
		
		if(uri.endsWith("savePassword.notification"))
		{
			
			PrintWriter pw;
			
			pw = response.getWriter();
			response.setContentType("text/html");
			int msg = 1;
				try {
					int id= Integer.parseInt(request.getParameter("id"));
					int type= Integer.parseInt(request.getParameter("type"));
					String password = request.getParameter("password");
					
					
					Login login = new Login();
					login.setType(type);
					login.setId(id);
					
					HttpSession session = request.getSession();
					session.setAttribute("login", login);
					lService.updatePassword(login,password);
					
					 pw.print(msg);
				} catch (Exception e) {
					System.out.println("\n In controller Error:"+e.getMessage());
					msg = 0;
					pw.print(msg);
				}
				
			
		}
		
		
		if(uri.endsWith("resetPassword.notification"))
		{
			
			PrintWriter pw;
			
			pw = response.getWriter();
			response.setContentType("text/html");
			int msg = 0;
				try {
					 int type = Integer.parseInt(request.getParameter("type"));
					 int text = Integer.parseInt(request.getParameter("text"));
					 
					 String email = request.getParameter("mobEmail");
					 
					 if(text == 1)
					 {
						 Long mobile = Long.parseLong(email);
						 int i = service.checkMobileExistOrNot(mobile,type);
						 if(i==0)
						 {
							 msg=1;
						 }
						 else
						 {
							 //Long OTP = service.sendOTP(mobile);
							 Random rn = new Random();
							 Long OTP = (long) (100000 + rn.nextInt(900000));
							 
							 HttpSession session = request.getSession();
						     session.setAttribute("OTP", OTP);
						     msg = 2;
						     String message = "Reset Password OTP is "+OTP;
						     
						     lService.sendMessage(mobile ,message);
							 
						 }
						 
					 }
					 
					 if(text == 2)
					 {
						 int id = service.checkEmailExistOrNot(email,type);
						
						 if(id==0)
						 {
							 msg=1;
						 }
						 else
						 {
							 
							 UUID uuid = UUID.randomUUID();
							 
						     String randomUUIDString = uuid.toString();
						    
						     service.setResetPassword(id , randomUUIDString ,type);
						     String message="http://localhost:8080/HealingClick/resetPasswordLink.notification?id="+randomUUIDString;
							 service.sendResetLink(email, message);
							 msg=3;
						 }
					 }
					
					
					
					pw.print(msg);
				} catch (Exception e) {
					System.out.println("\n In controller Error:"+e.getMessage());
					pw.print(msg);
				}
				
			
		}
		
		
		if(uri.endsWith("resetPasswordLink.notification"))
		{
			String target = "";
			try {

				String uid = request.getParameter("id");
				
				Notification notification = service.checkUIDExistOrNot(uid);
				
				if(notification.getNotificationId() > 0)
				{
					service.deleteUID(uid , notification.getNotificationId());
					request.setAttribute("id", notification.getNotificationId());
					request.setAttribute("type", notification.getType());
					target = "resetPassword.jsp";
				}
				else
				{
					request.setAttribute("error", "Sorry , Password reset link is not valid ");
					target = "home.jsp";
				}
				

			}  catch (Exception e) {
				System.out.println("\n In controller Error:"+e.getMessage());
				target = "home.jsp";
			}
			request.getRequestDispatcher(target).forward(request, response);
		}
		
		try {
	        if(uri.endsWith("like.notification"))
			{
				
					Login login=(Login) request.getSession().getAttribute("login");
					lService.setLastActive(login);
					int id=login.getId();

					if(login.getType()==1)
					{


						int postId = Integer.parseInt(request.getParameter("q"));
						ForumPost post = fService.getDoctorPostById(postId);
						fService.addDoctorLikePost(postId,id,login);
						int count = post.getLikes();
						fService.updateDoctorLike(postId,count+1,login);
						PrintWriter pw = response.getWriter();
						response.setContentType("text/html");
						
						pw.print(count+1);


					}

					if(login.getType()==2)
					{

						int postId = Integer.parseInt(request.getParameter("q"));
						ForumPost post = fService.getPatientPostById(postId);
						fService.addDoctorLikePost(postId,id,login);
						int count = post.getLikes();
						fService.updateDoctorLike(postId,count+1,login);
						PrintWriter pw = response.getWriter();
						response.setContentType("text/html");
						pw.print(count+1);


					}

					if(login.getType()==3)
					{

						int postId = Integer.parseInt(request.getParameter("q"));
						ForumPost post = fService.getPharmacyPostById(postId);
						fService.addDoctorLikePost(postId,id,login);
						int count = post.getLikes();
						fService.updateDoctorLike(postId,count+1,login);
						PrintWriter pw = response.getWriter();
						response.setContentType("text/html");
						pw.print(count+1);



					}
			}
				
					
					
			


			if(uri.endsWith("unLike.notification"))
			{
				
					Login login=(Login) request.getSession().getAttribute("login");
					lService.setLastActive(login);
					int id=login.getId();

					if(login.getType()==1)
					{

						int postId = Integer.parseInt(request.getParameter("q"));
						ForumPost post = fService.getDoctorPostById(postId);
						fService.removeDoctorLikePost(postId,id,login);
						int count = post.getLikes();
						fService.updateDoctorLike(postId,count-1,login);
						PrintWriter pw = response.getWriter();
						response.setContentType("text/html");
						pw.print(count-1);


					}

					if(login.getType()==2)
					{

						int postId = Integer.parseInt(request.getParameter("q"));
						ForumPost post = fService.getPatientPostById(postId);
						fService.removeDoctorLikePost(postId,id,login);
						int count = post.getLikes();
						fService.updateDoctorLike(postId,count-1,login);
						PrintWriter pw = response.getWriter();
						response.setContentType("text/html");
						pw.print(count-1);



					}

					if(login.getType()==3)
					{

						int postId = Integer.parseInt(request.getParameter("q"));
						ForumPost post = fService.getPharmacyPostById(postId);
						fService.removeDoctorLikePost(postId,id,login);
						int count = post.getLikes();
						fService.updateDoctorLike(postId,count-1,login);
						PrintWriter pw = response.getWriter();
						response.setContentType("text/html");
						pw.print(count-1);


					}
				
			}
			
			if(uri.endsWith("cancel.notification"))
			{
				
				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id=login.getId();
				
				int oId = Integer.parseInt(request.getParameter("id"));
				String comment  = request.getParameter("comment");
				
				PharmacyService pService= new PharmacyService();
				Order order = pService.getOrderById(oId);
				
				
				
				
				if(login.getType()==1)
				{

					Doctor doctor = lService.getDoctorById(id);
					Pharmacy pharmacy = lService.getPharmacyById(order.getSupplierPharmacyId());
					int i = oService.cancelOrderDoctor(oId,comment);
					
					String contentlog = "Cancelled ORDER with ID="+oId;
					Log log = new Log(id,1,contentlog );
					lService.saveLog(log);
					
					String content = "<a href='viewOrder.pharmacy?id="+oId+"'>Order with ID="+oId+" Has Been Cancelled By Customer</a>";
					Notification notification = new Notification(0, id, content, order.getSupplierPharmacyId(), 0,3);
					notification.setImage(doctor.getImage());
					lService.saveNotification(notification);
					
				
					String contentlog1 = "ORDER Got Cancelled By Customer  with OrderID="+oId;
					Log log1 = new Log(order.getSupplierPharmacyId(),3,contentlog1 );
					lService.saveLog(log1);
					
					String message = "Hi " +doctor.getFirstName()+" "+doctor.getLastName()+",you have cancelled your order with ID:"+oId; 
				    lService.sendMessage(doctor.getMobile() ,message);
				    message = "Hi " +pharmacy.getPharmacyName()+",your order with ID:"+oId+" has been cancelled by Customer"; 
				    lService.sendMessage(pharmacy.getMobile() ,message);
				    
				    message = "";
					
				}

				if(login.getType()==2)
				{
					Patient doctor = lService.getPatientById(id);
					Pharmacy pharmacy = lService.getPharmacyById(order.getSupplierPharmacyId());
					int i = oService.cancelOrderPatient(oId,comment);
					
					String contentlog = "Cancelled ORDER with ID="+oId;
					Log log = new Log(id,2,contentlog );
					lService.saveLog(log);
					
					String content = "<a href='viewOrder.pharmacy?id="+oId+"'>Order with ID="+oId+" Has Been Cancelled By Customer</a>";
					Notification notification = new Notification(0, id, content, order.getSupplierPharmacyId(), 0,3);
					notification.setImage(lService.getPatientById(id).getImage());
					lService.saveNotification(notification);
					
					String contentlog1 = "ORDER Got Cancelled By Customer  with OrderID="+oId;
					Log log1 = new Log(order.getSupplierPharmacyId(),3,contentlog1 );
					lService.saveLog(log1);
					
					String message = "Hi " +doctor.getFirstName()+" "+doctor.getLastName()+",you have cancelled your order with ID:"+oId; 
				    lService.sendMessage(doctor.getMobile() ,message);
				    message = "Hi " +pharmacy.getPharmacyName()+",your order with ID:"+oId+" has been cancelled by Customer"; 
				    lService.sendMessage(pharmacy.getMobile() ,message);
				}

				if(login.getType()==3)
				{
					
					int status = 0;
					if(order.getSupplierPharmacyId()==id)
					{
						
						Pharmacy pharmacy = lService.getPharmacyById(id);
						status=3;
						int j = oService.cancelOrderPharmacy(oId,status,comment);
						
						String contentlog = "Cancelled ORDER with ID="+oId;
						Log log = new Log(id,3,contentlog );
						lService.saveLog(log);
						
						String contentlog1 = "ORDER Got Cancelled By Pharmacy with OrderID="+oId;
						
						int type=0;
						int recieverId=0;
						if(order.getDoctorId()!=0)
						{
							Doctor doctor = lService.getDoctorById(order.getDoctorId());
							type=1;
							recieverId=order.getDoctorId();
							String message = "Hi " +doctor.getFirstName()+" "+doctor.getLastName()+",your order with ID:"+oId+" has been cancelled by pharmacy."; 
						    lService.sendMessage(doctor.getMobile() ,message);
							
								
						}
						if(order.getPatientId()!=0)
						{
							Patient doctor = lService.getPatientById(order.getPatientId());
							type=2;
							recieverId=order.getDoctorId();
							String message = "Hi " +doctor.getFirstName()+" "+doctor.getLastName()+",your order with ID:"+oId+" has been cancelled by pharmacy."; 
						    lService.sendMessage(doctor.getMobile() ,message);
							
						}
						if(order.getPharmacyId()!=0)
						{
							Pharmacy spharmacy = lService.getPharmacyById(order.getPharmacyId());
							type=3;
							recieverId=order.getDoctorId();
							String message = "Hi " +spharmacy.getPharmacyName()+",your order with ID:"+oId+" has been cancelled by pharmacy."; 
						    lService.sendMessage(spharmacy.getMobile() ,message);
						}
						
						
						Log log1 = new Log(recieverId,type,contentlog1 );
						lService.saveLog(log1);
						
						String content = "<a href='getOrder.order?id="+oId+"'>Order with ID="+oId+" Has Been Cancelled By Pharmacy</a>";
						Notification notification = new Notification(0, id, content, recieverId, 0,type);
						notification.setImage(lService.getPharmacyById(id).getImage());
						lService.saveNotification(notification);
						
						 String message = "Hi " +pharmacy.getPharmacyName()+",you have cancelled your order with ID:"+oId; 
						 lService.sendMessage(pharmacy.getMobile() ,message);
						
					}
					else
					{
						Pharmacy pharmacy = lService.getPharmacyById(id);
						status=5;
						int j = oService.cancelOrderPharmacy(oId,status,comment);
						
						String content = "<a href='viewOrder.pharmacy?id="+oId+"'>Order with ID="+oId+" Has Been Cancelled Customer</a>";
						Notification notification = new Notification(0, id, content, order.getSupplierPharmacyId(), 0,3);
						lService.saveNotification(notification);
						
						String contentlog = "Cancelled ORDER with ID="+oId;
						Log log = new Log(id,3,contentlog );
						lService.saveLog(log);
						
						String contentlog1 = "ORDER Got Cancelled By Customer  with OrderID="+oId;
						Log log1 = new Log(order.getSupplierPharmacyId(),3,contentlog1 );
						lService.saveLog(log1);
						Pharmacy spharmacy = lService.getPharmacyById(order.getSupplierPharmacyId());
						
						 String message = "Hi " +pharmacy.getPharmacyName()+",you have cancelled your order with ID:"+oId; 
						 lService.sendMessage(spharmacy.getMobile() ,message);
						 
						 message = "Hi " +pharmacy.getPharmacyName()+",your order with ID:"+oId+" Has Been Cancelled Customer</a>";
						 lService.sendMessage(spharmacy.getMobile() ,message);
					}
	
				}
				
				
				/*if(order.getCashType() == 2){
					service.refundMoney(order.getTransactionId() , order.getTotalCost());
				}*/
				
			}
	        } catch (Exception e) {
	        	System.out.println("\n In controller Error:"+e.getMessage());
	        	PrintWriter pw = response.getWriter();
				pw.print("empty");
			}
	        
	        if(uri.endsWith("verify.notification"))
			{
				int oId = Integer.parseInt(request.getParameter("q"));
				String msg="";
				
				PharmacyService pService= new PharmacyService();
				
				
				PrintWriter pw;
			
					pw = response.getWriter();
					response.setContentType("text/html");
					try {
						Login login=(Login) request.getSession().getAttribute("login");
						lService.setLastActive(login);
						int id=login.getId();
						
						Order order = pService.getOrderById(oId);
				
						
						
						oService.verifyOrder(oId);
						int type=0;
						int recieverId=0;
						if(order.getDoctorId()!=0)
						{
							Doctor doctor = lService.getDoctorById(order.getDoctorId());
							type=1;
							recieverId=order.getDoctorId();
							String message = "Hi " +doctor.getFirstName()+" "+doctor.getLastName()+",your order with ID:"+oId+" has been Verified by pharmacy."; 
						    lService.sendMessage(doctor.getMobile() ,message);
								
						}
						if(order.getPatientId()!=0)
						{
							Patient doctor = lService.getPatientById(order.getPatientId());
							type=2;
							recieverId=order.getDoctorId();
							String message = "Hi " +doctor.getFirstName()+" "+doctor.getLastName()+",your order with ID:"+oId+" has been Verified by pharmacy."; 
						    lService.sendMessage(doctor.getMobile() ,message);
						}
						if(order.getPharmacyId()!=0)
						{
							Pharmacy spharmacy = lService.getPharmacyById(order.getPharmacyId());
							type=3;
							recieverId=order.getDoctorId();
							String message = "Hi " +spharmacy.getPharmacyName()+",your order with ID:"+oId+" has been Verified by pharmacy."; 
						    lService.sendMessage(spharmacy.getMobile() ,message);
						}
						
						String content = "<a href='getOrder.order?id="+oId+"'>Order with ID="+oId+" Has Been Verified By Pharmacy</a>";
						Notification notification = new Notification(0, id, content, recieverId, 0,type);
						notification.setImage(lService.getPharmacyById(id).getImage());
						lService.saveNotification(notification);
						
						msg = "<input class='btn btn-info btn-block btn-sm' style='font-weight: bold; width: %;' type=button value=Delivered onclick=deliverOrder("+oId+")>";
						pw.print(msg);
					} catch (Exception e) {
						System.out.println("\n In controller Error:"+e.getMessage());
						PrintWriter pw1 = response.getWriter();
	    				pw1.print("empty");
					}

					
				
			}
			if(uri.endsWith("deliver.notification"))
			{
				int oId = Integer.parseInt(request.getParameter("q"));
				String msg="";
				PrintWriter pw;
				PharmacyService pService= new PharmacyService();
					pw = response.getWriter();
					response.setContentType("text/html");
					try {
						
						Login login=(Login) request.getSession().getAttribute("login");
						lService.setLastActive(login);
						int id=login.getId();
						
						
						
						Order order = pService.getOrderById(oId);
						oService.deliverOrder(oId);
						
						String contentlog = "Delivered ORDER with ID="+oId;
						Log log = new Log(id,3,contentlog );
						lService.saveLog(log);
						msg = "Success";
						
						
						int type=0;
						int recieverId=0;
						if(order.getDoctorId()!=0)
						{
							Doctor doctor = lService.getDoctorById(order.getDoctorId());
							type=1;
							recieverId=order.getDoctorId();
							String message = "Hi " +doctor.getFirstName()+" "+doctor.getLastName()+",your order with ID:"+oId+" has been Delivered by pharmacy."; 
						    lService.sendMessage(doctor.getMobile() ,message);
								
						}
						if(order.getPatientId()!=0)
						{
							Patient doctor = lService.getPatientById(order.getPatientId());
							type=2;
							recieverId=order.getDoctorId();
							String message = "Hi " +doctor.getFirstName()+" "+doctor.getLastName()+",your order with ID:"+oId+" has been Delivered by pharmacy."; 
						    lService.sendMessage(doctor.getMobile() ,message);
						}
						if(order.getPharmacyId()!=0)
						{
							Pharmacy spharmacy = lService.getPharmacyById(order.getPharmacyId());
							type=3;
							recieverId=order.getDoctorId();
							String message = "Hi " +spharmacy.getPharmacyName()+",your order with ID:"+oId+" has been Delivered by pharmacy."; 
						    lService.sendMessage(spharmacy.getMobile() ,message);
						}
						
						String contentlog1 = "ORDER got Delivered with ID="+oId;
						Log log1 = new Log(recieverId,type,contentlog1 );
						lService.saveLog(log1);
						
						String content = "<a href='getOrder.order?id="+oId+"'>Order with ID="+oId+" Has Been Delivered By Pharmacy</a>";
						Notification notification = new Notification(0, id, content, recieverId, 0,type);
						notification.setImage(lService.getPharmacyById(id).getImage());
						lService.saveNotification(notification);

						pw.print(msg);
					} catch (Exception e) {
						
	    				pw.print("empty");
					}
					
				
			}
	        
	        
	        if(uri.endsWith("getCost.notification"))
			{
				String mId = request.getParameter("mId");
				int qty = Integer.parseInt(request.getParameter("qty"));
				int supplierId = Integer.parseInt(request.getParameter("supplierId"));
				PrintWriter pw;
				
					pw = response.getWriter();
					response.setContentType("text/html");
					Medicine cost;
					try {
						cost = dService.getMedicineCostByName(mId ,supplierId);
						String msg = ""+cost.getCost()*qty+"";

						pw.print(msg);
					} catch (Exception e) {
						System.out.println("\n In controller Error:"+e.getMessage());
	    				pw.print(0);
					}
					
				
			}
		
		if(uri.endsWith("getNotification.notification"))
		{
			try {

				Login login = (Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);

				List<Notification> notifications = service.getNotifications(login);

				// create a new Gson instance
				Gson gson = new Gson();
				// convert your list to json
				String notificationList = gson.toJson(notifications);
               
				PrintWriter pw = response.getWriter();

				pw.print(notificationList);	

			}  catch (Exception e) {
				PrintWriter pw = response.getWriter();
				pw.print("empty");
			}
		}

		
		if(uri.endsWith("clearNotification.notification"))
		{
			try {

				Login login = (Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
                service.clearNotification(login);
				PrintWriter pw = response.getWriter();

				pw.print("clear");	

			}  catch (Exception e) {
				System.out.println("\n In controller Error:"+e.getMessage());
				PrintWriter pw = response.getWriter();
				pw.print("empty");
			}
		}
		
		if(uri.endsWith("clearRequest.notification"))
		{
			try {

				Login login = (Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
                service.clearRequest(login);
				PrintWriter pw = response.getWriter();

				pw.print("clear");	

			}  catch (Exception e) {
				System.out.println("\n In controller Error:"+e.getMessage());
				PrintWriter pw = response.getWriter();
				pw.print("empty");
			}
		}
		
		
		if(uri.endsWith("RatingAndReview.notification"))
		{
			try {

				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id=login.getId();

				if(login.getType()==1)
				{
					Long pharmacyId = Long.parseLong(request.getParameter("id"));
					String comment = request.getParameter("comment");
					double rating = Double.parseDouble(request.getParameter("rating"));
					
					Review review = new Review(pharmacyId, id, 1, comment, rating);
					
					int dup = oService.checkIfReviewExist(review);
					if(dup == 1)
					{
						oService.updateReview(review);
						
					}
					else
					{
						int i = oService.saveReview(review);
					}
					
					
				}

				if(login.getType()==2)
				{
					Long pharmacyId = Long.parseLong(request.getParameter("id"));
					String comment = request.getParameter("comment");
					double rating = Double.parseDouble(request.getParameter("rating"));
					
					Review review = new Review(pharmacyId, id, 2, comment, rating);
					int dup = oService.checkIfReviewExist(review);
					if(dup == 1)
					{
						oService.updateReview(review);
						
					}
					else
					{
						int i = oService.saveReview(review);
					}
				}

				if(login.getType()==3)
				{
					Long pharmacyId = Long.parseLong(request.getParameter("id"));
					String comment = request.getParameter("comment");
					double rating = Double.parseDouble(request.getParameter("rating"));
					
					Review review = new Review(pharmacyId, id, 3, comment, rating);
					int dup = oService.checkIfReviewExist(review);
					if(dup == 1)
					{
						oService.updateReview(review);
						
					}
					else
					{
						int i = oService.saveReview(review);
					}
				}
				PrintWriter pw = response.getWriter();

				pw.print(1);	

			}  catch (Exception e) {
				System.out.println("\n In controller Error:"+e.getMessage());
				PrintWriter pw = response.getWriter();
				pw.print("empty");
			}
		}
		
		if(uri.endsWith("getRequest.notification"))
		{
			try {

				Login login = (Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
                int id =login.getId();
				if(login.getType()==1)
				{
					List<User> requests = lService.getPatientRequestNotification(id);
					lService.getDoctorNotification(requests , id);
					
					// create a new Gson instance
					Gson gson = new Gson();
					// convert your list to json
					String notificationList = gson.toJson(requests);
	               
					PrintWriter pw = response.getWriter();

					pw.print(notificationList);
				}
				
				if(login.getType()==2)
				{
					List<Doctor> requests = lService.getDoctorRequestNotification(id);

					// create a new Gson instance
					Gson gson = new Gson();
					// convert your list to json
					String notificationList = gson.toJson(requests);
	                
					PrintWriter pw = response.getWriter();

					pw.print(notificationList);
				}
				

			}  catch (Exception e) {
				System.out.println("\n In controller Error:"+e.getMessage());
				PrintWriter pw = response.getWriter();
				pw.print("empty");
			}
		}
		
		if(uri.endsWith("saveSupport.notification"))
		{
			try {
				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id=login.getId();
				String content = request.getParameter("content");
				ForumPost post = new ForumPost();
				post.setOtherId(id);
				post.setType(login.getType());
				post.setContent(content);
				service.saveSupport(post);
				}  catch (Exception e) {
					System.out.println("\n In controller Error:"+e.getMessage());
					PrintWriter pw = response.getWriter();
    				pw.print("empty");
			}
	
		}
		
		if(uri.endsWith("savePrivacy.notification"))
		{
			try {
				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id=login.getId();
				int privacyValue = Integer.parseInt(request.getParameter("privacyValue"));
				service.savePrivacy(login , privacyValue);
				}  catch (Exception e) {
					System.out.println("\n In controller Error:"+e.getMessage());
					PrintWriter pw = response.getWriter();
    				pw.print("empty");
			}
	
		}
		
		if(uri.endsWith("symptoms.notification"))
		{
			try {
				
				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				
				String symptomsList =  (String) request.getSession().getAttribute("symptoms");
				
				if(null == symptomsList){  
				
				List<Symptom> symptoms = dService.getAllSymptoms();
				// create a new Gson instance
				Gson gson = new Gson();
				// convert your list to json
				symptomsList = gson.toJson(symptoms);
				HttpSession session = request.getSession();
				session.setAttribute("symptoms", symptomsList);
				
				}
				
               
				PrintWriter pw = response.getWriter();

				pw.print(symptomsList);	
				}  catch (Exception e) {
					System.out.println("\n In controller Error:"+e.getMessage());
					PrintWriter pw = response.getWriter();
    				pw.print("empty");
			}
	
		}
		
		if(uri.endsWith("medicines.notification"))
		{
			try {
				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id=login.getId();
				String medicinesList =  (String) request.getSession().getAttribute("medicines");
				
				if(null == medicinesList){  
					List<Medicine> medicines = dService.getAllMedicines();
					
					// create a new Gson instance
					Gson gson = new Gson();
					// convert your list to json
				    medicinesList = gson.toJson(medicines);
					HttpSession session = request.getSession();
					session.setAttribute("medicines", medicinesList);
					
					}
				
				
               
				PrintWriter pw = response.getWriter();

				pw.print(medicinesList);	
				}  catch (Exception e) {
					System.out.println("\n In controller Error:"+e.getMessage());
					PrintWriter pw = response.getWriter();
    				pw.print("empty");
			}
	
		}
		
		if(uri.endsWith("getPharmacyMedicines.notification"))
		{
			try {
				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id=login.getId();
				
				Login loginSupplier = new Login();
				loginSupplier.setId(Integer.parseInt(request.getParameter("supplierId")));
				
				PharmacyService phService = new PharmacyService();
				List<Medicine> pharmacyMedicines = phService.getPharmacyMedicines(loginSupplier);
				
				// create a new Gson instance
				Gson gson = new Gson();
				// convert your list to json
				String medicinesList = gson.toJson(pharmacyMedicines);
               
				PrintWriter pw = response.getWriter();

				pw.print(medicinesList);	
				}  catch (Exception e) {
					System.out.println("\n In controller Error:"+e.getMessage());
					PrintWriter pw = response.getWriter();
    				pw.print("empty");
			}
	
		}
		
		
		if(uri.endsWith("getPosts.notification"))
		{
			try {
				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				List<Post> posts = service.getAllPosts(login);
				
				for(int i=0;i<posts.size();i++)
				{
					Doctor doctor = lService.getDoctorById(posts.get(i).getDoctorId());
					posts.get(i).setDoctorName(doctor.getFirstName()+" "+doctor.getLastName());
					posts.get(i).setImage(doctor.getImage());
					List<Comment> comments = service.getAllPostCommentsById(posts.get(i).getId());
					for(int j=0;j<comments.size();j++)
					{
						Doctor doctor1 = lService.getDoctorById(comments.get(j).getDoctorId());
						comments.get(j).setDoctorName(doctor1.getFirstName()+" "+doctor1.getLastName());
						comments.get(j).setImage(doctor1.getImage());
					}
					posts.get(i).setComment(comments);
					posts.get(i).setComments(comments.size());
					
					List<Like> likes = service.getAllPostLikes(posts.get(i).getId());
					for(int j=0;j<likes.size();j++)
					{
						Doctor doctor1 = lService.getDoctorById(likes.get(j).getDoctorId());
						likes.get(j).setDoctorName(doctor1.getFirstName()+" "+doctor1.getLastName());
						if(doctor1.getId() == login.getId()){
							posts.get(i).setLiked(1);
						}
					}
					posts.get(i).setLike(likes);
					posts.get(i).setLikes(likes.size());
				}
				// create a new Gson instance
				Gson gson = new Gson();
				// convert your list to json
				String medicinesList = gson.toJson(posts);
               
				PrintWriter pw = response.getWriter();

				pw.print(medicinesList);	
				}  catch (Exception e) {
					System.out.println("\n In controller Error:"+e.getMessage());
					PrintWriter pw = response.getWriter();
    				pw.print("empty");
			}
	
		}
		
		if(uri.endsWith("getDoctorPosts.notification"))
		{
			try {
				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				
				int doctorId = Integer.parseInt(request.getParameter("id"));
				List<Post> posts = service.getAllDoctorPosts(doctorId);
				
				for(int i=0;i<posts.size();i++)
				{
					Doctor doctor = lService.getDoctorById(posts.get(i).getDoctorId());
					posts.get(i).setDoctorName(doctor.getFirstName()+" "+doctor.getLastName());
					posts.get(i).setImage(doctor.getImage());
					List<Comment> comments = service.getAllPostCommentsById(posts.get(i).getId());
					for(int j=0;j<comments.size();j++)
					{
						Doctor doctor1 = lService.getDoctorById(comments.get(j).getDoctorId());
						comments.get(j).setDoctorName(doctor1.getFirstName()+" "+doctor1.getLastName());
						comments.get(j).setImage(doctor1.getImage());
					}
					posts.get(i).setComment(comments);
					posts.get(i).setComments(comments.size());
					
					List<Like> likes = service.getAllPostLikes(posts.get(i).getId());
					for(int j=0;j<likes.size();j++)
					{
						Doctor doctor1 = lService.getDoctorById(likes.get(j).getDoctorId());
						likes.get(j).setDoctorName(doctor1.getFirstName()+" "+doctor1.getLastName());
						if(doctor1.getId() == login.getId()){
							posts.get(i).setLiked(1);
						}
					}
					posts.get(i).setLike(likes);
					posts.get(i).setLikes(likes.size());
				}
				// create a new Gson instance
				Gson gson = new Gson();
				// convert your list to json
				String medicinesList = gson.toJson(posts);
               
				PrintWriter pw = response.getWriter();

				pw.print(medicinesList);	
				}  catch (Exception e) {
					System.out.println("\n In controller Error:"+e.getMessage());
					PrintWriter pw = response.getWriter();
    				pw.print("empty");
			}
	
		}
		
		if(uri.endsWith("getCommunityPosts.notification"))
		{
			try {
				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int communityId = Integer.parseInt(request.getParameter("id"));
				List<Post> posts = service.getAllCommunityPosts(communityId);
				
				for(int i=0;i<posts.size();i++)
				{
					Doctor doctor = lService.getDoctorById(posts.get(i).getDoctorId());
					posts.get(i).setDoctorName(doctor.getFirstName()+" "+doctor.getLastName());
					posts.get(i).setImage(doctor.getImage());
					List<Comment> comments = service.getAllPostCommentsById(posts.get(i).getId());
					for(int j=0;j<comments.size();j++)
					{
						Doctor doctor1 = lService.getDoctorById(comments.get(j).getDoctorId());
						comments.get(j).setDoctorName(doctor1.getFirstName()+" "+doctor1.getLastName());
						comments.get(j).setImage(doctor1.getImage());
					}
					posts.get(i).setComment(comments);
					posts.get(i).setComments(comments.size());
					
					List<Like> likes = service.getAllPostLikes(posts.get(i).getId());
					for(int j=0;j<likes.size();j++)
					{
						Doctor doctor1 = lService.getDoctorById(likes.get(j).getDoctorId());
						likes.get(j).setDoctorName(doctor1.getFirstName()+" "+doctor1.getLastName());
						if(doctor1.getId() == login.getId()){
							posts.get(i).setLiked(1);
						}
					}
					posts.get(i).setLike(likes);
					posts.get(i).setLikes(likes.size());
				}
				// create a new Gson instance
				Gson gson = new Gson();
				// convert your list to json
				String medicinesList = gson.toJson(posts);
               
				PrintWriter pw = response.getWriter();

				pw.print(medicinesList);	
				}  catch (Exception e) {
					System.out.println("\n In controller Error:"+e.getMessage());
					PrintWriter pw = response.getWriter();
    				pw.print("empty");
			}
	
		}
		
		if(uri.endsWith("deletePost.notification"))
		{
			try {
				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int postId = Integer.parseInt(request.getParameter("id"));
				service.deletePost(postId , login);
				
				PrintWriter pw = response.getWriter();
				pw.print("deleted");	
				}  catch (Exception e) {
					System.out.println("\n In controller Error:"+e.getMessage());
					PrintWriter pw = response.getWriter();
    				pw.print("empty");
			}
	
		}
		
		if(uri.endsWith("deleteComment.notification"))
		{
			try {
				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int postId = Integer.parseInt(request.getParameter("id"));
				int commentId = Integer.parseInt(request.getParameter("cid"));
				int id = service.deleteComment(postId , commentId);
				
				PrintWriter pw = response.getWriter();
				pw.print("deleted");	
				}  catch (Exception e) {
					System.out.println("\n In controller Error:"+e.getMessage());
					PrintWriter pw = response.getWriter();
    				pw.print("empty");
			}
	
		}
		
		if(uri.endsWith("likePost.notification"))
		{
			try {
				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int postId = Integer.parseInt(request.getParameter("id"));
				service.saveLike(login , postId);
				List<Like> likes = service.getAllPostLikes(postId);
				
				
				
				
				PrintWriter pw = response.getWriter();

				pw.print(likes.size());	
				
				Doctor doctor1 = lService.getDoctorById(login.getId());
				
				Post post = service.getPostById(postId);
				if(post.getDoctorId() != login.getId()){
				Doctor doctor = lService.getDoctorById(post.getDoctorId());
				String content1 = "<a href='#like"+postId+"'>Dr."+doctor1.getFirstName()+" "+doctor1.getLastName()+" Has Liked Your Post</a>";
				
				Notification notification = new Notification(0, doctor1.getId(), content1,doctor.getId() , 0,1);
				notification.setImage(doctor1.getImage());
				lService.saveNotification(notification);
				}
				
				}  catch (Exception e) {
					System.out.println("\n In controller Error:"+e.getMessage());
					PrintWriter pw = response.getWriter();
    				pw.print("empty");
			}
	
		}
		
		if(uri.endsWith("unlikePostDoctor.notification"))
		{
			PrintWriter pw = response.getWriter();
			try {
				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int postId = Integer.parseInt(request.getParameter("id"));
				service.removeLike(login , postId);
				List<Like> likes = service.getAllPostLikes(postId);
				
				pw.print(likes.size());	
				}  catch (Exception e) {
					System.out.println("\n In controller Error:"+e.getMessage());
				
    				pw.print("empty");
			}
	
		}
		
		if(uri.endsWith("addMember.notification"))
		{
			PrintWriter pw = response.getWriter();
			try {
				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int communityId = Integer.parseInt(request.getParameter("communityId"));
				String[] doctorsId = request.getParameterValues("members[]");
				
				String title = request.getParameter("title");
				CommunityService cService= new CommunityService();
				Doctor doctor = lService.getDoctorById(login.getId());
				for(int j=0;j<doctorsId.length;j++)
				{
					
					cService.saveCommunityMembers(communityId , doctorsId[j]);
					Doctor doctor1 = lService.getDoctorById(Integer.parseInt(doctorsId[j]));
					
					String content1 = "<a href='getCommunity.community?id="+communityId+" '>Dr."+doctor.getFirstName()+" "+doctor.getLastName()+" Has Added you to  "+title+" Community </a>";
					Notification notification = new Notification(0, doctor.getId(), content1,doctor1.getId() , 0,1);
					notification.setImage(doctor.getImage());
					lService.saveNotification(notification);
				}
				}  catch (Exception e) {
					System.out.println("\n In controller Error:"+e.getMessage());
				
    				pw.print("empty");
			}
	
		}
		
		if(uri.endsWith("removeMember.notification"))
		{
			PrintWriter pw = response.getWriter();
			try {
				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int communityId = Integer.parseInt(request.getParameter("communityId"));
				String[] doctorsId = request.getParameterValues("members[]");
				
				String title = request.getParameter("title");
				CommunityService cService= new CommunityService();
				for(int j=0;j<doctorsId.length;j++)
				{
					
					cService.removeCommunityMembers(communityId , doctorsId[j]);
				}
				}  catch (Exception e) {
					System.out.println("\n In controller Error:"+e.getMessage());
				
    				pw.print("empty");
			}
	
		}
		
		if(uri.endsWith("saveComment.notification"))
		{
			PrintWriter pw = response.getWriter();
			try {
				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int postId = Integer.parseInt(request.getParameter("postId"));
				String message = request.getParameter("message");
				
				int cId = service.saveMessage(postId , message , login );
				pw.print(cId);
				
				Doctor doctor1 = lService.getDoctorById(login.getId());
				
				Post post = service.getPostById(postId);
				if(post.getDoctorId() != login.getId()){
					Doctor doctor = lService.getDoctorById(post.getDoctorId());
					String content1 = "<a href='#com"+postId+cId+"'>Dr."+doctor1.getFirstName()+" "+doctor1.getLastName()+" Has Commented on Your Post</a>";
					
					Notification notification = new Notification(0, doctor1.getId(), content1,doctor.getId() , 0,1);
					notification.setImage(doctor1.getImage());
					lService.saveNotification(notification);
				}
				
				
				
				}  catch (Exception e) {
					System.out.println("\n In controller Error:"+e.getMessage());
				
    				pw.print("empty");
			}
	
		}
		
		
		
		
		
	}

}
