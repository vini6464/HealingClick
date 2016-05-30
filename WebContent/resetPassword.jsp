<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" type="image" href="image/healingclick_icon.png" />
<link href="css/bootstrap.min.css" rel="stylesheet">

<link href="css/style.css" rel="stylesheet">
<script type="text/javascript" src="js/ajax.js"></script>
<script src="js/dropzone.js"></script>
<link href="css/dropzone.css" rel="stylesheet">
<link href="css/basic.css" rel="stylesheet">
<script src="js/jquery.js"></script>

<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.9.1.js"></script>
 <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script><script>
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
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

<script>
var id = "${id}";
var type= "${type}";

function savePassword()
{
	var password = document.getElementById("userpassword").value;
	$.ajax({
		method : "GET",
		url : 'savePassword.notification',
		data: { 
			id : id,
			type : type,
			password : password
		},
		success : function(result) {
			
			if (result == 1) {
					var home = "";
					if (type == 1) {
						home = "doctor.home";
					}
					if (type == 2) {
						home = "patient.home";
					}
					if (type == 3) {
						home = "pharmacy.home";
					}
					window.location = home;
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
.panel-default>.panel-heading {
    color: #333;
    background-color: transparent;
    border-color: #ddd;
    border-radius: 0px;
}
.panel {
    margin-bottom: 20px;
    background-color: rgba(255, 255, 255, 0.7);
    border: 1px solid transparent;
    border-radius: 0px;
    -webkit-box-shadow: 0 1px 1px rgba(0,0,0,.05);
    box-shadow: 0 1px 1px rgba(0,0,0,.05);
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
</style>
</head>
<body>
 <div class="container-fluid full_content navbar-fixed-top"  style="background-color:rgba(255,255,255,1);border-bottom:2px solid #20C1C4;">
	
<div class="container">
<div class="row">
<div class="col-xs-8 col-sm-6 col-md-4 column animated fadeIn"><!-- h1 class="header">HEALINGCLICK <small style="color:white;">EMPOWERING WELLNESS</small></h1> -->

<img src="image/healingclick.png" width="100%" height="65" style="max-width:350px;padding:5px 0px;"/>
</div>

<div class="col-xs-4 col-sm-6 col-md-8 column">

<!--  a class="pull-right btn-link t_30" href="privacy_policy.html">USER AGREEMENT </a-->

</div>
</div>
</div>
</div>
  <div style="height:100px"></div>




	<div class="container" style="margin-top: 4%;">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="row clearfix">
					<div class="col-sm-3 col-md-4"></div>
					<div class="col-xs-12 col-sm-6 col-md-4 column">

						<div class="panel panel-default" >
				<div class="panel-heading">
					<h3 class="panel-title text-center" style="font-weight:; color:grey ;">
							Change password
					</h3>
				</div>
				<div class="panel-body">
			
				
				${error}${success}
					<form class="form-horizontal" name="form" role="form" 
					action="javascript:savePassword()" onSubmit="return comparepassword();" method="post">
					
					<div class="form-group">
						<!--label for="inputPassword3" class="col-sm-4 control-label">Enter
							a new password:</label-->
						<div class="col-sm-12" style="padding-left:30px;padding-right:30px;padding-top:px;">
							<input type="password" class="form-control" id="userpassword"
								name="newpassword"   data-toggle="tooltip" data-placement="top" title="Enter your new password"  placeholder="New password"  style="height:30px;"   required/>
                                <div id="match"></div>
						</div>
					</div>
					<div class="form-group">
						<!--label for="inputPassword3" class="col-sm-4 control-label">Type
							it again:</label-->
						<div class="col-sm-12" style="padding-left:30px;padding-right:30px;padding-top:px;">
							<input type="password" class="form-control" id="userpassword1"
								name="confirmpassword" data-toggle="tooltip" data-placement="top" title="Retype password"  placeholder="Retype password"  style="height:30px;"  required />
                               
						</div>
					</div>

					

					
                     
					 <div class="col-md-12 column">
					<input type="submit" class="btn btn-default btn-info active btn-xs"
								style=" letter-spacing: 1px;font-weight:; font-size: 12px;" value="Change password"> </div>
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
		<a class="f_links pull-right" href="user_agreement.html">User Agreement </a>
		
		<a class="f_links pull-right" href="privacy_policy.html">Privacy Policy</a>
		
		<a class="f_links pull-right" href="about.html">About</a>
		</div>
		
		</div>
		
		
		
		<div class="row visible-xs text-center">
		
		
		
		<div class="col-xs-12 col-sm-6 col-md-6 column ">
		<a class="f_links" href="user_agreement.html">User Agreement </a>
		
		<a class="f_links" href="privacy_policy.html">Privacy Policy</a>
		
		<a class="f_links" href="about.html">About</a>
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










