package com.vinod.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.vinod.model.Doctor;
import com.vinod.model.Login;
import com.vinod.model.Medicine;
import com.vinod.model.Patient;
import com.vinod.model.Pharmacy;


import com.vinod.service.LoginService;
import com.vinod.service.SearchService;

/**
 * Servlet implementation class SearchController
 */
@WebServlet("*.search")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchController() {
		super();
		
	}
	
	final static Logger logger = Logger.getLogger(SearchController.class);

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
			HttpServletResponse response) throws ServletException, IOException{
		SearchService service = new SearchService();
		String uri=request.getRequestURI();
		String target = "home.jsp";
        LoginService lService = new LoginService();
        logger.info(uri);
		if(uri.endsWith("search.search"))
		{
			int type = Integer.parseInt(request.getParameter("q"));
			String text = request.getParameter("p");

			Login login=(Login) request.getSession().getAttribute("login");
            int id=login.getId();
			if(login.getType()==1)
			{
				if(type==1){

					
					try {
						lService.setLastActive(login);
						List<Doctor> doctors = service.getAllDoctorsForSearch(text,id);
						PrintWriter pw = response.getWriter();
						response.setContentType("text/html");
						
                        String msg="";
						for(int i=0;i<doctors.size()&&i<=10;i++)
						{
							if(doctors.get(i).getConnect()==2)
							{
								msg = msg+"<div style='width:400px;'><span><img alt=300x200 src="+doctors.get(i).getImage()+"  width=60px; height=60px; /></span> <span style='width:300px;margin-left:10px;'><a href=doctorViewDoctor.profile?id="+doctors.get(i).getId()+">"+doctors.get(i).getFirstName()+" "+doctors.get(i).getLastName()+"</a></span></div>";
							}
							if(doctors.get(i).getConnect()==0)
							{
								msg = msg+"<div style='width:400px;'><span><img alt=300x200 src="+doctors.get(i).getImage()+"  width=60px; height=60px; /></span> <span style='width:300px;margin-left:10px;'><a href=doctorViewDoctor.profile?id="+doctors.get(i).getId()+">"+doctors.get(i).getFirstName()+" "+doctors.get(i).getLastName()+"</a></span><span id="+doctors.get(i).getId()+" class='col-md-4 column' style='padding-top:17px;'><input class='btn btn-info btn-block btn-sm' type=button onclick=addDoctor("+doctors.get(i).getId()+"); style=; value='Connect'></span></div>";
							}
							if(doctors.get(i).getConnect()==1)
							{
								msg = msg+"<div style='width:400px;'><span><img alt=300x200 src="+doctors.get(i).getImage()+"  width=60px; height=60px; /></span> <span style='width:300px;margin-left:10px;'><a href=doctorViewDoctor.profile?id="+doctors.get(i).getId()+">"+doctors.get(i).getFirstName()+" "+doctors.get(i).getLastName()+"</a></span><span id="+doctors.get(i).getId()+" class='col-md-4 column' style='padding-top:17px;'>Request has been sent</span></div>";
							}
						}
						pw.print(msg);
					}catch (Exception e) {
						logger.error(Level.SEVERE,e);
						request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
						target="home.jsp";
					}

				}


				if(type==2)
				{
					
					try {
						List<Patient> patients = service.getAllPatientsForDoctorSearch(text,id);
						lService.setLastActive(login);
						PrintWriter pw = response.getWriter();
						response.setContentType("text/html");
						String msg = "";

						for(int i=0;i<patients.size()&&i<=10;i++)
						{
							if(patients.get(i).getConnect()==0)
							{
							msg = msg+"<div class='panel-body'><div class='col-sm-3 col-md-3 column'><img alt=300x200 src="+patients.get(i).getImage()+"  width=60px; height=60px; /></div> <div class='col-sm-5 col-md-5 column' style='padding-top:30px;'><a href=doctorViewPatient.profile?id="+patients.get(i).getId()+">"+patients.get(i).getFirstName()+" "+patients.get(i).getLastName()+"</a></div><div id="+patients.get(i).getId()+" ><div class='col-md-4 column' style='padding-top:17px;'><input class='btn btn-info btn-block btn-sm' type=button onclick=add("+patients.get(i).getId()+"); style=; value='Connect'></div></div></div>";
							}
							if(patients.get(i).getConnect()==2)
							{
								msg = msg+"<div class='panel-body'><div class='col-sm-3 col-md-3 column'><img alt=300x200 src="+patients.get(i).getImage()+"  width=60px; height=60px; /></div> <div class='col-sm-5 col-md-5 column' style='padding-top:30px;'><a href=doctorViewPatient.profile?id="+patients.get(i).getId()+">"+patients.get(i).getFirstName()+" "+patients.get(i).getLastName()+"</a></div></div>";
							}
							if(patients.get(i).getConnect()==1)
							{
							msg = msg+"<div class='panel-body'><div class='col-sm-3 col-md-3 column'><img alt=300x200 src="+patients.get(i).getImage()+"  width=60px; height=60px; /></div> <div class='col-sm-5 col-md-5 column' style='padding-top:30px;'><a href=doctorViewPatient.profile?id="+patients.get(i).getId()+">"+patients.get(i).getFirstName()+" "+patients.get(i).getLastName()+"</a></div><div id="+patients.get(i).getId()+" ><div class='col-md-4 column' style='padding-top:17px;'>Request has been sent</div></div></div>";
							}
							}
						pw.print(msg);
					} catch (Exception e) {
						logger.error(Level.SEVERE,e);
						request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
						target="home.jsp";
					}

				}

				if(type==3)
				{
					try {
						List<Pharmacy> pharmacies = service.getAllPharmaciesForDoctorSearch(text,id);
						lService.setLastActive(login);
						PrintWriter pw = response.getWriter();
						response.setContentType("text/html");
						String msg = "";

						for(int i=0;i<pharmacies.size()&&i<=10;i++)
						{
							if(pharmacies.get(i).getConnect()==0)
							{
							msg = msg+"<div class='panel-body'><div class='col-sm-3 col-md-3 column'><img alt=300x200 src="+pharmacies.get(i).getImage()+"  width=60px; height=60px; /></div> <div class='col-sm-5 col-md-5 column' style='padding-top:30px;'><a href=doctorViewPharmacy.profile?id="+pharmacies.get(i).getId()+">"+pharmacies.get(i).getPharmacyName()+"</a></div><div id="+pharmacies.get(i).getId()+" style='color:green;richness: inherit;'><div class='col-md-4 column' style='padding-top:17px;'><input class='btn btn-info btn-block btn-sm' type=button onclick=addp("+pharmacies.get(i).getId()+"); style=; value='Connect'></div></div></div>";
							}
							else
							{
								msg = msg+"<div class='panel-body'><div class='col-sm-3 col-md-3 column'><img alt=300x200 src="+pharmacies.get(i).getImage()+"  width=60px; height=60px; /></div> <div class='col-sm-5 col-md-5 column' style='padding-top:30px;'><a href=doctorViewPharmacy.profile?id="+pharmacies.get(i).getId()+">"+pharmacies.get(i).getPharmacyName()+"</a></div></div>";
							}
						}
						pw.print(msg);
					} catch (Exception e) {
						logger.error(Level.SEVERE,e);
						request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
						target="home.jsp";
					}
				}
			}

			if(login.getType()==2)
			{
				if(type==1){
					
					try {
						List<Doctor> doctors = service.getAllDoctorsForPatientSearch(text,id);
						lService.setLastActive(login);
						PrintWriter pw = response.getWriter();
						
						String msg = "";

						for(int i=0;i<doctors.size()&&i<=10;i++)
						{
							if(doctors.get(i).getConnect()==0)
							{
								msg = msg+"<div class='panel-body'><div class='col-sm-3 col-md-3 column'><img alt=300x200 src="+doctors.get(i).getImage()+"  width=60px; height=60px; /></div> <div class='col-sm-5 col-md-5 column' style='padding-top:30px;'><a href=patientViewDoctor.profile?id="+doctors.get(i).getId()+">"+doctors.get(i).getFirstName()+" "+doctors.get(i).getLastName()+"</a></div><div id="+doctors.get(i).getId()+" style='color:green;richness: inherit;'><div class='col-md-4 column' style='padding-top:17px;'><input class='btn btn-info btn-block btn-sm' type=button onclick=add("+doctors.get(i).getId()+"); style=';' value='Connect'></div></div></div>";
							}
							if(doctors.get(i).getConnect()==2)
							{
								msg = msg+"<div class='panel-body'><div class='col-sm-3 col-md-3 column'><img alt=300x200 src="+doctors.get(i).getImage()+"  width=60px; height=60px; /></div> <div class='col-sm-5 col-md-5 column' style='padding-top:30px;'><a href=patientViewDoctor.profile?id="+doctors.get(i).getId()+">"+doctors.get(i).getFirstName()+" "+doctors.get(i).getLastName()+"</a></div></div>";
							}
							if(doctors.get(i).getConnect()==1)
							{
								msg = msg+"<div class='panel-body'><div class='col-sm-3 col-md-3 column'><img alt=300x200 src="+doctors.get(i).getImage()+"  width=60px; height=60px; /></div> <div class='col-sm-5 col-md-5 column' style='padding-top:30px;'><a href=patientViewDoctor.profile?id="+doctors.get(i).getId()+">"+doctors.get(i).getFirstName()+" "+doctors.get(i).getLastName()+"</a></div><div id="+doctors.get(i).getId()+" style='color:green;richness: inherit;'><div class='col-md-4 column' style='padding-top:17px;'>Request has been sent</div></div></div>";
							}
						}
						
						pw.print(msg);
					} catch (Exception e) {
						logger.error(Level.SEVERE,e);
						request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
						target="home.jsp";
					}
				}


				if(type==2)
				{
					

					try {
						List<Patient> patients = service.getAllPatientsForSearch(text,id);
						lService.setLastActive(login);
						PrintWriter pw = response.getWriter();
						response.setContentType("text/html");
						String msg = "";

						for(int i=0;i<patients.size()&&i<=10;i++)
						{
							msg = msg+"<div class='panel-body'><div class='col-sm-3 col-md-3 column'><img alt=300x200 src="+patients.get(i).getImage()+"  width=60px; height=60px; /></div> <div class='col-sm-5 col-md-5 column' style='padding-top:30px;'><a href=patientViewPatient.profile?id="+patients.get(i).getId()+">"+patients.get(i).getFirstName()+" "+patients.get(i).getLastName()+"</a></div></div>";
						}
						pw.print(msg);
					} catch (Exception e) {
						logger.error(Level.SEVERE,e);
						request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
						target="home.jsp";
					}

				}

				if(type==3)
				{
					
					
					try {
						List<Pharmacy> pharmacies = service.getAllPharmaciesForPatientSearch(text,id);
						lService.setLastActive(login);
						PrintWriter pw = response.getWriter();
						response.setContentType("text/html");
						String msg = "";

						for(int i=0;i<pharmacies.size()&&i<=10;i++)
						{
							if(pharmacies.get(i).getConnect()==0)
							{
							    msg = msg+"<div class='panel-body'><div class='col-sm-3 col-md-3 column'><img alt=300x200 src="+pharmacies.get(i).getImage()+"  width=60px; height=60px; /></div> <div class='col-sm-5 col-md-5 column' style='padding-top:30px;'><a href=patientViewPharmacy.profile?id="+pharmacies.get(i).getId()+">"+pharmacies.get(i).getPharmacyName()+"</a></div><div id="+pharmacies.get(i).getId()+" style='color:green;richness: inherit;'><div class='col-md-4 column' style='padding-top:17px;'><input class='btn btn-info btn-block btn-sm' type=button onclick=addp("+pharmacies.get(i).getId()+"); style=; value='Connect'></div></div></div>";
							}
							else
							{
								msg = msg+"<div class='panel-body'><div class='col-sm-3 col-md-3 column'><img alt=300x200 src="+pharmacies.get(i).getImage()+"  width=60px; height=60px; /></div> <div class='col-sm-5 col-md-5 column' style='padding-top:30px;'><a href=patientViewPharmacy.profile?id="+pharmacies.get(i).getId()+">"+pharmacies.get(i).getPharmacyName()+"</a></div></div>";
							}

						}
						pw.print(msg);
					} catch (Exception e) {
						logger.error(Level.SEVERE,e);
						request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
						target="home.jsp";
					}
				}
			}

			if(login.getType()==3)
			{
				if(type==1){
					
					try {
						List<Doctor> doctors = service.getAllDoctorsForSearch(text);
						lService.setLastActive(login);
						PrintWriter pw = response.getWriter();
						response.setContentType("text/html");
						String msg = "";

						for(int i=0;i<doctors.size()&&i<=10;i++)
						{
							msg = msg+"<div class='panel-body'><div class='col-sm-3 col-md-3 column'><img alt=300x200 src="+doctors.get(i).getImage()+"  width=60px; height=60px; /></div> <div class='col-sm-5 col-md-5 column' style='padding-top:30px;'><a href=pharmacyViewDoctor.profile?id="+doctors.get(i).getId()+">"+doctors.get(i).getFirstName()+" "+doctors.get(i).getLastName()+"</a></div></div>";
						}
						pw.print(msg);
					} catch (Exception e) {
						logger.error(Level.SEVERE,e);
						request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
						target="home.jsp";
					}
				}


				if(type==2)
				{
					
					try {
						List<Patient> patients = service.getAllPatientsForSearch(text);
						lService.setLastActive(login);
						PrintWriter pw = response.getWriter();
						response.setContentType("text/html");
						String msg = "";

						for(int i=0;i<patients.size()&&i<=10;i++)
						{
							msg = msg+"<div class='panel-body'><div class='col-sm-3 col-md-3 column'><img alt=300x200 src="+patients.get(i).getImage()+"  width=60px; height=60px; /></div> <div class='col-sm-5 col-md-5 column' style='padding-top:30px;'><a href=pharmacyViewPatient.profile?id="+patients.get(i).getId()+">"+patients.get(i).getFirstName()+" "+patients.get(i).getLastName()+"</a></div></div>";
						}
						pw.print(msg);
					} catch (Exception e) {
						logger.error(Level.SEVERE,e);
						request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
						target="home.jsp";
					}


				}

				if(type==3)
				{
					

					try {
						List<Pharmacy> pharmacies = service.getAllPharmaciesForSearch(text,id);
						lService.setLastActive(login);
						PrintWriter pw = response.getWriter();
						response.setContentType("text/html");
						String msg = "";

						for(int i=0;i<pharmacies.size()&&i<=10;i++)
						{
							if(pharmacies.get(i).getConnect()==0)
							{
							msg = msg+"<div class='panel-body'><div class='col-sm-3 col-md-3 column'><img alt=300x200 src="+pharmacies.get(i).getImage()+"  width=60px; height=60px; /></div> <div class='col-sm-5 col-md-5 column' style='padding-top:30px;'><a href=pharmacyViewPharmacy.profile?id="+pharmacies.get(i).getId()+">"+pharmacies.get(i).getPharmacyName()+"</a></div><div id="+pharmacies.get(i).getId()+" style='color:green;richness: inherit;'><div class='col-md-4 column' style='padding-top:17px;'><input class='btn btn-info btn-block btn-sm' type=button onclick=addp("+pharmacies.get(i).getId()+") style=; value=Connect></div></div></div>";
							}
							else
							{
								msg = msg+"<div class='panel-body'><div class='col-sm-3 col-md-3 column'><img alt=300x200 src="+pharmacies.get(i).getImage()+"  width=60px; height=60px; /></div> <div class='col-sm-5 col-md-5 column' style='padding-top:30px;'><a href=pharmacyViewPharmacy.profile?id="+pharmacies.get(i).getId()+">"+pharmacies.get(i).getPharmacyName()+"</a></div></div>";
							}

						}
						pw.print(msg);
					}catch (Exception e) {
						logger.error(Level.SEVERE,e);
						request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
						target="home.jsp";
					}
				}
			}
		}

		if(uri.endsWith("add.search"))
		{
            int id = Integer.parseInt(request.getParameter("q"));
            Login login=(Login) request.getSession().getAttribute("login");
            
            if(login.getType()==1)
            {
            	
            	try {
            		int i = service.requestSentByDoctor(id,login.getId());
            		lService.setLastActive(login);
            		if(i==0){
            		Patient p = lService.getPatientById(id);
            		Doctor d = lService.getDoctorById(login.getId());
            		String message = "You have received an Invite from Dr."+d.getFirstName()+" "+d.getLastName()+". Kindly login to HC to respond";
				     
				    lService.sendMessage(p.getMobile() ,message);
            		}
            		
					PrintWriter pw = response.getWriter();
					response.setContentType("text/html");
					String msg = "Connection Request Sent";
					pw.print(msg);
					
				}catch (Exception e) {
					logger.error(Level.SEVERE,e);
					request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
					target="home.jsp";
				}
				
            }
            
            if(login.getType()==2)
            {
            	
            	try {
            		int i = service.requestSentByPatient(id,login.getId());
            		lService.setLastActive(login);
            		if(i==0){
            		Patient p = lService.getPatientById(login.getId());
            		Doctor d = lService.getDoctorById(id);
            		String message = "You have received an Invite from "+p.getFirstName()+" "+p.getLastName()+". Kindly login to HC to respond";
				     
				    lService.sendMessage(d.getMobile() ,message);
            		}
					PrintWriter pw = response.getWriter();
					response.setContentType("text/html");
					String msg = "Connection Request Sent";
					pw.print(msg);
				}catch (Exception e) {
					logger.error(Level.SEVERE,e);
					request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
					target="home.jsp";
				}
				
            }
		}
		
		
		if(uri.endsWith("addDoctor.search"))
		{
            int id = Integer.parseInt(request.getParameter("q"));
            Login login=(Login) request.getSession().getAttribute("login");
            
            if(login.getType()==1)
            {
            	
            	try {
            		int i = service.addDoctorDoctorConnection(login.getId(), id);
            		lService.setLastActive(login);
            		if(i==0){
            			Doctor p = lService.getDoctorById(login.getId());
                		Doctor d = lService.getDoctorById(id);
                		String message = "You have received an Invite from Dr."+p.getFirstName()+" "+p.getLastName()+". Kindly login to HC to respond";
    				     
    				    lService.sendMessage(d.getMobile() ,message);
            		}
            		
            		
					PrintWriter pw = response.getWriter();
					response.setContentType("text/html");
					String msg = "Connection Request Sent";
					pw.print(msg);
					
				}catch (Exception e) {
					logger.error(Level.SEVERE,e);
					request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
					target="home.jsp";
				}
				
            }
		}
		
		if(uri.endsWith("removeDoctor.search"))
		{
            int id = Integer.parseInt(request.getParameter("q"));
            Login login=(Login) request.getSession().getAttribute("login");
            
            if(login.getType()==1)
            {
            	
            	try {
            		int i = service.deleteDoctorDoctorConnection(id,login.getId());
            		lService.setLastActive(login);
					PrintWriter pw = response.getWriter();
					response.setContentType("text/html");
					String msg = "Deleted";
					pw.print(msg);
					
				}catch (Exception e) {
					logger.error(Level.SEVERE,e);
					request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
					target="home.jsp";
				}
				
            }
            
            
		}	
		
		if(uri.endsWith("remove.search"))
		{
            int id = Integer.parseInt(request.getParameter("q"));
            Login login=(Login) request.getSession().getAttribute("login");
            
            if(login.getType()==1)
            {
            	
            	try {
            		int i = service.deletePatientDoctorConnection(id,login.getId());
            		lService.setLastActive(login);
					PrintWriter pw = response.getWriter();
					response.setContentType("text/html");
					String msg = "Deleted";
					pw.print(msg);
					
				}catch (Exception e) {
					logger.error(Level.SEVERE,e);
					request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
					target="home.jsp";
				}
				
            }
            
            if(login.getType()==2)
            {
            	
            	try {
            		int i = service.deletePatientDoctorConnection(login.getId(),id);
            		lService.setLastActive(login);
					PrintWriter pw = response.getWriter();
					response.setContentType("text/html");
					String msg = "Deleted";
					pw.print(msg);
				}catch (Exception e) {
					logger.error(Level.SEVERE,e);
					request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
					target="home.jsp";
				}
				
            }
		}


		if(uri.endsWith("addp.search"))
		{
			int id = Integer.parseInt(request.getParameter("q"));
			Login login=(Login) request.getSession().getAttribute("login");
			
			 if(login.getType()==1)
	            {
	            	
	            	try {
	            		int i = service.doctorPharmacy(id,login.getId());
	            		lService.setLastActive(login);
						PrintWriter pw = response.getWriter();
						response.setContentType("text/html");
						String msg = "Successfully Added";
						pw.print(msg);
					}catch (Exception e) {
						logger.error(Level.SEVERE,e);
						request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
						target="home.jsp";
					}
					
	            }
	            
	            if(login.getType()==2)
	            {
	            	
	            	try {
	            		int i = service.patientPharmacy(id,login.getId());
	            		lService.setLastActive(login);
						PrintWriter pw = response.getWriter();
						response.setContentType("text/html");
						String msg = "Successfully Added";
						pw.print(msg);
					}catch (Exception e) {
						logger.error(Level.SEVERE,e);
						request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
						target="home.jsp";
					}
					
	            }
	            
	            if(login.getType()==3)
	            {

	            	try {
	            		if(id!=login.getId())
	            		{
	            			int i = service.pharmacyPharmacy(id,login.getId());
	            		}
	            		lService.setLastActive(login);
	            		PrintWriter pw = response.getWriter();
	            		response.setContentType("text/html");
	            		String msg = "Successfully Added";
	            		pw.print(msg);
	            	}catch (Exception e) {
	            		logger.error(Level.SEVERE,e);
	            		request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
	            		target="home.jsp";
	            	}

	            }
		}
		
		if(uri.endsWith("removep.search"))
		{
			int id = Integer.parseInt(request.getParameter("q"));
			Login login=(Login) request.getSession().getAttribute("login");
			
			 if(login.getType()==1)
	            {
	            	
	            	try {
	            		int i = service.deleteDoctorPharmacy(login.getId(),id);
	            		lService.setLastActive(login);
						PrintWriter pw = response.getWriter();
						response.setContentType("text/html");
						String msg = "Successfully Deleted";
						pw.print(msg);
					}catch (Exception e) {
						logger.error(Level.SEVERE,e);
						request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
						target="home.jsp";
					}
					
	            }
	            
	            if(login.getType()==2)
	            {
	            	
	            	try {
	            		int i = service.deletePatientPharmacy(login.getId(),id);
	            		lService.setLastActive(login);
						PrintWriter pw = response.getWriter();
						response.setContentType("text/html");
						String msg = "Successfully Deleted";
						pw.print(msg);
					}catch (Exception e) {
						logger.error(Level.SEVERE,e);
						request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
						target="home.jsp";
					}
					
	            }
	            
	            if(login.getType()==3)
	            {

	            	try {
	            		if(id!=login.getId())
	            		{
	            			int i = service.deletePharmacyPharmacy(login.getId(),id);
	            		}
	            		lService.setLastActive(login);
	            		PrintWriter pw = response.getWriter();
	            		response.setContentType("text/html");
	            		String msg = "Successfully Deleted";
	            		pw.print(msg);
	            	}catch (Exception e) {
	            		logger.error(Level.SEVERE,e);
	            		request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
	            		target="home.jsp";
	            	}

	            }
		}
		
		if(uri.endsWith("accept.search"))
		{
			int id = Integer.parseInt(request.getParameter("q"));
			Login login=(Login) request.getSession().getAttribute("login");
			
			 if(login.getType()==1)
	            {
	            	
	            	try {
	            		int i = service.acceptByDoctor(id,login.getId());
	            		lService.setLastActive(login);
						PrintWriter pw = response.getWriter();
						response.setContentType("text/html");
						String msg = "Successfully Connected";
						pw.print(msg);
					}catch (Exception e) {
						logger.error(Level.SEVERE,e);
						request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
						target="home.jsp";
					}
					
	            }
	            
	            if(login.getType()==2)
	            {
	            	
	            	try {
	            		int i = service.acceptByPatient(id,login.getId());
	            		lService.setLastActive(login);
						PrintWriter pw = response.getWriter();
						response.setContentType("text/html");
						String msg = "Successfully Connected";
						pw.print(msg);
					}catch (Exception e) {
						logger.error(Level.SEVERE,e);
						request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
						target="home.jsp";
					}
					
	            }
		}
		
		if(uri.endsWith("decline.search"))
		{
			int id = Integer.parseInt(request.getParameter("q"));
			Login login=(Login) request.getSession().getAttribute("login");
			
			 if(login.getType()==1)
	            {
	            	
	            	try {
	            		int i = service.declineByDoctor(id,login.getId());
	            		lService.setLastActive(login);
						PrintWriter pw = response.getWriter();
						response.setContentType("text/html");
						String msg = "Succesfully declined";
						pw.print(msg);
					} catch (Exception e) {
						logger.error(Level.SEVERE,e);
						request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
						target="home.jsp";
					}
					
	            }
	            
	            if(login.getType()==2)
	            {
	            	
	            	try {
	            		int i = service.declineByPatient(id,login.getId());
	            		lService.setLastActive(login);
						PrintWriter pw = response.getWriter();
						response.setContentType("text/html");
						String msg = "Succesfully declined";
						pw.print(msg);
					}catch (Exception e) {
						logger.error(Level.SEVERE,e);
						request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
						target="home.jsp";
					}
					
	            }
		}
		
		
		if(uri.endsWith("acceptDoctor.search"))
		{
			int id = Integer.parseInt(request.getParameter("q"));
			Login login=(Login) request.getSession().getAttribute("login");
			
			 if(login.getType()==1)
	            {
	            	
	            	try {
	            		int i = service.acceptDoctor(id,login.getId());
	            		lService.setLastActive(login);
						PrintWriter pw = response.getWriter();
						response.setContentType("text/html");
						String msg = "Successfully Connected";
						pw.print(msg);
					}catch (Exception e) {
						logger.error(Level.SEVERE,e);
						request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
						target="home.jsp";
					}
					
	            }
	            
		}
		
		if(uri.endsWith("declineDoctor.search"))
		{
			int id = Integer.parseInt(request.getParameter("q"));
			Login login=(Login) request.getSession().getAttribute("login");
			
			 if(login.getType()==1)
	            {
	            	
	            	try {
	            		int i = service.deleteDoctorDoctorConnection(id,login.getId());
	            		lService.setLastActive(login);
						PrintWriter pw = response.getWriter();
						response.setContentType("text/html");
						String msg = "Succesfully Declined";
						pw.print(msg);
					} catch (Exception e) {
						logger.error(Level.SEVERE,e);
						request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
						target="home.jsp";
					}
					
	            }
	            
	           
		}
		
		if(uri.endsWith("searchMedicine.search"))
		{
			
			
			Login login=(Login) request.getSession().getAttribute("login");

			try {
				String text = request.getParameter("text");

				List<Medicine> pharmacyMedicines = service.getSearchedMedicines(login , text);
				lService.setLastActive(login);
				Gson gson = new Gson();
				// convert your list to json
				String medicinesList = gson.toJson(pharmacyMedicines);
               
				PrintWriter pw = response.getWriter();

				pw.print(medicinesList);	
			} catch (Exception e) {
				logger.error(Level.SEVERE,e);
				request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
				target="home.jsp";
			}




		}
		
		if(uri.endsWith("addMedicine.search"))
		{
			
			
			Login login=(Login) request.getSession().getAttribute("login");

			try {
				int mId = Integer.parseInt(request.getParameter("mId"));

				Medicine m = service.getMedicineById(mId);
				service.addMedicine(login , m.getName());
				lService.setLastActive(login);
				
               
				PrintWriter pw = response.getWriter();

				pw.print("1");	
			} catch (Exception e) {
				logger.error(Level.SEVERE,e);
				request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
				target="home.jsp";
			}




		}
		
		if(uri.endsWith("updateMedicine.search"))
		{
			
			
			Login login=(Login) request.getSession().getAttribute("login");

			try {
				String name = request.getParameter("mId");
				double cost = Double.parseDouble(request.getParameter("cost"));
				service.updateMedicine(login , name ,cost);
				lService.setLastActive(login);
				
               
				PrintWriter pw = response.getWriter();

				pw.print("1");	
			} catch (Exception e) {
				logger.error(Level.SEVERE,e);
				request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
				target="home.jsp";
			}




		}
		
		if(uri.endsWith("deleteMedicine.search"))
		{
			
			
			Login login=(Login) request.getSession().getAttribute("login");

			try {
				String name = request.getParameter("medicineName");
			
				service.deleteMedicine(login , name);
				lService.setLastActive(login);
				
               
				PrintWriter pw = response.getWriter();

				pw.print("1");	
			} catch (Exception e) {
				logger.error(Level.SEVERE,e);
				request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
				target="home.jsp";
			}




		}
		
	}
}
