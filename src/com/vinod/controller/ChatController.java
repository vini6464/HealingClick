package com.vinod.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import com.vinod.model.Chat;
import com.vinod.model.Doctor;
import com.vinod.model.Login;
import com.vinod.model.Message;
import com.vinod.model.Patient;
import com.vinod.model.Pharmacy;
import com.vinod.model.User;
import com.vinod.service.ChatService;
import com.vinod.service.LoginService;

/**
 * Servlet implementation class ChatController
 */
@WebServlet("*.chat")
public class ChatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChatController() {
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
		String uri=request.getRequestURI();
		System.out.println(uri);
        ChatService service = new ChatService();
        LoginService lService = new LoginService();
        String target = "home.jsp";
        
        Login login=(Login) request.getSession().getAttribute("login");
        int id = 0;
        try {
			if(null == request.getSession().getAttribute("login")){  
				request.setAttribute("error", "Please Login To Continue.");
				target="home.jsp";
				request.getRequestDispatcher(target).forward(request, response); 
				return;
				
				}
			id=login.getId();
		}
		catch (Exception e) {
			request.setAttribute("error", "Please Login To Continue.");
			target="home.jsp";
			request.getRequestDispatcher(target).forward(request, response);
			return;
			//response.sendRedirect(target);
		}
        
        
		
		if(uri.endsWith("getactiveuser.chat"))
		{
			
			
			
			 if(login.getType()==1)
	            {
	            	
	            	try {
	            		lService.setLastActive(login);
	            		List<User> users = new ArrayList<User>();
	            		service.getActivePatients(login,users);
	            		service.getActivePharmacies(login,users);
	            		service.getActiveDoctors(login , users);
	            		Gson gson = new Gson();
	    				// convert your list to json
	    				String list = gson.toJson(users);
	    				
	    				PrintWriter pw = response.getWriter();

	    				pw.print(list);
					} catch (Exception e) {
						PrintWriter pw = response.getWriter();
	    				pw.print("empty");
					}
					
	            }
	            
	            if(login.getType()==2)
	            {
	            	
	            	try {
	            		List<User> users = new ArrayList<User>();
	            		lService.setLastActive(login);
	            		service.getActiveDoctors(login , users);
	            		service.getActivePharmacies(login,users);
	            		Gson gson = new Gson();
	    				// convert your list to json
	    				String list = gson.toJson(users);
	    				
	    				PrintWriter pw = response.getWriter();

	    				pw.print(list);
	            		
					}catch (Exception e) {
						PrintWriter pw = response.getWriter();
	    				pw.print("empty");
					}
					
	            }
	            
	            
	            if(login.getType()==3)
	            {
	            	
	            	try {
	            		List<User> users = new ArrayList<User>();
	            		lService.setLastActive(login);
	            		service.getActivePharmacies(login,users);
	            		Gson gson = new Gson();
	    				// convert your list to json
	    				String list = gson.toJson(users);
	    				
	    				PrintWriter pw = response.getWriter();

	    				pw.print(list);
	            		
					}catch (Exception e) {
						PrintWriter pw = response.getWriter();
	    				pw.print("empty");
					}
					
	            }
		}

