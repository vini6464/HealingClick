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
 <link rel="icon" type="image/x-icon" href="image/healingclick_icon.png" />
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>HealingClick</title>

<!-- Bootstrap -->
<script src="js/jquery.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<script type="text/javascript" src="js/ajax.js"></script>
<script src="js/dropzone.js"></script>
<link href="css/dropzone.css" rel="stylesheet">
<link href="css/basic.css" rel="stylesheet">
<link href="css/font-awesome.min.css" rel="stylesheet">

	<script>
function preventBackButton(){window.history.forward();}
setTimeout("preventBackButton()", 0);
window.onunload=function(){null};
</script>
<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
})(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

ga('create', 'UA-77164718-1', 'auto');
ga('send', 'pageview');

</script>

<script src="http://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.2/modernizr.js"></script>
<script type='text/javascript' src='js/loadImg.js'></script>
<script type='text/javascript'>
    $(function(){
        $('img').imgPreload();
    })
</script>


	<script>
function preventBackButton(){window.history.forward();}
setTimeout("preventBackButton()", 0);
window.onunload=function(){null};

//paste this code under the head tag or in a separate js file.
// Wait for window load
$(window).load(function() {
	// Animate loader off screen
	$(".se-pre-con").fadeOut("slow");;
});
</script>
<style>
  .images ul li img {
	width: 400px;
	height: 266px;
}
.images ul li {
	display: inline-block;
}
/* Paste this css to your style sheet file or under head tag */
/* This only works with JavaScript, 
if it's not present, don't show loader */
.no-js #loader { display: none;  }
.js #loader { display: block; position: absolute; left: 100px; top: 0; }
.se-pre-con {
	position: fixed;
	left: 0px;
	top: 0px;
	width: 100%;
	height: 100%;
	z-index: 9999;
	background: url(image/Preloader_2.gif) center no-repeat #fff;
}
</style>

<script>
	$(function() {
		$("#notify").click(function() {
			
			$.ajax({
				method : "GET",
				url : 'clearNotification.notification',
				success : function(result) {
					
					$("#notificationcount").addClass("hidden");
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
		$("#close").click(function() {
			location.reload(true);
		});
		$("#save").click(function() {
			location.reload(true);
		});
	});
	
	$(function() {
	$("#friend").click(function() {
		
					$.ajax({
						method : "GET",
						url : 'clearRequest.notification',
						success : function(result) {
							$("#requestcount").addClass("hidden");
						},
						statusCode : {
							500 : function(result) {
								alert(result);
							}
						}
					});

				});
	
	});

	function getNewNotification() {
		
		$.ajax({
			method : "GET",
			url : 'getNotification.notification',
			datatype : 'json',
			success : function(result) {
				var html = "<li><a href='#'>Notifications</a> </li><li class='divider'> </li>";
				var count = 0;
				var d = $.parseJSON(result);
				for ( var i = 0; i < d.length; i++) {
					if (d[i].hasRead == 0) {
						count++;
					}

					html = html + "<li><a href='#'><div class='row' style='padding-left:5px;'>"+
					"<div class='col-xs-4 col-md-3 column' style='width:60px;'> <image src='"+d[i].image +"' width='50px' height='50px'></div>"+
					"<div class='col-xs-8 col-md-9 column' style='color:;font-size:0.85em;padding-right:10px;'> "+d[i].content +" <br> <span style='color:grey;font-size:0.8em;padding-top:10px;'>"+d[i].creationDate+"</span></div></div></a></li><li class='divider'> </li>";
				}
				if (count != 0) {
					$("#notificationcount").removeClass("hidden");
					$("#notificationcount").html(count);
				}
				else
					{
					
					}
				$("#notification").html(html);
			},
			statusCode : {
				500 : function(result) {
					alert(result);
				}
			}
		});

	}

	function getNewRequest() {

						$.ajax({
									method : "GET",
									url : 'getRequest.notification',
									datatype : 'json',
									success : function(result) {
										var html = "<li><a href='#'>Requests</a> </li><li class='divider'> </li>";
										var count = 0;

										var d = $.parseJSON(result);
										for ( var i = 0; i < d.length; i++) {
											
											if (d[i].view == 0) {
												count++;
											}
											
											html = html
											+ '<li>'
											
											+ '<div class="row"'+
											'style="padding-left:5px;">'
											+ '<div class="col-xs-4 col-md-3 column" style="width:60px;">'
											+ '<img alt="300x200" src="'+d[i].image+'" width="50px;"'+
						'height="50px;" />'
											+ '</div>'
											+ ''
											+ '<div class="col-xs-8 col-md-9 column" style="font-size:0.85em;padding-right:20px;">'
											+ '<a href="patientViewDoctor.profile?id='
											+ d[i].id
											+ '" >'
											+ d[i].firstName
											+ '&nbsp; '
											+ d[i].lastName
											+ ' </a>  has sent you request'
											+ ''
											+ '<br/><br/>'
											+ '<a href="#" class="btn btn-success btn-sm"'
											+ 'type="button" style="" onclick="accepted('
											+ d[i].id
											+ ');">Accept</a>                   '
											+ '<a class="btn btn-info btn-warning btn-sm" type="button"'
											+ 'style="width: %;" onclick="decline('
											+ d[i].id
											+ ');">Decline</a>'
											+ '</div>' + '</div>'
											+ '' 
											+ '</li><li class="divider"> </li>';
										}
										if (count != 0) {
											$("#requestcount").removeClass("hidden");
											$("#requestcount").html(count);
											$("#request").html(html);
											
										}
										else
											{
											
											if(html == "<li><a href='#'>Requests</a> </li><li class='divider'> </li>")
											{
											$("#request").html("<li> <a href='#'>No Request</a></li>");
											}
										else
											{
											$("#request").html(html);
											}
											}
									},
									statusCode : {
										500 : function(result) {
											alert(result);
										}
									}
								});

					}
	
	//Call the yourAjaxCall() function every 1000 millisecond
	setInterval("getNewRequest()",1000 * 60 );
	setInterval("getNewNotification()",1000 * 60);
	


	$(document).ready(function() {
		
		getNewRequest();
		getNewNotification();


	});
</script>
<style>
 body{font-family: 'Josefin Sans', sans-serif;} 
 
 .nav_container{
  width:95%;
 }
 .nav>li>a {
   border-radius:0px;
}
 
 .nav>li>a:focus, .nav>li>a:hover {
    text-decoration: none;
    border-radius: 0px;
    background-color:transparent !important;
  
}
 
 @media(max-width:1025px){
 
 .nav>li>a {
    position: relative;
    display: block;
    padding: 10px 12px;
    font-size: 13px;
}
 
 }
 
 @media(max-width:400px){
  .nav_container{
     width: 100%;
    padding: 0px;
}

.nav>li>a {
    position: relative;
    display: block;
    padding: 8px 10px;
    font-size: 12px;
}
 }
</style>
</head>
<body>
<div class="se-pre-con"></div>
<div class="container-fluid navbar-fixed-top white" style="box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0),0 2px 10px 0 rgba(0, 0, 0, 0.05);">
 <div class="container nav_container">
  <div class="row  text-center">
    <div class="col-xs-4 col-sm-3 col-md-2 column ">
  	<img class="pull-left" src="image/healingclick_logo.png" alt="logo" style="padding-top:5px;padding-left:10px;"/>
    </div>
    <div class="col-xs-8 col-sm-9 col-md-3 col-md-push-7" style="">
      
       <ul class="nav nright nav-pills">
						<li class="name dropdown pull-right ">
							 <a href="#" data-toggle="dropdown" class="dropdown-toggle" style="color:white;background-image:linear-gradient(
      to top, 
      #00b9bc,#66d5d6
    );margin-right:-15px;"> ${patient.userName}<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li><a href="settings.login" style="white-space: inherit;">Settings   <span class="glyphicon glyphicon-cog pull-right"></span></a></li>
							<li><a href="patient.profile" style="white-space: inherit;">Profile <span class="glyphicon glyphicon-user pull-right"></span></a></li>
							<li><a href="support.support" style="white-space: inherit;">Support   <span class="glyphicon glyphicon-asterisk pull-right"></span></a></li>
							<li><a href="signout.login" style="white-space: inherit;">Logout <span class="glyphicon glyphicon-off pull-right"></span></a></li>
								
							</ul>
						</li>
						<li class="dropdown pull-right">
							 <a href="#" data-toggle="dropdown" class="dropdown-toggle" ><span class="glyphicon glyphicon-bell" id="notify"></span> <span id="notificationcount" class="hidden"></span></a>
							<ul class="dropdown-menu" id="notification"  >
								
							</ul>
						</li>
						
						<li class="dropdown pull-right">
							 <a href="#" data-toggle="dropdown" class="dropdown-toggle"><span class="glyphicon glyphicon-user" id="friend"></span> <span id="requestcount" class="hidden"></span> </a>
							<ul class="dropdown-menu" id="request" style="width:280px;">
							
							</ul>
						</li>
					</ul>
     
    </div>
    <div class=" col-xs-12 col-sm-12 col-md-7 col-md-pull-3 text-center">
	
	
     
       <ul class="nav ncenter nav-pills text-center">
   <c:set var="name" value="${pageContext.request.servletPath}"/>

						<c:set var="name" value="${pageContext.request.servletPath}"/>

						<c:set var="page" value="0"/>
						
						<c:if test="${ name == '/patienthome.jsp'}"> <c:set var="page" value="1"/> </c:if>
						<c:if test="${(name == '/patientEditProfile.jsp')  || (name == '/patientProfile.jsp')}"><c:set var="page" value="2"/>
                        </c:if>
                        <c:if test="${ (name == '/patientPlanner.jsp') || (name == '/patientDoctorPlanner.jsp')}"> <c:set var="page" value="3"/> </c:if>
                        <c:if test="${(name == '/patientPrescription.jsp')  || (name == '/patientViewAllPrescription.jsp')|| (name == '/patientViewPrescription.jsp')}"><c:set var="page" value="4"/>
                        </c:if>
                         <c:if test="${(name == '/patientViewAllOrder.jsp')  || (name == '/patientOrder.jsp') || (name == '/patientOrderAddress.jsp') || (name == '/patientOrderConfirm.jsp')}"><c:set var="page" value="5"/>
                        </c:if>
                         <c:if test="${(name == '/patientCreatePost.jsp')  || (name == '/patientForum.jsp') || (name == '/patientViewPost.jsp')}"><c:set var="page" value="6"/>
                        </c:if>
                         <c:if test="${(name == '/patientCreateSuggestion.jsp')  || (name == '/patientSuggestion.jsp') || (name == '/patientViewSuggestion.jsp')}"><c:set var="page" value="7"/>
                        </c:if>
						
    <li  <c:if test="${ page == 1}">class="nactive"</c:if>><a href="patient.home">Home</a></li>
	
	<li  <c:if test="${ page == 2}">class="nactive" </c:if>></li>
	
	<li  <c:if test="${ page == 3}">class="nactive" </c:if>><a href="appointment.patient">Appointment</a></li>	
		
	<li  <c:if test="${ page == 4}">class="nactive" </c:if>><a href="allPrescriptions.patient">Medical Tracker</a></li>				
			
	<li  <c:if test="${ page == 5}">class="nactive" </c:if>><a href="getAllUrgentOrder.patient">My Orders</a></li>	
	
	<li  <c:if test="${ page == 7}">class="nactive" </c:if>><a href="suggestion.suggestion">Queries</a></li>			
    
  </ul> 
     
    </div>
 
  </div>
  </div>
  </div>
  <div style="height:40px;"></div>
  <div class="visible-sm" style="height:30px;"></div>
   <div class="visible-xs" style="height:40px;"></div>
   
   
  <div class="container-fluid" style="background-color:#00b9bc;height:120px;">
  <div class="container " style="width:95%;">
  <div class="row effect7 text-left" >
		<div class="col-md-12" >
			<div class="row">
				<div class="col-xs-5 col-sm-2 col-md-2" style="margin-top:10px;width:135px;">
				
				<ul class="img-list">
									<li>
				<c:if test="${not empty patient.image}">
					<img alt="${patient.firstName}" src="${patient.image}"  class="img-rounded" style="height:130px;width:130px;" ondblclick="changeImage();" onError="this.onerror=null;this.src='image/d.jpg';" /> 
					</c:if>
					<c:if test="${empty patient.image}">
					<img alt="${patient.firstName}" src="image/d.jpg" class="img-rounded" style="height:130px;width:130px;" ondblclick="changeImage();" /> 
					</c:if>
					<br> <span
										class="text-content" ondblclick="changeImage();"> <span>Double

												Click To Change Image</span></span>
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
	</div>
	</div>
	<div class="modal fade" id="changeImage" role="dialog" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" id="close" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Update Profile Image</h4>
				</div>
				<div class="modal-body">
					<form action="changeProfileImage.login" class="dropzone" id="my-awesome-dropzone">
						<div class="fallback">
							<input name="file" type="file" />
						</div>
					</form>
					
					<div style="height:30px;">
					<div style="height:8px;"></div>
					<button id="save" type="button" class="btn btn-success pull-right pad_l_r_5"> Save</button>
					</div>
					
				</div>
			</div>

		</div>
	</div>
	<%@ include file="patientchat.jsp" %>