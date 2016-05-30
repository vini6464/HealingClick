package com.vinod.controller;

import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.vinod.model.Doctor;
import com.vinod.model.Login;
import com.vinod.model.Patient;
import com.vinod.model.Pharmacy;
import com.vinod.model.Review;
import com.vinod.service.LoginService;
import com.vinod.service.ProfileService;

/**
 * Servlet implementation class ProfileController
 */
@WebServlet("*.profile")
public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProfileController() {
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
		System.out.println(uri);
		String target = "home.jsp";
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
		ProfileService profile = new ProfileService();
		LoginService lService = new LoginService();


		try {

			if(uri.endsWith("doctorViewPatient.profile"))
			{



				Login login = (Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id = login.getId();
				LoginService service = new LoginService();
				int pId = Integer.parseInt(request.getParameter("id"));
				Patient patient = service.getPatientById(pId);

				
				if(patient.getId() == 0)
				{
					
					target = lService.setErrorControl(request,target,login1);
					
					request.setAttribute("error", "This patient doesnot exist");
				}
				else
				{
					Doctor doctor = service.getDoctorById(id);

					int connect = service.connectedOrNot(pId,id);

					String gender = "";
					if(patient.getGender()==1)
					{
						gender="Male";
					}
					else
					{
						gender="Female";
					}
					request.setAttribute("connect", connect);
					request.setAttribute("gender", gender);
					request.setAttribute("doctor", doctor);
					request.setAttribute("patient", patient);

					target="doctorViewPatientProfile.jsp";
				}
				
				



			}

			if(uri.endsWith("doctorViewPharmacy.profile"))
			{



				Login login = (Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id = login.getId();
				LoginService service = new LoginService();
				int pId = Integer.parseInt(request.getParameter("id"));
				Pharmacy pharmacy = service.getPharmacyById(pId);

				
				if(pharmacy.getId() == 0)
				{
					target = lService.setErrorControl(request,target,login1);
					request.setAttribute("error", "This pharmacy doesnot exist");
				}
				else
				{
				
				Doctor doctor = service.getDoctorById(id);

				int connect = service.doctorPharmacyConnectedOrNot(id,pId);
				
				
				
				List<Review> reviews = service.getAllReviews(pId);
				double totalRating = 0.0;
				
				for(int i=0;i<reviews.size();i++)
				{
					totalRating = totalRating + reviews.get(i).getRating();
					if(reviews.get(i).getType() == 1)
					{
						Doctor d = service.getDoctorById(reviews.get(i).getReviewerId());
						reviews.get(i).setReviewerName(d.getFirstName()+" "+d.getLastName());
					}
					if(reviews.get(i).getType() == 2)
					{
						Patient d = service.getPatientById(reviews.get(i).getReviewerId());
						reviews.get(i).setReviewerName(d.getFirstName()+" "+d.getLastName());
					}
					if(reviews.get(i).getType() == 3)
					{
						Pharmacy d = service.getPharmacyById(reviews.get(i).getReviewerId());
						reviews.get(i).setReviewerName(d.getPharmacyName());
					}
				}
				
				totalRating  = totalRating/reviews.size();
				pharmacy.setRating(totalRating);
				request.setAttribute("reviews", reviews);
				
				
				request.setAttribute("connect", connect);
				request.setAttribute("doctor", doctor);
				request.setAttribute("pharmacy", pharmacy);

				target="doctorViewPharmacyProfile.jsp";

				}
			}

			if(uri.endsWith("doctorViewDoctor.profile"))
			{


				Login login = (Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id = login.getId();
				LoginService service = new LoginService();
				int pId = Integer.parseInt(request.getParameter("id"));
				Doctor patient = service.getDoctorById(pId);

				if(patient.getId() == 0)
				{
					target = lService.setErrorControl(request,target,login1);
					request.setAttribute("error", "This doctor doesnot exist");
				}
				else
				{
				
				Doctor doctor = service.getDoctorById(id);

				int connect = service.doctorDoctorConnectedOrNot(id,pId);
				System.out.println(connect);
				request.setAttribute("connect", connect);
				String gender = "";
				if(patient.getGender()==1)
				{
					gender="Male";
				}
				else
				{
					gender="Female";
				}

				request.setAttribute("gender", gender);
				request.setAttribute("doctor", doctor);
				request.setAttribute("doctor1", patient);

				target="doctorViewDoctorProfile.jsp";

				}
			}

			if(uri.endsWith("doctor.profile"))
			{



				Login login = (Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id = login.getId();
				LoginService service = new LoginService();

				Doctor doctor = service.getDoctorById(id);


				String gender = "";
				if(doctor.getGender()==1)
				{
					gender="Male";
				}
				else
				{
					gender="Female";
				}
				List<Patient> patients = service.getAllPatients(id);
				List<Pharmacy> pharmacies = service.getAllPharmacies(id);

				request.setAttribute("patients", patients);
				request.setAttribute("pharmacies", pharmacies);
				request.setAttribute("gender", gender);
				request.setAttribute("doctor", doctor);

				target="doctorProfile.jsp";



			}


			if(uri.endsWith("doctorEdit.profile"))
			{




				Login login = (Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id = login.getId();
				LoginService service = new LoginService();
				Doctor doctor = service.getDoctorById(id);


				String gender = "";
				if(doctor.getGender()==1)
				{
					gender="Male";
				}
				else
				{
					gender="Female";
				}

				request.setAttribute("gender", gender);
				request.setAttribute("doctor", doctor);

				target="doctorEditProfile.jsp";


			}

			if(uri.endsWith("updateDoctor.profile"))
			{



				Login login = (Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id = login.getId();
				LoginService service = new LoginService();

				Doctor doctor = new Doctor();
				doctor.setId(id);
				doctor.setFirstName(request.getParameter("firstname"));
				doctor.setLastName(request.getParameter("lastname"));

				String dob = request.getParameter("dob");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
				Date date;
				date = sdf.parse(dob);
				doctor.setDob(date);
				doctor.setGender(Integer.parseInt(request.getParameter("gender")));
				doctor.setQualification(request.getParameter("qualification"));
				doctor.setSpeciality(request.getParameter("speciality"));
				doctor.setWorkLocation(request.getParameter("worklocation"));
				doctor.setAddress1(request.getParameter("address1"));
				doctor.setAddress2(request.getParameter("address2"));

				doctor.setCity(request.getParameter("city"));
				doctor.setState(request.getParameter("state"));
				doctor.setCountry(request.getParameter("country"));
				doctor.setPinCode(Long.parseLong(request.getParameter("pincode")));
				doctor.setLandMark(request.getParameter("landmark"));
				doctor.setBloodGroup(Integer.parseInt(request.getParameter("bloodgroup")));
				doctor.setLandLine(Long.parseLong(request.getParameter("landline")));

				doctor.setAboutMe(request.getParameter("aboutme"));

				int i = profile.updateDoctorProfile(doctor);
				if(i==1)
				{
					String gender = "";
					if(doctor.getGender()==1)
					{
						gender="Male";
					}
					else
					{
						gender="Female";
					}
					List<Patient> patients = service.getAllPatients(id);
					List<Pharmacy> pharmacies = service.getAllPharmacies(id);
					request.setAttribute("patients", patients);
					request.setAttribute("pharmacies", pharmacies);
					request.setAttribute("gender", gender);
					request.setAttribute("doctor", service.getDoctorById(id));


					target="doctorProfile.jsp";
				}


			}

			//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


			if(uri.endsWith("patientViewPatient.profile"))
			{



				Login login = (Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id = login.getId();
				LoginService service = new LoginService();
				int pId = Integer.parseInt(request.getParameter("id"));
				Patient patient = service.getPatientById(id);

				Patient patient1 = service.getPatientById(pId);



				String gender = "";
				if(patient1.getGender()==1)
				{
					gender="Male";
				}
				else
				{
					gender="Female";
				}

				request.setAttribute("gender", gender);
				request.setAttribute("patient1", patient1);
				request.setAttribute("patient", patient);

				target="patientViewPatientProfile.jsp";



			}

			if(uri.endsWith("patientViewPharmacy.profile"))
			{



				Login login = (Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id = login.getId();
				LoginService service = new LoginService();
				int pId = Integer.parseInt(request.getParameter("id"));
				Pharmacy pharmacy = service.getPharmacyById(pId);

				if(pharmacy.getId() == 0)
				{
					target = lService.setErrorControl(request,target,login1);
					request.setAttribute("error", "This pharmacy doesnot exist");
				}
				else
				{
				
				Patient patient = service.getPatientById(id);

				int connect = service.patientPharmacyConnectedOrNot(id,pId);
				
				
				List<Review> reviews = service.getAllReviews(pId);
				double totalRating = 0.0;
				
				for(int i=0;i<reviews.size();i++)
				{
					totalRating = totalRating + reviews.get(i).getRating();
					if(reviews.get(i).getType() == 1)
					{
						Doctor d = service.getDoctorById(reviews.get(i).getReviewerId());
						reviews.get(i).setReviewerName(d.getFirstName()+" "+d.getLastName());
					}
					if(reviews.get(i).getType() == 2)
					{
						Patient d = service.getPatientById(reviews.get(i).getReviewerId());
						reviews.get(i).setReviewerName(d.getFirstName()+" "+d.getLastName());
					}
					if(reviews.get(i).getType() == 3)
					{
						Pharmacy d = service.getPharmacyById(reviews.get(i).getReviewerId());
						reviews.get(i).setReviewerName(d.getPharmacyName());
					}
				}
				
				totalRating  = totalRating/reviews.size();
				pharmacy.setRating(totalRating);
				request.setAttribute("reviews", reviews);
				
				request.setAttribute("connect", connect);
				
				request.setAttribute("pharmacy", pharmacy);
				request.setAttribute("patient", patient);

				target="patientViewPharmacyProfile.jsp";

				}
			}

			if(uri.endsWith("patientViewDoctor.profile"))
			{



				Login login = (Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id = login.getId();
				LoginService service = new LoginService();
				int pId = Integer.parseInt(request.getParameter("id"));
				Patient patient = service.getPatientById(id);

				Doctor doctor = service.getDoctorById(pId);
				
				if(doctor.getId() == 0)
				{
					target = lService.setErrorControl(request,target,login1);
					request.setAttribute("error", "This doctor doesnot exist");
				}
				else
				{

				int connect = service.connectedOrNot(id,pId);
				String gender = "";
				if(doctor.getGender()==1)
				{
					gender="Male";
				}
				else
				{
					gender="Female";
				}
				request.setAttribute("connect", connect);
				request.setAttribute("gender", gender);
				request.setAttribute("doctor", doctor);
				request.setAttribute("patient", patient);

				target="patientViewDoctorProfile.jsp";
				}

			}

			if(uri.endsWith("patient.profile"))
			{


				Login login = (Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id = login.getId();
				LoginService service = new LoginService();

				Patient patient = service.getPatientById(id);


				String gender = "";
				if(patient.getGender()==1)
				{
					gender="Male";
				}
				else
				{
					gender="Female";
				}

				request.setAttribute("gender", gender);
				request.setAttribute("patient", patient);

				target="patientProfile.jsp";


			}


			if(uri.endsWith("patientEdit.profile"))
			{



				Login login = (Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id = login.getId();
				LoginService service = new LoginService();

				Patient patient = service.getPatientById(id);


				String gender = "";
				if(patient.getGender()==1)
				{
					gender="Male";
				}
				else
				{
					gender="Female";
				}

				request.setAttribute("gender", gender);
				request.setAttribute("patient", patient);


				target="patientEditProfile.jsp";


			}

			if(uri.endsWith("updatePatient.profile"))
			{



				Login login = (Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id = login.getId();
				LoginService service = new LoginService();

				Patient doctor = service.getPatientById(id);
				doctor.setId(id);
				doctor.setFirstName(request.getParameter("firstname"));
				doctor.setLastName(request.getParameter("lastname"));

				String dob = request.getParameter("dob");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
				Date date;
				date = sdf.parse(dob);
				doctor.setDob(date);
				doctor.setGender(Integer.parseInt(request.getParameter("gender")));
				doctor.setAddress1(request.getParameter("address1"));
				doctor.setAddress2(request.getParameter("address2"));
				doctor.setEmergency1(Long.parseLong(request.getParameter("emergency1")));
				doctor.setEmergency2(Long.parseLong(request.getParameter("emergency2")));
				doctor.setCity(request.getParameter("city"));
				doctor.setState(request.getParameter("state"));
				doctor.setCountry(request.getParameter("country"));
				doctor.setPinCode(Long.parseLong(request.getParameter("pincode")));
				doctor.setLandMark(request.getParameter("landmark"));
				doctor.setBloodGroup(Integer.parseInt(request.getParameter("bloodgroup")));
				doctor.setLandLine(Long.parseLong(request.getParameter("landline")));

				doctor.setAboutMe(request.getParameter("aboutme"));

				int i = profile.updatePatientProfile(doctor);
				if(i==1)
				{
					String gender = "";
					if(doctor.getGender()==1)
					{
						gender="Male";
					}
					else
					{
						gender="Female";
					}

					request.setAttribute("gender", gender);
					request.setAttribute("patient", service.getPatientById(id));
					target="patientProfile.jsp";
				}

			}

			//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


			if(uri.endsWith("pharmacyViewPatient.profile"))
			{


				Login login = (Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id = login.getId();
				LoginService service = new LoginService();

				int pId = Integer.parseInt(request.getParameter("id"));
				Patient patient = service.getPatientById(pId);

				if(patient.getId() == 0)
				{
					target = lService.setErrorControl(request,target,login1);
					request.setAttribute("error", "This patient doesnot exist");
				}
				else
				{
				
				Pharmacy pharmacy = service.getPharmacyById(id);



				String gender = "";
				if(patient.getGender()==1)
				{
					gender="Male";
				}
				else
				{
					gender="Female";
				}

				request.setAttribute("gender", gender);
				request.setAttribute("pharmacy", pharmacy);
				request.setAttribute("patient", patient);

				target="pharmacyViewPatientProfile.jsp";
				}

			}

			if(uri.endsWith("pharmacyViewPharmacy.profile"))
			{



				Login login = (Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id = login.getId();
				LoginService service = new LoginService();
				int pId = Integer.parseInt(request.getParameter("id"));
				Pharmacy patient1 = service.getPharmacyById(pId);

				if(patient1.getId() == 0)
				{
					target = lService.setErrorControl(request,target,login1);
					request.setAttribute("error", "This pharmacy doesnot exist");
				}
				else
				{
				
				Pharmacy pharmacy = service.getPharmacyById(id);
				int connect = service.connectedPharmacyToPharmacyOrNot(id,pId);
				
				List<Review> reviews = service.getAllReviews(pId);
				double totalRating = 0.0;
				
				for(int i=0;i<reviews.size();i++)
				{
					totalRating = totalRating + reviews.get(i).getRating();
					if(reviews.get(i).getType() == 1)
					{
						Doctor d = service.getDoctorById(reviews.get(i).getReviewerId());
						reviews.get(i).setReviewerName(d.getFirstName()+" "+d.getLastName());
					}
					if(reviews.get(i).getType() == 2)
					{
						Patient d = service.getPatientById(reviews.get(i).getReviewerId());
						reviews.get(i).setReviewerName(d.getFirstName()+" "+d.getLastName());
					}
					if(reviews.get(i).getType() == 3)
					{
						Pharmacy d = service.getPharmacyById(reviews.get(i).getReviewerId());
						reviews.get(i).setReviewerName(d.getPharmacyName());
					}
				}
				
				totalRating  = totalRating/reviews.size();
				patient1.setRating(totalRating);
				request.setAttribute("reviews", reviews);
				
				request.setAttribute("connect", connect);
				request.setAttribute("pharmacy", pharmacy);
				request.setAttribute("pharmacy1", patient1);

				target="pharmacyViewPharmacyProfile.jsp";
				}
			}

			if(uri.endsWith("pharmacyViewDoctor.profile"))
			{



				Login login = (Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id = login.getId();
				LoginService service = new LoginService();
				int pId = Integer.parseInt(request.getParameter("id"));
				Doctor patient = service.getDoctorById(pId);

				if(patient.getId() == 0)
				{
					target = lService.setErrorControl(request,target,login1);
					request.setAttribute("error", "This doctor doesnot exist");
				}
				else
				{
				
				Pharmacy pharmacy = service.getPharmacyById(id);


				String gender = "";
				if(patient.getGender()==1)
				{
					gender="Male";
				}
				else
				{
					gender="Female";
				}

				request.setAttribute("gender", gender);
				request.setAttribute("pharmacy", pharmacy);
				request.setAttribute("doctor", patient);

				target="pharmacyViewDoctorProfile.jsp";
				}

			}

			if(uri.endsWith("pharmacy.profile"))
			{



				Login login = (Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id = login.getId();
				LoginService service = new LoginService();

				Pharmacy pharmacy = service.getPharmacyById(id);

				List<Review> reviews = service.getAllReviews(id);
				double totalRating = 0.0;
				
				for(int i=0;i<reviews.size();i++)
				{
					totalRating = totalRating + reviews.get(i).getRating();
					if(reviews.get(i).getType() == 1)
					{
						Doctor d = service.getDoctorById(reviews.get(i).getReviewerId());
						reviews.get(i).setReviewerName(d.getFirstName()+" "+d.getLastName());
					}
					if(reviews.get(i).getType() == 2)
					{
						Patient d = service.getPatientById(reviews.get(i).getReviewerId());
						reviews.get(i).setReviewerName(d.getFirstName()+" "+d.getLastName());
					}
					if(reviews.get(i).getType() == 3)
					{
						Pharmacy d = service.getPharmacyById(reviews.get(i).getReviewerId());
						reviews.get(i).setReviewerName(d.getPharmacyName());
					}
				}
				
				totalRating  = totalRating/reviews.size();
				pharmacy.setRating(totalRating);
				request.setAttribute("reviews", reviews);
				
				request.setAttribute("pharmacy", pharmacy);

				target="pharmacyProfile.jsp";

			}


			if(uri.endsWith("pharmacyEdit.profile"))
			{



				Login login = (Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id = login.getId();
				LoginService service = new LoginService();

				Pharmacy pharmacy = service.getPharmacyById(id);
				request.setAttribute("pharmacy", pharmacy);

				target="pharmacyEditProfile.jsp";

			}

			if(uri.endsWith("updatePharmacy.profile"))
			{




				Login login = (Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id = login.getId();
				LoginService service = new LoginService();
				Pharmacy doctor = new Pharmacy();
				doctor.setId(id);


				doctor.setPharmacyName(request.getParameter("pharmacy"));
				doctor.setAddress1(request.getParameter("address1"));
				doctor.setAddress2(request.getParameter("address2"));

				doctor.setCity(request.getParameter("city"));
				doctor.setState(request.getParameter("state"));
				doctor.setCountry(request.getParameter("country"));
				doctor.setPinCode(Long.parseLong(request.getParameter("pincode")));
				doctor.setLandMark(request.getParameter("landmark"));

				doctor.setLandLine(Long.parseLong(request.getParameter("landline")));

				doctor.setAboutMe(request.getParameter("aboutme"));

				int i = profile.updatePharmacyProfile(doctor);
				request.setAttribute("pharmacy", service.getPharmacyById(id));
				target="pharmacyProfile.jsp";



			}

		} catch (Exception e) {
			try {
				System.out.println("\n In controller Error:"+e.getMessage());
				target = lService.setErrorControl(request,target,login1);
				request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
			} catch (Exception e1) {
				System.out.println("\n In controller Error:"+e1.getMessage());
				request.setAttribute("error", "Please Login To Continue.");
				target="home.jsp";
			}

		}
		request.getRequestDispatcher(target).forward(request, response);
	}
}






