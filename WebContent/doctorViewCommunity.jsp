<%@ include file="doctorHeader.jsp" %>
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

.w300{
width:70%;
} 

	</style>
<script>
var postCount = 0;
var repeat = 0;
var post ;
var doctorImage = $("#profileImage").attr('src');
var doctorId = "${doctor.id}";
var finish = 0;
var communityId = "${community.id}";
var communityOwner = "${community.doctorId}";
var title = "${community.title}";
var admin = 0;
if(communityOwner == doctorId){
	admin = 1;
}

$( document ).ready(function() {

	$("#post").click(function() {
		$("#addPost").modal();
	});
	
	$("#edit").click(function() {
		$("#editTitle").modal();
	});
	
	
	 var insert = "${insert}";
		if(insert == 1)
			{
				window.history.pushState("", "", 'getCommunity.community?id='+communityId);
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
			url : 'getCommunityPosts.notification',
			datatype : 'json',
			data: { 
				id : communityId
				
			},
			success : function(result) {
				if(result!="")
					{
					 post = $.parseJSON(result);
					 d = post;
					
					 var html = "";
					for ( var i = 0; i < d.length; i++) {
						
						if(postCount <= 10)
							{
							 var creator=0;
							html = html + '<div class="col-md-12 column"><div class="panel panel-default"><div class="panel-heading post_header"><div class="row"><div class="col-xs-3 col-md-2 poster_image">'
								+'<img src="'+d[i].image+'" style="width:30px; height:30px;"/></div><div class="col-xs-8 col-md-9"><span class="poster_name">'
								if(admin == 1){
									creator = 1;
									if(d[i].doctorId != doctorId){
										html = html +'<a href="doctorViewDoctor.profile?id='+d[i].doctorId+'">'+ d[i].doctorName+'</a></span><span class="pull-right"><div class="dropdown"><button class="btn dropdown-toggle btn-delp" type="button" data-toggle="dropdown"><span class="caret"></span></button><ul class="dropdown-menu dropdown_delp"><li><a href="javascript:deletePost('+d[i].id+');">Delete Post</a></li></ul></div></span><br/>';
									}
									else
										{
										creator = 1;
										html = html + d[i].doctorName+'</span> <span class="pull-right"><div class="dropdown"><button class="btn dropdown-toggle btn-delp" type="button" data-toggle="dropdown"><span class="caret"></span></button><ul class="dropdown-menu dropdown_delp"><li><a href="javascript:deletePost('+d[i].id+');">Delete Post</a></li></ul></div></span>'
										+'<br/>';
										}
								}
								if(admin == 0){
								if(d[i].doctorId != doctorId){
									html = html +'<a href="doctorViewDoctor.profile?id='+d[i].doctorId+'">'+ d[i].doctorName+'</a></span><br/>';
								}
								else
									{
									creator = 1;
									html = html + d[i].doctorName+'</span> <span class="pull-right"><div class="dropdown"><button class="btn dropdown-toggle btn-delp" type="button" data-toggle="dropdown"><span class="caret"></span></button><ul class="dropdown-menu dropdown_delp"><li><a href="javascript:deletePost('+d[i].id+');">Delete Post</a></li></ul></div></span>'
									+'<br/>';
									}
								}
								html = html +'<span class="posted_date_time">'+d[i].creationDate+'</span></div></div></div><div class="panel-body" style="padding-bottom:0px;"><h5 class="post_title">'+d[i].status+'</h5>';
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
					 var creator=0;
					html = html + '<div class="col-md-12 column"><div class="panel panel-default"><div class="panel-heading post_header"><div class="row"><div class="col-xs-3 col-md-2 poster_image">'
					+'<img src="'+d[i].image+'" style="width:30px; height:30px;"/></div><div class="col-xs-8 col-md-9"><span class="poster_name">'
					
					if(admin == 1){
						creator = 1;
						if(d[i].doctorId != doctorId){
							html = html +'<a href="doctorViewDoctor.profile?id='+d[i].doctorId+'">'+ d[i].doctorName+'</a></span><span class="pull-right"><div class="dropdown"><button class="btn dropdown-toggle btn-delp" type="button" data-toggle="dropdown"><span class="caret"></span></button><ul class="dropdown-menu dropdown_delp"><li><a href="javascript:deletePost('+d[i].id+');">Delete Post</a></li></ul></div></span><br/>';
						}
						else
							{
							creator = 1;
							html = html + d[i].doctorName+'</span> <span class="pull-right"><div class="dropdown"><button class="btn dropdown-toggle btn-delp" type="button" data-toggle="dropdown"><span class="caret"></span></button><ul class="dropdown-menu dropdown_delp"><li><a href="javascript:deletePost('+d[i].id+');">Delete Post</a></li></ul></div></span>'
							+'<br/>';
							}
					}
					if(admin == 0){
					if(d[i].doctorId != doctorId){
						html = html +'<a href="doctorViewDoctor.profile?id='+d[i].doctorId+'">'+ d[i].doctorName+'</a></span><br/>';
					}
					else
						{
						creator = 1;
						html = html + d[i].doctorName+'</span> <span class="pull-right"><div class="dropdown"><button class="btn dropdown-toggle btn-delp" type="button" data-toggle="dropdown"><span class="caret"></span></button><ul class="dropdown-menu dropdown_delp"><li><a href="javascript:deletePost('+d[i].id+');">Delete Post</a></li></ul></div></span>'
						+'<br/>';
						}
					}
					html = html +'<span class="posted_date_time">'+d[i].creationDate+'</span></div></div></div><div class="panel-body" style="padding-bottom:0px;"><h5 class="post_title">'+d[i].status+'</h5>';
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
 
function addDoctor(){

	var members= $('select#addMember').val();
	alert(members);
	if(members!=null){
		$.ajax({
			method : "GET",
			url : 'addMember.notification',
			datatype : 'json',
			data: { 
				communityId : communityId,
				members : members,
				title : title
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
	
}

function addDoctor(){

	var members= $('select#addMember').val();
	
	if(members!=null){
		$.ajax({
			method : "GET",
			url : 'addMember.notification',
			datatype : 'json',
			data: { 
				communityId : communityId,
				members : members,
				title : title
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
	
}

function removeDoctor(){

	var members= $('select#removeMember').val();
	
	if(members!=null){
		$.ajax({
			method : "GET",
			url : 'removeMember.notification',
			datatype : 'json',
			data: { 
				communityId : communityId,
				members : members,
				title : title
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
	<div class="h_30"></div>
<c:if test="${community.doctorId == doctor.id}">
<c:set var="admin" value="1"/>
</c:if>
<img  src="${community.filePath}" id="communityPic" class="hidden" onError="this.onerror=null;this.src='image/d.jpg';"/> 
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
					
					<div class="community_header" id="cPic" style="background-color:#fff;color:#fff;">
					<img src="${community.filePath}" width="100%">
					
					<h5 style="background-color:#00b9bc;color:#fff;padding:10px;position:relative;top:-20px;">${community.title}</h5>
					
					<c:if test="${admin == 1}">
					<span class="pull-right">
					
					<button type="button" class="btn btn-link" id="edit" style="    position: relative;
    top: -60px;"> <span class="glyphicon glyphicon-pencil" id="edit"></span></button>
					
					</span>
					</c:if>
					</div>
					
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
						
					
						
						
					<div class="row" id="postlength">
					
						
					</div>				
					</div>

					<div class="col-sm-6 col-md-4">
					
					   <div class="list-group"
							style='overflow: auto; min-width: 300px; max-width: 550px; max-height: 400px;'>
							<c:if test="${admin == 1}">
							<div class="list-group-item">
							<c:if test="${empty doctors}">
							<h5 class="listgroupheader"> 
								No Doctors are Available to Add </h5>
							</c:if>
							<c:if test="${not empty doctors}">
							<h5 class="listgroupheader"> 
								Add Members</h5>
								 <select multiple="multiple" class="w300" name="doctors" id="addMember" required>
					
										<c:forEach var="d" items="${doctors}">
											<c:if test="${not empty d}">
        				<option value="${d.id}">Dr. ${d.firstName} &nbsp; ${d.lastName} </option>
        				</c:if>
        				</c:forEach>
        				
   					 </select>
							<button type="button" class="btn btn-link" onclick="addDoctor();">Update</button>	
							</c:if>
							</div>
							
							<div class="list-group-item">
							<c:if test="${empty community.doctors}">
							<h5 class="listgroupheader"> 
								No Doctors are Available to Remove </h5>
							</c:if>
							<c:if test="${not empty community.doctors}">
							<h5 class="listgroupheader"> 
								Remove Members</h5>
								 <select multiple="multiple" class="w300 " name="doctors" id="removeMember" required>
					
										<c:forEach var="d" items="${community.doctors}">
											<c:if test="${not empty d}">
											<c:if test="${d.id != doctor.id}">
        				<option value="${d.id}">Dr. ${d.firstName} &nbsp; ${d.lastName} </option>
        				</c:if>
        				</c:if>
        				</c:forEach>
        				
   					 </select>
							<button type="button" class="btn btn-link" onclick="removeDoctor();">Update</button>	
							</c:if>
							</div>
							
					        </c:if>
					        <div class="list-group-item">

								<div class="row">
								
								<div class="col-xs-12">
								<h5 class="listgroupheader"> 
								 Description</h5>
					           <p>${community.description}</p>
					            </div>
					            </div>
					        </div>
					    </div>        
					      
					      
					     <!-- -------- doctors -->
						

						<div class="list-group">
							
							<!--<a href="#" class="list-group-item "><strong>Patients in your network</strong></a-->
							<div class="list-group-item">
							<h5 class="listgroupheader">Members Of this Community  <span class="pull-right" style="font-size:10px;"> ${fn:length(community.doctors)}  members</span></h5>
								
							</div>
							<div class="list-group-item">

								<div class="row">
									<c:if test="${not empty community.doctors}">
										<c:forEach var="doctor1" items="${community.doctors}">
											<c:if test="${not empty doctor1}">
												<div class="col-xs-4 col-sm-4 col-md-4">
													<div class="thumbnail" style="position:relative;">
														<img alt="300x200" src="${doctor1.image}" style="width:100px;height:80px;" onError="this.onerror=null;this.src='image/d.jpg';" />


														<p style="text-align: center; background-color: #f8f8f8;position:absolute;bottom:0px;word-wrap:break-word;font-size:12px;text-align:center;margin:2px auto;width:92%;">
														<%-- <span id="${doctor1.id}"><span class="glyphicon glyphicon-one-fine-dot" style="color:green;" ></span></span> --%>
														<c:if test="${doctor1.id != doctor.id}">
															<a  href="doctorViewDoctor.profile?id=${doctor1.id}"
																id="pn"> Dr. ${doctor1.firstName}&nbsp;${doctor1.lastName}</a> 
																</c:if>
																<c:if test="${doctor1.id == doctor.id}">
															<%-- ${doctor1.firstName}&nbsp;${doctor1.lastName}  --%>You
																</c:if>
														</p>

													</div>
												</div>

											</c:if>

										</c:forEach>
									</c:if>
									<c:if test="${empty community.doctors}">
										<p style="padding-left:10px;padding-right:10px;">
											No Doctors available 
											</p>
									</c:if>


								</div>

							</div>

						</div>
						
						
						<!-- -------- doctors close -->
						  
					            
					
					</div>
					
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
										<form action="addPost.community" method="post" class="form-horizontal" enctype="multipart/form-data">
													
												
													
			   								<input type="text" name="communityId" class="hidden" value="${community.id}">			
										     <input type="text" name="postType" class="hidden" value="1">
											<textarea class="form-control  post_area" rows="4" cols="3"
												placeholder="Share your thoughts here" name="status" required></textarea>
			
			<hr/>
			
					<div class="row">
												
												
													<div class="col-xs-12">
													<input type="submit" class="btn btn-success pull-right btn-sm  pad_l_r_15" value="Submit" >
													</div>
													
					</div>
			
			
										
									</form>	
							</div>
						</div>
						<div role="tabpanel" class="tab-pane fade" id="profile">
						
							<div class="row">
							<form action="addPost.community" method="post" class="form-horizontal"  enctype="multipart/form-data">
										
								<input type="text" name="communityId" class="hidden" value="${community.id}">		
								<input type="text" name="postType" class="hidden" value="2">
								<textarea placeholder="Say something about the image" class="form-control post_area" rows="4" cols="3"
									 title="What's important?" name="status" required></textarea>
								
								
								
								<hr/>
								<div class="row">
								               <div class="col-xs-4"> 
								               	<input type="file" class="" name="file" accept="image/*"/>
								               
								               </div>
												
												
													<div class="col-xs-8">
													<input type="submit" class="btn btn-success  pull-right btn-sm  pad_l_r_15" value="Submit" >
													</div>
													
								</div>
							</form>
							</div>

						</div>
						<div role="tabpanel" class="tab-pane fade" id="messages">
							<div class="row">
							<form action="addPost.community" method="post" class="form-horizontal"  enctype="multipart/form-data">
							<input type="text" name="postType" class="hidden" value="3">
							<input type="text" name="communityId" class="hidden" value="${community.id}">
								<span> <input type="text" name="videourl" class="form-control  post_area" placeholder="video URL" > </span>		
								<textarea class="form-control  post_area" rows="4" cols="3"
									placeholder="Say something about this Video" title="What's important?" name="status" required></textarea>
								
								
								<hr/>
								<div class="row">
								               <div class="col-xs-4"> 
								               	<input type="file" class="" name="file" accept="video/*"/>
								               
								               </div>
												
												<div class="col-xs-8">
													<input type="submit" class="btn btn-success  pull-right  btn-sm  pad_l_r_15" value="Submit" >
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
	
	<div class="modal fade" id="editTitle" role="dialog" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" id="close" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Edit your Community Details</h4>
				</div>
				<div class="modal-body">
					<form action="editCommunity.community" method="post" class="form-horizontal" enctype="multipart/form-data">
					<input type="text" name="communityId" class="hidden" value="${community.id}">
					<table class="table" style="margin-top: 5%;">

								<tr>
									<td class="field-label col-xs-3 active"><label>
											Title </label></td>
									<td class="col-md-9"><input id="title"  class="form-control input-sm"
										type="text" name="title" value="${community.title}" required/></td>
								</tr>
								<tr>
									<td class="field-label col-xs-3 active"><label>
											Description </label></td>
									<td class="col-md-9"><input id="description" class="form-control input-sm"  name="description" type="text" data-toggle="tooltip" data-placement="top"   value="${community.description}" required/></td>
								</tr>
								
								<tr>
									<td class="field-label col-xs-3 active"><label>
											Community Image </label></td>
									<td class="col-md-9"><input class="" type="file" name="file" accept="image/*" ></td>
								</tr>
								
								
							</table>
							
							<div style="text-align:right;">
							<button type="button" class="btn btn-default btn btn-default btn-info btn-sm" data-dismiss="modal">Close</button> <input class="btn btn-info btn-sm" type="submit"
								value="Save">
</div>
						
					</form>
				</div>
			</div>

		</div>
	</div>


				<script src="js/bootstrap.min.js">
				</script>
				<script src="js/multiple-select.js"></script>
    <script>
        $('select').multipleSelect();
        /* $( document ).ready(function() {
        	debugger
        	var communityImage = $("#communityPic").attr('src');
        	$("#cPic").css('background','url('+communityImage+')');
        	
        }); */
        	
    </script>
</body>
</html>










