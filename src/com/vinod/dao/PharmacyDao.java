package com.vinod.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vinod.exception.DaoException;
import com.vinod.model.Login;
import com.vinod.model.Medicine;
import com.vinod.model.Order;
import com.vinod.util.DBUtil;

public class PharmacyDao {

	public Order getOrderById(int oId) throws DaoException  {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Order order = new Order();
		String query = "select * from orders where id=?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, oId);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
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
				order.setTransactionId(rs.getString("transactionid"));
				order.setCashType(rs.getInt("paymenttype"));
			}
		} 
		 catch (SQLException e) {
			 
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		} finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return order;
	}

	public List<Medicine> getOrderedMedicinesByOrderId(int oId) throws DaoException  {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Medicine> medicines = new ArrayList<Medicine>();
		String query = "select * from ordermedicines where orderid=?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, oId);
			
			rs = ps.executeQuery();
			
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
		} 
		 catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		} finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return medicines;
	}

	public void updateCost(double deliveryCharge, double discount, Login login) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		String query="update pharmacy set deliverycharge=? , discount=? where id=? ";

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setDouble(1, deliveryCharge);
			ps.setDouble(2, discount);
			ps.setLong(3, login.getId());
			
		    int i=ps.executeUpdate();	
		    System.out.println("Successfully Delivery Charge Updated : "+i);
		}catch (SQLException e) {
			
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}

	public List<Medicine> getPharmacyMedicines(Login login) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Medicine> medicines = new ArrayList<Medicine>();
		String query = "select pharmacymedicine.medicinename,pharmacymedicine.cost from pharmacymedicine where pharmacymedicine.pharmacyid=?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1, login.getId());
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Medicine medicine = new Medicine();
				medicine.setName(rs.getString("medicinename"));
				medicine.setCost(rs.getDouble("cost"));
				
				medicines.add(medicine);
			}
		}  catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return medicines;
	}

	public void removeOldMedicines(Login login) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		String query = "delete from pharmacymedicine where pharmacyid=?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, login.getId());
			i = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();		}finally
			{
				DBUtil.releaseResource(rs);
				DBUtil.releaseResource(ps);
				DBUtil.releaseResource(con);
			}
		
	}

	public void updateNewMedicines(Login login, List<Medicine> medicines) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "insert into pharmacymedicine values (?,?,?)";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			int i = 0;

	        for (Medicine entity : medicines) {
	        	ps.setInt(1, login.getId());
	        	ps.setString(2, entity.getName());
	            ps.setDouble(3 , entity.getCost());

	        	ps.addBatch();
	            i++;

	            if (i % 1000 == 0 || i == medicines.size()) {
	            	ps.executeBatch(); // Execute every 1000 items.
	            }
	        }
		} catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();		}finally
			{
				DBUtil.releaseResource(rs);
				DBUtil.releaseResource(ps);
				DBUtil.releaseResource(con);
			}
		
		
	}

}
