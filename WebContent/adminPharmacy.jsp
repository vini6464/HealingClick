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
<link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.9.1.js"></script>
	
<script type="text/javascript" src="js/ajax.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="js/jquery.js"></script>

<style>
.btn-block{background-color:#f9f9f9;color:black;padding:5px;margin-top:-2px;}

</style>
</head>
<body>
	<body>
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
<div class="container-fluid">
<div class="row effect7 text-left">
		<div class="col-md-12" style="background-color:#00b9bc;height:120px;">
			<div class="row">
				<div class="col-xs-5 col-sm-2 col-md-2" style="margin-top:10px;width:135px;">
				
				<ul class="img-list">
									<li>
				
					<img alt="Bootstrap Image Preview" src="${pharmacy.image}" class="img-rounded" style="height:130px;width:130px;" onError="this.onerror=null;this.src='image/d.jpg';" /> 
												</li>
												</ul>
					<p style="width:140px;margin-top:-13px;padding-left:5px;padding:2px;color:white;background-image:
    linear-gradient(
      to right, 
      #00b9bc,#f6f6f6
    );">${pharmacy.pharmacyName}</p>
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
			
		
				<div class="col-md-8 column">
							<div class="panel panel-default">
							
							<div class="panel-body">
							  <div class="row">
							  <c:if test="${pharmacy.deleted == 0}">
							 <c:if test="${pharmacy.verified == 1}">
						<div class="col-md-3 column"><div id="${pharmacy.id}">
							<a class="btn btn-success btn-block btn-xs" type="button" style="width:%;" href="delete.admin?id=${pharmacy.id}&type=pharmacy">Delete Account</a></div></div></c:if>
							 
							<c:if test="${pharmacy.verified == 0}">
						
						
							
												<div class="col-md-3 column">
												<div id="${pharmacy.id}" style="color:green;richness: inherit;">
													<input class="btn btn-info btn-warning btn-sm" type="button"
														style="width: %;" onclick="activatePharmacy(${pharmacy.id});" value="Activate">
												</div>
											</div>
							 <div class="col-md-3 column">
							<a class="btn btn-success btn-block btn-xs" type="button" style="width:%;" href="delete.admin?id=${pharmacy.id}&type=pharmacy">Delete Account</a></div></c:if></c:if></div>
							
			</div>				
				   <br>
				   <br>	
				   
			<div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3  column pviewplabel">	Rating</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent">${pharmacy.rating}</div>
            </div>
            
            <div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3  column pviewplabel">	Number Of Orders Received:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent">${orderReceived}</div>
            </div>
			<div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3  column pviewplabel">	Number Of Orders Made:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent">${orderMade}</div>
            </div>	<br>		 
	    <div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3  column pviewplabel">Account Status:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent"><c:if test="${pharmacy.deleted == 1}">Deleted</c:if>
              <c:if test="${pharmacy.deleted == 0}">  <c:if test="${pharmacy.verified == 1}"> Active </c:if> <c:if test="${pharmacy.verified == 0}">Registered</c:if></c:if></div>
            </div>
            
			<div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3  column pviewplabel">	Pharmacy Name</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent">${pharmacy.pharmacyName}</div>
            </div>	<br>		 
	    <div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3  column pviewplabel">	Delivery Charges:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent"> ${pharmacy.deliveryCharge}Rs.</div>
            </div>
            
            <div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3  column pviewplabel">	Discount:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent"> ${pharmacy.discount}%</div>
            </div>
			<div class="row pviewpdesc">
             <div class="col-xs-4 col-sm-3 col-md-3  column pviewplabel">Pharmacy Address:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent">${pharmacy.landMark}<br>${pharmacy.address1}<br>${pharmacy.address2}<br>${pharmacy.city} - ${pharmacy.pinCode}<br>${pharmacy.state}<br>${pharmacy.country}</div>
            </div>			 
			<div class="row pviewpdesc">
             <div class="col-xs-4 col-sm-3 col-md-3  column pviewplabel">Contact Number:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent">${pharmacy.mobile}</div>
            </div>
            <div class="row pviewpdesc">
             <div class="col-xs-4 col-sm-3 col-md-3  column pviewplabel">Landline Number:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent">${pharmacy.landLine}</div>
            </div>			 		
	   
			<div class="row pviewpdesc">
             <div class="col-xs-4 col-sm-3 col-md-3  column pviewplabel"> Email:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent">${pharmacy.emailId}</div>
            </div>	
           	 
			<div class="row pviewpdesc">
             <div class="col-xs-4 col-sm-3 col-md-3  column pviewplabel">About ${pharmacy.pharmacyName}:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent">${pharmacy.aboutMe}</div>
            </div>						
					</div>
					
					
					<div class="panel" style="min-width: 350px;">
					
					<c:if test="${not empty reviews}">
							<c:forEach var="review" items="${reviews}">
							<div class="panel-heading" style="background-color:white;" >
								<div class="panel-title"><h5>
								<c:if test="${review.type == 1}"><a href="getDoctor.admin?id=${review.reviewerId}">Dr . ${review.reviewerName}</a></c:if>
								
								<c:if test="${review.type == 2}"><a href="getPatient.admin?id=${review.reviewerId}">${review.reviewerName}</a></c:if>
								<c:if test="${review.type == 3}"><a href="getPharmacy.admin?id=${review.reviewerId}"> ${review.reviewerName}</a></c:if>
								<span class="pull-right glyphicon glyphicon-list-alt" style="color:;"></span></h5></div>
								<!--hr align="left"  style="margin-top:;border:1px solid #ffa500;width:50px;"--->
								
							</div>
							<div class="panel-body">
								<div class="row">
									<div class="col-xs-6 col-sm-6 col-md-6 column">
										<ul style="list-style: none; padding: 2px;">

											<li>Rating :</li>
											<li>Comment :</li>
											
										</ul>
									</div>

									<div class="col-xs-6 col-sm-6 col-md-6 column">
										<ul style="list-style: none; padding: 2px;">
											<li>${review.rating}</li>
											<li>${review.comment}</li>
								
										</ul>
									</div>
								</div>
							</div>	
							</c:forEach>
							</c:if>
						</div>
					
					</div>
					
		
					
					</div>
				</div>
			</div>






			<script src="js/bootstrap.min.js"></script>
</body>
</html>










