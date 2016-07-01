package com.vinod.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.vinod.model.Appointment;
import com.vinod.model.Doctor;
import com.vinod.model.Login;
import com.vinod.model.Medicine;
import com.vinod.model.Order;
import com.vinod.model.Patient;
import com.vinod.model.Pharmacy;
import com.vinod.model.Prescription;
import com.vinod.model.Vaccination;
import com.vinod.model.Vaccine;
import com.vinod.service.LoginService;
import com.vinod.service.PatientService;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("*.home")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeController() {
		super();
		
	}

	final static Logger logger = Logger.getLogger(HomeController.class);
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

		String uri=request.getRequestURI();
		logger.info(uri);
		String target = "home.jsp";
		try {
			if(null == request.getSession().getAttribute("login")){  
				request.setAttribute("error", "Please Login To Continue.");
				target="home.jsp";
				request.getRequestDispatcher(target).forward(request, response); 
				return;
				
				}
			
			Login login=(Login) request.getSession().getAttribute("login");
			int i=login.getId();
		}
		catch (Exception e) {
			request.setAttribute("error", "Please Login To Continue.");
			target="home.jsp";
			request.getRequestDispatcher(target).forward(request, response);
			return;
			//response.sendRedirect(target);
		}
		if(uri.endsWith("doctor.home"))
		{


			try {
				Login login = (Login) request.getSession().getAttribute("login");
				int id = login.getId();
				LoginService service = new LoginService();
				Doctor doctor = service.getDoctorById(id);
				List<Patient> patients = service.getAllPatients(id);
				List<Pharmacy> pharmacies = service.getAllPharmacies(id);
				service.setLastActive(login);
				
				List<Appointment> appointments = service.getThisDayAppointment(id);
				
				for(int i=0;i<appointments.size();i++)
				{
					if(appointments.get(i).getPatientId()!=0)
					{
						Patient patient = service.getPatientById(appointments.get(i).getPatientId());
						appointments.get(i).setPatientName(patient.getFirstName()+" "+patient.getLastName());
					}
					
					String st = appointments.get(i).getStart();
					String lt = appointments.get(i).getEnd();
					
					 String[] res1 = st.split("T");
					 String[] res2 = lt.split("T");
					 //String day = res1[0];
					
					 String[] st1 = res1[1].split(":");
					 String startTime= st1[0]+":"+st1[1];
					
					 String[] lt1 = res2[1].split(":");
					 String endTime= lt1[0]+":"+lt1[1];
					 
					 appointments.get(i).setStartTime(startTime);
					 appointments.get(i).setEndTime(endTime);
				}
				List<Doctor> doctors = service.getAllDoctorsForDoctor(id);
				request.setAttribute("doctors", doctors);
				request.setAttribute("appointments", appointments);
				
				request.setAttribute("patients", patients);
				request.setAttribute("pharmacies", pharmacies);
				request.setAttribute("doctor", doctor);
				
				target="doctorhome.jsp";
			} catch (Exception e1) {

				request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
				target="home.jsp";

			}

			request.getRequestDispatcher(target).forward(request, response);

		}

		PatientService pService = new PatientService();
		if(uri.endsWith("patient.home"))
		{

			try {
				Login login = (Login) request.getSession().getAttribute("login");
				int id = login.getId();

				LoginService service = new LoginService();
				Patient patient;
				patient = service.getPatientById(id);
				
				List<Prescription> prescriptions;
				prescriptions = pService.getAllPrescriptionByPatientId(id);
				
				for(int i=0;i<prescriptions.size();i++)
				{
					Prescription prescription = prescriptions.get(i);
					if(prescription.getS1()!=0)
					{
					prescription.setsName1(pService.getSymptomNameById(prescription.getS1()));
					}
					if(prescription.getS2()!=0)
					{
					prescription.setsName2(pService.getSymptomNameById(prescription.getS2()));
					}
					if(prescription.getS3()!=0)
					{
					prescription.setsName3(pService.getSymptomNameById(prescription.getS3()));
					}
					if(prescription.getS4()!=0)
					{
					prescription.setsName4(pService.getSymptomNameById(prescription.getS4()));
					}
					if(prescription.getS5()!=0)
					{
					prescription.setsName5(pService.getSymptomNameById(prescription.getS5()));
					}
					
					if(prescription.getD1()!=0)
					{
					prescription.setdName1(pService.getDiseaseNameById(prescription.getD1()));
					}
					if(prescription.getD2()!=0)
					{
					prescription.setdName2(pService.getDiseaseNameById(prescription.getD2()));
					}
					if(prescription.getD3()!=0)
					{
					prescription.setdName3(pService.getDiseaseNameById(prescription.getD3()));
					}
					if(prescription.getD4()!=0)
					{
					prescription.setdName4(pService.getDiseaseNameById(prescription.getD4()));
					}
					if(prescription.getD5()!=0)
					{
					prescription.setdName5(pService.getDiseaseNameById(prescription.getD5()));
					}
					
					List<Medicine> medicines = new ArrayList<Medicine>();
					
					int sort = 1;
					if(prescription.getM1()!=0)
					{
						medicines.add(pService.getPrescribedMedicineById(prescription,prescription.getM1(),sort));
					}
					if(prescription.getM2()!=0)
					{
						medicines.add(pService.getPrescribedMedicineById(prescription,prescription.getM2(),sort));
					}
					if(prescription.getM3()!=0)
					{
						medicines.add(pService.getPrescribedMedicineById(prescription,prescription.getM3(),sort));
					}
					if(prescription.getM4()!=0)
					{
						medicines.add(pService.getPrescribedMedicineById(prescription,prescription.getM4(),sort));
					}
					if(prescription.getM5()!=0)
					{
						medicines.add(pService.getPrescribedMedicineById(prescription,prescription.getM5(),sort));
					}
					if(prescription.getDoctorId()>0)
					{
						Doctor patient1 = service.getDoctorById(prescription.getDoctorId());
						prescription.setDoctorName(patient1.getFirstName()+" "+patient1.getLastName());
					}
					
					prescription.setMedicines(medicines);
					
				}
				service.setLastActive(login);
				request.setAttribute("prescriptions", prescriptions);
				List<Doctor> doctors = service.getAllDoctors(id);
				List<Pharmacy> pharmacies = service.getAllPharmaciesBypatientId(id);

				Calendar calendar1 = Calendar.getInstance();
				Calendar calendar2 = Calendar.getInstance(); 
				calendar1.setTime(new Date());
				calendar2.setTime(patient.getDob());
				 
				
				long miliSecondForDate1 = calendar1.getTimeInMillis();
				long miliSecondForDate2 = calendar2.getTimeInMillis();
				
				long diffInMilis = miliSecondForDate1 - miliSecondForDate2;
			
				long age = diffInMilis / (24 * 60 * 60 * 1000);

				request.setAttribute("age", age);
				
				Vaccine vaccine = service.getVaccine(id);
			    request.setAttribute("vaccine", vaccine); 
				
				
			    List<Vaccination> vaccinations = service.getVaccination(id);
				request.setAttribute("vaccinations", vaccinations);
			    
				request.setAttribute("doctors", doctors);
				request.setAttribute("pharmacies", pharmacies);
				request.setAttribute("patient", patient);
				
				target="patienthome.jsp";
			} catch (Exception e) {
				System.err.println(e.getMessage());
				request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
				target="home.jsp";
			}
			request.getRequestDispatcher(target).forward(request, response);


		}
		
		if(uri.endsWith("patientBeginner.home"))
		{

			try {
				Login login = (Login) request.getSession().getAttribute("login");
				int id = login.getId();

				LoginService service = new LoginService();
				Patient patient;
				patient = service.getPatientById(id);
				
				List<Prescription> prescriptions;
				prescriptions = pService.getAllPrescriptionByPatientId(id);
				
				for(int i=0;i<prescriptions.size();i++)
				{
					Prescription prescription = prescriptions.get(i);
					if(prescription.getS1()!=0)
					{
					prescription.setsName1(pService.getSymptomNameById(prescription.getS1()));
					}
					if(prescription.getS2()!=0)
					{
					prescription.setsName2(pService.getSymptomNameById(prescription.getS2()));
					}
					if(prescription.getS3()!=0)
					{
					prescription.setsName3(pService.getSymptomNameById(prescription.getS3()));
					}
					if(prescription.getS4()!=0)
					{
					prescription.setsName4(pService.getSymptomNameById(prescription.getS4()));
					}
					if(prescription.getS5()!=0)
					{
					prescription.setsName5(pService.getSymptomNameById(prescription.getS5()));
					}
					
					if(prescription.getD1()!=0)
					{
					prescription.setdName1(pService.getDiseaseNameById(prescription.getD1()));
					}
					if(prescription.getD2()!=0)
					{
					prescription.setdName2(pService.getDiseaseNameById(prescription.getD2()));
					}
					if(prescription.getD3()!=0)
					{
					prescription.setdName3(pService.getDiseaseNameById(prescription.getD3()));
					}
					if(prescription.getD4()!=0)
					{
					prescription.setdName4(pService.getDiseaseNameById(prescription.getD4()));
					}
					if(prescription.getD5()!=0)
					{
					prescription.setdName5(pService.getDiseaseNameById(prescription.getD5()));
					}
					
					List<Medicine> medicines = new ArrayList<Medicine>();
					
					int sort = 1;
					if(prescription.getM1()!=0)
					{
						medicines.add(pService.getPrescribedMedicineById(prescription,prescription.getM1(),sort));
					}
					if(prescription.getM2()!=0)
					{
						medicines.add(pService.getPrescribedMedicineById(prescription,prescription.getM2(),sort));
					}
					if(prescription.getM3()!=0)
					{
						medicines.add(pService.getPrescribedMedicineById(prescription,prescription.getM3(),sort));
					}
					if(prescription.getM4()!=0)
					{
						medicines.add(pService.getPrescribedMedicineById(prescription,prescription.getM4(),sort));
					}
					if(prescription.getM5()!=0)
					{
						medicines.add(pService.getPrescribedMedicineById(prescription,prescription.getM5(),sort));
					}
					if(prescription.getDoctorId()>0)
					{
						Doctor patient1 = service.getDoctorById(prescription.getDoctorId());
						prescription.setDoctorName(patient1.getFirstName()+" "+patient1.getLastName());
					}
					
					prescription.setMedicines(medicines);
					
				}
				service.setLastActive(login);
				request.setAttribute("prescriptions", prescriptions);
				List<Doctor> doctors = service.getAllDoctors(id);
				List<Pharmacy> pharmacies = service.getAllPharmaciesBypatientId(id);

				Calendar calendar1 = Calendar.getInstance();
				Calendar calendar2 = Calendar.getInstance(); 
				calendar1.setTime(new Date());
				calendar2.setTime(patient.getDob());
				 
				
				long miliSecondForDate1 = calendar1.getTimeInMillis();
				long miliSecondForDate2 = calendar2.getTimeInMillis();
				
				long diffInMilis = miliSecondForDate1 - miliSecondForDate2;
			
				long age = diffInMilis / (24 * 60 * 60 * 1000);

				request.setAttribute("age", age);
				
				Vaccine vaccine = service.getVaccine(id);
			    request.setAttribute("vaccine", vaccine); 
				
				
			    List<Vaccination> vaccinations = service.getVaccination(id);
				request.setAttribute("vaccinations", vaccinations);
			    
				request.setAttribute("doctors", doctors);
				request.setAttribute("pharmacies", pharmacies);
				request.setAttribute("patient", patient);
				
				request.setAttribute("beginner", 1);
				request.setAttribute("insert", 1);
				
				target="patienthome.jsp";
			} catch (Exception e) {
				System.err.println(e.getMessage());
				request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
				target="home.jsp";
			}
			request.getRequestDispatcher(target).forward(request, response);


		}


		if(uri.endsWith("pharmacy.home"))
		{

			try {
				Login login = (Login) request.getSession().getAttribute("login");
				int id = login.getId();

				LoginService service = new LoginService();
				Pharmacy pharmacy;
				pharmacy = service.getPharmacyById(id);
				List<Pharmacy> pharmacies = service.getAllPharmaciesForPharmacy(id);
				List<Order> orders = service.getAllNewOrders(id);
				for(int i=0;i<orders.size();i++)
				{
					if(orders.get(i).getDoctorId()!=0)
					{
						Doctor doctor = service.getDoctorById(orders.get(i).getDoctorId());
						orders.get(i).setDoctorName(doctor.getFirstName()+" "+doctor.getLastName());
					}

					if(orders.get(i).getPatientId()!=0)
					{
						Patient patient = service.getPatientById(orders.get(i).getPatientId());
						orders.get(i).setPatientName(patient.getFirstName()+" "+patient.getLastName());
					}

					if(orders.get(i).getPharmacyId()!=0)
					{
						Pharmacy doctor = service.getPharmacyById(orders.get(i).getPharmacyId());
						orders.get(i).setPharmacyName(doctor.getPharmacyName());
					}
				}
				service.setLastActive(login);
				request.setAttribute("orders", orders);
				request.setAttribute("pharmacies", pharmacies);
				request.setAttribute("pharmacy", pharmacy);
				target="pharmacyhome.jsp";
			} catch (Exception e1) {
				logger.error(Level.SEVERE,e1);
				request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
				target="home.jsp";
			}
			request.getRequestDispatcher(target).forward(request, response);

		}
		
		
		

	}

}
