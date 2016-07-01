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
import com.vinod.model.Appointment;
import com.vinod.model.Doctor;
import com.vinod.model.Event;
import com.vinod.model.Log;
import com.vinod.model.Login;
import com.vinod.model.Notification;
import com.vinod.model.Patient;
import com.vinod.model.Pharmacy;
import com.vinod.model.Symptom;
import com.vinod.service.AppointmentService;
import com.vinod.service.DoctorService;
import com.vinod.service.LoginService;

/**
 * Servlet implementation class AppointmentController
 */
@WebServlet("*.appointment")
public class AppointmentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AppointmentController() {
		super();
		
	}

	final static Logger logger = Logger.getLogger(AppointmentController.class);
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
		String target = "home.jsp";
		DoctorService dService= new DoctorService();
		LoginService lService = new LoginService();
		String uri=request.getRequestURI();
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
		AppointmentService service = new AppointmentService();
		
		
		if(uri.endsWith("patientDoctor.appointment"))
		{
			try {

				if(request.getParameter("operation").equalsIgnoreCase("insert"))
				{
					Login login = (Login) request.getSession().getAttribute("login");
					int pId = login.getId();
					int dId = Integer.parseInt(request.getParameter("doctorid"));
					//String description = request.getParameter("description");
					String title = request.getParameter("title");
					String start = request.getParameter("start");
					String end = request.getParameter("end");

					String day = request.getParameter("day");
					
					String st = start;
					String lt = end;
					
					start = day+"T"+start+":00.000Z";
					end = day+"T"+end+":00.000Z";
					
					String symptom = "";
					for(int i=1;i<=5;i++)
					{
						if(!request.getParameter("s"+i).isEmpty())
						{
							symptom = symptom+request.getParameter("s"+i)+",";
						}
					}
					Appointment appointment = new Appointment();
					appointment.setDoctorId(dId);
					appointment.setPatientId(pId);
					appointment.setStart(start);
					appointment.setEnd(end);
					appointment.setTitle(title);
					appointment.setDescription("");
					appointment.setSymptom(symptom);

					int eventId = service.saveAppointment(appointment);

					Patient patient = lService.getPatientById(pId);
					
					
					String contentlog = "Requested for Appointment with Doctor Id="+dId+"and eventId="+eventId;
					Log log = new Log(pId,2,contentlog );
					lService.saveLog(log);
					
					
					String contentlog1 = "Received Requested for Appointment from Patient Id="+pId+"and eventId="+eventId;
					Log log1 = new Log(dId,1,contentlog1 );
					lService.saveLog(log1);
					
					String content = "<a href='appointment.doctor?eId="+eventId+"'>"+patient.getFirstName()+"&nbsp; "+patient.getLastName()+" Has Made Request for Appointment</a>";
					Notification notification = new Notification(0, pId, content, dId, 0,1);
					notification.setImage(patient.getImage());
					lService.saveNotification(notification);
					lService.setLastActive(login);
					
					Doctor doctor = lService.getDoctorById(dId);
					String message = "Hi " +patient.getFirstName()+" "+patient.getLastName()+", You have requested for appointment on "+day+" from "+st+" to "+lt+" with Dr."+doctor.getFirstName()+" "+doctor.getLastName()+". Please note your appointment ID:"+eventId+" and wait for doctor's confirmation."; 
				    
					lService.sendMessage(patient.getMobile() ,message);
				    
				    String message1 = "Hi Dr." +doctor.getFirstName()+" "+doctor.getLastName()+", You have received appointment request on "+day+" from "+st+" to "+lt+" from "+patient.getFirstName()+" "+patient.getLastName()+". Please note your appointment ID:"+eventId+" and provide confirmation on status of appointment to patient."; 
				    lService.sendMessage(doctor.getMobile() ,message1);
					
					
					request.setAttribute("doctor", doctor);
					
					request.setAttribute("patient", patient);
					request.setAttribute("insert", 1);
					request.setAttribute("event", eventId);
					target="patientDoctorPlanner.jsp";
				}
				if(request.getParameter("operation").equalsIgnoreCase("update"))
				{
					Login login = (Login) request.getSession().getAttribute("login");
					int pId = login.getId();
					int dId = Integer.parseInt(request.getParameter("doctorid"));
					//String description = request.getParameter("description");
					String title = request.getParameter("title");
					String start = request.getParameter("start");
					String end = request.getParameter("end");
					String day = request.getParameter("day");
					String st = start;
					String lt = end;
					start = day+"T"+start+":00.000Z";
					end = day+"T"+end+":00.000Z";
					
                    int eventId = Integer.parseInt(request.getParameter("eventId"));
					String symptom = "";
					for(int i=1;i<=5;i++)
					{
						if(!request.getParameter("s"+i).isEmpty())
						{
							symptom = symptom+request.getParameter("s"+i)+",";
						}
					}
					Appointment appointment = new Appointment();
					appointment.setDoctorId(dId);
					appointment.setPatientId(pId);
					appointment.setStart(start);
					appointment.setEnd(end);
					appointment.setTitle(title);
					appointment.setDescription("");
					appointment.setSymptom(symptom);
                    appointment.setId(eventId);
					service.updateAppointment(appointment);

					Patient patient = lService.getPatientById(pId);
					lService.setLastActive(login);
					String content = "<a href='appointment.doctor?eId="+eventId+"'>"+patient.getFirstName()+"&nbsp; "+patient.getLastName()+" Has Updated Details of Requested Appointment</a>";
					Notification notification = new Notification(0, pId, content, dId, 0,1);
					notification.setImage(patient.getImage());
					lService.saveNotification(notification);
					
					Doctor doctor = lService.getDoctorById(dId);
					String message = "Hi " +patient.getFirstName()+" "+patient.getLastName()+", You have updated  appointment on "+day+" from "+st+" to "+lt+" with Dr."+doctor.getFirstName()+" "+doctor.getLastName()+" for appointment ID:"+eventId+". Please wait for doctor's confirmation."; 
				    lService.sendMessage(patient.getMobile() ,message);
				    
				    String message1 = "Hi Dr." +doctor.getFirstName()+" "+doctor.getLastName()+", You have received updated appointment request on "+day+" from "+st+" to "+lt+" from "+patient.getFirstName()+" "+patient.getLastName()+" for appointment ID:"+eventId+" . Please provide confirmation on status of appointment to patient."; 
				    lService.sendMessage(doctor.getMobile() ,message1);
					
					
					request.setAttribute("doctor",doctor);
					
					request.setAttribute("patient", patient);
					request.setAttribute("insert", 1);
					request.setAttribute("event", eventId);
					target="patientDoctorPlanner.jsp";
				}
				
				if(request.getParameter("operation").equalsIgnoreCase("delete"))
				{
					Login login = (Login) request.getSession().getAttribute("login");
					int pId = login.getId();
					int dId = Integer.parseInt(request.getParameter("doctorid"));
					int eventId = Integer.parseInt(request.getParameter("eventId"));
					
					service.deleteAppointment(eventId);
					
					Patient patient = lService.getPatientById(pId);
					
					String content = "<a href='appointment.doctor'>"+patient.getFirstName()+"&nbsp; "+patient.getLastName()+" Has Deleted Appointment</a>";
					Notification notification = new Notification(0, pId, content, dId, 0,1);
					notification.setImage(patient.getImage());
					lService.saveNotification(notification);
					lService.setLastActive(login);
					
					String contentlog = "Deleted Appointment with Event Id="+eventId;
					Log log = new Log(pId,2,contentlog );
					lService.saveLog(log);
					
					
					String contentlog1 = "Appointment got Cancelled by Patient with eventId"+eventId;
					Log log1 = new Log(dId,1,contentlog1 );
					lService.saveLog(log1);
					
					
					request.setAttribute("doctor", lService.getDoctorById(dId));
					
					request.setAttribute("patient", patient);
					request.setAttribute("insert", 1);
					target="patientDoctorPlanner.jsp";
					
				}
			}  catch (Exception e) {
				try {
					logger.error(Level.SEVERE,e);
					target = lService.setErrorControl(request,target,login1);
					request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
				} catch (Exception e1) {
					logger.error(Level.SEVERE,e);
					
					request.setAttribute("error", "Please Login To Continue.");
					target="home.jsp";
				}

			}
			request.getRequestDispatcher(target).forward(request, response);

		}

		if(uri.endsWith("getPatientDoctorEvents.appointment"))
		{
			try {
				Login login = (Login) request.getSession().getAttribute("login");
				int pId = login.getId();
				int dId = Integer.parseInt(request.getParameter("doctorId"));
				lService.setLastActive(login);
				List<Event> events = service.getPatientDoctorEvent(pId,dId);
				// create a new Gson instance
				Gson gson = new Gson();
				// convert your list to json
				String eventList = gson.toJson(events);
				
				PrintWriter pw = response.getWriter();

				pw.print(eventList);


			}  catch (Exception e) {
				logger.error(Level.SEVERE,e);
			}
		}
		
		
		if(uri.endsWith("patientDoctorUpdate.appointment"))
		{
			try {
				Login login = (Login) request.getSession().getAttribute("login");
				int id = login.getId();
				lService.setLastActive(login);
				String description = request.getParameter("title");
				String title = request.getParameter("title");
				String start = request.getParameter("start");
				String end = request.getParameter("end");
				String day = request.getParameter("day");
				
				start = day+"T"+start+":00.000Z";
				end = day+"T"+end+":00.000Z";
				
                int eventId = Integer.parseInt(request.getParameter("eventId"));
				String symptom = "";
				for(int i=1;i<=5;i++)
				{
					if(!request.getParameter("s"+i).isEmpty())
					{
						symptom = symptom+request.getParameter("s"+i)+",";
					}
				}
				Appointment appointment = new Appointment();
				appointment.setStart(start);
				appointment.setEnd(end);
				appointment.setTitle(title);
				appointment.setDescription(description);
				appointment.setSymptom(symptom);
                appointment.setId(eventId);
				service.updateAppointment(appointment);

				Patient patient = lService.getPatientById(id);
				
				Appointment appointment1 = service.getEventById(eventId);
				
				String content = "<a href='appointment.doctor?eId="+eventId+"'> "+patient.getFirstName()+"&nbsp; "+patient.getLastName()+" Has Updated Details of Requested Appointment</a>";
				Notification notification = new Notification(0, id, content, appointment1.getDoctorId(), 0,1);
				notification.setImage(patient.getImage());
				lService.saveNotification(notification);
				
				
				request.setAttribute("patient", patient);
				request.setAttribute("insert", 1);
				request.setAttribute("event", eventId);
				target="patientPlanner.jsp";
			}  catch (Exception e) {
				try {
					logger.error(Level.SEVERE,e);
					target = lService.setErrorControl(request,target,login1);
					request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
				} catch (Exception e1) {
					
					request.setAttribute("error", "Please Login To Continue.");
					target="home.jsp";
				}

			}
			request.getRequestDispatcher(target).forward(request, response);
		}
		
		if(uri.endsWith("save.appointment"))
		{
			try {
				
				Login login = (Login) request.getSession().getAttribute("login");
				
				//String description = request.getParameter("description");
				String title = request.getParameter("title");
				String start = request.getParameter("start");
				String end = request.getParameter("end");
				String day = request.getParameter("day");
				
				start = day+"T"+start+":00.000Z";
				end = day+"T"+end+":00.000Z";
				lService.setLastActive(login);
				if(login.getType()==1)
				{
					int id = login.getId();
					Appointment appointment = new Appointment();
					appointment.setDoctorId(id);
					appointment.setPatientId(0);
					appointment.setPharmacyId(0);
					appointment.setStart(start);
					appointment.setEnd(end);
					appointment.setTitle(title);
					appointment.setDescription("");
					appointment.setSymptom("");
					service.saveAppointment(appointment);
	
					Doctor doctor = lService.getDoctorById(id);
					request.setAttribute("doctor", doctor);
					
					target ="doctorPlanner.jsp";
				}
				if(login.getType()==2)
				{
					int id = login.getId();
					Appointment appointment = new Appointment();
					int doctorId = Integer.parseInt(request.getParameter("doctorid"));

					appointment.setDoctorId(doctorId);
					appointment.setPatientId(id);
					appointment.setPharmacyId(0);
					appointment.setStart(start);
					appointment.setEnd(end);
					appointment.setTitle(title);
					appointment.setDescription("");
					String symptom = "";
					for(int i=1;i<=5;i++)
					{
						if(!request.getParameter("s"+i).isEmpty())
						{
							symptom = symptom+request.getParameter("s"+i)+",";
						}
					}
					appointment.setSymptom(symptom);
					int eventId = service.saveAppointment(appointment);


					Patient patient = lService.getPatientById(id);

					if(doctorId>0)
					{
						String content = "<a href='appointment.doctor?eId="+eventId+"'>"+patient.getFirstName()+"&nbsp; "+patient.getLastName()+" Has Made Request for Appointment</a>";
						Notification notification = new Notification(0, id, content, doctorId, 0,1);
						notification.setImage(patient.getImage());
						lService.saveNotification(notification);
						
						String contentlog = "Requested for Appointment with Doctor Id="+doctorId+"and eventId="+eventId;
						Log log = new Log(id,2,contentlog );
						lService.saveLog(log);
						
						
						String contentlog1 = "Received Requested for Appointment from Patient Id="+id+"and eventId="+eventId;
						Log log1 = new Log(doctorId,1,contentlog1 );
						lService.saveLog(log1);
						
					}
					List<Doctor> doctors = lService.getAllDoctors(id);
					request.setAttribute("doctors", doctors);
					request.setAttribute("patient", patient);
					request.setAttribute("event", eventId);
					target="patientPlanner.jsp";
				}
				if(login.getType()==3)
				{
					int id = login.getId();
					Appointment appointment = new Appointment();
					appointment.setDoctorId(0);
					appointment.setPatientId(0);
					appointment.setPharmacyId(id);
					appointment.setStart(start);
					appointment.setEnd(end);
					appointment.setTitle(title);
					appointment.setDescription("");
					appointment.setSymptom("");
					service.saveAppointment(appointment);
					
					Pharmacy pharmacy = lService.getPharmacyById(id);
					request.setAttribute("pharmacy", pharmacy);
					target = "pharmacyPlanner.jsp";
				}
				request.setAttribute("insert", 1);
				
			}  catch (Exception e) {
				try {
					logger.error(Level.SEVERE,e);
					target = lService.setErrorControl(request,target,login1);
					request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
				} catch (Exception e1) {
					
					request.setAttribute("error", "Please Login To Continue.");
					target="home.jsp";
				}

			}
			request.getRequestDispatcher(target).forward(request, response);
		}
		
		if(uri.endsWith("update.appointment"))
		{
			try {
				
				Login login = (Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				//String description = request.getParameter("description");
				String title = request.getParameter("title");
				String start = request.getParameter("start");
				String end = request.getParameter("end");
				String day = request.getParameter("day");
				
				start = day+"T"+start+":00.000Z";
				end = day+"T"+end+":00.000Z";
				int eventId = Integer.parseInt(request.getParameter("eventId"));
				if(login.getType()==1)
				{
					int id = login.getId();
					
					Appointment appointment = new Appointment();
					
					appointment.setDoctorId(id);
					appointment.setPatientId(0);
					appointment.setPharmacyId(0);
					appointment.setStart(start);
					appointment.setEnd(end);
					appointment.setTitle(title);
					appointment.setDescription("");
					appointment.setId(eventId);
					appointment.setSymptom("");
					service.updateAppointment(appointment);
	
					Doctor doctor = lService.getDoctorById(id);
					request.setAttribute("doctor", doctor);
					request.setAttribute("event", eventId);
					target ="doctorPlanner.jsp";
				}
				if(login.getType()==2)
				{
					int id = login.getId();
					Appointment appointment = new Appointment();
					appointment.setDoctorId(0);
					appointment.setPatientId(id);
					appointment.setPharmacyId(0);
					appointment.setStart(start);
					appointment.setEnd(end);
					appointment.setTitle(title);
					appointment.setDescription("");
					appointment.setId(eventId);
					appointment.setSymptom("");
					service.updateAppointment(appointment);

					Patient patient = lService.getPatientById(id);
					request.setAttribute("event", eventId);
					request.setAttribute("patient", patient);
					target="patientPlanner.jsp";
				}
				if(login.getType()==3)
				{
					int id = login.getId();
					System.out.println(id);
					Appointment appointment = new Appointment();
					appointment.setDoctorId(0);
					appointment.setPatientId(0);
					appointment.setPharmacyId(id);
					appointment.setStart(start);
					appointment.setEnd(end);
					appointment.setTitle(title);
					appointment.setDescription("");
					appointment.setId(eventId);
					appointment.setSymptom("");
					service.updateAppointment(appointment);
					
					Pharmacy pharmacy = lService.getPharmacyById(id);
					request.setAttribute("pharmacy", pharmacy);
					target = "pharmacyPlanner.jsp";
				}
				request.setAttribute("insert", 1);
			}  catch (Exception e) {
				try {
					target = lService.setErrorControl(request,target,login1);
					request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
				} catch (Exception e1) {
					logger.error(Level.SEVERE,e);
					request.setAttribute("error", "Please Login To Continue.");
					target="home.jsp";
				}

			}
			request.getRequestDispatcher(target).forward(request, response);
		}
		
		
		if(uri.endsWith("delete.appointment"))
		{
			try {
				
				Login login = (Login) request.getSession().getAttribute("login");
				
				
				lService.setLastActive(login);
				if(login.getType()==1)
				{
					int id = login.getId();
                    int eventId = Integer.parseInt(request.getParameter("eventId"));
					
					service.deleteAppointment(eventId);
	
					Doctor doctor = lService.getDoctorById(id);
					request.setAttribute("doctor", doctor);
					target ="doctorPlanner.jsp";
				}
				if(login.getType()==2)
				{
					int id = login.getId();
                    int eventId = Integer.parseInt(request.getParameter("eventId"));
					
					service.deleteAppointment(eventId);

					Patient patient = lService.getPatientById(id);
					
					request.setAttribute("patient", patient);
					target="patientPlanner.jsp";
				}
				if(login.getType()==3)
				{
					int id = login.getId();
                    int eventId = Integer.parseInt(request.getParameter("eventId"));
					
					service.deleteAppointment(eventId);
					
					Pharmacy pharmacy = lService.getPharmacyById(id);
					request.setAttribute("pharmacy", pharmacy);
					target = "pharmacyPlanner.jsp";
				}
				request.setAttribute("insert", 1);
			}  catch (Exception e) {
				try {
					target = lService.setErrorControl(request,target,login1);
					request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
				} catch (Exception e1) {
					logger.error(Level.SEVERE,e);
					request.setAttribute("error", "Please Login To Continue.");
					target="home.jsp";
				}

			}
			request.getRequestDispatcher(target).forward(request, response);
		}
		
		
		if(uri.endsWith("deletePatientDoctor.appointment"))
		{
			try {
				
				Login login = (Login) request.getSession().getAttribute("login");
				
				
				lService.setLastActive(login);
				if(login.getType()==1)
				{
					int id = login.getId();
                    int eventId = Integer.parseInt(request.getParameter("eventId"));
                    int pId = Integer.parseInt(request.getParameter("id"));
					service.deleteAppointment(eventId);
	
					Doctor doctor = lService.getDoctorById(id);
					
					String content = "<a href='appointment.patient'> Doctor :"+doctor.getFirstName()+"&nbsp; "+doctor.getLastName()+" Has Deleted Request for Appointment</a>";
					Notification notification = new Notification(0, id, content, pId, 0,2);
					notification.setImage(doctor.getImage());
					lService.saveNotification(notification);
					
					
					String contentlog = "Deleted Appointment with Event Id="+eventId;
					Log log = new Log(id,1,contentlog );
					lService.saveLog(log);
					
					
					String contentlog1 = "Appointment got Cancelled by Doctor with eventId"+eventId;
					Log log1 = new Log(pId,2,contentlog1 );
					lService.saveLog(log1);
					
					request.setAttribute("doctor", doctor);
					target ="doctorPlanner.jsp";
				}
				if(login.getType()==2)
				{
					int id = login.getId();
                    int eventId = Integer.parseInt(request.getParameter("eventId"));
                    int dId = Integer.parseInt(request.getParameter("id"));
					
					service.deleteAppointment(eventId);

					Patient patient = lService.getPatientById(id);
					String content = "<a href='appointment.doctor'>Patient: "+patient.getFirstName()+"&nbsp; "+patient.getLastName()+" Has Deleted Request for Appointment</a>";
					Notification notification = new Notification(0, id, content, dId, 0,1);
					notification.setImage(patient.getImage());
					lService.saveNotification(notification);
					
					String contentlog = "Deleted Appointment with Event Id="+eventId;
					Log log = new Log(id,2,contentlog );
					lService.saveLog(log);
					
					
					String contentlog1 = "Appointment got Cancelled by Patient with eventId"+eventId;
					Log log1 = new Log(dId,1,contentlog1 );
					lService.saveLog(log1);
					
					
					request.setAttribute("patient", patient);
					target="patientPlanner.jsp";
				}
				request.setAttribute("insert", 1);
				
			} catch (Exception e) {
				try {
					target = lService.setErrorControl(request,target,login1);
					request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
				} catch (Exception e1) {
					logger.error(Level.SEVERE,e);
					request.setAttribute("error", "Please Login To Continue.");
					target="home.jsp";
				}

			}
			request.getRequestDispatcher(target).forward(request, response);
		}
		
		if(uri.endsWith("getEvents.appointment"))
		{
			try {
				Login login = (Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				List<Event> events = service.getEvents(login);
				// create a new Gson instance
				Gson gson = new Gson();
				// convert your list to json
				String eventList = gson.toJson(events);
				
				PrintWriter pw = response.getWriter();

				pw.print(eventList);


			}  catch (Exception e) {
				logger.error(Level.SEVERE,e);
			}
		}
		
		
		if(uri.endsWith("accept.appointment"))
		{
			try {
				
				Login login = (Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id = login.getId();
				Doctor doctor = lService.getDoctorById(id);
				int eventId = Integer.parseInt(request.getParameter("eventId"));
				
				service.acceptAppointment(eventId);
				
				Appointment appointment = service.getEventById(eventId);
				
				
				String content = "<a href='appointment.patient?eId="+eventId+"'> Doctor:"+doctor.getFirstName()+"&nbsp; "+doctor.getLastName()+" Has Confirmed Your Request for Appointment</a>";
				Notification notification = new Notification(0, appointment.getDoctorId(), content, appointment.getPatientId(), 0,2);
				notification.setImage(doctor.getImage());
				lService.saveNotification(notification);
				
				
				String contentlog = "Accepted Appointment with Event Id="+eventId;
				Log log = new Log(id,1,contentlog );
				lService.saveLog(log);
				
				int pId = (int) appointment.getPatientId();
				String contentlog1 = "Appointment got Accepted by Doctor with eventId"+eventId;
				Log log1 = new Log(pId,2,contentlog1 );
				lService.saveLog(log1);
				
				PrintWriter pw = response.getWriter();

				
				
				pw.print("Accepted");


			}  catch (Exception e) {
				logger.error(Level.SEVERE,e);
			}
		}
		

		if(uri.endsWith("getEvent.appointment"))
		{
			try {
				int eventId = Integer.parseInt(request.getParameter("eventId"));

				Appointment appointment = service.getEventById(eventId);

				if(appointment.getDoctorId()!=0)
				{
					Doctor doctor1 = lService.getDoctorById(appointment.getDoctorId());
					appointment.setDoctorName(doctor1.getFirstName()+" "+doctor1.getLastName());
				}

				if(appointment.getPatientId()!=0)
				{
					Patient patient = lService.getPatientById(appointment.getPatientId());
					appointment.setPatientName(patient.getFirstName()+" "+patient.getLastName());
				}

				if(appointment.getPharmacyId()!=0)
				{
					Pharmacy doctor1 = lService.getPharmacyById(appointment.getPharmacyId());
					appointment.setPharmacyName(doctor1.getPharmacyName());
				}
				Gson gson = new Gson();
				String eventList = gson.toJson(appointment);
				
				PrintWriter pw = response.getWriter();
				pw.print(eventList);


			}  catch (Exception e) {
				logger.error(Level.SEVERE,e);
			}
		}

	}

}
