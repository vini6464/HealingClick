<%@ include file="doctorHeader.jsp" %>

 <script src="js/bootstrap.min.js"></script>
 
 <script>
 $(document).ready(function() {
	 var insert = "${insert}";
		if(insert == 1)
			{
				window.history.pushState("", "", 'getSuggestion.suggestion?id=${post.id}');
			}
	 });
 
 </script>
	<div class="container">
	<div class="row clearfix">
	
	<div class="col-xs-12 col-sm-12 col-md-2 column"></div>
		<div class="col-md-8 column">
			<div class="panel panel-default">
					
					<div class="panel-body">		
	<div class="row clearfix">
		<div class="col-xs-12 col-sm-12 col-md-12 column">
			<h5 class="post_name">
				<a href="#" style="text-decoration:none;">${post.name}</a></h5>
				<hr class="post-hr"><h5 class="pull-right"><small>Created by</small>
				 <a class="post_by" href="doctorViewPatient.profile?id=${post.patientId}"> ${post.patientName}</a></h5>
				
			
		</div>
		
	    
	</div>
					</h3>
				</div>
				
				<p class="post_content">	${post.content} <br>
					
					<c:if test="${not empty post.forumImage}">
										<img alt="300x200" height=300 width=300 src="${post.forumImage}" />
										
									</c:if>
					
					</p>
				
				<div class="panel-footer">
				<div class="row">
				
				<div class="col-xs-12 col-sm-12 col-md-12 column">
			<div class="pull-right">
					<span class="glyphicon glyphicon-pencil"  style="padding-left:10px;"></span>  ${post.comments} Replies 	
                </div>
				 
		  </div>
				</div>
			</div>
			<form action="saveComment.suggestion?id=${post.id}" method="post">
		<div class="row clearfix getpost_writecomment">
		<div class="col-md-9 column">
		<div class="form-group">
					 <label for="exampleInputPassword1">Write Your Comment:</label><textarea rows="3" cols="10" class="form-control" id="exampleInputPassword1" name="comment" required></textarea>
					  <label for="exampleInputPassword1">Fees : </label><input type="number" class="form-control" id="exampleInputPassword1" name=fees value=0 required/>
				</div>
				
					 <input type="submit" class="btn btn-xs writepost_button" style="width:100px;" value="Submit">
                </div>
				 
		
		
		<div class="col-md-3 column">
		</div>
	</div>
	</form>
	
	<hr class="getpost-hr">
			<c:forEach var="comment" items="${post.comment}">
			<div class="row clearfix getpost_commentby">
	    
		<div class="col-xs-12 col-sm-12 col-md-12 column-active">
			<h5 class="post_by">
			
			<c:if test="${comment.patientId ne 0}">
				<a href="doctorViewPatient.profile?id=${comment.patientId}" style="text-decoration:none;">${comment.patientName}</a> <small>&nbsp;&nbsp;&nbsp;&nbsp;Commented As&nbsp;&nbsp;&nbsp;&nbsp; </small> ${comment.comment}</c:if>
				<c:if test="${comment.doctorId ne 0}">
				Dr . ${comment.doctorName}<small>&nbsp;&nbsp;&nbsp;&nbsp;Commented As&nbsp;&nbsp;&nbsp;&nbsp; </small> ${comment.comment}
				<c:if test="${comment.fees eq 0}">
				(No Fees expected)
				</c:if>
				
				<c:if test="${comment.fees ne 0}">
					<c:if test="${not empty comment.transactionId}">
				    (${comment.fees}.Rs has been paid as fees)
					</c:if>
					
					<c:if test="${empty comment.transactionId}">
				    (${comment.fees}.Rs has not been paid as fees. So comment will not be visible to patient)
					</c:if>
					
					</c:if>
				</c:if>
				</h5>
				<hr class="getpost-hr">
				
			
		</div>	
		</div>
		</c:forEach>
		
	</div>
</div>
	
	
	
	<div class="col-xs-12 col-sm-12 col-md-2 column"></div>
	</div>

  </body>
</html>