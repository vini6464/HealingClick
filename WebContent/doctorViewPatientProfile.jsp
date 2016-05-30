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
<link rel="icon" type="image/x-icon" href="image/healingclick_icon.png" />
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<link href="css/basic.css" rel="stylesheet">
<script src="js/jquery.js" type="text/javascript"></script>
	
<script type="text/javascript" src="js/ajax.js"></script>
<%@ include file="doctorchat.jsp" %>

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

<style type="text/css">
 .btn-block{background-color:#f9f9f9;color:black;padding:5px;margin-top:-2px;}


</style>
<script src="js/jquery.js"></script>
<script>

var FIVE_MINS = 60 * 5 * 1000; /* ms */
var d = "${patient.lastActive}";
var olddate = new Date(d);
if(((new Date) - olddate) < FIVE_MINS)
	{
	$("#active").show();
	$("#notactive").hide();
	}
else
	{
	$("#active").hide();
	$("#notactive").show();
	$("#notactive").html(d);
	}
	


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
				
				}
			}
		});
	});
});

$(function() {
	$("#close").click(function() {
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
				"<div class='col-xs-4 col-md-3 column' style='width:60px;' > <image src='"+d[i].image +"' width='50px' height='50px'></div>"+
				"<div class='col-xs-8 col-md-9 column' style='font-size:0.85em;padding-right:20px;'> "+d[i].content +" <br> <span style='color:grey;font-size:0.8em;padding-top:10px;'>"+d[i].creationDate+"</span></div></div></a></li><li class='divider'> </li>";
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

function getNewRequest(){
					$
							.ajax({
								method : "GET",
								url : 'getRequest.notification',
								datatype : 'json',
								success : function(result) {

									var html = "<li><a href='#'>Requests</a> </li><li class='divider'> </li>";
									var count = 0;

									var d = $.parseJSON(result);
									for ( var i = 0; i < d.length; i++) {
										debugger
										if (d[i].view == 0) {
											count++;
										}
										
										if(d[i].type == 1)
											{
											html = html
											+ '<li><div id="'+d[i].id+'" style="text-align:center;">'
											
											+ '<div class="row"'+
											'style="min-width: 350px; max-width: 400px;">'
											+ '<div class="col-xs-4 col-sm-2 column">'
											+ '<img alt="300x200" src="'+d[i].image+'" width="50px;"'+
						'height="50px;" />'
											+ '</div>'
											+ '<div id="req" style="color:;">'
											+ '<div class="col-xs-4 col-md-4 column">'
											+ '<a href="doctorViewPatient.profile?id='
											+ d[i].id
											+ '" id="">Dr.'
											+ d[i].firstName
											+ '&nbsp; '
											+ d[i].lastName
											+ ' </a> '
											+ '</div>'
											+ '<div class="col-xs-2 col-md-3 column">'
											+ '<a href="#" class="btn btn-success btn-sm"'
											+ 'type="button" style="" onclick="acceptDoctor('
											+ d[i].id
											+ ');">Accept</a>'
											+ '</div>'
											+ '<div class="col-xs-2 col-md-3 column">'
											+ '<a class="btn btn-info btn-warning btn-sm" type="button"'
											+ 'style="width: %;" onclick="declineDoctor('
											+ d[i].id
											+ ');">Decline</a>'
											+ '</div>' + '</div>'
											+ '</div>' 
											+ '</div></li><li class="divider"> </li>';
											}
										else
											{
											html = html
											+ '<li><div id="'+d[i].id+'">'
											
											+ '<div class="row"'+
											'style="min-width: 350px; max-width: 400px;">'
											+ '<div class="col-xs-4 col-sm-2 column">'
											+ '<img alt="300x200" src="'+d[i].image+'" width="50px;"'+
						'height="50px;" />'
											+ '</div>'
											+ '<div id="req" style="color:;">'
											+ '<div class="col-xs-4 col-md-4 column">'
											+ '<a href="doctorViewPatient.profile?id='
											+ d[i].id
											+ '" id="">'
											+ d[i].firstName
											+ '&nbsp; '
											+ d[i].lastName
											+ ' </a> '
											+ '</div>'
											+ '<div class="col-xs-2 col-md-3 column">'
											+ '<a href="#" class="btn btn-success btn-sm"'
											+ 'type="button" style="" onclick="accepted('
											+ d[i].id
											+ ');">Accept</a>'
											+ '</div>'
											+ '<div class="col-xs-2 col-md-3 column">'
											+ '<a class="btn btn-info btn-warning btn-sm" type="button"'
											+ 'style="width: %;" onclick="decline('
											+ d[i].id
											+ ');">Decline</a>'
											+ '</div>' + '</div>'
											+ '</div>' 
											+ '</div></li><li class="divider"> </li>';
											}
										
									}
									
									if (count != 0) {
										$("#requestcount").removeClass("hidden");
										$("#requestcount").html(count);
										$("#request").html(html);
									}
									else
										{
										
										if(html == "")
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
body{font-family: 'Josefin Sans', sans-serif;
background-color: #f6f6f6;}
</style>
</head>
<body>
<div class="se-pre-con"></div>
<div class="container-fluid" >
 <div class="row  text-center" style="background-color:white;">
    <div class="col-xs-5 col-md-2 column ">
     <img class="pull-left" src="image/healingclick_logo.png" alt="logo" style="padding-top:5px;padding-left:10px;"/>
      </div>
    <div class="col-xs-7 col-sm-6 col-md-3 col-md-push-7" style="">
      
       <ul class="nav nright nav-pills">
						<li class="name dropdown pull-right ">
							 <a href="#" data-toggle="dropdown" class="dropdown-toggle" style="color:white;background-color:#00b9bc;margin-right:-15px;"> ${doctor.userName}<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li><a href="settings.login" style="white-space: inherit;">Settings   <span class="glyphicon glyphicon-cog pull-right"></span></a></li>
								<li><a href="doctor.profile" style="white-space: inherit;">Profile <span class="glyphicon glyphicon-user pull-right"></span></a></li>
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
							<ul class="dropdown-menu " id="request" style="width:280px;">
							
							</ul>
						</li>
					</ul>
     
    </div>
    <div class=" col-xs-12 col-sm-12 col-md-7 col-md-pull-3">
	
	
     
       <ul class="nav ncenter nav-pills">
   <c:set var="name" value="${pageContext.request.servletPath}"/>

						<c:set var="page" value="0"/>
						
						<c:if test="${ name == '/doctorhome.jsp'}"> <c:set var="page" value="1"/> </c:if>
						<c:if test="${(name == '/doctorEditProfile.jsp')  || (name == '/doctorProfile.jsp') || (name == '/doctorProfile.jsp')}"><c:set var="page" value="2"/>
                        </c:if>
                        <c:if test="${ name == '/doctorPlanner.jsp'}"> <c:set var="page" value="3"/> </c:if>
                        <c:if test="${(name == '/doctorPrescription.jsp')  || (name == '/doctorViewAllPrescription.jsp')}"><c:set var="page" value="4"/>
                        </c:if>
                         <c:if test="${(name == '/doctorViewAllOrder.jsp')  || (name == '/doctorOrder.jsp') || (name == '/doctorOrderAddress.jsp') || (name == '/doctorOrderConfirm.jsp')}"><c:set var="page" value="5"/>
                        </c:if>
                         <c:if test="${(name == '/doctorCreatePost.jsp')  || (name == '/doctorForum.jsp') || (name == '/doctorViewPost.jsp')}"><c:set var="page" value="6"/>
                        </c:if>
                        <c:if test="${(name == '/doctorSuggestion.jsp')   || (name == '/doctorViewSuggestion.jsp')}"><c:set var="page" value="7"/>
                        </c:if>
						
    <li  <c:if test="${ page == 1}">class="nactive"</c:if>><a href="doctor.home">Home</a></li>
	
	
	
	<li  <c:if test="${ page == 3}">class="nactive" </c:if>><a href="appointment.doctor">Appointment</a></li>	
		
	<li  <c:if test="${ page == 4}">class="nactive" </c:if>><a href="allPrescriptions.doctor">Prescriptions</a></li>				
			
	<li  <c:if test="${ page == 5}">class="nactive" </c:if>><a href="getAllUrgentOrder.doctor">My Orders</a></li>		
							
	<li  <c:if test="${ page == 6}">class="nactive" </c:if>><a href="community.community">Community</a></li>
	
	<li  <c:if test="${ page == 7}">class="nactive" </c:if>><a href="suggestion.suggestion">Suggestion</a></li>	
    
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
							  <c:if test="${patient.deleted == 0}">
							 <c:if test="${connect == 0}">
						<div class="col-md-3 column"><div id="${patient.id}">
							 <input class="btn btn-block btn-xs" type="button" style=";" value="Connect" onclick="add(${patient.id})"></div></div></c:if>
							 
							 <c:if test="${connect == 1}">
						<div class="col-md-3 column">
							 Connection Request has been sent</div></c:if>
							 
							<c:if test="${connect == 2}">
						
						<div class="col-md-3 column">
							 <a href="prescription.doctor?id=${patient.id}" class="btn btn-block btn-xs" type="button" style=";">Write Prescription</a></div>
							 <div class="col-md-3 column">
							 <input class="btn btn-block btn-xs" type="button" style="width:%;" value="Remove From Network" onclick="removeConnection(${patient.id})"> </div>
							  <div class="col-md-3 column">
							 <a class="btn btn-block btn-xs" type="button" style="width:%;" href="viewMedicalTracker.doctor?id=${patient.id}">Medical Tracker</a></div>
							 
							 </c:if>
							</c:if>
							 <div class="col-md-3 column">
							 <a class="btn btn-block btn-xs" type="button" style="width:%;" href=javascript:register_popup("${patient.firstName}&nbsp;${patient.lastName}",${patient.id},2);>Send Message</a></div>
							
							 </div>
							 
							
							 
							 
							 				
				   <br>
				   <br>
				   
				   <c:if test="${patient.privacy == 0}">
<c:set var="view" value="0"/>
</c:if>
<c:if test="${patient.privacy == 1}">
<c:if test="${connect == 2}"><c:set var="view" value="0"/></c:if>
<c:if test="${connect != 2}"><c:set var="view" value="1"/></c:if>
</c:if>
<c:if test="${patient.privacy == 2}">
<c:set var="view" value="1"/>
</c:if>
<c:if test="${view == 0}">
<div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3 column " >	Name</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent">${patient.firstName} ${patient.lastName}</div>
            </div>			 
			<div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3  column pviewplabel">	Gender:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent">${gender}</div>
            </div>	
           	    <c:if test="${connect == 2}">
			<div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3  column pviewplabel">Date Of Birth:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent">${patient.dob}</div>
            </div>	
            </c:if>		 
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
	    <c:if test="${connect == 2}">
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
            </c:if>		 
			<div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3  column pviewplabel">About ${patient.firstName}:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent">${patient.aboutMe}</div>
            </div>
</c:if>
<c:if test="${view == 1}">
<div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3 column " >	Name</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent">${patient.firstName} ${patient.lastName}</div>
            </div>
</c:if>
									
					</div>
					</div>
					</div>
					
					
					
					

				</div>
			</div>






			<script src="js/bootstrap.min.js"></script>
</body>
</html>










