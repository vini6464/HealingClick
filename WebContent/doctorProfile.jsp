<%@ include file="doctorHeader.jsp" %>

<script>
function preventBackButton(){window.history.forward();}
setTimeout("preventBackButton()", 0);
window.onunload=function(){null};
</script>


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
			url : 'getDoctorPosts.notification',
			data: { 
				id : doctorId
				
			},
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
									html = html + d[i].doctorName+'</span> <span class="pull-right"><div class="dropdown"><button class="btn dropdown-toggle btn-delp" type="button" data-toggle="dropdown"><span class="caret"></span></button><ul class="dropdown-menu dropdown_delp"><li><a href="javascript:deletePost('+d[i].id+');">Delete Post</a></li></ul></div></span>'
									+'<br/>';
									}
								html = html +'<span class="posted_date_time">'+d[i].creationDate+'</span></div></div></div><div class="panel-body"><h5 class="post_title">'+d[i].status+'</h5>';
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
					html = html +'<span class="posted_date_time">'+d[i].creationDate+'</span></div></div></div><div class="panel-body"><h5 class="post_title">'+d[i].status+'</h5>';
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
background-color: rgba(158, 158, 158, 0.08);
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
<style>
.profpd{padding:11px;padding-left:20px;}
.profpbelowimage{margin-top:2%;width:92%;}
.profpheader{font-size:1.2em;font-weight:bold;letter-spacing:0.8px;}
.profplabel{background-color:#f5fafe;padding:7px;}
.pfilldetailsinput{border-bottom:1px solid grey;border-top:none;border-left:none;border-right:none;}

.filldetailsheading{padding:10px;font-size:1.1em;letter-spacing:1px;}
.peditbtn{max-width:300px;min-width:200px;font-weight:bold;}

</style>


	<div class="container-fluid profpbelowimage" >
	<div class="row clearfix" >
		
		 
			
			
		
				
				<div class="col-xs-12 col-sm-10 col-md-8 column col-md-offset-2 col-sm-offset-1" style="word-wrap:break-word;">
				
				<h4 class="text-center profpheader"> YOUR PROFILE</h4>
						
						<div class="panel panel-default">
						<div class="panel-body">
							 
							   <a href="doctorEdit.profile" class="btn  btn-warning btn-xs pull-right" type="button" >Edit Profile</a>
							
			</div>					
				  
			<div class="row profpd" >
               <div class="col-xs-4 col-sm-3 col-md-3  column">	Name:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column" >${doctor.firstName} ${doctor.lastName}</div>
            </div>			 
			<div class="row profpd" >
               <div class="col-xs-4 col-sm-3 col-md-3  column">	Gender:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column" >${gender}</div>
            </div>	
           	    
			<div class="row profpd" >
               <div class="col-xs-4 col-sm-3 col-md-3  column">Date Of Birth:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column" >${doctor.dob}</div>
            </div>	
          	 
			<div class="row profpd" >
               <div class="col-xs-4 col-sm-3 col-md-3  column">Blood Group:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column" ><c:if test="${doctor.bloodGroup == 1}">A+</c:if>
             <c:if test="${doctor.bloodGroup == 2}">A-</c:if>
             <c:if test="${doctor.bloodGroup == 3}">B+</c:if>
             <c:if test="${doctor.bloodGroup == 4}">B-</c:if>
             <c:if test="${doctor.bloodGroup == 5}">O+</c:if>
             <c:if test="${doctor.bloodGroup == 6}">O-</c:if>
             <c:if test="${doctor.bloodGroup == 7}">AB+</c:if>
             <c:if test="${doctor.bloodGroup == 8}">AB-</c:if></div>
            </div>		
	  <div class="row profpd" >
               <div class="col-xs-4 col-sm-3 col-md-3  column">Qualification:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column" >${doctor.qualification}</div>
            </div>
            <div class="row profpd" >
               <div class="col-xs-4 col-sm-3 col-md-3  column">Speciality:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column" >${doctor.speciality}</div>
            </div>
            <div class="row profpd" >
               <div class="col-xs-4 col-sm-3 col-md-3  column">Work Location:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column" >${doctor.workLocation}</div>
            </div>
			<div class="row profpd" >
               <div class="col-xs-4 col-sm-3 col-md-3  column">Address:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column" >${doctor.landMark}<br>${doctor.address1}<br>${doctor.address2}<br>${doctor.city} - ${doctor.pinCode}<br>${doctor.state}<br>${doctor.country}</div>
            </div>			 
			<div class="row profpd" >
               <div class="col-xs-4 col-sm-3 col-md-3  column">Contact Number:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column" >${doctor.mobile}</div>
            </div>
            <div class="row profpd" >
               <div class="col-xs-4 col-sm-3 col-md-3  column">Landline Number:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column" >${doctor.landLine}</div>
            </div>			 		
	   
			<div class="row profpd" >
               <div class="col-xs-4 col-sm-3 col-md-3  column"> Email:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column" >${doctor.emailId}</div>
            </div>	
            
			<div class="row profpd" >
               <div class="col-xs-4 col-sm-3 col-md-3  column">About ${patient.firstName}:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column" >${doctor.aboutMe}</div>
            </div>						
					</div>
					</div>
					

				
			</div>


</div>



			<script src="js/bootstrap.min.js"></script>
</body>
</html>