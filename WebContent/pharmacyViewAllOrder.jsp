 <%@ include file="pharmacyHeader.jsp" %>
<script>
function preventBackButton(){window.history.forward();}
setTimeout("preventBackButton()", 0);
window.onunload=function(){null};
</script>

<style type="text/css">
#bactive{
  background-color:white;color:#ff8400;}
  
  #binactive{background-color:white;color:black;}

</style>

<script>$(function() {
	$("#ord").click(function() {
		$("#notord").hide();
	
});		
});
</script>




		<div class="container" style="margin-top: 4%;">
		
		
				
			<div class="row clearfix">
				<div class="col-xs-12 col-sm-3 col-md-2 column col-md-offset-1">
				
				<c:if test="${type == 1 }">

							
								<a href="getAllUrgentOrder.pharmacy" class="btn  btn-block btn-sm"
									type="button" id="bactive">Urgent Order</a>
						
								<a href="getAllNormalOrder.pharmacy" class="btn  btn-block btn-sm"
									type="button" id="binactive"
									>Normal
									Order</a>
						
								<a href="getAllDeliveredOrder.pharmacy" class="btn  btn-block btn-sm"
									type="button" id="binactive">Delivered Order</a>
							
							
						
								<a href="getAllMyOrder.pharmacy" class="btn  btn-block btn-sm"
									type="button" id="binactive">My Order</a>
							
						</c:if>

						<c:if test="${type == 2 }">

							
								<a href="getAllUrgentOrder.pharmacy" class="btn  btn-block btn-sm"
									type="button" id="binactive">Urgent Order</a>
							
								<a href="getAllNormalOrder.pharmacy" class="btn  btn-block btn-sm"
									type="button"
									id="bactive">Normal
									Order</a>
						
								<a href="getAllDeliveredOrder.pharmacy" class="btn  btn-block btn-sm"
									type="button" id="binactive">Delivered Order</a>
							
								<a href="getAllMyOrder.pharmacy" class="btn  btn-block btn-sm"
									type="button" id="binactive">My Order</a>
							
						</c:if>

						<c:if test="${type == 3 }">

							
								<a href="getAllUrgentOrder.pharmacy" class="btn  btn-block btn-sm"
									type="button" id="binactive">Urgent Order</a>
							
								<a href="getAllNormalOrder.pharmacy" class="btn  btn-block btn-sm"
									type="button" id="binactive">Normal Order</a>
							
								<a href="getAllDeliveredOrder.pharmacy" class="btn  btn-block btn-sm"
									type="button"
									id="bactive">Delivered
									Order</a>
							
								<a href="getAllMyOrder.pharmacy" class="btn  btn-block btn-sm"
									type="button" id="binactive">My Order</a>
							
						</c:if>
						
						<c:if test="${type == 4 }">

							
								<a href="getAllUrgentOrder.pharmacy" class="btn  btn-block btn-sm"
									type="button" id="binactive"
									
									>Urgent Order</a>
							
								<a href="getAllNormalOrder.pharmacy" class="btn  btn-block btn-sm"
									type="button" id="binactive">Normal Order</a>
							
								<a href="getAllDeliveredOrder.pharmacy" class="btn  btn-block btn-sm"
									type="button" id="binactive">Delivered Order</a>
							
								<a href="getAllMyOrder.pharmacy" class="btn  btn-block btn-sm"
									type="button" id="bactive">My Order</a>
							
						</c:if>
			
				</div>
		
			
				
				
			
			<div class="col-xs-12 col-sm-9 col-md-8 column">
			
		<div class="panel panel-default">
				<div class="panel-heading">
			<h3 class="panel-title" style="letter-spacing:0.8px;text-align:center;">
						<strong><c:if test="${type == 1 }">List Of Urgent Orders</c:if>
						<c:if test="${type == 2 }">List Of Normal Orders</c:if>
						<c:if test="${type == 3 }">List Of Delivered Orders</c:if>
						<c:if test="${type == 4 }">List Of My Orders</c:if></strong>
					</h3>
			</div>
		</div>
			
			
			
				
