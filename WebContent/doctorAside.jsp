<div class="aside col-xs-12 col-sm-6 col-md-4 column"
						style="min-width: 300px; max-width:450px; padding-left: 2%;">
						<form name="form">

							<div class="row-clearfix">
							
							<div class="input-group" style="">
      
      
									<input style="border:none;border-bottom:;" type="text" placeholder="Search" name="text" id="text"
										class="form-control"  onkeyup="search()"
										onkeydown="search()" />
										
									<span class="glyphicon glyphicon-search	input-group-addon" style="background-color:white;border:1px solid #f4f4f4;color:#00b9bc;"></span>
									</div>
							
								
								<div class="row clearfix" style="color:;">

									<div class="col-xs-4 col-sm-4 column"
										style="padding-left: 20px; text-align: center">


										<label class="radio"> <input type="radio" name="type"
											data-toggle="radio" id="optionsRadios1" value="1" checked>
											<i></i><small >Doctor</small>
										</label>
									</div>
									<div class="col-xs-4 col-sm-4 column"
										style="padding-left: 0px; text-align: center">
										<label class="radio"> <input type="radio" name="type"
											data-toggle="radio" id="optionsRadios1" value="2"> <i></i><small
											>Patient</small>
										</label>

									</div>
									<div class="col-xs-4 col-sm-4 column"
										style="padding-left: 0px; text-align: center;">

										<label class="radio"> <input type="radio" name="type"
											data-toggle="radio" id="optionsRadios1" value="3"> <i></i><small
											>Pharmacist</small>
										</label>
									</div>
								</div>
							</div>

							<br>
                           
							<div class='panel panel-default' id='search'></div>
							
						</form>




						<div class="list-group"
							style='overflow: auto; min-width: 300px; max-width: 550px; max-height: 400px;'>
							<!--<a href="#" class="list-group-item "><strong>Patients in your network</strong></a-->
							<div class="list-group-item">
							<h5 class="listgroupheader">Patients In Your
									Network</h5>
								
							</div>
							<div class="list-group-item">

								<div class="row">
									<c:if test="${not empty patients}">
										<c:forEach var="patient" items="${patients}">
											<c:if test="${not empty patient}">
												<div class="col-xs-4 col-sm-4 col-md-4">
													<div class="thumbnail" style="position:relative;">
														<img alt="300x200" src="${patient.image}" style="width:100px;height:80px;" onError="this.onerror=null;this.src='image/d.jpg';" />


														<p style="text-align: center; background-color: rgba(248, 248, 248, 0.83);position:absolute;bottom:0px;word-wrap:break-word;font-size:12px;text-align:center;margin:2px auto;width:92%;">
													<span id="${patient.id}"><span class="glyphicon glyphicon-one-fine-dot" style="color:green;" ></span></span>
															<a  href="doctorViewPatient.profile?id=${patient.id}"
																id="pn">${patient.firstName}&nbsp;${patient.lastName}</a> <a
																href="prescription.doctor?id=${patient.id}"
																class="btn btn-xs btn-block" type="button"
																style="    height: 19px;
    /* margin-bottom: -5px; */
    letter-spacing: 0px;
    background-color: #00b9bc;
    color: #fff;
    margin-top: -2;
    line-height: 1;">Write
																Prescription</a>
														</p>

													</div>
												</div>

											</c:if>

										</c:forEach>
									</c:if>
									<c:if test="${empty patients}">
										<p style="padding-left:10px;padding-right:10px;">
											No Patients In Your Network, Please Search and Add Them
											</p>
									</c:if>


								</div>

							</div>

						</div>
						
						
						
						
						
						<!-- -------- doctors -->
						

						<div class="list-group"
							style='overflow: auto; min-width: 300px; max-width: 550px; max-height: 400px;'>
							<!--<a href="#" class="list-group-item "><strong>Patients in your network</strong></a-->
							<div class="list-group-item">
							<h5 class="listgroupheader">Doctors In Your
									Network</h5>
								
							</div>
							<div class="list-group-item">

								<div class="row">
									<c:if test="${not empty doctors}">
										<c:forEach var="doctor1" items="${doctors}">
											<c:if test="${not empty doctor1}">
												<div class="col-xs-4 col-sm-4 col-md-4">
													<div class="thumbnail" style="position:relative;">
														<img alt="300x200" src="${doctor1.image}" style="width:100px;height:80px;" onError="this.onerror=null;this.src='image/d.jpg';" />


														<p style="text-align: center; background-color: rgba(248, 248, 248, 0.83);position:absolute;bottom:0px;word-wrap:break-word;font-size:12px;text-align:center;margin:2px auto;width:92%;">
														<span id="${doctor1.id}"><span class="glyphicon glyphicon-one-fine-dot" style="color:green;" ></span></span>
															<a  href="doctorViewDoctor.profile?id=${doctor1.id}"
																id="pn"> ${doctor1.firstName}&nbsp;${doctor1.lastName}</a> 
														</p>

													</div>
												</div>

											</c:if>

										</c:forEach>
									</c:if>
									<c:if test="${empty doctors}">
										<p style="padding-left:10px;padding-right:10px;">
											No Doctors In Your Network, Please Search and Add Them
											</p>
									</c:if>


								</div>

							</div>

						</div>
						
						
						<!-- -------- doctors close -->
						
						
						
						
						
						
						
						
						
						
						
						
						
							<div class="list-group"
							style='overflow: auto; min-width: 300px; max-width: 550px; max-height: 400px;'>
								<div class="list-group-item" style="border-bottom:none;">
							<h5 class="listgroupheader">
								Pharmacies In Your Network </h5>
								</div>
							<c:if test="${not empty pharmacies}">
								<c:forEach var="pharmacy" items="${pharmacies}">
									<c:if test="${not empty pharmacy}">
										<div class="list-group-item" id="lgi">
										<span id="${pharmacy.id}"><span class="glyphicon glyphicon-one-fine-dot pull-left" style="color:green;" ></span></span>
											<a href="doctorViewPharmacy.profile?id=${pharmacy.id}">${pharmacy.pharmacyName}<br />${pharmacy.address1},${pharmacy.address2},${pharmacy.landMark}
											</a><a href="order.doctor?id=${pharmacy.id}"
												class="btn btn-xs pull-right" type="button">Make
												order</a>
										</div>
									</c:if>
								</c:forEach>
							</c:if>
							<c:if test="${empty pharmacies}">

								<div class="list-group-item" id="lgi">No Pharmacies In
									Your Network, Please Search and Add Them.</div>
							</c:if>
						</div>
		</div>
		
<script type="text/javascript">
setInterval("getConnectedActiveStatus()",1000 * 100 );

jQuery(document).ready(function() {

	getConnectedActiveStatus();
});

setInterval("getConnectedActiveStatus()",1000 * 60 );

function getConnectedActiveStatus(){
	jQuery.ajax({
		method : "GET",
		url : 'getConnectedActiveStatus.chat',
		datatype : 'json',
		success : function(result) {
			if(result!="[]")
			{
				var d = jQuery.parseJSON(result);
				var FIVE_MINS = 60 * 5 * 1000; /* ms */
    			for ( var i = 0; i < d.length; i++) {
				
				var olddate = new Date(d[i].lastActive);
				if(((new Date) - olddate) < FIVE_MINS)
					{
					jQuery("#"+d[i].id).show();
					}
				else
					{
					jQuery("#"+d[i].id).hide();
					}
				
    			}
			}
			
		},
		statusCode : {
			500 : function(result) {
				
			}
		}
	});
}


</script>