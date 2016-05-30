<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="icon" type="image" href="image/healingclick_icon.png" />


<script src="js/jquery.js"></script>
<script>
function preventBackButton(){window.history.forward();}
setTimeout("preventBackButton()", 0);
window.onunload=function(){null};
</script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
      <script>
$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip();   
});
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
@import 
url(https://fonts.googleapis.com/css?family=Josefin+Sans:600
);

body{font-family: 'Josefin Sans', sans-serif;

  font-weight:;letter-spacing:1px;
 

background: url(image/healingclick_home.png) no-repeat center center fixed; 
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;

  background-color: #f6f6f6; 

}
.panel {
    margin-bottom: 20px;
    background-color: rgba(255, 255, 255, 0.62);
    border: 1px solid transparent;
    border-radius: 4px;
    -webkit-box-shadow: 0 1px 1px rgba(0,0,0,.05);
    box-shadow: 0 1px 1px rgba(0,0,0,.05);
}


.panel-default>.panel-heading {
    color: #333;
    background-color:  rgba(255, 255, 255, 0.82);
    border:none;
}
.panel-heading {
    padding: 15px 15px;
    border-bottom: 0px solid transparent;
    border-top-left-radius: 3px;
    border-top-right-radius: 3px;
}
  .f_links{
    color:#fff;
    font-size:11px;
     padding:0px 5px;
    }
    
    .f_links:hover{
    color:#fff;
    font-size:11px;
   
    
    }
    
    .btn-info {
    color: #fff;
    background-color: #00B9BC;
    border:none;
    font-size: 12px;
}
    btn-info.active, .btn-info.focus, .btn-info:active, .btn-info:focus, .btn-info:hover, .open>.dropdown-toggle.btn-info {
    color: #fff;
    background-color: #00B9BC;
    border:none;
    opacity:0.8;
}

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

var mobile = "${patient.mobile}";
var i = 0;
function resendOTP()
{
	$.ajax({
		method : "GET",
		url : 'resendOTP.notification',
		data: { 
			mobile : mobile	
		},
		success : function(result) {
			$("#errorotp").show();
			var span = document.getElementById("errorotp");
			var txt = document.createTextNode("New OTP has been Send to "+mobile);
			span.innerText = txt.textContent;
		},
		statusCode : {
			500 : function(result) {
				
			}
		}
	});	   
}

function verifyOTP()
{
	i++;
		var otp = document.getElementById("otp").value;
		var id = document.getElementById("id").value;;
		$.ajax({
			method : "GET",
			url : 'verifyOTP.login',
			data: { 
				id : id,
				otp : otp
			},
			success : function(result) {
				if(result==1){
					window.location = "patientBeginner.home";
				}
				else
					{
					if(i == 4)
						{
						window.location = "home.jsp";
						}
					else
						{
						$("#errorotp").show();
						var span = document.getElementById("errorotp");
						var txt = document.createTextNode("Sorry OTP Does Not Match ");
						span.innerText = txt.textContent;
						}
					
					}
			},
			statusCode : {
				500 : function(result) {
					
				}
			}
		});	   
}

</script>

<style>
  font-family: 'Josefin Sans', sans-serif;
</style>
</head>
<body>
<div class="se-pre-con"></div>
  
  <div class="container-fluid full_content navbar-fixed-top"  style="background-color:rgba(255,255,255,0.8);border-bottom:2px solid #20C1C4;">
	
<div class="container">
<div class="row">
<div class="col-sm-4 col-md-4 column animated fadeIn"><!-- h1 class="header">HEALINGCLICK <small style="color:white;">EMPOWERING WELLNESS</small></h1> -->

<img src="image/healingclick.png" width="100%" height="65" style="max-width:350px;padding:5px 0px;"/>
</div>
<div class="col-sm-4 col-md-4 column">

</div>
<div class="col-md-4 column">
</div>
</div>
</div>
</div>





	<div class="container" style="margin-top:18%;">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="row clearfix">
					<div class="col-sm-6 col-md-4"></div>
					<div class="col-xs-12 col-sm-6 col-md-4 column">

				<div class="panel panel-default" >
				<div class="panel-heading">
					<h4 class="panel-title text-center" >
							OTP Has been Sent to your mobile number ${patient.mobile}
					</h4>
				</div>
				<div class="panel-body" style="background-color:rgba(255,255,255,0.6) !important;">
			
				<div style="height:15px;"></div>
				${error}${success}
					<form class="form-horizontal" name="form" role="form" 
					action="javascript:verifyOTP()"  method="post">
					<span class="error-message" id="errorotp" style="color:black;"></span>
					<div class="form-group">
						<div class="col-sm-12" >
							<input type="number" class="form-control" id="otp"
								name="otp"   data-toggle="tooltip" data-placement="top" title="Enter your OTP"  placeholder="Enter your OTP"     required/>
                                <div id="match"></div>
						</div>
					</div>
					<div class="form-group" style="display:none;">
						<div class="col-sm-12">
							<input type="text" class="hidden" id="id"
								name="id"   data-toggle="tooltip" data-placement="top" title="Enter your OTP"  placeholder="OTP" value="${patient.id}" />
                                <div id="match"></div>
						</div>
					</div>
					<div class="form-group" >
					 <div class="col-md-12 column">
						 <a href="javascript:resendOTP();">Resend OTP</a></div>
						  </div>
						  <div class="form-group" >
					 <div class="col-md-12 column">
					<input type="submit" class="btn btn-info pull-right btn-sm"
								value="Verify"> </div>
								</div>
				</form>
				
					
				</div>
				
			</div>
					</div>
					




					



					

						</div>

					</div>
















					

				</div>
			</div>


	
		<div class="navbar-fixed-bottom" style="background-color:#00b9bc;color:#fff;padding-top:10px;">
		<!-- -- <h5 class="text-center"---> <!-- span class="glyphicon glyphicon-copyright-mark"></span>Copyright  Healingclick <span class="glyphicon glyphicon-registration-mark"></span> 2016</h5> 
	--->
	
		
		
		<div class="container below_nav">
		<div class="row hidden-xs">
		
		<div class="col-xs-12 col-sm-6 col-md-6">
		<p class="pull-left f_links">Copyright  Healingclick <span class="glyphicon glyphicon-registration-mark"></span> 2016 </p>
		</div>
		
		<div class="col-xs-12 col-sm-6 col-md-6 column ">
		<a class="f_links pull-right" href="">User Agreement </a>
		
		<a class="f_links pull-right" href="">Pricvacy Policy</a>
		
		<a class="f_links pull-right" href="">About</a>
		</div>
		
		</div>
		
		
		
		<div class="row visible-xs text-center">
		
		
		
		<div class="col-xs-12 col-sm-6 col-md-6 column ">
		<a class="f_links" href="">User Agreement </a>
		
		<a class="f_links" href="">Pricvacy Policy</a>
		
		<a class="f_links" href="">About</a>
		</div>
		
		<div class="col-xs-12 col-sm-6 col-md-6">
		<p class="f_links">Copyright  Healingclick <span class="glyphicon glyphicon-registration-mark"></span> 2016 </p>
		</div>
		
		</div>
		
		
		
		</div>
		
		</div>
		



			<script src="js/bootstrap.min.js"></script>
</body>
</html>










