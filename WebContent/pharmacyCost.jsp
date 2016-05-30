<%@ include file="pharmacyHeader.jsp" %>
<script>
function preventBackButton(){window.history.forward();}
setTimeout("preventBackButton()", 0);
window.onunload=function(){null};
</script>
<script type="text/javascript" src="js/validation.js"></script>

	<div class="container-fluid profpbelowimage" >
	<div class="row clearfix" >
		
			
		
				<div class="col-xs-12 col-sm-10 col-md-8 column col-md-offset-2 col-sm-offset-1" style="word-wrap:break-word;">
				<h4 class="text-center profpheader"> Edit Delivery Charge and Discount </h4>
							<div class="panel panel-default">
						
							 <div class="panel-body">
							  
				 <h5 class="filldetailsheading">  PLEASE FILL THE DETAILS BELOW</h5> 
					<hr align="left"  style="margin-top:-7px;border:2px solid #ffa500;width:70px;margin-left:10px;">
						
				  
				   <form action="updateDeliveryCost.pharmacy" method="post" name="form" >
			
            <div class="row profpd" >
              <div class="col-xs-6 col-sm-3 col-md-3 profplabel column">Delivery Charge :</div>
               <div class="col-xs-12 col-sm-9 col-md-9 column" ><div class="col-xs-9 col-sm-9 col-md-9 column"><input type="number" value=0  step="0.01" class="form-control  pfilldetailsinput" name="deliveryCharge" placeholder="Delivery Charge" required/></div>Rupees<span class="error-message" id="deliveryCharge"></span></div>
             
            </div>			 
			<div class="row profpd" >
             <div class="col-xs-6 col-sm-3 col-md-3 profplabel column">Discount :</div>
               <div class="col-xs-12 col-sm-9 col-md-9 column" ><div class="col-xs-9 col-sm-9 col-md-9 column"><input class="form-control pfilldetailsinput"  value=0  step="0.01" name="disount" type="number" placeholder="Discount" required/></div>%<span class="error-message" id="disount"></span></div>
            </div>	
            
            
            <div class="row profpd text-center" >
             <div class="col-xs-6 col-sm-3 col-md-3  column"></div>
             <div class="col-xs-12 col-sm-9 col-md-9 column" ><div class="col-xs-9 col-sm-9 col-md-9 column"><input type="submit" value="Update"  class="btn btn-sm btn-warning peditbtn"></div></div>
            </div>	
            </form>				
					</div>
					</div>
					
					
					
				
				</div>
			</div>

</div>




			<script src="js/bootstrap.min.js"></script>

</html>










