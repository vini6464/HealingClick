<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link rel="icon" type="image" href="image/healingclick_icon.png" />
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Google Sign UP Changes Start -->
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
  <script src="https://apis.google.com/js/api:client.js"></script>


<!-- Google Sign UP Changes Ends -->


<title>HealingClick</title>

<!-- Bootstrap -->

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/animate.css" rel="stylesheet">
<script src="js/jquery.js"></script>

<script type="text/javascript" src="js/ajax.js"></script>
<script type="text/javascript" src="js/validation.js"></script>



<script src="js/bootstrap.min.js"></script>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
    #customBtn1 {
      display: inline-block;
      background: white;
      color: #444;
      width: 100px;
      border-radius: 5px;
      border: thin solid #888;
      box-shadow: 1px 1px 1px grey;
      white-space: nowrap;
    }
    #customBtn1:hover {
      cursor: pointer;
    }
    
    #customBtn2 {
      display: inline-block;
      background: white;
      color: #444;
      width: 100px;
      border-radius: 5px;
      border: thin solid #888;
      box-shadow: 1px 1px 1px grey;
      white-space: nowrap;
    }
    #customBtn2:hover {
      cursor: pointer;
    }
    
    span.label {
      font-family: serif;
      font-weight: normal;
    }
    span.icon {
      background: url('image/g-normal.png') transparent 5px 50% no-repeat;
      display: inline-block;
      vertical-align: middle;
      width: 35px;
      height: 42px;
    }
    span.buttonText {
      display: inline-block;
      vertical-align: middle;
      padding-left: 0px;
      padding-right: 0px;
      font-size: 14px;
      font-weight: bold;
      /* Use the Roboto font that is loaded in the <head> */
      font-family: 'Roboto', sans-serif;
    }
  </style>
<style>
@import url(https://fonts.googleapis.com/css?family=Josefin+Sans:600);



.form-control {
	border: none;
}

.f_links {
	color: #fff;
	font-size: 11px;
	padding: 0px 5px;
}

.f_links:hover {
	color: #fff;
	font-size: 11px;
}

.modal-header {
	background-color: #00b9bc;
	color: #fff;
}

.btn.active.focus, .btn.active:focus, .btn.focus, .btn:active.focus,
	.btn:active:focus, .btn:focus {
	outline: none;
	outline: 0px auto -webkit-focus-ring-color;
	outline-offset: -2px;
}

.error-message {
	color: rgba(183, 36, 36, 0.73);
	font-size: 10px;
	padding-bottom: 0px;
}

body {
	font-family: 'Josefin Sans', sans-serif;
	letter-spacing: 1px;
	background: url(image/healingclick_home.png) no-repeat center center
		fixed;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
	background-color: #f6f6f6;
	
}

.tooltip {
	font-family: 'Josefin Sans', sans-serif;
	
}

.footer {
	text-align: center;
}

.footer a {
	color: #53B2C8;
}

#bbb {
	font-size: 1em;
	background-color: #00b9bc;
	color: #fff;
	text-align: left;
	-webkit-transition: all 0.5s;
	-moz-transition: all 0.5s;
	transition: all 0.5s;
	opacity:0.7;
	border-radius:5px;
}

a:hover, a:focus, a:active {
	text-decoration: none !important;
}

#bbb:hover {
	-webkit-transition: all 0.5s;
	-moz-transition: all 0.5s;
	transition: all 0.5s;
	background-color: #00b9bc;
	color: white;
	opacity: 0.5;
	text-align: right;
}

.form-control:focus {
	/* border-color: #66afe9; */
	outline: 0;
	/* -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,233,.6); */
	box-shadow: inset 0 1px 1px rgba(0, 185, 188, 0.12), 0 0 8px
		rgba(0, 185, 188, 0.16);
}

.form-control {
	background-color: rgba(255, 255, 255, 1);
}

.form-control:hover, inset {
	border-botom: 2px solid white;
}

.panel-default {
	background-color: rgba(255, 255, 255, 0.8);
	border: none;
	margin: 8px auto;
	max-width: 400px;
}

