<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="patientHeader.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<link href='calendar/css/fullcalendar.css' rel='stylesheet' />
<link href='calendar/css/fullcalendar.print.css' rel='stylesheet'
	media='print' />
<script src='calendar/lib/moment.min.js'></script>
<script
	src="js/bootstrap.min.js"></script>
<script src='calendar/js/fullcalendar.js'></script>
<script src="js/jquery-ui.min.js"></script>
<link href="css/jquery-ui.min.css" rel="stylesheet">
<style type="text/css">


.ui-autocomplete { position: absolute; cursor: default;z-index:99999 !important; }
</style>

<script>

var event;
var symptomList=[];
var doctorId =  "${doctor.id}";
var patientId = "${patient.id}";

		$( document ).ready(function() {
			 var insert = "${insert}";
				if(insert == 1)
					{
						window.history.pushState("", "", 'fixAppointment.patient?id='+doctorId);
					}
				
				
				$.ajax({
					method : "GET",
					url : 'symptoms.notification',
					datatype : 'json',
					success : function(result) {
						symptomList = $.parseJSON(result);
						
						
						$("#sym1").autocomplete({source: symptomList}); 
						
						$("#sym2").autocomplete({source: symptomList}); 
						$("#sym3").autocomplete({source: symptomList}); 
						$("#sym4").autocomplete({source: symptomList}); 
						$("#sym5").autocomplete({source: symptomList}); 
						
$("#ssym1").autocomplete({source: symptomList}); 
						
						$("#ssym2").autocomplete({source: symptomList}); 
						$("#ssym3").autocomplete({source: symptomList}); 
						$("#ssym4").autocomplete({source: symptomList}); 
						$("#ssym5").autocomplete({source: symptomList}); 
					},
					statusCode : {
						500 : function(result) {
							
						}
					}
				});
			
		});



$( document ).ready(function() {
	$("#s2").hide();
	$("#s3").hide();
	$("#s4").hide();
	$("#s5").hide();
	$("#ss2").hide();
	$("#ss3").hide();
	$("#ss4").hide();
	$("#ss5").hide();
});

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
	$("#ssymptom").click(function() {
		if ($("#ss1").is(":visible")) {
		if ($("#ss2").is(":visible")) {

			if ($("#ss3").is(":visible")) {

				if ($("#ss4").is(":visible")) {

					$("#ss5").show();
					$("#ssymptom").hide();
				}
				if ($("#ss4").is(":hidden")) {
					$("#ss4").show();
				}
			}
			if ($("#ss3").is(":hidden")) {
				$("#ss3").show();
			}
		}

		if ($("#ss2").is(":hidden")) {
			$("#ss2").show();
		}
		}
		if ($("#ss1").is(":hidden")) {
			$("#ss1").show();
		}

	});
});

