package com.vinod.controller;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.vinod.model.Chat;

import com.vinod.model.Login;
import com.vinod.model.Message;

import com.vinod.service.ChatService;
import com.vinod.service.LoginService;

/**
 * Servlet implementation class MobileController
 */
@WebServlet("*.mobile")
public class MobileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(MobileController.class);   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MobileController() {
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
			HttpServletResponse response) throws ServletException, IOException {
		String uri=request.getRequestURI();
        ChatService service = new ChatService();
        LoginService lService = new LoginService();
        Login login=(Login) request.getSession().getAttribute("login");
        int id = login.getId();
        logger.info(uri);
        String target = "home.jsp";
        if(uri.endsWith("getChatMessages.mobile"))
		{
			target = "MobileChat.jsp";
	            	
	            	try {
	            	    long otherId = Long.parseLong(request.getParameter("otherId"));
	            	    int type = Integer.parseInt(request.getParameter("type"));
	            	    lService.setLastActive(login);
	            	    int chatId = service.getChatID(login,otherId,type);
	            	    List<Message> messages = new ArrayList<Message>();
	            	    if(chatId == 0)
	            	    {
	            	    	int i = service.createConversation(login,otherId,type);
	            	    	request.setAttribute("messages", messages);
	            	    }
	            	    
	            	    else
	            	    {
	            	    	
	            	    	messages = service.getMessagesByChatId(chatId);
	            	    	
	            	    	if(messages.isEmpty())
	            	    	{
	            	    		request.setAttribute("messages", messages);
	            	    	}
	            	    	else
	            	    	{
	            	    		service.setRead(chatId,login);
	            	    		request.setAttribute("messages", messages);
	            	    	}
	            	    	
	            	    }
	            	    
	            	    if(type==1)
	             	    {
	            	    	request.setAttribute("otherDoctor", lService.getDoctorById(otherId));
	             	    }
	             	    if(type==2)
	             	    {
	             	    	if(login.getType() == 1)
	             	    	{
	             	    		request.setAttribute("patient", lService.getPatientById(otherId));
	             	    	}
	             	    	if(login.getType() == 2)
	             	    	{
	             	    		request.setAttribute("doctor", lService.getDoctorById(otherId));
	             	    	}
	             	    	
	             	    }
	             	    if(type==3)
	             	    {
	             	    	if(login.getType() == 1)
	             	    	{
	             	    		request.setAttribute("pharmacy", lService.getPharmacyById(otherId));
	             	    	}
	             	    	if(login.getType() == 3)
	             	    	{
	             	    		request.setAttribute("doctor", lService.getDoctorById(otherId));
	             	    	}
	             	    }
	             	   if(type==4)
	            	    {
	             		  if(login.getType() == 2)
	            	    	{
	             			 request.setAttribute("pharmacy", lService.getPharmacyById(otherId));
	            	    	}
	            	    	if(login.getType() == 3)
	            	    	{
	            	    		request.setAttribute("patient", lService.getPatientById(otherId));
	            	    	}
	            	    }
	             	  if(type==5)
	            	    {
	             		 request.setAttribute("otherPharmacy", lService.getPharmacyById(otherId));
	            	    }
	            	    
	            	    
	            	    if(login.getType()==1)
	    				{
	    					request.setAttribute("doctor", lService.getDoctorById(id));
	    				}

	    				if(login.getType()==2)
	    				{
	    					request.setAttribute("patient", lService.getPatientById(id));
	    				}

	    				if(login.getType()==3)
	    				{
	    					request.setAttribute("pharmacy", lService.getPharmacyById(id));
	    				}
	    				request.setAttribute("type", type);
	    				request.setAttribute("chatId", chatId);
	            	    target=login.getType()+target;
	            	    
					} catch (Exception e) {
						try {
							logger.error(Level.SEVERE,e);
							lService.setErrorControl(request,target,login);
							request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
						} catch (Exception e1) {
							request.setAttribute("error", "Please Login To Continue.");
							target="home.jsp";
						}
					}
	            	request.getRequestDispatcher(target).forward(request, response);	
	           
		}
        
        if(uri.endsWith("saveMessages.mobile"))
		{
        	 target = "MobileChat.jsp";
	            	
	            	try {
	            		lService.setLastActive(login);
	            	    String message = request.getParameter("message");
	            	    int chatId = Integer.parseInt(request.getParameter("chatId"));
	            	    int type = Integer.parseInt(request.getParameter("type"));
	            	    service.saveMessage(login,chatId,message);
	            	    
	            	    List<Message> messages = new ArrayList<Message>();
            	    	messages = service.getMessagesByChatId(chatId);
            	    	
            	    	service.setRead(chatId,login);
            	    	
            	    	if(login.getType()==1)
	    				{
	    					request.setAttribute("doctor", lService.getDoctorById(id));
	    				}

	    				if(login.getType()==2)
	    				{
	    					request.setAttribute("patient", lService.getPatientById(id));
	    				}

	    				if(login.getType()==3)
	    				{
	    					request.setAttribute("pharmacy", lService.getPharmacyById(id));
	    				}
	    				
	    				
	    				Chat chat = service.getChatById(chatId);
	    				if(type==1)
	             	    {
	    					if(chat.getDoctorId()== login.getId())
							{
	            	    		if(chat.getOtherDoctorId()!=0)
	    						{
	    							
	    							request.setAttribute("otherDoctor", lService.getDoctorById(chat.getOtherDoctorId()));
	    						}
							}
	            	    	
	            	    	if(chat.getOtherDoctorId()== login.getId())
							{
	            	    		if(chat.getDoctorId()!=0)
	    						{
	    							request.setAttribute("otherDoctor", lService.getDoctorById(chat.getDoctorId()));
	    						}
							}
	    					
	            	    	
	             	    }
	             	    if(type==2)
	             	    {
	             	    	if(login.getType() == 1)
	             	    	{
	             	    		request.setAttribute("patient", lService.getPatientById(chat.getPatientId()));
	             	    	}
	             	    	if(login.getType() == 2)
	             	    	{
	             	    		request.setAttribute("doctor", lService.getDoctorById(chat.getDoctorId()));
	             	    	}
	             	    	
	             	    }
	             	    if(type==3)
	             	    {
	             	    	if(login.getType() == 1)
	             	    	{
	             	    		request.setAttribute("pharmacy", lService.getPharmacyById(chat.getPharmacyId()));
	             	    	}
	             	    	if(login.getType() == 3)
	             	    	{
	             	    		request.setAttribute("doctor", lService.getDoctorById(chat.getDoctorId()));
	             	    	}
	             	    }
	             	   if(type==4)
	            	    {
	             		  if(login.getType() == 2)
	            	    	{
	             			 request.setAttribute("pharmacy", lService.getPharmacyById(chat.getPharmacyId()));
	            	    	}
	            	    	if(login.getType() == 3)
	            	    	{
	            	    		request.setAttribute("patient", lService.getPatientById(chat.getPatientId()));
	            	    	}
	            	    }
	             	  if(type==5)
	            	    {
	             		 
	             		 
	             		if(chat.getPharmacyId()== login.getId())
						{
            	    		if(chat.getOtherPharmacyId()!=0)
    						{
            	    			request.setAttribute("otherPharmacy", lService.getPharmacyById(chat.getOtherPharmacyId()));
    							
    						}
						}
            	    	
            	    	if(chat.getOtherPharmacyId()== login.getId())
						{
            	    		if(chat.getPharmacyId()!=0)
    						{
            	    			request.setAttribute("otherPharmacy", lService.getPharmacyById(chat.getPharmacyId()));
    							
    						}
						}
	             		 
	             		 
	            	    }
	            	    
	            	    
	            	    if(login.getType()==1)
	    				{
	    					request.setAttribute("doctor", lService.getDoctorById(id));
	    				}

	    				if(login.getType()==2)
	    				{
	    					request.setAttribute("patient", lService.getPatientById(id));
	    				}

	    				if(login.getType()==3)
	    				{
	    					request.setAttribute("pharmacy", lService.getPharmacyById(id));
	    				}
	    				
	    				
	    				request.setAttribute("type", type);
	    				request.setAttribute("messages", messages);
	    				target=login.getType()+target;
	    				request.setAttribute("chatId", chatId);
					} catch (Exception e) {
						try {
							logger.error(Level.SEVERE,e);
							lService.setErrorControl(request,target,login);
							request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
						} catch (Exception e1) {
							request.setAttribute("error", "Please Login To Continue.");
							target="home.jsp";
						}
					}
	            	request.getRequestDispatcher(target).forward(request, response);	
	           
		}
        
        if(uri.endsWith("getMessages.mobile"))
		{
		
        	target = "MobileChat.jsp";
	            	try {
	            		lService.setLastActive(login);
	            	    int chatId = Integer.parseInt(request.getParameter("chatId"));
	            	    int type = Integer.parseInt(request.getParameter("type"));
                        service.setRead(chatId,login);
	            	    
	            	    List<Message> messages = new ArrayList<Message>();
            	    	messages = service.getMessagesByChatId(chatId);
            	    	service.setRead(chatId,login);
            	    	
            	    	if(login.getType()==1)
	    				{
	    					request.setAttribute("doctor", lService.getDoctorById(id));
	    				}

	    				if(login.getType()==2)
	    				{
	    					request.setAttribute("patient", lService.getPatientById(id));
	    				}

	    				if(login.getType()==3)
	    				{
	    					request.setAttribute("pharmacy", lService.getPharmacyById(id));
	    				}
	    				
	    				
	    				Chat chat = service.getChatById(chatId);
	    				if(type==1)
	             	    {
	    					if(chat.getDoctorId()== login.getId())
							{
	            	    		if(chat.getOtherDoctorId()!=0)
	    						{
	    							
	    							request.setAttribute("otherDoctor", lService.getDoctorById(chat.getOtherDoctorId()));
	    						}
							}
	            	    	
	            	    	if(chat.getOtherDoctorId()== login.getId())
							{
	            	    		if(chat.getDoctorId()!=0)
	    						{
	    							request.setAttribute("otherDoctor", lService.getDoctorById(chat.getDoctorId()));
	    						}
							}
	    					
	            	    	
	             	    }
	             	    if(type==2)
	             	    {
	             	    	if(login.getType() == 1)
	             	    	{
	             	    		request.setAttribute("patient", lService.getPatientById(chat.getPatientId()));
	             	    	}
	             	    	if(login.getType() == 2)
	             	    	{
	             	    		request.setAttribute("doctor", lService.getDoctorById(chat.getDoctorId()));
	             	    	}
	             	    	
	             	    }
	             	    if(type==3)
	             	    {
	             	    	if(login.getType() == 1)
	             	    	{
	             	    		request.setAttribute("pharmacy", lService.getPharmacyById(chat.getPharmacyId()));
	             	    	}
	             	    	if(login.getType() == 3)
	             	    	{
	             	    		request.setAttribute("doctor", lService.getDoctorById(chat.getDoctorId()));
	             	    	}
	             	    }
	             	   if(type==4)
	            	    {
	             		  if(login.getType() == 2)
	            	    	{
	             			 request.setAttribute("pharmacy", lService.getPharmacyById(chat.getPharmacyId()));
	            	    	}
	            	    	if(login.getType() == 3)
	            	    	{
	            	    		request.setAttribute("patient", lService.getPatientById(chat.getPatientId()));
	            	    	}
	            	    }
	             	  if(type==5)
	            	    {
	             		 
	             		 
	             		if(chat.getPharmacyId()== login.getId())
						{
            	    		if(chat.getOtherPharmacyId()!=0)
    						{
            	    			request.setAttribute("otherPharmacy", lService.getPharmacyById(chat.getOtherPharmacyId()));
    							
    						}
						}
            	    	
            	    	if(chat.getOtherPharmacyId()== login.getId())
						{
            	    		if(chat.getPharmacyId()!=0)
    						{
            	    			request.setAttribute("otherPharmacy", lService.getPharmacyById(chat.getPharmacyId()));
    							
    						}
						}
	             		 
	             		 
	            	    }
	            	    
	            	    
	            	    if(login.getType()==1)
	    				{
	    					request.setAttribute("doctor", lService.getDoctorById(id));
	    				}

	    				if(login.getType()==2)
	    				{
	    					request.setAttribute("patient", lService.getPatientById(id));
	    				}

	    				if(login.getType()==3)
	    				{
	    					request.setAttribute("pharmacy", lService.getPharmacyById(id));
	    				}
	    				
	    				
	    				request.setAttribute("type", type);
	    				request.setAttribute("messages", messages);
	    				target=login.getType()+target;
	    				request.setAttribute("chatId", chatId);
					} catch (Exception e) {
						try {
							logger.error(Level.SEVERE,e);
							target = lService.setErrorControl(request,target,login);
							request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
						} catch (Exception e1) {
							logger.error(Level.SEVERE,e1);
							request.setAttribute("error", "Please Login To Continue.");
							target="home.jsp";
						}
					}
	            	request.getRequestDispatcher(target).forward(request, response);
	      
		}
        
        
		
	}

}
