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
<link href="css/bootstrap.min.css" rel="stylesheet">

<link href="css/style.css" rel="stylesheet">
<script type="text/javascript" src="js/ajax.js"></script>
<script src="js/dropzone.js" type="text/javascript"></script>
<link href="css/dropzone.css" rel="stylesheet">
<link href="css/basic.css" rel="stylesheet">
<link href="https://cdn.datatables.net/1.10.10/css/jquery.dataTables.min.css" rel="stylesheet">
<script src="js/jquery.js" type="text/javascript"></script>	
<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>	
<script src="js/jquery.dataTables.min.js" type="text/javascript"></script>


<style>
  body{font-family: 'Josefin Sans', sans-serif;}
  .admin_nav{background-color:white;}
   .admin_forum{padding:10px;}
</style>
<script type="text/javascript">
$(document).ready(function() {
	$('#example').DataTable();
	  /* $('#example').DataTable( {
	        "pagingType": "full_numbers",
	        "ordering": false,
	        'iDisplayLength': 2,
	        "bLengthChange": false,
	        "bFilter": true,
	    } );  */
} );


function searchOrder()
{
	
	var startDate = document.getElementById("startDate").value;
	var endDate = document.getElementById("endDate").value;
	
	$.ajax({
		method : "GET",
		url : 'searchOrder.admin',
		data: { 
			startDate : startDate,
			endDate : endDate
		},
		success : function(result) {
			if(result!="")
			{
			
			var html = "";
			var d = jQuery.parseJSON(result);
			for ( var i = 0; i < d.length; i++) {
				
				html = html+'<tr><td>'+d[i].id+'</td><td>'+d[i].supplierPharmacyId+'</td><td>'+d[i].supplierPharmacyId+'</td><td>'+d[i].patientId+'</td><td>'+d[i].totalCost+'</td></tr>';	
			}
			html = html+'';
			$("#orderBody").html(html);
			
			}
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
 <div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<nav class="navbar navbar-fixed-top admin_nav" role="navigation" >
				<div class="navbar-header">
					 <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button> <a class="navbar-brand" href="#">LOGO</a>
				</div>
				
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav" style="padding-top:15px;padding-bottom:10px;margin-left:30%;">
					<li><a href="home.admin" style="background-color: ;">Home</a>
					
					<li><a href="order.admin">Order</a></li>
					<li><a href="prescription.admin">Prescription</a></li>
					<li><a href="users.jsp">User</a></li>
					<li><a href="forums.jsp">Community</a></li>	
					<li><a href="complaints.admin">Complaints</a></li>
					</ul>
					
					<ul class="nav navbar-nav navbar-right">
						<li>
							<a href="logout.admin"> <span class="glyphicon glyphicon-off"></span> Log Out</a>
						</li>
					
					</ul>
				</div>
				
			</nav>
		</div>
	</div>
</div>
  
  	<div class="container-fluid" style="margin-top: 7%;">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="row clearfix">
					<div class="col-xs-12 col-sm-12 col-md-12 column">
						
						  <div class="panel panel-default admin_forum">
							<form class="form" action="searchForum.admin" name="form"  method="post" ><!-- onSubmit="return pharmacy1();" -->
					
					 
							Start Date :<input class="" id="startDate" name="startDate" type="date" data-toggle="tooltip" data-placement="top" title="Start Date" placeholder="Start Date" /><span class="error-message" id="date1"></span>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							End Date :<input class="" id="endDate" name="endDate" type="date" data-toggle="tooltip" data-placement="top" title="End Date" placeholder="End Date" /><span class="error-message" id="date2"></span>
						    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								
							User Type: <select name="type">
							
							<option value="1">Doctor</option>
							
							<option value="3">Pharmacy</option>
							
							</select>
						    <br/>
						<input type=submit value="Search">
						</form>
						</div>
						
						<div class="panel panel-default">
							<div class="col-sm-4 column" style="color: red;"></div>
							<c:if test="${not empty posts}">
							<table id="example" class="display" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th>Title</th>
                <th>Created By</th>
                <th>Replies</th>
                <th>Likes</th>
                <th>Created Date</th>
            </tr>
        </thead>
        <tbody>
        
								<c:forEach var="post" items="${posts}">
								<tr>
                <td>
					<a href="getForum.admin?id=${post.id}&type=${type}">${post.name}</a> 
				</td>
               
                <td><c:if test="${type == 2}">
					<a href="getPatient.admin?id=${post.patientId}">${post.patientName}(${post.patientId})</a> 
				</c:if>
												<c:if test="${type == 1}">
					<a href="getDoctor.admin?id=${post.doctorId}">Dr.${post.doctorName}(${post.doctorId})</a> 
				</c:if>
												<c:if test="${type == 3}">
					<a href="getPharmacy.admin?id=${post.pharmacyId}">${post.pharmacyName}(${post.pharmacyId})</a>
				</c:if></td>
                <td>${post.comments}</td>
                
                <td>${post.likes}</td>
                <td>${post.creationDate.getDate()}/${post.creationDate.getMonth()+1}/${post.creationDate.getYear()+1900}</td>
            </tr>
								
								
								</c:forEach>
								
        </tbody>
    </table>
			</c:if>			</div>
						
					</div>


			<br>
		
			
		</div>
</div>
</div>
</div>
  
  
  
  
  
 