function removeSymptom(id){
	$("#s"+id).html('<input name=s'+id+' id=sym'+id+' list=symptoms ><input type=button class="pull-right" value=x onclick="removeSymptom('+id+');">');
	$("#sym"+id).autocomplete({source: symptomList}); 
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

function removesSymptom(id){
	$("#ss"+id).html('<input name=s'+id+' id=ssym'+id+' list=symptoms ><input type=button class="pull-right" value=x onclick="removeSymptom('+id+');">');
	$("#ssym"+id).autocomplete({source: symptomList}); 
	$("#ss"+id).hide();
	if ($("#ss1").is(":visible")) {
		if ($("#ss2").is(":visible")) {
			if ($("#ss3").is(":visible")) {
				if ($("#ss4").is(":visible")) {
					if ($("#ss5").is(":visible")) {
						$("#ssymptom").hide();
					}
					else
						$("#ssymptom").show();
				}
				else
					$("#ssymptom").show();
			}
			else
				$("#ssymptom").show();
		}
		else
			$("#ssymptom").show();
	}
	else
		$("#ssymptom").show();	
}
function SwitchDetails(){
	$("#details").hide();
	$("#ss2").hide();
	$("#ss3").hide();
	$("#ss4").hide();
	$("#ss5").hide();
	$("#update").show();
	
}


	function validateInsertTime() {

		var start = document.getElementById("start").value;
		var end = document.getElementById("last").value;
		
		start = start.split(":");
		end = end.split(":");
		
		var h1 = start[0];
		var m1 = start[1];
		
		var h2 = end[0];
		var m2 = end[1];
		
		
	if ((h2 - h1) == 0 ) {
		
			if((m2-m1) <= 30){
				
				if((m2-m1) < 0)
					{
					$("#errortime").show();
					var span = document.getElementById("errortime");
					var txt = document.createTextNode("Please make sure that start time is not more than end time");
					span.innerText = txt.textContent;
					
					return false;
					}
				else
					{
					return true;
					}
				
			}
			else
				{
				
				$("#errortime").show();
				var span = document.getElementById("errortime");
				var txt = document.createTextNode("Sorry You Cant book for appointment more than 30 minutes");
				span.innerText = txt.textContent;
				
				return false;
				}
		}
	else
		if ((h2 - h1) == 1 )
		{
			debugger
			m2 = +m2 + +60;
			
			if((m2-m1) <= 30){
				return true;
			}
			else
				{
				$("#errortime").show();
				var span = document.getElementById("errortime");
				var txt = document.createTextNode("Sorry You Cant book for appointment more than 30 minutes");
				span.innerText = txt.textContent;
				
				return false;
				}
			
		}
		
	if((h2-h1)<0)
		{
		$("#errortime").show();
		var span = document.getElementById("errortime");
		var txt = document.createTextNode("Please make sure that start time is not more than end time");
		span.innerText = txt.textContent;
		
		return false;
		}
	
	if((h2-h1)>1)
	{
	$("#errortime").show();
	var span = document.getElementById("errortime");
	var txt = document.createTextNode("Sorry You Cant book for appointment more than 30 minutes");
	span.innerText = txt.textContent;
	
	return false;
	}

	}

	function validateUpdateTime() {
		if ($("#st").is(":visible")) {
			var start = document.getElementById("start1").value;
			var end = document.getElementById("last1").value;
			
			start = start.split(":");
			end = end.split(":");
			
			var h1 = start[0];
			var m1 = start[1];
			
			var h2 = end[0];
			var m2 = end[1];
			
			
		if ((h2 - h1) == 0 ) {
			
				if((m2-m1) <= 30){
					
					if((m2-m1) < 0)
						{
						$("#uerrortime").show();
						var span = document.getElementById("uerrortime");
						var txt = document.createTextNode("Please make sure that start time is not more than end time");
						span.innerText = txt.textContent;
						
						return false;
						}
					else
						{
						return true;
						}
					
				}
				else
					{
					
					$("#uerrortime").show();
					var span = document.getElementById("uerrortime");
					var txt = document.createTextNode("Sorry You Cant book for appointment more than 30 minutes");
					span.innerText = txt.textContent;
					
					return false;
					}
			}
		else
			if ((h2 - h1) == 1 )
			{
				m2 = +m2 + +60;
				if((m2-m1) <= 30){
					return true;
				}
				else
					{
					$("#uerrortime").show();
					var span = document.getElementById("uerrortime");
					var txt = document.createTextNode("Sorry You Cant book for appointment more than 30 minutes");
					span.innerText = txt.textContent;
					
					return false;
					}
				
			}
			
		if((h2-h1)<0)
			{
			$("#uerrortime").show();
			var span = document.getElementById("uerrortime");
			var txt = document.createTextNode("Please make sure that start time is not more than end time");
			span.innerText = txt.textContent;
			
			return false;
			}
		
		if((h2-h1)>1)
		{
		$("#uerrortime").show();
		var span = document.getElementById("uerrortime");
		var txt = document.createTextNode("Sorry You Cant book for appointment more than 30 minutes");
		span.innerText = txt.textContent;
		
		return false;
		}
			
		} else {
			return true;
		}
	}

	function deleteEvent(id) {

		var r = confirm("Are You Sure You Want to delete this Appointment ? ");
		if (r == true) {
			window.location = "patientDoctor.appointment?operation=delete&doctorid="
					+ doctorId + "&eventId=" + id;
		}
	}
	
	
	function showAppointment(id , changeDate) {
		
		$.ajax({
			method: "GET",
			url : 'getEvent.appointment',
        	datatype:'json',
        	data: { 
        		eventId : id
			},
			success: function(result){
        
				var res = jQuery.parseJSON(result);
				var st = new Date(res.start).toJSON();
				var lt = new Date(res.end).toJSON();
				 var res1 = st.split("T");
				 var res2 = lt.split("T");
				 var day = res1[0];
				 
				 if(changeDate == 1){
					 
					 $('#calendar').fullCalendar( 'gotoDate', new Date(day));
				 }
				 var dayRes = day.split("-");
				 var st1 = res1[1].split(":");
				 st1 = st1[0]+":"+st1[1];
				
				 var lt1 = res2[1].split(":");
				 lt1 = lt1[0]+":"+lt1[1];
				if(res.patientId == patientId){
					var status = "Accepted";
					if(res.status==0)
					{
						status = "Pending";
					}
					
					
					var htmlString='<table id="details"><tr><td class="field-label col-xs-3 active"><label>Doctor Name</label></td><td class="col-md-9"> '+res.doctorName+'</td></tr>';
					htmlString = htmlString+'<tr><td class="field-label col-xs-3 active"><label>Title</label></td><td class="col-md-9"> '+res.title+'</td></tr>';
					/* htmlString = htmlString+'<tr><td class="field-label col-xs-3 active"><label>Description</label></td><td class="col-md-9"> '+res.description+'</td></tr>'; */
					htmlString = htmlString+'<tr><td class="field-label col-xs-3 active"><label>Symptoms</label></td><td class="col-md-9"> '+res.symptom+'</td></tr>';
					htmlString = htmlString+'<tr><td class="field-label col-xs-3 active"><label>Date</label></td><td class="col-md-9"> '+dayRes[2]+"/"+dayRes[1]+"/"+dayRes[0]+'</td></tr>';
					htmlString = htmlString+'<tr><td class="field-label col-xs-3 active"><label>Start Time</label></td><td class="col-md-9"> '+st1+'</td></tr>';
					htmlString = htmlString+'<tr><td class="field-label col-xs-3 active"><label>End Time</label></td><td class="col-md-9"> '+lt1+'</td></tr>';
					
					htmlString = htmlString+'<tr><td class="field-label col-xs-3 active"><label>Status</label></td><td class="col-md-9"> '+status+'</td></tr>';
					
					if(res.status==0)
					{
						htmlString = htmlString+'<tr><td class="field-label col-xs-3"><input class="btn btn-info btn-block btn-sm" type="button" value="Update" onclick="SwitchDetails()"></td><td class="col-md-9"> <input class="btn btn-info btn-block btn-sm" type="button" value="Delete" onclick = deleteEvent('+id+');></td></tr></table>';
						
						$("#st").show();
						$("#et").show();
						$('#start1').val(st1); 
						 $('#last1').val(lt1); 
						 $('#day1').text(dayRes[2]+"/"+dayRes[1]+"/"+dayRes[0]); 
					}
					
					if(res.status==1)
					{
						
						$("#st").hide();
						$("#et").hide();
						$('#day1').text(dayRes[2]+"/"+dayRes[1]+"/"+dayRes[0]); 
						htmlString = htmlString+'<tr><td class="field-label col-xs-3"></td><td class="col-md-9"><input class="btn btn-info btn-block btn-sm" type="button" value="Update" onclick="SwitchDetails()"></td></tr></table>';
					}
					
					$("#addEventId").html('<input type=text class=hidden name=day value='+day+' ><input class=hidden type=text name=eventId value='+id+'>');
					$("#showbody").html(htmlString);
					
					
					$("#update").hide();
					$("#show").modal();
				}
				else
					{
					alert("You dont have access to view this content");
					}
				
			},
			statusCode : {
				500: function(result){
					alert(result);
				}
			}
		});
		
		
	}
</script>
<script>


	$(document).ready(function() {
		
		$('#calendar').fullCalendar({
			header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month,agendaWeek,agendaDay'
			},
			defaultView: 'agendaDay',
			eventClick: function(event, element) {
            
				showAppointment(event.id ,0);
                

		    },
			selectable: true,
			selectHelper: true,
			select: function(start, end) {
				$("#s2").hide();
				$("#s3").hide();
				$("#s4").hide();
				$("#s5").hide();
				 $("#insert").modal();
				 var st = new Date(start).toJSON();
				 var lt = new Date(end).toJSON();
				
				 var res1 = st.split("T");
				 var res2 = lt.split("T");
				 var day = res1[0];
				 var dayRes = day.split("-");
				 var st1 = res1[1].split(":");
				 st1 = st1[0]+":"+st1[1];
				
				 var lt1 = res2[1].split(":");
				 lt1 = lt1[0]+":"+lt1[1];
				 
				 $("#no").html('<input type=text class=hidden name=day value='+day+' >');
				
				 $('#start').val(st1); 
				 $('#last').val(lt1); 
				 $('#day').text(dayRes[2]+"/"+dayRes[1]+"/"+dayRes[0]); 
				
				$('#calendar').fullCalendar('unselect');
			},
			editable: true,
			eventLimit: true, // allow "more" link when too many events
			events: "getPatientDoctorEvents.appointment?doctorId="+doctorId
		});
		
		var eventId = "${event}";
		showAppointment(eventId , 1);
		
	});

