<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>HealingClick</title>

<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<link href="css/basic.css" rel="stylesheet">
<script type="text/javascript" src="js/ajax.js"></script>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="js/jquery.js"></script>
<style>
.btn-block{background-color:#f9f9f9;color:black;padding:5px;margin-top:-2px;}
</style>
</head>
<body>
<div class="container-fluid">
	 <div class="row  text-center" style="background-color:white;">
    <div class="col-xs-5 col-md-2 column logo">
    HEALINGCLICK
      </div>
    <div class="col-xs-7 col-sm-6 col-md-3 col-md-push-7" style="">
      
       <ul class="nav nright nav-pills">
						<li>
							<a href="logout.admin"> <span class="glyphicon glyphicon-off"></span> Log Out</a>
						</li>
					</ul>
     
    </div>
   <div class=" col-xs-12 col-sm-12 col-md-7 col-md-pull-3">
	
	
     
       <ul class="nav ncenter nav-pills">
 	
    <li><a href="home.admin" style="background-color: #f5eee8;">Home</a>
					<li><a href="order.admin">Order</a></li>
					<li><a href="prescription.admin">Prescription</a></li>
					<li><a href="users.jsp">User</a></li>
					<li><a href="forums.jsp">Community</a></li>
					<li><a href="complaints.admin">Complaints</a></li>
  </ul> 
     
    </div>
 
  </div>
  

	<div class="container-fluid" style="margin-top: 4%;">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="row clearfix">
                  <div class=" col-md-2 column"></div>
					<div class="col-xs-12 col-sm-6 col-md-6 column">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h5 class="panel-title">
									Order details</h5>
							</div>
							<div class="panel-body">
								<table class="table table-condensed">
								<tr>
										<td class="field-label col-xs-4 col-sm-3 col-md-3 column  active"><label>
										Order Id </label></td>
										<td class="col-xs-8 col-sm-9 col-md-9 column"> ${order.id}</td>
									</tr>
									<tr>
										<td class="field-label col-xs-3 active"><label>
										Customer Name: </label></td>
										<td class="col-md-9"><c:if test="${order.patientId ne 0}">
					<a href="getPatient.admin?id=${order.patientId}">${order.patientName}</a>
				</c:if>
												<c:if test="${order.doctorId ne 0}">
					<a href="getDoctor.admin?id=${order.doctorId}">Dr. ${order.doctorName}</a>
				</c:if>
												<c:if test="${order.pharmacyId ne 0}">
					<a href="getPharmacy.admin?id=${order.pharmacyId}">${order.pharmacyName}</a>
				</c:if></td>
									</tr>
								<tr>
										<td class="field-label col-xs-3 active"><label>
										Pharmacy Name: </label></td>
										<td class="col-md-9">
					<a href="getPharmacy.admin?id=${order.supplierPharmacyId}">${order.supplierPharmacyName}</a>
				</td>
									</tr>
									<tr>
										<td class="field-label col-xs-3 active"><label>
										Status </label></td>
										<td class="col-md-9"><c:if test="${order.status == 1}">Processing</c:if>
											<c:if test="${order.status == 2}">Dispatched</c:if>
											<c:if test="${order.status == 3}">Cancelled By Seller</c:if>
											<c:if test="${order.status == 4}">Delivered</c:if>
											<c:if test="${order.status == 5}">Cancelled By Customer</c:if></td>
									</tr>
								
									<tr>
										<td class="field-label col-xs-3 active"><label>Type
												of Order:</label></td>
										<td class="col-md-9"><c:if test="${order.orderType == 1}">Normal</c:if>
											<c:if test="${order.orderType == 2}">Urgent</c:if></td>
									</tr>
								
<c:if test="${not empty order.image }">
										<tr>
											<td class="field-label col-xs-3 active"><label>Prescription
													Image </label></td>
											<td class="thumbnail"><img alt="300x200"
												src="${order.image}" /></td>
										</tr>
									</c:if>
                             </table>
								<table class="table" style="margin-top: 2%;">
									<tr>
										<td class="field-label col-xs-6 active"><label>Medicine
												Name </label></td>
										<td class="field-label col-xs-3 active"><label>Quantity</label>
										</td>
										<td class="field-label col-xs-3 active"><label>Cost</label>
										</td>
									</tr>
									<c:forEach var="medicine" items="${order.medicines}">
										<c:if test="${medicine.name ne null}">
											<tr>
												<td class="col-md-6">${medicine.name}</td>
												<td class="col-md-3">${medicine.quantity}</td>
												<td class="col-md-3">${medicine.cost}</td>
											</tr>
										</c:if>
									</c:forEach>
								</table>

								<table class="table" style="margin-top: 2%;">
									<tr>
										<td class="field-label col-xs-3 active"><label>Total
												Cost:</label></td>
										<td class="col-md-9">${order.totalCost}</td>
									</tr>
									<tr>
										<td class="field-label col-xs-3 active"><label>
												Payment Type: </label></td>
										<td class="col-md-9"><c:if test="${order.cashType == 1}">Cash On Delivery</c:if>
											<c:if test="${order.cashType == 2}">Card</c:if></td>
									</tr>
									<tr>
										<td class="field-label col-xs-3 active"><label>Delivery
												Date:</label></td>
										<td class="col-md-9">${order.deliveredOn.getDate()}/${order.deliveredOn.getMonth()+1}/${order.deliveredOn.getYear()+1900}
										</td>
									</tr>

								</table>

								<h4 class="text-left" style="color: grey;">Shipping Details</h4>


								<table class="table">
									<tr>
										<td class="field-label col-xs-3 active"><label>Name:</label>
										</td>
										<td class="col-md-9">
												<c:if test="${order.patientId ne 0}">
					<a href="getPatient.admin?id=${order.patientId}">${order.patientName}</a>
				</c:if>
												<c:if test="${order.doctorId ne 0}">
					<a href="getDoctor.admin?id=${order.doctorId}">Dr. ${order.doctorName}</a>
				</c:if>
												<c:if test="${order.pharmacyId ne 0}">
					<a href="getPharmacy.admin?id=${order.pharmacyId}">${order.pharmacyName}</a>
				</c:if>
											</td>
									</tr>
									
									<c:if test="${not empty order.comment}">
									<tr>
										<td class="field-label col-xs-3 active"><label>
												Comment:</label></td>
										<td class="col-md-9">${order.comment}</td>
									</tr>
									</c:if>
									
									<tr>
										<td class="field-label col-xs-3 active"><label>
												Contact:</label></td>
										<td class="col-md-9">${order.phoneNumber}</td>
									</tr>
									<tr>
										<td class="field-label col-xs-3 active"><label>
												Email:</label></td>
										<td class="col-md-9">${order.email}</td>
									</tr>


									<tr>
										<td class="field-label col-xs-3 active"><label>
												Address:</label></td>
										<td class="col-md-9">${order.address1} <br>${order.address2}
										</td>
									</tr>

									<tr>
										<td class="field-label col-xs-3 active"><label>
												Landmark:</label></td>
										<td class="col-md-9">${order.landMark}</td>
									</tr>

									<tr>
										<td class="field-label col-xs-3 active"><label>City:</label>
										</td>
										<td class="col-md-9">${order.city}</td>
									</tr>
									<tr>
										<td class="field-label col-xs-3 active"><label>
												State:</label></td>
										<td class="col-md-9">${order.state}</td>
									</tr>
									<tr>
										<td class="field-label col-xs-3 active"><label>Country:</label>
										</td>
										<td class="col-md-9">${order.country}</td>
									</tr>
									<tr>
										<td class="field-label col-xs-3 active"><label>Pincode:</label>
										</td>
										<td class="col-md-9">${order.pinCode}</td>
									</tr>

								</table>
							</div>
							
						</div>

					</div>


				</div>
			</div>



</div>
</div>

			<script src="js/bootstrap.min.js"></script>
</body>
</html>
