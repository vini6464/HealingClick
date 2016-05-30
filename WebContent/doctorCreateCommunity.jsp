<%@ include file="doctorHeader.jsp" %>
    <script src="js/bootstrap.min.js"></script>
	<style>

.ms-parent {
    display: inline-block;
    position: relative;
    vertical-align: middle;
}

.ms-choice {
    display: block;
    width: 100%;
    height: 26px;
    padding: 0;
    overflow: hidden;
    cursor: pointer;
    border: 1px solid #aaa;
    text-align: left;
    white-space: nowrap;
    line-height: 26px;
    color: #444;
    text-decoration: none;
    -webkit-border-radius: 4px;
    -moz-border-radius: 4px;
    border-radius: 4px;
    background-color: #fff;
}

.ms-choice.disabled {
    background-color: #f4f4f4;
    background-image: none;
    border: 1px solid #ddd;
    cursor: default;
}

.ms-choice > span {
    position: absolute;
    top: 0;
    left: 0;
    right: 20px;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    display: block;
    padding-left: 8px;
}

.ms-choice > span.placeholder {
    color: #999;
}

.ms-choice > div {
    position: absolute;
    top: 0;
    right: 0;
    width: 20px;
    height: 25px;
    background: url('image/multiple-select.png') left top no-repeat;
}

.ms-choice > div.open {
    background: url('image/multiple-select.png') right top no-repeat;
}

.ms-drop {
    width: 100%;
    overflow: hidden;
    display: none;
    margin-top: -1px;
    padding: 0;
    position: absolute;
    z-index: 1000;
    background: #fff;
    color: #000;
    border: 1px solid #aaa;
    -webkit-border-radius: 4px;
    -moz-border-radius: 4px;
    border-radius: 4px;
}

.ms-drop.bottom {
    top: 100%;
    -webkit-box-shadow: 0 4px 5px rgba(0, 0, 0, .15);
    -moz-box-shadow: 0 4px 5px rgba(0, 0, 0, .15);
    box-shadow: 0 4px 5px rgba(0, 0, 0, .15);
}

.ms-drop.top {
    bottom: 100%;
    -webkit-box-shadow: 0 -4px 5px rgba(0, 0, 0, .15);
    -moz-box-shadow: 0 -4px 5px rgba(0, 0, 0, .15);
    box-shadow: 0 -4px 5px rgba(0, 0, 0, .15);
}

.ms-search {
    display: inline-block;
    margin: 0;
    min-height: 26px;
    padding: 4px;
    position: relative;
    white-space: nowrap;
    width: 100%;
    z-index: 10000;
}

