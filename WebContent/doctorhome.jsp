<%@ include file="doctorHeader.jsp" %>
<script>
var postCount = 0;
var repeat = 0;
var post ;
var doctorImage = $("#profileImage").attr('src');
var doctorId = "${doctor.id}";
var finish = 0;
$( document ).ready(function() {
	$("#post").click(function() {
		$("#addPost").modal();
	});
	
	 var insert = "${insert}";
		if(insert == 1)
			{
				window.history.pushState("", "", 'doctor.home');
			}
	
	showPost();	
	
	$(window).scroll(function(){
		if(finish=0){
			var s = $( "#postlength" ).height();
		    if($( window ).scrollTop( ) >= (s - 100)) {
		
		    		showPost();
		    	
		    }
		}
	
		}); 
});

function showPost(){
	
	if(postCount == 0)
		{
		finish=1;
		$.ajax({
			method : "GET",
			url : 'getPosts.notification',
			datatype : 'json',
			success : function(result) {
				if(result!="")
					{
					 post = $.parseJSON(result);
					 d = post;
					
					 var html = "";
					for ( var i = 0; i < d.length; i++) {
						
						if(postCount <= 10)
							{
							 var creator = 0;
							html = html + '<div class="col-md-12 column"><div class="panel panel-default"><div class="panel-heading post_header"><div class="row"><div class="col-xs-3 col-md-2 poster_image">'
								+'<img src="'+d[i].image+'" style="width:30px; height:30px;"/></div><div class="col-xs-8 col-md-9"><span class="poster_name">'
								
								if(d[i].doctorId != doctorId){
									html = html +'<a href="doctorViewDoctor.profile?id='+d[i].doctorId+'">'+ d[i].doctorName+'</a></span><br/>';
								}
								else
									{
									creator = 1;
									html = html + d[i].doctorName+'</span> <span class="pull-right"><div class="dropdown"><button class="btn dropdown-toggle btn-delp" type="button" data-toggle="dropdown"><span class="glyphicon glyphicon-menu-down"></span></button><ul class="dropdown-menu dropdown_delp"><li><a href="javascript:deletePost('+d[i].id+');">Delete Post</a></li></ul></div></span>'
									+'<br/>';
									}
								html = html +'<span class="posted_date_time">'+d[i].creationDate+'</span></div></div></div><div class="panel-body no_pad_bottom"><h5 class="post_title">'+d[i].status+'</h5>';
								if(d[i].postType == 2){
									html = html + '<p><img src="'+d[i].filePath+'" style="max-width:100%"/></p>';
								}
								if(d[i].postType == 3){
									html = html + '<p><iframe style="width:100%" height="315" src="'+d[i].filePath+'" frameborder="0" allowfullscreen></iframe></p>';
								}
								if(d[i].liked == 1){
								html = html+'<div class="row" ><div class="col-xs-12"><span class="glyphicon glyphicon-thumbs-up" style="color: #00b9bc;" id="like'+d[i].id+'" data-value="1" onclick="likeFunction('+d[i].id+');"></span> <label id="likeValue'+d[i].id+'" style="color: #23527c;"> '+d[i].likes+' </label>         '
								+'       <button type="button" class="btn-link"><span class="glyphicon glyphicon-comment"></span> <label id="commentValue'+d[i].id+'" data-value="'+d[i].comments+'" > '+d[i].comments+'</label></button></div></div>';
								}
								
								if(d[i].liked == 0){
								html = html+'<div class="row" ><div class="col-xs-12"><span class="glyphicon glyphicon-thumbs-up" id="like'+d[i].id+'" data-value="0" onclick="likeFunction('+d[i].id+');"></span> <label id="likeValue'+d[i].id+'" style="color: #23527c;">'+d[i].likes+'</label>                     '
								+'       <button type="button" class="btn-link"><span class="glyphicon glyphicon-comment"></span> <label id="commentValue'+d[i].id+'" data-value="'+d[i].comments+'" > '+d[i].comments+'</label></button></div></div>';
								}
								
								html = html+'<div class="row comment_row"><div class="col-xs-2 comment_by_img_container" style="width:30px;height:30px;"><img class="comment_by_img" src="'+doctorImage+'" /></div><div class="col-xs-10">'
								+'<textarea placeholder="Write a comment"  class="form-control cmnt_form" id="cmt'+d[i].id+'" onkeypress=saveComment(event,'+d[i].id+'); ></textarea></div></div><p style="background-color:#fff; height:1px;margin:0px;padding:0px;"></p> <span id="shift'+d[i].id+'"></span>';
								
								var com = d[i].comment;
								for ( var j = 0; j < com.length; j++) {
									var dc = "";
									var owner="";
									if(com[j].doctorId != doctorId){
										dc ='<a href="doctorViewDoctor.profile?id='+com[j].doctorId+'">Dr.'+ com[j].doctorName+'</a>';
									}
									else
										{
										owner='<span class="close pull-right"><a href="javascript:deleteComment('+d[i].id+','+com[j].id+');" class="close" data-dismiss="alert" aria-label="close">&times;</a></span>';
										dc = 'Dr.'+d[i].doctorName;
										}
									
									if(creator == 1){
										owner='<span class="close pull-right"><a href="javascript:deleteComment('+d[i].id+','+com[j].id+');" class="close" data-dismiss="alert" aria-label="close">&times;</a></span>';
									}
									
									html = html+'<div id=com'+d[i].id+com[j].id+'><div class="row comment_row"><div class="col-xs-2 comment_by_img_container" style="width:30px;height:30px;"><img class="comment_by_img" src="'+com[j].image+'"/></div>'
									+'<div class="col-xs-10"><p class="ppl_comments"><span class="commentby_name">'+dc+'</span>   commented as   '+com[j].comment+' '+owner+'  </p><span class="posted_date_time" style="padding-left:10px;position: relative;top: -5px;">'+com[j].created+'</span></div></div></div>';
									
									
								}
								
								html = html+'</div></div></div>';	
								postCount = postCount + 1;
							}
						
					}
					
					
						html = html+'<div id="'+postCount+'"></div>';	
						$( "#postlength" ).html(html);
					    finish=0;
					
					
					}
				

			},
			statusCode : {
				500 : function(result) {
					
				}
			}
		});
		
		}
	else
		{
		
		
			d = post;
			 var html = "";
			 var count = postCount;
			for ( var i = postCount; i < d.length; i++) {
				
				if(count <= postCount+10)
					{
					 var creator = 0;
					html = html + '<div class="col-md-12 column"><div class="panel panel-default"><div class="panel-heading post_header"><div class="row"><div class="col-xs-3 col-md-2 poster_image">'
					+'<img src="'+d[i].image+'" style="width:30px; height:30px;"/></div><div class="col-xs-8 col-md-9"><span class="poster_name">'
					
					if(d[i].doctorId != doctorId){
						html = html +'<a href="doctorViewDoctor.profile?id='+d[i].doctorId+'">'+ d[i].doctorName+'</a></span><br/>';
					}
					else
						{
						creator=1;
						html = html + d[i].doctorName+'</span> <span class="pull-right"><div class="dropdown"><button class="btn dropdown-toggle btn-delp" type="button" data-toggle="dropdown"><span class="caret"></span></button><ul class="dropdown-menu dropdown_delp"><li><a href="javascript:deletePost('+d[i].id+');">Delete Post</a></li></ul></div></span>'
						+'<br/>';
						}
					html = html +'<span class="posted_date_time">'+d[i].creationDate+'</span></div></div></div><div class="panel-body no_pad_bottom"><h5 class="post_title">'+d[i].status+'</h5>';
					if(d[i].postType == 2){
						html = html + '<p><img src="'+d[i].filePath+'" style="max-width:100%"/></p>';
					}
					if(d[i].postType == 3){
						html = html + '<p><iframe style="width:100%" height="315" src="'+d[i].filePath+'" frameborder="0" allowfullscreen></iframe></p>';
					}
					if(d[i].liked == 1){
					html = html+'<div class="row" ><div class="col-xs-12"><span class="glyphicon glyphicon-thumbs-up" style="color: #00b9bc;" id="like'+d[i].id+'" data-value="1" onclick="likeFunction('+d[i].id+');"></span> <label id="likeValue'+d[i].id+'" style="color: #23527c;"> '+d[i].likes+' </label>         '
					+'       <button type="button" class="btn-link"><span class="glyphicon glyphicon-comment"></span> <label id="commentValue'+d[i].id+'" data-value="'+d[i].comments+'" > '+d[i].comments+'</label></button></div></div>';
					}
					
					if(d[i].liked == 0){
					html = html+'<div class="row" ><div class="col-xs-12"><span class="glyphicon glyphicon-thumbs-up" id="like'+d[i].id+'" data-value="0" onclick="likeFunction('+d[i].id+');"></span> <label id="likeValue'+d[i].id+'" style="color: #23527c;">'+d[i].likes+'</label>                     '
					+'       <button type="button" class="btn-link"><span class="glyphicon glyphicon-comment"></span> <label id="commentValue'+d[i].id+'" data-value="'+d[i].comments+'" > '+d[i].comments+'</label></button></div></div>';
					}
					
					html = html+'<div class="row comment_row"><div class="col-xs-2 comment_by_img_container" style="width:30px;height:30px;"><img class="comment_by_img" src="'+doctorImage+'" /></div><div class="col-xs-10">'
					+'<textarea placeholder="Write a comment"  class="form-control cmnt_form" id="cmt'+d[i].id+'" onkeypress=saveComment(event,'+d[i].id+'); ></textarea></div></div><p style="background-color:#fff; height:1px;margin:0px;padding:0px;"></p> <span id="shift'+d[i].id+'"></span>';
					
					var com = d[i].comment;
					for ( var j = 0; j < com.length; j++) {
						var dc = "";
						var owner="";
						if(com[j].doctorId != doctorId){
							dc ='<a href="doctorViewDoctor.profile?id='+com[j].doctorId+'">Dr.'+ com[j].doctorName+'</a>';
						}
						else
							{
							owner='<span class="close pull-right"><a href="javascript:deleteComment('+d[i].id+','+com[j].id+');" class="close" data-dismiss="alert" aria-label="close">&times;</a></span>';
							dc = 'Dr.'+d[i].doctorName;
							}
						if(creator == 1){
							owner='<span class="close pull-right"><a href="javascript:deleteComment('+d[i].id+','+com[j].id+');" class="close" data-dismiss="alert" aria-label="close">&times;</a></span>';
						}
						
						html = html+'<div id=com'+d[i].id+com[j].id+'><div class="row comment_row"><div class="col-xs-2 comment_by_img_container" style="width:30px;height:30px;"><img class="comment_by_img" src="'+com[j].image+'"/></div>'
						+'<div class="col-xs-10"><p class="ppl_comments"><span class="commentby_name">'+dc+'</span>   commented as   '+com[j].comment+' '+owner+'  </p><span class="posted_date_time" style="padding-left:10px;position: relative;top: -5px;">'+com[j].created+'</span></div></div></div>';
						
						
						
					}
					
					html = html+'</div></div></div>';		
						count = count + 1;
					}
				
			}
			var place = postCount;
			postCount = count;
			
			html = html+'<div id="'+postCount+'"></div>';	
			$( "#"+place ).html(html);

		
			}	
}


