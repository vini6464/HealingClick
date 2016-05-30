<%@ include file="doctorHeader.jsp" %>
 <script src="js/bootstrap.min.js"></script>
 <script>
$( document ).ready(function() {
	$("#support").click(function() {
		$("#question").modal();
	});
	
});
</script>
 
<div class="container" style="margin-top:%;width:80%;">
	<div class="row clearfix" style=";">
		<div class="col-md-12 column">
			<h3>
			<br/>
		<button id="support" type="button"  class="btn btn-default  btn-sm btn-post ">Ask Question</button></h3>
		 
		</div>
	</div>
</div>	
	
	
	<div class="row clearfix" style="margin-top:2%;">
		<div class="container" style="width:85%;">
		
		
		<c:if test="${empty posts}">
		
			
				<div class="row clearfix">
				
				<div class="col-md-1 column"></div>
				<div class="col-md-10 column">
		<div class="panel panel-default">
  <div class="panel-body">
			<h5 class="text-center">
				There are no Questions available </h5></div></div>
				</div>
				<div class="col-md-1 column"></div>
		</div>
		
	
			</c:if>



			<div class="row clearfix">
			<div class="col-md-2 column"></div>
			<div class="col-md-7 column">
				<c:if test="${not empty posts}">
					<c:forEach var="post" items="${posts}">
					
					<div class="panel panel-default">
  <div class="panel-body">
						<div class="col-xs-9 col-sm-9 col-md-9 column">
							<h5 class="post_name">
								<a href="getSupport.support?id=${post.id}">${post.content}</a>
							</h5>
							<hr class="post-hr">
						</div>
  					
						<div class="col-xs-3 col-sm-3 col-md-3 column">
							<blockquote class="post_blockquote">
								<p class="replies">
									<span class="glyphicon glyphicon-pencil"></span>
									${post.comments} Replies
								</p>
							</blockquote>
						</div>
					</div>
</div>
		
						
					</c:forEach>
				</c:if>
			</div>

<div class="col-md-3 column"></div>


		</div>
	</div>
	
	
	
	
</div>

  
 <div class="modal fade" id="question" role="dialog" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" id="close" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Ask Question / Issues </h4>
				</div>
				<div class="modal-body">
					<form action="javascript:addQuestion()" method="post" class="form-horizontal">
					<table class="table" style="margin-top: 5%;">

								<tr>
									<td class="field-label col-xs-3 active"><label>
											Question </label></td>
									<td class="col-md-9"><textarea class="form-control"
											rows="4" style="height: 200px;" name="content" id="content"  required></textarea></td>
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
  
  
  
  

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    
  </body>
</html>