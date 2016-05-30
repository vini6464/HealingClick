<%@ include file="pharmacyHeader.jsp" %>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script><script>
function preventBackButton(){window.history.forward();}
setTimeout("preventBackButton()", 0);
window.onunload=function(){null};
</script>
	
	
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
	
	
	<script>$(function() {
	$("#ord").click(function() {
		$("#notord").hide();
	
});		
});
</script>


		
<div class="container">
	<div class="row clearfix">
		<div class="col-md-1 column"></div>
		<div class="col-md-3 column">
			
		
			<br>
			<br>
			<ul class="breadcrumb communitybc">
				
				<li>
					<a href="forum.forum">Community</a> <span class="divider">/</span>
				</li>
				<li class="active">
					Create Post
				</li>
			</ul>
			
		</div>
		<div class="col-md-6 column">
		</div>
	</div>
	
	<div class="row clearfix">
		<div class="col-md-8 column">
			<form role="form" class="form-horizontal" action="savePost.forum" method="post" enctype="multipart/form-data">
				<div class="form-group">
					 <label for="forumtitle" class="col-sm-2  input-sm control-label forumlabel">Title</label>
					 <div class="col-sm-10">
					 <input type="text" name="postname" class="form-control foruminput" id="exampleInputtext" required/></div>
				</div>
				<div class="form-group">
				
					 <label for="textarea" class="col-sm-2  control-label forumlabel">Content</label>
					 <div class="col-sm-10">
					 <textarea rows="3" cols="10" name="postcontent" class="form-control foruminput" id="exampleInputPassword1" required></textarea></div>
				</div>
				<div class="form-group">
					<label for="exampleInputPassword1" class="col-sm-2  control-label forumlabel">Image</label>
					 <div class="col-sm-10">
					 <input type="file" name="file" accept="image/*"> </div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
				 <input type="submit" class="btn btn-xs forumsubmit" value="Submit">
				 </div></div>
			</form>
		</div>
		<div class="col-md-4 column">
		</div>
	</div>
</div>

  </div>
  
  
  
  
  

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    
  </body>
</html>