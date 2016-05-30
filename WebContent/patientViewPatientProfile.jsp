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
<script type="text/javascript" src="js/ajax.js"></script>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="js/jquery.js"></script>
	<script>
function preventBackButton(){window.history.forward();}
setTimeout("preventBackButton()", 0);
window.onunload=function(){null};
</script>
<script>
	

	$(function() {
		$("#notify").click(function() {
			$.ajax({
				method : "GET",
				url : 'clearNotification.notification',
				success : function(result) {
					
					$("#notificationcount").hide();
				},
				statusCode : {
					500 : function(result) {
						alert(result);
					}
				}
			});
		});
	});
	$(function() {
	$("#friend").click(function() {
		
					$.ajax({
						method : "GET",
						url : 'clearRequest.notification',
						success : function(result) {
							
							$("#requestcount").hide();
						},
						statusCode : {
							500 : function(result) {
								alert(result);
							}
						}
					});

				});
	
	});

	$(document).ready(function() {

		$.ajax({
			method : "GET",
			url : 'getNotification.notification',
			datatype : 'json',
			success : function(result) {
				var html = "";
				var count = 0;
				var d = $.parseJSON(result);
				for ( var i = 0; i < d.length; i++) {
					if (d[i].hasRead == 0) {
						count++;
					}

					html = html + d[i].content;
				}
				if (count != 0) {
					$("#notificationcount").html(count);
				}

				$("#notification").html(html);
			},
			statusCode : {
				500 : function(result) {
					alert(result);
				}
			}
		});

	});

	$(document).ready(function() {

						$.ajax({
									method : "GET",
									url : 'getRequest.notification',
									datatype : 'json',
									success : function(result) {
										var html = "";
										var count = 0;

										var d = $.parseJSON(result);
										for ( var i = 0; i < d.length; i++) {
											
											if (d[i].view == 0) {
												count++;
											}
											
											html = html
													+ '<div id="'+d[i].id+'">'
													+ '<div class="container"'+
					'style="min-width: 350px; max-width: 500px;">'
													+ '<div class="row">'
													+ '<div class="col-sm-2 column">'
													+ '<img alt="300x200" src="'+d[i].image+'" width="60px;"'+
								'height="60px;" />'
													+ '</div>'
													+ '<div id="req" style="color: green; richness: inherit;">'
													+ '<div class="col-md-4 column">'
													+ '<a href="patientViewDoctor.profile?id='
													+ d[i].id
													+ '" id="">'
													+ d[i].firstName
													+ '&nbsp; '
													+ d[i].lastName
													+ ' </a> has sent you request'
													+ '</div>'
													+ '<div class="col-md-3 column">'
													+ '<a href="#" class="btn btn-success btn-block btn-sm"'
													+ 'type="button" style="" onclick="accepted('
													+ d[i].id
													+ ');">Accept</a>'
													+ '</div>'
													+ '<div class="col-md-3 column">'
													+ '<a class="btn btn-info btn-warning btn-sm" type="button"'
													+ 'style="width: %;" onclick="decline('
													+ d[i].id
													+ ');">Decline</a>'
													+ '</div>' + '</div>'
													+ '</div>' + '</div>'
													+ '</div>';
										}
										if (count != 0) {
											$("#requestcount").html(count);
										}

										$("#request").html(html);
									},
									statusCode : {
										500 : function(result) {
											alert(result);
										}
									}
								});

					});
</script>

<script>
function image()
{
	 window.location = 'image.jsp';
	
}
  </script>         
<style type="text/css">

