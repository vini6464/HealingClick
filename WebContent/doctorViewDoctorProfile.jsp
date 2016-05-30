<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>HealingClick</title>
<link rel="icon" type="image/x-icon" href="image/healingclick_icon.png" />
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<script src="js/jquery.js" type="text/javascript"></script>
	
<script type="text/javascript" src="js/ajax.js"></script>
<%@ include file="doctorchat.jsp" %>

	<script>
function preventBackButton(){window.history.forward();}
setTimeout("preventBackButton()", 0);
window.onunload=function(){null};
</script>

<link href="css/basic.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.2/modernizr.js"></script>
<script type='text/javascript' src='js/loadImg.js'></script>
<script type='text/javascript'>
    $(function(){
        $('img').imgPreload();
    })
</script>


	<script>
function preventBackButton(){window.history.forward();}
setTimeout("preventBackButton()", 0);
window.onunload=function(){null};

//paste this code under the head tag or in a separate js file.
// Wait for window load
$(window).load(function() {
	// Animate loader off screen
	$(".se-pre-con").fadeOut("slow");;
});
</script>
<style>
  .images ul li img {
	width: 400px;
	height: 266px;
}
.images ul li {
	display: inline-block;
}
/* Paste this css to your style sheet file or under head tag */
/* This only works with JavaScript, 
if it's not present, don't show loader */
.no-js #loader { display: none;  }
.js #loader { display: block; position: absolute; left: 100px; top: 0; }
.se-pre-con {
	position: fixed;
	left: 0px;
	top: 0px;
	width: 100%;
	height: 100%;
	z-index: 9999;
	background: url(image/Preloader_2.gif) center no-repeat #fff;
}
</style>

