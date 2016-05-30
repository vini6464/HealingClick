<%@ include file="doctorHeader.jsp" %>
<script src="js/jquery-ui.min.js"></script>
<link href="css/jquery-ui.min.css" rel="stylesheet">
<script type="text/javascript">
var symptomList=[];
var medList=[];
$( document ).ready(function() {
	$("#s2").hide();
	$("#s3").hide();
	$("#s4").hide();
	$("#s5").hide();
	
	$("#d2").hide();
	$("#d3").hide();
	$("#d4").hide();
	$("#d5").hide();
	
	$("#m2").hide();
	$("#m3").hide();
	$("#m4").hide();
	$("#m5").hide();
	
	$("#mt1").hide();
	$("#mt2").hide();
	$("#mt3").hide();
	$("#mt4").hide();
	$("#mt5").hide();
	
	$("#at1").hide();
	$("#at2").hide();
	$("#at3").hide();
	$("#at4").hide();
	$("#at5").hide();
	
	$("#nt1").hide();
	$("#nt2").hide();
	$("#nt3").hide();
	$("#nt4").hide();
	$("#nt5").hide();
	
	
	$.ajax({
		method : "GET",
		url : 'symptoms.notification',
		datatype : 'json',
		success : function(result) {
			
			
			symptomList = $.parseJSON(result);
			/*for ( var i = 0; i < d.length; i++) {
			 html = html + "<option value='"+d[i].name+"'>"+d[i].name+"</option>"; 
				
				symptomList.push('' + d[i].name);
			}
			 $("#symptoms").html(html); */
			
			$("#sym1").autocomplete({source: symptomList}); 
			$("#dis1").autocomplete({source: symptomList}); 
			$("#sym2").autocomplete({source: symptomList}); 
			$("#sym3").autocomplete({source: symptomList}); 
			$("#sym4").autocomplete({source: symptomList}); 
			$("#sym5").autocomplete({source: symptomList}); 
			$("#dis2").autocomplete({source: symptomList}); 
			$("#dis3").autocomplete({source: symptomList}); 
			$("#dis4").autocomplete({source: symptomList}); 
			$("#dis5").autocomplete({source: symptomList}); 
		},
		statusCode : {
			500 : function(result) {
				
			}
		}
	});


	$.ajax({
		method : "GET",
		url : 'medicines.notification',
		datatype : 'json',
		success : function(result) {
			
			medList = $.parseJSON(result);
			/* for ( var i = 0; i < d.length; i++) {
			 html = html + "<option value='"+d[i].name+"'>"+d[i].name+"</option>"; 
				
				medList.push('' + d[i].name); 
			}
			  $("#medicines").html(html); */ 
			
			$("#med1").autocomplete({source: medList});
			$("#med2").autocomplete({source: medList});
			$("#med3").autocomplete({source: medList});
			$("#med4").autocomplete({source: medList});
			$("#med5").autocomplete({source: medList});
			
		},
		statusCode : {
			500 : function(result) {
				
			}
		}
	});
});
</script>
<script type="text/javascript">
	
	$(function() {
		$("#symptom").click(function() {
			if ($("#s1").is(":visible")) {
			if ($("#s2").is(":visible")) {

				if ($("#s3").is(":visible")) {

					if ($("#s4").is(":visible")) {

						$("#s5").show();
						$("#symptom").hide();
					}
					if ($("#s4").is(":hidden")) {
						$("#s4").show();
					}
				}
				if ($("#s3").is(":hidden")) {
					$("#s3").show();
				}
			}

			if ($("#s2").is(":hidden")) {
				$("#s2").show();
			}
			}
			if ($("#s1").is(":hidden")) {
				$("#s1").show();
			}

		});
	});
	
	$(function() {
		$("#disease").click(function() {

			if ($("#d1").is(":visible")) {
			
			if ($("#d2").is(":visible")) {

				if ($("#d3").is(":visible")) {

					if ($("#d4").is(":visible")) {

						$("#d5").show();
						$("#disease").hide();
					}
					if ($("#d4").is(":hidden")) {
						$("#d4").show();
					}
				}
				if ($("#d3").is(":hidden")) {
					$("#d3").show();
				}
			}

			if ($("#d2").is(":hidden")) {
				$("#d2").show();
			}
			}
			if ($("#d1").is(":hidden")) {
				$("#d1").show();
			}

		});
	});
	
	$(function() {
		$("#medicine").click(function() {
			if ($("#m1").is(":visible")) {
			if ($("#m2").is(":visible")) {

				if ($("#m3").is(":visible")) {

					if ($("#m4").is(":visible")) {

						$("#m5").show();
						$("#medicine").hide();
					}
					if ($("#m4").is(":hidden")) {
						$("#m4").show();
					}
				}
				if ($("#m3").is(":hidden")) {
					$("#m3").show();
				}
			}

			if ($("#m2").is(":hidden")) {
				$("#m2").show();
			}
			}
			if ($("#m1").is(":hidden")) {
				$("#m1").show();
			}

		});
	});
	
	
	function removeSymptom(id){
		$("#s"+id).html('<input name=s'+id+' id=symp'+id+' list=symptoms ><input type=button class="pull-right" value=x onclick="removeSymptom('+id+');">');
		$("#symp"+id).autocomplete({source: symptomList}); 
		$("#s"+id).hide();
		if ($("#s1").is(":visible")) {
			if ($("#s2").is(":visible")) {
				if ($("#s3").is(":visible")) {
					if ($("#s4").is(":visible")) {
						if ($("#s5").is(":visible")) {
							$("#symptom").hide();
						}
						else
							$("#symptom").show();
					}
					else
						$("#symptom").show();
				}
				else
					$("#symptom").show();
			}
			else
				$("#symptom").show();
		}
		else
			$("#symptom").show();	
	}

	function removeDisease(id){
		$("#d"+id).html('<input name=d'+id+' id=dis'+id+' list=symptoms><input type=button class="pull-right" value=x onclick="removeDisease('+id+');">');
		$("#dis"+id).autocomplete({source: symptomList}); 
		$("#d"+id).hide();
		
		if ($("#d1").is(":visible")) {
			if ($("#d2").is(":visible")) {
				if ($("#d3").is(":visible")) {
					if ($("#d4").is(":visible")) {
						if ($("#d5").is(":visible")) {
							$("#disease").hide();
						}
						else
							$("#disease").show();
					}
					else
						$("#disease").show();
				}
				else
					$("#disease").show();
			}
			else
				$("#disease").show();
		}
		else
			$("#disease").show();	
	}

	function removeMedicine(id){
		$("#m"+id).html('<td class="field-label col-xs-2 col-sm-2 col-md-2"><input name=m'+id+' id=med'+id+' list=medicines  autocomplete=off></td>'+
	    '<td class="field-label col-xs-2 col-sm-2 col-md-2"><input type=number name=q'+id+' style="width:70px;"/></td>'+
				'<td class="field-label col-xs-3 col-sm-3 col-md-3"> <input type="checkbox" value=1 id=morning'+id+' name=morning'+id+' onclick=getMorningTime('+id+');>Morning <br />'+
				'<input type=checkbox value=1 id=afternoon'+id+' name=afternoon'+id+' onclick=getAfternoonTime('+id+');>AfterNoon <br />'+
				'<input type=checkbox value=1 id=night'+id+' name=night'+id+' onclick=getNightTime('+id+');>Night  </td>'+
				'<td class=field-label col-xs-2 col-sm-2 col-md-3><input type=time id=mt'+id+' name=mt'+id+'><br/><input type=time id=at'+id+' name=at'+id+'><br/><input type=time id=nt'+id+' name=nt'+id+'>'+ 
				'<input type=button class="pull-right" value=x onclick="removeMedicine('+id+');"></td>');
		$("#med"+id).autocomplete({source: medList});
		$("#mt"+id).hide();
		$("#at"+id).hide();
		$("#nt"+id).hide();
		$("#m"+id).hide();
		
		if ($("#m1").is(":visible")) {
			if ($("#m2").is(":visible")) {
				if ($("#m3").is(":visible")) {
					if ($("#m4").is(":visible")) {
						if ($("#m5").is(":visible")) {
							$("#medicine").hide();
						}
						else
							$("#medicine").show();
					}
					else
						$("#medicine").show();
				}
				else
					$("#medicine").show();
			}
			else
				$("#medicine").show();
		}
		else
			$("#medicine").show();	
	}
	
	
