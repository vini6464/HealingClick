package com.vinod.controller;


import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.vinod.model.Comment;
import com.vinod.model.Doctor;
import com.vinod.model.ForumPost;
import com.vinod.model.Login;
import com.vinod.model.Patient;
import com.vinod.model.Pharmacy;

import com.vinod.service.LoginService;
import com.vinod.service.SupportService;


/**
 * Servlet implementation class SupportController
 */
@WebServlet("*.support")
public class SupportController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupportController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    final static Logger logger = Logger.getLogger(SupportController.class);

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
		String target = "home.jsp";
		logger.info(uri);
		
		Login login1 = new Login();
		try {
			if(null == request.getSession().getAttribute("login")){  
				request.setAttribute("error", "Please Login To Continue.");
				target="home.jsp";
				request.getRequestDispatcher(target).forward(request, response); 
				return;

			}

			login1=(Login) request.getSession().getAttribute("login");
			int i=login1.getId();
		}
		catch (Exception e) {
			request.setAttribute("error", "Please Login To Continue.");
			target="home.jsp";
			request.getRequestDispatcher(target).forward(request, response);
			return;
			//response.sendRedirect(target);
		}
		SupportService service = new SupportService();
		LoginService lService = new LoginService();

		
		try {
		
			if(uri.endsWith("support.support"))
			{
				
					Login login=(Login) request.getSession().getAttribute("login");
					lService.setLastActive(login);
					int id=login.getId();

					if(login.getType()== 1)
					{

						List<ForumPost> posts = service.getAllQuestions(login);

						for(int i=0;i<posts.size();i++)
						{
							

							List<Comment> comments = service.getAllQuestionCommentsById(posts.get(i).getId());
							posts.get(i).setComment(comments);
							posts.get(i).setComments(comments.size());
							
						}
						request.setAttribute("doctor", lService.getDoctorById(id));
						request.setAttribute("posts", posts);
						target="doctorComplain.jsp";
					} 


					if(login.getType()== 2)
					{

						List<ForumPost> posts = service.getAllQuestions(login);

						for(int i=0;i<posts.size();i++)
						{
							
							List<Comment> comments = service.getAllQuestionCommentsById(posts.get(i).getId());
							
							posts.get(i).setComment(comments);
							posts.get(i).setComments(comments.size());
						}
						request.setAttribute("patient", lService.getPatientById(id));
						request.setAttribute("posts", posts);
						
						
						target="patientComplain.jsp";
					}


					if(login.getType()== 3)
					{

						List<ForumPost> posts = service.getAllQuestions(login);

						for(int i=0;i<posts.size();i++)
						{
							

							List<Comment> comments = service.getAllQuestionCommentsById(posts.get(i).getId());
							
							posts.get(i).setComment(comments);
							posts.get(i).setComments(comments.size());
						}
						
						request.setAttribute("pharmacy", lService.getPharmacyById(id));
						request.setAttribute("posts", posts);
						target="pharmacyComplain.jsp";
					}
				
			}
			//********^%^*TRTGFFFFFFFFFFFFFFFFFF

			if(uri.endsWith("getSupport.support"))
			{
				
					Login login=(Login) request.getSession().getAttribute("login");
					lService.setLastActive(login);
					int id=login.getId();

					if(login.getType()==1)
					{

						int postId = Integer.parseInt(request.getParameter("id"));
						ForumPost post = service.getSupportById(postId , login.getType());
						if(post.getId() == 0)
						{
							target = lService.setErrorControl(request,target,login1);
							request.setAttribute("error", "This Support doesnot exist");
						}
						else
						{
						List<Comment> comments = service.getAllSupportCommentsById(post.getId());
						
						post.setComment(comments);
						post.setComments(comments.size());

						request.setAttribute("doctor", lService.getDoctorById(id));
						request.setAttribute("post", post);
						target="doctorViewComplaint.jsp";
						}

					}

					if(login.getType()==2)
					{


						int postId = Integer.parseInt(request.getParameter("id"));
						ForumPost post = service.getSupportById(postId , login.getType());
						if(post.getId() == 0)
						{
							target = lService.setErrorControl(request,target,login1);
							request.setAttribute("error", "This Support doesnot exist");
						}
						else
						{
						List<Comment> comments = service.getAllSupportCommentsById(post.getId());
						
						post.setComment(comments);
						post.setComments(comments.size());

						request.setAttribute("patient", lService.getPatientById(id));
						request.setAttribute("post", post);
						
						
						target="patientViewComplaint.jsp";
						}
					}

					if(login.getType()==3)
					{

						int postId = Integer.parseInt(request.getParameter("id"));
						ForumPost post = service.getSupportById(postId , login.getType());
						if(post.getId() == 0)
						{
							target = lService.setErrorControl(request,target,login1);
							request.setAttribute("error", "This Support doesnot exist");
						}
						else
						{
						List<Comment> comments = service.getAllSupportCommentsById(post.getId());
						
						post.setComment(comments);
						post.setComments(comments.size());
						
						request.setAttribute("pharmacy", lService.getPharmacyById(id));
						request.setAttribute("post", post);
						target="pharmacyViewComplain.jsp";
						}
					}
				
			}

			//#########################################


			if(uri.endsWith("saveComment.support"))
			{
				
					Login login=(Login) request.getSession().getAttribute("login");
					lService.setLastActive(login);
					int id=login.getId();

					if(login.getType()==1)
					{

						int postId = Integer.parseInt(request.getParameter("id"));
						service.saveSupportComment(postId,id,request.getParameter("comment"));
						ForumPost post = service.getSupportById(postId , login.getType());
						if(post.getId() == 0)
						{
							target = lService.setErrorControl(request,target,login1);
							request.setAttribute("error", "This Support doesnot exist");
						}
						else
						{
						List<Comment> comments = service.getAllSupportCommentsById(post.getId());
						
						post.setComment(comments);
						post.setComments(comments.size());

						request.setAttribute("doctor", lService.getDoctorById(id));
						request.setAttribute("post", post);
						target="doctorViewComplaint.jsp";
						}
					}

					if(login.getType()==2)
					{

						int postId = Integer.parseInt(request.getParameter("id"));
						service.saveSupportComment(postId,id,request.getParameter("comment"));
						ForumPost post = service.getSupportById(postId , login.getType());
						if(post.getId() == 0)
						{
							target = lService.setErrorControl(request,target,login1);
							request.setAttribute("error", "This Support doesnot exist");
						}
						else
						{
						List<Comment> comments = service.getAllSupportCommentsById(post.getId());
						
						post.setComment(comments);
						post.setComments(comments.size());

						request.setAttribute("patient", lService.getPatientById(id));
						request.setAttribute("post", post);
						
						
						target="patientViewComplaint.jsp";
						}
					}

					if(login.getType()==3)
					{

						int postId = Integer.parseInt(request.getParameter("id"));
						service.saveSupportComment(postId,id,request.getParameter("comment"));
						ForumPost post = service.getSupportById(postId , login.getType());
						if(post.getId() == 0)
						{
							target = lService.setErrorControl(request,target,login1);
							request.setAttribute("error", "This Support doesnot exist");
						}
						else
						{
						List<Comment> comments = service.getAllSupportCommentsById(post.getId());
						
						post.setComment(comments);
						post.setComments(comments.size());
						
						request.setAttribute("pharmacy", lService.getPharmacyById(id));
						request.setAttribute("post", post);
						target="pharmacyViewComplain.jsp";
						}
					}
					request.setAttribute("insert", 1);
			}
			//**********************************************

			
		} catch (Exception e) {
			try {
				logger.error(e.getStackTrace());
				target = lService.setErrorControl(request,target,login1);
				request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
			} catch (Exception e1) {
				logger.error(e1.getStackTrace());
				request.setAttribute("error", "Please Login To Continue.");
				target="home.jsp";
			}

		}
		request.getRequestDispatcher(target).forward(request, response);
		
	}

}
