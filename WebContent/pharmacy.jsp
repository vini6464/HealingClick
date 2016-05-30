<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>HealingClick</title>
    <link rel="icon" type="image/x-icon" href="image/healingclick_icon.png" />
<script type="text/javascript" src="js/jquery.js"></script>  
<script type="text/javascript" src="js/ajax.js"></script>
<script type="text/javascript" src="js/countries.js"></script>
<script type="text/javascript" src="js/validation.js"></script>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
	
<script>
function preventBackButton(){window.history.forward();}
setTimeout("preventBackButton()",0);
window.onunload=function(){null};
</script>
      <script>
$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip();   
});
</script>  


<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
})(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

ga('create', 'UA-77164718-1', 'auto');
ga('send', 'pageview');

</script>

	
	
	
	
	<style>
	

@import url(https://fonts.googleapis.com/css?family=Josefin+Sans:600);
 
  body{font-family: 'Josefin Sans',sans-serif;
  font-weight:;letter-spacing:1px;

	}
	
	.btn-info.active,.btn-info.focus,.btn-info:active,.btn-info:focus,.btn-info:hover,.open>.dropdown-toggle.btn-info {
    color: #fff;
     background-color: #00b9bc;
    border-color: none;
    opacity:0.8;
}
.form-control{
	    border: 1px solid rgba(128,128,128,0.08);
	    }
	.btn-info {
    color: #fff;
    background-color: #00b9bc;
    border-color: none;
}
	.tooltip{font-family: 'Josefin Sans',sans-serif;}


	
	.forms{
  

background: url("image/healingclick_home.png") no-repeat cEnter cEnter fixed;
-webkit-background-size: cover;
-moz-background-size: cover;
-o-background-size: cover;
background-size:cover;
    
   
    
}

.page_heading{
	 font-size:16px;
	 font-weight:bold;
	}
	.m_h{
	font-size:15px;
	font-weight:bold;
	padding-top:20px;
	}
	.md_f{
	font-size:14px;
	padding-top:7px;
	}
	.sm_h{
	font-weight:bold;
	font-size:14px;
	padding-top:10px;
	}
	.md_h{
	font-size:14px;
	font-weight:bold;
	padding-top:15px;
	}
	ol li{
	padding-top:10px;
	}
	ol.lower_alpha{list-style-type: lower-alpha;}
	
	ul.no_list_style{
	list-style-type:none;
	}
	
	.address li{
	list-style-type:none;
	padding-left:0px;
	}
	
	.sm_h{
	font-weight:bold;
	font-size:14px;
	padding-top:10px;
	padding-bottom:0px;
	}
	.md_h{
	font-size:14px;
	font-weight:bold;
	padding-top:15px;
	}
	ol li{
	padding-top:10px;
	font-size:13px;
	}
	ul li{
	padding-top:10px;
	font-size:13px;
	}
	ol.lower-roman{
	list-style-type:lower-roman;
	}
	ol.lower_alpha{list-style-type: lower-alpha;}
	
	</style>
	
	
	
	
  </head>
  <body>
  
 
  
    <div class="container-fluid" style="margin-top:4%;">
	 <div class="row clearfix">
		<div class="col-xs-12 col-sm-12 col-md-6 column">
			<div class="row clearfix">
				<div class="col-md-12 column" style="margin-top:40%;padding-left:10%;">
				
					<h3 class="text-muted text-center">
						<strong>HEALINGCLICK</strong>
					</h3>
				</div>
			</div>
		</div>
		
		
		<div class="col-xs-12 col-sm-12 col-md-6 column" style="background-color:;padding-right:%;"><ul class="breadcrumb">
				<li>
					<a href="home.jsp">Home</a> <span class="divider">/</span>
				</li><li class="active">
					Sign up
				<li>
					<a href="#">next</a> <span class="divider">/</span>
				</li>
				<!--<li class="active">
					Data
				</li-->
			</ul>
		   <div class="forms">
			
				  <div class="container" style="width:80%;background-color:white;">
					
				   <h4 style="text-align:cEnter;letter-spacing:0.5px;">Please Enter your details</h4>
				   <h2 style="text-align:cEnter;">${user}</h2>
				   <br>
					
					<form class="form-horizontal" action="pharmacyRegister.register" name="form"  style="margin-left:11%;padding-right:%;padding:%;" enctype="multipart/form-data" onSubmit="return pharmacy1();" method="post">
					
					   <div class="container" style="width:100%;">
						<div class="form-group">
							 <!--<label for="inputPassword3" class="col-sm-2 control-label">Password</label>-->
							<div class="col-sm-10">
								<input class="form-control" id="inputPassword3" name="username" type="text" data-toggle="tooltip" data-placement="top" title="Enter your user name" placeholder="Unique user name" onblur="checkUserName(3);" required/><span class="error-message" id="user1" data-value="1"></span><span class="error-message" id="user"></span>
							</div>
						</div>
						<div class="form-group">
							 <!--<label for="inputPassword3" class="col-sm-2 control-label">Password</label>-->
							<div class="col-sm-10">
								<input class="form-control" id="inputPassword3" type="password" name="password" data-toggle="tooltip" data-placement="top" title="Enter your password" placeholder="Your Password" required/><span class="error-message" id="password"></span>
							</div>
						</div>
						<!-- <div class="form-group">
							 <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
							<div class="col-sm-10">
								<input class="form-control" id="inputPassword3" name="dob" type="text" placeholder="Date Of Birth in (dd/mm/yyyy)"/>
							</div>
						</div> -->
						
						
	  					<!-- <div class="form-group" >
							 <div class="col-sm-10">
					
							<input  name="file" type="file" class="form-control"/>
							</div>
						</div> -->
						
	  						<div class="form-group">
							 <!--<label for="inputPassword3" class="col-sm-2 control-label">Password</label>-->
							<div class="col-sm-10">
								<input class="form-control" name=pharmacy id="inputPassword3" type="text" data-toggle="tooltip" data-placement="top" title="Enter your pharmacy name" placeholder="Your Pharmacy Name" required/><span class="error-message" id="pharmacy"></span>
							</div>
						</div>
						<!-- <div class="form-group">
							 <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
							<div class="col-sm-10">
								<input class="form-control" name=landline id="inputPassword3" type="number" data-toggle="tooltip" data-placement="top" title="Enter your pharmacy landline no" placeholder="Pharmacy Land Line" required/><span class="error-message" id="landline"></span>
							</div>
						</div> -->
                   <div class="form-group">
                      <div class="col-sm-10">
                          <textarea class="form-control" rows="2"  data-toggle="tooltip" data-placement="top" title="Enter your address1" name=address1 placeholder="Address1" required></textarea><span class="error-message" id="address1"></span>
                        </div>

                       </div>

							
                   <div class="form-group">
                      <div class="col-sm-10">
                          <textarea class="form-control" rows="2" name=address2 data-toggle="tooltip" data-placement="top" title="Enter your address2" placeholder="Address2" required></textarea><span class="error-message" id="address2"></span>
                        </div>

                       </div>
                       <div class="form-group">
							 <!--<label for="inputPassword3" class="col-sm-2 control-label">Password</label>-->
							<div class="col-sm-10">
								<input class="form-control" name=landmark id="inputPassword3" type="text" data-toggle="tooltip" data-placement="top" title="Enter your landmark" placeholder="Your Landmark" required/><span class="error-message" id="landmark"></span>
							</div>
						</div>
						<div class="form-group">
							 <!--<label for="inputPassword3" class="col-sm-2 control-label">Password</label>-->
							<div class="col-sm-10">
							
							<select name="country" id="country"  class="form-control" data-toggle="tooltip" data-placement="top" title="Select your country" required>
	  							
	  						</select><span class="error-message" id="country1"></span>
							
							
								<!-- <input class="form-control" id="inputPassword3" type="text" name=country placeholder="Your Country"/> -->
							</div>
						</div>
						
						<div class="form-group">
							 <!--<label for="inputPassword3" class="col-sm-2 control-label">Password</label>-->
							<div class="col-sm-10">
								
								
								<select name="state" id="state"  class="form-control" data-toggle="tooltip" data-placement="top" title="Select your state" required>
	  							
	  						</select><span class="error-message" id="state1"></span>
							</div>
						</div>
						<div class="form-group">
							 <!--<label for="inputPassword3" class="col-sm-2 control-label">Password</label>-->
							<div class="col-sm-10">
								
								<select name="city"  id="city" class="form-control" data-toggle="tooltip" data-placement="top" title="Select your city" required>
	  						</select><span class="error-message" id="city1"></span>
							
							</div>
						</div>
						
						<div class="form-group">
							 <!--<label for="inputPassword3" class="col-sm-2 control-label">Password</label>-->
							<div class="col-sm-10">
								<input class="form-control" id="inputPassword3" type="number" name=pincode data-toggle="tooltip" data-placement="top" title="Enter your pincode" placeholder="Your Pincode" required/><span class="error-message" id="pincode"></span>
							</div>
						</div>
						   
						<div class="form-group">
							 <!--<label for="inputPassword3" class="col-sm-2 control-label">Password</label>-->
							<div class="col-sm-10">
								<input class="form-control" name=licensedto id="inputPassword3" type="text" data-toggle="tooltip" data-placement="top" title="pharmacy licensed to" placeholder="Pharmacy licensed To" required/><span class="error-message" id="licensedto"></span>
							</div>
						</div>
						<div class="form-group">
							 <!--<label for="inputPassword3" class="col-sm-2 control-label">Password</label>-->
							<div class="col-sm-10">
								<input class="form-control" id="inputPassword3" type="text" data-toggle="tooltip" data-placement="top" title="proprietor name" placeholder="Proprietor Name" name=proprietorname required/><span class="error-message" id="proprietorname"></span>
							</div>
						</div>
						
						  <div class="form-group">
                      <div class="col-sm-10">
                          <textarea class="form-control" rows="2" data-toggle="tooltip" data-placement="top" title="proprietor address" placeholder="Proprietor Address" name="proprietoraddress" required></textarea><span class="error-message" id="proprietoraddress"></span>
                        </div>

                       </div>
						<!-- <div class="form-group">
							 <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
							<div class="col-sm-10">
								<input class="form-control" id="inputPassword3" type="number" data-toggle="tooltip" data-placement="top" title="proprietor contact number" placeholder="Proprietor Contact Number" name=proprietornumber required/><span class="error-message" id="proprietornumber" maxlength="10"></span>
							</div>
						</div> -->
						<div class="form-group">
							 <!--<label for="inputPassword3" class="col-sm-2 control-label">Password</label>-->
							<div class="col-sm-10">
								<input class="form-control" id="inputPassword3" type="email" data-toggle="tooltip" data-placement="top" title="proprietor email-id" placeholder="Proprietor Email Id" name=proprietoremail required/><span class="error-message" id="proprietoremail"></span>
							</div>
						</div>
						<div class="form-group">
						<div class="col-sm-10">
						<div class="checkbox" style="font-size:12px;color:grey;">		<label><input type="checkbox" required/> <p style="padding-top:3px;font-size:1.1em;">I accept the <a id="modal-965486" href="#modal-container-965486"  data-toggle="modal">terms and conditions</a></p></label></div></div>
				  </div>  </div>
						

                   <div class="col-xs-12 col-sm-10 col-md-10 column">
			 <input class="btn btn-info btn-block btn-sm" type="submit" value="Register">
		</div>
		
		
	  			<br>
				<br>
				<br>
						
						</div>
						
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			 
			
			<div class="modal fade" id="modal-container-965486" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							 
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
								X
							</button>
							<h4 class="modal-title" id="myModalLabel" style="font-family:Candara;">
								Terms and Conditions
							</h4>
						</div>
					<div class="modal-body" style="font-family:Calibri;text-align:;padding:20px 20px 20px 20px;">
						
						
						
						
						
						
						
						
						
						
						
						
	
	<div class="row">
	<div class="col-xs-12">
	
<!-- -terms start -->

<h3 class="page_heading">1.	TERMS OF USE </h3>
	<p class="md_f">
Healing Click ("us","we",or "Healing Click") is the author and publisher of the internet
resource <a href="https://www.healingclick.com"> www.healingclick.com </a>("<b>Website</b>") on the world wide web.<br/>
Healing Click owns and operates the services provided through the Website. </p>




<h4 class="m_h">
1. NATURE AND APPLICABILITY OF TERMS </h4>
<p class="md_f">Please carefully go through these terms and conditions ("Terms") and the privacy policy available at https://www.healingclick.com ("Privacy Policy") before you decide to access the Website or avail the services made available on the Website by Healing Click. These Terms and the Privacy Policy together constitute a legal agreement ("Agreement") between you and Healing Click in connection with your visit to the Website and your use of the Services (as mentioned below).
The Agreement applies to you whether you are -</p>

<ol class="">
<li>A medical practitioner or health care provider (whether an individual professional or an organization) or similar institution wishing to be listed,or already listed,on the Website,including designated,authorised associates of such Doctors or institutions("Practitioner(s)","you" or "User"); or
 </li>
<li> 	A patient,his/her representatives or affiliates,searching for Doctors through the Website ("End-User","you" or "User"); or
</li>
<li>Otherwise a user of the Website ("you" or "User").</li>
</ol>


<p class="md_f">This Agreement applies to those services made available by Healing Click on the Website,which are offered free of charge to the Users ("Services"),including the following:
 </p>
 
 
 
 
<h4 class="m_h"> For Doctors:</h4>
<p class="md_f">Listing of Doctors and their profiles and contact details,to be made available to the other Users and visitors to the Website;
</p>

<h4 class="m_h"> For other Users:</h4>

<p class="md_f">

Facility to (i) create and maintain 'Your Accounts',(ii) search for Doctors by name,specialty,and geographical area,or any other criteria that may be developed and made available by Healing Click,and (iii) to make appointments with Doctors.
</p>


<p class="md_f">
The Services may change from time to time,at the sole discretion of Healing Click,and the Agreement will apply to your visit to and your use of the Website to avail the Service,as well as to all information provided by you on the Website at any given point in time.
</p>
<p class="md_f">
This Agreement defines the terms and conditions under which you are allowed to use the Website and describes the manner in which we shall treat your account while you are registered as a member with us. If you have any questions about any part of the Agreement,feel free to contact us at support@healingclick.com.
</p>

<p class="md_f">
By accessing the Website to use the Services,you irrevocably accept all the conditions stipulated in this Agreement and agree to abide by them. This Agreement supersedes all previous oral and written terms and conditions (if any) communicated to you. By availing any Service,you signify your agreement to this Agreement.
</p>

<p class="md_f">
We reserve the right to modify or terminate any portion of the Agreement for any reason and at any time,and such modifications shall be informed to you in writing. You should read the Agreement at regular intervals. Your use of the Website following any such modification constitutes your agreement to follow and be bound by the Agreement so modified. Any additional terms and conditions,disclaimers,privacy policies and other policies applicable in general and/ or to specific areas of this Website or to particular Service are also considered as part of the Agreement.
</p>

<p class="md_f">
You acknowledge that you will be bound by this Agreement for availing any of the Services offered by us. If you do not agree with any part of the Agreement,please do not use the Website or avail any Services.
</p>

<p class="md_f">
Your access to use of the Website and the Services will be solely at the discretion of Healing Click.
</p>
<p class="md_f">
The Agreement is published in compliance of,and is governed by the provisions of Indian law,including but not limited to:
</p>






<ol>

    <li>
	The Indian Contract Act,1872,
	
   </li>
    <li>
The (Indian) Information Technology Act,2000,and

   </li>
    <li>
The rules,regulations,guidelines and clarifications framed there under,including the (Indian) Information Technology (Reasonable Security Practices and Procedures and Sensitive Personal Information) Rules,2011 (the "SPI Rules"),and the (Indian) Information Technology (Intermediaries Guidelines) Rules,2011 (the "IG Rules").

   </li>

</ol>

<h4 class="m_h">
2. CONDITIONS OF USE
</h4>



<p class="md_f">  
You must be 18 years of age or older to register,use the Services,or visit or use the Website in any manner. By registering,visiting and using the Website or accepting this Agreement,you represent and warrant to Healing Click that you are 18 years of age or older,and that you have the right,authority and capacity to use the Website and the Services available through the Website,and agree to and abide by this Agreement.
</p>

<h4 class="m_h">3. TERMS OF USE APPLICABLE TO ALL USERS OTHER THAN DOCTORS</h4>

<p class="md_f">  The terms in this Clause 3 are applicable only to Users other than Doctors.

</p>

<h5 class="sm_h"> 3.1	END-USER ACCOUNT AND DATA PRIVACY  </h5>
<ul class="no_list_style">

<li>
3.1.1	The terms "personal information" and "sensitive personal data or information" are defined under the SPI Rules,and are reproduced in the Privacy Policy.
</li>

<li>
3.1.2	Healing Click may by its Services,collect information relating to the devices through which you access the Website,and anonymous data of your usage. The collected information will be used only for improving the quality of Healing Click's services and to build new services.
</li>
<li>
3.1.3	The Website allows Healing Click to have access to registered Users' personal email or phone number,for communication purpose so as to provide you a better way of booking appointments and for obtaining feedback in relation to the Doctors and their practice.
</li>
<li>
3.1.4	The Privacy Policy sets out:
		<ul class="">
		<li> The type of information collected from Users,including sensitive personal data or information;</li>
		<li>The purpose,means and modes of usage of such information;</li>
		<li>How and to whom Healing Click will disclose such information; and,</li>
		<li>Other information mandated by the SPI Rules.</li>

		</ul>
</li>
<li>
3.1.5	The User is expected to read and understand the Privacy Policy,so as to ensure that he or she has the knowledge of:

		<ul class="">
		<li> The fact that certain information is being collected;
		</li>
		<li> The purpose for which the information is being collected;
		</li>
		<li> The intended recipients of the information;
		</li>
		<li> The nature of collection and retention of the information; and
		</li>
		<li> The various rights available to such Users in respect of such information.
		</li>
		</ul>

</li>
<li>
3.1.6	Healing Click shall not be responsible in any manner for the authenticity of the personal information or sensitive personal data or information supplied by the User to Healing Click or to any other person acting on behalf of Healing Click.
</li>
<li>
3.1.7	The User is responsible for maintaining the confidentiality of the User's account access information and password,if the User is registered on the Website. The User shall be responsible for all usage of the User's account and password,whether or not authorized by the User. The User shall immediately notify Healing Click of any actual or suspected unauthorized use of the User's account or password. Although Healing Click will not be liable for your losses caused by any unauthorized use of your account,you may be liable for the losses of Healing Click or others due to such unauthorized use.
</li>

<li>
3.1.8	If a User provides any information that is untrue,inaccurate,not current or incomplete (or becomes untrue,inaccurate,not current or incomplete),or Healing Click has reasonable grounds to suspect that such information is untrue,inaccurate,not current or incomplete,Healing Click has the right to discontinue the Services to the User at its sole discretion.
</li>

<li>
3.1.9	Healing Click may use such information collected from the Users from time to time for the purposes of debugging customer support related issues.
</li>
<li>
3.1.10	Healing Click may provide End-Users with a free facility on the Website. The specific terms relating to such account are as below,without prejudice to the rest of these Terms and the Privacy Policy:
		<ul class="no_list_style">
		<li>3.1.10.1	Your Login Account is only created after you have signed up and explicitly accepted these Terms.
		</li>
		<li>3.1.10.2	Information available in your Health Account is of two types:
		    <ul class="">
			<li>3.1.10.2.1	Patient-created: Information generated and uploaded by you.
			</li>
			<li>3.1.10.2.2	Physician-created: Information generated by your interaction with a Practitioner who uses Healing Click Website.
			</li>
			</ul>
		</li>
		<li>3.1.10.3	Any Practice created information is provided on an as-is basis and Healing Click does not validate the said information and makes no representation in connection therewith. You should contact the 
		relevant Practitioner in case you wish to point out any discrepancies or add,delete,or modify the information in any manner.
		</li>
		<li>3.1.10.4	The Account is provided on a best-efforts as-is basis. While we strive to maintain the highest levels of service availability,Healing Click is not liable for any interruption that may be caused to your access of the Services.
		</li>
		<li>3.1.10.5	The 'Innovative SMS' provided by your Account is only a supplementary way of reminding you to take your medication as prescribed by your doctor,to remind you of timely visits to the hospital/clinic ,and remind you of your vaccinations. You should refer to your prescription before taking any medicines. Healing Click is not liable if for any reason 'Innovative SMS' are not delivered to you or are delivered late or delivered incorrectly,despite its best efforts. In case you do not wish to receive the 'Innovative SMS',you can request your doctor to not enable it during your consultation or otherwise,or give a missed call to the number included in the first 'Innovative SMS' that you receive.
		</li>
		<li>3.1.10.6	It is your responsibility to keep your correct mobile number and email ID updated in the Your Account. The records will be sent to the account associated with this mobile number and/or email ID. Every time you change any contact information (mobile or email),we will send a confirmation. Healing Click is not responsible for any loss or inconvenience caused due to your non-updating of your contact details for the Your Account.
		</li>
		<li>3.1.10.7	Healing Click uses industry-level security and encryption to your Account. However,Healing Click cannot guarantee prevent unauthorized access if you lose your login credentials or they are otherwise compromised. Please safeguard your login credentials and report any actual suspected breach of account to support@healingclick.com.
		</li>
		</ul>


</li>



</ul>

	
<h5 class="sm_h">3.2 RANKING ALGORITHM</h5>
<p class="md_f">Healing Click's ranking algorithm for the Doctors is a fully automated system that lists the Doctors,their profile and information regarding their Practice on its Website. These listings of Doctors do not represent any fixed objective ranking or endorsement by Healing Click. Healing Click will not be liable for any change in the ranking of the Doctors,which may take place from time to time. The listing of Doctors will be based on automated computation of the various factors including inputs made by the Users including their comments and feedback. The ranking algorithm is proprietary technology and cannot be disclosed in full. Such factors may change from time to time,in order to improve the listing algorithm. Healing Click in no event will be held responsible for the accuracy and the relevancy of the listing order of the Doctors on the Website.
</p>

<h5 class="sm_h">3.3	LISTING CONTENT AND DISSEMINATING INFORMATION</h5>
<p class="md_f">
3.3.1	Healing Click collects,directly or indirectly,and displays on the Website,relevant information regarding the profile and practice of the Doctors listed on the Website,such as their specialisation,qualification,fees,location,visiting hours,and similar details. Healing Click takes reasonable efforts to ensure that such information is updated at frequent intervals. Although Healing Click screens and vets the information and photos submitted by the Doctors,it cannot be held liable for any inaccuracies or incompleteness represented from it,despite such reasonable efforts.
</p>
<p class="md_f">
3.3.2	The Services provided by Healing Click or any of its licensors or service providers are provided on an "as is" and "as available' basis,and without any warranties or conditions (express or implied,including the implied warranties of merchantability,accuracy,fitness for a particular purpose,title and non-infringement,arising by statute or otherwise in law or from a course of dealing or usage or trade). Healing Click does not provide or make any representation,warranty or guarantee,express or implied about the Website or the Services. Healing Click does not guarantee the accuracy or completeness of any content or information provided by Users on the Website. To the fullest extent permitted by law,Healing Click disclaims all liability arising out of the User's use or reliance upon the Website,the Services,representations and warranties made by other Users,the content or information provided by the Users on the Website,or any opinion or suggestion given or expressed by Healing Click or any User in relation to any User or services provided by such User.
</p>
<p class="md_f">
3.3.3	The Website may be linked to the website of third parties,affiliates and business partners. Healing Click has no control over,and not liable or responsible for content,accuracy,validity,reliability,quality of such websites or made available by/through our Website. Inclusion of any link on the Website does not imply that Healing Click endorses the linked site. User may use the links and these services at User's own risk.
</p>
<p class="md_f">
3.3.4	Healing Click assumes no responsibility,and shall not be liable for,any damages to,or viruses that may infect User's equipment on account of User's access to,use of,or browsing the Website or the downloading of any material,data,text,images,video content,or audio content from the Website. If a User is dissatisfied with the Website,User's sole remedy is to discontinue using the Website.
</p>
<p class="md_f">
3.3.5	If Healing Click determines that you have provided fraudulent,inaccurate,or incomplete information,including through feedback,Healing Click reserves the right to immediately suspend your access to the Website or any of your accounts with Healing Click and makes such declaration on the website alongside your name/your clinic's name as determined by Healing Click for the protection of its business and in the interests of Users. You shall be liable to indemnify Healing Click for any losses incurred as a result of your misrepresentations or fraudulent feedback that has adversely affected Healing Click or its Users.
</p>
<p class="md_f">
</p>


<h5 class="sm_h">

3.4	APPOINTMENT BOOKING AND INTERACTION WITH DOCTORS

</h5>



<p class="md_f">3.4.1	Healing Click provides value added telephonic services which connects Users directly to the Doctors and the information exchanged between the User and the Doctors is stored and used in accordance with the Privacy Policy. However,it is at the discretion of the User,to avail the Service. If a User has used the telephony service,Healing Click reserves the right to share the contact details of the User with the Doctors contacted.
</p>
<p class="md_f">3.4.2	You understand and agree that any interactions and associated issues with other Users including but not limited to your health issues and your experiences is strictly between you and the other Users. You shall not hold Healing Click responsible for any such interactions and associated issues. For avoidance of doubt,Healing Click is not involved in providing any healthcare or medical advice or diagnosis and hence is not responsible for any outcome between you and the Practitioner you interact with,pursuant to any interactions on the Website. If you decide to engage with a Practitioner to provide medical services to you,you do so at your own risk. The results of any search you perform on the Website for Doctors should not be construed as an endorsement by Healing Click of any such particular Practitioner. Healing Click shall not be responsible for any breach of service or service deficiency by any Practitioner. We cannot assure nor guarantee the ability or intent of the Practitioner(s) to fulfill their obligations towards you. We advise you to perform your own investigation prior to selecting a Practitioner.
</p>
<p class="md_f">
3.4.3	Without prejudice to the generality of the above,Healing Click will not be liable for:
</p>
		<ul class="no_list_style">
		<li>3.4.3.1	any wrong medication or treatment quality being given by the Practitioner(s),or any medical negligence on part of the Practitioner(s);
		</li><li>3.4.3.2	any type of inconvenience suffered by the User due to a failure on the part of the Practitioner to provide agreed services or to make himself/herself available at the appointed time,no show by the Practitioner,inappropriate treatment,or similar difficulties;
		</li><li>3.4.3.3	any misconduct or inappropriate behaviour by the Practitioner or the Practitioner's staff;
		</li><li>3.4.3.4	cancellation or rescheduling of booked appointment or any variance in the fees charged;
		</li><li>3.4.3.5	any medical eventualities that might occur subsequent to using the services of a Practitioner,whom the User has selected on the basis of the information available on the Website or with whom the User has booked an appointment through the Website.
		</li><li>3.4.3.6	Further,Healing Click shall not be liable,under any event,for any comments or feedback given by any of the Users in relation to the services provided by another User. All such feedback should be made in accordance with applicable law. The option of Users to give feedback remains at Healing Click's sole discretion and may be modified or withdrawn at its sole discretion. Healing Click may moderate such feedback at any time. Healing Click shall not be obliged to act in any manner to give effect to the content of Users' feedback,such as suggestions for delisting of a particular Practitioner from the Website.
		</li>
		</ul>		
<h5 class="sm_h">3.5	NO DOCTOR PATIENT RELATIONSHIP NOT FOR EMERGENCY USE</h5>

<p class="md_f">3.5.1 Please note that some of the content,text,data,graphics,images,information,suggestions,guidance,and other material (collectively,"Information") that may be available on the Website (including information provided in direct response to your questions or postings) may be provided by individuals in the medical profession. The provision of such Information does not create a licensed medical professional/patient relationship,between Healing Click and you and does not constitute an opinion,medical advice,or diagnosis or treatment of any particular condition,but is only provided to assist you with locating appropriate medical care from a qualified practitioner.
</p><p class="md_f">3.5.2	It is hereby expressly clarified that,the Information that you obtain or receive from Healing Click,and its employees,contractors,partners,sponsors,advertisers,licensors or otherwise on the Website is for informational purposes only. We make no guarantees,representations or warranties,whether expressed or implied,with respect to professional qualifications,quality of work,expertise or other information provided on the Website. In no event shall we be liable to you or anyone else for any decision made or action taken by you in reliance on such information.
</p><p class="md_f">3.5.3	The Services are not intended to be a substitute for getting in touch with emergency healthcare. If you are an End-User facing a medical emergency (either on your or a another person's behalf),please contact an ambulance service or hospital directly.
</p>



<h5 class="sm_h">3.6	HEALING CLICK QUERIES</h5>

<p class="md_f">3.6.1	The User hereby grants consent to Healing Click to transfer the health related queries posted by the User to Doctors for the provision of the services under Healing Click Consult and agrees that any such information provided by the User will be subject to our Privacy Policy.
</p><p class="md_f">3.6.2	The public queries posted by the Users are sent to multiple Doctors chosen through a fully automated system. The automated system chooses Doctors based on inter alia the information furnished by each Practitioner,the number of questions answered by the Practitioner and the rating or reviews procured by the Practitioner. Healing Click will not be responsible for the Doctors chosen to respond to the queries.
</p><p class="md_f">3.6.3	In the event the User has posted a private query,the Practitioner may indicate the pre-consultation fee payable for the advice solicited and in such case Healing Click shall provide the User an option to directly remit the amount to the Practitioner through Healing Click using the Health Account. Upon receipt of the payment,the Practitioner shall provide his response to the query. The amount paid to the Practitioner is non-refundable and Healing Click is not responsible for the nature of advice provided by the Practitioner. However,in the event,the User does not receive advice from the Practitioner,the same may be communicated to Healing Click at support@healingclick.com and Healing Click shall take reasonable endeavours to resolve the matter.
</p><p class="md_f">3.6.4	Healing Click Consult is intended for general purposes only and is not meant to be used in emergencies. All the conditions prescribed in Clause 3.5 of this Terms of Use shall apply to the Users.
</p><p class="md_f">3.6.5	The User understands and agrees that any interactions and associated issues with the Practitioner on Healing Click including but not limited to the User's health issues and the User's experiences is strictly between the User and the Practitioner. The User shall not hold Healing Click responsible for any such interactions and associated issues. Healing Click is not involved in providing any healthcare or medical advice or diagnosis and hence is not responsible for any outcome between the User and the Practitioner,the User interacts with. If you decide to engage with a Practitioner to provide medical services to you,you do so at your own risk. Healing Click shall not be responsible for any breach of service or service deficiency by any doctor or health care provider.
</p>


<h5 class="sm_h">3.7	CONTENT OWNERSHIP AND COPYRIGHT CONDITIONS OF ACCESS</h5>

<p class="md_f">1)	The contents listed on the Website are (i) User generated content,or (ii) belong to Healing Click. The information that is collected by Healing Click directly or indirectly from the End- Users and the Doctors shall belong to Healing Click. Copying of the copyrighted content published by Healing Click on the Website for any commercial purpose or for the purpose of earning profit will be a violation of copyright and Healing Click reserves its rights under applicable law accordingly.
</p><p class="md_f">2)	Healing Click authorizes the User to view and access the content available on or from the Website solely for ordering,receiving,delivering and communicating only as per this Agreement. The contents of the Website,information,text,graphics,images,logos,button icons,software code,design,and the collection,arrangement and assembly of content on the Website (collectively,"Healing Click Content"),are the property of Healing Click and are protected under copyright,trademark and other laws. User shall not modify the Healing Click Content or reproduce,display,publicly perform,distribute,or otherwise use the Healing Click Content in any way for any public or commercial purpose or for personal gain.
</p><p class="md_f">3)	User shall not access the Services for purposes of monitoring their availability,performance or functionality,or for any other benchmarking or competitive purposes.
</p>

