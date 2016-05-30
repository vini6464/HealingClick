<%@ include file="pharmacyHeader.jsp" %>
<script>
function preventBackButton(){window.history.forward();}
setTimeout("preventBackButton()", 0);
window.onunload=function(){null};
</script>






<script>$(function() {
	$("#ord").click(function() {
		$("#notord").hide();
	
});		
});
</script>



<div class="container-fluid profpbelowimage" >
	<div class="row clearfix" >
		
			
		
				<div class="col-md-8 column col-md-offset-2 col-sm-offset-1 col-xs-offset-1" style="word-wrap:break-word;">
				<h4 class="text-center profpheader"> YOUR PROFILE</h4>
							<div class="panel panel-default">
						     <div class="panel-body">
							<a href="pharmacyCostEdit.pharmacy" class="btn   btn-xs pull-left" type="button" >Edit Delivery Charge And Discount</a>&nbsp;&nbsp;&nbsp;
							    <a href="pharmacyEdit.profile" class="btn   btn-xs pull-right" type="button" >Edit Profile</a>
							 </div>			
				  
			 
			 <div class="row profpd" >
              <div class="col-xs-4 col-sm-3 col-md-3  column">Rating</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column" >${pharmacy.rating}</div>
            </div>
			 
			<div class="row profpd" >
              <div class="col-xs-4 col-sm-3 col-md-3  column">	Pharmacy Name</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column" >${pharmacy.pharmacyName}</div>
            </div>
            
             <div class="row profpd">
              <div class="col-xs-4 col-sm-3 col-md-3  column">	Delivery Charges:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column"> ${pharmacy.deliveryCharge}Rs.</div>
            </div>
            
            <div class="row profpd">
              <div class="col-xs-4 col-sm-3 col-md-3  column">	Discount:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column"> ${pharmacy.discount}%</div>
            </div>
            
			<div class="row profpd" >
              <div class="col-xs-4 col-sm-3 col-md-3  column">Pharmacy Address</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column" >${pharmacy.landMark}<br>${pharmacy.address1}<br>${pharmacy.address2}<br>${pharmacy.city} - ${pharmacy.pinCode}<br>${pharmacy.state}<br>${pharmacy.country}</div>
            </div>			 
			<div class="row profpd" >
              <div class="col-xs-4 col-sm-3 col-md-3  column">Contact Number</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column" >${pharmacy.mobile}</div>
            </div>
            <div class="row profpd" >
              <div class="col-xs-4 col-sm-3 col-md-3  column">Landline Number</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column" >${pharmacy.landLine}</div>
            </div>			 		
	   
			<div class="row profpd" >
              <div class="col-xs-4 col-sm-3 col-md-3  column"> Email</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column" >${pharmacy.emailId}</div>
            </div>	
           	 
			<div class="row profpd">
              <div class="col-xs-4 col-sm-3 col-md-3  column">About ${pharmacy.pharmacyName}</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column" >${pharmacy.aboutMe}</div>
            </div>									
					</div>
					<div class="panel" style="min-width: 350px;">
					
					<c:if test="${not empty reviews}">
							<c:forEach var="review" items="${reviews}">
							<div class="panel-body">
							<h5>
								<c:if test="${review.type == 1}"><a href="pharmacyViewDoctor.profile?id=${review.reviewerId}">Dr . ${review.reviewerName}</a></c:if>
								
								<c:if test="${review.type == 2}"><a href="pharmacyViewPatient.profile?id=${review.reviewerId}">${review.reviewerName}</a></c:if>
								<c:if test="${review.type == 3}"><a href="pharmacyViewPharmacy.profile?id=${review.reviewerId}"> ${review.reviewerName}</a></c:if>
								<span class="pull-right glyphicon glyphicon-list-alt" style="color:;"></span></h5>
							
								<div class="row">
									<div class="col-xs-6 col-sm-6 col-md-6 column">
										<ul style="list-style: none; padding: 2px;">

											<li>Rating :</li>
											<li>Comment :</li>
											
										</ul>
									</div>

									<div class="col-xs-6 col-sm-6 col-md-6 column">
										<ul style="list-style: none; padding: 2px;">
											<li>${review.rating}</li>
											<li>${review.comment}</li>
								
										</ul>
									</div>
								</div>
							</div>	
							</c:forEach>
							</c:if>
						</div>
					</div>	
									
					</div>
					</div>
					
			

			<script src="js/bootstrap.min.js"></script>
</body>
</html>