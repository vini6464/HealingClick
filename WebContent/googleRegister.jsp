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
					
					 Hi  ${googleUser.firstName}   ${googleUser.lastName} , <br/>
							Please Enter your Personal Details
					</h3>
				</div>
				<div class="panel-body">
			
				
				                 
					<form class="form-horizontal" name="form" role="form" 
					action="personalDeatils.register"  method="post">
					
						<div class="form-group">
							 <!--<label for="inputPassword3" class="col-sm-2 control-label">Password</label>-->
							<div class="col-sm-10">
								<input class="form-control" id="inputPassword3" name="username" type="text" data-toggle="tooltip" data-placement="top" title="Enter your user name" placeholder="Unique user name" onblur="checkUserName(2);" required/><span class="error-message" id="user1" data-value="1"></span><span class="error-message" id="user"></span>
							</div>
						</div>
					
						<div class="form-group">
							 <!--<label for="inputPassword3" class="col-sm-2 control-label">Password</label>-->
							<div class="col-sm-10">
								<input class="form-control" id="inputPassword3" type="password" name="password" data-toggle="tooltip" data-placement="top" title="Enter your password" placeholder="Your Password" required><span class="error-message" id="password" ></span>
							</div>
						</div>
						
						<div class="form-group" >
	  						 <div class="col-sm-10">
								
							<select name="gender" class="form-control">
	  							<option value="1">Male</option>
	  							<option value="2">Female</option>
	  							
	  						</select><span class="error-message" id="gender"></span>
	  						</div>
	  					</div>
	  					
	  					<div class="form-group">
							 <!--<label for="inputPassword3" class="col-sm-2 control-label">Password</label>-->
							<div class="col-sm-10">
								<input class="form-control" id="dob" name="dob" type="date" data-toggle="tooltip" data-placement="top" title="Enter your Date of Birth (mm/dd/yyyy) "  placeholder="Date of Birth (dd/mm/yyyy)" required/>
							</div>
						</div>
						
						<div class="form-group">
										<div class="col-sm-10">
											<input type="number" name="mobile" class="form-control"
												maxlength="10" id="inputcontact" data-toggle="tooltip"
												data-placement="top" title="Enter your contact no"
												placeholder="Contact No." onchange="checkPhone();" required /><span
												class="error-message" id="phone"></span><span
												class="error-message" id="phone1"></span>
										</div>
									</div>

					

					
                     
					 <div class="col-md-12 column">
					<input type="submit" class="btn btn-default btn-info active btn-xs"
								style=" letter-spacing: 1px;font-weight:; font-size: 12px;" value="Update"> </div>
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
			<script>
    $('#dob').prop('max', function(){
        return new Date().toJSON().split('T')[0];
    });
    </script>
</body>
</html>