<h5 class="sm_h">3.8	REVIEWS AND FEEDBACK</h5>
<p class="md_f">By using this Website,you agree that any information shared by you with Healing Click or with any Practitioner will be subject to our Privacy Policy.
</p><p class="md_f">You are solely responsible for the content that you choose to submit for publication on the Website,including any feedback,ratings,or reviews ("Critical Content") relating to Doctors or other healthcare professionals. The role of Healing Click in publishing Critical Content is restricted to that of an 'intermediary' under the Information Technology Act,2000. Healing Click disclaims all responsibility with respect to the content of Critical Content,and its role with respect to such content is restricted to its obligations as an 'intermediary' under the said Act. Healing Click shall not be liable to pay any consideration to any User for re-publishing any content across any of its platforms.
</p><p class="md_f">Your publication of reviews and feedback on the Website is governed by Clause 5 of these Terms. Without prejudice to the detailed terms stated in Clause 5,you hereby agree not to post or publish any content on the Website that (a) infringes any third-party intellectual property or publicity or privacy rights,or (b) violates any applicable law or regulation,including but not limited to the IG Rules and SPI Rules. Healing Click,at its sole discretion,may choose not to publish your reviews and feedback,if so required by applicable law,and in accordance with Clause 5 of these Terms. You agree that Healing Click may contact you through telephone,email,SMS,or any other electronic means of communication for the purpose of:
<ul class="no-list-style">
<li>i.	Obtaining feedback in relation to Website or Healing Click's services; and/or
</li><li>ii.	Obtaining feedback in relation to any Doctors listed on the Website; and/or
</li><li>iii.	Resolving any complaints,information,or queries by Doctors regarding your Critical Content;
</li>
<p class="md_f">and you agree to provide your fullest co-operation further to such communication by Healing Click.
</p>
<p class="md_f">Healing Click's Feedback Collection and Fraud Detection Policy,is annexed as the Schedule hereto,and remains subject always to these Terms.
</p>

