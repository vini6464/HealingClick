<%@ include file="pharmacyHeader.jsp" %>

<script>
function preventBackButton(){window.history.forward();}
setTimeout("preventBackButton()", 0);
window.onunload=function(){null};
</script>


<script>$(function() {
	$("#ord").click(function() {
		$("#notord").hide();
	
});		
});
</script>




	<div class="container-fluid" style="margin-top:4%;">
	<div class="row clearfix" >
		<div class="col-md-12 column">
			<div class="row clearfix">
			  <div class=" col-md-2 column"> </div>
				<div class="col-xs-12 col-sm-6 col-md-6 column" style="max-width:px;">
				<form class="form-horizontal" action="orderAddress.order" >
						<div class="panel panel-default">
						<div class="panel-heading" >
							<h5 class="panel-title">
							  ${order.supplierPharmacyName}
							</h5>
						</div>
						
						<div class="panel-body">
							
							
							 <div class="form-group">
							  <label for="inputEmail3" class="col-sm-2 control-label">Address1</label>
                      <div class="col-sm-10">
                          <textarea class="form-control" rows="2" placeholder="" name="address1" required></textarea>
                        </div>

                       </div>
					    <div class="form-group">
							  <label for="inputEmail3" class="col-sm-2 control-label">Address2</label>
                      <div class="col-sm-10">
                          <textarea class="form-control" rows="2" placeholder="" name="address2" required></textarea>
                        </div>

                       </div>
					   			
				<div class="form-group">
					 <label for="inputEmail3" class="col-sm-2 control-label">Landmark</label>
					<div class="col-sm-10">
						<input class="form-control" id="" type="text" name="landmark" required/>
					</div>
				</div>
				
					   			
				<div class="form-group">
					 <label for="inputEmail3" class="col-sm-2 control-label">City</label>
					<div class="col-sm-10">
						<input class="form-control" id="" type="text" name="city" required/>
					</div>
				</div>
							
					   			
				<div class="form-group">
					 <label for="inputEmail3" class="col-sm-2 control-label">State</label>
					<div class="col-sm-10">
						<input class="form-control" id="" type="text" name="state" required/>
					</div>
				</div>			
							
								   			
				<div class="form-group">
					 <label for="inputEmail3" class="col-sm-2 control-label">Country</label>
					<div class="col-sm-10">
						<input class="form-control" id="" type="text" name="country" required/>
					</div>
				</div>
								   			
				<div class="form-group">
					 <label for="inputEmail3" class="col-sm-2 control-label">Pincode</label>
					<div class="col-sm-10">
						<input class="form-control" id="" type="number" name="pincode" required/>
					</div>
				</div>
					   			
				<div class="form-group">
					 <label for="inputEmail3" class="col-sm-2 control-label">Mobile Number</label>
					<div class="col-sm-10">
						<input class="form-control" id="" type="number" name="mobile" required/>
					</div>
				</div>
				<div class="form-group">
					 <label for="inputEmail3" class="col-sm-2 control-label">Email</label>
					<div class="col-sm-10">
						<input class="form-control" id="inputEmail3" type="email" name="email" required />
					</div>
				</div>
				
				
			
						
					</div>
					<div class="panel-footer" >
						<div class="row">
						<div class="col-xs-2 col-sm-6 col-md-6 column"> </div>
						
							 <div class="col-xs-5 col-sm-3 col-md-3 column">
							 <input class="btn btn-info btn-block btn-xs" type="submit" style="width:%;" value="Proceed"></div> 
							 <div class="col-xs-5 col-sm-3 col-md-3 column">
							 <a href="pharmacy.home" class="btn btn-info btn-block btn-xs" type="button" style=";">Cancel</a></div></div>
						</div>
				</div>
				</form>
			</div>     

<%@ include file="pharmacyAside.jsp" %>
			</div>





			<script src="js/bootstrap.min.js"></script>
</body>
</html>










