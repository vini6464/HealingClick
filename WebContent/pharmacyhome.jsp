
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

$( document ).ready(function() {
	 var insert = "${insert}";
		if(insert == 1)
			{
				window.history.pushState("", "", 'pharmacy.home');
			}
	
});
</script>


<div class="h_30"></div>
	<div class="container-fluid" style="margin-top: 1%;">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="row clearfix">
						<div class="col-sm-5  col-md-2 column  a_left">
					 	 
					 	
					</div>
					<div class="col-xs-12 col-sm-7 col-md-6 column">
						
						<h4 class="text-center"
							style="font-size: 1.2em; font-weight: bold; letter-spacing: 1px;font-family:'Josefin Sans', sans-serif;">
							UNDELIVERED ORDERS</h4>
						
						  <div class="panel panel-default">
							<div class="col-sm-4 column" style="color: red;">${error}</div>
							
							
							<c:if test="${empty orders}"><p style="padding:10px;text-align:center;">No Orders are Available to Deliver</p></c:if>
							<c:if test="${not empty orders}">
								<c:forEach var="order" items="${orders}">
                                  
									<div class="panel-body">
										
											<c:if test="${order.doctorId ne 0}"><a style="font-weight:; text-transform: uppercase;" href="pharmacyViewDoctor.profile?id=${order.doctorId}">${order.doctorName}</a></c:if>
											<c:if test="${order.patientId ne 0}"><a style="font-weight:; text-transform: uppercase;" href="pharmacyViewPatient.profile?id=${order.patientId}">${order.patientName}</a></c:if>
											<c:if test="${order.pharmacyId ne 0}"><a style="font-weight:; text-transform: uppercase;" href="pharmacyViewPharmacy.profile?id=${order.pharmacyId}">${order.pharmacyName}</a></c:if>
										 <span style="padding: 0px 2% 0px 2%;">Has Made An
											Order</span>
									</div>
									<div class="panel-footer">
										<div class="row">
											<c:if test="${order.status == 1}">
												<div class="col-xs-12 col-sm-12 col-md-6 column"></div>
												<div class="col-xs-4 col-sm-4 col-md-3 column">
													<a href="viewOrder.pharmacy?id=${order.id}"
														class="btn  btn-block btn-xs" type="button"
														style="font-weight: bold;">View Order</a>
												</div>
												<div class="col-xs-4 col-sm-4 col-md-3 column">
													<div id="${order.id}">
														<input class="btn  btn-block btn-xs" type="button"
															style="font-weight: bold; width: %;" value=Verify
															onclick="verifyOrder(${order.id})">
													</div>
												</div>
											</c:if>


											<c:if test="${order.status == 2}">
												<div class="col-xs-12 col-sm-12 col-md-6 column"></div>
												<div class="col-xs-4 col-sm-4 col-md-3 column">
													<a href="viewOrder.pharmacy?id=${order.id}"
														class="btn  btn-block btn-xs" type="button"
														style="font-weight: bold;">View Order</a>
												</div>
												<div class="col-xs-4 col-sm-4 col-md-3 column">
													<div id="${order.id}">
														<input class="btn btn-block btn-xs" type="button"
															style="font-weight: bold; width: %;" value=Deliver
															onclick="deliverOrder(${order.id})">
													</div>
												</div>
											</c:if>
											</div>
											</div>
								</c:forEach>
							</c:if>
						</div>
					</div>


			<br>
			


<%@ include file="pharmacyAside.jsp" %>
			
		</div>
</div>
</div>
</div>
		<script src="js/bootstrap.min.js"></script>
</body>
</html>