<h4 class="m_h">4.	TERMS OF USE DOCTORS</h4>
<p class="md_f">The terms in this Clause 4 are applicable only to Doctors.
</p>







<h5 class="sm_h">

4.1	LISTING POLICY
</h5>

<p class="md_f">4.1.1	Healing Click,directly and indirectly,collects information regarding the Doctors' profiles,contact details,and practice,and publishes these details on the Website. On a Practitioner's request,Healing Click may take down any part of such Practitioner's profile on the Website,provided,however,that Healing Click shall at no time be under any obligation to take down publicly available information about a Practitioner,his/her profile,contact details and/or practice. All information regarding the Doctors' profiles,contact details,and practice is collected for the purpose of facilitating interaction between and among End-Users,members of the public and Doctors. If any information displayed on the Website in connection with you and your profile is found to be incorrect,you are required to inform Healing Click immediately to enable Healing Click to make the necessary amendments.
</p><p class="md_f">4.1.2	Healing Click shall not be liable and responsible for the ranking of the Doctors on external websites and search engines
</p><p class="md_f">4.1.3	Healing Click shall not be responsible or liable in any manner to the Users for any losses,damage,injuries or expenses incurred by the Users as a result of any disclosures or publications made by Healing Click,where the User has expressly or implicitly consented to the making of disclosures or publications by Healing Click. If the User had revoked such consent under the terms of the Privacy Policy,then Healing Click shall not be responsible or liable in any manner to the User for any losses,damage,injuries or expenses incurred by the User as a result of any disclosures made by Healing Click prior to its actual receipt of such revocation.
</p><p class="md_f">4.1.4	Healing Click reserves the right to moderate the suggestions made by the Doctors through feedback and the right to remove any abusive or inappropriate or promotional content added on the Website. However,Healing Click shall not be liable if any inactive,inaccurate,fraudulent,or non- existent profiles of Doctors are added to the Website.
</p><p class="md_f">4.1.5	Healing Click reserves the right to publish the User information to a third party,subject to prior explicit informed consent from the User.
</p><p class="md_f">4.1.6	You as a Practitioner hereby represent and warrant that you will use the Services in accordance with applicable law. Any contravention of applicable law as a result of your use of these Services is your sole responsibility,and Healing Click accepts no liability for the same.
</p>
<h5 class="sm_h">4.2 PROFILE OWNERSHIP AND EDITING RIGHTS
</h5>
<p class="md_f">Healing Click ensures easy access to the Doctors by providing a tool to update your profile information. Healing Click reserves the right of ownership of all the Practitioner's profile and photographs and to moderate the changes or updates requested by Doctors. However,Healing Click takes the independent decision whether to publish or reject the requests submitted for the respective changes or updates. You hereby represent and warrant that you are fully entitled under law to upload all content uploaded by you as part of your profile or otherwise while using Healing Click's services,and that no such content breaches any third party rights,including intellectual property rights. Upon becoming aware of a breach of the foregoing representation,Healing Click may modify or delete parts of your profile information at its sole discretion with or without notice to you.
</p>