<c:forEach var="order" items="${orders}">	
					
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title"
									style="letter-spacing: 1px; font-weight: bold; color: grey;">
									Order details</h3>
							</div>
							<div class="panel-body">
								<table class="table table-condensed">
								<tr>
										<td class="field-label col-xs-4 col-sm-3 col-md-3 column  active"><label>
										Order Id </label></td>
										<td class="col-xs-8 col-sm-9 col-md-9 column"> ${order.id}</td>
									</tr>
									<tr>
										<td class="field-label col-xs-4 col-sm-3 col-md-3 active"><label>
										Customer Name: </label></td>
										<td class="col-xs-8 col-sm-9 col-md-9 column">
									    <c:if test="${order ne null}">
												<c:if test="${order.patientId ne 0}">
					<a href="pharmacyViewPatient.profile?id=${order.patientId}">${order.patientName}</a>
				</c:if>
												<c:if test="${order.doctorId ne 0}">
					<a href="pharmacyViewDoctor.profile?id=${order.doctorId}">${order.doctorName}</a>
				</c:if>
												<c:if test="${order.pharmacyId ne 0}">
					<a href="pharmacyViewPharmacy.profile?id=${order.pharmacyId}">${order.pharmacyName}</a>
				</c:if>
											</c:if></td>
									</tr>
								
                         <c:if test="${type == 4 }">
                                  
									<tr>
										<td class="field-label col-xs-4 col-sm-3 col-md-3 active"><label>
												Supplier Name:</label></td>
										<td class="col-xs-8 col-sm-9 col-md-9 column"><a href="pharmacyViewPharmacy.profile?id=${order.supplierPharmacyId}">${order.supplierPharmacyName}</a></td>
									</tr>
								</c:if>
								
								
									<tr>
										<td class="field-label col-xs-4 col-sm-3 col-md-3 active">
										<label>
										Status </label></td>
										<td class="col-xs-8 col-sm-9 col-md-9 column"><c:if test="${order.status == 1}">Processing</c:if>
											<c:if test="${order.status == 2}">Dispatched</c:if>
											<c:if test="${order.status == 3}">Cancelled</c:if>
											<c:if test="${order.status == 4}">Delivered</c:if></td>
									</tr>
								<c:if test="${not empty order.comment}">
									<tr>
										<td class="field-label col-xs-3 active"><label>
												Comment:</label></td>
										<td class="col-md-9">${order.comment}</td>
									</tr>
									</c:if>
									<tr>
										<td class="field-label col-xs-4 col-sm-3 col-md-3 active"><label>Type
												of Order:</label></td>
										<td class="col-xs-8 col-sm-9 col-md-9 column"><c:if test="${order.orderType == 1}">Normal</c:if>
											<c:if test="${order.orderType == 2}">Urgent</c:if></td>
									</tr>
								

                                  <c:if test="${not empty order.image }">
										<tr>
											<td class="field-label col-xs-3 active"><label>Prescription
													Image </label></td>
											<td class="thumbnail"><img alt="300x200"
												src="${order.image}" /></td>
										</tr>
									</c:if>
								</table>	
								<table class="table table-bordered table-condensed" style="margin-top: 5%;">
									<tr>
										<td class="field-label col-xs-6 active"><label>Medicine
												Name </label></td>
										<td class="field-label col-xs-3 active"><label>Quantity</label>
										</td>
										<td class="field-label col-xs-3 active"><label>Cost</label>
										</td>
									</tr>
									<c:forEach var="medicine" items="${order.medicines}">
										<c:if test="${medicine.name ne null}">
											<tr>
												<td class="col-md-6">${medicine.name}</td>
												<td class="col-md-3">${medicine.quantity}</td>
												<td class="col-md-3">${medicine.cost}</td>
											</tr>
										</c:if>
									</c:forEach>
								</table>

								<table class="table table-condensed" style="margin-top: 5%;">
									<tr>
										<td class="field-label col-xs-4 col-sm-3 col-md-3 active"><label>Total
												Cost::</label></td>
										<td class="col-xs-8 col-sm-9 col-md-9 column">${order.totalCost}</td>
									</tr>
									<tr>
										<td class="field-label col-xs-4 col-sm-3 col-md-3  active"><label>
												Payment Type: </label></td>
										<td class="col-xs-8 col-sm-9 col-md-9 column"><c:if test="${order.cashType == 1}">Cash On Delivery</c:if>
											<c:if test="${order.cashType == 2}">Card</c:if></td>
									</tr>
									<tr>
										<td class="field-label col-xs-4 col-sm-3 col-md-3  active"><label>Delivery
												Date:</label></td>
										<td class="col-xs-8 col-sm-9 col-md-9 column">${order.deliveredOn.getDate()}/${order.deliveredOn.getMonth()+1}/${order.deliveredOn.getYear()+1900}
										</td>
									</tr>

								</table>

								<h4 class="text-left" style="color: grey;">Shipping Details</h4>


								<table class="table table-condensed">
									<tr>
										<td class="field-label col-xs-4 col-sm-3 col-md-3  active"><label>Name:</label>
										</td>
										<td class="col-xs-8 col-sm-9 col-md-9 column"><c:if test="${order ne null}">
												<c:if test="${order.patientId ne 0}">
					<a href="pharmacyViewPatient.profile?id=${order.patientId}">${order.patientName}</a>
				</c:if>
												<c:if test="${order.doctorId ne 0}">
					<a href="pharmacyViewDoctor.profile?id=${order.doctorId}">${order.doctorName}</a>
				</c:if>
												<c:if test="${order.pharmacyId ne 0}">
					<a href="pharmacyViewPharmacy.profile?id=${order.pharmacyId}">${order.pharmacyName}</a>
				</c:if>
											</c:if></td>
									</tr>
									<tr>
										<td class="field-label col-xs-4 col-sm-3 col-md-3  active"><label>
												Contact:</label></td>
										<td class="col-xs-8 col-sm-9 col-md-9 column">${order.phoneNumber}</td>
									</tr>
									<tr>
										<td class="field-label col-xs-4 col-sm-3 col-md-3  active"><label>
												Email:</label></td>
										<td class="col-xs-8 col-sm-9 col-md-9 column">${order.email}</td>
									</tr>


									<tr>
										<td class="field-label col-xs-4 col-sm-3 col-md-3  active"><label>
												Address:</label></td>
										<td class="col-xs-8 col-sm-9 col-md-9 column">${order.address1} <br>${order.address2}
										</td>
									</tr>

									<tr>
										<td class="field-label col-xs-4 col-sm-3 col-md-3  active"><label>
												Landmark:</label></td>
										<td class="col-xs-8 col-sm-9 col-md-9 column">${order.landMark}</td>
									</tr>

									<tr>
										<td class="field-label col-xs-4 col-sm-3 col-md-3  active"><label>City:</label>
										</td>
										<td class="col-xs-8 col-sm-9 col-md-9 column">${order.city}</td>
									</tr>
									<tr>
										<td class="field-label col-xs-4 col-sm-3 col-md-3  active"><label>
												State:</label></td>
										<td class="col-xs-8 col-sm-9 col-md-9 column">${order.state}</td>
									</tr>
									<tr>
										<td class="field-label col-xs-4 col-sm-3 col-md-3  active"><label>Country:</label>
										</td>
										<td class="col-xs-8 col-sm-9 col-md-9 column">${order.country}</td>
									</tr>
									<tr>
										<td class="field-label col-xs-4 col-sm-3 col-md-3  active"><label>Pincode:</label>
										</td>
										<td class="col-xs-8 col-sm-9 col-md-9 column">${order.pinCode}</td>
									</tr>

								</table>
							</div>
							<c:if test="${type == 1 }">
							<div class="panel-footer">
								<div class="row">
									<c:if test="${order.status == 1}">
									<div class="col-xs-4 col-sm-4 col-md-6 column"></div>
									<div class="col-xs-4 col-sm-4 col-md-3 column">
											<div id="${order.id}">
												<input class="btn btn-warning btn-block btn-xs" type="button"
													style="font-weight: bold; width: %;" value=verify
													onclick="verifyOrder(${order.id})">
											</div>
										</div>
										
										<div class="col-xs-4 col-sm-4 col-md-3 column">
											<input class="btn btn-warning btn-block btn-xs" type="button"
													style="font-weight: bold; width: %;" value=Cancel
													onclick="cancelOrder(${order.id})">
										</div>
										
									</c:if>

									
										<c:if test="${order.status == 2}">

											<div class="col-xs-4 col-sm-4 col-md-3 column">
												<div id="${order.id}">
													<input class="btn btn-warning btn-block btn-xs" type="button"
														style="font-weight: bold; width: %;" value=Delivered
														onclick="deliverOrder(${order.id})">
												</div>
											</div>
										</c:if>
								</div>
							</div>
							</c:if>
							
							<c:if test="${type == 2 }">
							<div class="panel-footer">
								<div class="row">
									<c:if test="${order.status == 1}">
									<div class="col-xs-4 col-sm-4 col-md-6 column"></div>
									<div class="col-xs-4 col-sm-4 col-md-3 column">
											<div id="${order.id}">
												<input class="btn btn-warning btn-block btn-xs" type="button"
													style="font-weight: bold; width: %;" value=verify
													onclick="verifyOrder(${order.id})">
											</div>
										</div>
										
										<div class="col-xs-4 col-sm-4 col-md-3 column">
											<input class="btn btn-warning btn-block btn-xs" type="button"
													style="font-weight: bold; width: %;" value=Cancel
													onclick="cancelOrder(${order.id})">
										</div>
										
									</c:if>

									
										<c:if test="${order.status == 2}">

											<div class="col-xs-4 col-sm-4 col-md-3 column">
												<div id="${order.id}">
													<input class="btn btn-warning btn-block btn-xs" type="button"
														style="font-weight: bold; width: %;" value=Delivered
														onclick="deliverOrder(${order.id})">
												</div>
											</div>
										</c:if>
								</div>
							</div>
							</c:if>
							
							<c:if test="${type == 3 }">
							<div class="panel-footer">
								<div class="row">
									<c:if test="${order.status == 1}">
									<div class="col-xs-4 col-sm-4 col-md-6 column"></div>
									<div class="col-xs-4 col-sm-4 col-md-3 column">
											<div id="${order.id}">
												<input class="btn btn-warning btn-block btn-xs" type="button"
													style="font-weight: bold; width: %;" value=verify
													onclick="verifyOrder(${order.id})">
											</div>
										</div>
										
										<div class="col-xs-4 col-sm-4 col-md-3 column">
											<input class="btn btn-warning btn-block btn-xs" type="button"
													style="font-weight: bold; width: %;" value=Cancel
													onclick="cancelOrder(${order.id})">
										</div>
										
									</c:if>

									
										<c:if test="${order.status == 2}">

											<div class="col-xs-4 col-sm-4 col-md-3 column">
												<div id="${order.id}">
													<input class="btn btn-warning btn-block btn-xs" type="button"
														style="font-weight: bold; width: %;" value=Delivered
														onclick="deliverOrder(${order.id})">
												</div>
											</div>
										</c:if>
								</div>
							</div>
							</c:if>
							
							<c:if test="${type == 4 }">
							<div class="panel-footer">
								<div class="row">
									<c:if test="${order.status == 1}">
									
										<div class="col-xs-4 col-sm-4 col-md-3 column">
											<input class="btn btn-warning btn-block btn-xs" type="button"
													style="font-weight: bold; width: %;" value=Cancel
													onclick="cancelOrder(${order.id})">
										</div>
										
									</c:if>
								</div>
							</div>
							</c:if>
						</div>



					
					</c:forEach>
				
	</div>
	</div>	
			

					
					

</div>

				
			






			<script src="js/bootstrap.min.js"></script>
</body>
</html>




















