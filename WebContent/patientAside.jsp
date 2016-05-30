<div class="aside col-xs-12 col-sm-6 col-md-4 column"
						style="min-width: 300px; max-width:450px; padding-left: 2%;">
						<form name="form">

							<div class="row-clearfix">
								<div class="input-group" style="">
      
									<input style="border:none;" type="text" placeholder="Search" name="text" id="text"
										class="form-control" style="width: %;" onkeyup="search()"
										onkeydown="search()" />
								<span class="glyphicon glyphicon-search	input-group-addon" style="background-color:white;border:1px solid #f4f4f4;color:#00b9bc;"></span>
									</div>
								
								<div class="row clearfix" >

									<div class="col-xs-4 col-sm-4 column"
										style="padding-left: 20px; text-align: center">


										<label class="radio"> <input type="radio" name="type"
											data-toggle="radio" id="optionsRadios1" value="1" checked>
											<i></i><small>Doctor</small>
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
							style='overflow: auto; min-width: 300px; max-width: 550px; max-height: 400px;border:none;'>
							<!--<a href="#" class="list-group-item "><strong>Patients in your network</strong></a-->
							<div class="list-group-item" style="border-bottom:none;">
								<h5 class="listgroupheader">Doctors In Your
									Network</h5>
									<!--hr align="left"  style="margin-bottom:-9px;margin-top:-2px;border:1px solid #ffa500;width:50px;"-->
							</div>
							<div class="list-group-item" style="border-top:none;">

								<div class="row">
								
									<c:if test="${not empty doctors}">
										<c:forEach var="doctor" items="${doctors}">
											<c:if test="${not empty doctor}">
												<div class="col-xs-4 col-sm-4 col-md-4">
														<div class="thumbnail" style="position:relative;">
														<img alt="Image" src="${doctor.image}" style="width:100px;height:80px;" onError="this.onerror=null;this.src='image/d.jpg';"/>


													<p style="text-align: center; background-color:rgba(248, 248, 248, 0.83);position:absolute;bottom:0px;word-wrap:break-word;font-size:12px;text-align:center;margin:2px auto;width:92%;">
														<span  id="${doctor.id}"><span class="glyphicon glyphicon-one-fine-dot pull-left" style="color:green;" ></span></span>
															<a href="patientViewDoctor.profile?id=${doctor.id}"
																id="pn">${doctor.firstName} &nbsp; ${doctor.lastName} </a> <a
																href="fixAppointment.patient?id=${doctor.id}"
																class="btn btn-xs btn-block" type="button"
																style="    height: 19px;
    /* margin-bottom: -5px; */
    letter-spacing: 0px;
    background-color: #00b9bc;
    color: #fff;
    margin-top: -2;
    line-height: 1;">
																Appointment</a>
														</p>

													</div>
												</div>

											</c:if>

										</c:forEach>
									</c:if>
									<c:if test="${empty doctors}">
										<p style="padding-left:10px;padding-right:10px;text-align:center;">
											No Doctors In Your Network, Please Search and Add Them.</p>
									</c:if>



								</div>

							</div>

						</div>
						<div class="list-group"
							style='overflow: auto; min-width: 300px; max-width: 550px; max-height: 400px;'>
								<div class="list-group-item" style="border-bottom:none;">
							<h5 class="listgroupheader">
								Pharmacies In Your Network </h5>
								</div>
							<c:if test="${not empty pharmacies}">
								<c:forEach var="pharmacy" items="${pharmacies}">
									<c:if test="${not empty pharmacy}">
										<div class="list-group-item" style="border-top:none;">
										<span id="${pharmacy.id}"><span class="glyphicon glyphicon-one-fine-dot pull-left" style="color:green;" ></span></span>
											<a href="patientViewPharmacy.profile?id=${pharmacy.id}">${pharmacy.pharmacyName}<br/>${pharmacy.address1},${pharmacy.address2},${pharmacy.landMark}
											</a><a href="order.patient?id=${pharmacy.id}"
												class="btn btn-xs pull-right" type="button">Make
												Order</a>
										</div>
									</c:if>
								</c:forEach>
							</c:if>
							<c:if test="${empty pharmacies}">

								<div class="list-group-item" style="border-top:none;">No Pharmacies In Your
									Network, Please Search and Add Them.</div>
							</c:if>
						</div>
					</div>
					
					<script>
setInterval("getConnectedActiveStatus()",1000 * 100 );
$(document).ready(function() {

	getConnectedActiveStatus();
});

setInterval("getConnectedActiveStatus()",1000 * 60 );

function getConnectedActiveStatus(){
	$.ajax({
		method : "GET",
		url : 'getConnectedActiveStatus.chat',
		datatype : 'json',
		success : function(result) {
			
			if(result!="[]")
			{
				var d = $.parseJSON(result);
				var FIVE_MINS = 60 * 5 * 1000; /* ms */
    			for ( var i = 0; i < d.length; i++) {
				
				var olddate = new Date(d[i].lastActive);
				if(((new Date) - olddate) < FIVE_MINS)
					{
					$("#"+d[i].id).show();
					}
				else
					{
					$("#"+d[i].id).hide();
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