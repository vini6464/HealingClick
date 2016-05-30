<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Healingclick</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
<script type="text/javascript" src="js/ajax.js"></script>
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
  <link href="css/basic.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
	<style>
	#first{
	font-size:1em;text-align:left;}
	#second{
	font-size:1.5em;text-align:left;}
	#text{text-align:center;}
	
	.numbers{text-align:center;font-size:1.1em;}
	.round{border-radius:20px;}
	
	.admin_nav{background-color:white;}
	
	.doc{background-color:rgba(14,175,165, 0.8);color:white;}
	.doc_verification{color:rgba(14,175,165, 1);background-color:white;padding:10px;}
	.gly_doc{color:rgba(14,175,165, 0.9);}
	.patient{background-color:rgba(191,62,70, 0.8);color:white;}
	.pharmacy{background-color:rgba(191,119,62, 0.8);color:white;}
	.pharmacy_verification{color:rgba(191,119,62, 0.8);background-color:white;padding:10px;}
	.glyphicon-stats{color:rgba(191,119,62, 0.8);}
	</style>
	
	
	
  </head>
  
  <body style="min-height:800px;background: -webkit-linear-gradient(left,  rgba(246,246,246, 0.8), rgba(246,246,246,1)); /* For Safari 5.1 to 6.0 */
    background: -o-linear-gradient(right,  rgba(246,246,246, 0.8), rgba(246,246,246,1)); /* For Opera 11.1 to 12.0 */
    background: -moz-linear-gradient(right,  rgba(246,246,246, 0.8), rgba(246,246,246,1)); /* For Firefox 3.6 to 15 */
    background: linear-gradient(to right,  rgba(246,246,246, 0.8), rgba(246,246,246,1)); /* Standard syntax (must be last) */
} ">
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
						<li>
							 <a href="#" data-toggle="dropdown" class="dropdown-toggle" ><span class="glyphicon glyphicon-bell" id="notify"></span> <span id="notificationcount"></span></a>
							<ul class="dropdown-menu" id="notification"  >
								
							</ul>
						</li>
					
					</ul>
				</div>
				
			</nav>
		</div>
	</div>