.ms-search input {
    width: 100%;
    height: auto !important;
    min-height: 24px;
    padding: 0 20px 0 5px;
    margin: 0;
    outline: 0;
    font-family: sans-serif;
    font-size: 1em;
    border: 1px solid #aaa;
    -webkit-border-radius: 0;
    -moz-border-radius: 0;
    border-radius: 0;
    -webkit-box-shadow: none;
    -moz-box-shadow: none;
    box-shadow: none;
    background: #fff url('multiple-select.png') no-repeat 100% -22px;
    background: url('multiple-select.png') no-repeat 100% -22px, -webkit-gradient(linear, left bottom, left top, color-stop(0.85, white), color-stop(0.99, #eeeeee));
    background: url('multiple-select.png') no-repeat 100% -22px, -webkit-linear-gradient(center bottom, white 85%, #eeeeee 99%);
    background: url('multiple-select.png') no-repeat 100% -22px, -moz-linear-gradient(center bottom, white 85%, #eeeeee 99%);
    background: url('multiple-select.png') no-repeat 100% -22px, -o-linear-gradient(bottom, white 85%, #eeeeee 99%);
    background: url('multiple-select.png') no-repeat 100% -22px, -ms-linear-gradient(top, #ffffff 85%, #eeeeee 99%);
    background: url('multiple-select.png') no-repeat 100% -22px, linear-gradient(top, #ffffff 85%, #eeeeee 99%);
}

.ms-search, .ms-search input {
    -webkit-box-sizing: border-box;
    -khtml-box-sizing: border-box;
    -moz-box-sizing: border-box;
    -ms-box-sizing: border-box;
    box-sizing: border-box;
}

.ms-drop ul {
    overflow: auto;
    margin: 0;
    padding: 5px 8px;
}

.ms-drop ul > li {
    list-style: none;
    display: list-item;
    background-image: none;
    position: static;
}

.ms-drop ul > li .disabled {
    opacity: .35;
    filter: Alpha(Opacity=35);
}

.ms-drop ul > li.multiple {
    display: block;
    float: left;
}

.ms-drop ul > li.group {
    clear: both;
}

.ms-drop ul > li.multiple label {
    width: 100%;
    display: block;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
}

.ms-drop ul > li label {
    font-weight: normal;
    display: block;
    white-space: nowrap;
}

.ms-drop ul > li label.optgroup {
    font-weight: bold;
}

.ms-drop input[type="checkbox"] {
    vertical-align: middle;
}

.ms-drop .ms-no-results {
    display: none;
}
	.form-horizontal{margin:10px;}
    .communitybc{letterspacing:1px;font-size:1em;text-align:center;}
  

	.forumlabel{background-color:;text-align:center;letter-spacing:1px;}
	
	.foruminput{border:none;border-bottom:1px solid #cccccc;}
   
      .forumsubmit{font-size:1em;width:100px;letter-spacing:1px;}
      .w300 {
    width: 85%;
    border:none;
}
	</style>
	
  
 



	<div class="clearfix h_30"></div>
		
<div class="container-fluid">

   
	<div class="row clearfix">
		<div class="col-sm-5 col-md-3 column"></div>
		<div class="col-sm-7 col-md-5 column">
			
			<ul class="breadcrumb communitybc">
				
				<li>
					<a href="community.community">Community</a> <span class="divider">/</span>
				</li>
				<li class="active">
					Create Community
				</li>
			</ul>
			
		</div>
		<div class="col-md-4 column">
		</div>
	</div>
	
	
	<div class="row clearfix">
		<div class="col-sm-5 col-md-3 column"></div>
		<div class="col-sm-7 col-md-5 column white">
		<c:if test="${empty doctors}">
		
				
				
				
				<h5 class="text-center">
				Sorry , You are not connected with any Doctors.</h5>
				
		
		</c:if>
		<c:if test="${not empty doctors}">
			<form role="form" class="form-horizontal pad_15" action="addCommunity.community" method="post" enctype="multipart/form-data">
				<div class="form-group ">
					 <label for="forumtitle" class=" control-label forumlabel">Title*</label>
					 
					
					  <input type="text" name="title" class="form-control input-sm foruminput" id="exampleInputtext" required/> 
				</div>
				<div class="form-group">
					 <label for="textarea" class=" control-label forumlabel">Description*</label>
					 <textarea rows="3" cols="10" name="description" class="form-control foruminput" id="exampleInputPassword1" required></textarea>
					 
				</div>
				
				<div class="form-group">
					 <label for="textarea" class="control-label forumlabel">Doctors*</label>
					<select multiple="multiple" class="w300" name="doctors" required>
					
										<c:forEach var="doctor" items="${doctors}">
											<c:if test="${not empty doctor}">
        				<option value="${doctor.id}">Dr. ${doctor.firstName} &nbsp; ${doctor.lastName} </option>
        				</c:if>
        				</c:forEach>
   					 </select>
				
				</div>
				
				
				<div class="form-group">
					 <label for="exampleInputPassword1" class="control-label forumlabel">Community Profile Image</label>
					 
					 
					 <input class="" type="file" name="file" accept="image/*" required>
					
				</div>
				<div class="form-group">
					
				 <input type="submit" class="btn btn-xs  forumsubmit pull-right" value="Submit">
				</div>
			</form>
			</c:if>
		</div>
		
		
		<div class="col-md-4 column">
		</div>
	</div>
</div>

  
  
  
  
  
  
<script src="js/multiple-select.js"></script>
    <script>
        $('select').multipleSelect();
    </script>
    
    
  </body>
</html>