.panel-group .panel-heading+.panel-collapse>.list-group, .panel-group .panel-heading+.panel-collapse>.panel-body {
    border-top: none;
    background-color:rgba(255,255,255,0.3);
}

.panel-title{
text-align: center;
 font-weight: bold;
  letter-spacing: 2px;
 color:#000;
}

.panel-group .panel {
    margin-bottom: 0;
    border-radius: 0px;
}


.checkbox input[type=checkbox], .checkbox-inline input[type=checkbox], .radio input[type=radio], .radio-inline input[type=radio] {
    position: absolute;
    margin-top: 4px \9;
    margin-left: -20px;
    cursor: pointer;
}

@media ( max-width :360px) {
	.xs_small {
		font-size: 13px !important;
		padding-left: 7px;
	}
}

@media ( max-width :340px) {
	.xs_small {
		font-size: 12px !important;
		padding-left: 10px;
	}
}
</style>

<!-- <script type="text/javascript" src="//platform.linkedin.com/in.js">
    api_key: 78a8x6styqzpzb
    authorize: true
    onLoad: onLinkedInLoad
</script> -->

<!-- <script type="text/javascript">
    
    // Setup an event listener to make an API call once auth is complete
    function onLinkedInLoad() {
        IN.Event.on(IN, "auth", getProfileData);
    }

    // Handle the successful return from the API call
    function onSuccess(data) {
        console.log(data);
    }

    // Handle an error response from the API call
    function onError(error) {
        console.log(error);
    }

    // Use the API call wrapper to request the member's basic profile data
    function getProfileData() {
        IN.API.Raw("/people/~").result(onSuccess).error(onError);
    }

</script> -->


<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
})(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

ga('create', 'UA-77164718-1', 'auto');
ga('send', 'pageview');

</script>

<script type="text/javascript">

var googleUser = {};
var startApp = function() {
  gapi.load('auth2', function(){
    // Retrieve the singleton for the GoogleAuth library and set up the client.
    auth2 = gapi.auth2.init({
      client_id: '473089701112-5scm033r5b0889igb3i8ooa8gp5r5g69.apps.googleusercontent.com',
      cookiepolicy: 'single_host_origin',
      // Request scopes in addition to 'profile' and 'email'
      //scope: 'additional_scope'
    });
    attachSignin(document.getElementById('customBtn1'),document.getElementById('customBtn2'));
  });
};

function attachSignin(element,elem) {
  console.log(element.id);
  auth2.attachClickHandler(element, {},
      function(googleUser) {
	  var profile = googleUser.getBasicProfile();
	  checkUser(profile);
      }, function(error) {
        alert(JSON.stringify(error, undefined, 2));
      });
  auth2.attachClickHandler(elem, {},
	      function(googleUser) {
		  var profile = googleUser.getBasicProfile();
		  checkUser(profile);
	      }, function(error) {
	        alert(JSON.stringify(error, undefined, 2));
	      });
}

	
function checkUser(profile) {
	var id = profile.getId();
	var name = profile.getName();
	var mail = profile.getEmail();
	  console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
	  console.log('Name: ' + profile.getName());
	  console.log('Image URL: ' + profile.getImageUrl());
	  console.log('Email: ' + profile.getEmail());
	  $.ajax({
			method : "GET",
			url : 'checkGoogle.notification',
			data: { 
				id : id,
				name : name,
				mail : mail
				
			},
			success : function(result) {
				if(result!="")
					{
					window.location.href = result;
					}

			},
			statusCode : {
				500 : function(result) {
					
				}
			}
		});
	  
	 
	}



$(document).ready(function() {
	$("#customBtn1").hide();
	$("#customBtn2").hide();
	
		$.ajax({
			method : "GET",
			url : 'checkLogin.notification',
			success : function(result) {
				if(result!="")
					{
					window.location.href = result;
					}

			},
			statusCode : {
				500 : function(result) {
					
				}
			}
		});
});

$(document).ready(function(){

    $('[data-toggle="tooltip"]').tooltip();
    var mobile="";
    var type="";

});

function changePassword()
{
	
	$("#changePassword").modal();	
	
}

function showGoogleSignIn(id)
{
	if(id == 2){
		$("#customBtn1").show();
	}else{
		$("#customBtn1").hide();
	}
}

function showGoogleSignUp(id)
{
	if(id == 2){
		$("#customBtn2").show();
	}else{
		$("#customBtn2").hide();
	}
	
}

</script>

<script
	src="http://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.2/modernizr.js"></script>
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
.no-js #loader {
	display: none;
}

