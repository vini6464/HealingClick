package com.vinod.service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;





import com.vinod.comparator.DoctorComparator;
import com.vinod.comparator.PatientComparator;
import com.vinod.comparator.PharmacyComparator;
import com.vinod.comparator.ReviewComparator;
import com.vinod.dao.LoginDao;
import com.vinod.exception.DaoException;
import com.vinod.model.Appointment;
import com.vinod.model.Doctor;
import com.vinod.model.Log;
import com.vinod.model.Login;
import com.vinod.model.Medicine;
import com.vinod.model.Notification;
import com.vinod.model.Order;
import com.vinod.model.Patient;
import com.vinod.model.Pharmacy;
import com.vinod.model.Prescription;
import com.vinod.model.Review;
import com.vinod.model.User;
import com.vinod.model.Vaccination;
import com.vinod.model.Vaccine;

public class LoginService {
	LoginDao dao = new LoginDao();

	public int validateLoginDoctor(String userName, String password) throws DaoException {
		int i = dao.validateLoginDoctor(userName,password);
		return i;
	}

	public List<Patient> getAllPatients(int id) throws DaoException {
		List<Patient> patients = dao.getAllPatients(id);
		Collections.sort(patients, new PatientComparator());
		return patients;
	}

	public List<Pharmacy> getAllPharmacies(int id) throws DaoException {
		List<Pharmacy> pharmacies = dao.getAllPharmacies(id);
		Collections.sort(pharmacies, new PharmacyComparator());
		return pharmacies;
	}

	public Doctor getDoctorById(long id) throws DaoException {
		Doctor doctor = dao.getDoctorById(id);
		return doctor;
	}

	public int validateLoginPatient(String userName, String password) throws DaoException {
		int i = dao.validateLoginPatient(userName,password);
		return i;
	}

	public Patient getPatientById(long id) throws DaoException {
		Patient patient = dao.getPatientById(id);
		return patient;
	}

	public List<Doctor> getAllDoctors(int id) throws DaoException {
		List<Doctor> doctors = dao.getAllDoctorsByPatientId(id);
		Collections.sort(doctors, new DoctorComparator());
		return doctors;
	}

	public List<Pharmacy> getAllPharmaciesBypatientId(int id) throws DaoException {
		List<Pharmacy> pharmacies = dao.getAllPharmaciesByPatientId(id);
		Collections.sort(pharmacies, new PharmacyComparator());
		return pharmacies;
	}

	public int validateLoginPharmacy(String userName, String password) throws DaoException {
		int i = dao.validateLoginPharmacy(userName,password);
		return i;
	}

	public Pharmacy getPharmacyById(long id) throws DaoException {
		Pharmacy pharmacy = dao.getPharmacyById(id);
		return pharmacy;
	}

	public List<User> getPatientRequestNotification(int id) throws DaoException {
		List<User> patients = dao.getPatientRequestNotification(id);
		return patients;
	}

	public List<Doctor> getDoctorRequestNotification(int id) throws DaoException {
		List<Doctor> doctors = dao.getDoctorRequestNotification(id);
		Collections.sort(doctors, new DoctorComparator());
		return doctors;
	}

	public int connectedOrNot(int pId, int dId) throws DaoException {
		int i = dao.connectedOrNot(pId,dId);
		return i;
	}

	public List<Order> getAllNewOrders(int id) throws DaoException {
		List<Order> orders = dao.getAllNewOrders(id);
		//Collections.sort(orders, new OrderComparator());
		return orders;
	}

	public List<Pharmacy> getAllPharmaciesForPharmacy(int id) throws DaoException {
		List<Pharmacy> pharmacies = dao.getAllPharmaciesForPharmacy(id);
		Collections.sort(pharmacies, new PharmacyComparator());
		return pharmacies;
	}

	public void updateProfileImage(String imagePath, Login login) throws DaoException {
		dao.updateProfileImage(imagePath,login);
		
	}

	public int doctorPharmacyConnectedOrNot(int id, int pId) throws DaoException {
		int connect = dao.doctorPharmacyConnectedOrNot(id,pId);
		return connect;
	}

	public int patientPharmacyConnectedOrNot(int id, int pId) throws DaoException {
		int connect = dao.patientPharmacyConnectedOrNot(id,pId);
		return connect;
	}

	public int connectedPharmacyToPharmacyOrNot(int pId, int id) throws DaoException {
		int connect = dao.connectedPharmacyToPharmacyOrNot(pId,id);
		return connect;
	}

	public void setLastActive(Login login) throws DaoException {
		dao.setLastActive(login);
		
	}

	
	public int checkPassword(Login login, String pwd) throws DaoException {
		int i=dao.checkPassword(login,pwd);
		return i;
		
	}

	public void updatePassword(Login login, String newpwd) throws DaoException {
		dao.updatePassword(login,newpwd);
		
	}

	public Vaccine getVaccine(int id) throws DaoException {
		Vaccine i = dao.checkVaccine(id);
		if(i.getPatientId()==0)
		{
			dao.setVaccine(id);
			i = new Vaccine(0,0,0,0,0,0,0,0,0,0,0);
		}
		return i;
	}

	public void saveNotification(Notification notification) throws DaoException {
		dao.saveNotification(notification);
		
	}