</style>
<title>healingclick</title>
</head>
<body>
	<div class="container-fluid">
		<div class="menu row clearfix" >
			<div class="col-xs-12 col-sm-10 col-md-2 col-lg-2 column">

				<img alt="140x140" src="image/logo.gif" />
			</div>
			<br> <br>

			<div class="col-xs-12 col-sm-10 col-md-10 col-lg-10 column">
				<ul class="nav nav-pills" style="font-weight: lighter;">
					<li><a href="patient.home">Home</a>
					</li>
					<li><a href="patient.profile">Profile</a></li>
					<li><a href="appointment.patient">Appointment</a></li>
					<li><a href="allPrescriptions.patient">Prescriptions</a></li>
					<li><a href="getAllUrgentOrder.patient">My Orders</a></li>
					<li><a href="forum.forum">Community</a></li>
					

					<li class="dropdown pull-right"
						style="background-color: #f5eee8; font-weight: bold;"><a
						href="#" data-toggle="dropdown" class="dropdown-toggle">${patient.userName}<strong
							class="caret"></strong></a>
						<ul class="dropdown-menu">
							<li><a href="settings.login">Settings <span class="glyphicon glyphicon-cog pull-right"></span></a></li>
							<li><a href="signout.login">Logout <span class="glyphicon glyphicon-off pull-right"></span></a></li>
						</ul></li>

					<li class="dropdown pull-right"><a href=""
						data-toggle="dropdown" class="dropdown-toggle"> <span
							class="glyphicon glyphicon-globe" id="notify"><span
								style="color: red;" id="notificationcount"> </span></span>
					</a>
						<ul class="dropdown-menu" id="notification">

						</ul></li>

					<li class="dropdown pull-right"><a href=""
						data-toggle="dropdown" class="dropdown-toggle"> <span
							class="glyphicon glyphicon-user" id="friend"></span> <span
							style="color: red;" id="requestcount"> </span>
					</a>
						<ul class="dropdown-menu" id="request">
						</ul></li>






				</ul>
			</div>
		</div>
	</div>

	<div id="bn"></div>
	<br>

	



	<div class="wrapper" style="margin-top:px;">
		<div class="container-fluid;">

			<div class="row clearfix" style="padding-top: %;">
				<div class="col-sm-12 col-md-12 column">
					<div class="row clearfix">

						<div class="col-xs-8 col-sm-6 col-md-4 column"
							style="padding-left: 6%;">
							<c:if test="${not empty patient1.image}">
								<ul class="img-list">
									<li><img class="alignnone size-thumbnail wp-image-300"
										alt="2010-09-29 15.37.14" src="${patient1.image}"
										
										></li>
								</ul>
							</c:if>
							<c:if test="${empty patient1.image}">
								<ul class="img-list">
									<li><img class="alignnone size-thumbnail wp-image-300"
										alt="2010-09-29 15.37.14" src="image/d.jpg"></li>
								</ul>
							</c:if>
						</div>






						<div class="col-md-2 column"></div>
						<div class="col-xs-10 col-sm-6 col-md-6 column"
							style="margin-top: %;"></div>
					</div>
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
							  </div>			
				   <br>
				   <br>
			 
			<div class="row pviewpdesc">
             <div class="col-xs-4 col-sm-3 col-md-3  column pviewplabel">	Name</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent">${patient1.firstName} ${patient1.lastName}</div>
            </div>	<br>		 
			<div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3 column" >	Gender:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent">${gender}</div>
            </div>	
           	    <c:if test="${connect == 1}">
			<div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3 column" >Date Of Birth:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent">${patient1.dob}</div>
            </div>	
            </c:if>		 
			<div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3 column" >Blood Group:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent"><c:if test="${patient1.bloodGroup == 1}">A+</c:if>
             <c:if test="${patient1.bloodGroup == 2}">A-</c:if>
             <c:if test="${patient1.bloodGroup == 3}">B+</c:if>
             <c:if test="${patient1.bloodGroup == 4}">B-</c:if>
             <c:if test="${patient1.bloodGroup == 5}">O+</c:if>
             <c:if test="${patient1.bloodGroup == 6}">O-</c:if>
             <c:if test="${patient1.bloodGroup == 7}">AB+</c:if>
             <c:if test="${patient1.bloodGroup == 8}">AB-</c:if></div>
            </div>		
	    
			<div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3 column" >Address:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent">${patient1.landMark}<br>${patient1.address1}<br>${patient1.address2}<br>${patient1.city} - ${patient1.pinCode}<br>${patient1.state}<br>${patient1.country}</div>
            </div>			 
			<div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3 column" >Contact Number:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent">${patient1.mobile}</div>
            </div>
            <div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3 column" >Landline Number:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent">${patient1.landLine}</div>
            </div>	
            <div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3 column" >Emergency Contacts:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent">${patient1.emergency1}<br>${patient1.emergency2}</div>
            </div>
         	
			<div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3 column" > Email:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent">${patient1.emailId}</div>
            </div>	
          	 
			<div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3 column" >About ${patient1.firstName}:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent">${patient1.aboutMe}</div>
            </div>		
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