.js #loader {
	display: block;
	position: absolute;
	left: 100px;
	top: 0;
}

.se-pre-con {
	position: fixed;
	left: 0px;
	top: 0px;
	width: 100%;
	height: 100%;
	z-index: 9999;
	background: url(image/Preloader_2.gif) center no-repeat #fff;
}

.alert-success span {
	color: #00b9bc;
}

.alert-success {
	color: rgba(0, 150, 136, 0.85);
	background-color: rgba(255, 255, 255, 0.9);
	border-color: none;
}

.alert-danger {
	color: #a94442;
	background-color: rgba(242, 222, 222, 0.86);
	border: none;
}

.alert {
	padding: 12px;
	margin-bottom: 12px;
	border: 1px solid transparent;
	border-radius: 1px;
	max-width: 400px;
	margin: auto;
}
</style>
</head>
<body>
	<div class="se-pre-con"></div>



	<div class="container-fluid full_content navbar-fixed-top"
		style="background-color: rgba(255, 255, 255, 0.8); border-bottom: 2px solid #00b9bc;">

		<div class="container">
			<div class="row">
				<div class="col-sm-4 col-md-4 column animated fadeIn">
					<!-- h1 class="header">HEALINGCLICK <small style="color:white;">EMPOWERING WELLNESS</small></h1> -->

					<img src="image/healingclick.png" width="100%" height="70"
						style="min-width:300px;max-width: 350px; padding: 5px 0px;" />
				</div>
				<div class="col-sm-4 col-md-4 column"></div>
				<div class="col-md-4 column"></div>
			</div>
		</div>
	</div>

	<div class="container">
		<noscript>

			<h4
				style="background-color: rgba(255, 255, 255, 0.5); padding: 20px; text-align: center; display: block;">JavaScript
				is not enabled, please check your browser settings.</h4>
		</noscript>
	</div>


	<div class="container">
		<div class="row">
			<div class="visible-xs" style="height:30px;"></div>
			<div class="col-xs-12 col-sm-3 col-md-4 column text-center" style="">
			
		
			
			
      		 </div>
			<div
				class="col-xs-12 col-sm-6 col-md-4 column text-center animated fadeIn"
				style="text-align: center; margin-top: 18%;">







				<c:if test="${not empty error}">
					<div class="alert alert-danger fade in animated slideInUp">
						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						<span class="glyphicon glyphicon-remove"></span> ${error}
					</div>
				</c:if>
				<c:if test="${not empty msg}">

					<div class="alert alert-success fade in animated fadeInDown">
						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						<span class="glyphicon glyphicon-ok"></span> ${msg}
					</div>

				</c:if>





				<div class="panel-group" id="accordion" role="tablist"
					aria-multiselectable="true">
					<div class="panel panel-default">
						<a data-toggle="collapse" data-parent="#accordion"
									href="#collapseOne" aria-expanded="true"
									aria-controls="collapseOne">
						<div class="panel-heading" role="tab" id="headingOne">
						
							<h4 class="panel-title">
								
								 SIGN IN<br>
								
							</h4>
						</div>
						</a>
						<div id="collapseOne" class="panel-collapse collapse in"
							role="tabpanel" aria-labelledby="headingOne">
							<div class="panel-body">






								<form role="form" action="login.login" style="padding: 10px;"
									method="post">
									<div class="row clearfix xs_small"
										style="margin-top: -10px; position: relative; top: -10px;">
										<div class="col-xs-4 col-sm-4 column xs_small"
											style="padding-left:; text-align: center">


											<label class="radio"> <input type="radio" name="type"
												data-toggle="radio" id="optionsRadios1" value="1" onclick="showGoogleSignIn(1)"
												
												 checked>
												<small style="letter-spacing: 1px;" data-toggle="tooltip" data-placement="top"
												title="Select this if u are a Doctor">Doctor</small>
											</label>
										</div>
										<div class="col-xs-4 col-sm-4 column"
											style="margin-left: px; text-align: center">
											<label class="radio"> <input type="radio" name="type"
												data-toggle="radio" id="optionsRadios1" value="2" onclick="showGoogleSignIn(2)"> 
												<small
												style="color:; letter-spacing: 1px;" data-toggle="tooltip" data-placement="top"
												title="Select this if u are a Patient">Patient</small>
											</label>

										</div>
										<div class="col-xs-4 col-sm-4 column"
											style="margin-left: px; text-align: center; width: px;">

											<label class="radio"> <input type="radio" name="type"
												data-toggle="radio" id="optionsRadios1" value="3" onclick="showGoogleSignIn(3)"> <i></i><small
												style="letter-spacing: 1px;" data-toggle="tooltip" data-placement="top"
												title="Select this if u are a Pharmacist">Pharmacist</small>
											</label>
										</div>
									</div>
									<div class="row clearfix">
										<div class="col-xs-12">
											<div class="form-group">
												<input type="text" class="form-control" name="email"
													id="exampleInputEmail1" placeholder="Email/Username"
													data-toggle="tooltip" data-placement="top"
												title="Enter your name or email" 
													/>
											</div>
											<div class="form-group" style="margin-top: -10px;">
												<input type="password" name="password" class="form-control"
													id="exampleInputPassword1" placeholder="Password" 
													data-toggle="tooltip" data-placement="top"
												title="Enter your password"/>
											</div>
										</div>
									</div>
									
									<div class="row clearfix" style="margin-top: -7px;">
										<div class="form-group">
											<div class="col-md-5 colsm-5 col-xs-5 column">
												<input type="submit" class="btn btn-block  btn-sm pull-left"
													id="bbb" style="font-weight: bold; letter-spacing: 1px;"
													value="Login ">
													
													
											</div>
											
											
												<div id="customBtn1" class="customGPlusSignIn">
													<span class="icon"></span> <span class="buttonText">Sign In</span>
												</div>
											
											
											<script>startApp();</script>
											
											<script type="in/Login"></script>
											<div class="col-md-7 colsm-7 col-xs-7 column"
												style="padding-top: 5px;">
												<a href="javascript:changePassword();"
													class="btn-link pull-right"
													style="font-weight: bold; letter-spacing: 1px; font-weight: bold; letter-spacing: 1px; font-size: 0.84em; color: #3e3e3e;">Forgot
													Password <Span
													style="background-color: rgba(255, 255, 255, 0.6); color: #ff7373; padding: 5px; border-radius: 15px; font-weight: bold; font-family: Josefin Sans; font-size: 1.2em;">?</Span>
												</a>
											</div>
										</div>

									</div>
								</form>

							</div>
						</div>
					</div>

					<div class="panel panel-default">
						<a class="collapsed" data-toggle="collapse"
									data-parent="#accordion" href="#collapseTwo"
									aria-expanded="false" aria-controls="collapseTwo">
						<div class="panel-heading" role="tab" id="headingTwo" data-toggle="tooltip" data-placement="top"
												title="Sign up if u have not registered">
						
							<h4 class="panel-title">
								
								 SIGN UP
								
							</h4>
						</div></a>
						<div id="collapseTwo" class="panel-collapse collapse "
							role="tabpanel" aria-labelledby="headingTwo">
							<div class="panel-body">
								

								<form class="form-horizontal" role="form" name="form1"
									action="register.register" style="padding: 10px;" method="post"
									onSubmit="return home();">
									<div class="row clearfix" style="margin-top: -5px;">
										<div class="col-xs-4 col-sm-4 column"
											style="padding-left: 22px; text-align: center">


											<label class="radio"> <input type="radio"
												name="type1" data-toggle="radio" id="optionsRadios1"
												value="1" onclick="showGoogleSignUp(1)" checked> <small
												style="letter-spacing: 1px;" data-toggle="tooltip" data-placement="top"
												title="Select this if u are a Doctor">Doctor</small>
											</label>
										</div>
										<div class="col-xs-4 col-sm-4 column"
											style="padding-left: 0px; text-align: center">
											<label class="radio"> <input type="radio"
												name="type1" data-toggle="radio" id="optionsRadios1"
												value="2" onclick="showGoogleSignUp(2)"> <small
												style="letter-spacing: 1px;" data-toggle="tooltip" data-placement="top"
												title="Select this if u are a Patient">Patient</small>
											</label>

										</div>
										<div class="col-xs-4 col-sm-4 column"
											style="padding-left: 0px; text-align: center;">

											<label class="radio"> <input type="radio"
												name="type1" data-toggle="radio" id="optionsRadios1"
												value="3" onclick="showGoogleSignUp(3)"> <small
												style="letter-spacing: 1px;" data-toggle="tooltip" data-placement="top"
												title="Select this if u are a Pharmacist">Pharmacist</small>
											</label>
										</div>
									</div>
									<br/>
									<div class="form-group">
										<div class="col-xs-6 col-sm-6">
											<input type="text" class="form-control" name="firstname"
												data-toggle="tooltip" data-placement="top"
												title="Enter your first name" placeholder="First Name"
												onblur="checkName(1);" required /><span
												class="error-message" id="firstname"></span>
										</div>
										<div class="col-xs-6 col-sm-6">
											<input type="text" class="form-control" name="lastname"
												data-toggle="tooltip" data-placement="top"
												title="Enter your last name" placeholder="Last Name"
												onblur="checkName(2);" required /><span
												class="error-message" id="lastname"></span>
										</div>
									</div>


									<div class="form-group">
										<div class="col-sm-12">
											<input type="email" name="email" class="form-control"
												id="inputEmail3" data-toggle="tooltip" data-placement="top"
												title="Enter your email" placeholder="Email"
												onchange="checkEmail();" required /><span
												class="error-message" id="mail"></span><span
												class="error-message" id="mail1"></span>
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-12">
											<input type="number" name="mobile" class="form-control"
												maxlength="10" id="inputcontact" data-toggle="tooltip"
												data-placement="top" title="Enter your contact no"
												placeholder="Contact No." onchange="checkPhone();" required /><span
												class="error-message" id="phone"></span><span
												class="error-message" id="phone1"></span>
										</div>
									</div>


										
										

									<div class="form-group" style="margin-top: 12px;margin-bottom:0px;">
									
										<div class="col-md-12 ">
										<div id="customBtn2" class="customGPlusSignIn">
													<span class="icon"></span> <span class="buttonText">Sign Up</span>
												</div>
											<input type="submit" id="bbb" class="btn btn-block btn-sm pull-right"
												style="font-weight: bold; width: 100px;" value="Register">
										</div>
										
										
												
											
									</div>
									
									
								</form>



							</div>
						</div>
					</div>
				</div>
			</div>



		</div>



	</div>



	<div class="modal fade" id="changePassword" role="dialog"
		data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog" style="max-width:400px;margin:auto;">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>


					<h4>Forgot Password?</h4>

					

				</div>
				<div class="modal-body" id="resetBody">

					<div class="panel-body">
						

						<form role="form" action="javascript:resetPassword()"
							style="padding: 10px;" method="post" name="reset"
							onSubmit="return checkReset();">

							<div class="form-group">
								<input type="text" class="form-control" name="email"
									id="resetText" placeholder="Enter your Mobile or Email"
									required /><span class="error-message" id="reset"
									style="color: black;"></span>
							</div>
							<div class="row clearfix" style="margin-top: -10px;">
								<div class="col-xs-4 col-sm-4 column"
									style="padding-left: 22px; text-align: center">


									<label class="radio"> <input type="radio"
										name="resetType" data-toggle="radio" id="resetType" value="1"
										checked> <small
										style="color: black; letter-spacing: 1px;">Doctor</small>
									</label>
								</div>
								<div class="col-xs-4 col-sm-4 column"
									style="margin-left: px; text-align: center">
									<label class="radio"> <input type="radio"
										name="resetType" data-toggle="radio" id="resetType" value="2">
										<small style="color: black; letter-spacing: 1px;">Patient</small>
									</label>

								</div>
								<div class="col-xs-4 col-sm-4 column"
									style="margin-left: px; text-align: center; width: px;">

									<label class="radio"> <input type="radio"
										name="resetType" data-toggle="radio" id="resetType" value="3">
										<small style="color: black; letter-spacing: 1px;">Pharmacist</small>
									</label>
								</div>
							</div>
							<br />
							<div class="form-group">
								<input type="submit" id="bbb" class="btn btn-sm pull-right"
									value="Submit">
							</div>