		if(uri.endsWith("getConnectedActiveStatus.chat"))
		{
			
			
			
			 if(login.getType()==1)
	            {
	            	
	            	try {
	            		lService.setLastActive(login);
	            		List<User> users = new ArrayList<User>();
	            		service.getConnectedPatients(login,users);
	            		service.getConnectedPharmacies(login,users);
	            		service.getConnectedDoctors(login , users);
	            		Gson gson = new Gson();
	    				// convert your list to json
	    				String list = gson.toJson(users);
	    				
	    				PrintWriter pw = response.getWriter();

	    				pw.print(list);
					} catch (Exception e) {
						PrintWriter pw = response.getWriter();
	    				pw.print("empty");
					}
					
	            }
	            
	            if(login.getType()==2)
	            {
	            	
	            	try {
	            		List<User> users = new ArrayList<User>();
	            		lService.setLastActive(login);
	            		service.getConnectedDoctors(login , users);
	            		service.getConnectedPharmacies(login,users);
	            		Gson gson = new Gson();
	    				// convert your list to json
	    				String list = gson.toJson(users);
	    				
	    				PrintWriter pw = response.getWriter();

	    				pw.print(list);
	            		
					}catch (Exception e) {
						PrintWriter pw = response.getWriter();
	    				pw.print("empty");
					}
					
	            }
	            
	            
	            if(login.getType()==3)
	            {
	            	
	            	try {
	            		List<User> users = new ArrayList<User>();
	            		lService.setLastActive(login);
	            		service.getConnectedPharmacies(login,users);
	            		Gson gson = new Gson();
	    				// convert your list to json
	    				String list = gson.toJson(users);
	    				
	    				PrintWriter pw = response.getWriter();

	    				pw.print(list);
	            		
					}catch (Exception e) {
						PrintWriter pw = response.getWriter();
	    				pw.print("empty");
					}
					
	            }
		}
		
		
		if(uri.endsWith("newUserChat.chat"))
		{
			 if(login.getType()==1)
	            {
	            	
	            	try {
	            		lService.setLastActive(login);
	            		List<User> users = new ArrayList<User>();
	            		service.getPatients(login,users);
	            		service.getPharmacies(login,users);
	            		Gson gson = new Gson();
	    				// convert your list to json
	    				String list = gson.toJson(users);
	    				
	    				PrintWriter pw = response.getWriter();

	    				pw.print(list);
					} catch (Exception e) {
						PrintWriter pw = response.getWriter();
	    				pw.print("empty");
					}
					
	            }
	            
	            if(login.getType()==2)
	            {
	            	
	            	try {
	            		List<User> users = new ArrayList<User>();
	            		lService.setLastActive(login);
	            		service.getDoctors(login , users);
	            		service.getPharmacies(login,users);
	            		Gson gson = new Gson();
	    				// convert your list to json
	    				String list = gson.toJson(users);
	    				
	    				PrintWriter pw = response.getWriter();

	    				pw.print(list);
	            		
					}catch (Exception e) {
						PrintWriter pw = response.getWriter();
	    				pw.print("empty");
					}
					
	            }
	            
	            
	            if(login.getType()==3)
	            {
	            	
	            	try {
	            		List<User> users = new ArrayList<User>();
	            		lService.setLastActive(login);
	            		service.getPharmacies(login,users);
	            		Gson gson = new Gson();
	    				// convert your list to json
	    				String list = gson.toJson(users);
	    				
	    				PrintWriter pw = response.getWriter();

	    				pw.print(list);
	            		
					}catch (Exception e) {
						PrintWriter pw = response.getWriter();
	    				pw.print("empty");
					}
					
	            }
		}
		
		
		if(uri.endsWith("getnonactiveuser.chat"))
		{		
			try {
				lService.setLastActive(login);
				
				List<Chat> chats = service.getLatestChat(login);

				for(int i=0;i<chats.size();i++)
				{
					if(chats.get(i).getDoctorId()!=0)
					{
						Doctor d = lService.getDoctorById(chats.get(i).getDoctorId());
						chats.get(i).setDoctorName(d.getFirstName()+" "+d.getLastName());
						chats.get(i).setDoctorImage(d.getImage());
					}
					if(chats.get(i).getPatientId()!=0)
					{
						Patient p = lService.getPatientById(chats.get(i).getPatientId());
						chats.get(i).setPatientName(p.getFirstName()+" "+p.getLastName());
						chats.get(i).setPatientImage(p.getImage());
					}
					if(chats.get(i).getPharmacyId()!=0)
					{
						Pharmacy p = lService.getPharmacyById(chats.get(i).getPharmacyId());
						chats.get(i).setPharmacyName(p.getPharmacyName());
						chats.get(i).setPharmacyImage(p.getImage());
					}
					if(chats.get(i).getOtherDoctorId()!=0)
					{
						Doctor d = lService.getDoctorById(chats.get(i).getOtherDoctorId());
						chats.get(i).setOtherDoctorName(d.getFirstName()+" "+d.getLastName());
						chats.get(i).setOtherDoctorImage(d.getImage());
					}
					if(chats.get(i).getOtherPatientId()!=0)
					{
						Patient p = lService.getPatientById(chats.get(i).getOtherPatientId());
						chats.get(i).setOtherPatientName(p.getFirstName()+" "+p.getLastName());
						chats.get(i).setOtherPatientImage(p.getImage());
					}
					if(chats.get(i).getOtherPharmacyId()!=0)
					{
						Pharmacy p = lService.getPharmacyById(chats.get(i).getOtherPharmacyId());
						chats.get(i).setOtherPharmacyName(p.getPharmacyName());
						chats.get(i).setOtherPharmacyImage(p.getImage());
					}

				}
				Gson gson = new Gson();
				// convert your list to json
				String list = gson.toJson(chats);
				
				PrintWriter pw = response.getWriter();

				pw.print(list);


			} catch (Exception e) {
				PrintWriter pw = response.getWriter();
				pw.print("empty");
			}


		}
		
		
		if(uri.endsWith("getChatMessages.chat"))
		{
		
	            	
	            	try {
	            	    long otherId = Long.parseLong(request.getParameter("otherId"));
	            	    int type = Integer.parseInt(request.getParameter("type"));
	            	    lService.setLastActive(login);
	            	    int chatId = service.getChatID(login,otherId,type);
	            	 
	            	    if(chatId == 0)
	            	    {
	            	    	int i = service.createConversation(login,otherId,type);
	            	    	Gson gson = new Gson();
		    				// convert your list to json
		    				String messageList = gson.toJson(i);
		    				
		    				PrintWriter pw = response.getWriter();

		    				pw.print(messageList);
	            	    }
	            	    
	            	    else
	            	    {
	            	    	List<Message> messages = new ArrayList<Message>();
	            	    	messages = service.getMessagesByChatId(chatId);
	            	    	
	            	    	if(messages.isEmpty())
	            	    	{
	            	    		Gson gson = new Gson();
			    				// convert your list to json
			    				String messageList = gson.toJson(chatId);
			    				
			    				PrintWriter pw = response.getWriter();

			    				pw.print(messageList);
	            	    	}
	            	    	else
	            	    	{
	            	    		service.setRead(chatId,login);
	            	    		Gson gson = new Gson();
			    				// convert your list to json
			    				String messageList = gson.toJson(messages);
			    				
			    				PrintWriter pw = response.getWriter();

			    				pw.print(messageList);
	            	    	}
	            	    	
	            	    }
	            	    
	            		
					} catch (Exception e) {
						PrintWriter pw = response.getWriter();
	    				pw.print("empty");
					}
					
	           
		}
		
		
		
