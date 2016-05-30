<%@ include file="patientHeader.jsp" %>

  <!-- Include all compiled plugins (below), or include individual files as needed -->
   <script src="js/bootstrap.min.js"></script>

	<style>
	
	.form-horizontal{margin:10px;}
    .communitybc{letterspacing:1px;font-size:1em;text-align:center;}
  

	.forumlabel{background-color:;text-align:center;letter-spacing:1px;}
	
	.foruminput{border:none;border-bottom:1px solid #cccccc;}
   
      .forumsubmit{font-size:1em;width:100px;letter-spacing:1px;}
	</style>
	
  
 


<div class="h_30"></div>
	
		
<div class="container-fluid">
	<div class="row clearfix">
		<div class="col-sm-4 col-md-3 column"></div>
		<div class="col-sm-8 col-md-5 column ">
			
		
			
			<ul class="breadcrumb communitybc">
				
				<li>
					<a href="suggestion.suggestion">Suggestion</a> <span class="divider">/</span>
				</li>
				<li class="active">
					Create Suggestion
				</li>
			</ul>
			
		</div>
		<div class="col-md-4 column">
		</div>
	</div>
	
	
	<div class="row clearfix">
		<div class="col-sm-4 col-md-3 column"></div>
		<div class="col-sm-8 col-md-5 column white">
		
			<form role="form" class="form-horizontal pad_15" action="saveSuggestion.suggestion" method="post" enctype="multipart/form-data">
				<div class="form-group">
					 <label for="forumtitle" class=" control-label forumlabel">Doctor Name</label>
					<select name="doctorid" class="form-control"><c:forEach var="doctor" items="${doctors}"><option value="${doctor.id}">Dr. ${doctor.firstName} ${doctor.lastName}</option></c:forEach></select>
				
				</div>
				<div class="form-group ">
					 <label for="forumtitle" class=" control-label forumlabel">Title</label>
					 
					
					  <input type="text" name="postname" class="form-control input-sm foruminput" id="exampleInputtext" required/> 
					 
				</div>
				<div class="form-group">
					 <label for="textarea" class="  control-label forumlabel">Content</label>
					 <textarea rows="3" cols="10" name="postcontent" class="form-control foruminput" id="exampleInputPassword1" required></textarea>
					
				</div>
				<div class="form-group">
					 <label for="exampleInputPassword1" class=" control-label forumlabel">Image</label>
					 
					 <input class="" type="file" name="file" accept="image/*">
					
				</div>
				<div class="form-group">
					
				 <input type="submit" class="btn btn-xs pull-right forumsubmit" value="Submit">
				 </div>
			</form>
		</div>
		
		
		<div class="col-md-4 column">
		</div>
	</div>
</div>

  
  
  
  
  
  

    
    
  </body>
</html>