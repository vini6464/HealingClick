<%@ include file="patientHeader.jsp" %>
 <script src="js/bootstrap.min.js"></script>
 
 <div class="container" style="margin-top:%;width:80%;">
	<div class="row clearfix" style=";">
		<div class="col-md-12 column">
			<h3>
			<br/>
		 <a href="createSuggestion.suggestion"><button type="button"  class="btn btn-default  btn-sm btn-post ">Create a New Suggestion</button></a></h3>
		 
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
				There are no Suggestions available </h5></div></div>
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
								<a href="getSuggestion.suggestion?id=${post.id}">${post.name}</a>
							</h5>
							<hr class="post-hr">
							<h5 class="pull-right ">
								<small>To </small><a class="post_by"
									href="patientViewDoctor.profile?id=${post.doctorId}">
									Dr. ${post.doctorName}</a>
							</h5>


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

  
  
  
  
  
  

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    
  </body>
</html>