<h5 class="sm_h">4.3	REVIEWS AND FEEDBACK DISPLAY RIGHTS OF HEALING CLICK</h5>
<p class="md_f">4.3.1	All Critical Content is content created by the Users of www.healingclick.com ("Website") and the clients of Healing Click customers and Doctors,including the End-Users. As a platform,Healing Click does not take responsibility for Critical Content and its role with respect to Critical Content is restricted to that of an 'intermediary' under the Information Technology Act,2000. The role of Healing Click and other legal rights and obligations relating to the Critical Conte nt. Healing Click's Feedback Collection and Fraud Detection Policy,is annexed as the Schedule hereto,and remains subject always to these Terms.
</p><p class="md_f">4.3.2	Healing Click reserves the right to collect feedback and Critical Content for all the Doctors,Clinics and Healthcare Providers listed on the Website.
</p><p class="md_f">4.3.3	Healing Click shall have no obligation to pre-screen,review,flag,filter,modify,refuse or remove any or all Critical Content from any Service,except as required by applicable law.
</p><p class="md_f">4.3.4	You understand that by using the Services you may be exposed to Critical Content or other content that you may find offensive or objectionable. Healing Click shall not be liable for any effect on Practitioner's business due to Critical Content of a negative nature. In these respects,you may use the Service at your own risk. Healing Click however,as an 'intermediary,takes steps as required to comply with applicable law as regards the publication of Critical Content. The legal rights and obligations with respect to Critical Content and any other information sought to be published by Users.
</p><p class="md_f">4.3.5	Healing Click will take down information under standards consistent with applicable law,and shall in no circumstances be liable or responsible for Critical Content,which has been created by the Users. The principles set out in relation to third party content in the terms of Service for the Website shall be applicable mutatis mutandis in relation to Critical Content posted on the Website.
</p><p class="md_f">4.3.6	If Healing Click determines that you have provided inaccurate information or enabled fraudulent feedback,Healing Click reserves the right to immediately suspend any of your accounts with Healing Click and makes such declaration on the website alongside your name/your clinics name as determined by Healing Click for the protection of its business and in the interests of Users. You shall be liable to indemnify Healing Click for any losses incurred as a result of your misrepresentations or fraudulent feedback that has adversely affected Healing Click or its Users.
</p>

