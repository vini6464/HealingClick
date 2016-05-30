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
<script>
var symptomList=[];
$( document ).ready(function() {
	 var insert = "${insert}";
		if(insert == 1)
			{
				window.history.pushState("", "", 'appointment.patient');
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

function specialSwitchDetails(){
	$("#specialdetails").hide();
	$("#s2").hide();
	$("#s3").hide();
	$("#s4").hide();
	$("#s5").hide();
	$("#specialupdate").show();
	
}

function SwitchDetails(){
	$("#details").hide();
	$("#update").show();
	
}

function deleteSpecialEvent(eventId,id){
	
	var r=confirm("Are You Sure You Want to delete this Appointment ? ");
	if (r == true) {
	  window.location="deletePatientDoctor.appointment?eventId="+eventId+"&id="+id;
	}
}

function deleteEvent(id){
	
	var r=confirm("Are You Sure You Want to delete this Appointment ? ");
	if (r == true) {
	  window.location="delete.appointment?eventId="+id;
	}
}


function validateITime() {
	var start = document.getElementById("start").value;
	var end = document.getElementById("last").value;
	
	start = start.split(":");
	end = end.split(":");
	
	var h1 = start[0];
	var m1 = start[1];
	
	var h2 = end[0];
	var m2 = end[1];
	debugger
	if ((h2 - h1) == 0 ) {
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
		if((h2 - h1) < 0 )
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

function validateUTime() {
	var start = document.getElementById("start1").value;
	var end = document.getElementById("last1").value;
	
	start = start.split(":");
	end = end.split(":");
	
	var h1 = start[0];
	var m1 = start[1];
	
	var h2 = end[0];
	var m2 = end[1];
	
	if ((h2 - h1) == 0 ) {
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
		if((h2 - h1) < 0 )
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



	function validateInsertTime() {

		var doctorid = document.getElementById("doctorid").value;

		if (doctorid == 0) {
			var start = document.getElementById("start").value;
			var end = document.getElementById("last").value;
			
			start = start.split(":");
			end = end.split(":");
			
			var h1 = start[0];
			var m1 = start[1];
			
			var h2 = end[0];
			var m2 = end[1];
			
			if ((h2 - h1) == 0 ) {
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
				if((h2 - h1) < 0 )
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
		} else {

			var start = document.getElementById("start").value;
			var end = document.getElementById("last").value;

			start = start.split(":");
			end = end.split(":");

			var h1 = start[0];
			var m1 = start[1];

			var h2 = end[0];
			var m2 = end[1];

			if ((h2 - h1) == 0) {

				if ((m2 - m1) <= 30) {

					if ((m2 - m1) < 0) {
						$("#errortime").show();
						var span = document.getElementById("errortime");
						var txt = document
								.createTextNode("Please make sure that start time is not more than end time");
						span.innerText = txt.textContent;

						return false;
					} else {
						return true;
					}

				} else {

					$("#errortime").show();
					var span = document.getElementById("errortime");
					var txt = document
							.createTextNode("Sorry You Cant book for appointment more than 30 minutes");
					span.innerText = txt.textContent;

					return false;
				}
			} else if ((h2 - h1) == 1) {
				m2 = +m2 + +60;
				if ((m2 - m1) <= 30) {
					return true;
				} else {
					$("#errortime").show();
					var span = document.getElementById("errortime");
					var txt = document
							.createTextNode("Sorry You Cant book for appointment more than 30 minutes");
					span.innerText = txt.textContent;

					return false;
				}

			}

			if ((h2 - h1) < 0) {
				$("#errortime").show();
				var span = document.getElementById("errortime");
				var txt = document
						.createTextNode("Please make sure that start time is not more than end time");
				span.innerText = txt.textContent;

				return false;
			}

			if ((h2 - h1) > 1) {
				$("#errortime").show();
				var span = document.getElementById("errortime");
				var txt = document
						.createTextNode("Sorry You Cant book for appointment more than 30 minutes");
				span.innerText = txt.textContent;

				return false;
			}

		}

	}
</script>
<style type="text/css">


.ui-autocomplete { position: absolute; cursor: default;z-index:99999 !important; }
</style>
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
        
            $.ajax({
				method: "GET",
				url : 'getEvent.appointment',
            	datatype:'json',
            	data: { 
            		eventId : event.id
				},
				success: function(result){
            
					var res = jQuery.parseJSON(result);
					
					if(res.doctorId == 0){
						
						var st = new Date(event.start).toJSON();
						var lt = new Date(event.end).toJSON();
						 var res1 = st.split("T");
						 var res2 = lt.split("T");
						 var day = res1[0];
						 var dayRes = day.split("-");
						 var st1 = res1[1].split(":");
						 st1 = st1[0]+":"+st1[1];
						
						 var lt1 = res2[1].split(":");
						 lt1 = lt1[0]+":"+lt1[1];
						
						var htmlString='<table id="details">';
						htmlString = htmlString+'<tr><td class="field-label col-xs-3 active"><label>Title</label></td><td class="col-md-9"> '+res.title+'</td></tr>';
						/* htmlString = htmlString+'<tr><td class="field-label col-xs-3 active"><label>Description</label></td><td class="col-md-9"> '+res.description+'</td></tr>'; */
						htmlString = htmlString+'<tr><td class="field-label col-xs-3 active"><label>Date</label></td><td class="col-md-9"> '+dayRes[2]+"/"+dayRes[1]+"/"+dayRes[0]+'</td></tr>';
						htmlString = htmlString+'<tr><td class="field-label col-xs-3 active"><label>Start Time</label></td><td class="col-md-9"> '+st1+'</td></tr>';
						htmlString = htmlString+'<tr><td class="field-label col-xs-3 active"><label>End Time</label></td><td class="col-md-9"> '+lt1+'</td></tr>';
						
						htmlString = htmlString+'<tr><td class="field-label col-xs-3"><input class="btn btn-info btn-block btn-sm" type="button" value="Update" onclick="SwitchDetails()"></td><td class="col-md-9"> <input class="btn btn-info btn-block btn-sm" type="button" value="Delete" onclick = deleteEvent('+event.id+');></td></tr></table>';
						
						$("#addEventId").html('<input type=text class=hidden name=day value='+day+'> <input class=hidden type=text name=eventId value='+event.id+'>');
						$("#showbody").html(htmlString);
						 $('#start1').val(st1); 
						 $('#last1').val(lt1); 
						 $('#day1').text(dayRes[2]+"/"+dayRes[1]+"/"+dayRes[0]); 
						$("#update").hide();
						$("#show").modal();
					}
					else
						{
						var st = new Date(event.start).toJSON();
						var lt = new Date(event.end).toJSON();
						 var res1 = st.split("T");
						 var res2 = lt.split("T");
						 var day = res1[0];
						 var dayRes = day.split("-");
						 var st1 = res1[1].split(":");
						 st1 = st1[0]+":"+st1[1];
						
						 var lt1 = res2[1].split(":");
						 lt1 = lt1[0]+":"+lt1[1];
						var status = "Accepted";
						if(res.status==0)
						{
							status = "Pending";
						}
						var htmlString='<table id="specialdetails"><tr><td class="field-label col-xs-3 active"><label>Doctor Name</label></td><td class="col-md-9"> '+res.doctorName+'</td></tr>';
						htmlString = htmlString+'<tr><td class="field-label col-xs-3 active"><label>Title</label></td><td class="col-md-9"> '+res.title+'</td></tr>';
						/* htmlString = htmlString+'<tr><td class="field-label col-xs-3 active"><label>Description</label></td><td class="col-md-9"> '+res.description+'</td></tr>'; */
						htmlString = htmlString+'<tr><td class="field-label col-xs-3 active"><label>Symptoms</label></td><td class="col-md-9"> '+res.symptom+'</td></tr>';
						htmlString = htmlString+'<tr><td class="field-label col-xs-3 active"><label>Date</label></td><td class="col-md-9"> '+dayRes[2]+"/"+dayRes[1]+"/"+dayRes[0]+'</td></tr>';
						htmlString = htmlString+'<tr><td class="field-label col-xs-3 active"><label>Start Time</label></td><td class="col-md-9"> '+st1+'</td></tr>';
						htmlString = htmlString+'<tr><td class="field-label col-xs-3 active"><label>End Time</label></td><td class="col-md-9"> '+lt1+'</td></tr>';
						htmlString = htmlString+'<tr><td class="field-label col-xs-3 active"><label>Status</label></td><td class="col-md-9"> '+status+'</td></tr>';
						
						if(res.status==0)
						{
							htmlString = htmlString+'<tr><td class="col-md-9"> <input class="btn btn-info btn-block btn-sm" type="button" value="Delete" onclick = deleteSpecialEvent('+event.id+','+res.doctorId+');></td></tr></table>';
							
						}
						
						if(res.status==1)
						{
							htmlString = htmlString+'<tr><td class="col-md-9"></td></tr></table>';
						}
						var st = new Date(event.start).toJSON();
						var lt = new Date(event.end).toJSON();
						 
						$("#doctorname").html(res.doctorName);
						$("#specialaddEventId").html('<input class=hidden type=text name=start value='+st+'> <br> <input class=hidden type=text name=end value='+lt+'> <input class=hidden type=text name=eventId value='+event.id+'>');
						$("#specialshowbody").html(htmlString);
						 $('#start1').val(st1); 
						 $('#last1').val(lt1); 
						 $('#day1').text(dayRes[2]+"/"+dayRes[1]+"/"+dayRes[0]); 
						$("#specialupdate").hide();
						$("#specialshow").modal();
						}
						
					
				},
				statusCode : {
					500: function(result){
						alert(result);
					}
				}
			});

	    },
		selectable: true,
		selectHelper: true,
		select: function(start, end) {
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
		events: "getEvents.appointment"
	});
	
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
	<datalist id="symptoms">
	</datalist>
	<div class="modal fade" id="insert" role="dialog" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Appointment</h4>
				</div>
				<div class="modal-body">
				<span id="errortime" style="color:red;"></span>
					<form class="form-horizontal" action="save.appointment" name="form"
						style="margin-left:; padding-right: %; padding: %;"
						method="post" onSubmit="return validateInsertTime();">
						<table class="table" style="margin-top: 5%;">
							<tr>
									<td class="field-label col-xs-3 active"><label>
											Doctor Name</label></td>
									<td class="col-md-9"><select class="form-control" name="doctorid" id="doctorid" >
	  							<option value="0">---Select---</option>
	  							<c:forEach var="doctors" items="${doctors}">
									<option value="${doctors.id}">${doctors.firstName} &nbsp; ${doctors.lastName}</option>
								</c:forEach>
	  							
	  							
	  						</select></td>
								</tr>
							<tr>
								<td class="field-label col-xs-3 active"><label>
										Title </label></td>
								<td class="col-md-9"><input id="exampleInputFile"  class="form-control input-sm"
									type="text" name="title" required/></td>
							</tr>
							<!-- <tr>
								<td class="field-label col-xs-3 active"><label>
										Description </label></td>
								<td class="col-md-9"><textarea class="form-control"
										rows="4" style="height: 200px;" name=description  required></textarea></td>
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
											<input name=s2 id=sym2 list=symptoms><input type=button
												class="pull-right" value=x onclick="removeSymptom(2);">
										</div>
										<div id=s3 style="margin-top: 1%;">
											<input name=s3 id=sym3 list=symptoms><input type=button
												class="pull-right" value=x onclick="removeSymptom(3);">
										</div>
										<div id=s4 style="margin-top: 1%;">
											<input name=s4 id=sym4 list=symptoms><input type=button
												class="pull-right" value=x onclick="removeSymptom(4);">
										</div>
										<div id=s5 style="margin-top: 1%;">
											<input name=s5 id=sym5 list=symptoms><input type=button
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


	<div class="modal fade" id="specialshow" role="dialog" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Appointment</h4>
				</div>
				<div class="modal-body">
				<span id="errortime" style="color:red;"></span>
					<div id="specialshowbody"></div>
					<div id=specialupdate>
						<form class="form-horizontal"
							action="patientDoctorUpdate.appointment" name="form"
							style="margin-left:; padding-right: %; padding: %;"
							method="post">
							<table class="table" style="margin-top: 5%;">
								<tr>
									<td class="field-label col-xs-3 active"><label>Doctor
											Name</label></td>
									<td class="col-md-9" id="doctorname"></td>
								</tr>
								<tr>
									<td class="field-label col-xs-3 active"><label>
											Title </label></td>
									<td class="col-md-9"><input id="exampleInputFile"  class="form-control input-sm"
										type="text" name="title" required/></td>
								</tr>
								<!-- <tr>
									<td class="field-label col-xs-3 active"><label>
											Description </label></td>
									<td class="col-md-9"><textarea class="form-control"
											rows="4" style="height: 200px;" name=description  required></textarea></td>
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
											<input name=s2 id=sym2 list=symptoms><input type=button
												class="pull-right" value=x onclick="removeSymptom(2);">
										</div>
										<div id=s3 style="margin-top: 1%;">
											<input name=s3 id=sym3 list=symptoms><input type=button
												class="pull-right" value=x onclick="removeSymptom(3);">
										</div>
										<div id=s4 style="margin-top: 1%;">
											<input name=s4 id=sym4 list=symptoms><input type=button
												class="pull-right" value=x onclick="removeSymptom(4);">
										</div>
										<div id=s5 style="margin-top: 1%;">
											<input name=s5 id=sym5 list=symptoms><input type=button
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
							<div id=specialaddEventId></div>
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


	<div class="modal fade" id="show" role="dialog" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Appointment</h4>
				</div>
				<div class="modal-body">
				<span id="uerrortime" style="color:red;"></span>
					<div id="showbody"></div>
					<div id=update>
						<form class="form-horizontal" action="update.appointment"
							name="form"
							style="margin-left: ; padding-right: %; padding: %;"
							method="post" onSubmit="return validateUTime();">
							<table class="table" style="margin-top: 5%;">

								<tr>
									<td class="field-label col-xs-3 active"><label>
											Title </label></td>
									<td class="col-md-9"><input id="exampleInputFile"  class="form-control input-sm"
										type="text" name="title" required/></td>
								</tr>
								<!-- <tr>
									<td class="field-label col-xs-3 active"><label>
											Description </label></td>
									<td class="col-md-9"><textarea class="form-control"
											rows="4" style="height: 200px;" name=description  required></textarea></td>
								</tr> -->
								<tr>
								<td class="field-label col-xs-3 active"><label>
										Date</label></td>
								<td class="col-md-9"><span id="day1"></span>
										</td>
								</tr>
								<tr>
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
							<tr>
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