function likeFunction(id){
	
	var val = $("#like"+id).data("value");
	if (val == 1)
	{

		$("#like"+id).data("value",0);
		$("#like"+id).css({ "color": ''});
		
		$.ajax({
			method : "GET",
			url : 'unlikePostDoctor.notification',
			datatype : 'json',
			data: { 
				id : id
				
			},
			success : function(result) {
				$("#likeValue"+id).html(result);
				
			},
			statusCode : {
				500 : function(result) {
					
				}
			}
		});
	}
	else
		{
		
		$("#like"+id).data("value",1);
		$("#like"+id).css({ "color": '#00b9bc'});
		
		$.ajax({
			method : "GET",
			url : 'likePost.notification',
			datatype : 'json',
			data: { 
				id : id
				
			},
			success : function(result) {
				$("#likeValue"+id).html(result);
				
			},
			statusCode : {
				500 : function(result) {
					
				}
			}
		});
		}
	
}

function deletePost(id){
	
	$.ajax({
		method : "GET",
		url : 'deletePost.notification',
		datatype : 'json',
		data: { 
			id : id
			
		},
		success : function(result) {
			location.reload(true);
			
		},
		statusCode : {
			500 : function(result) {
				
			}
		}
	});
}

function deleteComment(id , cid){
	
	$.ajax({
		method : "GET",
		url : 'deleteComment.notification',
		datatype : 'json',
		data: { 
			id : id,
			cid : cid
			
		},
		success : function(result) {
			$("#com"+id+cid).hide();
			var val = $("#commentValue"+id).data("value");
			$("#commentValue"+id).data("value",val-1);
			$("#commentValue"+id).html(val-1);
			
		},
		statusCode : {
			500 : function(result) {
				
			}
		}
	});
}


