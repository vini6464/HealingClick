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
				<div class="col-md-2 column"></div>
					<div class="col-md-6 column">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h5 class="panel-title">
									Prescription</h5>
							</div>
							<div class="panel-body">




								<table class="table" style="margin-top: 5%;">
								<tr>
										<td class="field-label col-xs-3 active"><label>Prescription
												Id</label></td>
										<td class="col-md-9">${prescription.id}
											</td>
									</tr>
									<c:if test="${prescription.doctorId ne 0}">
									<tr>
										<td class="field-label col-xs-3 active"><label>Doctor
												Name</label></td>
										<td class="col-md-9"><a href="getDoctor.admin?id=${prescription.doctorId}">Dr .${prescription.doctorName}</a>
											</td>
									</tr>
									</c:if>
									<tr>
										<td class="field-label col-xs-3 active"><label>
												Patient Name </label></td>
										<td class="col-md-9"><a href="getPatient.admin?id=${prescription.patientId}">${prescription.patientName}
											</a></td>
									</tr>
									<c:if test="${not empty prescription.bp}">
									<tr>
										<td class="field-label col-xs-3 active"><label>
												BP Level</label></td>
										<td class="col-md-9">${prescription.bp}(${prescription.bpStatus})</td>
									</tr>
									</c:if>
									<c:if test="${prescription.sugar ne 0}">
									<tr>
										<td class="field-label col-xs-3 active"><label>
												Sugar level</label></td>
										<td class="col-md-9">${prescription.sugar}(${prescription.sugarStatus})</td>
									</tr>
									</c:if>
									<c:if test="${prescription.cholesterol ne 0}">
									<tr>
										<td class="field-label col-xs-3 active"><label>
												Cholestrol Level</label></td>
										<td class="col-md-9">${prescription.cholesterol}(${prescription.cholesterolStatus})</td>
									</tr>
									</c:if>
									<c:if test="${prescription.bmi ne 0}">
									<tr>
										<td class="field-label col-xs-3 active"><label>
												Body mass index(BMI) Level</label></td>
										<td class="col-md-9">${prescription.bmi}(${prescription.bmiStatus})</td>
									</tr>
									</c:if>
									<tr>
										<td class="field-label col-xs-3 active"><label>Symptoms</label>
										</td>
										<td class="col-md-9"><c:if
												test="${not empty prescription.sName1 }">${prescription.sName1}</c:if>
											<c:if test="${not empty prescription.sName2 }">,${prescription.sName2}</c:if>
											<c:if test="${not empty prescription.sName3 }">,${prescription.sName3}</c:if>
											<c:if test="${not empty prescription.sName4}">,${prescription.sName4}</c:if>
											<c:if test="${not empty prescription.sName5 }">,${prescription.sName5}</c:if>
										</td>
									</tr>

									<tr>
										<td class="field-label col-xs-3 active"><label>Diseases</label>
										</td>
										<td class="col-md-9"><c:if
												test="${not empty prescription.dName1}">${prescription.dName1}</c:if>
											<c:if test="${not empty prescription.dName2}">,${prescription.dName2}</c:if>
											<c:if test="${not empty prescription.dName3}">,${prescription.dName3}</c:if>
											<c:if test="${not empty prescription.dName4}">,${prescription.dName4}</c:if>
											<c:if test="${not empty prescription.dName5}">,${prescription.dName5}</c:if>
										</td>
									</tr>
									<c:if test="${not empty prescription.prescriptionPath }">
										<tr>
											<td class="field-label col-xs-3 active"><label>Prescription
													Image </label></td>
											<td class="thumbnail"><img alt="300x200"
												src="${prescription.prescriptionPath}" /></td>
										</tr>
									</c:if>
									<tr>
										<td class="field-label col-xs-3 active"><label>
												Physical Checkup Status </label></td>
										<td class="col-md-9"><c:if test="${prescription.checkup == 1}">Yes</c:if>
											<c:if test="${prescription.checkup == 2}">No</c:if></td>
									</tr>
									<tr>
										<td class="field-label col-xs-3 active"><label>
												Suggestions </label></td>
										<td class="col-md-9">${prescription.suggestion}</td>
									</tr>
<tr>
										<td class="field-label col-xs-3 active"><label>
												Prescribed Date </label></td>
										<td class="col-md-9">${prescription.prescribedDate.getDate()}/${prescription.prescribedDate.getMonth()+1}/${prescription.prescribedDate.getYear()+1900}  ${prescription.prescribedDate.getHours()}:${prescription.prescribedDate.getMinutes()}</td>
									</tr>
								</table>

								<table class="table" style="margin-top: 5%;">
									<tr>
										<td class="field-label col-xs-6 active"><label>Medicine
												name </label></td>
										<td class="field-label col-xs-3 active"><label>Quantity</label>
										</td>
										<td class="field-label col-xs-3 active"><label>Dosage</label>
										</td>
										<td class="field-label col-xs-3 active"><label>Time</label>
										</td>
									</tr>
									<c:forEach var="medicine" items="${prescription.medicines}">
										<tr>
											<td class="col-md-6">${medicine.name}</td>
											<td class="col-md-3">${medicine.quantity}</td>
											<td class="col-md-3"><c:if
													test="${medicine.morning ne 0}">Morning<br />
												</c:if> <c:if test="${medicine.afternoon ne 0}">AfterNoon<br />
												</c:if> <c:if test="${medicine.night ne 0}">Night<br />
												</c:if></td>
												<td class="col-md-3"><c:if
													test="${medicine.morning ne 0}">${medicine.mt}<br />
												</c:if> <c:if test="${medicine.afternoon ne 0}">${medicine.at}<br />
												</c:if> <c:if test="${medicine.night ne 0}">${medicine.nt}<br />
												</c:if></td>
										</tr>
									</c:forEach>

								</table>
							</div>
						</div>
</div>
				</div>






				<script src="js/bootstrap.min.js"></script>
</body>
</html>