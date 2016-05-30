<%@ include file="doctorHeader.jsp" %>

<script type="text/javascript" src="js/validation.js"></script><script>
function preventBackButton(){window.history.forward();}
setTimeout("preventBackButton()", 0);
window.onunload=function(){null};
</script>

<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script type="text/javascript" src="js/countries.js"></script>
<style type="text/css">




</style>
<div class="container-fluid profpbelowimage" >
	<div class="row clearfix" >
		
			
		
				<div class="col-xs-12 col-sm-10 col-md-8 column col-md-offset-2 col-sm-offset-1" style="word-wrap:break-word;">
				<h4 class="text-center profpheader">EDIT PROFILE</h4>
							<div class="panel panel-default">
						
							 <div class="panel-body">
							  
				 <h5 class="filldetailsheading">  PLEASE FILL THE DETAILS BELOW</h5> 
					<hr align="left"  style="margin-top:-7px;border:2px solid #ffa500;width:70px;margin-left:10px;">
					
				   <form action="updateDoctor.profile" method="post" name="form" onSubmit="return postdoctor();">
			<div class="row profpd" >
              <div class="col-xs-6 col-sm-3 col-md-3 profplabel column">	First Name:</div>
             <div class="col-xs-12 col-sm-9 col-md-9 column" ><div class="col-xs-9 col-sm-9 col-md-9 column"><input type="text" class="form-control  pfilldetailsinput" name="firstname" placeholder="First Name" required/></div><span class="error-message" id="firstname"></span></div>
             
            </div>
            <div class="row profpd" >
              <div class="col-xs-6 col-sm-3 col-md-3 profplabel column">	Last Name:</div>
             <div class="col-xs-12 col-sm-9 col-md-9 column" ><div class="col-xs-9 col-sm-9 col-md-9 column"><input type="text" class="form-control  pfilldetailsinput" name="lastname" placeholder="Last Name" required/></div><span class="error-message" id="lastname"></span></div>
             
            </div>			 
			<div class="row profpd" >
              <div class="col-xs-6 col-sm-3 col-md-3 profplabel column">	Gender:</div>
             <div class="col-xs-12 col-sm-9 col-md-9 column" ><div class="col-xs-9 col-sm-9 col-md-9 column">
								
							<select name="gender"   class="form-control  pfilldetailsinput">
	  							<option value="0">Your Gender</option>
	  							<option value="1">Male</option>
	  							<option value="2">Female</option>
	  							
	  						</select>
	  						</div><span class="error-message" id="gender"></span></div>
            </div>	
           	    
			<div class="row profpd" >
              <div class="col-xs-6 col-sm-3 col-md-3 profplabel column">Date Of Birth:</div>
             <div class="col-xs-12 col-sm-9 col-md-9 column" ><div class="col-xs-9 col-sm-9 col-md-9 column"><input class="form-control  pfilldetailsinput" id="dob" name="dob" type="date" placeholder="Date of Birth (dd/mm/yyyy)" required/></div><span class="error-message" id="dob"></span></div>
            </div>	
          	 
			<div class="row profpd" >
              <div class="col-xs-6 col-sm-3 col-md-3 profplabel column">Blood Group:</div>
             <div class="col-xs-12 col-sm-9 col-md-9 column" ><div class="col-xs-9 col-sm-9 col-md-9 column">
								
							<select name="bloodgroup"   class="form-control  pfilldetailsinput" required>
	  							<option value="0">Your Blood Group</option>
	  							<option value="1">A+</option>
	  							<option value="2">A-</option>
	  							<option value="3">B+</option>
	  							<option value="4">B-</option>
	                            <option value="5">O+</option>
	                            <option value="6">O-</option>
	                            <option value="7">AB+</option>
	                            <option value="8">AB-</option>
	  						</select>
	  						</div><span class="error-message" id="bloodgroup"></span></div>
            </div>		
	  <div class="row profpd" >
              <div class="col-xs-6 col-sm-3 col-md-3 profplabel column">Qualification:</div>
             <div class="col-xs-12 col-sm-9 col-md-9 column" ><div class="col-xs-9 col-sm-9 col-md-9 column"><input class="form-control  pfilldetailsinput" name="qualification" type="text" placeholder="Qualification" required/></div><span class="error-message" id="qualification"></span></div>
            </div>
            <div class="row profpd" >
              <div class="col-xs-6 col-sm-3 col-md-3 profplabel column">Speciality:</div>
             <div class="col-xs-12 col-sm-9 col-md-9 column" ><div class="col-xs-9 col-sm-9 col-md-9 column"><input class="form-control  pfilldetailsinput" name="speciality" type="text" placeholder="Speciality" required/></div><span class="error-message" id="speciality"></span></div>
            </div>
            <div class="row profpd" >
              <div class="col-xs-6 col-sm-3 col-md-3 profplabel column">Work Location:</div>
             <div class="col-xs-12 col-sm-9 col-md-9 column" ><div class="col-xs-9 col-sm-9 col-md-9 column"><input class="form-control  pfilldetailsinput" name="worklocation" type="text" placeholder="WorkLocation" required/></div><span class="error-message" id="worklocation"></span></div>
            </div>
			<div class="row profpd" >
              <div class="col-xs-6 col-sm-3 col-md-3 profplabel column">Address1:</div>
             <div class="col-xs-12 col-sm-9 col-md-9 column" ><div class="col-xs-9 col-sm-9 col-md-9 column"><input class="form-control  pfilldetailsinput" name="address1" type="text" placeholder="Address1" required/></div><span class="error-message" id="address1"></span></div>
            </div>	
            <div class="row profpd" >
              <div class="col-xs-6 col-sm-3 col-md-3 profplabel column">Address2:</div>
             <div class="col-xs-12 col-sm-9 col-md-9 column" ><div class="col-xs-9 col-sm-9 col-md-9 column"><input class="form-control  pfilldetailsinput" name="address2" type="text" placeholder="Address2" required/></div><span class="error-message" id="address2"></span></div>
            </div>
            <div class="row profpd" >
              <div class="col-xs-6 col-sm-3 col-md-3 profplabel column">LandMark:</div>
             <div class="col-xs-12 col-sm-9 col-md-9 column" ><div class="col-xs-9 col-sm-9 col-md-9 column"><input class="form-control  pfilldetailsinput" name="landmark" type="text" placeholder="LandMark" required/></div><span class="error-message" id="landmark"></span></div>
            </div>
             <div class="row profpd" >
              <div class="col-xs-6 col-sm-3 col-md-3 profplabel column">Country:</div>
             <div class="col-xs-12 col-sm-9 col-md-9 column" ><div class="col-xs-9 col-sm-9 col-md-9 column"><select name="country" id="country"  class="form-control" data-toggle="tooltip" data-placement="top" title="Select your country" required></select></div><span class="error-message" id="country1"></span></div>
            </div>
            <div class="row profpd" >
              <div class="col-xs-6 col-sm-3 col-md-3 profplabel column">State:</div>
             <div class="col-xs-12 col-sm-9 col-md-9 column" ><div class="col-xs-9 col-sm-9 col-md-9 column"><select name="state" id="state"  class="form-control" data-toggle="tooltip" data-placement="top" title="select your state" required></select></div><span class="error-message" id="state1"></span></div>
            </div>
            <div class="row profpd" >
              <div class="col-xs-6 col-sm-3 col-md-3 profplabel column">City:</div>
             <div class="col-xs-12 col-sm-9 col-md-9 column" ><div class="col-xs-9 col-sm-9 col-md-9 column"><select name="city"  id="city" class="form-control" data-toggle="tooltip" data-placement="top" title="select your city" required></select></div><span class="error-message" id="city1"></span></div>
            </div>
            
           
            <div class="row profpd" >
              <div class="col-xs-6 col-sm-3 col-md-3 profplabel column">Pincode:</div>
             <div class="col-xs-12 col-sm-9 col-md-9 column" ><div class="col-xs-9 col-sm-9 col-md-9 column"><input class="form-control  pfilldetailsinput" name="pincode" type="number" placeholder="PinCode" required/></div><span class="error-message" id="pincode"></span></div>
            </div>		 
			
            <div class="row profpd" >
              <div class="col-xs-6 col-sm-3 col-md-3 profplabel column">Landline Number:</div>
             <div class="col-xs-12 col-sm-9 col-md-9 column" ><div class="col-xs-9 col-sm-9 col-md-9 column"><input class="form-control  pfilldetailsinput" name="landline" type="number" placeholder="LandLine" required/></div><span class="error-message" id="landline"></span></div>
            </div>			 			
            
			<div class="row profpd" >
              <div class="col-xs-6 col-sm-3 col-md-3 profplabel column">About ${doctor.firstName}:</div>
             <div class="col-xs-12 col-sm-9 col-md-9 column" ><div class="col-xs-9 col-sm-9 col-md-9 column"><input class="form-control  pfilldetailsinput" name="aboutme" type="text" placeholder="About Yourself" required/></div><span class="error-message" id="aboutme"></span></div>
            </div>	
            
            <div class="row profpd text-center" >
             <div class="col-xs-6 col-sm-3 col-md-3  column"></div>
             <div class="col-xs-12 col-sm-9 col-md-9 column" ><div class="col-xs-9 col-sm-9 col-md-9 column"><input type="submit" value="Update"  class="btn btn-sm btn-warning peditbtn"></div></div>
            </div>	
            </form>				
					</div>
					</div>
					
					
					
				
				</div>
			</div>

</div>





			<script src="js/bootstrap.min.js"></script>
			<script>
    
    populateCountries("country", "state" , "city");
    $('#dob').prop('max', function(){
        return new Date().toJSON().split('T')[0];
    });
    </script>
</body>
</html>