function saveComment(event,postId){
	
	if (event.keyCode === 13) {
		
		var message = document.getElementById("cmt"+postId).value;
		
		if(message.trim() != "")
		{
		$.ajax({
			method : "GET",
			url : 'saveComment.notification',
			datatype : 'json',
			data: { 
				postId : postId,
				message : message
			},
			success : function(result) {
				var html = '<span id="new'+postId+'"></span><div id=com'+postId+result+'><div class="row comment_row"><div class="col-xs-2 comment_by_img_container" style="width:30px;height:30px;"><img class="comment_by_img" src="'+doctorImage+'"/></div>'
				+'<div class="col-xs-10"><p class="ppl_comments"><span class="commentby_name">Dr. ${doctor.firstName} ${doctor.lastName}</span>  commented as '+message+' <span class="close pull-right"><a href="javascript:deleteComment('+postId+','+result+');" class="close" data-dismiss="alert" aria-label="close">&times;</a></span></p><span class="posted_date_time" style="padding-left:10px;position: relative;top: -5px;">Now</span></div></div></div>';
				$("#shift"+postId).html(html);
				$("#cmt"+postId).val('');
				
				$("#shift"+postId).attr('id', 'old'+postId);
				$("#new"+postId).attr('id', 'shift'+postId);
				
				
				var val = $("#commentValue"+postId).data("value");
				$("#commentValue"+postId).data("value",val+1);
				$("#commentValue"+postId).html(val+1);
				
			},
			statusCode : {
				500 : function(result) {
					
				}
			}
		});
		}
		
		
    } 	
}	
 