</div>
  
  
  <div class="container-fluid">
	<div class="row clearfix ">
	
	 
	 
	 <div class="col-md-12 column"  style="margin-top:100px;">
	 <div class="row">
	 
		<div class="col-md-4 column">
			<div class="panel panel-default doc">
				
				  <div class="panel-body">
				<h1> 	<span class="glyphicon glyphicon-lock" id="first"></span>
					<span class="glyphicon glyphicon-user" id="second"></span>&nbsp;&nbsp;${doctorCount} </h1>
					<p id="text">DOCTORS</p>
				  </div>
				
		     	</div>
		</div>
		<div class="col-md-4 column">
		<div class="panel panel-default patient">
				
				  <div class="panel-body">
				<h1> 	<span class="glyphicon glyphicon-user" id="first"></span>
					<span class="glyphicon glyphicon-user" id="second"></span>&nbsp;&nbsp; ${patientCount} </h1>
					<p id="text">PATIENTS</p>
				  </div>
				
		     	</div>
		</div>
		<div class="col-md-4 column">
		<div class="panel panel-default pharmacy">
				
				  <div class="panel-body">
					<h1> <span class="glyphicon glyphicon-paste" id="first"></span>
					<span class="glyphicon glyphicon-user" id="second"></span>&nbsp;&nbsp; ${pharmacyCount} </h1>
					<p id="text">PHARMACIES</p>
				  </div>
				
		</div>
		</div>
	    </div>
	
	<div class="row clearfix">
		
		<div class="col-md-6 column">
		<div class="panel panel-default">
				<div class="doc_verification ">
					<h3 class="panel-title ">
						Doctor Verification
					</h3>
				</div>
				<div class="panel-body">
				<c:if test="${not empty doctors}">
					<c:forEach var="request" items="${doctors}">
								
									<div class="row">
										
											<!--  div class="col-md-2 column">
												<img alt="300x200" src="${request.image}" width="60px;"
													height="60px;" />
											</div>-->

											
												<div class="col-md-6 column">
													<a href="getDoctor.admin?id=${request.id}" id="">${request.firstName}</a>
												</div>

												<div class="col-md-3 column">
													<a href="getDoctor.admin?id=${request.id}" class="btn btn-sm"
														type="button" style="">View Details</a>
												</div>
												<div id="${request.id}" style="">
												<div class="col-md-3 column">
													<input class="btn  btn-sm" type="button"
														style="width: %;" onclick="activate(${request.id});" value="Activate">
												</div>
											</div>	
											
											</div>	
											<br/>
							</c:forEach>
							</c:if>
				</div>
				
			</div>
		</div>
		
		<div class="col-md-6 column">
		<div class="panel panel-default">
				<div class="pharmacy_verification">
					<h3 class="panel-title">
						Pharmacy Verification
					</h3>
				</div>
				<div class="panel-body">
				<c:if test="${not empty pharmacies}">
					<c:forEach var="request" items="${pharmacies}">
								
									
										<div class="row">
											<!-- div class="col-md-2 column">
												<img alt="300x200" src="${request.image}" width="60px;"
													height="60px;" />
											</div--->

											
												<div class="col-md-6 column">
													<a href="getPharmacy.admin?id=${request.id}" id="">${request.pharmacyName}</a>
												</div>

												<div class="col-md-3 column">
													<a href="getPharmacy.admin?id=${request.id}" class="btn btn-sm"
														type="button" style="">View Details</a>
												</div>
												<div id="${request.id}" style="color:green;richness: inherit;">
												<div class="col-md-3 column">
													<input class="btn btn-sm" type="button"
														style="width: %;" onclick="activatePharmacy(${request.id});" value="Activate">
												</div>
											</div>
										</div>
										<br/>
							</c:forEach>
							</c:if>
				</div>
				
			</div>
		</div>
		
		<div class="col-md-9 column">
			<div class="row clearfix">
				<div class="col-md-4 column">
				<div class="panel panel-default round">
				
				  <div class="panel-body">
				  <span class="glyphicon glyphicon-eye-open pull-left gly_doc"></span>
				  <p class="numbers">
					Prescriptions made by doctor
					<br>
					<span class="gly_doc_stat">${prescriptionCount} </span></p>
				  </div>
				
		     	</div>
				</div>
				<!-- <div class="col-md-4 column">
				<div class="panel panel-default round">
				
				  <div class="panel-body">
				  <p class="numbers">
					Orders made by Patients <br>
						 45</p>
				  </div>
				
		     	</div>
				</div> -->
				<div class="col-md-4 column">
				<div class="panel panel-default round">
				
				  <div class="panel-body">
				   <span class="glyphicon glyphicon-stats pull-left"></span>  
				  <p class="numbers"> 
					Orders delivered by Pharmacy<br>
						<span class="gly_phar_stat">${orderCount}</span></p>
				  </div>
				
		     	</div>
				</div>
			</div>
		</div>
	</div>
	
	
	
	
	
	
	
	
	 <div class="row">
	 <div class="col-md-12 column">
	 <div class="panel panel-default">
				
				  <div class="panel-body">
				
					<p id="text">THIS MONTH <br><span class="glyphicon glyphicon-chevron-down"></span></p>
				  </div>
				
		     	</div>
	 </div>
	 
	 <div class="col-md-4 column">
		
		</div>
		<div class="col-md-4 column">
			<div class="panel panel-default doc">
				
				  <div class="panel-body">
				 	<span class="glyphicon glyphicon-lock" id="first"></span>
					<span class="glyphicon glyphicon-user" id="second"></span>&nbsp;&nbsp;${doctorMonth}
					<p id="text">DOCTORS</p>
				  </div>
				
		     	</div>
				
				<div class="panel panel-default patient">
				
				  <div class="panel-body">
				<span class="glyphicon glyphicon-user" id="first"></span>
					<span class="glyphicon glyphicon-user" id="second"></span>&nbsp;&nbsp; ${patientMonth} 
					<p id="text">PATIENTS</p>
				  </div>
				
		     	</div>
				
				<div class="panel panel-default pharmacy">
				
				  <div class="panel-body">
					 <span class="glyphicon glyphicon-paste" id="first"></span>
					<span class="glyphicon glyphicon-user" id="second"></span>&nbsp;&nbsp; ${pharmacyMonth}
					<p id="text">PHARMACIES</p>
				  </div>
				
		</div>
				
		</div>
		<div class="col-md-4 column">
		
		<div class="panel panel-default round">
				
				  <div class="panel-body">
				  <span class="glyphicon glyphicon-eye-open pull-left gly_doc"></span>
				  <p class="numbers">
					Prescriptions made by doctor
					<br>
					 ${prescriptionMonth}</p>
				  </div>
				
		     	</div>
				
				
				<!-- <div class="panel panel-default round">
				
				  <div class="panel-body">
				  <p class="numbers">
					Orders made by Patients <br>
						 45</p>
				  </div>
				
		     	</div> -->
				
				
				<div class="panel panel-default round">
				
				  <div class="panel-body">
				  
				 <span class="glyphicon glyphicon-stats pull-left"></span>  
				  <p class="numbers"> 
					Orders delivered by Pharmacy<br>
						${orderMonth}</p>
				  </div>
				
		     	</div>
		
		</div>
		
	    </div>
	
	
	
	
	
	
</div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>