</script>

<style type="text/css">


/* .ui-autocomplete { height: 200px; overflow-y: scroll; overflow-x: hidden;} */


#mt1,#mt2,#mt3,#mt4,#mt5,#at1,#at2,#at3,#at4,#at5,#nt1,#nt2,#nt3,#nt4,#nt5{height:20px;margin-bottom:1px;}
</style>
	<div class="container-fluid" style="margin-top: 4%;">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="row clearfix">
				<div class="col-md-2 column"></div>
					<div class="col-md-6 column">
					<form action="prescriptionSave.doctor?id=${patient.id}"
									name="form1" method="post" onSubmit="return checkPrescriptionForm();" enctype="multipart/form-data">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h5 class="panel-title">
									Prescription</h5>
							</div>
							
							<div class="panel-body">

 <datalist id="medicines">
 </datalist>
 <datalist id="symptoms">
 </datalist>

								

									<table class="table" style="margin-top: 2%;">
										<tr>
											<td class="field-label col-xs-3 active"><label>Doctor
													Name</label></td>
											<td class="col-md-9">${doctor.firstName}</td>
										</tr>
										<tr>
											<td class="field-label col-xs-3 active"><label>
													Patient Name </label></td>
											<td class="col-md-9">${patient.firstName}</td>
										</tr>
										<tr>
											<td class="field-label col-xs-3 active"><label>Symptoms</label>
											</td>
											<td class="col-md-9">
											<div id=s1 style="margin-top:1%;"><input name=s1 id="sym1" list=symptoms><input type=button class="pull-right" value=x onclick="removeSymptom(1);"></div>
											<div id=s2 style="margin-top:1%;"><input name=s2 id="sym2" list=symptoms><input type=button class="pull-right" value=x onclick="removeSymptom(2);"></div> 
											<div id=s3 style="margin-top:1%;"><input name=s3 id="sym3" list=symptoms><input type=button class="pull-right" value=x onclick="removeSymptom(3);"></div> 
											<div id=s4 style="margin-top:1%;"><input name=s4 id="sym4" list=symptoms><input type=button class="pull-right" value=x onclick="removeSymptom(4);"></div> 
											<div id=s5 style="margin-top:1%;"><input name=s5 id="sym5" list=symptoms><input type=button class="pull-right" value=x onclick="removeSymptom(5);"></div> 
											<input type=button value=+ id=symptom></td>
										</tr>

										<tr>
											<td class="field-label col-xs-3 active"><label>Diseases</label>
											</td>
											<td class="col-md-9"><div id=d1 style="margin-top:1%;"><input name=d1 id=dis1 list=symptoms><input type=button class="pull-right" value=x onclick="removeDisease(1);"></div>
											<div id=d2 style="margin-top:1%;"><input name=d2  id=dis2 list=symptoms><input type=button class="pull-right" value=x onclick="removeDisease(2);"></div> 
											<div id=d3 style="margin-top:1%;"><input name=d3  id=dis3 list=symptoms><input type=button class="pull-right" value=x onclick="removeDisease(3);"></div> 
											<div id=d4 style="margin-top:1%;"><input name=d4  id=dis4 list=symptoms><input type=button class="pull-right" value=x onclick="removeDisease(4);"></div> 
											<div id=d5 style="margin-top:1%;"><input name=d5  id=dis5 list=symptoms><input type=button class="pull-right" value=x onclick="removeDisease(5);"></div> 
											<input type=button value=+ id=disease></td>
										</tr>



									</table>


									<table class="table" style="margin-top: 5%;">
										<tr>
											<td class="field-label col-xs-2 col-sm-2 col-md-2 active"><label>Medicine
													Name </label></td>
											<td class="field-label col-xs-2 col-sm-2 col-md-2 active"><label>Quantity</label>
											</td>
											<td class="field-label col-xs-3 col-sm-3 col-md-3 active"><label>Dosage</label>
											</td>
											<td class="field-label col-xs-2 col-sm-2 col-md-2 active"><label>Time</label>
											</td>
										</tr>
										
										<tr id=m1>
					<td class='field-label col-xs-2 col-sm-2 col-md-2'><input name=m1 id=med1 list=medicines autocomplete=off></td>
					<td class='field-label col-xs-2 col-sm-2 col-md-2'><input type=number name=q1 style='width:70px;'/></td>
					<td class='field-label col-xs-3 col-sm-3 col-md-3'> <input type="checkbox" value=1 id=morning1 name=morning1 onclick=getMorningTime(1);>Morning <br />
					<input type=checkbox value=1 id=afternoon1 name=afternoon1 onclick=getAfternoonTime(1);>AfterNoon <br />
					<input type=checkbox value=1 id=night1 name=night1 onclick=getNightTime(1);>Night  </td>
					<td class='field-label col-xs-2 col-sm-2 col-md-3'><input type='time' id=mt1 name='mt1'><br/><input type='time' id=at1 name='at1'><br/><input type='time' id=nt1 name='nt1'> 
					<input type=button class="pull-right" value=x onclick="removeMedicine(1);"></td>
				</tr>
				<tr id=m2>
					<td class='field-label col-xs-2 col-sm-2 col-md-2'><input name=m2 id=med2 list=medicines autocomplete=off></td>
					<td class='field-label col-xs-2 col-sm-2 col-md-2'><input type=number name=q2 style='width:100px;'/></td>
					<td class='field-label col-xs-3 col-sm-3 col-md-3'> <input type="checkbox" value=1 id=morning2 name=morning2 onclick=getMorningTime(2);>Morning <br />
					<input type=checkbox value=1 id=afternoon2 name=afternoon2 onclick=getAfternoonTime(2);>AfterNoon <br />
					<input type=checkbox value=1 id=night2 name=night2 onclick=getNightTime(2);>Night  </td>
					<td class='field-label col-xs-2 col-sm-2 col-md-3'><input type='time' id=mt2 name='mt2'><br/><input type='time' id=at2 name='at2'><br/><input type='time' id=nt2 name='nt2'><input type=button class="pull-right" value=x onclick="removeMedicine(2);"></td>
				</tr>
				<tr id=m3>
					<td class='field-label col-xs-2 col-sm-2 col-md-2'><input name=m3 id=med3 list=medicines autocomplete=off></td>
					<td class='field-label col-xs-2 col-sm-2 col-md-2'><input type=number name=q3 style='width:100px;'/></td>
					<td class='field-label col-xs-3 col-sm-3 col-md-3'> <input type="checkbox" value=1 id=morning3 name=morning3 onclick=getMorningTime(3);>Morning <br />
					<input type=checkbox value=1 id=afternoon3 name=afternoon3 onclick=getAfternoonTime(3);>AfterNoon <br />
					<input type=checkbox value=1 id=night3 name=night3 onclick=getNightTime(3);>Night  </td>
					<td class='field-label col-xs-2 col-sm-2 col-md-3'><input type='time' id=mt3 name='mt3'><br/><input type='time' id=at3 name='at3'><br/><input type='time' id=nt3 name='nt3'><input type=button class="pull-right" value=x onclick="removeMedicine(3);"></td>
				</tr>
				<tr id=m4>
					<td class='field-label col-xs-2 col-sm-2 col-md-2'><input name=m4 id=med4 list=medicines autocomplete=off></td>
					<td class='field-label col-xs-2 col-sm-2 col-md-2'><input type=number name=q4 style='width:100px;'/></td>
					<td class='field-label col-xs-3 col-sm-3 col-md-3'> <input type="checkbox" value=1 id=morning4 name=morning4 onclick=getMorningTime(4);>Morning <br />
					<input type=checkbox value=1 id=afternoon4 name=afternoon4 onclick=getAfternoonTime(4);>AfterNoon <br />
					<input type=checkbox value=1 id=night4 name=night4 onclick=getNightTime(4);>Night  </td>
					<td class='field-label col-xs-2 col-sm-2 col-md-3'><input type='time' id=mt4 name='mt4'><br/><input type='time' id=at4 name='at4'><br/><input type='time' id=nt4 name='nt4'><input type=button class="pull-right" value=x onclick="removeMedicine(4);"></td>
				</tr>
				<tr id=m5>
					<td class='field-label col-xs-2 col-sm-2 col-md-2'><input name=m5 id=med5 list=medicines autocomplete=off></td>
					<td class='field-label col-xs-2 col-sm-2 col-md-2'><input type=number name=q5 style='width:100px;'/></td>
					<td class='field-label col-xs-3 col-sm-3 col-md-3'><input type="checkbox" value=1 id=morning5 name=morning5 onclick=getMorningTime(5);>Morning <br />
					<input type=checkbox value=1 id=afternoon5 name=afternoon5 onclick=getAfternoonTime(5);>AfterNoon <br />
					<input type=checkbox value=1 id=night5 name=night5 onclick=getNightTime(5);>Night  </td>
					<td class='field-label col-xs-2 col-sm-2 col-md-3'><input type='time' id=mt5 name='mt5'><br/><input type='time' id=at5 name='at5'><br/><input type='time' id=nt5 name='nt5'><input type=button class="pull-right" value=x onclick="removeMedicine(5);"></td>
				</tr>
			</table>
				<input type='button' value='+' id=medicine>
			
									


									<table class="table" style="margin-top: 5%;">
										<tr>
											<td class="field-label col-xs-3 active"><label>Prescription
													Image </label></td>
											<td class="col-md-9">
												<div class="form-group">
													<input id="exampleInputFile" type="file" name="file" accept="image/*"/>

												</div>
											</td>
										</tr>
										
										<tr>
											<td class="field-label col-xs-3 active"><label>BP Level
													 </label></td>
											<td class="col-md-9">
												<div class="form-group">
													<input id="exampleInputFile" type="text" name="bp"  onblur="checkBP(${age});"/>
													<span id="bp" > </span><span id="errorbp" style="color:red;"> </span>
												</div>
											</td>
										</tr>
										
										<tr>
											<td class="field-label col-xs-3 active"><label>Sugar Level
													 </label></td>
											<td class="col-md-9">
												<div class="form-group">
													<input id="exampleInputFile" type="number" name="sugar" value=0  step="0.01"  onblur="checkSugar(${age});"/>
													<span id="sugar" > </span>
												</div>
												
											</td>
										</tr>
										
										<tr>
											<td class="field-label col-xs-3 active"><label>Cholesterol Level
													 </label></td>
											<td class="col-md-9">
												<div class="form-group">
													<input id="exampleInputFile" type="number" name="cholesterol" value=0  step="0.01"  onblur="checkCholesterol(${age});"/>
													<span id="cholesterol"> </span>
												</div>
												
											</td>
										</tr>
										<tr>
											<td class="field-label col-xs-3 active"><label>
													Physical CheckUp  </label></td>
											<td class="col-md-9">
											 <input type="radio"
												name="checkup" data-toggle="radio" id="optionsRadios1"
												value="1" checked> <i></i><small
												style="color:; letter-spacing: 1px;">Yes</small>
											
										
											 <input type="radio"
												name="checkup" data-toggle="radio" id="optionsRadios1"
												value="2"> <i></i><small
												style="color:; letter-spacing: 1px;">No</small>
											

										</td>
										</tr>
										<tr>
											<td class="field-label col-xs-3 active"><label>
													Suggestions </label></td>
											<td class="col-md-9"><textarea class="form-control"
													rows="4" placeholder="" name=suggestion></textarea></td>
										</tr>
										</table>
							</div>
							<div class="panel-footer">
								<div class="row">
									<div class="col-md-6 column"></div>
									<div class="col-md-3 column">
										<input class="btn btn-info btn-block btn-xs"
											type="submit" value="Submit">
									</div>
									<div class="col-md-3 column">
										<a class="btn btn-info btn-block btn-xs" type="button" href="doctor.home"
											style="width: %;">Cancel</a>
									</div>
								</div>
							</div>
							
						</div>
</form>

<script src="js/bootstrap.min.js"></script>
					</div>


<%@ include file="doctorAside.jsp" %>
			</div>






			
			
			
</body>
</html>










