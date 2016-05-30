<%@ include file="patientHeader.jsp" %>
<script src="js/jquery-ui.min.js"></script>
<link href="css/jquery-ui.min.css" rel="stylesheet">
<script>
function preventBackButton(){window.history.forward();}
setTimeout("preventBackButton()", 0);
window.onunload=function(){null};
</script>

<script>
var medList=[];
$( document ).ready(function() {
	$("#m2").hide();
	$("#m3").hide();
	$("#m4").hide();
	$("#m5").hide();
	$("#m6").hide();
	$("#m7").hide();
	$("#m8").hide();
	$("#m9").hide();
	$("#m10").hide();
});
</script>

<script>
var supplierId = "${pharmacy.id}";	

function getCost(id)
{
	var xmlhttp;
	var mId = document.forms["order"]["m"+id].value;
	var qty = document.forms["order"]["q"+id].value;

	if(mId == 0 || qty == "" || qty == null)
		{
		document.getElementById("c"+id).innerHTML="";
		}
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange=function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			document.getElementById("c"+id).innerHTML=xmlhttp.responseText;
		}
	};
	xmlhttp.open("GET","getCost.notification?mId="+mId+"&qty="+qty+"&supplierId="+supplierId,true);
	xmlhttp.send(null);
	
	
	
}

