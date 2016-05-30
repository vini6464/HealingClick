<%@ include file="doctorHeader.jsp" %>
 <script src="js/bootstrap.min.js"></script>
 
 <script>
 
 var postId = "${post.id}";
 
 $(document).ready(function() {
 var insert = "${insert}";
	if(insert == 1)
		{
			window.history.pushState("", "", 'getPost.forum?id=${post.id}');
		}
 });
 $(function() {
		$("#like").click(function() {
			
			var val = $("#like").data("value");
			if (val == 1)
			{
				notLike(postId);
			}
			else
				{
				doLike(postId);
				}
		});
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
				<a href="#" style="text-decoration:none;">${post.name}</a>
				<span class="pull-right">Members of community :
				<c:forEach var="doctor" items="${doctors}">
											<c:if test="${not empty doctor}">
        				<a href="doctorViewDoctor.profile?id=${doctor.id}">Dr. ${doctor.firstName} &nbsp; ${doctor.lastName} ,</a>
        				</c:if>
        				</c:forEach></span>
				<c:if test="${post.doctorId == doctor.id}"><a href="deletePost.forum?id=${post.id}" class="pull-right"><input type="button" class="btn btn-xs" value="X"></a></c:if></h5>
				<hr class="post-hr"><h5 class="pull-right"><small>Created by</small> <c:if test="${doctor.id ne post.doctorId}">
								<a href="doctorViewDoctor.profile?id=${post.doctorId}">
									Dr. ${post.doctorName}</a>
									</c:if>
									
									<c:if test="${doctor.id eq post.doctorId}">
								     You
									</c:if></h5>
				
			
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
			
				<c:if test="${post.liked == 1}">
						<span class="glyphicon glyphicon-thumbs-up" style="color: aqua;" id="like" data-value="1"></span>   <label id="likeValue">${post.likes}</label> Likes</c:if>
					<c:if test="${post.liked == 0}"><span class="glyphicon glyphicon-thumbs-up" id="like" data-value="0"></span>   <label id="likeValue">${post.likes}</label> Likes</c:if>	
						
					<span class="glyphicon glyphicon-pencil"  style="padding-left:10px;"></span>  ${post.comments} Replies 	
                </div>
				 
		  </div>
				</div>
			</div>
			<c:forEach var="comment" items="${post.comment}">
			<div class="row clearfix getpost_commentby">
	    
		<div class="col-xs-12 col-sm-12 col-md-12 column-active">
			<h5 class="post_by">
			
			<c:if test="${doctor.id ne comment.doctorId}">
								<a href="doctorViewDoctor.profile?id=${comment.doctorId}" style="text-decoration:none;">${comment.doctorName}</a>
									</c:if>
									
									<c:if test="${doctor.id eq comment.doctorId}">
								     You
									</c:if>
			
				 <small>    Commented As </small> ${comment.comment}</h5>
				
				<hr class="getpost-hr">
				
			
		</div>	
		</div>
		</c:forEach>
		<form action="saveComment.forum?id=${post.id}" method="post">
		<div class="row clearfix getpost_writecomment">
		<div class="col-md-9 column">
		<div class="form-group">
					 <label for="exampleInputPassword1">Write Your Comment:</label><textarea rows="3" cols="10" class="form-control" id="exampleInputPassword1" name="comment" required></textarea>
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