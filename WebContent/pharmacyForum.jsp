<%@ include file="pharmacyHeader.jsp" %>
	
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
<div class="container" style="margin-top:%;width:80%;">
	<div class="row clearfix" style=";">
		<div class="col-md-12 column">
			<h3>
			<br/>
		 <a href="createPost.forum"><button type="button"  class="btn btn-default  btn-sm btn-post ">Create a New Post</button></a></h3>
		 
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
				There are no posts available </h5></div></div>
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
    <!-- div class="col-xs-2 col-sm-2 col-md-2 column">
							<img class="img-thumbnail pull-right" alt="140x140"
								src="image/d.jpg" / style="width: 80px; height: 80px;">
						</div--->
						<div class="col-xs-9 col-sm-9 col-md-9 column">
							<h5 class="post_name">
								<a href="getPost.forum?id=${post.id}">${post.name}</a>
							</h5>
							<hr class="post-hr"><h5 class="pull-right">
								<small>Created by</small>
								<c:if test="${pharmacy.id ne post.pharmacyId}">
								<a class="post_by" href="pharmacyViewPharmacy.profile?id=${post.pharmacyId}"> ${post.pharmacyName}</a>
									
									</c:if>
									
									<c:if test="${pharmacy.id eq post.pharmacyId}">
								     You
									</c:if>
								
								
							</h5>


						</div>
  					
						<div class="col-xs-3 col-sm-3 col-md-3 column">
						<blockquote class="post_blockquote">
								<p class="replies">
									<span class="glyphicon glyphicon-pencil"></span>
									${post.comments} Replies
								</p>
								<p class="likes">
									<span class="glyphicon glyphicon-thumbs-up"></span>
									${post.likes} Likes
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
  
  
  
  

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    
  </body>
</html>