</script>
<style>


.ppl_comments{
	padding-left:10px;
}
	 .poster_name{  color: #333;
  }
.post_header{
background-color: rgb(255, 255, 255) !important;
    border-bottom: 1px solid rgba(128, 128, 128, 0.05) !important;
}

.posted_date_time {
    font-size: 0.75em;
    color: rgba(128, 128, 128, 0.69);

}

.comment_row{
background-color: rgba(158, 158, 158, 0.06);
    padding: 10px 0px;
}


.cmnt_form{
	border:none;
	height:30px;
}

.comment_by_img_container{
	width:40px;
	height:30px;
}

.comment_by_img{
	width:40px;
	height:30px;
}

.form-control{
	border-radius:0px;
}



		.nav-tabs>li {
    float: left;
    margin-bottom: -1px;
    width: 33%;
    text-align: center;
  
}


	.outer{
	border:;
	}
	
	.post_header{
		max-height:50px !important;
	}
	
	.post_area{
	border:none;
		border-radius:0px;
		background-color:f6f6f6;
	border:none;
	box-shadow: inset 0 0px 0px rgba(0, 0, 0, 0) !important;
	}
	
	
	.tab-content{
	margin:0px 15px;}
	
	
	
	.post_select{
	text-align:center;}
	
	
	.posted_date_time{
		font-size:0.75em;
	}
	
	
	.poster_image{
	    width: 35px;
	}
	
	
	</style>
	<div class="h_30"></div>
	<div class="container-fluid" style="margin-top: 4%;">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="row clearfix">
					<div class="col-sm-5  col-md-3 column  a_left">
					 	 
					 	<ul class="left_aside bg_w">
					 	
					 	<li> <button type="button" class="btn btn-link btn-sm" id="post"><span class="glyphicon glyphicon-pencil"></span> &nbsp; Write a Post </button>  </li>
			
					 	
					 	
					 	</ul> 
					 	 
					 
					 </div>
					<div class="col-sm-7 col-md-5 column">
			<c:if test="${not empty order}">
						<div class="row">	
					<div class="col-sm-12 column ">
					
					<div class="alert alert-danger fade in">
							    <a href="#" class="close alert_close" data-dismiss="alert" aria-label="close">&times;</a>
							    <strong></strong> ${order}
  					</div>
					
					
					
                    </div> 
					</div>	
					
				</c:if>
				<c:if test="${not empty error}">
						<div class="row">	
					<div class="col-sm-12 column ">
					
					<div class="alert alert-danger fade in">
							    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
							    <strong></strong> ${error}
  					</div>
					
					
					
                    </div> 
					</div>	
					
				</c:if>
						
				<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12 column">
						<div class="panel panel-default">
							<div class="panel-heading" style="background-color:white;" >
								<div class="panel-title"><h5>Today's Appointments<span class="pull-right glyphicon glyphicon-list-alt" style="color:;"></span></h5></div>
								
								
							</div>	
						</div>
						<c:if test="${empty appointments}">
						<div class="row">	
					<div class="col-sm-12 column ">
					
					<div class="alert alert-danger fade in">
							    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
							    <strong></strong> There are no Appointments.
  					</div>
					
					
					
                    </div> 
					</div>	
						</c:if>
						<c:if test="${not empty appointments}">
							<c:forEach var="appointment" items="${appointments}">