<script type="text/javascript">

	$(function() {
		$("#notify").click(function() {
			$.ajax({
				method : "GET",
				url : 'clearNotification.notification',
				success : function(result) {
					$("#notificationcount").addClass("hidden");
					
				},
				statusCode : {
					500 : function(result) {
					
					}
				}
			});
		});
	});
	
	$(function() {
		$("#close").click(function() {
			location.reload(true);
		});
	});
	
	$(function() {
		$("#friend").click(function() {

			$.ajax({
				method : "GET",
				url : 'clearRequest.notification',
				success : function(result) {

					$("#requestcount").addClass("hidden");
				},
				statusCode : {
					500 : function(result) {
						
					}
				}
			});

		});

	});
	
	
	
	function getNewNotification() {
		
		$.ajax({
			method : "GET",
			url : 'getNotification.notification',
			datatype : 'json',
			success : function(result) {
				var html = "<li><a href='#'>Notifications</a> </li><li class='divider'> </li>";
				var count = 0;
				var d = $.parseJSON(result);
				for ( var i = 0; i < d.length; i++) {
					if (d[i].hasRead == 0) {
						count++;
					}

					html = html + "<li><a href='#'><div class='row' style='padding-left:5px;'>"+
					"<div class='col-xs-4 col-md-3 column' style='width:60px;' > <image src='"+d[i].image +"' width='50px' height='50px'></div>"+
					"<div class='col-xs-8 col-md-9 column' style='font-size:0.85em;padding-right:20px;'> "+d[i].content +" <br> <span style='color:grey;font-size:0.8em;padding-top:10px;'>"+d[i].creationDate+"</span></div></div></a></li><li class='divider'> </li>";
				}
				if (count != 0) {
					$("#notificationcount").removeClass("hidden");
					$("#notificationcount").html(count);
				}
				else
					{
					
					}
				$("#notification").html(html);
			},
			statusCode : {
				500 : function(result) {
					alert(result);
				}
			}
		});

	}

	function getNewRequest(){
						$
								.ajax({
									method : "GET",
									url : 'getRequest.notification',
									datatype : 'json',
									success : function(result) {

										var html = "<li><a href='#'>Requests</a> </li><li class='divider'> </li>";
										var count = 0;

										var d = $.parseJSON(result);
										for ( var i = 0; i < d.length; i++) {
											debugger
											if (d[i].view == 0) {
												count++;
											}
											
											if(d[i].type == 1)
												{
												html = html
												+ '<li><div id="'+d[i].id+'" style="text-align:center;">'
												
												+ '<div class="row"'+
												'style="min-width: 350px; max-width: 400px;">'
												+ '<div class="col-xs-4 col-sm-2 column">'
												+ '<img alt="300x200" src="'+d[i].image+'" width="50px;"'+
							'height="50px;" />'
												+ '</div>'
												+ '<div id="req" style="color:;">'
												+ '<div class="col-xs-4 col-md-4 column">'
												+ '<a href="doctorViewPatient.profile?id='
												+ d[i].id
												+ '" id="">Dr.'
												+ d[i].firstName
												+ '&nbsp; '
												+ d[i].lastName
												+ ' </a> '
												+ '</div>'
												+ '<div class="col-xs-2 col-md-3 column">'
												+ '<a href="#" class="btn btn-success btn-sm"'
												+ 'type="button" style="" onclick="acceptDoctor('
												+ d[i].id
												+ ');">Accept</a>'
												+ '</div>'
												+ '<div class="col-xs-2 col-md-3 column">'
												+ '<a class="btn btn-info btn-warning btn-sm" type="button"'
												+ 'style="width: %;" onclick="declineDoctor('
												+ d[i].id
												+ ');">Decline</a>'
												+ '</div>' + '</div>'
												+ '</div>' 
												+ '</div></li><li class="divider"> </li>';
												}
											else
												{
												html = html
												+ '<li><div id="'+d[i].id+'">'
												
												+ '<div class="row"'+
												'style="min-width: 350px; max-width: 400px;">'
												+ '<div class="col-xs-4 col-sm-2 column">'
												+ '<img alt="300x200" src="'+d[i].image+'" width="50px;"'+
							'height="50px;" />'
												+ '</div>'
												+ '<div id="req" style="color:;">'
												+ '<div class="col-xs-4 col-md-4 column">'
												+ '<a href="doctorViewPatient.profile?id='
												+ d[i].id
												+ '" id="">'
												+ d[i].firstName
												+ '&nbsp; '
												+ d[i].lastName
												+ ' </a> '
												+ '</div>'
												+ '<div class="col-xs-2 col-md-3 column">'
												+ '<a href="#" class="btn btn-success btn-sm"'
												+ 'type="button" style="" onclick="accepted('
												+ d[i].id
												+ ');">Accept</a>'
												+ '</div>'
												+ '<div class="col-xs-2 col-md-3 column">'
												+ '<a class="btn btn-info btn-warning btn-sm" type="button"'
												+ 'style="width: %;" onclick="decline('
												+ d[i].id
												+ ');">Decline</a>'
												+ '</div>' + '</div>'
												+ '</div>' 
												+ '</div></li><li class="divider"> </li>';
												}
											
										}
										
										if (count != 0) {
											$("#requestcount").removeClass("hidden");
											$("#requestcount").html(count);
											$("#request").html(html);
										}
										else
											{
											
											if(html == "")
												{
												$("#request").html("<li> <a href='#'>No Request</a></li>");
												}
											else
												{
												$("#request").html(html);
												}
											
											}
										
									},
									statusCode : {
										500 : function(result) {
											alert(result);
										}
									}
								});

	}
	
	//Call the yourAjaxCall() function every 1000 millisecond
	setInterval("getNewRequest()",1000 * 60 );
	setInterval("getNewNotification()",1000 * 60);
	


	$(document).ready(function() {
		
		getNewRequest();
		getNewNotification();


	});
</script>

