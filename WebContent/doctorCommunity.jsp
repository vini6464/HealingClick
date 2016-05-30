<%@ include file="doctorHeader.jsp" %>
 <script src="js/bootstrap.min.js"></script>
<div class="container" style="margin-top:%;width:80%;">
<div class="h_20"></div>
	<div class="row clearfix" style=";">
		<div class="col-md-12 column">
			<h3>
			<br/>
		 <a href="createCommunity.community"><button type="button"  class="btn btn-default  btn-sm btn-post ">Create a New Community</button></a></h3>
		 
		</div>
	</div>
</div>	
	
	
	<div class="row clearfix" style="margin-top:2%;">
		<div class="container" style="width:85%;">
		
		
		<c:if test="${empty communities}">
		
			
				<div class="row clearfix">
				
				<div class="col-md-1 column"></div>
				<div class="col-md-10 column">
		<div class="panel panel-default">
  <div class="panel-body">
			<h5 class="text-center">
				There are no Communities available </h5></div></div>
				</div>
				<div class="col-md-1 column"></div>
		</div>
		
	
			</c:if>



			<div class="row clearfix">
			<div class="col-md-2 column"></div>
			<div class="col-md-7 column">
				<c:if test="${not empty communities}">
					<c:forEach var="post" items="${communities}">
					
					<div class="panel panel-default">
  <div class="panel-body">
						<div class="col-xs-12">
							<h5 class="post_name">
								<a href="getCommunity.community?id=${post.id}">${post.title}</a>
							
							
							<span class="pull-right ">
								<small>Created by</small>
								<c:if test="${doctor.id ne post.doctorId}">
								<a class="post_by"
									href="doctorViewDoctor.profile?id=${post.doctorId}">
									${post.doctorName}</a>
									</c:if>
									
									<c:if test="${doctor.id eq post.doctorId}">
								     You
									</c:if>
							</span>

                       </h5>
						</div>
  					
						<%-- <div class="col-xs-3 col-sm-3 col-md-3 column">
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
						</div> --%>
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