<%@ include file="pharmacyHeader.jsp" %>
<script>
function preventBackButton(){window.history.forward();}
setTimeout("preventBackButton()", 0);
window.onunload=function(){null};
</script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
      <script>
$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip();   
    var insert = "${insert}";
	if(insert == 1)
		{
			window.history.pushState("", "", 'settings.login');
		}
});
</script>


<style type="text/css">


</style>

<script>$(function() {
	$("#ord").click(function() {
		$("#notord").hide();
	
});		
});
</script>




	<div class="container" style="margin-top: 4%;">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="row clearfix">
					<div class="col-md-2"></div>
					<div class="col-xs-12 col-sm-6 col-md-5 column">

						<div class="panel panel-default" >
				<div class="panel-heading">
					<h3 class="panel-title text-center" style="font-weight:; color:grey ;">
							Change password
					</h3>
				</div>
				<div class="panel-body" style="background-color: #eeeeee;">
			
				
				${error}${success}
					<form class="form-horizontal" name="form" role="form" 
					action="changepassword.login" onSubmit="return comparepassword();" method="post">
					

					

                       <div class="form-group">
						
						<div class="col-sm-12" style="padding-left:30px;padding-right:30px;padding-top:px;">
							<input type="password" class="form-control" id="userpassword"
								name="oldpassword"   data-toggle="tooltip" data-placement="top" title="enter your old password" placeholder="old password"  style="height:30px;" onblur="checkPassword();" required/>
                             <div id="pass"></div>  
						</div>
					</div>


					<div class="form-group">
						<!--label for="inputPassword3" class="col-sm-4 control-label">Enter
							a new password:</label-->
						<div class="col-sm-12" style="padding-left:30px;padding-right:30px;padding-top:px;">
							<input type="password" class="form-control" id="userpassword"
								name="newpassword"   data-toggle="tooltip" data-placement="top" title="enter your new password" placeholder="new password"  style="height:30px;"   required/>
                                <div id="match"></div>
						</div>
					</div>
					<div class="form-group">
						<!--label for="inputPassword3" class="col-sm-4 control-label">Type
							it again:</label-->
						<div class="col-sm-12" style="padding-left:30px;padding-right:30px;padding-top:px;">
							<input type="password" class="form-control" id="userpassword1"
								name="confirmpassword" data-toggle="tooltip" data-placement="top" title="retype password" placeholder="retype password"  style="height:30px;"  required />
                               
						</div>
					</div>

					

					
                     
					 <div class="col-md-12 column">
					<input type="submit" class="btn btn-default btn-info active btn-xs"
								style=" letter-spacing: 1px;font-weight:; font-size: 12px;" value="Change password"> </div>
				</form>
				
					
				</div>
				
			</div>
					</div>
					




					



					

						</div>

					</div>
















					

				</div>
			</div>
</body>
</html>