<h5 class="sm_h">4.4 RANKING ALGORITHM</h5>
</h5><p class="md_f">Healing Click has designed the ranking algorithm in the best interest of the End-User and may adjust the ranking from time to time to improve the quality of the results given to the patients. It is a pure merit driven,proprietary ranking algorithm which cannot be altered for specific Doctors. Healing Click shall not be liable for any effect on the Practitioner's business interests due to the rank of the doctor in the Ranking Algorithm.
</p>
<h5 class="sm_h">4.5 INDEPENDENT SERVICES</h5>
<p class="md_f">Your use of each Service confers upon you only the rights and obligations relating to such Service,and not to any other service that may be provided by Healing Click.
</p>
<h5 class="sm_h">4.6 HEALING CLICK REACH RIGHTS</h5>
<p class="md_f">Healing Click reserves the rights to display sponsored ads on the Website. These ads would be marked as "Sponsored Listings". Without prejudice to the status of other content,Healing Click will not be liable for the accuracy of information or the claims made in the Sponsored Listings. Healing Click does not encourage the Users to visit the Sponsored Listings page or to avail any services from them. Healing Click will not be liable for the services of the providers of the Sponsored Listings.
You represent and warrant that you will use these Services in accordance with applicable law. Any contravention of applicable law as a result of your use of these Services is your sole responsibility,and Healing Click accepts no liability for the same.
</p>
<h5 class="sm_h">4.7 HEALING CLICK FOR DOCTORS</h5>
<p class="md_f">4.7.1	Healing Click enables Doctors to connect with the Users by responding to the health related queries posted by them on their Accounts 
</p><p class="md_f">4.7.2 	The User may post two types of queries on their Accounts (i) public query,which is sent through Healing Click to multiple Doctors and enables any of the chosen Doctors to respond to the query posted; and (ii) private query,which will be only sent to the Practitioner that the User has chosen.
	</p><p class="md_f">	4.7.3 	Practitioner agrees that,when providing any written response to a User's query that constitutes a performance of his/her services,the Practitioner shall not post language that may be considered abusive,objectionable or demeaning to the User or in general.
	</p><p class="md_f">	4.7.4	In the event,the Practitioner indicates as part of his response the pre-consultation fee payable for the advice solicited through the private query,Healing Click shall provide the User an option to directly remit the amount to the Practitioner through Your Account and subsequently deduct a portion from the amount paid as its fee for the performance of the services.

	</p><p class="md_f">	4.7.5	 Healing Click shall remit the fees to the Practitioner in accordance with the terms agreed between the Doctors and Healing Click in the Software License and Services Agreement executed between them.

	</p><p class="md_f">	4.7.6	Healing Click reserves the right to revise the fee terms at any time at their discretion. The Practitioner's continued use of the services and shall constitute his/her consent to such revision.

	</p><p class="md_f">	4.7.7	Practitioner hereby represents and warrants that he/she
		<ul class="no_list_style">
		<li>
		4.7.7.1	is qualified to provide medical services within the territory of India;
		
		</li><li>4.7.7.2	has obtained all licenses as required by law to provide medical services and has not committed any act or omission that might prejudice its continuance or renewal; and
			</li>
			<li>4.7.7.3	has provided Healing Click true,accurate,complete and up to date details about their qualification and credentials.
			</li>
		</ul>
		</p><p class="md_f">4.7.8	Practitioner agrees that he/she shall at all times abide by the applicable medical regulations including the code of professional ethics as prescribed under applicable laws.
		</p><p class="md_f">4.7.9	Doctors shall promptly renew their licenses required to provide medical services and notify Healing Click about the same.
		</p><p class="md_f">4.7.10	Healing Click reserves the right to terminate any account of the Practitioner in case:
			<ul class="no_list_style">
			<li>4.7.10.1	The Practitioner breaches any terms and conditions of this terms of use or privacy policy or applicable laws;
			</li><li>4.7.10.2	Healing Click is unable to verify or authenticate any information provided to it by a Practitioner; or
			</li><li>4.7.10.3	Healing Click in its sole and absolute discretion believes that actions of the Practitioner may cause legal liability for Healing Click or other Users and / or may adversely affect the services rendered by Healing Click.
			</li>
			</ul>
			</p><p class="md_f">4.7.11	Practitioner hereby agrees that,for any User that contacts the Practitioner using Healing Click,only he/she shall be allowed to perform the services for the User and that the Practitioner may under no circumstances be permitted to transfer the performance of Your Services to any other person,whether under their supervision or not. The Practitioner accepts all responsibility and liability for the use of Healing Click,including the performance of its services,by any other party claiming to be the Practitioner and hereby agrees to indemnify Healing Click against any claim or loss that may be faced by Healing Click consequent to such use.
		</p><p class="md_f">4.7.12	Practitioner hereby agrees to hold in strictest confidence all information provided by a User to him/her under all circumstances. Practitioner agrees that he/she shall not disclose any information or documentation provided by a User to any other person,nor shall he/she allow,by act or omission,such information or documentation to be acquired by any other person.
		</p><p class="md_f">4.7.13	Practitioner agrees to render his/her services and fulfil their obligations towards their patients using their best efforts,skill and ability.
		</p><p class="md_f">4.7.14	Practitioner hereby agrees to assign to Healing Click in perpetuity all intellectual property rights residing in the responses provided by him/her for use by Healing Click worldwide.
		</p><p class="md_f">4.7.15	Practitioner hereby agrees not to seek the contact details of any User or to contact any User.
		</p><p class="md_f">4.7.16	 Any communication sent by or through Healing Click to the Practitioner is based solely on information uploaded by the Users. Healing Click shall not be responsible for the incompleteness or inaccuracy such information,including if as a result of such inaccuracy,a communication is sent to an unintended recipient.
		</p><p class="md_f">4.7.17	Practitioner shall be liable to indemnify and hold Healing Click harmless from and against all actions,claims,damages,losses and expenses,including court costs and reasonable attorneys' fees,arising out of or resulting from any breach,default,contravention,non-observance,non-performance,improper performance of any of its obligations or the terms,conditions,covenants and provisions contained in this Terms of Use.
           </p>
