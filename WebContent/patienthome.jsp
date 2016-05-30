
<%@ include file="patientHeader.jsp"%>

<script src="js/bootstrap.min.js"></script>
<script>
$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip(); 
   
});
</script>
<script>
$( document ).ready(function() {
	$("#vaccine").click(function() {
		$("#addVaccine").modal();
	});
	 var insert = "${insert}";
		if(insert == 1)
			{
				window.history.pushState("", "", 'patient.home');
			}
		
		var beg = "${beginner}";
		debugger
		if(beg == 1){
			
				$("#intro").css("overflow", "hidden");
			
				$("#intro_close").click(function(){
				$("#intro").css("overflow-y", "scroll");
				$("div.intro").remove();
		       
		    });
		}
	
});
</script>

<!-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>--->


<link href="css/animate.css" rel="stylesheet">

<style type="text/css">
.fi {
	animation-delay: 1s;
	-moz-animation-delay: 1s;
	-webkit-animation-delay: 1s;
}

.se {
	animation-delay: 3s;
	-moz-animation-delay: 3s;
	-webkit-animation-delay: 3s;
}

.th {
	animation-delay: 6s;
	-moz-animation-delay: 6s;
	-webkit-animation-delay: 6s;
}

.fo {
	animation-delay: 9s;
	-moz-animation-delay: 9s;
	-webkit-animation-delay: 9s;
}

.fif {
	animation-delay: 10.2s;
	-moz-animation-delay: 10.2s;
	-webkit-animation-delay: 10.2s;
}

.si {
	animation-delay: 10.8s;
	-moz-animation-delay: 10.8s;
	-webkit-animation-delay: 10.8s;
}

.sev {
	animation-delay: 14s;
	-moz-animation-delay: 14s;
	-webkit-animation-delay: 14s;
}

.c_w {
	color: #fff;
}

.f_b {
	font-size: 20px;
}

.f_m {
	font-size: 16px;
}

.f_s {
	font-size: 16px;
}

.pad_t_20 {
	padding-top: 20px;
}

.h_100 {
	height: 100px;
}

.h_300 {
	height: 300px;
}

.intro_name {
	background-color: #00b9bc;
	width: 220px;
	height: 200px;
	border-radius: 50%;
	margin: auto;
	padding-top: 67px;
}
</style>
<title>Healingclick</title>
</head>


<body id="intro">
	<c:if test="${beginner == 1}">
		<div class="intro"
			style="position: absolute; left: 0; top: 0; bottom: 0; right: 0; background-color: rgba(0, 0, 0, 0.5); z-index: 99999;">

			<div class="row text-center">

				<h5
					class="text-center f_big c_w pad_t_20 f_b animated fadeIn intro_name">
					<span class="animated fadeInUp">Welcome <br />
						${patient.firstName} &nbsp; ${patient.lastName}
					</span>
				</h5>

			</div>


			<div class="container text-center c_w f_m"
				style="width: 95%; margin: auto;">

				<div class="row clearfix">
					<div class="fi col-sm-3 animated fadeIn">
						<i class="fa fa-pencil-square-o"></i>Find the list of mandatory
						vaccinations &amp; <br />Set up reminders <span
							class="glyphicon glyphicon-time"></span> for your next
						vaccination
					</div>
					<div class="se col-sm-5 animated fadeIn">
						Find your latest health status here <br />Click medical tracker
						button to open your health tracker
					</div>
					<div class="th col-sm-4 animated fadeIn pull-right">
						<i class="fa fa-user-md" style="font-size: 30px;"></i> Search your
						family doctor here, if not listed, you could send them an invite
						instantly <br /> <br /> <br /> <br /> <br /> <br /> <span
							class="fo"> Order your medicine by connecting to your
							pharmacies : You could search them and add them to your list as a
							first step. </span>
					</div>

				</div>

				<div class="row">
					<div class="col-sm-3 animated fadeInUp"></div>
					<div class="sev col-sm-5 animated fadeIn"></div>
					<div class="fif col-sm-4 animated fadeIn"
						style="position: absolute; bottom: 20px; right: 10px;">
						<i class="fa fa-comments-o" style="font-size: 30px"></i> Chat
						support <br /> You could interact with your connected Pharmacies
						and Doctors here

					</div>
				</div>


				<div class="text-center animated fadeIn sev">
					That's it !! You are done! Start owning your health.<br /> Lead
					healthier Lives!<br /> <i class="fa fa-child"
						style="font-size: 36px"></i>
					<div class="h_20"></div>
					<button type="button" class=" btn btn-success" id="intro_close">
						Close &nbsp;<span class="glyphicon glyphicon-send"></span>
					</button>

				</div>

			</div>
		</div>
	</c:if>