$(function() {
	$("#add").click(function() {

		if ($("#m1").is(":visible")) {
		
		if ($("#m2").is(":visible")) {

			if ($("#m3").is(":visible")) {

				if ($("#m4").is(":visible")) {

					if ($("#m5").is(":visible")) {
						if ($("#m6").is(":visible")) {
							if ($("#m7").is(":visible")) {
								if ($("#m8").is(":visible")) {
									if ($("#m9").is(":visible")) {
										$("#m10").show();
										$("#add").hide();
									}
									if ($("#m9").is(":hidden")) {
										$("#m9").show();
									}
								}
								if ($("#m8").is(":hidden")) {
									$("#m8").show();
								}
							}
							if ($("#m7").is(":hidden")) {
								$("#m7").show();
							}
						}
						if ($("#m6").is(":hidden")) {
							$("#m6").show();
						}
					}
					if ($("#m5").is(":hidden")) {
						$("#m5").show();
					}
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


function removeMedicine(id){
	$("#m"+id).html('<td class="col-md-6"><input name=m'+id+' id=med'+id+' list=medicines autocomplete=off ></td>'+
			'<td class=col-md-3><input type="number" name="q'+id+'" '+
			'style="width: 70px;" onblur=getCost('+id+') required></td>'+
		'<td class="col-md-3"><label id=c'+id+'></label><input type=button class="pull-right" value=x onclick="removeMedicine('+id+');"></td>');
	$("#med"+id).autocomplete({source: medList});
	$("#m"+id).hide();
	if ($("#m1").is(":visible")) {
		if ($("#m2").is(":visible")) {
			if ($("#m3").is(":visible")) {
				if ($("#m4").is(":visible")) {
					if ($("#m5").is(":visible")) {
						if ($("#m6").is(":visible")) {
							if ($("#m7").is(":visible")) {
								if ($("#m8").is(":visible")) {
									if ($("#m9").is(":visible")) {
										if ($("#m10").is(":visible")) {
											$("#add").hide();
										}
										else
											$("#add").show();
									}
									else
										$("#add").show();
								}
								else
									$("#add").show();
							}
							else
								$("#add").show();
						}
						else
							$("#add").show();
					}
					else
						$("#add").show();
				}
				else
					$("#add").show();
			}
			else
				$("#add").show();
		}
		else
			$("#add").show();
	}
	else
		$("#add").show();		
	
}
</script>
<div class="container-fluid" style="margin-top: 2%;">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="row clearfix">
					<div class="col-md-2 column"></div>
					<div class="col-md-6 column" id="noMedicines">
						<form action="confirm.order?id=${pharmacy.id}" name="order"
							method="post" enctype="multipart/form-data" name="form1">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">${pharmacy.pharmacyName}</h3>
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-xs-5 col-sm-5 col-md-5 column"
											style="padding-top: 3px; text-align:">

											<h5>Type of Order:</h5>
										</div>
										<div class="col-xs-4 col-sm-4 column"
											style="padding-left: 0px; text-align:">
											<label class="radio"> <input type="radio"
												name="oType" data-toggle="radio" id="optionsRadios1"
												value="1" checked> <i></i><small
												style="color:; letter-spacing: 1px;">Normal</small>
											</label>

										</div>
										<div class="col-xs-3 col-sm-3 column"
											style="padding-left: 0px; text-align:">
											<label class="radio"> <input type="radio"
												name="oType" data-toggle="radio" id="optionsRadios1"
												value="2"> <i></i><small
												style="color:; letter-spacing: 1px;">Urgent</small>
											</label>

										</div>
									</div>

  <div class="row">
										<div class="col-xs-5 col-sm-5 col-md-5 column"
											style="padding-top: 3px; text-align:">

											<h5>Prescription Image:</h5>
										</div>
										<div class="col-xs-4 col-sm-4 column"
											style="padding-left: 0px; text-align:">
												<input type="file" name="file" style="margin-left:-20px;margin-top:7px;" accept="image/*"/>

										</div>
									</div>

								<div class="row">
										<div class="col-xs-5 col-sm-5 col-md-5 column"
											style="padding-top: 3px;">

											<h5>Delivery Charge:</h5>
										</div>
										<div class="col-xs-4 col-sm-4 column" style="padding-top: 3px;">
											
											
											${pharmacy.deliveryCharge}Rs.

										</div>
									</div>
									
									<div class="row">
										<div class="col-xs-5 col-sm-5 col-md-5 column"
											style="padding-top: 3px;">

											<h5>Discount:</h5>
										</div>
										<div class="col-xs-4 col-sm-4 column" style="padding-top: 3px;">
											
											
											${pharmacy.discount}%

										</div>
									</div>

									<datalist id="medicines">
                              </datalist>                    
									  <table class="table" style="margin-top: 5%;border:2px solid white;">
										<tr>
											<td class="field-label col-xs-6 active"><label>Medicine
													Name </label></td>
											<td class="field-label col-xs-3 active"><label>Quantity</label>
											</td>
											<td class="field-label col-xs-3 active"><label>Cost</label>
											</td>
										</tr>

										<tr id="m1">
											<td class='col-md-6'><input name=m1 list=medicines id=med1 autocomplete=off></td>
											<td class='col-md-3'><input type="number" name=q1
												style="width: 70px;" onblur=getCost(1) required></td>
											<td class="col-md-3"><label id=c1></label><input type=button class="pull-right" value=x onclick="removeMedicine(1);"></td>
										</tr>
										<tr id="m2">
											<td class='col-md-6'><input name=m2 list=medicines id=med2 autocomplete=off></td>
											<td class='col-md-3'><input type="number" name=q2
												style="width: 70px;" onblur=getCost(2)></td>
											<td class="col-md-3"><label id=c2></label><input type=button class="pull-right" value=x onclick="removeMedicine(2);"></td>
										</tr>
										<tr id="m3">
											<td class='col-md-6'><input name=m3 list=medicines id=med3 autocomplete=off></td>
											<td class='col-md-3'><input type="number" name=q3
												style="width: 70px;" onblur=getCost(3)></td>
											<td class="col-md-3"><label id=c3></label><input type=button class="pull-right" value=x onclick="removeMedicine(3);"></td>
										</tr>
										<tr id="m4">
											<td class='col-md-6'><input name=m4 list=medicines id=med4 autocomplete=off></td>
											<td class='col-md-3'><input type="number" name=q4
												style="width: 70px;" onblur=getCost(4)></td>
											<td class="col-md-3"><label id=c4></label><input type=button class="pull-right" value=x onclick="removeMedicine(4);"></td>
										</tr>
										<tr id="m5">
											<td class='col-md-6'><input name=m5 list=medicines id=med5 autocomplete=off></td>
											<td class='col-md-3'><input type="number" name=q5
												style="width: 70px;" onblur=getCost(5)></td>
											<td class="col-md-3"><label id=c5></label><input type=button class="pull-right" value=x onclick="removeMedicine(5);"></td>
										</tr>
										<tr id="m6">
											<td class='col-md-6'><input name=m6 list=medicines id=med6 autocomplete=off></td>
											<td class='col-md-3'><input type="number" name=q6
												style="width: 70px;" onblur=getCost(6)></td>
											<td class="col-md-3"><label id=c6></label><input type=button class="pull-right" value=x onclick="removeMedicine(6);"></td>
										</tr>
										<tr id="m7">
											<td class='col-md-6'><input name=m7 list=medicines id=med7 autocomplete=off></td>
											<td class='col-md-3'><input type="number" name=q7
												style="width: 70px;" onblur=getCost(7)></td>
											<td class="col-md-3"><label id=c7></label><input type=button class="pull-right" value=x onclick="removeMedicine(7);"></td>
										</tr>
										<tr id="m8">
											<td class='col-md-6'><input name=m8 list=medicines id=med8 autocomplete=off></td>
											<td class='col-md-3'><input type="number" name=q8
												style="width: 70px;" onblur=getCost(8)></td>
											<td class="col-md-3"><label id=c8></label><input type=button class="pull-right" value=x onclick="removeMedicine(8);"></td>
										</tr>
										<tr id="m9">
											<td class='col-md-6'><input name=m9 list=medicines id=med9 autocomplete=off></td>
											<td class='col-md-3'><input type="number" name=q9
												style="width: 70px;" onblur=getCost(9)></td>
											<td class="col-md-3"><label id=c9></label><input type=button class="pull-right" value=x onclick="removeMedicine(9);"></td>
										</tr>
										<tr id="m10">
											<td class='col-md-6'><input name=m10 list=medicines id=med10 autocomplete=off></td>
											<td class='col-md-3'><input type="number" name=q10
												style="width: 70px;" onblur=getCost(10)></td>
											<td class="col-md-3"><label id=c10></label><input type=button class="pull-right" value=x onclick="removeMedicine(10);"></td>
										</tr>
									</table>
										<input type=button value=+ id="add">
										</div>

								<div class="panel-body">
									<div class="row">
										<div class=" active col-xs-5 col-sm-5 column "
											style="padding-top: 3px; text-align:">

											<h5>Payment Type:</h5>
										</div>
										<div class="col-xs-4 col-sm-4 column"
											style="padding-left: 0px; text-align:">
											<label class="radio"> <input type="radio"
												name="pType" value="1" data-toggle="radio"
												id="optionsRadios1" checked> <i></i><small
												style="color:; letter-spacing: 1px;">Cash on Delivery</small>
											</label>

										</div>
										<div class="col-xs-3 col-sm-3 column"
											style="padding-left: 0px; text-align:">
											<label class="radio"> <input type="radio"
												name="pType" value="2" data-toggle="radio"
												id="optionsRadios1"> <i></i><small
												style="color:; letter-spacing: 1px;">Card</small>
											</label>

										</div>
									</div>


								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-xs-5 col-sm-5 column"
											style="padding-top: 3px; text-align:">

											<h5>Shipping Address:</h5>
										</div>
										<div class="col-xs-4 col-sm-4 column"
											style="padding-left: 0px; text-align:">
											<label class="radio"> <input type="radio"
												name="address" value="1" data-toggle="radio"
												id="optionsRadios1" checked> <i></i><small
												style="color:; letter-spacing: 1px;">Same As Profile Address and Contact Details</small>
											</label>

										</div>
										<div class="col-xs-3 col-sm-3 column"
											style="padding-left: 0px; text-align:">
											<label class="radio"> <input type="radio"
												name="address" value="2" data-toggle="radio"
												id="optionsRadios1"> <i></i><small
												style="color:; letter-spacing: 1px;">Different Address</small>
											</label>

										</div>
									</div>


								</div>
								<div class="panel-footer">
									<div class="row">
										<div class="col-md-6 column"></div>
										<div class="col-md-3 column pull-right">
											<input class="btn btn-info btn-block  btn-xs"
												type="submit" style="" value="Submit">
										</div>
										
									</div>
								</div>
							</div>
						</form>
					</div>
 <%@ include file="patientAside.jsp" %>
					
					
					
					</div>






					
					<script type="text/javascript">
			
$(document).ready(function() {
			
				$.ajax({
					method : "GET",
					url : 'getPharmacyMedicines.notification',
					datatype : 'json',
					data: { 
						supplierId : supplierId
						
					},
					success : function(result) {
						if(result!="[]"){var html = "";
						
						var d = $.parseJSON(result);
						for ( var i = 0; i < d.length; i++) {
							/* html = html + "<option value='"+d[i].name+"'>"+d[i].name+"</option>"; */	
							medList.push('' + d[i].name); 
						}
						/* $("#medicines").html(html); */
						$("#med1").autocomplete({source: medList});
						$("#med2").autocomplete({source: medList});
						$("#med3").autocomplete({source: medList});
						$("#med4").autocomplete({source: medList});
						$("#med6").autocomplete({source: medList});
						$("#med5").autocomplete({source: medList});
						$("#med7").autocomplete({source: medList});
						$("#med8").autocomplete({source: medList});
						$("#med9").autocomplete({source: medList});
						$("#med10").autocomplete({source: medList});
						}else{
							$("#noMedicines").html("Sorry, This Pharmacy doesnot have any medicines to sell").addClass("no_message");
						}
					},
					statusCode : {
						500 : function(result) {
							
						}
					}
				});

			});
			
			</script>
			
			
</body>
</html>










