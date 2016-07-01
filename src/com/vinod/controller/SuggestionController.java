package com.vinod.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import com.vinod.model.Comment;
import com.vinod.model.Doctor;
import com.vinod.model.ForumPost;
import com.vinod.model.Login;
import com.vinod.model.Notification;
import com.vinod.model.Patient;

import com.vinod.model.Suggestion;

import com.vinod.service.LoginService;
import com.vinod.service.SuggestionService;

/**
 * Servlet implementation class SuggestionController
 */
@MultipartConfig
@WebServlet("*.suggestion")
public class SuggestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SuggestionController() {
		super();
		// TODO Auto-generated constructor stub
	}

	final static Logger logger = Logger.getLogger(SuggestionController.class);
	
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
			HttpServletResponse response) throws ServletException, IOException {
		String uri=request.getRequestURI();
		SuggestionService service = new SuggestionService();
		LoginService lService = new LoginService();
		logger.info(uri);
		Login login1=(Login) request.getSession().getAttribute("login");
		String target="";

		if(null == request.getSession().getAttribute("login")){  
			request.setAttribute("error", "Please Login To Continue.");
			target="home.jsp";
			request.getRequestDispatcher(target).forward(request, response); 
			return;
		}

		try {
			Login login=(Login) request.getSession().getAttribute("login");
			int i=login.getId();
		}
		catch (NullPointerException e) {
			request.setAttribute("error", "Please Login To Continue.");
			target="home.jsp";
			request.getRequestDispatcher(target).forward(request, response);
			return;

		}
		
		
		if(uri.endsWith("success.suggestion"))
		{
			try {
				Login login=(Login) request.getSession().getAttribute("login");
				int id=login.getId();

					int postId = Integer.parseInt(request.getParameter("id"));
					int commentId = Integer.parseInt(request.getParameter("cId"));
					String txnId = request.getParameter("tId");
					
					int i = service.saveSuggestionCommentPayment(postId,commentId,txnId);
					
					Suggestion post = service.getSuggestionById(postId);

					if(post.getId() == 0)
					{
						target = lService.setErrorControl(request,target,login1);
						request.setAttribute("error", "This Suggestion doesnot exist");
					}
					else
					{

						if(post.getPatientId() !=  id)
						{
							target = lService.setErrorControl(request,target,login1);
							request.setAttribute("error", "You can't view this Suggestion");
						}
						else
						{
							Patient patient = lService.getPatientById(id);
							post.setPatientName(patient.getFirstName()+" "+patient.getLastName());
							Doctor doctor = lService.getDoctorById(post.getDoctorId());
							post.setDoctorName(doctor.getFirstName()+" "+doctor.getLastName());
							List<Comment> comments = service.getAllCommentsBySuggestionId(post.getId());
							for(int j=0;j<comments.size();j++)
							{
								if(comments.get(j).getDoctorId()!=0)
								{
									comments.get(j).setDoctorName(doctor.getFirstName()+" "+doctor.getLastName());
								}
								if(comments.get(j).getPatientId()!=0)
								{
									comments.get(j).setPatientName(patient.getFirstName()+" "+patient.getLastName());
								}

							}
							post.setComment(comments);
							post.setComments(comments.size());

							request.setAttribute("error", "Payment is Successfull");
							request.setAttribute("insert", 1);
							request.setAttribute("patient", patient);
							request.setAttribute("post", post);
							target="patientViewSuggestion.jsp";
						}
					}
				

			} catch (Exception e) {

				try {
					logger.error(e.getStackTrace());
					target = lService.setErrorControl(request,target,login1);
					request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
				} catch (Exception e1) {

					request.setAttribute("error", "Please Login To Continue.");
					target="home.jsp";
				}
			}
			request.getRequestDispatcher(target).forward(request, response);
		}
		
		
		if(uri.endsWith("fail.suggestion"))
		{
			try {
				Login login=(Login) request.getSession().getAttribute("login");
				int id=login.getId();

					int postId = Integer.parseInt(request.getParameter("id"));
					Suggestion post = service.getSuggestionById(postId);

					if(post.getId() == 0)
					{
						target = lService.setErrorControl(request,target,login1);
						request.setAttribute("error", "This Suggestion doesnot exist");
					}
					else
					{

						if(post.getPatientId() !=  id)
						{
							target = lService.setErrorControl(request,target,login1);
							request.setAttribute("error", "You can't view this Suggestion");
						}
						else
						{
							Patient patient = lService.getPatientById(id);
							post.setPatientName(patient.getFirstName()+" "+patient.getLastName());
							Doctor doctor = lService.getDoctorById(post.getDoctorId());
							post.setDoctorName(doctor.getFirstName()+" "+doctor.getLastName());
							List<Comment> comments = service.getAllCommentsBySuggestionId(post.getId());
							for(int j=0;j<comments.size();j++)
							{
								if(comments.get(j).getDoctorId()!=0)
								{
									comments.get(j).setDoctorName(doctor.getFirstName()+" "+doctor.getLastName());
								}
								if(comments.get(j).getPatientId()!=0)
								{
									comments.get(j).setPatientName(patient.getFirstName()+" "+patient.getLastName());
								}

							}
							post.setComment(comments);
							post.setComments(comments.size());


							request.setAttribute("error", "Payment Failed");
							request.setAttribute("patient", patient);
							request.setAttribute("post", post);
							target="patientViewSuggestion.jsp";
						}
					}
				

			} catch (Exception e) {

				try {
					logger.error(e.getStackTrace());
					target = lService.setErrorControl(request,target,login1);
					request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
				} catch (Exception e1) {

					request.setAttribute("error", "Please Login To Continue.");
					target="home.jsp";
				}
			}
			request.getRequestDispatcher(target).forward(request, response);
		}
		

		if(uri.endsWith("createSuggestion.suggestion"))
		{
			try {
				Login login=(Login) request.getSession().getAttribute("login");
				int id=login.getId();
				List<Doctor> doctors = lService.getAllDoctors(id);
				request.setAttribute("doctors", doctors);
				request.setAttribute("patient", lService.getPatientById(id));

				target="patientCreateSuggestion.jsp";

			} catch (Exception e) {
				try {
					logger.error(e.getStackTrace());
					target = lService.setErrorControl(request,target,login1);
					request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
				} catch (Exception e1) {

					request.setAttribute("error", "Please Login To Continue.");
					target="home.jsp";
				}
			}
			request.getRequestDispatcher(target).forward(request, response);
		}

		if(uri.endsWith("suggestion.suggestion"))
		{
			try {
				Login login=(Login) request.getSession().getAttribute("login");
				int id=login.getId();

				if(login.getType()==1)
				{

					List<Suggestion> posts = service.getAllSuggestionForDoctorById(id);

					for(int i=0;i<posts.size();i++)
					{
						Doctor doctor = lService.getDoctorById(posts.get(i).getDoctorId());
						Patient patient = lService.getPatientById(posts.get(i).getPatientId());
						posts.get(i).setDoctorName(doctor.getFirstName()+" "+doctor.getLastName());
						posts.get(i).setPatientName(patient.getFirstName()+" "+patient.getLastName());
						List<Comment> comments = service.getAllCommentsBySuggestionId(posts.get(i).getId());
						posts.get(i).setComment(comments);
						posts.get(i).setComments(comments.size());

					}




					request.setAttribute("doctor", lService.getDoctorById(id));
					request.setAttribute("posts", posts);
					target="doctorSuggestion.jsp";
				} 


				if(login.getType()==2)
				{


					List<Suggestion> posts = service.getAllSuggestionForPatientById(id);

					for(int i=0;i<posts.size();i++)
					{
						Patient patient = lService.getPatientById(posts.get(i).getPatientId());
						posts.get(i).setPatientName(patient.getFirstName()+" "+patient.getLastName());
						Doctor doctor = lService.getDoctorById(posts.get(i).getDoctorId());
						posts.get(i).setDoctorName(doctor.getFirstName()+" "+doctor.getLastName());
						List<Comment> comments = service.getAllCommentsBySuggestionId(posts.get(i).getId());
						posts.get(i).setComment(comments);
						posts.get(i).setComments(comments.size());

					}



					request.setAttribute("patient", lService.getPatientById(id));
					request.setAttribute("posts", posts);
					target="patientSuggestion.jsp";
				}

			}catch (Exception e) {
				try {
					logger.error(e.getStackTrace());
					target = lService.setErrorControl(request,target,login1);
					request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
				} catch (Exception e1) {

					request.setAttribute("error", "Please Login To Continue.");
					target="home.jsp";
				}
			}

			request.getRequestDispatcher(target).forward(request, response);
		}

		if(uri.endsWith("getSuggestion.suggestion"))
		{
			try {
				Login login=(Login) request.getSession().getAttribute("login");
				int id=login.getId();

				if(login.getType()==1)
				{

					int postId = Integer.parseInt(request.getParameter("id"));
					Suggestion post = service.getSuggestionById(postId);
					if(post.getId() == 0)
					{
						target = lService.setErrorControl(request,target,login1);
						request.setAttribute("error", "This Suggestion doesnot exist");
					}
					else
					{

						if(post.getDoctorId() !=  id)
						{
							target = lService.setErrorControl(request,target,login1);
							request.setAttribute("error", "You can't view this Suggestion");
						}
						else
						{
							Doctor doctor = lService.getDoctorById(id);
							post.setDoctorName(doctor.getFirstName()+" "+doctor.getLastName());


							Patient patient = lService.getPatientById(post.getPatientId());

							post.setPatientName(patient.getFirstName()+" "+patient.getLastName());
							List<Comment> comments = service.getAllCommentsBySuggestionId(post.getId());
							for(int j=0;j<comments.size();j++)
							{
								if(comments.get(j).getDoctorId()!=0)
								{
									comments.get(j).setDoctorName(doctor.getFirstName()+" "+doctor.getLastName());
								}
								if(comments.get(j).getPatientId()!=0)
								{
									comments.get(j).setPatientName(patient.getFirstName()+" "+patient.getLastName());
								}

							}
							post.setComment(comments);
							post.setComments(comments.size());


							request.setAttribute("doctor", doctor);
							request.setAttribute("post", post);
							target="doctorViewSuggestion.jsp";
						}
					}

				}

				if(login.getType()==2)
				{

					int postId = Integer.parseInt(request.getParameter("id"));
					Suggestion post = service.getSuggestionById(postId);

					if(post.getId() == 0)
					{
						target = lService.setErrorControl(request,target,login1);
						request.setAttribute("error", "This Suggestion doesnot exist");
					}
					else
					{

						if(post.getPatientId() !=  id)
						{
							target = lService.setErrorControl(request,target,login1);
							request.setAttribute("error", "You can't view this Suggestion");
						}
						else
						{
							Patient patient = lService.getPatientById(id);
							post.setPatientName(patient.getFirstName()+" "+patient.getLastName());
							Doctor doctor = lService.getDoctorById(post.getDoctorId());
							post.setDoctorName(doctor.getFirstName()+" "+doctor.getLastName());
							List<Comment> comments = service.getAllCommentsBySuggestionId(post.getId());
							for(int j=0;j<comments.size();j++)
							{
								if(comments.get(j).getDoctorId()!=0)
								{
									comments.get(j).setDoctorName(doctor.getFirstName()+" "+doctor.getLastName());
								}
								if(comments.get(j).getPatientId()!=0)
								{
									comments.get(j).setPatientName(patient.getFirstName()+" "+patient.getLastName());
								}

							}
							post.setComment(comments);
							post.setComments(comments.size());



							request.setAttribute("patient", patient);
							request.setAttribute("post", post);
							target="patientViewSuggestion.jsp";
						}
					}
				}

			} catch (Exception e) {

				try {
					logger.error(e.getStackTrace());
					target = lService.setErrorControl(request,target,login1);
					request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
				} catch (Exception e1) {

					request.setAttribute("error", "Please Login To Continue.");
					target="home.jsp";
				}
			}
			request.getRequestDispatcher(target).forward(request, response);
		}

		if(uri.endsWith("saveComment.suggestion"))
		{
			try {
				Login login=(Login) request.getSession().getAttribute("login");
				int id=login.getId();

				if(login.getType()==1)
				{

					int postId = Integer.parseInt(request.getParameter("id"));
					int fees = Integer.parseInt(request.getParameter("fees"));
					service.saveDoctorSuggestionComment(postId,id,request.getParameter("comment"),fees);
					Suggestion post = service.getSuggestionById(postId);
					if(post.getId() == 0)
					{
						target = lService.setErrorControl(request,target,login1);
						request.setAttribute("error", "This Suggestion doesnot exist");
					}
					else
					{

						Doctor doctor = lService.getDoctorById(post.getDoctorId());
						post.setDoctorName(doctor.getFirstName()+" "+doctor.getLastName());


						Patient patient = lService.getPatientById(post.getPatientId());

						post.setPatientName(patient.getFirstName()+" "+patient.getLastName());
						List<Comment> comments = service.getAllCommentsBySuggestionId(post.getId());
						for(int j=0;j<comments.size();j++)
						{
							if(comments.get(j).getDoctorId()!=0)
							{
								comments.get(j).setDoctorName(doctor.getFirstName()+" "+doctor.getLastName());
							}
							if(comments.get(j).getPatientId()!=0)
							{
								comments.get(j).setPatientName(patient.getFirstName()+" "+patient.getLastName());
							}

						}
						post.setComment(comments);
						post.setComments(comments.size());


						String content1 = "<a href='getSuggestion.suggestion?id="+postId+" '>Dr."+post.getDoctorName()+" Has Replied for Suggestion </a>";
						Notification notification = new Notification(0, id, content1,post.getPatientId() , 0,2);
						notification.setImage(doctor.getImage());
						lService.saveNotification(notification);

						request.setAttribute("doctor", doctor);
						request.setAttribute("post", post);

						target="doctorViewSuggestion.jsp";

					}
				}

				if(login.getType()==2)
				{

					int postId = Integer.parseInt(request.getParameter("id"));
					service.savePatientSuggestionComment(postId,id,request.getParameter("comment"));
					Suggestion post = service.getSuggestionById(postId);
					if(post.getId() == 0)
					{
						target = lService.setErrorControl(request,target,login1);
						request.setAttribute("error", "This Suggestion doesnot exist");
					}
					else
					{

						Patient patient = lService.getPatientById(post.getPatientId());
						post.setPatientName(patient.getFirstName()+" "+patient.getLastName());
						Doctor doctor = lService.getDoctorById(post.getDoctorId());
						List<Comment> comments = service.getAllCommentsBySuggestionId(post.getId());
						for(int j=0;j<comments.size();j++)
						{
							if(comments.get(j).getDoctorId()!=0)
							{
								comments.get(j).setDoctorName(doctor.getFirstName()+" "+doctor.getLastName());
							}
							if(comments.get(j).getPatientId()!=0)
							{
								comments.get(j).setPatientName(patient.getFirstName()+" "+patient.getLastName());
							}

						}
						post.setComment(comments);
						post.setComments(comments.size());

						String content1 = "<a href='getSuggestion.suggestion?id="+postId+" '>"+post.getPatientName()+" Has Replied for Suggestion </a>";
						Notification notification = new Notification(0, id, content1,post.getDoctorId() , 0,1);
						notification.setImage(patient.getImage());
						lService.saveNotification(notification);

						request.setAttribute("patient", patient);
						request.setAttribute("post", post);
						target="patientViewSuggestion.jsp";
					}
				}
				 request.setAttribute("insert", 1);
			} catch (Exception e) {
				try {
					logger.error(e.getStackTrace());
					target = lService.setErrorControl(request,target,login1);
					request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
				} catch (Exception e1) {

					request.setAttribute("error", "Please Login To Continue.");
					target="home.jsp";
				}
			}
			request.getRequestDispatcher(target).forward(request, response);
		}

		if(uri.endsWith("saveSuggestion.suggestion"))
		{
			try {
				Login login=(Login) request.getSession().getAttribute("login");
				int id=login.getId();


				int doctorId = Integer.parseInt(request.getParameter("doctorid"));

				String name = request.getParameter("postname");
				String content = request.getParameter("postcontent");

				String forumPath ="";
				Part filePart = request.getPart("file");

				if(filePart.getSize()!=0)
				{
					String SAVE_DIR = "Images"+ File.separator +"Suggestion"+ File.separator +"suggestion"+login.getUserName();
					// gets absolute path of the web application
					String appPath = request.getServletContext().getRealPath("");
					// constructs path of the directory to save uploaded file
					String savePath = appPath + File.separator + SAVE_DIR;

					// creates the save directory if it does not exists
					File fileSaveDir = new File(savePath);
					if (!fileSaveDir.exists()) {
						fileSaveDir.mkdir();
					}

					for (Part part : request.getParts()) {
						String fileName = extractFileName(part);
						if(fileName!=null)
						{
							forumPath=SAVE_DIR + File.separator +new Date().getTime();
						}

						part.write(savePath + File.separator +new Date().getTime());

					}
				}

				ForumPost post = new ForumPost();
				post.setPatientId(id);
				post.setDoctorId(doctorId);
				post.setName(name);
				post.setContent(content);
				post.setForumImage(forumPath);
				int postId=service.saveSuggestion(post);

				post.setId(postId);



				Patient patient = lService.getPatientById(id);
				post.setPatientName(patient.getFirstName()+" "+patient.getLastName());

				Doctor doctor = lService.getDoctorById(doctorId);
				post.setDoctorName(doctor.getFirstName()+" "+doctor.getLastName());
				String content1 = "<a href='getSuggestion.suggestion?id="+postId+" '>"+post.getPatientName()+" Has Asked for Suggestion </a>";
				Notification notification = new Notification(0, id, content1,doctorId , 0,1);
				notification.setImage(patient.getImage());
				lService.saveNotification(notification);

				request.setAttribute("patient", patient);
				request.setAttribute("msg", "Successfully Created Suggestion");
				request.setAttribute("post", post);
				target="patientViewSuggestion.jsp";
				request.setAttribute("insert", 1);

			}catch (Exception e) {
				try {
					logger.error(e.getStackTrace());
					target = lService.setErrorControl(request,target,login1);
					request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
				} catch (Exception e1) {

					request.setAttribute("error", "Please Login To Continue.");
					target="home.jsp";
				}
			}
			request.getRequestDispatcher(target).forward(request, response);
		}
	}

	private String extractFileName(Part part) {
		final String partHeader = part.getHeader("content-disposition");


		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {


				if(content.contains(":"))
				{

					content = content.substring(
							content.indexOf('=') + 1).trim().replace("\"", "");
					File file = new File(content);
					String name = file.getName();
					return name;
				}
				else
				{
					return content.substring(
							content.indexOf('=') + 1).trim().replace("\"", "");
				}

			}
		}
		return null;
	}


}



