<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="doctorHeader.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="css/bootstrap.min.css" rel="stylesheet">

<link href='calendar/css/fullcalendar.css' rel='stylesheet' />
<link href='calendar/css/fullcalendar.print.css' rel='stylesheet'
	media='print' />
<script src='calendar/lib/moment.min.js'></script>
<link rel="stylesheet"
	href="css/bootstrap.min.css">

<script
	src="js/bootstrap.min.js"></script>
<script src='calendar/js/fullcalendar.js'></script>

<script>
function preventBackButton(){window.history.forward();}
setTimeout("preventBackButton()", 0);
window.onunload=function(){null};
</script>
<script>

$( document ).ready(function() {
	 var insert = "${insert}";
		if(insert == 1)
			{
				window.history.pushState("", "", 'appointment.doctor');
			}
	
});

function SwitchDetails(){
	$("#details").hide();
	$("#update").show();
	
}

function deleteEvent(id){
	
	var r=confirm("Are You Sure You Want to delete this Appointment ? ");
	if (r == true) {
	  window.location="delete.appointment?eventId="+id;
	}
}

function deleteSpecialEvent(eventId,id){
	
	var r=confirm("Are You Sure You Want to delete this Appointment ? ");
	if (r == true) {
	  window.location="deletePatientDoctor.appointment?eventId="+eventId+"&id="+id;
	}
}

function acceptAppointment(id){
	$.ajax({
		method: "GET",
		url : 'accept.appointment',
    	datatype:'text',
    	data: { 
    		eventId : id
		},
		success: function(result){
			$("#accept").hide();
		},
		statusCode : {
			500: function(result){
				alert(result);
			}
		}
	});
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
        
            $.ajax({
				method: "GET",
				url : 'getEvent.appointment',
            	datatype:'json',
            	data: { 
            		eventId : event.id
				},
				success: function(result){
            
					var res = jQuery.parseJSON(result);
					
					if(res.patientId == 0){
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
						
						
						htmlString = htmlString+'<tr><td class="field-label col-xs-3"><input class="btn btn-info btn-block btn-sm" type="button" value="Delete" onclick = deleteEvent('+event.id+');></td><td class="col-md-9"> <input class="btn btn-info btn-block btn-sm" type="button" value="Update" onclick="SwitchDetails()"></td></tr></table>';
						
						$("#addEventId").html('<input type=text class=hidden name=day value='+day+'><input class=hidden type=text name=eventId value='+event.id+'>');
						
						
						$("#showbody").html(htmlString);
						
						 
						 $("#no").html('<input type=text class=hidden name=day value='+day+' >');
						
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
						var htmlString='<table id="specialdetails"><tr><td class="field-label col-xs-3 active"><label>Patient Name</label></td><td class="col-md-9"> '+res.patientName+'</td></tr>';
						htmlString = htmlString+'<tr><td class="field-label col-xs-3 active"><label>Title</label></td><td class="col-md-9"> '+res.title+'</td></tr>';
						/* htmlString = htmlString+'<tr><td class="field-label col-xs-3 active"><label>Description</label></td><td class="col-md-9"> '+res.description+'</td></tr>'; */
						htmlString = htmlString+'<tr><td class="field-label col-xs-3 active"><label>Symptoms</label></td><td class="col-md-9"> '+res.symptom+'</td></tr>';
						htmlString = htmlString+'<tr><td class="field-label col-xs-3 active"><label>Date</label></td><td class="col-md-9"> '+dayRes[2]+"/"+dayRes[1]+"/"+dayRes[0]+'</td></tr>';
						htmlString = htmlString+'<tr><td class="field-label col-xs-3 active"><label>Start Time</label></td><td class="col-md-9"> '+st1+'</td></tr>';
						htmlString = htmlString+'<tr><td class="field-label col-xs-3 active"><label>End Time</label></td><td class="col-md-9"> '+lt1+'</td></tr>';
						htmlString = htmlString+'<tr><td class="field-label col-xs-3 active"><label>Status</label></td><td class="col-md-9"> '+status+'</td></tr>';
						
						if(res.status==0)
						{
							htmlString = htmlString+'<tr id=accept><td class="field-label col-xs-3"><input class="btn btn-info btn-block btn-sm" type="button" value="Accept" onclick="acceptAppointment('+event.id+')"></td><td class="col-md-9"> <input class="btn btn-info btn-block btn-sm" type="button" value="Delete" onclick = deleteSpecialEvent('+event.id+','+res.patientId+');></td></tr></table>';
							
						}
						
						$("#specialshowbody").html(htmlString);
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
						method="post" onSubmit="return validateITime();">
						<table class="table" style="margin-top: 5%;">

							<tr>
								<td class="field-label col-xs-3 active"><label>
										Title </label></td>
								<td class="col-md-9"><input id="exampleInputFile"
									type="text" class="form-control input-sm" name="title" /></td>
							</tr>
							<!-- <tr>
								<td class="field-label col-xs-3 active"><label>
										Description </label></td>
								<td class="col-md-9"><textarea class="form-control"
										rows="4" style="height: 150px;" name=description required></textarea></td>
							</tr> -->
							
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
					<h4 class="modal-title"> Header</h4>
				</div>
				<div class="modal-body">
					<div id="specialshowbody"></div>
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
							style="margin-left:; padding-right: %; padding: %;"
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
											rows="4" style="height: 150px;" name=description  required></textarea></td>
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