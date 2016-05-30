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
    <link href="css/animate.css" rel="stylesheet">
    <script src="js/jquery.js"></script>
	
	   <script type="text/javascript" src="js/ajax.js"></script>
   <script type="text/javascript" src="js/validation.js"></script>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
	<style>
	
@import 
url(https://fonts.googleapis.com/css?family=Josefin+Sans:600
);

 

body{font-family: 'Josefin Sans', sans-serif;

  font-weight:;letter-spacing:1px;

  background-color: #f6f6f6; 

color: white;}

.tooltip{font-family: 'Josefin Sans', sans-serif;}


.panel-title{color:white;}


.footer {
  text-align: center;
}

.footer a {
  color: #53B2C8;
}

#bbb{

background-color:rgba(255,255,255, 1);color:grey;

width:90px;text-align:left;-webkit-transition: all 0.5s; 

-moz-transition: all 0.5s; 

transition: all 0.5s;}


#bbb:hover{

-webkit-transition: all 0.5s; 

-moz-transition: all 0.5s; 

transition: all 0.5s;

background-color:#3ddcc8;color:white;

text-align:right;

}


.h{ animation-delay:0.2s; -moz-animation-delay:0.2s; -webkit-animation-delay:0.2s; }
	
	.e{ animation-delay:0.3s; -moz-animation-delay:0.3s; -webkit-animation-delay:0.3s; }
	.a{ animation-delay:0.4s; -moz-animation-delay:0.4s; -webkit-animation-delay:0.4s; }
	.l{ animation-delay:0.5s; -moz-animation-delay:0.5s; -webkit-animation-delay:0.5s; }
	.i{ animation-delay:0.6s; -moz-animation-delay:0.6s; -webkit-animation-delay:0.6s; }
	.n{ animation-delay:0.7s; -moz-animation-delay:0.7s; -webkit-animation-delay:0.7s; }
	.g{ animation-delay:0.8s; -moz-animation-delay:0.8s; -webkit-animation-delay:0.8s; }
	.c{ animation-delay:0.9s; -moz-animation-delay:0.9s; -webkit-animation-delay:0.9s; }
	.l{ animation-delay:1s; -moz-animation-delay:1s; -webkit-animation-delay:1s; }
	.i{ animation-delay:1s; -moz-animation-delay:1s; -webkit-animation-delay:1s; }
	.c{ animation-delay:1s; -moz-animation-delay:1s; -webkit-animation-delay:1s; }
	.k{ animation-delay:1s; -moz-animation-delay:1s; -webkit-animation-delay:1s; }
	.emp{animation-delay:1s; -moz-animation-delay:1s; -webkit-animation-delay:1s;}
	
	
	
	
	
</style>

<script type="text/javascript">

$(document).ready(function() {

	
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

});

</script>

  </head>
 <body style="background:url(image/healingclick_home.png);

      background-repeat: no-repeat;

      background-size:100% 100%;min-height:800px;">
  
  
  
  
  <div class="container-fluid">
	<div class="row" style="background-color:rgba(39,39,39, 0.4);">
		<div class="col-md-7 column" style="margin-top:%;">
			<div class="row">
				
				<div class="col-md-12 column" >
					<div id="header" class="row text-center" style="text-shadow: 1px 1px 1px rgba(255,255,255, 0.8);font-size:2em;color:white;padding:1%;">
		<div id="logo">
			<span id="healing">
				<span class="h animated bounceInDown">H</span>
				<span class="e animated bounceInDown">E</span>
				<span class="a animated bounceInDown">A</span>
				<span class="l animated bounceInDown">L</span>
				<span class="i animated bounceInDown">I</span>
				<span class="n animated bounceInDown">N</span>
				<span class="g animated bounceInDown">G</span>
			</span>
			<span id="click">
				<span class="c animated bounceInDown">C</span>
				<span class="l animated bounceInRight">L</span>
				<span class="i animated bounceInDown">I</span>
				<span class="c animated bounceInDown">C</span>
				<span class="k animated lightSpeedIn">K</span>
				
			</span>
			<span class="emp animated bounceInDown" style="font-size:0.5em;letter-spacing:0.5px;">EMPOWERING WELLNESS</span>
		</div>

		<!--nav id="main-nav">
			<ul class="list-unstyled list-inline">
				<li><a id="demo-1" class="animated btn btn-danger" href="index.html">Demo 1</a></li>
				<li><a id="demo-2" class="animated btn btn-danger" href="two.html">Demo 2</a></li>
				<li><a id="demo-3" class="animated btn btn-danger" href="three.html">Demo 3</a></li>
			</ul>
		</nav-->
	</div>
				</div>
				
				
			</div>
		
	
</div>
<div class="col-md-5 column" > </div>



</div>




<div class="row">
		<div class="col-md-7 column" style="margin-top:%;"></div>

  
  <div class="col-xs-12 col-sm-12 col-md-5 column text-center" style="min-height:600px;text-align:center;margin:auto;">
   
   
   
   
   
