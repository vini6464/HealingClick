package com.vinod.service;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.vinod.dao.AlertDao;
import com.vinod.exception.DaoException;
import com.vinod.model.Medicine;
import com.vinod.model.Vaccination;

public class AlertService {


	AlertDao dao = new AlertDao();
	public List<Vaccination> getVaccinations() throws DaoException {
		List<Vaccination> vaccinations = dao.getVaccinations();
		return vaccinations;
	}
	public List<Medicine> getMedicines() throws DaoException {
		List<Medicine> medicines = dao.getMedicines();
		return medicines;
	}
	public void sendSMS(String msg, long mobile, Time mt) {
		String destination = ""+mobile;
		Date d = new Date();
		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
		String result = df.format(d.getTime());
		
		
		DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a");
		String r = dateFormat.format(mt.getTime());
		
		String sc = result+" "+r;
		System.out.println(sc);
		try {
		
			String urlStr  = "http://www.smsidea.co.in/sendScheduleSMS.aspx?"
				+"mobile=8951191375"
				+"&pass=healing2014"
				+"&senderid=HCREMD"
				+"&to="+destination
				+"&msg="+msg
				+"&schDT="+sc
				+"&msgtype=uc";
		
		
			URL url = new URL(urlStr);
			URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
			url = uri.toURL();
		System.out.println(url);
		
		HttpURLConnection uc = (HttpURLConnection)url.openConnection();
		System.out.println(uc.getResponseMessage());
		uc.disconnect();
		} catch(Exception ex) {
			System.out.println("in exception");
		System.out.println(ex.getMessage());
		}
		
	}
	public void updateLeftMedicines(int prescriptionId, String name, int left) throws DaoException {
		dao.updateLeftMedicines(prescriptionId , name , left);
		
	}

}