<body>
	<div class="container-fluid" style="margin-top: 5%;">


		<div class="h_20 clearfix"></div>
		<div class="row clearfix">


			<div class="col-xs-12 col-sm-5 col-md-3 column">
				<div class="accordion" id="accordion2">
					<div class="accordion-group">
						<div class="accordion-heading bg_w">
							<a class="accordion-toggle" role="button" data-toggle="collapse"
								data-parent="#accordion2" href="#collapseOne"> <image
									src="image/vaccination.png" /> Necessary Vaccinations
							</a>
						</div>
						<div style="height: 3px;"></div>
						<div id="collapseOne" class="accordion-body collapse">
							<div class="accordion-inner">
								<div class="panel panel-default">
									<div class="panel-heading" style="background-color: white;">
										<div class="panel-title"></div>

									</div>

									<table class="table"
										style="width: 100%; padding-left: 1%; font-size: 0.9em;">
										<tr>
											<td class="field-label col-xs-1 col-sm-1 col-md-1 ">Age</td>
											<td class="field-label col-xs-8 col-sm-8 col-md-8 ">Vaccination
											</td>
											<td class="field-label col-xs-3 col-sm-3 col-md-3">Status<br>Yes/No
											</td>


										</tr>
										<c:if test="${age ge 1}">
											<tr>
												<td class="field-label col-xs-1 col-sm-1 col-md-1 ">At
													Birth</td>
												<td class="field-label col-xs-8 col-sm-8 col-md-8 ">BCG</td>
												<td class="field-label col-xs-3 col-sm-3 col-md-3"><c:if
														test="${vaccine.BCG == 1}">

														<input type="radio" name="vaccine1" data-toggle="tooltip"
															data-placement="top" onclick="Vaccine(1);" value="1"
															checked>&nbsp;&nbsp;
												<input type="radio" name="vaccine1" data-toggle="tooltip"
															data-placement="top" onclick="Vaccine(1);" value="2">

													</c:if> <c:if test="${vaccine.BCG == 0}">
														<input type="radio" name="vaccine1" data-toggle="tooltip"
															data-placement="top" onclick="Vaccine(1);" value="1">&nbsp;&nbsp;
												<input type="radio" name="vaccine1" data-toggle="tooltip"
															data-placement="top" onclick="Vaccine(1);" value="2"
															checked>
													</c:if></td>
											</tr>
										</c:if>

										<c:if test="${age ge 42}">
											<tr>
												<td class="field-label col-xs-1 col-sm-1 col-md-1 ">6
													Weeks</td>
												<td class="field-label col-xs-8 col-sm-8 col-md-8 ">DPT,
													Polio and Hepatitis B (1st Dose)</td>
												<td class="field-label col-xs-3 col-sm-3 col-md-3"><c:if
														test="${vaccine.DPT_Polio_and_Hepatitis_B_1 == 1}">
														<input type="radio" name="vaccine2" data-toggle="tooltip"
															data-placement="top" onclick="Vaccine(2);" value="1"
															checked>&nbsp;&nbsp;
												<input type="radio" name="vaccine2" data-toggle="tooltip"
															data-placement="top" onclick="Vaccine(2);" value="2">
													</c:if> <c:if test="${vaccine.DPT_Polio_and_Hepatitis_B_1 == 0}">
														<input type="radio" name="vaccine2" data-toggle="tooltip"
															data-placement="top" onclick="Vaccine(2);" value="1">&nbsp;&nbsp;
												<input type="radio" name="vaccine2" data-toggle="tooltip"
															data-placement="top" onclick="Vaccine(2);" value="2"
															checked>
													</c:if></td>
											</tr>
										</c:if>
										<c:if test="${age ge 90}">
											<tr>
												<td class="field-label col-xs-1 col-sm-1 col-md-1 ">3
													Months</td>
												<td class="field-label col-xs-8 col-sm-8 col-md-8 ">DPT,
													Polio and Hepatitis B (2nd Dose)</td>
												<td class="field-label col-xs-3 col-sm-3 col-md-3"><c:if
														test="${vaccine.DPT_Polio_and_Hepatitis_B_2 == 1}">
														<input type="radio" name="vaccine3" data-toggle="tooltip"
															data-placement="top" onclick="Vaccine(3);" value="1"
															checked>&nbsp;&nbsp;
												<input type="radio" name="vaccine3" data-toggle="tooltip"
															data-placement="top" onclick="Vaccine(3);" value="2">
													</c:if> <c:if test="${vaccine.DPT_Polio_and_Hepatitis_B_2 == 0}">
														<input type="radio" name="vaccine3" data-toggle="tooltip"
															data-placement="top" onclick="Vaccine(3);" value="1">&nbsp;&nbsp;
												<input type="radio" name="vaccine3" data-toggle="tooltip"
															data-placement="top" onclick="Vaccine(3);" value="2"
															checked>
													</c:if></td>
											</tr>
										</c:if>
										<c:if test="${age ge 150}">
											<tr>
												<td class="field-label col-xs-1 col-sm-1 col-md-1 ">5
													Months</td>
												<td class="field-label col-xs-8 col-sm-8 col-md-8 ">DPT,
													Polio and Hepatitis B (3rd Dose)</td>
												<td class="field-label col-xs-3 col-sm-3 col-md-3"><c:if
														test="${vaccine.DPT_Polio_and_Hepatitis_B_3 == 1}">
														<input type="radio" name="vaccine4" data-toggle="tooltip"
															data-placement="top" onclick="Vaccine(4);" value="1"
															checked>&nbsp;&nbsp;
												<input type="radio" name="vaccine4" data-toggle="tooltip"
															data-placement="top" onclick="Vaccine(4);" value="2">
													</c:if> <c:if test="${vaccine.DPT_Polio_and_Hepatitis_B_3 == 0}">
														<input type="radio" name="vaccine4" data-toggle="tooltip"
															data-placement="top" onclick="Vaccine(4);" value="1">&nbsp;&nbsp;
												<input type="radio" name="vaccine4" data-toggle="tooltip"
															data-placement="top" onclick="Vaccine(4);" value="2"
															checked>
													</c:if></td>
											</tr>
										</c:if>
										<c:if test="${age ge 180}">
											<tr>
												<td class="field-label col-xs-1 col-sm-1 col-md-1 ">6
													Months</td>
												<td class="field-label col-xs-8 col-sm-8 col-md-8 ">Measels
												</td>
												<td class="field-label col-xs-3 col-sm-3 col-md-3"><c:if
														test="${vaccine.measels == 1}">
														<input type="radio" name="vaccine5" data-toggle="tooltip"
															data-placement="top" onclick="Vaccine(5);" value="1"
															checked>&nbsp;&nbsp;
												<input type="radio" name="vaccine5" data-toggle="tooltip"
															data-placement="top" onclick="Vaccine(5);" value="2">
													</c:if> <c:if test="${vaccine.measels == 0}">
														<input type="radio" name="vaccine5" data-toggle="tooltip"
															data-placement="top" onclick="Vaccine(5);" value="1">&nbsp;&nbsp;
												<input type="radio" name="vaccine5" data-toggle="tooltip"
															data-placement="top" onclick="Vaccine(5);" value="2"
															checked>
													</c:if></td>
											</tr>
										</c:if>
										<c:if test="${age ge 365}">
											<tr>
												<td class="field-label col-xs-1 col-sm-1 col-md-1 ">1
													Year</td>
												<td class="field-label col-xs-8 col-sm-8 col-md-8 ">MMR
													(Mums, Meals and Rubels)</td>
												<td class="field-label col-xs-3 col-sm-3 col-md-3"><c:if
														test="${vaccine.MMR == 1}">
														<input type="radio" name="vaccine6" data-toggle="tooltip"
															data-placement="top" onclick="Vaccine(6);" value="1"
															checked>&nbsp;&nbsp;
												<input type="radio" name="vaccine6" data-toggle="tooltip"
															data-placement="top" onclick="Vaccine(6);" value="2">
													</c:if> <c:if test="${vaccine.MMR == 0}">
														<input type="radio" name="vaccine6" data-toggle="tooltip"
															data-placement="top" onclick="Vaccine(6);" value="1">&nbsp;&nbsp;
												<input type="radio" name="vaccine6" data-toggle="tooltip"
															data-placement="top" onclick="Vaccine(6);" value="2"
															checked>
													</c:if></td>
											</tr>
										</c:if>
										<c:if test="${age ge 548}">
											<tr>
												<td class="field-label col-xs-1 col-sm-1 col-md-1 ">1 ½
													Year</td>
												<td class="field-label col-xs-8 col-sm-8 col-md-8 ">DPT,
													Polio and Hepatitis - B (Booster Dose)</td>
												<td class="field-label col-xs-3 col-sm-3 col-md-3"><c:if
														test="${vaccine.DPT_Polio_and_Hepatitis_B_booster_1 == 1}">
														<input type="radio" name="vaccine7" data-toggle="tooltip"
															data-placement="top" onclick="Vaccine(7);" value="1"
															checked>&nbsp;&nbsp;
												<input type="radio" name="vaccine7" data-toggle="tooltip"
															data-placement="top" onclick="Vaccine(7);" value="2">
													</c:if> <c:if
														test="${vaccine.DPT_Polio_and_Hepatitis_B_booster_1 == 0}">
														<input type="radio" name="vaccine7" data-toggle="tooltip"
															data-placement="top" onclick="Vaccine(7);" value="1">&nbsp;&nbsp;
												<input type="radio" name="vaccine7" data-toggle="tooltip"
															data-placement="top" onclick="Vaccine(7);" value="2"
															checked>
													</c:if></td>
											</tr>
										</c:if>
										<c:if test="${age ge 1824}">
											<tr>
												<td class="field-label col-xs-1 col-sm-1 col-md-1 ">5
													Years</td>
												<td class="field-label col-xs-8 col-sm-8 col-md-8 ">DPT,
													Polio and Hepatitis - B (2nd Booster Dose)</td>
												<td class="field-label col-xs-3 col-sm-3 col-md-3"><c:if
														test="${vaccine.DPT_Polio_and_Hepatitis_B_booster_2 == 1}">
														<input type="radio" name="vaccine8" data-toggle="tooltip"
															data-placement="top" onclick="Vaccine(8);" value="1"
															checked>&nbsp;&nbsp;
												<input type="radio" name="vaccine8" data-toggle="tooltip"
															data-placement="top" onclick="Vaccine(8);" value="2">
													</c:if> <c:if
														test="${vaccine.DPT_Polio_and_Hepatitis_B_booster_2 == 0}">
														<input type="radio" name="vaccine8" data-toggle="tooltip"
															data-placement="top" onclick="Vaccine(8);" value="1">&nbsp;&nbsp;
												<input type="radio" name="vaccine8" data-toggle="tooltip"
															data-placement="top" onclick="Vaccine(8);" value="2"
															checked>
													</c:if></td>
											</tr>
										</c:if>
										<c:if test="${age ge 3648}">
											<tr>
												<td class="field-label col-xs-1 col-sm-1 col-md-1 ">10
													Years</td>
												<td class="field-label col-xs-8 col-sm-8 col-md-8 ">DT</td>
												<td class="field-label col-xs-3 col-sm-3 col-md-3"><c:if
														test="${vaccine.DT == 1}">
														<input type="radio" name="vaccine9" data-toggle="tooltip"
															data-placement="top" onclick="Vaccine(9);" value="1"
															checked>&nbsp;&nbsp;
												<input type="radio" name="vaccine9" data-toggle="tooltip"
															data-placement="top" onclick="Vaccine(9);" value="2">
													</c:if> <c:if test="${vaccine.DT == 0}">
														<input type="radio" name="vaccine9" data-toggle="tooltip"
															data-placement="top" onclick="Vaccine(9);" value="1">&nbsp;&nbsp;
												<input type="radio" name="vaccine9" data-toggle="tooltip"
															data-placement="top" onclick="Vaccine(9);" value="2"
															checked>
													</c:if></td>
											</tr>
										</c:if>
										<c:if test="${patient.gender == 2}">
											<c:if test="${age ge 5471}">
												<tr>
													<td class="field-label col-xs-1 col-sm-1 col-md-1 ">15
														Years</td>
													<td class="field-label col-xs-8 col-sm-8 col-md-8 ">2
														dose of tetanus</td>
													<td class="field-label col-xs-3 col-sm-3 col-md-3"><c:if
															test="${vaccine.dose_of_tetanus == 1}">
															<input type="radio" name="vaccine10"
																data-toggle="tooltip" data-placement="top"
																onclick="Vaccine(10);" value="1" checked>&nbsp;&nbsp;
												<input type="radio" name="vaccine10" data-toggle="tooltip"
																data-placement="top" onclick="Vaccine(10);" value="2">
														</c:if> <c:if test="${vaccine.dose_of_tetanus == 0}">
															<input type="radio" name="vaccine10"
																data-toggle="tooltip" data-placement="top"
																onclick="Vaccine(10);" value="1">&nbsp;&nbsp;
												<input type="radio" name="vaccine10" data-toggle="tooltip"
																data-placement="top" onclick="Vaccine(10);" value="2"
																checked>
														</c:if></td>
												</tr>
											</c:if>
										</c:if>

										<c:if test="${age ge 6570}">
											<tr>
												<td class="field-label col-xs-1 col-sm-1 col-md-1 ">Seasonal
													Vaccination</td>
												<td class="field-label col-xs-8 col-sm-8 col-md-8 ">Typhoid
													(Antibody)<br />Mengis (Ppl going to Haj)
												</td>
												<td class="field-label col-xs-3 col-sm-3 col-md-3"><c:if
														test="${vaccine.seasonal == 1}">
														<input type="radio" name="vaccine11" data-toggle="tooltip"
															data-placement="top" onclick="Vaccine(11);" value="1"
															checked>&nbsp;&nbsp;
												<input type="radio" name="vaccine11" data-toggle="tooltip"
															data-placement="top" onclick="Vaccine(11);" value="2">
													</c:if> <c:if test="${vaccine.seasonal == 0}">
														<input type="radio" name="vaccine11" data-toggle="tooltip"
															data-placement="top" onclick="Vaccine(11);" value="1">&nbsp;&nbsp;
												<input type="radio" name="vaccine11" data-toggle="tooltip"
															data-placement="top" onclick="Vaccine(11);" value="2"
															checked>
													</c:if></td>
											</tr>
										</c:if>
										<!-- <tr>
										
										<td class="field-label col-xs-8 col-sm-8 col-md-8 ">
											Add new Vaccine
										</td>
										<td class="field-label col-xs-3 col-sm-3 col-md-3">
											<input type="button" value="Add" id="vaccine"></td>
									</tr>  -->
									</table>
								</div>


							</div>
						</div>
					</div>

					<div class="accordion-group">
						<div class="accordion-heading bg_w">
							<a class="accordion-toggle" role="button" data-toggle="collapse"
								data-parent="#accordion2" href="#collapseTwo"> <span
								class="glyphicon glyphicon-edit"></span> Your Vaccinations
							</a>
						</div>
						<div style="height: 3px;"></div>
						<div id="collapseTwo" class="accordion-body collapse in">
							<div class="accordion-inner">
								<div class="panel panel-default">
									<div class="panel-heading" style="background-color: white;">
										<div class="panel-title"></div>

									</div>

									<table class="table"
										style="width: 100%; padding-left: 1%; font-size: 0.9em;">
										<tr>
											<td class="field-label col-xs-1 col-sm-1 col-md-1 ">Date</td>
											<td class="field-label col-xs-8 col-sm-8 col-md-8 ">Vaccination
											</td>
											<td class="field-label col-xs-3 col-sm-3 col-md-3">Status<br>Yes/No
											</td>


										</tr>

										<c:if test="${not empty vaccinations}">
											<c:forEach var="vaccination" items="${vaccinations}">

												<tr>
													<td class="field-label col-xs-1 col-sm-1 col-md-1 ">
														${vaccination.duedate.getDate()}/${vaccination.duedate.getMonth()+1}/${vaccination.duedate.getYear()+1900}
														at ${vaccination.time}</td>
													<td class="field-label col-xs-8 col-sm-8 col-md-8 ">${vaccination.name}
													</td>
													<td class="field-label col-xs-3 col-sm-3 col-md-3"><c:if
															test="${vaccination.status == 1}">
															<input type="radio"
																name="vaccine${vaccination.patientId}${vaccination.id}"
																data-toggle="tooltip" data-placement="top"
																onclick="VaccineStatus(${vaccination.patientId},${vaccination.id});"
																value="1" checked>&nbsp;&nbsp;
												<input type="radio"
																name="vaccine${vaccination.patientId}${vaccination.id}"
																data-toggle="tooltip" data-placement="top"
																onclick="VaccineStatus(${vaccination.patientId},${vaccination.id});"
																value="2">
														</c:if> <c:if test="${vaccination.status == 0}">
															<input type="radio"
																name="vaccine${vaccination.patientId}${vaccination.id}"
																data-toggle="tooltip" data-placement="top"
																onclick="VaccineStatus(${vaccination.patientId},${vaccination.id});"
																value="1">&nbsp;&nbsp;
												<input type="radio"
																name="vaccine${vaccination.patientId}${vaccination.id}"
																data-toggle="tooltip" data-placement="top"
																onclick="VaccineStatus(${vaccination.patientId},${vaccination.id});"
																value="2" checked>
														</c:if></td>
													<td><span class="close"
														onclick="deleteVaccine(${vaccination.id})">&times;</span></td>
												</tr>

											</c:forEach>
										</c:if>

										<tr>

											<td class="field-label col-xs-8 col-sm-8 col-md-8 ">Add
												Vaccine</td>
											<td class="field-label col-xs-3 col-sm-3 col-md-3"><input
												type="button" class="btn btn-info btn-xs" value="Add"
												id="vaccine"></td>
										</tr>
									</table>
								</div>


							</div>
						</div>
					</div>

				</div>


			</div>


			<div class="col-xs-12 col-sm-7 col-md-5 column">
				<div class="panel panel-default">
					<div class="panel-heading" style="background-color: white;">
						<div class="panel-title">
							<h5>
								Detailed Medical History <span
									class="pull-right glyphicon glyphicon-list-alt" style="color:;"></span>
							</h5>
						</div>
						<!--hr align="left"  style="margin-top:;border:1px solid #ffa500;width:50px;"--->

					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-xs-6 col-sm-6 col-md-6 column">
								<ul style="list-style: none; padding: 2px;">

									<li>BP Level</li>
									<li>Sugar Level</li>
									<li>Cholestrol</li>
									<li>Body Mass Index(BMI)</li>
								</ul>
							</div>

							<div class="col-xs-6 col-sm-6 col-md-6 column">
								<ul style="list-style: none; padding: 2px;">
									<li><c:if test="${not empty patient.bp}">${patient.bp}(${patient.bpStatus})</c:if></li>
									<li><c:if test="${patient.sugar ne 0}">${patient.sugar}(${patient.sugarStatus})</c:if></li>
									<li><c:if test="${patient.cholesterol ne 0}">${patient.cholesterol}(${patient.cholesterolStatus})</c:if></li>
									<li><c:if test="${patient.bmi ne 0}">${patient.bmi}</c:if></li>

								</ul>
							</div>
							<div class="col-md-12 column" style="text-align: right;">
								<a href="prescription.patient"><input type="button"
									class="btn btn-green btn-xs " value="Medical Tracker" /></a>
							</div>
						</div>
					</div>

				</div>








				<c:if test="${not empty prescriptions}">
					<c:forEach var="prescription" items="${prescriptions}">

						<div class="panel-group" id="accordion" role="tablist"
							aria-multiselectable="false">
							<div class="panel panel-default">
								<div class="panel-heading presctitle" role="tab"
									id="heading${prescription.id}"
									style="background-color: white; color: black;">

									<a role="button" data-toggle="collapse"
										data-parent="#accordion" href="#${prescription.id}"
										aria-expanded="false" aria-controls="${prescription.id}"
										style="background-color: white; color: black;"> <c:if
											test="${not empty prescription.doctorName}">

											<span class="glyphicon glyphicon-edit"></span>  Prescribed by ${prescription.doctorName} on ${prescription.prescribedDate.getDate()}/${prescription.prescribedDate.getMonth()+1}/${prescription.prescribedDate.getYear()+1900} at ${prescription.prescribedDate.getHours()}:${prescription.prescribedDate.getMinutes()}
												
											
										</c:if> <c:if test="${empty prescription.doctorName}">
											<span class="glyphicon glyphicon-edit"></span>	Self Prescription on ${prescription.prescribedDate.getDate()}/${prescription.prescribedDate.getMonth()+1}/${prescription.prescribedDate.getYear()+1900} at ${prescription.prescribedDate.getHours()}:${prescription.prescribedDate.getMinutes()}
										</c:if>
									</a>

								</div>
								<div id="${prescription.id}" class="accordion-body collapse"
									role="tabpanel" aria-labelledby="heading${prescription.id}">
									<div class="panel-body">
										<c:if test="${not empty prescription.doctorName}">
											<div class="row" style="padding: 8px">
												<div class="col-xs-4 col-sm-4 col-md-4 column">Doctor
													Name</div>
												<div class="col-xs-8 col-sm-8 col-md-8 column"
													style="word-wrap: break-word;">
													<a
														href="patientViewDoctor.profile?id=${prescription.doctorId}">${prescription.doctorName}</a>
												</div>
											</div>
										</c:if>



										<div class="row" style="padding: 8px">
											<div class="col-xs-4 col-sm-4 col-md-4 column ">Symptoms
											</div>
											<div class="col-xs-8 col-sm-8 col-md-8 column"
												style="word-wrap: break-word;">
												<c:if test="${not empty prescription.sName1}">${prescription.sName1}</c:if>
												<c:if test="${not empty prescription.sName2}">,${prescription.sName2}</c:if>
												<c:if test="${not empty prescription.sName3}">,${prescription.sName3}</c:if>
												<c:if test="${not empty prescription.sName4}">,${prescription.sName4}</c:if>
												<c:if test="${not empty prescription.sName5}">,${prescription.sName5}</c:if>
											</div>
										</div>


										<div class="row" style="padding: 8px">
											<div class="col-xs-4 col-sm-4 col-md-4 column">Diseases
											</div>
											<div class="col-xs-8 col-sm-8 col-md-8 column"
												style="word-wrap: break-word;">
												<c:if test="${not empty prescription.dName1}">${prescription.dName1}</c:if>
												<c:if test="${not empty prescription.dName2}">,${prescription.dName2}</c:if>
												<c:if test="${not empty prescription.dName3}">,${prescription.dName3}</c:if>
												<c:if test="${not empty prescription.dName4}">,${prescription.dName4}</c:if>
												<c:if test="${not empty prescription.dName5}">,${prescription.dName5}</c:if>
											</div>
										</div>

										<table class="table table-condensed" id="medicinecontainer">
											<tr id="medicinerow">
												<td class="field-label col-xs-4 active"><label>Medicine
														Name </label></td>
												<td class="field-label col-xs-2 active"><label>Quantity</label>
												</td>
												<td class="field-label col-xs-3 active"><label>Dosage</label>
												</td>
												<td class="field-label col-xs-3 active"><label>
														Time</label></td>
											</tr>
											<c:forEach var="medicine" items="${prescription.medicines}">
												<tr>
													<td class="col-md-4 active">${medicine.name}</td>
													<td class="col-md-2 active">${medicine.quantity}</td>
													<td class="col-md-3 active"><c:if
															test="${medicine.morning ne 0}">Morning<br />
														</c:if> <c:if test="${medicine.afternoon ne 0}">AfterNoon<br />
														</c:if> <c:if test="${medicine.night ne 0}">Night<br />
														</c:if></td>
													<td class="col-md-3 active"><c:if
															test="${medicine.morning ne 0}">${medicine.mt}<br />
														</c:if> <c:if test="${medicine.afternoon ne 0}">${medicine.at}<br />
														</c:if> <c:if test="${medicine.night ne 0}">${medicine.nt}<br />
														</c:if></td>
												</tr>
											</c:forEach>

										</table>
									</div>
								</div>
							</div>
						</div>


					</c:forEach>
				</c:if>

			</div>


			<div class="col-sm-4 column" style="color: red;">${error}</div>
			<%@ include file="patientAside.jsp"%>
			
			 

		<div class="modal fade" id="addVaccine" role="dialog"
			data-backdrop="static" data-keyboard="false">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" id="close" class="close"
							data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Add new Vaccine</h4>
					</div>
					<div class="modal-body">
						<form action="javascript:addVaccine()" method="post"
							class="form-horizontal">
							<table class="table" style="margin-top: 5%;">

								<tr>
									<td class="field-label col-xs-3 active"><label>
											Vaccine Name </label></td>
									<td class="col-md-9"><input id="vaccineName"
										class="form-control input-sm" type="text" name="name" required /></td>
								</tr>
								<tr>
									<td class="field-label col-xs-3 active"><label>
											Date </label></td>
									<td class="col-md-9"><input id="dueDate"
										class="form-control input-sm" name="dob" type="date"
										data-toggle="tooltip" data-placement="top"
										placeholder="vaccine date(dd/mm/yyyy)" required /></td>
								</tr>

								<tr>
									<td class="field-label col-xs-3 active"><label>
											Time </label></td>
									<td class="col-md-9"><input id="dueTime"
										class="form-control input-sm" name="dob" type="time"
										data-toggle="tooltip" data-placement="top"
										placeholder="vaccine time(hh:mm)" required /></td>
								</tr>


							</table>

							<div style="text-align: right;">
								<button type="button"
									class="btn btn-default btn btn-default btn-info btn-sm"
									data-dismiss="modal">Close</button>
								<input class="btn btn-info btn-sm" type="submit" value="Save">
							</div>

						</form>
					</div>
				</div>

			</div>
		</div>

		<script>
	
		

		
</script>
</body>
</html>










