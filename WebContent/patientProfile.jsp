<%@ include file="patientHeader.jsp" %>
<script>
function preventBackButton(){window.history.forward();}
setTimeout("preventBackButton()", 0);
window.onunload=function(){null};
</script>

<script>
function image()
{
	 window.location = 'image.jsp';
	
}
  </script>         

<script>
	
	$(function() {
		$("#sug").click(function() {
			$("#notsug").hide();
		
	});
		
$("#friend").click(function() {
$("#notfriend").hide();

});
	});
</script>



	<div class="container-fluid profpbelowimage" >
	<div class="row clearfix" >
		
		
				<div class="col-xs-12 col-sm-10 col-md-8 column col-md-offset-2 col-sm-offset-1" style="word-wrap:break-word;">
					<h4 class="text-center profpheader"> YOUR PROFILE</h4>
						
						<div class="panel panel-default">
						<div class="panel-body">
							 
							     <a href="patientEdit.profile" class="btn  btn-warning btn-xs pull-right" type="button" >Edit Profile</a>
							 </div>			
				   
			<div class="row profpd" >
               <div class="col-xs-4 col-sm-3 col-md-3  column">	Name</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column" >${patient.firstName} ${patient.lastName}</div>
            </div>			 
			<div class="row profpd" >
              <div class="col-xs-4 col-sm-3 col-md-3 column" >	Gender:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column" >${gender}</div>
            </div>	
           	    
			<div class="row profpd" >
              <div class="col-xs-4 col-sm-3 col-md-3 column" >Date Of Birth:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column" >${patient.dob}</div>
            </div>	
          	 
			<div class="row profpd" >
              <div class="col-xs-4 col-sm-3 col-md-3 column" >Blood Group:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column" ><c:if test="${patient.bloodGroup == 1}">A+</c:if>
             <c:if test="${patient.bloodGroup == 2}">A-</c:if>
             <c:if test="${patient.bloodGroup == 3}">B+</c:if>
             <c:if test="${patient.bloodGroup == 4}">B-</c:if>
             <c:if test="${patient.bloodGroup == 5}">O+</c:if>
             <c:if test="${patient.bloodGroup == 6}">O-</c:if>
             <c:if test="${patient.bloodGroup == 7}">AB+</c:if>
             <c:if test="${patient.bloodGroup == 8}">AB-</c:if></div>
            </div>		
	    
			<div class="row profpd" >
              <div class="col-xs-4 col-sm-3 col-md-3 column" >Address:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column" >${patient.landMark}<br>${patient.address1}<br>${patient.address2}<br>${patient.city} - ${patient.pinCode}<br>${patient.state}<br>${patient.country}</div>
            </div>			 
			<div class="row profpd" >
              <div class="col-xs-4 col-sm-3 col-md-3 column" >Contact Number:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column" >${patient.mobile}</div>
            </div>
            <div class="row profpd" >
              <div class="col-xs-4 col-sm-3 col-md-3 column" >Landline Number:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column" >${patient.landLine}</div>
            </div>	
            <div class="row profpd" >
              <div class="col-xs-4 col-sm-3 col-md-3 column" >Emergency Contacts:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column" >${patient.emergency1}<br>${patient.emergency2}</div>
            </div>
         	
			<div class="row profpd" >
              <div class="col-xs-4 col-sm-3 col-md-3 column" > Email:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column" >${patient.emailId}</div>
            </div>	
          	 
			<div class="row profpd" >
              <div class="col-xs-4 col-sm-3 col-md-3 column" >About ${patient.firstName}:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column" >${patient.aboutMe}</div>
            </div>
					</div>
					</div>					
					</div>
					</div>
					

			<script src="js/bootstrap.min.js"></script>
</body>
</html>