<!--  --p>We will send a link on your registered email or One Time
						Password (OTP) on your mobile to reset your password.</p-->

						</form>

					</div>
				</div>
			</div>

		</div>
	</div>

	<div class="modal fade" id="checkOTP" role="dialog"
		data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog modal-sm">

			<!-- Modal content-->
			<div class="modal-content modal-sm">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>

					<ul>
						<li style="color: black;">Enter Your 6 digits OTP Sent To
							Your Mobile <br> <span id="mobilo"></span>
						</li>
					</ul>

				</div>
				<div class="modal-body" id="otpBody">

					<div class="panel-body">
						

						<form role="form" action="javascript:checkOTP()"
							style="padding: 10px;" method="post" name="otpform">

							<div class="form-group">
								<input type="text" class="form-control" name="otp" id="resetOTP"
									placeholder="Enter your 6 Digits OTP" required /><span
									class="error-message" id="errorotp" style="color: black;"></span>
							</div>
							<div class="row clearfix" style="margin-top: -10px;"></div>
							<div class="form-group">
								<a href="javascript:resendOTP();"
									style="font-weight: bold; letter-spacing: 1px;"">Resend OTP</a>
							</div>
							<div class="form-group">
								<input type="submit" id="bbb" class="btn  btn-sm pull-right"
									style="font-weight: bold; letter-spacing: 1px;"
									value="Reset Password"/>
							</div>


						</form>

					</div>
				</div>
			</div>

		</div>
	</div>


	<div class="navbar-fixed-bottom"
		style="background-color: #00b9bc; color: #fff; padding-top: 10px;">


		<div class="container below_nav">
			<div class="row hidden-xs">

				<div class="col-xs-12 col-sm-6 col-md-6">
					<p class="pull-left f_links">
						Copyright Healingclick <span
							class="glyphicon glyphicon-registration-mark"></span> 2016
					</p>
				</div>

				<div class="col-xs-12 col-sm-6 col-md-6 column ">
					<a class="f_links pull-right" href="user_agreement.html">User
						Agreement </a> <a class="f_links pull-right"
						href="privacy_policy.html">Privacy Policy</a> <a
						class="f_links pull-right" href="about.html">About</a>
				</div>

			</div>



			<div class="row visible-xs text-center">



				<div class="col-xs-12 col-sm-6 col-md-6 column ">
					<a class="f_links" href="user_agreement.html">User Agreement </a> <a
						class="f_links" href="privacy_policy.html">Pricvacy Policy</a> <a
						class="f_links" href="about.html">About</a>
				</div>

				<div class="col-xs-12 col-sm-6 col-md-6">
					<p class="f_links">
						Copyright Healingclick <span
							class="glyphicon glyphicon-registration-mark"></span> 2016
					</p>
				</div>

			</div>



		</div>

	</div>








	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>