		if(uri.endsWith("getMessages.chat"))
		{
		
	            	
	            	try {
	            		lService.setLastActive(login);
	            	    int chatId = Integer.parseInt(request.getParameter("chatId"));
	            	    
                        service.setRead(chatId,login);
	            	    
	            	    List<Message> messages = new ArrayList<Message>();
            	    	messages = service.getMessagesByChatId(chatId);
            	    	Gson gson = new Gson();
	    				// convert your list to json
	    				String messageList = gson.toJson(messages);
	    				
	    				PrintWriter pw = response.getWriter();

	    				pw.print(messageList);
	            	    
	            		
					} catch (Exception e) {
						PrintWriter pw = response.getWriter();
	    				pw.print("empty");
					}
					
	           
		}
		
		if(uri.endsWith("getRecentChat.chat"))
		{
		
	            	
	            	try {
	            		lService.setLastActive(login);
	            	    List<Chat> chats = new ArrayList<Chat>();
            	    	chats = service.getNewMessages(login);
            	    	
            	    	for(int i=0;i<chats.size();i++)
        				{
        					if(chats.get(i).getDoctorId()!=0)
        					{
        						Doctor d = lService.getDoctorById(chats.get(i).getDoctorId());
        						chats.get(i).setDoctorName(d.getFirstName()+" "+d.getLastName());
        					}
        					if(chats.get(i).getPatientId()!=0)
        					{
        						Patient p = lService.getPatientById(chats.get(i).getPatientId());
        						chats.get(i).setPatientName(p.getFirstName()+" "+p.getLastName());
        					}
        					if(chats.get(i).getPharmacyId()!=0)
        					{
        						Pharmacy p = lService.getPharmacyById(chats.get(i).getPharmacyId());
        						chats.get(i).setPharmacyName(p.getPharmacyName());
        					}
        					if(chats.get(i).getOtherDoctorId()!=0)
        					{
        						Doctor d = lService.getDoctorById(chats.get(i).getOtherDoctorId());
        						chats.get(i).setOtherDoctorName(d.getFirstName()+" "+d.getLastName());
        					}
        					if(chats.get(i).getOtherPatientId()!=0)
        					{
        						Patient p = lService.getPatientById(chats.get(i).getOtherPatientId());
        						chats.get(i).setOtherPatientName(p.getFirstName()+" "+p.getLastName());
        					}
        					if(chats.get(i).getOtherPharmacyId()!=0)
        					{
        						Pharmacy p = lService.getPharmacyById(chats.get(i).getOtherPharmacyId());
        						chats.get(i).setOtherPharmacyName(p.getPharmacyName());
        					}
        					
        				}
            	    	
            	    	for(int i=0;i<chats.size();i++)
        				{
            	    	service.setRead(chats.get(i).getChatId(),login);
        				}
            	    	Gson gson = new Gson();
	    				// convert your list to json
	    				String chatList = gson.toJson(chats);
	    				
	    				PrintWriter pw = response.getWriter();

	    				pw.print(chatList);
	            	    
	            		
					} catch (Exception e) {
						PrintWriter pw = response.getWriter();
	    				pw.print("empty");
					}
					
	           
		}
		
		
		if(uri.endsWith("saveMessages.chat"))
		{
		
	            	
	            	try {
	            		lService.setLastActive(login);
	            	    String message = request.getParameter("message");
	            	    int chatId = Integer.parseInt(request.getParameter("chatId"));
	            	    
	            	    service.saveMessage(login,chatId,message);
	            	    
	            	    List<Message> messages = new ArrayList<Message>();
            	    	messages = service.getMessagesByChatId(chatId);
            	    	
            	    	service.setRead(chatId,login);
            	    	Gson gson = new Gson();
	    				// convert your list to json
	    				String messageList = gson.toJson(messages);
	    				
	    				PrintWriter pw = response.getWriter();

	    				pw.print(messageList);
	            	    
	            		
					} catch (Exception e) {
						PrintWriter pw = response.getWriter();
	    				pw.print("empty");
					}
					
	           
		}
		
		
		if(uri.endsWith("checkUserActive.chat"))
		{
		
	            	
	            	try {
	            		
	            	    int chatId = Integer.parseInt(request.getParameter("chatId"));
	            	    
	            	    Chat chat = service.getChatById(chatId);
	            	    
	            	    Timestamp time = null;
	            	    
	            	    if(login.getType()==1)
	    	            {
	            	    	if(chat.getPatientId()!=0)
							{
								Patient p = lService.getPatientById(chat.getPatientId());
								time = p.getLastActive();
							}
	            	    	
	            	    	if(chat.getPharmacyId()!=0)
							{
								Pharmacy p = lService.getPharmacyById(chat.getPharmacyId());
								time = p.getLastActive();
							}
	            	    	
	            	    	if(chat.getDoctorId()== login.getId())
							{
	            	    		if(chat.getOtherDoctorId()!=0)
	    						{
	    							Doctor d = lService.getDoctorById(chat.getOtherDoctorId());
	    							time = d.getLastActive();
	    						}
							}
	            	    	
	            	    	if(chat.getOtherDoctorId()== login.getId())
							{
	            	    		if(chat.getDoctorId()!=0)
	    						{
	    							Doctor d = lService.getDoctorById(chat.getDoctorId());
	    							time = d.getLastActive();
	    						}
							}
	            	    	
	    	            }
	            	    
	            	    if(login.getType()==2)
	    	            {
	            	    	
	            	    	
	            	    	if(chat.getPharmacyId()!=0)
							{
								Pharmacy p = lService.getPharmacyById(chat.getPharmacyId());
								time = p.getLastActive();
							}
	            	    	
	            	    	if(chat.getDoctorId()!=0)
    						{
    							Doctor d = lService.getDoctorById(chat.getDoctorId());
    							time = d.getLastActive();
    						}
	            	    	 	
	    	            }
	            	    
	            	    
	            	    if(login.getType()==3)
	    	            {
	            	    	if(chat.getPatientId()!=0)
							{
								Patient p = lService.getPatientById(chat.getPatientId());
								time = p.getLastActive();
							}
	            	    	
	            	    	if(chat.getDoctorId()!=0)
    						{
    							Doctor d = lService.getDoctorById(chat.getDoctorId());
    							time = d.getLastActive();
    						}
	            	    	
	            	    	if(chat.getPharmacyId()== login.getId())
							{
	            	    		if(chat.getOtherPharmacyId()!=0)
	    						{
	            	    			Pharmacy p = lService.getPharmacyById(chat.getOtherPharmacyId());
	    							time = p.getLastActive();
	    						}
							}
	            	    	
	            	    	if(chat.getOtherPharmacyId()== login.getId())
							{
	            	    		if(chat.getPharmacyId()!=0)
	    						{
	            	    			Pharmacy p = lService.getPharmacyById(chat.getPharmacyId());
	    							time = p.getLastActive();
	    						}
							}
	            	    	
	    	            }
	            	    
        
            	    	Gson gson = new Gson();
	    				// convert your list to json
	    				String messageList = gson.toJson(time);
	    				
	    				PrintWriter pw = response.getWriter();

	    				pw.print(messageList);
	            	    
	            		
					} catch (Exception e) {
						PrintWriter pw = response.getWriter();
	    				pw.print("empty");
					}
					
	           
		}
		
		
		
		
		
		
	}

}