<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="false">
  <div class="panel panel-default">
    <div class="panel-heading presctitle" role="tab" id="heading${appointment.id}" style="background-color:white;color:black;">
      
        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#${appointment.id}" aria-expanded="false" aria-controls="${prescription.id}" style="background-color:white;color:black;">
          <c:if test="${not empty appointment.patientName}">
												
											<span class="glyphicon glyphicon-edit"></span>  Appointment by ${appointment.patientName} from ${appointment.startTime} to ${appointment.endTime}
												
											
										</c:if>
										<c:if test="${empty appointment.patientName}">
											<span class="glyphicon glyphicon-edit"></span>	Personal Appointment from ${appointment.startTime} to ${appointment.endTime}
										</c:if>
        </a>
    
    </div>
    <div id="${appointment.id}" class="accordion-body collapse" role="tabpanel" aria-labelledby="heading${appointment.id}">
      <div class="panel-body ">
      <c:if test="${not empty appointment.patientName}">
											<div class="row" style="padding: 8px">
												<div class="col-xs-4 col-sm-4 col-md-4 column">Patient
													Name</div>
												<div class="col-xs-8 col-sm-8 col-md-8 column"
													style="word-wrap: break-word;">
													<a
														href="doctorViewPatient.profile?id=${appointment.patientId}">${appointment.patientName}</a>
												</div>
											</div>
										</c:if>

										

										<div class="row" style="padding: 8px">
											<div class="col-xs-4 col-sm-4 col-md-4 column ">Title
											</div>
											<div class="col-xs-8 col-sm-8 col-md-8 column"
												style="word-wrap: break-word;">
												${appointment.title}
											</div>
										</div>


										<div class="row" style="padding: 8px">
											<div class="col-xs-4 col-sm-4 col-md-4 column">Description
											</div>
											<div class="col-xs-8 col-sm-8 col-md-8 column"
												style="word-wrap: break-word;">
											${appointment.description}
											</div>
										</div>	
										<c:if test="${not empty appointment.patientName}">
										<div class="row" style="padding: 8px">
											<div class="col-xs-4 col-sm-4 col-md-4 column">Symptom
											</div>
											<div class="col-xs-8 col-sm-8 col-md-8 column"
												style="word-wrap: break-word;">
											${appointment.symptom}
											</div>
										</div>	
										
										<div class="row" style="padding: 8px">
											<div class="col-xs-4 col-sm-4 col-md-4 column">Status
											</div>
											<div class="col-xs-8 col-sm-8 col-md-8 column"
												style="word-wrap: break-word;">
											<c:if test="${appointment.status == 0}">
											Not Approved By you
											</c:if>
											<c:if test="${appointment.status == 1}">
											Approved
											</c:if>
											</div>
										</div>	
										</c:if>		
										<div class="row" style="padding: 8px">
											<div class="col-xs-4 col-sm-4 col-md-4 column">Start Time
											</div>
											<div class="col-xs-8 col-sm-8 col-md-8 column"
												style="word-wrap: break-word;">
											${appointment.startTime}
											</div>
										</div>	
										<div class="row" style="padding: 8px">
											<div class="col-xs-4 col-sm-4 col-md-4 column">End Time
											</div>
											<div class="col-xs-8 col-sm-8 col-md-8 column"
												style="word-wrap: break-word;">
											${appointment.endTime}
											</div>
										</div>						
      </div>
    </div>
  </div>
