package com.vinod.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import org.apache.log4j.Logger;

import com.vinod.exception.DaoException;
import com.vinod.model.Login;
import com.vinod.model.Medicine;
import com.vinod.model.Order;
import com.vinod.model.Review;
import com.vinod.util.DBUtil;

public class OrderDao {
	final static Logger logger = Logger.getLogger(OrderDao.class);
	public int saveOrder(Order order) throws DaoException  {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int i=0;
		int dId=0;
		int pId=0;
		int phId=0;
		if(order.getDoctorId()!=0)
		{
			dId = order.getDoctorId();
			
		}
		
		if(order.getPatientId()!=0)
		{
			pId= order.getPatientId();
			
		}
		
		if(order.getPharmacyId()!=0)
		{
			phId= order.getPharmacyId();
			
		}
		String query = "insert into orders(doctorid,patientid,pharmacyid,supplierid,totalcost,ordertype,paymenttype,address1,address2,landmark,city,state,country,pincode,deliveredon,mobile,email,image,createdon,status,transactionid) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,1,?)";
		
		try {
			con = DBUtil.getConnection();
			
			ps = con.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, dId);
			ps.setInt(2, pId);
			ps.setInt(3, phId);
			ps.setInt(4, order.getSupplierPharmacyId());
			ps.setDouble(5, order.getTotalCost());
			ps.setInt(6, order.getOrderType());
			ps.setInt(7, order.getCashType());
			ps.setString(8, order.getAddress1());
			ps.setString(9, order.getAddress2());
			ps.setString(10, order.getLandMark());
			ps.setString(11, order.getCity());
			ps.setString(12, order.getState());
			ps.setString(13, order.getCountry());
			ps.setLong(14, order.getPinCode());
			ps.setTimestamp(15, order.getDeliveredOn());
			ps.setLong(16, order.getPhoneNumber());
			ps.setString(17, order.getEmail());
			ps.setString(18, order.getImage());
			ps.setTimestamp(19, new Timestamp(new Date().getTime()));
			ps.setString(20, order.getTransactionId());
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys();
			
			while(rs.next())
			{
				i = rs.getInt(1);
			}
		}  catch (SQLException e) {
			
			logger.error(Level.SEVERE,e); throw new DaoException();
		} finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
		return i;
	}

	public void saveMedicine(long orderId, Medicine medicine) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "insert into ordermedicines(orderid,medicineid,name,quantity,cost) values (?,?,?,?,?)";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1, orderId);
			ps.setInt(2, medicine.getId());
			ps.setString(3, medicine.getName());
			ps.setInt(4, medicine.getQuantity());
			ps.setDouble(5, medicine.getCost());
			ps.executeUpdate();
			
		}  catch (SQLException e) {
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
		
	}

	public void verifyOrder(int oId) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Date d = new Date();
		Timestamp t = new Timestamp(d.getTime());
		String query = "update orders set status = 2,processedon=? where id=?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setTimestamp(1,t);
			ps.setInt(2, oId);
			
			
			 ps.executeUpdate();
		}catch (SQLException e) {
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
		
	}

	public void deliverOrder(int oId) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
	
		String query = "update orders set status = 4 where id=?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, oId);
			
			
			 ps.executeUpdate();
		}catch (SQLException e) {
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
		
	}

	public List<Order> getAllUrgentOrder(Login login) throws DaoException {
		
		int id=0;
		String query ="";
		if(login.getType()==1)
		{
			id = login.getId();
			query = "select * from orders where doctorid=? and ordertype=2 and status<>4";
		}
		if(login.getType()==2)
		{
			id = login.getId();
			query = "select * from orders where patientid=? and ordertype=2 and status<>4";
		}
		List<Order> orders = new ArrayList<Order>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setDoctorId(rs.getInt("doctorid"));
				order.setPatientId(rs.getInt("patientid"));
				order.setPharmacyId(rs.getInt("pharmacyid"));
				order.setSupplierPharmacyId(rs.getInt("supplierid"));
				order.setAddress1(rs.getString("address1"));
				order.setAddress2(rs.getString("address2"));
				order.setCashType(rs.getInt("paymenttype"));
				order.setCity(rs.getString("city"));
				order.setCountry(rs.getString("country"));
				order.setCreatedOn(rs.getTimestamp("createdon"));
				order.setDeliveredOn(rs.getTimestamp("deliveredon"));
				order.setEmail(rs.getString("email"));
				order.setLandMark(rs.getString("landmark"));
				order.setOrderType(rs.getInt("ordertype"));
				order.setPhoneNumber(rs.getLong("mobile"));
				order.setPinCode(rs.getLong("pincode"));
				//order.setProcessedOn(rs.getTimestamp("processedon"));
				order.setState(rs.getString("state"));
				order.setTotalCost(rs.getDouble("totalcost"));
				order.setStatus(rs.getInt("status"));
				order.setImage(rs.getString("image"));
				order.setComment(rs.getString("comment"));
				orders.add(order);
			}
		}catch (SQLException e) {
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return orders;
	}

	public List<Medicine> getAllMedicinesByOrderId(long l) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Medicine> medicines = new ArrayList<Medicine>();
		String query = "select * from ordermedicines where orderid=?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1, l);
			rs =ps.executeQuery();
			
			while(rs.next())
			{
				Medicine medicine  = new Medicine();
				medicine.setOrderId(rs.getInt("orderid"));
				medicine.setId(rs.getInt("medicineid"));
				medicine.setName(rs.getString("name"));
				medicine.setQuantity(rs.getInt("quantity"));
				medicine.setCost(rs.getDouble("cost"));
				
				medicines.add(medicine);
			}
		}catch (SQLException e) {
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return medicines;
	}

	public List<Order> getAllNormalOrder(Login login) throws DaoException {
		int id=0;
		String query ="";
		if(login.getType()==1)
		{
			id = login.getId();
			query = "select * from orders where doctorid=? and ordertype=1 and status<>4";
		}
		if(login.getType()==2)
		{
			id = login.getId();
			query = "select * from orders where patientid=? and ordertype=1 and status<>4";
		}
		List<Order> orders = new ArrayList<Order>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setDoctorId(rs.getInt("doctorid"));
				order.setPatientId(rs.getInt("patientid"));
				order.setPharmacyId(rs.getInt("pharmacyid"));
				order.setSupplierPharmacyId(rs.getInt("supplierid"));
				order.setAddress1(rs.getString("address1"));
				order.setAddress2(rs.getString("address2"));
				order.setCashType(rs.getInt("paymenttype"));
				order.setCity(rs.getString("city"));
				order.setCountry(rs.getString("country"));
				order.setCreatedOn(rs.getTimestamp("createdon"));
				order.setDeliveredOn(rs.getTimestamp("deliveredon"));
				order.setEmail(rs.getString("email"));
				order.setLandMark(rs.getString("landmark"));
				order.setOrderType(rs.getInt("ordertype"));
				order.setPhoneNumber(rs.getLong("mobile"));
				order.setPinCode(rs.getLong("pincode"));
				//order.setProcessedOn(rs.getTimestamp("processedon"));
				order.setState(rs.getString("state"));
				order.setTotalCost(rs.getDouble("totalcost"));
				order.setStatus(rs.getInt("status"));
				order.setImage(rs.getString("image"));
				order.setComment(rs.getString("comment"));
				
				orders.add(order);
			}
		}catch (SQLException e) {
			System.err.println(e.getMessage());
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return orders;
	}

	public List<Order> getAllDeliveredOrder(Login login) throws DaoException {
		int id=0;
		String query ="";
		if(login.getType()==1)
		{
			id = login.getId();
			query = "select * from orders where doctorid=? and status=4";
		}
		if(login.getType()==2)
		{
			id = login.getId();
			query = "select * from orders where patientid=? and status=4";
		}
		List<Order> orders = new ArrayList<Order>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setDoctorId(rs.getInt("doctorid"));
				order.setPatientId(rs.getInt("patientid"));
				order.setPharmacyId(rs.getInt("pharmacyid"));
				order.setSupplierPharmacyId(rs.getInt("supplierid"));
				order.setAddress1(rs.getString("address1"));
				order.setAddress2(rs.getString("address2"));
				order.setCashType(rs.getInt("paymenttype"));
				order.setCity(rs.getString("city"));
				order.setCountry(rs.getString("country"));
				order.setCreatedOn(rs.getTimestamp("createdon"));
				order.setDeliveredOn(rs.getTimestamp("deliveredon"));
				order.setEmail(rs.getString("email"));
				order.setLandMark(rs.getString("landmark"));
				order.setOrderType(rs.getInt("ordertype"));
				order.setPhoneNumber(rs.getLong("mobile"));
				order.setPinCode(rs.getLong("pincode"));
				//order.setProcessedOn(rs.getTimestamp("processedon"));
				order.setState(rs.getString("state"));
				order.setTotalCost(rs.getDouble("totalcost"));
				order.setStatus(rs.getInt("status"));
				order.setImage(rs.getString("image"));
				order.setComment(rs.getString("comment"));
				
				orders.add(order);
			}
		}catch (SQLException e) {
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return orders;
	}

	public List<Order> getAllUrgentOrderForPharmacy(Login login) throws DaoException {
		
		List<Order> orders = new ArrayList<Order>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select * from orders where supplierid=? and ordertype=2 and status<>4";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, login.getId());
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setDoctorId(rs.getInt("doctorid"));
				order.setPatientId(rs.getInt("patientid"));
				order.setPharmacyId(rs.getInt("pharmacyid"));
				order.setSupplierPharmacyId(rs.getInt("supplierid"));
				order.setAddress1(rs.getString("address1"));
				order.setAddress2(rs.getString("address2"));
				order.setCashType(rs.getInt("paymenttype"));
				order.setCity(rs.getString("city"));
				order.setCountry(rs.getString("country"));
				order.setCreatedOn(rs.getTimestamp("createdon"));
				order.setDeliveredOn(rs.getTimestamp("deliveredon"));
				order.setEmail(rs.getString("email"));
				order.setLandMark(rs.getString("landmark"));
				order.setOrderType(rs.getInt("ordertype"));
				order.setPhoneNumber(rs.getLong("mobile"));
				order.setPinCode(rs.getLong("pincode"));
				//order.setProcessedOn(rs.getTimestamp("processedon"));
				order.setState(rs.getString("state"));
				order.setTotalCost(rs.getDouble("totalcost"));
				order.setStatus(rs.getInt("status"));
				order.setImage(rs.getString("image"));
				order.setComment(rs.getString("comment"));
				
				orders.add(order);
			}
		}catch (SQLException e) {
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return orders;
	}

	public List<Order> getAllNormalOrderForPharmacy(Login login) throws DaoException {
		List<Order> orders = new ArrayList<Order>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select * from orders where supplierid=? and ordertype=1 and status<>4";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, login.getId());
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setDoctorId(rs.getInt("doctorid"));
				order.setPatientId(rs.getInt("patientid"));
				order.setPharmacyId(rs.getInt("pharmacyid"));
				order.setSupplierPharmacyId(rs.getInt("supplierid"));
				order.setAddress1(rs.getString("address1"));
				order.setAddress2(rs.getString("address2"));
				order.setCashType(rs.getInt("paymenttype"));
				order.setCity(rs.getString("city"));
				order.setCountry(rs.getString("country"));
				order.setCreatedOn(rs.getTimestamp("createdon"));
				order.setDeliveredOn(rs.getTimestamp("deliveredon"));
				order.setEmail(rs.getString("email"));
				order.setLandMark(rs.getString("landmark"));
				order.setOrderType(rs.getInt("ordertype"));
				order.setPhoneNumber(rs.getLong("mobile"));
				order.setPinCode(rs.getLong("pincode"));
				//order.setProcessedOn(rs.getTimestamp("processedon"));
				order.setState(rs.getString("state"));
				order.setTotalCost(rs.getDouble("totalcost"));
				order.setStatus(rs.getInt("status"));
				order.setImage(rs.getString("image"));
				order.setComment(rs.getString("comment"));
				orders.add(order);
			}
		}catch (SQLException e) {
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return orders;
	}

	public List<Order> getAllDeliveredOrderForPharmacy(Login login) throws DaoException {
		List<Order> orders = new ArrayList<Order>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select * from orders where supplierid=? and status=4";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, login.getId());
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setDoctorId(rs.getInt("doctorid"));
				order.setPatientId(rs.getInt("patientid"));
				order.setPharmacyId(rs.getInt("pharmacyid"));
				order.setSupplierPharmacyId(rs.getInt("supplierid"));
				order.setAddress1(rs.getString("address1"));
				order.setAddress2(rs.getString("address2"));
				order.setCashType(rs.getInt("paymenttype"));
				order.setCity(rs.getString("city"));
				order.setCountry(rs.getString("country"));
				order.setCreatedOn(rs.getTimestamp("createdon"));
				order.setDeliveredOn(rs.getTimestamp("deliveredon"));
				order.setEmail(rs.getString("email"));
				order.setLandMark(rs.getString("landmark"));
				order.setOrderType(rs.getInt("ordertype"));
				order.setPhoneNumber(rs.getLong("mobile"));
				order.setPinCode(rs.getLong("pincode"));
				//order.setProcessedOn(rs.getTimestamp("processedon"));
				order.setState(rs.getString("state"));
				order.setTotalCost(rs.getDouble("totalcost"));
				order.setStatus(rs.getInt("status"));
				order.setImage(rs.getString("image"));
				order.setComment(rs.getString("comment"));
				orders.add(order);
			}
		}catch (SQLException e) {
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return orders;
	}

	public List<Order> getAllMyOrderForPharmacy(Login login) throws DaoException {
		List<Order> orders = new ArrayList<Order>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select * from orders where pharmacyid=? ";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, login.getId());
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setDoctorId(rs.getInt("doctorid"));
				order.setPatientId(rs.getInt("patientid"));
				order.setPharmacyId(rs.getInt("pharmacyid"));
				order.setSupplierPharmacyId(rs.getInt("supplierid"));
				order.setAddress1(rs.getString("address1"));
				order.setAddress2(rs.getString("address2"));
				order.setCashType(rs.getInt("paymenttype"));
				order.setCity(rs.getString("city"));
				order.setCountry(rs.getString("country"));
				order.setCreatedOn(rs.getTimestamp("createdon"));
				order.setDeliveredOn(rs.getTimestamp("deliveredon"));
				order.setEmail(rs.getString("email"));
				order.setLandMark(rs.getString("landmark"));
				order.setOrderType(rs.getInt("ordertype"));
				order.setPhoneNumber(rs.getLong("mobile"));
				order.setPinCode(rs.getLong("pincode"));
				//order.setProcessedOn(rs.getTimestamp("processedon"));
				order.setState(rs.getString("state"));
				order.setTotalCost(rs.getDouble("totalcost"));
				order.setStatus(rs.getInt("status"));
				order.setImage(rs.getString("image"));
				order.setComment(rs.getString("comment"));
				orders.add(order);
			}
		}catch (SQLException e) {
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return orders;
	}

	public int cancelOrderDoctor(int oId ,String comment) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		String query = "update orders set status = 5,comment=? where id=?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, comment);
			ps.setInt(2, oId);
			
			
			 i=ps.executeUpdate();
		}catch (SQLException e) {
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return i;
	}

	public int cancelOrderPatient(int oId ,String comment) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		String query = "update orders set status = 5 ,comment=? where id=?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, comment);
			ps.setInt(2, oId);
			
			
			 i=ps.executeUpdate();
		}catch (SQLException e) {
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return i;
	}

	public int cancelOrderPharmacy(int oId, int status ,String comment) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		String query = "update orders set status = ? ,comment=? where id=?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, status);
			ps.setString(2, comment);
			ps.setInt(3, oId);
			
			
			 i=ps.executeUpdate();
		}catch (SQLException e) {
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return i;
	}

	public int saveReview(Review review) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		String query = "insert into review(pharmacyid,reviewerid,type,comment,rating) values (?,?,?,?,?)";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1, review.getPharmacyId());
			ps.setLong(2, review.getReviewerId());
			ps.setInt(3, review.getType());
			ps.setString(4, review.getComment());
			ps.setDouble(5, review.getRating());
			
			
			
			 i=ps.executeUpdate();
		}catch (SQLException e) {
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return i;
	}

	public int checkIfReviewExist(Review review) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		String query = "select count(*) from review where pharmacyid=? and reviewerid=?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1, review.getPharmacyId());
			ps.setLong(2, review.getReviewerId());
			
			 rs = ps.executeQuery();
			 while(rs.next())
				{
				 i = rs.getInt(1);
				}
			 
		}catch (SQLException e) {
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return i;
	}

	

	public void updateReview(Review review) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "update review set comment =? ,rating =? where pharmacyid=? and reviewerid=?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, review.getComment());
			ps.setDouble(2, review.getRating());
			ps.setLong(3, review.getPharmacyId());
			ps.setLong(4, review.getReviewerId());
			ps.executeUpdate();
		}catch (SQLException e) {
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}

}