<h5 class="sm_h">4.8	HEALING CLICK FIT</h5>
<p class="md_f">4.8.1 	Healing Click Physicians ("Physicians") is an online content platform available on the website,wherein Doctors who have a Healing Click profile and Users who have a health account can login and post health and wellness related content.
</p><p class="md_f">4.8.2	A Practitioner can use Physicians by logging in from their profile,creating original content comprising text,audio,video,images data or any combination of the same ("as defined in Clause 3.7.2"),and uploading said Content to Healing Click's servers. The Practitioner can upload their own images or choose an image from the gallery that Healing Click provides. Healing Click shall post such Content to Physicians at its own option and subject to these Terms and Conditions. The Content uploaded via Fit does not constitute medical advice and may not be construed as such by any person.
</p><p class="md_f">4.8.3	The Practitioner acknowledges that they are the original authors and creators of any Content or comments uploaded by them via Physicians and that no Content or comment uploaded by them would constitute infringement of the intellectual property rights of any other person. Healing Click reserves the right to remove any Content or comment which it may determine at its own discretion as violating the intellectual property rights of any other person. The Practitioner agrees to absolve Healing Click from and indemnify Healing Click against all claims that may arise as a result of any third party intellectual property right claim that may arise from the Practitioner's uploading of any Content on Fit. The Practitioner also agrees to absolve Healing Click from and indemnify Healing Click against all claims that may arise as a result of any third party intellectual property claim if the Practitioner downloads an image from Healing Click's gallery and utilizes it for his/her personal or commercial gain.
</p><p class="md_f">4.8.4	The Practitioner hereby assigns to Healing Click,in perpetuity and worldwide,all intellectual property rights in any Content or comments created by the Practitioner and uploaded by the Practitioner via Physicians.
</p><p class="md_f">4.8.5	Healing Click shall have the right to edit or remove the Content and any comments in such manner as it may deem fit at any time.
</p><p class="md_f">4.8.6	The Practitioner may also use Physicians in order to view original content created by Users or other Doctors and also create and upload comments on such Content including their own content where allowed.
</p><p class="md_f">4.8.7	Practitioner acknowledges that the content on Physicians reflects the views and opinions of the authors of such content and does not necessarily reflect Healing Click's views.
</p><p class="md_f">4.8.8	Practitioner agrees not to post any comments or upload any Content which is defamatory,obscene and objectionable or in nature and Healing Click reserves the right to remove any comments which it may determine at its own discretion to violate these Terms and Conditions or be violative of any law or statute in force at the time. The Practitioner agrees to absolve Healing Click from and indemnify Healing Click against all claims that may arise as a result of any legal claim arising from the nature of the Content or the comments posted by the Practitioner on Physicians.
</p>






