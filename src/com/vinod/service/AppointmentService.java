package com.vinod.service;

import java.util.List;

import com.vinod.dao.AppointmentDao;
import com.vinod.exception.DaoException;
import com.vinod.model.Appointment;
import com.vinod.model.Event;
import com.vinod.model.Login;

public class AppointmentService {

	AppointmentDao dao = new AppointmentDao();

	public int saveAppointment(Appointment appointment) throws DaoException {
		int i = dao.saveAppointment(appointment);
		return i;
	}

	public List<Event> getPatientDoctorEvent(int pId, int dId) throws DaoException {
		List<Event> events = dao.getPatientDoctorEvent(pId,dId);
		return events;
	}

	public Appointment getEventById(int eventId) throws DaoException {
		Appointment appointment = dao.getEventById(eventId);
		return appointment;
	}

	public void updateAppointment(Appointment appointment) throws DaoException {
		dao.updateAppointment(appointment);
		
	}

	public void deleteAppointment(int eventId) throws DaoException {
		dao.deleteAppointment(eventId);
		
	}

	public List<Event> getEvents(Login login) throws DaoException {
		List<Event> events = dao.getEvents(login);
		return events;
	}

	public void acceptAppointment(int eventId) throws DaoException {
		dao.acceptAppointment(eventId);
		
	}
	

}
