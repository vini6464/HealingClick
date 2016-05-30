<%@ include file="pharmacyHeader.jsp" %>
<script>
function preventBackButton(){window.history.forward();}
setTimeout("preventBackButton()", 0);
window.onunload=function(){null};
</script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
<link href="https://cdn.datatables.net/1.10.10/css/jquery.dataTables.min.css" rel="stylesheet">
<script src="js/jquery.dataTables.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function() {
	$('#example').DataTable();
	  /* $('#example').DataTable( {
	        "pagingType": "full_numbers",
	        "ordering": false,
	        'iDisplayLength': 2,
	        "bLengthChange": false,
	        "bFilter": true,
	    } );  */
} );


</script>
      <script>
$(document).ready(function(){
	$("#medicine").click(function() {
		$("#searchMed").modal();
	});
	
	$("#excel").click(function() {
		$("#addExcel").modal();
	});
    $('[data-toggle="tooltip"]').tooltip();   
    var insert = "${insert}";
	if(insert == 1)
		{
			window.history.pushState("", "", 'medicines.pharmacy');
		}
	
	$(function() {
		$("#medClose").click(function() {
			location.reload(true);
		});
	});
});



function searchMedicine()
{
	var xmlhttp;

	var text = document.search.text.value;

	if(text=="")
		{
		document.getElementById("s").innerHTML="";
		}
	else
		{
		$.ajax({
			method : "POST",
			url : 'searchMedicine.search',
			data: { 
				text : text
			},
			success : function(result) {
				var html = "";
				var d = $.parseJSON(result);
				for ( var i = 0; i < d.length; i++) {
					if(d[i].status == 1){
						html = html + '<div class="panel-body">'+d[i].name+' &nbsp;&nbsp;&nbsp;&nbsp;</div>';	
					}else{
						html = html + '<div class="panel-body">'+d[i].name+' &nbsp;&nbsp;&nbsp;&nbsp;<span id='+d[i].id+'><input type="button" class="btn btn-info btn-xs" onclick="addMedicine('+d[i].id+');" value="Add"/><span></div>';	
					}	
				}
				$("#s").html(html);
			},
			statusCode : {
				500 : function(result) {
					
				}
			}
		});	   
		}
}

function addMedicine(id)
{
	
		$.ajax({
			method : "POST",
			url : 'addMedicine.search',
			data: { 
				mId : id
			},
			success : function(result) {
				
				$("#"+id).html("Added");
			},
			statusCode : {
				500 : function(result) {
					
				}
			}
		});	   
		
}
function deleteMedicine(name)
{
	
		$.ajax({
			method : "POST",
			url : 'deleteMedicine.search',
			data: { 
				medicineName : name
			},
			success : function(result) {
				location.reload(true);
			},
			statusCode : {
				500 : function(result) {
					
				}
			}
		});	   
		
}
function editMedicine(name,cost)
{
	$("#mName").html(name);
	$("#cost").val(cost);
	$("#mId").val(name);
		$("#edit").modal();
	
		
}

function updateMedicine()
{
	
	var mId = document.getElementById("mId").value;
	var cost = document.getElementById("cost").value;
	$.ajax({
		method : "POST",
		url : 'updateMedicine.search',
		data: { 
			mId : mId,
			cost : cost
		},
		success : function(result) {
			location.reload(true);
		},
		statusCode : {
			500 : function(result) {
				
			}
		}
	});	   
}
</script>
	<div class="container" style="margin-top: 4%;">
		<div class="row clearfix">
			<div class="col-md-12 column">
			<div class="h_20"></div>
				<div class="row clearfix">
					<div class="col-sm-5  col-md-3 column  a_left">
					 	 
					 	<ul class="left_aside bg_w">
					 	
					 	
					<li><a href="pharmacyCostEdit.pharmacy" class="btn   btn-xs pull-left" type="button" >Edit Delivery Charge And Discount</a>&nbsp;&nbsp;&nbsp;
					</li>
					</ul>
					</div>
					<div class=" col-sm-7 col-md-9 column">

						<div class="panel panel-default" >
				<div class="panel-heading">
					<input type="button" class="btn btn-info btn-xs" value="Add Medicines" id="medicine">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;OR &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" class="btn btn-info btn-xs" value="Add Excel" id="excel">
				</div>
				
				
				
			</div>
					</div>
				</div>	
				
		<div class="row clearfix">
		<div class="col-sm-5  col-md-3 column"></div>
		<div class=" col-sm-7 col-md-9 ">			