<h5 class="sm_h">5.	RIGHTS AND OBLIGATIONS RELATING TO CONTENT</h5>
<p class="md_f">5.1	As mandated by Regulation 3(2) of the IG Rules,Healing Click hereby informs Users that they are not permitted to host,display,upload,modify,publish,transmit,update or share any information that:
</p>
	<ol class="lower-roman">
	<li>	belongs to another person and to which the User does not have any right to;
	</li><li>	is grossly harmful,harassing,blasphemous,defamatory,obscene,pornographic,pedophilic,libelous,invasive of another's privacy,hateful,or racially,ethnically objectionable,disparaging,relating or encouraging money laundering or gambling,or otherwise unlawful in any manner whatever;
	</li><li>	harm minors in any way;
	</li><li>	infringes any patent,trademark,copyright or other proprietary rights;
	</li><li>	violates any law for the time being in force;
	</li><li>	deceives or misleads the addressee about the origin of such messages or communicates any information which is grossly offensive or menacing in nature;
	</li><li>	impersonate another person;
	</li><li>	contains software viruses or any other computer code,files or programs designed to interrupt,destroy or limit the functionality of any computer resource;
	</li><li>threatens the unity,integrity,defence,security or sovereignty of India,friendly relations with foreign states,or public order or causes incitement to the commission of any cognisable offence or prevents investigation of any offence or is insulting any other nation.
	</li>
	</ol>


<p class="md_f">5.2 	Users are also prohibited from:</p>
<ol class="lower-roman">
	<li>	violating or attempting to violate the integrity or security of the Website or any Healing Click Content;
</li><li>transmitting any information (including job posts,messages and hyperlinks) on or through the Website that is disruptive or competitive to the provision of Services by Healing Click;
</li><li>	intentionally submitting on the Website any incomplete,false or inaccurate information;
</li><li>	making any unsolicited communications to other Users;
</li><li>	using any engine,software,tool,agent or other device or mechanism (such as spiders,robots,avatars or intelligent agents) to navigate or search the Website;
</li><li>	attempting to decipher,decompile,disassemble or reverse engineer any part of the Website;
</li><li>	copying or duplicating in any manner any of the Healing Click Content or other information available from the Website;
</li><li>	framing or hot linking or deep linking any Healing Click Content.
</li>
</ol>
</p><p class="md_f">5.3	Healing Click,upon obtaining knowledge by itself or been brought to actual knowledge by an affected person in writing or through email signed with electronic signature about any such information (as mentioned above) generated by Users,or on being notified by the appropriate Government or its agency that the Website is being used by the User to commit any unlawful act and/or is being used in violation of Clauses 5.1 and 5.2 above,shall be entitled to remove or disable access to the material or information that is in contravention of this Clause 5. Healing Click is entitled to act,as required by the IG Rules,within thirty six hours of obtaining such knowledge and,where applicable,work with Users to disable such information that is in contravention of applicable law. Healing Click shall also be entitled to preserve such information and associated records for at least 90 (ninety) days for production to governmental authorities for investigation purposes.
</p><p class="md_f">5.4	In case of non-compliance with any applicable laws,rules or regulations,or the Agreement (including the Privacy Policy) by a User,Healing Click has the right to immediately terminate the access or usage rights of the User to the Website and Services and to remove non-compliant information from the Website.