</div>
								

							</c:forEach>
						</c:if>

					</div>
					</div>		
						
						
					<div class="row" id="postlength">
					
						
					</div>				
					</div>

					
					<%@ include file="doctorAside.jsp" %>
				</div>


</div>
</div>
</div>

<div class="modal fade" id="addPost" role="dialog" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" id="close" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Add new Post</h4>
				</div>
				<div class="modal-body">
					
					<ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="active left_10"><a href="#home" aria-controls="home" role="tab" data-toggle="tab"> Post</a></li>
    <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">Image</a></li>
    <li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">Video</a></li>
  </ul>
					<div class="tab-content">
						<div role="tabpanel" class="tab-pane fade in active" id="home">
							<div class="row">
										<form action="addPost.doctor" method="post" class="form-horizontal" enctype="multipart/form-data">
													
												
													
			   											
										     <input type="text" name="postType" class="hidden" value="1">
											<textarea class="form-control  post_area" rows="4" cols="3"
												placeholder="Share your thoughts here" name="status" required></textarea>
			
			<hr/>
			
					<div class="row">
												<div class="col-xs-3" style="text-align:right">
												<i class="fa fa-user-plus" aria-hidden="true" style="font-size:30px"></i>
												</div>
												<div class="col-xs-6">	
													<select class="form-control"
													 name="privacy" data-toggle="tooltip" title="Share With" style="border:none;">
													<option value="1">Friends</option>
			   										<option value="2">Public</option>
			   										
													</select>
													</div>
													<div class="col-xs-3">
													<input type="submit" class="btn btn-success btn-sm btn-block" value="Submit" >
													</div>
													
					</div>
			
			
										
									</form>	
							</div>
						</div>
						<div role="tabpanel" class="tab-pane fade" id="profile">
						
							<div class="row">
							<form action="addPost.doctor" method="post" class="form-horizontal"  enctype="multipart/form-data">
										
										
								<input type="text" name="postType" class="hidden" value="2">
								<textarea placeholder="Say something about the image" class="form-control post_area" rows="4" cols="3"
									 title="What's important?" name="status" required></textarea>
								
								
								
								<hr/>
								<div class="row">
								               <div class="col-xs-4"> 
								               	<input type="file" class="" name="file"accept="image/*"/>
								               
								               </div>
												<div class="col-xs-1" style="text-align:right">
												<i class="fa fa-user-plus pull-right" aria-hidden="true" style="font-size:30px"></i>
												</div>
												<div class="col-xs-4">	
													<select class="form-control"
													 name="privacy" data-toggle="tooltip" title="Share With" style="border:none;">
													<option value="1">Friends</option>
			   										<option value="2">Public</option>
			   										
													</select>
													</div>
													<div class="col-xs-3">
													<input type="submit" class="btn btn-success btn-sm btn-block" value="Submit" >
													</div>
													
								</div>
			
								
								
							</form>
							</div>

						</div>
						<div role="tabpanel" class="tab-pane fade" id="messages">
							<div class="row">
							<form action="addPost.doctor" method="post" class="form-horizontal"  enctype="multipart/form-data">
							<input type="text" name="postType" class="hidden" value="3">
							
								<span> <input type="text" name="videourl" class="form-control  post_area" placeholder="video URL" > </span>		
								<textarea class="form-control  post_area" rows="4" cols="3"
									placeholder="Say something about this Video" title="What's important?" name="status" required></textarea>
								
								
								<hr/>
								<div class="row">
								               <div class="col-xs-4"> 
								               	<input type="file" class="" name="file" accept="video/*"/>
								               
								               </div>
												<div class="col-xs-1" style="text-align:right">
												<i class="fa fa-user-plus" aria-hidden="true" style="font-size:30px"></i>
												</div>
												<div class="col-xs-4">	
													<select class="form-control"
													 name="privacy" data-toggle="tooltip" title="Share With" style="border:none;">
													<option value="1">Friends</option>
			   										<option value="2">Public</option>
			   										
													</select>
													</div>
													<div class="col-xs-3">
													<input type="submit" class="btn btn-success btn-sm btn-block" value="Submit" >
													</div>
													
								</div>
									
</form>
							</div>
						</div>

					</div>

				</div>
			</div>

		</div>
	</div>


				<script src="js/bootstrap.min.js">
				</script>
</body>
</html>










