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
<link href="css/basic.css" rel="stylesheet">
<script src="js/jquery.js"></script>
<script type="text/javascript" src="js/ajax.js"></script>
<link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">

<%@ include file="patientchat.jsp" %>
	<script>
function preventBackButton(){window.history.forward();}
setTimeout("preventBackButton()", 0);
window.onunload=function(){null};
</script>
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
<script>
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
					alert(result);
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
							alert(result);
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
				"<div class='col-xs-4 col-md-3 column' style='width:60px;'> <image src='"+d[i].image +"' width='50px' height='50px'></div>"+
				"<div class='col-xs-8 col-md-9 column' style='color:;font-size:0.85em;padding-right:10px;'> "+d[i].content +" <br> <span style='color:grey;font-size:0.8em;padding-top:10px;'>"+d[i].creationDate+"</span></div></div></a></li><li class='divider'> </li>";
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

function getNewRequest() {

					$.ajax({
								method : "GET",
								url : 'getRequest.notification',
								datatype : 'json',
								success : function(result) {
									var html = "<li><a href='#'>Requests</a> </li><li class='divider'> </li>";
									var count = 0;

									var d = $.parseJSON(result);
									for ( var i = 0; i < d.length; i++) {
										
										if (d[i].view == 0) {
											count++;
										}
										
										html = html
										+ '<li>'
										
										+ '<div class="row"'+
										'style="padding-left:5px;">'
										+ '<div class="col-xs-4 col-md-3 column" style="width:60px;">'
										+ '<img alt="300x200" src="'+d[i].image+'" width="50px;"'+
					'height="50px;" />'
										+ '</div>'
										+ ''
										+ '<div class="col-xs-8 col-md-9 column" style="font-size:0.85em;padding-right:20px;">'
										+ '<a href="patientViewDoctor.profile?id='
										+ d[i].id
										+ '" >'
										+ d[i].firstName
										+ '&nbsp; '
										+ d[i].lastName
										+ ' </a>  has sent you request'
										+ ''
										+ '<br/><br/>'
										+ '<a href="#" class="btn btn-success btn-sm"'
										+ 'type="button" style="" onclick="accepted('
										+ d[i].id
										+ ');">Accept</a>                   '
										+ '<a class="btn btn-info btn-warning btn-sm" type="button"'
										+ 'style="width: %;" onclick="decline('
										+ d[i].id
										+ ');">Decline</a>'
										+ '</div>' + '</div>'
										+ '' 
										+ '</li><li class="divider"> </li>';
									}
									if (count != 0) {
										$("#requestcount").removeClass("hidden");
										$("#requestcount").html(count);
										$("#request").html(html);
										
									}
									else
										{
										
										if(html == "<li><a href='#'>Requests</a> </li><li class='divider'> </li>")
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
				
				
var pharmacyName = "${pharmacy.pharmacyName}";
var id = "${pharmacy.id}";

function sendPharmacyMessage()
{
	var res = pharmacyName.split(" ");
	var name ="";
	for (var j = 0; j < res.length; j++) {
		name = name+"&nbsp"+res[j];
	}
	
	register_popup(name ,id ,4);
}

function saveRating()
{
	
	var rating = document.r.rating.value;
	var comment = document.r.comment.value;
	if(rating == "")
	{
	rating = 1;
	}
	$.ajax({
		method : "POST",
		url : 'RatingAndReview.notification',
		data: { 
			rating : rating,
			comment : comment,
			id : id
		},
		success : function(result) {
			location.reload();
		},
		statusCode : {
			500 : function(result) {
				
			}
		}
	});	   
}


(function(e){var t,o={className:"autosizejs",append:"",callback:!1,resizeDelay:10},i='<textarea tabindex="-1" style="position:absolute; top:-999px; left:0; right:auto; bottom:auto; border:0; padding: 0; -moz-box-sizing:content-box; -webkit-box-sizing:content-box; box-sizing:content-box; word-wrap:break-word; height:0 !important; min-height:0 !important; overflow:hidden; transition:none; -webkit-transition:none; -moz-transition:none;"/>',n=["fontFamily","fontSize","fontWeight","fontStyle","letterSpacing","textTransform","wordSpacing","textIndent"],s=e(i).data("autosize",!0)[0];s.style.lineHeight="99px","99px"===e(s).css("lineHeight")&&n.push("lineHeight"),s.style.lineHeight="",e.fn.autosize=function(i){return this.length?(i=e.extend({},o,i||{}),s.parentNode!==document.body&&e(document.body).append(s),this.each(function(){function o(){var t,o;"getComputedStyle"in window?(t=window.getComputedStyle(u,null),o=u.getBoundingClientRect().width,e.each(["paddingLeft","paddingRight","borderLeftWidth","borderRightWidth"],function(e,i){o-=parseInt(t[i],10)}),s.style.width=o+"px"):s.style.width=Math.max(p.width(),0)+"px"}function a(){var a={};if(t=u,s.className=i.className,d=parseInt(p.css("maxHeight"),10),e.each(n,function(e,t){a[t]=p.css(t)}),e(s).css(a),o(),window.chrome){var r=u.style.width;u.style.width="0px",u.offsetWidth,u.style.width=r}}function r(){var e,n;t!==u?a():o(),s.value=u.value+i.append,s.style.overflowY=u.style.overflowY,n=parseInt(u.style.height,10),s.scrollTop=0,s.scrollTop=9e4,e=s.scrollTop,d&&e>d?(u.style.overflowY="scroll",e=d):(u.style.overflowY="hidden",c>e&&(e=c)),e+=w,n!==e&&(u.style.height=e+"px",f&&i.callback.call(u,u))}function l(){clearTimeout(h),h=setTimeout(function(){var e=p.width();e!==g&&(g=e,r())},parseInt(i.resizeDelay,10))}var d,c,h,u=this,p=e(u),w=0,f=e.isFunction(i.callback),z={height:u.style.height,overflow:u.style.overflow,overflowY:u.style.overflowY,wordWrap:u.style.wordWrap,resize:u.style.resize},g=p.width();p.data("autosize")||(p.data("autosize",!0),("border-box"===p.css("box-sizing")||"border-box"===p.css("-moz-box-sizing")||"border-box"===p.css("-webkit-box-sizing"))&&(w=p.outerHeight()-p.height()),c=Math.max(parseInt(p.css("minHeight"),10)-w||0,p.height()),p.css({overflow:"hidden",overflowY:"hidden",wordWrap:"break-word",resize:"none"===p.css("resize")||"vertical"===p.css("resize")?"none":"horizontal"}),"onpropertychange"in u?"oninput"in u?p.on("input.autosize keyup.autosize",r):p.on("propertychange.autosize",function(){"value"===event.propertyName&&r()}):p.on("input.autosize",r),i.resizeDelay!==!1&&e(window).on("resize.autosize",l),p.on("autosize.resize",r),p.on("autosize.resizeIncludeStyle",function(){t=null,r()}),p.on("autosize.destroy",function(){t=null,clearTimeout(h),e(window).off("resize",l),p.off("autosize").off(".autosize").css(z).removeData("autosize")}),r())})):this}})(window.jQuery||window.$);

var __slice=[].slice;(function(e,t){var n;n=function(){function t(t,n){var r,i,s,o=this;this.options=e.extend({},this.defaults,n);this.$el=t;s=this.defaults;for(r in s){i=s[r];if(this.$el.data(r)!=null){this.options[r]=this.$el.data(r)}}this.createStars();this.syncRating();this.$el.on("mouseover.starrr","span",function(e){return o.syncRating(o.$el.find("span").index(e.currentTarget)+1)});this.$el.on("mouseout.starrr",function(){return o.syncRating()});this.$el.on("click.starrr","span",function(e){return o.setRating(o.$el.find("span").index(e.currentTarget)+1)});this.$el.on("starrr:change",this.options.change)}t.prototype.defaults={rating:void 0,numStars:5,change:function(e,t){}};t.prototype.createStars=function(){var e,t,n;n=[];for(e=1,t=this.options.numStars;1<=t?e<=t:e>=t;1<=t?e++:e--){n.push(this.$el.append("<span class='glyphicon .glyphicon-star-empty'></span>"))}return n};t.prototype.setRating=function(e){if(this.options.rating===e){e=void 0}this.options.rating=e;this.syncRating();return this.$el.trigger("starrr:change",e)};t.prototype.syncRating=function(e){var t,n,r,i;e||(e=this.options.rating);if(e){for(t=n=0,i=e-1;0<=i?n<=i:n>=i;t=0<=i?++n:--n){this.$el.find("span").eq(t).removeClass("glyphicon-star-empty").addClass("glyphicon-star")}}if(e&&e<5){for(t=r=e;e<=4?r<=4:r>=4;t=e<=4?++r:--r){this.$el.find("span").eq(t).removeClass("glyphicon-star").addClass("glyphicon-star-empty")}}if(!e){return this.$el.find("span").removeClass("glyphicon-star").addClass("glyphicon-star-empty")}};return t}();return e.fn.extend({starrr:function(){var t,r;r=arguments[0],t=2<=arguments.length?__slice.call(arguments,1):[];return this.each(function(){var i;i=e(this).data("star-rating");if(!i){e(this).data("star-rating",i=new n(e(this),r))}if(typeof r==="string"){return i[r].apply(i,t)}})}})})(window.jQuery,window);$(function(){return $(".starrr").starrr()})

$(function(){

  $('#new-review').autosize({append: "\n"});

  var reviewBox = $('#post-review-box');
  var newReview = $('#new-review');
  var openReviewBtn = $('#open-review-box');
  var closeReviewBtn = $('#close-review-box');
  var ratingsField = $('#ratings-hidden');

  openReviewBtn.click(function(e)
  {
    reviewBox.slideDown(400, function()
      {
        $('#new-review').trigger('autosize.resize');
        newReview.focus();
      });
    openReviewBtn.fadeOut(100);
    closeReviewBtn.show();
  });

  closeReviewBtn.click(function(e)
  {
    e.preventDefault();
    reviewBox.slideUp(300, function()
      {
        newReview.focus();
        openReviewBtn.fadeIn(200);
      });
    closeReviewBtn.hide();
    
  });
});
				
</script>
<style>
.btn-block{background-color:#f9f9f9;color:black;padding:5px;margin-top:-2px;}
fieldset, label { margin: 0; padding: 0; }

h1 { font-size: 1.5em; margin: 10px; }

/****** Style Star Rating Widget *****/

.rating { 
  border: none;
  float: left;
}

.rating > input { display: none; } 
.rating > label:before { 
  margin: 5px;
  font-size: 1.25em;
  font-family: FontAwesome;
  display: inline-block;
  content: "\f005";
}

.rating > .half:before { 
  content: "\f089";
  position: absolute;
}

.rating > label { 
  color: #ddd; 
 float: right; 
}

/***** CSS Magic to Highlight Stars on Hover *****/

.rating > input:checked ~ label, /* show gold star when clicked */
.rating:not(:checked) > label:hover, /* hover current star */
.rating:not(:checked) > label:hover ~ label { color: #FFD700;  } /* hover previous stars in list */

.rating > input:checked + label:hover, /* hover current star when changing rating */
.rating > input:checked ~ label:hover,
.rating > label:hover ~ input:checked ~ label, /* lighten current selection */
.rating > input:checked ~ label:hover ~ label { color: #FFED85;  } 



 .animated {
    -webkit-transition: height 0.2s;
    -moz-transition: height 0.2s;
    transition: height 0.2s;
}

.stars
{
    margin: 20px 0;
    font-size: 24px;
    color: #d17581;
}
</style>
</head>
<body>
<div class="se-pre-con"></div>
<div class="container-fluid navbar-fixed-top white" style="background-color:#fff;box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0),0 2px 10px 0 rgba(0, 0, 0, 0.05);">
 <div class="container nav_container">
  <div class="row  text-center">
    <div class="col-xs-4 col-sm-3 col-md-2 column ">
  	<img class="pull-left" src="image/healingclick_logo.png" alt="logo" style="padding-top:5px;padding-left:10px;"/>
    </div>
    <div class="col-xs-8 col-sm-9 col-md-3 col-md-push-7" style="">
      
       <ul class="nav nright nav-pills">
						<li class="name dropdown pull-right ">
							 <a href="#" data-toggle="dropdown" class="dropdown-toggle" style="color:white;background-image:linear-gradient(
      to top, 
      #00b9bc,#66d5d6
    );margin-right:-15px;"> ${patient.userName}<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li><a href="settings.login" style="white-space: inherit;">Settings   <span class="glyphicon glyphicon-cog pull-right"></span></a></li>
							<li><a href="patient.profile" style="white-space: inherit;">Profile <span class="glyphicon glyphicon-user pull-right"></span></a></li>
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
							<ul class="dropdown-menu" id="request" style="width:280px;">
							
							</ul>
						</li>
					</ul>
     
    </div>
    <div class=" col-xs-12 col-sm-12 col-md-7 col-md-pull-3 text-center">
	
	
     
       <ul class="nav ncenter nav-pills text-center">
   <c:set var="name" value="${pageContext.request.servletPath}"/>

						<c:set var="name" value="${pageContext.request.servletPath}"/>

						<c:set var="page" value="0"/>
						
						<c:if test="${ name == '/patienthome.jsp'}"> <c:set var="page" value="1"/> </c:if>
						<c:if test="${(name == '/patientEditProfile.jsp')  || (name == '/patientProfile.jsp')}"><c:set var="page" value="2"/>
                        </c:if>
                        <c:if test="${ (name == '/patientPlanner.jsp') || (name == '/patientDoctorPlanner.jsp')}"> <c:set var="page" value="3"/> </c:if>
                        <c:if test="${(name == '/patientPrescription.jsp')  || (name == '/patientViewAllPrescription.jsp')|| (name == '/patientViewPrescription.jsp')}"><c:set var="page" value="4"/>
                        </c:if>
                         <c:if test="${(name == '/patientViewAllOrder.jsp')  || (name == '/patientOrder.jsp') || (name == '/patientOrderAddress.jsp') || (name == '/patientOrderConfirm.jsp')}"><c:set var="page" value="5"/>
                        </c:if>
                         <c:if test="${(name == '/patientCreatePost.jsp')  || (name == '/patientForum.jsp') || (name == '/patientViewPost.jsp')}"><c:set var="page" value="6"/>
                        </c:if>
                         <c:if test="${(name == '/patientCreateSuggestion.jsp')  || (name == '/patientSuggestion.jsp') || (name == '/patientViewSuggestion.jsp')}"><c:set var="page" value="7"/>
                        </c:if>
						
    <li  <c:if test="${ page == 1}">class="nactive"</c:if>><a href="patient.home">Home</a></li>
	
	<li  <c:if test="${ page == 2}">class="nactive" </c:if>></li>
	
	<li  <c:if test="${ page == 3}">class="nactive" </c:if>><a href="appointment.patient">Appointment</a></li>	
		
	<li  <c:if test="${ page == 4}">class="nactive" </c:if>><a href="allPrescriptions.patient">Medical Tracker</a></li>				
			
	<li  <c:if test="${ page == 5}">class="nactive" </c:if>><a href="getAllUrgentOrder.patient">My Orders</a></li>	
	
	<li  <c:if test="${ page == 7}">class="nactive" </c:if>><a href="suggestion.suggestion">Queries</a></li>			
    
  </ul> 
     
    </div>
 
  </div>
  </div>
  </div>

  <div style="height:40px;"></div>
  <div class="visible-sm" style="height:30px;"></div>
   <div class="visible-xs" style="height:40px;"></div>
   
  
  <div class="container-fluid" style="background-color:#00b9bc;height:120px;">
  <div class="container " style="width:95%;">
  

	
<div class="row effect7 text-left">
		<div class="col-md-12" style="background-color:#00b9bc;height:120px;">
			<div class="row">
				<div class="col-xs-5 col-sm-2 col-md-2" style="margin-top:10px;width:135px;">
				
				<ul class="img-list">
									<li>
				
					<img alt="Bootstrap Image Preview" src="${pharmacy.image}" class="img-rounded" style="height:130px;width:130px;" onError="this.onerror=null;this.src='image/d.jpg';" /> 
												</li>
												</ul>
												
					<p style="width:140px;margin-top:-13px;padding-left:5px;padding:2px;color:white;background-image:
    linear-gradient(
      to right, 
      #00b9bc,#f6f6f6
    );">${pharmacy.pharmacyName}</p>							
					
				</div>
				<div class="col-md-4">
				</div>
				<div class="col-md-4">
				</div>
			</div>
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
							  <div class="row">
				<c:if test="${pharmacy.deleted == 0}">			  
			<c:if test="${connect == 0}">
						<div class="col-md-3 column"><div id="${pharmacy.id}">
							 <input class="btn btn-block btn-xs" type="button" style=";" value="Add" onclick="addp(${pharmacy.id})"></div></div></c:if>
							
							 <c:if test="${connect == 1}">
						
						<div class="col-md-3 column">
							 <a href="order.patient?id=${pharmacy.id}" class="btn btn-block btn-xs" type="button" style=";">Make Order</a></div>
							 <div class="col-md-3 column">
							 <input class="btn btn-block btn-xs" type="button" style="width:%;" value="Remove From Network" onclick="removep(${pharmacy.id})"></div></c:if>
							 
							  <div class="col-md-3 column">
							 <a class="btn btn-block btn-xs" type="button" style="width:%;" href=javascript:sendPharmacyMessage();>Send Message</a></div>
							</c:if> </div>			
				   <br>
				   <br>
			 <div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3  column pviewplabel">	Rating</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent">${pharmacy.rating}</div>
            </div>
			<div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3  column pviewplabel">	Pharmacy Name</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent">${pharmacy.pharmacyName}</div>
            </div>		 
	    <div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3  column pviewplabel">	Delivery Charges:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent"> ${pharmacy.deliveryCharge}Rs.</div>
            </div>
            
            <div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3  column pviewplabel">	Discount:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent"> ${pharmacy.discount}%</div>
            </div>
			<div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3 column" >Pharmacy Address:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent">${pharmacy.landMark}<br>${pharmacy.address1}<br>${pharmacy.address2}<br>${pharmacy.city} - ${pharmacy.pinCode}<br>${pharmacy.state}<br>${pharmacy.country}</div>
            </div>			 
			<div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3 column" >Contact Number:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent">${pharmacy.mobile}</div>
            </div>
            <div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3 column" >Landline Number:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent">${pharmacy.landLine}</div>
            </div>			 		
	   
			<div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3 column" > Email:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent">${pharmacy.emailId}</div>
            </div>	
           	 
			<div class="row pviewpdesc">
              <div class="col-xs-4 col-sm-3 col-md-3 column" >About ${pharmacy.pharmacyName}:</div>
             <div class="col-xs-8 col-sm-9 col-md-9 column pviewpcontent">${pharmacy.aboutMe}</div>
            </div>						
					</div>
					</div>	
					<div class="container">
	<div class="row" style="margin-top:40px;">
		<div class="col-md-6">
    	<div class="well well-sm">
            <div class="text-right">
                <a class="btn btn-success btn-green" href="#reviews-anchor" id="open-review-box">Leave a Review</a>
            </div>
        
            <div class="row" id="post-review-box" style="display:none;">
                <div class="col-md-12">
                    <form accept-charset="UTF-8" action="javascript:saveRating();" method="post" name="r">
                        <input id="ratings-hidden" name="rating" type="hidden"> 
                        <textarea class="form-control animated" cols="50" id="new-review" name="comment" placeholder="Enter your review here..." rows="5" required></textarea>
        
                        <div class="text-right">
                           <fieldset class="rating">
    <input type="radio" id="star5" name="rating" value="5" /><label class = "full" for="star5" title="Awesome - 5 stars"></label>
    <input type="radio" id="star4half" name="rating" value="4.5" /><label class="half" for="star4half" title="Pretty good - 4.5 stars"></label>
    <input type="radio" id="star4" name="rating" value="4" /><label class = "full" for="star4" title="Pretty good - 4 stars"></label>
    <input type="radio" id="star3half" name="rating" value="3.5" /><label class="half" for="star3half" title="Meh - 3.5 stars"></label>
    <input type="radio" id="star3" name="rating" value="3" /><label class = "full" for="star3" title="Meh - 3 stars"></label>
    <input type="radio" id="star2half" name="rating" value="2.5" /><label class="half" for="star2half" title="Kinda bad - 2.5 stars"></label>
    <input type="radio" id="star2" name="rating" value="2" /><label class = "full" for="star2" title="Kinda bad - 2 stars"></label>
    <input type="radio" id="star1half" name="rating" value="1.5" /><label class="half" for="star1half" title="Meh - 1.5 stars"></label>
    <input type="radio" id="star1" name="rating" value="1" /><label class = "full" for="star1" title="Sucks big time - 1 star"></label>
    <input type="radio" id="starhalf" name="rating" value="0.5" /><label class="half" for="starhalf" title="Sucks big time - 0.5 stars"></label>
</fieldset>	
                            <a class="btn btn-danger btn-sm" href="#" id="close-review-box" style="display:none; margin-right: 10px;">
                            <span class="glyphicon glyphicon-remove"></span>Cancel</a>
                            <button class="btn btn-success btn-lg" type="submit">Save</button>
                        </div>
                    </form>
                </div>
            </div>
        </div> 
         
		</div>
	</div>
</div>
					<div class="panel" style="min-width: 350px;">
					
					<c:if test="${not empty reviews}">
							<c:forEach var="review" items="${reviews}">
							<div class="panel-heading" style="background-color:white;" >
								<div class="panel-title"><h5>
								<c:if test="${review.type == 2}"><c:if test="${review.reviewerId != patient.id}">${review.reviewerName}</c:if>
								<c:if test="${review.reviewerId == patient.id}">Your Review</c:if></c:if>
								<c:if test="${review.type == 1}"><a href="patientViewDoctor.profile?id=${review.reviewerId}">Dr . ${review.reviewerName}</a></c:if>
								<c:if test="${review.type == 3}"><a href="patientViewPharmacy.profile?id=${review.reviewerId}"> ${review.reviewerName}</a></c:if>
								<span class="pull-right glyphicon glyphicon-list-alt" style="color:;"></span></h5></div>
								<!--hr align="left"  style="margin-top:;border:1px solid #ffa500;width:50px;"--->
								
							</div>
							<div class="panel-body">
								<div class="row">
									<div class="col-xs-6 col-sm-6 col-md-6 column">
										<ul style="list-style: none; padding: 2px;">

											<li>Rating :</li>
											<li>Comment :</li>
											
										</ul>
									</div>

									<div class="col-xs-6 col-sm-6 col-md-6 column">
										<ul style="list-style: none; padding: 2px;">
											<li>${review.rating}</li>
											<li>${review.comment}</li>
								
										</ul>
									</div>
								</div>
							</div>	
							</c:forEach>
							</c:if>
						</div>				
					</div>
					</div>
					</div>
				</div>
			</div>

			<script src="js/bootstrap.min.js"></script>
</body>
</html>