<script>
var postCount = 0;
var repeat = 0;
var post ;
var doctorImage = $("#profileImage").attr('src');
var doctorId = "${doctor.id}";
var doctorId1 = "${doctor1.id}";
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
				id : doctorId1
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
								
								
								html = html +'<a href="doctorViewDoctor.profile?id='+d[i].doctorId+'">'+ d[i].doctorName+'</a></span><br/>';
								
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
					
						html = html +'<a href="doctorViewDoctor.profile?id='+d[i].doctorId+'">'+ d[i].doctorName+'</a></span><br/>';
					
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
  body{font-family: 'Josefin Sans', sans-serif;
  background-color: #f6f6f6;}
</style>
</head>
<body>
<div class="se-pre-con"></div>
<div class="container-fluid" >
   <div class="row  text-center" style="background-color:white;">
    <div class="col-xs-5 col-md-2 column ">
     <img class="pull-left" src="image/healingclick_logo.png" alt="logo" style="padding-top:5px;padding-left:10px;"/>
      </div>
    <div class="col-xs-7 col-sm-6 col-md-3 col-md-push-7" style="">
      
       <ul class="nav nright nav-pills">
						<li class="name dropdown pull-right ">
							 <a href="#" data-toggle="dropdown" class="dropdown-toggle" style="color:white;background-color:#00b9bc;margin-right:-15px;"> ${doctor.userName}<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li><a href="settings.login" style="white-space: inherit;">Settings   <span class="glyphicon glyphicon-cog pull-right"></span></a></li>
								<li><a href="doctor.profile" style="white-space: inherit;">Profile <span class="glyphicon glyphicon-user pull-right"></span></a></li>
								<li><a href="support.support" style="white-space: inherit;">Support   <span class="glyphicon glyphicon-asterisk pull-right"></span></a></li>
							<li><a href="signout.login" style="white-space: inherit;">Logout <span class="glyphicon glyphicon-off pull-right"></span></a></li>
								
							</ul>
						</li>
						<li class="dropdown pull-right">
							 <a href="#" data-toggle="dropdown" class="dropdown-toggle" ><span class="glyphicon glyphicon-bell" id="notify"></span> <span id="notificationcount" class="hidden"></span></a>
							<ul class="dropdown-menu" id="notification"  >
								
							</ul>
						</li>
						
						<li class="dropdown pull-right">
							 <a href="#" data-toggle="dropdown" class="dropdown-toggle"><span class="glyphicon glyphicon-user" id="friend"></span> <span id="requestcount" class="hidden"></span> </a>
							<ul class="dropdown-menu " id="request" style="width:280px;">
							
							</ul>
						</li>
					</ul>
     
    </div>
    <div class=" col-xs-12 col-sm-12 col-md-7 col-md-pull-3">
	
	
     
       <ul class="nav ncenter nav-pills">
   <c:set var="name" value="${pageContext.request.servletPath}"/>

						<c:set var="page" value="0"/>
						
						<c:if test="${ name == '/doctorhome.jsp'}"> <c:set var="page" value="1"/> </c:if>
						<c:if test="${(name == '/doctorEditProfile.jsp')  || (name == '/doctorProfile.jsp') || (name == '/doctorProfile.jsp')}"><c:set var="page" value="2"/>
                        </c:if>
                        <c:if test="${ name == '/doctorPlanner.jsp'}"> <c:set var="page" value="3"/> </c:if>
                        <c:if test="${(name == '/doctorPrescription.jsp')  || (name == '/doctorViewAllPrescription.jsp')}"><c:set var="page" value="4"/>
                        </c:if>
                         <c:if test="${(name == '/doctorViewAllOrder.jsp')  || (name == '/doctorOrder.jsp') || (name == '/doctorOrderAddress.jsp') || (name == '/doctorOrderConfirm.jsp')}"><c:set var="page" value="5"/>
                        </c:if>
                         <c:if test="${(name == '/doctorCreatePost.jsp')  || (name == '/doctorForum.jsp') || (name == '/doctorViewPost.jsp')}"><c:set var="page" value="6"/>
                        </c:if>
                        <c:if test="${(name == '/doctorSuggestion.jsp')   || (name == '/doctorViewSuggestion.jsp')}"><c:set var="page" value="7"/>
                        </c:if>
						
    <li  <c:if test="${ page == 1}">class="nactive"</c:if>><a href="doctor.home">Home</a></li>
	
	
	
	<li  <c:if test="${ page == 3}">class="nactive" </c:if>><a href="appointment.doctor">Appointment</a></li>	
		
	<li  <c:if test="${ page == 4}">class="nactive" </c:if>><a href="allPrescriptions.doctor">Prescriptions</a></li>				
			
	<li  <c:if test="${ page == 5}">class="nactive" </c:if>><a href="getAllUrgentOrder.doctor">My Orders</a></li>		
							
	<li  <c:if test="${ page == 6}">class="nactive" </c:if>><a href="community.community">Community</a></li>
	
	<li  <c:if test="${ page == 7}">class="nactive" </c:if>><a href="suggestion.suggestion">Suggestion</a></li>	
    
  </ul> 
     
    </div>
 
  </div>

<div class="row effect7 text-left">
		<div class="col-md-12" style="background-color:#00b9bc;height:120px;">
			<div class="row">
				<div class="col-xs-5 col-sm-2 col-md-2" style="margin-top:10px;width:135px;">
				
				<ul class="img-list">
									<li>
				
					<img alt="Bootstrap Image Preview" src="${doctor1.image}" class="img-rounded" style="height:130px;width:130px;" onError="this.onerror=null;this.src='image/d.jpg';"/> 
					<img alt="Bootstrap Image Preview" src="${doctor.image}" id="profileImage" class="hidden" onError="this.onerror=null;this.src='image/d.jpg';"/> 
												</li>
												</ul>
					<p style="width:140px;margin-top:-13px;padding-left:5px;padding:2px;color:white;background-image:
    linear-gradient(
      to right, 
      #00b9bc,#f6f6f6
    );">${doctor1.firstName} &nbsp; ${doctor1.lastName}</p>
				</div>
				<div class="col-md-4">
				</div>
				<div class="col-md-4">
				</div>
			</div>
		</div>
	</div>


	<div class="container-fluid" style="margin-top:4%;width:92%;">
	<div class="row clearfix" >
		<div class="col-md-12 column">
		 
			<div class="row clearfix">
			
		
				<div class="col-md-8 column col-md-offset-2 col-sm-offset-1 col-xs-offset-1" style="word-wrap:break-word;">
							<div class="panel panel-default">
						<div class="panel-body">
						 <c:if test="${doctor1.deleted == 0}">
							  <div class="row">
							   <c:if test="${connect == 0}">
						<div class="col-md-3 column"><div id="${doctor1.id}">
							 <input class="btn btn-block btn-xs" type="button" style=";" value="Connect" onclick="addDoctor(${doctor1.id})"></div></div></c:if>
							  <c:if test="${connect == 1}">
						<div class="col-md-3 column">
							 Connection Request has been sent</div></c:if>
							<c:if test="${connect == 2}">
						
							 <div class="col-md-3 column">
							 <input class="btn btn-block btn-xs" type="button" style="width:%;" value="Remove From Network" onclick="removeDoctor(${doctor1.id})"> </div></c:if>
							  <div class="col-md-3 column">
							 <a class="btn btn-success btn-block btn-xs" type="button" style="width:%;" href=javascript:register_popup("${doctor1.firstName}&nbsp;${doctor1.lastName}",${doctor1.id},1);>Send Message</a></div>
							
			</div>	</c:if>				
				   <br>
				   <br>
<c:if test="${doctor1.privacy == 0}">
<c:set var="view" value="0"/>
</c:if>
<c:if test="${doctor1.privacy == 1}">
<c:if test="${connect == 2}"><c:set var="view" value="0"/></c:if>
<c:if test="${connect != 2}"><c:set var="view" value="1"/></c:if>
</c:if>
<c:if test="${doctor1.privacy == 2}">
<c:set var="view" value="1"/>
</c:if>

<c:if test="${view == 0}">
<div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3  column pviewplabel">	Name</div>
            <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent">${doctor1.firstName} ${doctor1.lastName}</div>
            </div>	<br>		 
			<div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3  column pviewplabel">	Gender:</div>
            <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent">${gender}</div>
            </div>	
           	    
			<div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3  column pviewplabel">Date Of Birth:</div>
            <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent">${doctor1.dob}</div>
            </div>	
          	 
			<div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3  column pviewplabel">Blood Group:</div>
            <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent"><c:if test="${doctor1.bloodGroup == 1}">A+</c:if>
             <c:if test="${doctor1.bloodGroup == 2}">A-</c:if>
             <c:if test="${doctor1.bloodGroup == 3}">B+</c:if>
             <c:if test="${doctor1.bloodGroup == 4}">B-</c:if>
             <c:if test="${doctor1.bloodGroup == 5}">O+</c:if>
             <c:if test="${doctor1.bloodGroup == 6}">O-</c:if>
             <c:if test="${doctor1.bloodGroup == 7}">AB+</c:if>
             <c:if test="${doctor1.bloodGroup == 8}">AB-</c:if></div>
            </div>		
	  <div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3  column pviewplabel">Qualification:</div>
            <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent">${doctor1.qualification}</div>
            </div>
            <div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3  column pviewplabel">Speciality:</div>
            <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent">${doctor1.speciality}</div>
            </div>
            <div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3  column pviewplabel">Work Location:</div>
            <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent">${doctor1.workLocation}</div>
            </div>
			<div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3  column pviewplabel">Address:</div>
            <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent">${doctor1.landMark}<br>${doctor1.address1}<br>${doctor1.address2}<br>${doctor1.city} - ${doctor1.pinCode}<br>${doctor1.state}<br>${doctor1.country}</div>
            </div>			 
			<div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3  column pviewplabel">Contact Number:</div>
            <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent">${doctor1.mobile}</div>
            </div>
            <div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3  column pviewplabel">Landline Number:</div>
            <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent">${doctor1.landLine}</div>
            </div>			 		
	   
			<div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3  column pviewplabel"> Email:</div>
            <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent">${doctor1.emailId}</div>
            </div>	
            
			<div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3  column pviewplabel">About ${patient.firstName}:</div>
            <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent">${doctor1.aboutMe}</div>
            </div>
</c:if>
<c:if test="${view == 1}">
<div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3  column pviewplabel">	Name</div>
            <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent">${doctor1.firstName} ${doctor1.lastName}</div>
            </div>
</c:if>
									
					</div>
					</div>
					</div>
					
					
					
					
				</div>
			</div>






			<script src="js/bootstrap.min.js"></script>
</body>
</html>