<div class="panel panel-default edit_med_container">
							<div class="col-sm-4 column" style="color: red;"></div>
							<c:if test="${not empty pharmacyMedicines}">
							<table id="example" class="display" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th>Medicine Name (ID)</th>
                <th>Cost</th>
                <th>Options</th>
                
            </tr>
        </thead>
        <tbody>
        
								<c:forEach var="medicine" items="${pharmacyMedicines}">
								<tr>
                <td>${medicine.name}</td>
                <td>${medicine.cost}</td>
                <td><button type="button" class="btn btn-link" onclick="editMedicine('${medicine.name}',${medicine.cost});"><span class="glyphicon glyphicon-edit" ></span> Edit</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-link" onclick="deleteMedicine('${medicine.name}');"><span class="glyphicon glyphicon-remove" ></span> Delete</button></td>
               
            </tr>
								
								
								</c:forEach>
								
        </tbody>
    </table>
			</c:if>			</div>
						</div>
<div class="col-sm-2"></div>
</div>
				
<div class="modal fade" id="searchMed" role="dialog" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" id="medClose" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Add Medicines</h4>
				</div>
				<form name="search">
				<div class="input-group" style="">
      
      
									<input style="border:none;" type="text" placeholder="Search" name="text" id="text" class="form-control" style="" 
								>
						  <span class="glyphicon glyphicon-search	input-group-addon" style="background-color:white;border:1px solid #f4f4f4;color:#00b9bc;" onclick="searchMedicine();"></span>
								
							</div>
							<div class="panel panel-default" id="s"></div>
							</form>
			</div>

		</div>
	</div>
	
	<div class="modal fade" id="edit" role="dialog" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button"  class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Update Medicine Cost</h4>
				</div>
				<div class="modal-body">
					<form action="javascript:updateMedicine()" method="post" class="form-horizontal">
					<table class="table" style="margin-top: 5%;">

								<tr>
									<td class="field-label col-xs-3 active"><label>
											Medicine Name </label></td>
									<td class="col-md-9" id="mName"></td>
								</tr>
								<tr>
									<td class="field-label col-xs-3 active"><label>
											Cost </label></td>
									<td class="col-md-9"><input id="cost" class="form-control input-sm"  name="cost" type="number" data-toggle="tooltip" data-placement="top"  required/>
									<input type="text" class="hidden" id="mId"></td>
								</tr>
								
								
							</table>
							
							<div style="text-align:right;">
							<button type="button" class="btn btn-default btn btn-default btn-info btn-sm" data-dismiss="modal">Close</button> <input class="btn btn-info btn-sm" type="submit"
								value="Save">
</div>
						
					</form>
				</div>
			</div>

		</div>
	</div>
    
    	<div class="modal fade" id="addExcel" role="dialog" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button"  class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Update Medicine Cost</h4>
				</div>
				<div class="modal-body">
					<form action="saveMedicines.pharmacy" method="post" class="form-horizontal" enctype="multipart/form-data">
					<table class="table" style="margin-top: 5%;">

								<tr>
									<td class="field-label col-xs-3 active"><label>
											Medicines List </label></td>
									<td class="col-md-9" > <input class="" type="file" name="file" required></td>
								</tr>
								
								
								
							</table>
							
							<div style="text-align:right;">
							<button type="button" class="btn btn-default btn btn-default btn-info btn-sm" data-dismiss="modal">Close</button> <input class="btn btn-info btn-sm" type="submit"
								value="Save">
</div>
						
					</form>
					
					<b>NOTE:</b><br/>
					1.Old Medicines will be removed to avoid duplicate entry.<br/>
					2.In excel sheet first column should contain medicine name and second column should contain its price.
				</div>
			</div>

		</div>
	</div>
</body>
</html>










