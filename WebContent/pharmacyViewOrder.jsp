<%@ include file="pharmacyHeader.jsp"%>

<div class="container-fluid" style="margin-top: 4%;">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<div class="row clearfix">
				<div class=" col-md-2 column"></div>
				<div class="col-xs-12 col-sm-6 col-md-6 column">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h5 class="panel-title">Order details</h5>
						</div>
						<div class="panel-body">
							<table class="table table-condensed">
								<tr>
									<td
										class="field-label col-xs-4 col-sm-3 col-md-3 column  active"><label>
											Order Id </label></td>
									<td class="col-xs-8 col-sm-9 col-md-9 column">${order.id}</td>
								</tr>
								<tr>
									<td class="field-label col-xs-3 active"><label>
											Name: </label></td>
									<td class="col-md-9"><c:if test="${type == 0 }">
											<c:if test="${order.patientId ne 0}">
												<a href="pharmacyViewPatient.profile?id=${order.patientId}">${order.patientName}</a>
											</c:if>
											<c:if test="${order.doctorId ne 0}">
												<a href="pharmacyViewDoctor.profile?id=${order.doctorId}">${order.doctorName}</a>
											</c:if>
											<c:if test="${order.pharmacyId ne 0}">
												<a
													href="pharmacyViewPharmacy.profile?id=${order.pharmacyId}">${order.pharmacyName}</a>
											</c:if>
										</c:if>
										<c:if test="${type == 1 }">${pharmacy.pharmacyName}</c:if></td>
								</tr>
								<c:if test="${type == 1 }">

									<tr>
										<td class="field-label col-xs-4 col-sm-3 col-md-3 active"><label>
												Supplier Name:</label></td>
										<td class="col-xs-8 col-sm-9 col-md-9 column"><a
											href="pharmacyViewPharmacy.profile?id=${order.supplierPharmacyId}">${order.supplierPharmacyName}</a></td>
									</tr>
								</c:if>
								<tr>
									<td class="field-label col-xs-3 active"><label>
											Status </label></td>
									<td class="col-md-9"><c:if test="${order.status == 1}">Processing</c:if>
										<c:if test="${order.status == 2}">Dispatched</c:if> <c:if
											test="${order.status == 3}">Cancelled By Seller</c:if> <c:if
											test="${order.status == 4}">Delivered</c:if> <c:if
											test="${order.status == 5}">Cancelled By Customer</c:if></td>
								</tr>
<c:if test="${not empty order.comment}">
									<tr>
										<td class="field-label col-xs-3 active"><label>
												Comment:</label></td>
										<td class="col-md-9">${order.comment}</td>
									</tr>
									</c:if>
								<tr>
									<td class="field-label col-xs-3 active"><label>Type
											of Order:</label></td>
									<td class="col-md-9"><c:if test="${order.orderType == 1}">Normal</c:if>
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
							<table class="table" style="margin-top: 2%;">
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

							<table class="table" style="margin-top: 2%;">
								<tr>
									<td class="field-label col-xs-3 active"><label>Total
											Cost:</label></td>
									<td class="col-md-9">${order.totalCost}</td>
								</tr>
								<tr>
									<td class="field-label col-xs-3 active"><label>
											Payment Type: </label></td>
									<td class="col-md-9"><c:if test="${order.cashType == 1}">Cash On Delivery</c:if>
										<c:if test="${order.cashType == 2}">Card</c:if></td>
								</tr>
								<tr>
									<td class="field-label col-xs-3 active"><label>Delivery
											Date:</label></td>
									<td class="col-md-9">${order.deliveredOn.getDate()}/${order.deliveredOn.getMonth()+1}/${order.deliveredOn.getYear()+1900}
									</td>
								</tr>

							</table>

							<h4 class="text-left" style="color: grey;">Shipping Details</h4>


							<table class="table">
								<tr>
									<td class="field-label col-xs-3 active"><label>Name:</label>
									</td>
									<td class="col-md-9"><c:if test="${type == 0 }">
											<c:if test="${order.patientId ne 0}">
												<a href="pharmacyViewPatient.profile?id=${order.patientId}">${order.patientName}</a>
											</c:if>
											<c:if test="${order.doctorId ne 0}">
												<a href="pharmacyViewDoctor.profile?id=${order.doctorId}">${order.doctorName}</a>
											</c:if>
											<c:if test="${order.pharmacyId ne 0}">
												<a
													href="pharmacyViewPharmacy.profile?id=${order.pharmacyId}">${order.pharmacyName}</a>
											</c:if>
										</c:if>
										<c:if test="${type == 1 }">${pharmacy.pharmacyName}</c:if></td>
								</tr>

								<c:if test="${not empty order.comment}">
									<tr>
										<td class="field-label col-xs-3 active"><label>
												Comment:</label></td>
										<td class="col-md-9">${order.comment}</td>
									</tr>
								</c:if>

								<tr>
									<td class="field-label col-xs-3 active"><label>
											Contact:</label></td>
									<td class="col-md-9">${order.phoneNumber}</td>
								</tr>
								<tr>
									<td class="field-label col-xs-3 active"><label>
											Email:</label></td>
									<td class="col-md-9">${order.email}</td>
								</tr>


								<tr>
									<td class="field-label col-xs-3 active"><label>
											Address:</label></td>
									<td class="col-md-9">${order.address1} <br>${order.address2}
									</td>
								</tr>

								<tr>
									<td class="field-label col-xs-3 active"><label>
											Landmark:</label></td>
									<td class="col-md-9">${order.landMark}</td>
								</tr>

								<tr>
									<td class="field-label col-xs-3 active"><label>City:</label>
									</td>
									<td class="col-md-9">${order.city}</td>
								</tr>
								<tr>
									<td class="field-label col-xs-3 active"><label>
											State:</label></td>
									<td class="col-md-9">${order.state}</td>
								</tr>
								<tr>
									<td class="field-label col-xs-3 active"><label>Country:</label>
									</td>
									<td class="col-md-9">${order.country}</td>
								</tr>
								<tr>
									<td class="field-label col-xs-3 active"><label>Pincode:</label>
									</td>
									<td class="col-md-9">${order.pinCode}</td>
								</tr>

							</table>
						</div>
						<div class="panel-footer">
							<div class="row">
								<c:if test="${type == 0}">
									<c:if test="${order.status == 1}">
										<div class="col-xs-4 col-sm-4 col-md-6 column"></div>
										<div class="col-xs-4 col-sm-4 col-md-3 column">
											<div id="${order.id}">
												<input class="btn btn-warning btn-block btn-xs"
													type="button" style="font-weight: bold; width: %;"
													value=Verify onclick="verifyOrder(${order.id})">
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
												<input class="btn btn-warning btn-block btn-xs"
													type="button" style="font-weight: bold; width: %;"
													value=Delivered onclick="deliverOrder(${order.id})">
											</div>
										</div>
									</c:if>
								</c:if>
								<c:if test="${type == 1}">
									<c:if test="${order.status == 1}">

										<div class="col-xs-4 col-sm-4 col-md-3 column">
											<input class="btn btn-warning btn-block btn-xs" type="button"
												style="font-weight: bold; width: %;" value=Cancel
												onclick="cancelOrder(${order.id})">
										</div>

									</c:if>
								</c:if>

							</div>
						</div>
					</div>

				</div>





				<%@ include file="pharmacyAside.jsp"%>

			</div>
		</div>



	</div>
</div>

<script src="js/bootstrap.min.js"></script>
</body>
</html>










