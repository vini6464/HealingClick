<%@ include file="pharmacyHeader.jsp" %>

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
   
    <script src="js/bootstrap.min.js"></script>
	

	
	<br/>
	<br/>
	<br/>
	
	
	
	
	<div class="container">
	<div class="row clearfix">
		<div class="col-xs-12 col-sm-12 col-md-2 column"></div>
		<div class="col-md-8 column">
			<div class="panel panel-default">
				
							<div class="panel-body">			
	<div class="row clearfix">
	    <!-- 
		<div class="col-xs-2 col-sm-2 col-md-1 column">
			<img class="img-thumbnail"alt="140x140" src="image/d.jpg"  style="width:90px;height:80px;">
		</div> -->
		<div class="col-xs-12 col-sm-12 col-md-12 column">
			<h5 class="post_name">
				<a href="#" style="text-decoration:none;">${post.name}</a><c:if test="${post.pharmacyId == pharmacy.id}"><a href="deletePost.forum?id=${post.id}" class="pull-right"><input type="button" class="btn btn-xs" value="X"></a></c:if></h3>
				<hr style="margin-top:-8px;margin-bottom:-8px;height:1px; border: none; color: #c9ead2; background-color: #337AB7;"><h3 class="pull-right"><small>Created by</small>
				<c:if test="${pharmacy.id ne post.pharmacyId}">
								<a href="pharmacyViewPharmacy.profile?id=${post.pharmacyId}"> ${post.pharmacyName}</a>
									
									</c:if>
									
									<c:if test="${pharmacy.id eq post.pharmacyId}">
								     You
									</c:if>  </h3>
				
			
		
		
	    
	</div>
					</h3>
				</div>
			
					${post.content}<br>
					
					<c:if test="${not empty post.forumImage}">
										<img alt="300x200" height=300 width=300 src="${post.forumImage}" />
										
									</c:if>
					
					
				</div>
				<div class="panel-footer">
				<div class="row">
				
				<div class="col-xs-12 col-sm-12 col-md-12 column">
				<!-- 
				<c:if test="${post.liked == 1}"><div id="like">
			<input type="button" value="UnLike" class="btn btn-default btn-xs btn-info" onclick="notLike(${post.id});">
			</div></c:if>
			
			<c:if test="${post.liked == 0}"><div id="like">
			<input type="button" value="Like" class="btn btn-default btn-xs btn-info" onclick="doLike(${post.id});">
			</div></c:if>-->
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
			<div class="row clearfix">
	    
		<div class="col-xs-12 col-sm-12 col-md-12 column-active">
			<h5 class="post_by">
			
			<c:if test="${pharmacy.id ne comment.pharmacyId}">
								<a href="pharmacyViewPharmacy.profile?${comment.pharmacyId}" style="text-decoration:none;">${comment.pharmacyName}</a>
									
									</c:if>
									
									<c:if test="${pharmacy.id eq comment.pharmacyId}">
								     You
									</c:if> 
			
				 <small>Commented As </small> ${comment.comment}</h5>
				
				<hr class="getpost-hr">
				
			
		</div>	
		</div>
		</c:forEach>
		<form action="saveComment.forum?id=${post.id}" method="post">
		<div class="row clearfix">
		<div class="col-md-8 column" style="padding-left:3%;padding-bottom:1%;padding-top:1%;padding-right:1%;">
		<div class="form-group">
					 <label for="exampleInputPassword1">Write Your Comment:</label><textarea rows="3" cols="10" class="form-control" id="exampleInputPassword1" name="comment" required/> </textarea>
				</div>
				
					 <input type="submit" class="btn btn-xs writepost_button" style="width:100px;" value="Submit">
                </div>
				 
		
		
		<div class="col-md-4 column">
		</div>
	</div>
	</form>
	</div>
</div>
	
	</div>

  </body>
</html>