</p><p class="md_f">5.5	Healing Click may disclose or transfer User-generated information to its affiliates or governmental authorities in such manner as permitted or required by applicable law,and you hereby consent to such transfer. Healing Click will comply with any duly-issued government or court directions to disable access to the User-generated information,should it be found to be illegal by a competent governmental authority.
</p>

<h5 class="sm_h">6.	TERMINATION</h5>
<p class="md_f">6.1	Healing Click reserves the right to suspend or terminate a User's access to the Website and the Services with or without notice and to exercise any other remedy available under law,in cases where,
</p>
<ol class="lower-roman">
	<li>		Such User breaches any terms and conditions of the Agreement;
</li><li>	A third party reports violation of any of its right as a result of your use of the Services;
</li><li>	Healing Click is unable to verify or authenticate any information provide to Healing Click by a User;
</li><li>	Healing Click has reasonable grounds for suspecting any illegal,fraudulent or abusive activity on part of such User; or
</li><li>	Healing Click believes in its sole discretion that User's actions may cause legal liability for such User,other Users or for Healing Click or are contrary to the interests of the Website.
</li>
</ol>

<p class="md_f">6.2	Once temporarily suspended,indefinitely suspended or terminated,the User may not continue to use the Website under the same account,a different account or re-register under a new account. On termination of an account due to the reasons mentioned herein,such User shall no longer have access to data,messages,files and other material kept on the Website by such User. The User shall ensure that he/she/it has continuous backup of any medical services the User has rendered in order to comply with the User's record keeping process and practices.
</p>

<h5 class="sm_h">7.	LIMITATION OF LIABILITY</h5>
<p class="md_f">In no event,including but not limited to negligence,shall Healing Click,or any of its partners,officers,employees,agents or content or service providers (collectively,the "Protected Entities") be liable for any direct,indirect,special,incidental,consequential,exemplary or punitive damages arising from,or directly or indirectly related to,the use of,or the inability to use,the Website or the content,materials and functions related thereto,the Services,User's provision of information via the Website,lost business or lost End-Users,even if such Protected Entity has been advised of the possibility of such damages. In no event shall the Protected Entities be liable for:
</p>
<ol class="lower-roman">
	<li>		provision of or failure to provide all or any service by Doctors to End- Users contacted or managed through the Website;
</li><li>	any content posted,transmitted,exchanged or received by or on behalf of any User or other person on or through the Website;
</li><li>	any unauthorized access to or alteration of your transmissions or data; or
</li><li>	any other matter relating to the Website or the Service.
</li>
</ol>


<p class="md_f">In no event shall the total aggregate liability of the Protected Entities to a User for all damages,losses,and causes of action (whether in contract or tort,including,but not limited to,negligence or otherwise) arising from this Agreement or a User's use of the Website or the Services exceed,in the aggregate Rs. 1000/- (Rupees One Thousand Only).
</p>

<h5 class="sm_h">8. INDEMNITY</h5>
<p class="md_f">User agrees to indemnify and hold harmless Healing Click,its affiliates,officers,partners,employees,consultants,licensors,agents,and representatives from any and all third party claims,losses,liability,damages,and/or costs (including reasonable attorney fees and costs) arising from User's access to or use of Service,violation of this Agreement,or infringement,or infringement by any other User of his/her/its account,of any intellectual property or other right of any person or entity. Healing Click will notify you promptly of any such claim,loss,liability,or demand,and in addition to your foregoing obligations,you agree to provide us with reasonable assistance,at your expense,in defending any such claim,loss,liability,damage,or cost.
</p>

<h5 class="sm_h">9. APPLICABLE LAW AND DISPUTE SETTLEMENT</h5>
<p class="md_f">9.1	You agree that this Agreement and any contractual obligation between Healing Click and User will be governed by the laws of India.
</p><p class="md_f">9.2	Any dispute,claim or controversy arising out of or relating to this Agreement,including the determination of the scope or applicability of this Agreement to arbitrate,or your use of the Website or the Services or information to which it gives access,shall be determined by arbitration in India,before a sole arbitrator appointed by Healing Click. Arbitration shall be conducted in accordance with the Arbitration and Conciliation Act,1996. The seat of such arbitration shall be Bangalore. All proceedings of such arbitration,including,without limitation,any awards,shall be in the English language. The award shall be final and binding on the parties to the dispute.
<p class="md_f">9.3	Subject to the above Clause 9.2,the courts at Bengaluru shall have exclusive jurisdiction over any disputes arising out of or in relation to this Agreement,your use of the Website or the Services or the information to which it gives access.
</p>

<h5 class="sm_h">10. CONTACT INFORMATION GRIEVANCE OFFICER</h5>
<p class="md_f">10.1	If a User has any questions concerning Healing Click,the Website,this Agreement,the Services,or anything related to any of the foregoing,Healing Click customer support can be reached at the following email support@healingclick.com or via the contact information available from the following hyperlink: www.healingclick.com/contact.
</p><p class="md_f">10.2	In accordance with the Information Technology Act,2000,and the rules made there under,if you have any grievance with respect to the Website or the service,including any discrepancies and grievances with respect to processing of information,you can contact our Grievance Officer at:
</p>		
			<ul class="no_list_style">

			<li>Name: Navyashree S
			</li><li>Designation: Partner
			</li><li>Address: 227,Kallappa Layout,4th Cross,Amruthahalli,Bangalore -      560 092,India.
			</li><li>Email: support@healingclick.com
			</li><li>Telephone: +91-********** (Ask to be connected to the Grievance Officer)
			</li><li>In the event you suffer as a result of access or usage of our Website by any person in violation of Rule 3 of the IG Rules,please address your grievance to the above person.
		   </li>
		 </ul>
			
			<h5 class="sm_h">11. SEVERABILITY </h5>
<p class="md_f">If any provision of the Agreement is held by a court of competent jurisdiction or arbitral tribunal to be unenforceable under applicable law,then such provision shall be excluded from this Agreement and the remainder of the Agreement shall be interpreted as if such provision were so excluded and shall be enforceable in accordance with its terms; provided however that,in such event,the Agreement shall be interpreted so as to give effect,to the greatest extent consistent with and permitted by applicable law,to the meaning and intention of the excluded provision as determined by such court of competent jurisdiction or arbitral tribunal.
</p>
<h5 class="sm_h">12. WAIVER </h5>
<p class="md_f">No provision of this Agreement shall be deemed to be waived and no breach excused,unless such waiver or consent shall be in writing and signed by Healing Click. Any consent by Healing Click to,or a waiver by Healing Click of any breach by you,whether expressed or implied,shall not constitute consent to,waiver of,or excuse for any other different or subsequent breach.
</p>



















</div>
</div>



	
	
	</div>

						

						<div class="modal-footer">
							 
							<button type="button" class="btn btn-warning btn-xs" data-dismiss="modal">
								Close
							</button> 
							
						</div>
					</div>
					
				</div>
				
			</div>
			
		</div>
	</div>
</div>
    <!-- Include all compiled plugins (below),or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    <script>
    
    populateCountries("country","state" ,"city");
    </script>
  </body>
</html>