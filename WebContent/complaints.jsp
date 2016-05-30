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
  
	<div class="container-fluid" style="margin-top:4%;width:92%;">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">
									
		<div class="row clearfix" style="margin-top:2%;">
		<div class="container" style="width:85%;">
		
		
		<c:if test="${empty posts}">
		
			
				<div class="row clearfix">
				
				<div class="col-md-1 column"></div>
				<div class="col-md-10 column">
		<div class="panel panel-default">
  <div class="panel-body">
			<h5 class="text-center">
				There are no Questions available </h5></div></div>
				</div>
				<div class="col-md-1 column"></div>
		</div>
		
	
			</c:if>



			<div class="row clearfix">
			<div class="col-md-2 column"></div>
			<div class="col-md-7 column">
				<c:if test="${not empty posts}">
					<c:forEach var="post" items="${posts}">
					
					<div class="panel panel-default">
  <div class="panel-body">
						<div class="col-xs-9 col-sm-9 col-md-9 column">
							<h5 class="post_name">
								<a href="getSupport.admin?id=${post.id}">${post.content}</a>
							</h5>
							<hr class="post-hr"><h5 class="pull-right">
								<small>Asked by</small>
								
								<c:if test="${post.type == 1}">
								<a class="post_by" href="getDoctor.admin?id=${post.otherId}"> ${post.otherName} (Doctor)</a></c:if>
							<c:if test="${post.type == 2}">
								<a class="post_by" href="getPatient.admin?id=${post.otherId}"> ${post.otherName} (Patient)</a></c:if>
							<c:if test="${post.type == 3}">
								<a class="post_by" href="getPharmacy.admin?id=${post.otherId}"> ${post.otherName} (Pharmacy)</a></c:if>
							</h5>
							
						</div>
  					
						<div class="col-xs-3 col-sm-3 col-md-3 column">
							<blockquote class="post_blockquote">
								<p class="replies">
									<span class="glyphicon glyphicon-pencil"></span>
									${post.comments} Replies
								</p>
							</blockquote>
						</div>
					</div>
</div>
		
						
					</c:forEach>
				</c:if>
			</div>

<div class="col-md-3 column"></div>

</div>
</div></div>
</h3>
</div>
</div>
</div>
		</div>
	</div>
	
	
	
	
</div>
  </body>
</html>