	public String setErrorControl(HttpServletRequest request, String target,
			Login login) throws DaoException {
		target = "home.jsp";
		PatientService pService = new PatientService();
		int id = login.getId();
		if(login.getType()==1)
		{
			setLastActive(login);
			Doctor doctor =getDoctorById(id);
			List<Patient> patients = getAllPatients(id);
			List<Pharmacy> pharmacies = getAllPharmacies(id);
			request.setAttribute("patients", patients);
			request.setAttribute("pharmacies", pharmacies);
			request.setAttribute("doctor", doctor);
			
			List<Appointment> appointments = getThisDayAppointment(id);
			
			for(int i=0;i<appointments.size();i++)
			{
				if(appointments.get(i).getPatientId()!=0)
				{
					Patient patient = getPatientById(appointments.get(i).getPatientId());
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
			List<Doctor> doctors = getAllDoctorsForDoctor(id);
			request.setAttribute("doctors", doctors);
			request.setAttribute("appointments", appointments);
			
			target="doctorhome.jsp";
		}
		if(login.getType()==2)
		{
			setLastActive(login);
			Patient patient = getPatientById(id);

			Calendar calendar1 = Calendar.getInstance();
			Calendar calendar2 = Calendar.getInstance(); 
			calendar1.setTime(new Date());
			calendar2.setTime(patient.getDob());


			long miliSecondForDate1 = calendar1.getTimeInMillis();
			long miliSecondForDate2 = calendar2.getTimeInMillis();

			long diffInMilis = miliSecondForDate1 - miliSecondForDate2;

			long age = diffInMilis / (24 * 60 * 60 * 1000);

			request.setAttribute("age", age);

			Vaccine vaccine = getVaccine(id);
			request.setAttribute("vaccine", vaccine); 

			List<Vaccination> vaccinations = getVaccination(id);
			request.setAttribute("vaccinations", vaccinations);
			
			List<Doctor> doctors = getAllDoctors(id);
			List<Pharmacy> pharmacies = getAllPharmaciesBypatientId(id);



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
					Doctor patient1 = getDoctorById(prescription.getDoctorId());
					prescription.setDoctorName(patient1.getFirstName()+" "+patient1.getLastName());
				}
				prescription.setMedicines(medicines);

			}
			
			
			
			request.setAttribute("prescriptions", prescriptions);
			request.setAttribute("doctors", doctors);
			request.setAttribute("pharmacies", pharmacies);
			request.setAttribute("patient", patient);
			target="patienthome.jsp";
		}
		if(login.getType()==3)
		{
			setLastActive(login);
			Pharmacy pharmacy = getPharmacyById(id);
			List<Pharmacy> pharmacies = getAllPharmaciesForPharmacy(id);
			List<Order> orders = getAllNewOrders(id);
			for(int i=0;i<orders.size();i++)
			{
				if(orders.get(i).getDoctorId()!=0)
				{
					Doctor doctor = getDoctorById(orders.get(i).getDoctorId());
					orders.get(i).setDoctorName(doctor.getFirstName()+" "+doctor.getLastName());
				}

				if(orders.get(i).getPatientId()!=0)
				{
					Patient patient = getPatientById(orders.get(i).getPatientId());
					orders.get(i).setPatientName(patient.getFirstName()+" "+patient.getLastName());
				}

				if(orders.get(i).getPharmacyId()!=0)
				{
					Pharmacy doctor = getPharmacyById(orders.get(i).getPharmacyId());
					orders.get(i).setPharmacyName(doctor.getPharmacyName());
				}
			}
			request.setAttribute("pharmacies", pharmacies);
			request.setAttribute("orders", orders);
			request.setAttribute("pharmacy", pharmacy);
			target="pharmacyhome.jsp";
		}
		return target;
	}

	public void saveLog(Log log) throws DaoException {
		dao.saveLog(log);
		
	}

	public void changeNotificationImage(Login login , String imagePath) throws DaoException {
		dao.changeNotificationImage(login , imagePath);
		
	}

	public List<Vaccination> getVaccination(int id) throws DaoException {
		List<Vaccination> vaccinations = dao.getVaccination(id);
		return vaccinations;
	}

	public List<Appointment> getThisDayAppointment(int id) throws DaoException {
		List<Appointment> appointments = dao.getThisDayAppointment(id);
		return appointments;
	}

	public void setPatientVerified(long id) throws DaoException {
		dao.setPatientVerified(id);
		
	}

	public List<Review> getAllReviews(int id) throws DaoException {
		List<Review> reviews = dao.getAllReviews(id);
		Collections.sort(reviews, new ReviewComparator());
		return reviews;
	}

	public void getDoctorNotification(List<User> requests, int id) throws DaoException {
		dao.getDoctorNotification(requests , id);
		
	}

	public int doctorDoctorConnectedOrNot(int dId1, int dId2) throws DaoException {
		int i = dao.doctorDoctorConnectedOrNot(dId1,dId2);
		return i;
	}

	public List<Doctor> getAllDoctorsForDoctor(int id) throws DaoException {
		List<Doctor> doctors = dao.getAllDoctorsForDoctor(id);
		return doctors;
	}

	public void sendMessage(Long mobile, String message) {
		String destination = ""+mobile;
		try {
		
		String requestUrl  = "http://www.smsidea.co.in/sendsms.aspx?"
				+ "mobile="+URLEncoder.encode("8951191375", "UTF-8")
				+ "&pass="+URLEncoder.encode("healing2014", "UTF-8")
				+ "&senderid="+URLEncoder.encode("HCREMD", "UTF-8")
				+ "&to="+URLEncoder.encode(destination, "UTF-8")
				+ "&msg=" +URLEncoder.encode(message, "UTF-8");
		
		System.out.println(requestUrl);
		URL url = new URL(requestUrl);
		HttpURLConnection uc = (HttpURLConnection)url.openConnection();
		System.out.println(uc.getResponseMessage());
		uc.disconnect();
		} catch(Exception ex) {
			System.out.println("in exception");
		System.out.println(ex.getMessage());
		}
		
	}
	

	

}