</script>


<style>
body{font-family: 'Josefin Sans', sans-serif;
  font-weight:;letter-spacing:1px;
  background-color: #f6f6f6;
	color: #666666;}

tr{border:1px solid #d8d8d8;}
#calendar {
font-size:0.9em;
	max-width: 900px;
	margin: 30px auto;
	padding:20px;
	background-color:white;
	
}
</style>
<title>Calendar</title>
</head>
<body>
<div class="h_30"></div><div class="h_20"></div>
	<div id='calendar'></div>


	<div class="modal fade" id="insert" role="dialog" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<datalist id="symptoms">
			</datalist>
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Appointment</h4>
				</div>
				<div class="modal-body">
				<span id="errortime" style="color:red;"></span>
					<form class="form-horizontal"
						action="patientDoctor.appointment?operation=insert" name="form"
						style="margin-left:; padding-right: %; padding: %;"
						method="post" onSubmit="return validateInsertTime();">
						<table class="table" style="margin-top: 5%;width:100%;">
							<tr>
								<td class="field-label col-xs-3 active"><label>Doctor
										Name</label></td>
								<td class="col-md-9">${doctor.firstName} &nbsp;
									${doctor.lastName} <input class="hidden" name="doctorid"
									value="${doctor.id}">
								</td>
							</tr>
							<tr>
								<td class="field-label col-xs-3 col-md-3 active"><label>
										Title </label></td>
								<td class="col-xs-9 col-md-9"><input id="exampleInputFile" class="form-control input-sm"
									type="text" name="title" required/></td>
							</tr>
							<!-- <tr>
								<td class="field-label col-xs-3 active"><label>
										Description </label></td>
								<td class="col-md-9"><textarea class="form-control"
										rows="4" style="height:150px;" name=description  required></textarea></td>
							</tr> -->
							<tr>
								<td class="field-label col-xs-3 active"><label>Symptoms</label>
								</td>
								<td class="col-md-9">
									<div id=s1 style="margin-top: 1%;">
										<input name=s1 id=sym1 list=symptoms><input type=button
											class="pull-right" value=x onclick="removeSymptom(1);">
									</div>
									<div id=s2 style="margin-top: 1%;">
										<input name=s2  id=sym2 list=symptoms><input type=button
											class="pull-right" value=x onclick="removeSymptom(2);">
									</div>
									<div id=s3 style="margin-top: 1%;">
										<input name=s3  id=sym3 list=symptoms><input type=button
											class="pull-right" value=x onclick="removeSymptom(3);">
									</div>
									<div id=s4 style="margin-top: 1%;">
										<input name=s4  id=sym4 list=symptoms><input type=button
											class="pull-right" value=x onclick="removeSymptom(4);">
									</div>
									<div id=s5 style="margin-top: 1%;">
										<input name=s5  id=sym5 list=symptoms><input type=button
											class="pull-right" value=x onclick="removeSymptom(5);">
									</div> <input type=button value=+ id=symptom>
								</td>
							</tr>
							<tr>
								<td class="field-label col-xs-3 active"><label>
										Date</label></td>
								<td class="col-md-9"><span id="day"></span>
										</td>
							</tr>
							
							<tr>
								<td class="field-label col-xs-3 active"><label>
										Start Time </label></td>
								<td class="col-md-9"><select class="form-control" name=start id="start">
										<c:forEach var="i" begin="0" end="23">
   										<c:if test="${ i < 10}"><c:set var="i" value="0${i}" /></c:if>
   										<c:set var="j" value="0" />
   										 <c:forEach var="k" begin="0" end="11">
   										 <c:if test="${ j < 10}"><c:set var="j" value="0${j}" /></c:if>
   										<option value="${i}:${j}">${i}:${j}</option>
   										<c:set var="j" value="${j+5}" />
   										</c:forEach>
   										
   										<c:set var="i" value="${i+1}" />
										</c:forEach>
										
										</select></td>
							</tr>
							<tr>
								<td class="field-label col-xs-3 active"><label>
										End Time </label></td>
								<td class="col-md-9"><select class="form-control"
										 name=end id="last">
										 <c:forEach var="i" begin="0" end="23">
   										<c:if test="${ i < 10}"><c:set var="i" value="0${i}" /></c:if>
   										<c:set var="j" value="0" />
   										 <c:forEach var="k" begin="0" end="11">
   										 <c:if test="${ j < 10}"><c:set var="j" value="0${j}" /></c:if>
   										<option value="${i}:${j}">${i}:${j}</option>
   										<c:set var="j" value="${j+5}" />
   										</c:forEach>
   										
   										<c:set var="i" value="${i+1}" />
										</c:forEach>
										</select></td>
							</tr>
						</table>
						<div id=no></div>
						<div style="text-align:right;">
							<button type="button" class="btn btn-default btn btn-default btn-info btn-sm" data-dismiss="modal">Close</button> <input class="btn btn-info btn-sm" type="submit"
								value="Save">
</div>
					</form>
				</div>
			</div>

		</div>
	</div>


	<div class="modal fade" id="show" role="dialog" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h5 class="modal-title">Appointment</h5>
				</div>
				<div class="modal-body">
					<div id="showbody"></div>
					<div id=update>
					<span id="uerrortime" style="color:red;"></span>
						<form class="form-horizontal"
							action="patientDoctor.appointment?operation=update" name="form"
							style="margin-left:; padding-right: %; padding: %;"
							method="post" onSubmit="return validateUpdateTime();">
							<table class="table" style="margin-top: 5%;">
								<tr>
									<td class="field-label col-xs-3 active"><label>Doctor
											Name</label></td>
									<td class="col-md-9">${doctor.firstName} &nbsp;
										${doctor.lastName} <input class="hidden" name="doctorid"
										value="${doctor.id}">
									</td>
								</tr>
								<tr>
									<td class="field-label col-xs-3 active"><label>
											Title </label></td>
									<td class="col-md-9"><input id="exampleInputFile" class="form-control input-sm"
										type="text" name="title" required/></td>
								</tr>
								<!-- <tr>
								<td class="field-label col-xs-3 active"><label>
										Description </label></td>
								<td class="col-md-9"><textarea class="form-control"
										rows="4" style="height:150px;" name=description required></textarea></td>
							</tr> -->
								<tr>
									<td class="field-label col-xs-3 active"><label>Symptoms</label>
									</td>
									<td class="col-md-9">
										<div id=ss1 style="margin-top: 1%;">
											<input name=s1  id=ssym1 list=symptoms><input type=button
												class="pull-right" value=x onclick="removesSymptom(1);">
										</div>
										<div id=ss2 style="margin-top: 1%;">
											<input name=s2  id=ssym2 list=symptoms><input type=button
												class="pull-right" value=x onclick="removesSymptom(2);">
										</div>
										<div id=ss3 style="margin-top: 1%;">
											<input name=s3  id=ssym3 list=symptoms><input type=button
												class="pull-right" value=x onclick="removesSymptom(3);">
										</div>
										<div id=ss4 style="margin-top: 1%;">
											<input name=s4  id=ssym4 list=symptoms><input type=button
												class="pull-right" value=x onclick="removesSymptom(4);">
										</div>
										<div id=ss5 style="margin-top: 1%;">
											<input name=s5  id=ssym5 list=symptoms><input type=button
												class="pull-right" value=x onclick="removesSymptom(5);">
										</div> <input type=button value=+ id=ssymptom>
									</td>
								</tr>
								<tr>
								<td class="field-label col-xs-3 active"><label>
										Date</label></td>
								<td class="col-md-9"><span id="day1"></span>
										</td>
								</tr>
								<tr id="st">
								<td class="field-label col-xs-3 active"><label>
										Start Time </label></td>
								<td class="col-md-9"><select class="form-control" name=start id="start1">
										<c:forEach var="i" begin="0" end="23">
   										<c:if test="${ i < 10}"><c:set var="i" value="0${i}" /></c:if>
   										<c:set var="j" value="0" />
   										 <c:forEach var="k" begin="0" end="11">
   										 <c:if test="${ j < 10}"><c:set var="j" value="0${j}" /></c:if>
   										<option value="${i}:${j}">${i}:${j}</option>
   										<c:set var="j" value="${j+5}" />
   										</c:forEach>
   										
   										<c:set var="i" value="${i+1}" />
										</c:forEach>
										
										</select></td>
							</tr>
							<tr id="et">
								<td class="field-label col-xs-3 active"><label>
										End Time </label></td>
								<td class="col-md-9"><select class="form-control"
										 name=end id="last1">
										 <c:forEach var="i" begin="0" end="23">
   										<c:if test="${ i < 10}"><c:set var="i" value="0${i}" /></c:if>
   										<c:set var="j" value="0" />
   										 <c:forEach var="k" begin="0" end="11">
   										 <c:if test="${ j < 10}"><c:set var="j" value="0${j}" /></c:if>
   										<option value="${i}:${j}">${i}:${j}</option>
   										<c:set var="j" value="${j+5}" />
   										</c:forEach>
   										
   										<c:set var="i" value="${i+1}" />
										</c:forEach>
										</select></td>
							</tr>
								
							</table>
							<div id=addEventId></div>
							<div style="text-align:right;">
							<button type="button" class="btn btn-default btn btn-default btn-info btn-sm" data-dismiss="modal">Close</button> <input class="btn btn-info btn-sm" type="submit"
								value="Update">
</div>
						</form>
					</div>
				</div>
			</div>

		</div>
	</div>

</body>
</html>