<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="doctorchat.jsp" %>
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
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.9.1.js"></script>
	
<script type="text/javascript" src="js/ajax.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script>
function preventBackButton(){window.history.forward();}
setTimeout("preventBackButton()", 0);
window.onunload=function(){null};
</script>

<style type="text/css">
 .btn-block{background-color:#f9f9f9;color:black;padding:5px;margin-top:-2px;}


</style>
<script src="js/jquery.js"></script>
<style>
  font-family: 'Josefin Sans', sans-serif;
</style>
</head>
<body>
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

<div class="row effect7 text-left">
		<div class="col-md-12" style="background-color:#00b9bc;height:120px;">
			<div class="row">
				<div class="col-xs-5 col-sm-2 col-md-2" style="margin-top:10px;width:135px;">
				
				<ul class="img-list">
									<li>
				
					<img alt="Bootstrap Image Preview" src="${patient.image}" class="img-rounded" style="height:130px;width:130px;" onError="this.onerror=null;this.src='image/d.jpg';"/> 
												</li>
												</ul>
					<p style="width:140px;margin-top:-13px;padding-left:5px;padding:2px;color:white;background-image:
    linear-gradient(
      to right, 
      #00b9bc,#f6f6f6
    );">${patient.firstName} &nbsp; ${patient.lastName}</p>
				</div>
				<div class="col-md-4">
				</div>
				<div class="col-md-4">
				</div>
			</div>
		</div>
	</div>


	<div class="container-fluid" style="margin-top:4%;width:92%;">
	<div class="row clearfix" >
		<div class="col-md-12 column">
		 
			<div class="row clearfix">
			
		
				<div class="col-md-8 column col-md-offset-2 col-sm-offset-1 col-xs-offset-1" style="word-wrap:break-word;">
							<div class="panel panel-default">
						<div class="panel-body">
							  <div class="row">
							     <div class="col-md-3 column"><div id="${patient.id}">
							     <c:if test="${patient.deleted == 0}">
							<a class="btn btn-success btn-block btn-xs" type="button" style="width:%;" href="delete.admin?id=${patient.id}&type=patient">Delete Account</a></c:if></div></div></div>
							 </div>
							 
							 
							 				
				   <br>
				   <br>
				   
			<div class="row pviewpdesc" >
              <div class="col-xs-4 col-sm-3 col-md-3 column" >	Number Of Prescription Received:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent" >${prescriptionCount}</div>
            </div>
            <div class="row pviewpdesc" >
              <div class="col-xs-4 col-sm-3 col-md-3 column" >	Number Of Orders Made:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent" >${orderCount}</div>
            </div>
           
            <div class="row pviewpdesc" >
              <div class="col-xs-4 col-sm-3 col-md-3 column " >	Account Status </div>
             <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent" > <c:if test="${patient.deleted == 1}">Deleted</c:if>
              <c:if test="${patient.deleted == 0}">  <c:if test="${patient.verified == 1}"> Active </c:if> <c:if test="${patient.verified == 0}">Registered</c:if></c:if>
             </div>
            </div>
			<div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3 column " >	Name</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent">${patient.firstName} ${patient.lastName}</div>
            </div>			 
			<div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3  column pviewplabel">	Gender:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent">${gender}</div>
            </div>	
           	    
			<div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3  column pviewplabel">Date Of Birth:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent">${patient.dob}</div>
            </div>	
           
			<div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3  column pviewplabel">Blood Group:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent"><c:if test="${patient.bloodGroup == 1}">A+</c:if>
             <c:if test="${patient.bloodGroup == 2}">A-</c:if>
             <c:if test="${patient.bloodGroup == 3}">B+</c:if>
             <c:if test="${patient.bloodGroup == 4}">B-</c:if>
             <c:if test="${patient.bloodGroup == 5}">O+</c:if>
             <c:if test="${patient.bloodGroup == 6}">O-</c:if>
             <c:if test="${patient.bloodGroup == 7}">AB+</c:if>
             <c:if test="${patient.bloodGroup == 8}">AB-</c:if></div>
            </div>		
	   
			<div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3  column pviewplabel">Address:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent">${patient.landMark}<br>${patient.address1}<br>${patient.address2}<br>${patient.city} - ${patient.pinCode}<br>${patient.state}<br>${patient.country}</div>
            </div>			 
			<div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3  column pviewplabel">Contact Number:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent">${patient.mobile}</div>
            </div>
            <div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3  column pviewplabel">Landline Number:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent">${patient.landLine}</div>
            </div>	
            <div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3  column pviewplabel">Emergency Contacts:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent">${patient.emergency1}<br>${patient.emergency2}</div>
            </div>
         	
			<div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3  column pviewplabel"> Email:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent">${patient.emailId}</div>
            </div>	
           
			<div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3  column pviewplabel">About ${patient.firstName}:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent">${patient.aboutMe}</div>
            </div>						
					</div>
					</div>
					</div>
					
					
					
					

				</div>
			</div>






			<script src="js/bootstrap.min.js"></script>
</body>
</html>