<div class="animated fadeInUp " style="margin-top:30%;">

  
<div class="row clearfix text-center">
  		<div class="col-md-12 column text-center" style="max-width:400px;">
		
			
				
					
	          <span style="background-color:;font-size:1.4em;width:400px;letter-spacing:1px;text-align:center;">${error}${msg}${user}${email}${phone}</span>
			
      <div class="panel-body" style="border-top:none;background-color: rgba(255,255,255, 0.3);">
				<form role="form" action="login.login" style="padding:10px;" method="post">
					
						<div class="form-group">
							<input type="text" class="form-control" name="email" id="exampleInputEmail1" placeholder="Email/Username" required/>
						</div>
						<div class="form-group" style="margin-top:-10px;">
							 <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Password" required />
						</div>
						<div class="row clearfix" style="margin-top:-10px;" >	  
				<div class="col-xs-4 col-sm-4 column" style="padding-left:22px;text-align:center">
				 
                    
                       <label class="radio">
                        <input type="radio" name="type" data-toggle="radio" id="optionsRadios1" value="1" checked>
                        <i></i><small style="color:;letter-spacing:1px;">Doctor</small>
                      </label>
				</div>
				<div class="col-xs-4 col-sm-4 column" style="margin-left:px;text-align:center">
				<label class="radio">
                        <input type="radio" name="type" data-toggle="radio" id="optionsRadios1" value="2">
                        <i></i><small style="color:;letter-spacing:1px;">Patient</small>
                      </label>
				   
				</div>
				<div class="col-xs-4 col-sm-4 column" style="margin-left:px;text-align:center;width:px;">
				
				  <label class="radio">
                        <input type="radio" name="type" data-toggle="radio" id="optionsRadios1" value="3">
                        <i></i><small style="color;letter-spacing:1px;">Pharmacist</small>
                      </label>
				</div>
			</div>
			<div class="form-group" >
						 <input type="submit" class="btn btn-block  btn-sm pull-left" id="bbb" style="font-weight:bold;letter-spacing:1px;" value="Login"></div> <div class="form-group" >
						
						 <!--div class="row">
						 <div class="col-xs-6 col-sm-6 col-md-6 column">
				<div class="checkbox" style="font-size:12px;color:grey;">		<label><input type="checkbox" style="padding-top:10px;"/> Remember Me</label></div></div>
				    <div class="col-xs-6 col-sm-6 col-md-6 column">
						 <a href="#" class="btn btn-link btn-sm pull-right" style="margin-top:;letter-spacing:px;padding-top:10px;font-size:11.1px;margin-left:15px;" type="button">Forgot Password?</a></div>
						 </div-->
					</form>

	   </div>
    </div>
  </div>
  
                <div class="panel panel-default" style="background-color: rgba(39,39,39, 0.3);border:none;">
    <div class="panel-heading" role="tab" id="headingTwo" style="background-color:rgba(255,255,255, 0.3);">
      <h4 class="panel-title" style="text-align:center;font-weight:bold;">
        <a class="collapsed" style="text-center" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo" >
        Signup
        </a>
      </h4>
    </div>
    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
      <div class="panel-body" style="background-color: rgba(255,255,255, 0.3);border-top:none;">
        
<form class="form-horizontal" role="form" name="form1" action="register.register" style="padding:10px;" method="post" onSubmit="return home();">
				<div class="form-group">
					<div class="col-xs-6 col-sm-6"><input type="text" class="form-control" name="firstname"  data-toggle="tooltip" data-placement="top" title="Enter your first name"  placeholder="First Name" onblur="checkName(1);" required/><span class="error-message" id="firstname" ></span></div>
					<div class="col-xs-6 col-sm-6"><input type="text" class="form-control" name="lastname" data-toggle="tooltip" data-placement="top" title="Enter your last name" placeholder="Last Name" onblur="checkName(2);" required/><span class="error-message" id="lastname" ></span></div>
				</div>
				
						
				<div class="form-group">
				<div class="col-sm-12"><input type="email" name="email" class="form-control" id="inputEmail3"  data-toggle="tooltip" data-placement="top" title="Enter your email" placeholder="Email" onchange="checkEmail();" required/><span class="error-message" id="mail" ></span><span class="error-message" id="mail1" ></span></div>
				</div>
					<div class="form-group">
				<div class="col-sm-12"><input type="number" name="mobile" class="form-control" maxlength="10"  id="inputcontact"  data-toggle="tooltip" data-placement="top" title="Enter your contact no" placeholder="Contact No." onchange="checkPhone();" required/><span class="error-message" id="phone" ></span><span class="error-message" id="phone1" ></span></div>
				</div>
				
				
				
			
				<div class="row clearfix" style="margin-top:-5px;">	  
				<div class="col-xs-4 col-sm-4 column" style="padding-left:22px;text-align:center">
				 
                    
                       <label class="radio">
                        <input type="radio" name="type1" data-toggle="radio" id="optionsRadios1" value="1" checked>
                        <i></i><small style="letter-spacing:1px;">Doctor</small>
                      </label>
				</div>
				<div class="col-xs-4 col-sm-4 column" style="padding-left:0px;text-align:center">
				<label class="radio">
                        <input type="radio" name="type1" data-toggle="radio" id="optionsRadios1" value="2">
                        <i></i><small style="letter-spacing:1px;">Patient</small>
                      </label>
				   
				</div>
				<div class="col-xs-4 col-sm-4 column" style="padding-left:0px;text-align:center;">
				
				  <label class="radio">
                        <input type="radio" name="type1" data-toggle="radio" id="optionsRadios1" value="3">
                        <i></i><small style="letter-spacing:1px;">Pharmacist</small>
                      </label>
				</div>
			</div>
				
				
				
			
				<div class="form-group" style="margin-top:10px;">
					<div class="col-sm-12 pull-right">
						 <input type="submit" id="bbb" class="btn btn-block btn-xs" style="font-weight:bold;" value="Register">
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

  

</div>
		</div>	
			
			
			
				
					
	

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>