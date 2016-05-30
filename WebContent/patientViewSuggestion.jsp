<%@ include file="patientHeader.jsp" %>
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
	    
		<!-- div class="col-xs-2 col-sm-2 col-md-1 column">
			<img class="img-thumbnail"alt="140x140" src="image/d.jpg" / style="width:90px;height:80px;">
		</div-->
		
		
		<div class="col-xs-12 col-sm-12 col-md-12 column">
			<h5 class="post_name">
				<a href="#" style="text-decoration:none;">${post.name}</a></h5>
				<hr class="post-hr"><h5 class="pull-right"><small>To</small>
				<a class="post_by"
									href="patientViewDoctor.profile?id=${post.doctorId}">
									Dr. ${post.doctorName}</a></h5>
				
			
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
			<c:forEach var="comment" items="${post.comment}">
			<div class="row clearfix getpost_commentby">
	    
		<div class="col-xs-12 col-sm-12 col-md-12 column-active">
			<h5 class="post_by">
			
			<c:if test="${comment.patientId ne 0}">
			${comment.patientName} <small>&nbsp;&nbsp;&nbsp;&nbsp;Commented As&nbsp;&nbsp;&nbsp;&nbsp; </small> ${comment.comment}</c:if>
				<c:if test="${comment.doctorId ne 0}">
				<a class="post_by" href="patientViewDoctor.profile?id=${comment.doctorId}">
						Dr . ${comment.doctorName}			</a><small>&nbsp;&nbsp;&nbsp;&nbsp;Commented As&nbsp;&nbsp;&nbsp;&nbsp; </small> ${comment.comment}</c:if>
				</h5>
				<hr class="getpost-hr">
				
			
		</div>	
		</div>
		</c:forEach>
		<form action="saveComment.suggestion?id=${post.id}" method="post">
		<div class="row clearfix getpost_writecomment">
		<div class="col-md-9 column">
		<div class="form-group">
					 <label for="exampleInputPassword1">Write Your Comment:</label><textarea rows="3" cols="10" class="form-control" id="exampleInputPassword1" name="comment"/> </textarea>
				</div>
				
					 <input type="submit" class="btn btn-xs writepost_button" style="width:100px;" value="Submit">
                </div>
				 
		
		
		<div class="col-md-3 column">
		</div>
	</div>
	</form>
	</div>
</div>
	
	
	
	<div class="col-xs-12 col-sm-12 col-md-2 column"></div>
	</div>

  </body>
</html>