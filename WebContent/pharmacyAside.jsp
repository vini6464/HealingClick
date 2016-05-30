<div class="aside col-xs-12 col-sm-5 col-md-4 column"
				style="min-width: 300px;max-width: 550px; padding-left: 2%;">
				
				<form name="form">

					<div class="row-clearfix">
					
					
					<div class="input-group" style="">
      
      
									<input style="border:none;" type="text" placeholder="Search" name="text" id="text" class="form-control" style="" onkeyup="search()"
								onkeydown="search()">
						  <span class="glyphicon glyphicon-search	input-group-addon" style="background-color:white;border:1px solid #f4f4f4;color:#00b9bc;"></span>
								
							</div>
							
							
								
								
									
								
							
						
						<div class="row clearfix">

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
				<br>


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
									<a href="pharmacyViewPharmacy.profile?id=${pharmacy.id}">${pharmacy.pharmacyName}<br/>${pharmacy.address1},${pharmacy.address2},${pharmacy.landMark}
									</a><a href="order.pharmacy?id=${pharmacy.id}"
										class="btn btn-xs pull-right " type="button">Make
										Order</a>
								</div>
							</c:if>
						</c:forEach>
					</c:if>
					<c:if test="${empty pharmacies}">

						<div class="list-group-item">
						No Pharmacies In Your
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