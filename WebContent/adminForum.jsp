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
<script>
function deletePost(id , type)
{
	$.ajax({
		method : "POST",
		url : 'deletePost.admin',
		data: { 
			id : id,
			type : type
		},
		success : function(result) {
			window.location = "home.admin";
		},
		statusCode : {
			500 : function(result) {
				
			}
		}
	});	 
}

</script>
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
									
	<div class="row clearfix">
	    
		<div class="col-xs-2 col-sm-2 col-md-1 column">
			<img class="img-thumbnail"alt="140x140" src="image/d.jpg" / style="width:90px;height:80px;">
		</div>
		<div class="col-xs-7 col-sm-7 col-md-10 column">
			<h3>
				<a href="#" style="text-decoration:none;">${post.name}</a><a href="javascript:deletePost(${post.id} , ${type});" class="pull-right"><input type="button" class="btn btn-default btn-xs btn-info" value="Delete Post"></a></h3>
				<hr style="margin-top:-8px;margin-bottom:-8px;height:1px; border: none; color: #c9ead2; background-color: #337AB7;"><h3 class="pull-right"><small>Created by</small> <c:if test="${type == 2}">
					<a href="getPatient.admin?id=${post.patientId}">${post.patientName}</a> 
				</c:if>
												<c:if test="${type == 1}">
					<a href="getDoctor.admin?id=${post.doctorId}">Dr.${post.doctorName}</a> 
				</c:if>
												<c:if test="${type == 3}">
					<a href="getPharmacy.admin?id=${post.pharmacyId}">${post.pharmacyName}</a>
				</c:if></a></h3>
				
			
		</div>
		
	    
	</div>
					</h3>
				</div>
				<div class="panel-body">
					${post.content}<br>
					
					<c:if test="${not empty post.forumImage}">
										<img alt="300x200" height=300 width=300 src="${post.forumImage}" />
										
									</c:if>
					
					
				</div>
				<div class="panel-footer">
				<div class="row">

			<div class="pull-right">
			
				
						<span class="glyphicon glyphicon-thumbs-up"></span>   ${post.likes} Likes
					<span class="glyphicon glyphicon-pencil"  style="padding-left:10px;"></span>  ${post.comments} Replies 	
                </div>
				 
		  </div>
				</div>
			</div>
			<c:forEach var="comment" items="${post.comment}">
			<div class="row clearfix">
	    
		<div class="col-xs-7 col-sm-7 col-md-10 column-active">
			<h3>
				<c:if test="${type == 2}">
					<a href="getPatient.admin?id=${post.patientId}" style="text-decoration:none;" >${post.patientName}</a> 
				</c:if>
												<c:if test="${type == 1}">
					<a href="getDoctor.admin?id=${post.doctorId}" style="text-decoration:none;">Dr.${post.doctorName}</a> 
				</c:if>
												<c:if test="${type == 3}">
					<a href="getPharmacy.admin?id=${post.pharmacyId}" style="text-decoration:none;">${post.pharmacyName}</a>
				</c:if><small>Commented As </small> ${comment.comment}</h3>
				
				
				
				<hr>
				
			
		</div>	
		</div>
		</c:forEach>
	</div>
</div>
	
	</div>

  </body>
</html>