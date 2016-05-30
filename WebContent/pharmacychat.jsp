<head>
<link href="css/chat.css" rel="stylesheet">

<script>

            var pharamcyId = "${pharmacy.id}";
            //this function can remove a array element.
            Array.remove = function(array, from, to) {
                var rest = array.slice((to || from) + 1 || array.length);
                array.length = from < 0 ? array.length + from : from;
                return array.push.apply(array, rest);
            };
        
            //this variable represents the total number of popups can be displayed according to the viewport width
            var total_popups = 0;
            
            //arrays of popups ids
            var popups = [];
           
        
            //this is used to close a popup
            function close_popup(id)
            {
                for(var iii = 0; iii < popups.length; iii++)
                {
                    if(id == popups[iii])
                    {
                        Array.remove(popups, iii);
                        
                        document.getElementById(id).style.display = "none";
                        
                        calculate_popups();
                        
                        return;
                    }
                }   
            }
        
            //displays the popups. Displays based on the maximum number of popups that can be displayed on the current viewport width
            function display_popups()
            {
                var right = 220;
                
                var iii = 0;
                for(iii; iii < total_popups; iii++)
                {
                    if(popups[iii] != undefined)
                    {
                        var element = document.getElementById(popups[iii]);
                        element.style.right = right + "px";
                        right = right + 320;
                        element.style.display = "block";
                    }
                }
                
                for(var jjj = iii; jjj < popups.length; jjj++)
                {
                    var element = document.getElementById(popups[jjj]);
                    element.style.display = "none";
                }
            }
            
            //creates markup for a new popup. Adds the id to popups array.
            function register_popup(name ,otherId ,type)
            {
                       
				var isMobile = detectMobile();
            	
            	if(isMobile == true)
            		{
            		window.location = "getChatMessages.mobile?otherId="+otherId+"&type="+type;
            		}
            	else
            		{
                
              
                	$.ajax({
                		method : "GET",
                		url : 'getChatMessages.chat',
                		datatype : 'json',
                		data: { 
                			otherId : otherId,
                			type : type
    					},
                		success : function(result) {
                			
                			if(result>0)
                				{
                				
                				 for(var iii = 0; iii < popups.length; iii++)
                	                {   
                	                    //already registered. Bring it to front.
                	                    if(result == popups[iii])
                	                    {
                	                        Array.remove(popups, iii);
                	                    
                	                        popups.unshift(result);
                	                        
                	                        calculate_popups();
                	                                       	                        
                	                        return;
                	                    }
                	                }       
                				
                				var element = '<div class="popup-box chat-popup" id="'+ result +'">';
                                element = element + '<div class="popup-head">';
                                element = element + '<div class="popup-head-left">'+ name +'<span class="glyphicon glyphicon-one-fine-dot pull-left" style="color:green;" id="active'+ result +'" ></span>&nbsp;&nbsp;&nbsp;<span id="notactive'+ result +'" style="font-size:0.5em;padding-top:10px;"></span></div>';
                                element = element + '<div class="popup-head-right"><a href="javascript:close_popup(\''+ result +'\');">&#10005;</a></div>';
                                element = element + '<div style="clear: both"></div></div><div id="messages'+result+'" class="messages"><div class="clear"></div></div><div class="input-box"><textarea placeholder="Enter message" id = "msg'+result+'" onkeypress=sendMessage(event,'+result+');></textarea></div></div>';
                                
                                document.getElementById("c").innerHTML = document.getElementById("c").innerHTML + element;
                        
                                popups.unshift(result);
                                        
                                calculate_popups();
                                
                                var myDiv = $("#messages"+result).get(0);
                    			myDiv.scrollTop = myDiv.scrollHeight;
                    			checkUserActive(result);
                				}
                			else
                				{
                				var html = "<ul>";
                				var d = $.parseJSON(result);
                    			var chatId = "";
                    			for ( var i = 0; i < d.length; i++) {
                    				chatId = d[i].chatId;
                    				if(d[i].replierId == pharamcyId)
                    				{
                    					html = html+'<li><span class="right">'+d[i].reply+'<br> <span style="color:grey;font-size:0.5em;padding-top:10px;background-color: #eeeeee;border-style: none;">'+d[i].creationDate+'</span></span><div class="clear"></div></li>';
                    					}
                    				else
                    					{
                    					html = html+'<li><span class="left">'+d[i].reply+'<br> <span style="color:grey;font-size:0.5em;padding-top:10px;border-style: none;">'+d[i].creationDate+'</span></span><div class="clear"></div></li>';
                    					}
                    				
                    			}
                    			html = html+'</ul>';
                    			
                    			 for(var iii = 0; iii < popups.length; iii++)
                                 {   
                                     //already registered. Bring it to front.
                                     if(chatId == popups[iii])
                                     {
                                         Array.remove(popups, iii);
                                     
                                         popups.unshift(chatId);
                                         
                                         calculate_popups();
                                         
                                         $("#messages"+chatId).html(html);
                              			var myDiv = $("#messages"+chatId).get(0);
                              			myDiv.scrollTop = myDiv.scrollHeight;
                                         
                                         return;
                                     }
                                 }       
                    			
                    			var element = '<div class="popup-box chat-popup" id="'+ chatId +'" >';
                                element = element + '<div class="popup-head">';
                                element = element + '<div class="popup-head-left">'+ name +'<span class="glyphicon glyphicon-one-fine-dot pull-left" style="color:green;" id="active'+ chatId +'" ></span>&nbsp;&nbsp;&nbsp;<span id="notactive'+ chatId +'" style="font-size:0.5em;padding-top:10px;"></span></div>';
                                element = element + '<div class="popup-head-right"><a href="javascript:close_popup(\''+ chatId +'\');">&#10005;</a></div>';
                                element = element + '<div style="clear: both"></div></div><div id="messages'+chatId+'" class="messages">'+html+'<div class="clear"></div></div><div class="input-box"><textarea placeholder="Enter message" id = "msg'+chatId+'" onkeypress=sendMessage(event,'+chatId+');></textarea></div></div>';
                                
                                document.getElementById("c").innerHTML = document.getElementById("c").innerHTML + element;
                        
                                popups.unshift(chatId);
                                        
                                calculate_popups();
                                
                                var myDiv = $("#messages"+chatId).get(0);
                    			myDiv.scrollTop = myDiv.scrollHeight;
                    			
                    			checkUserActive(chatId);
                				}	
                		},
                		statusCode : {
                			500 : function(result) {
                				
                			}
                		}
                	});
            		}
                }
                  
            //calculate the total number of popups suitable and then populate the toatal_popups variable.
            function calculate_popups()
            {
                var width = window.innerWidth;
                if(width < 540)
                {
                    total_popups = 0;
                }
                else
                {
                    width = width - 200;
                    //320 is width of a single popup box
                    total_popups = parseInt(width/320);
                }
                
                display_popups();
                
            }
            
            //recalculate when window is loaded and also when window is resized.
            window.addEventListener("resize", calculate_popups);
            window.addEventListener("load", calculate_popups);
            
        </script>

<script>
//Call the yourAjaxCall() function every 1000 millisecond
setInterval("activeUser()",1000 * 60 );
setInterval("getNewMessages()",1000 * 20 );
//setInterval("nonActiveUser()",1000 * 1000);
$(document).ready(function() {

	activeUser();
	getNewMessages();
	

});

function getNewMessages(){
	$.ajax({
		method : "GET",
		url : 'getRecentChat.chat',
		datatype : 'json',
		success : function(result) {
			if(result!="")
				{
				var d = $.parseJSON(result);
				for ( var i = 0; i < d.length; i++) {
					if(d[i].patientId != 0)
						{
							register_popup(d[i].patientName ,d[i].patientId ,4);
						}
					
					if(d[i].doctorId != 0)
					{
						register_popup("Dr."+d[i].doctorName ,d[i].doctorId ,3);
					}
					
					if(d[i].pharmacyId==pharamcyId)
						{
							if(d[i].otherpharmacyId != 0)
								{
								register_popup(d[i].otherPharmacyName ,d[i].otherPharmacyId ,5);
								}
						}
					
					if(d[i].otherPharmacyId==pharamcyId)
					{
						if(d[i].pharmacyId != 0)
							{
							register_popup(d[i].pharmacyName ,d[i].pharmacyId ,5);
							}
					}
					
					
				}
				}
			

		},
		statusCode : {
			500 : function(result) {
				
			}
		}
	});
}


function activeUser(){
	$.ajax({
		method : "GET",
		url : 'getactiveuser.chat',
		datatype : 'json',
		success : function(result) {
			if(result!="[]")
			{
			var html = "";
			var d = $.parseJSON(result);
			for ( var i = 0; i < d.length; i++) {
					var res = d[i].pharmacyName.split(" ");
					
					var name ="";
					for (var j = 0; j < res.length; j++) {
						name = name+"&nbsp"+res[j];
					}
				html = html +'<div class="sidebar-name"> '+
                '<a href=javascript:register_popup("'+name+'",'+d[i].id+',3);>'+
                   ' <img width="30" height="30" src='+d[i].image+' />'+
                    '<span>'+d[i].pharmacyName+'</span><span class="glyphicon glyphicon-one-fine-dot"></span><span class="glyphicon glyphicon-one-fine-dot" style="color:#35c1a1;"></span>'+
                '</a>'+
            '</div>';
				
			}
			
			$("#chat").html(html);
			}
			else
				{
				
				nonActiveUser();
				}
		},
		statusCode : {
			500 : function(result) {
				
			}
		}
	});
}

function nonActiveUser(){
	$.ajax({
		method : "GET",
		url : 'getnonactiveuser.chat',
		datatype : 'json',
		success : function(result) {
			var d = $.parseJSON(result);
			if(d.length!=0)
			{
			var html = "";
			for ( var i = 0; i < d.length; i++) {
				if(d[i].patientId != 0)
				{
					var res = d[i].patientName.split(" ");
					var name ="";
					for (var j = 0; j < res.length; j++) {
						name = name+"&nbsp"+res[j];
					}
					
					html = html +'<div class="sidebar-name"> '+
	                '<a href=javascript:register_popup("'+name+'",'+d[i].patientId+',4);>'+
	                   ' <img width="30" height="30" src='+d[i].patientImage+' />'+
	                    '<span>'+d[i].patientName+'</span><span class="glyphicon glyphicon-one-fine-dot" style="color:red;"></span>'+
	                '</a>'+
	            '</div>';
				}
			
			if(d[i].doctorId != 0)
			{
				var res = d[i].doctorName.split(" ");
				var name ="";
				for (var j = 0; j < res.length; j++) {
					name = name+"&nbsp"+res[j];
				}
				html = html +'<div class="sidebar-name"> '+
                '<a href=javascript:register_popup("Dr.'+name+'",'+d[i].doctorId+',3);>'+
                   ' <img width="30" height="30" src='+d[i].doctorImage+' />'+
                    '<span>Dr.'+d[i].doctorName+'</span><span class="glyphicon glyphicon-one-fine-dot" style="color:red;"></span>'+
                '</a>'+
            '</div>';
			}
			
			if(d[i].pharmacyId==pharamcyId)
				{
					if(d[i].otherPharmacyId != 0)
						{
						
						var res = d[i].otherPharmacyName.split(" ");
						var name ="";
						for (var j = 0; j < res.length; j++) {
							name = name+"&nbsp"+res[j];
						}
						
						
						html = html +'<div class="sidebar-name"> '+
		                '<a href=javascript:register_popup("'+name+'",'+d[i].otherPharmacyId+',5);>'+
		                   ' <img width="30" height="30" src='+d[i].otherPharmacyImage+' />'+
		                    '<span>'+d[i].otherPharmacyName+'</span><span class="glyphicon glyphicon-one-fine-dot" style="color:red;"></span>'+
		                '</a>'+
		            '</div>';
						}
				}
			
			if(d[i].otherPharmacyId==pharamcyId)
			{
				if(d[i].pharmacyId != 0)
					{
					var res = d[i].pharmacyName.split(" ");
					var name ="";
					for (var j = 0; j < res.length; j++) {
						name = name+"&nbsp"+res[j];
					}
					html = html +'<div class="sidebar-name"> '+
	                '<a href=javascript:register_popup("'+name+'",'+d[i].pharmacyId+',5);>'+
	                   ' <img width="30" height="30" src='+d[i].pharmacyImage+' />'+
	                    '<span>'+d[i].pharmacyName+'</span><span class="glyphicon glyphicon-one-fine-dot" style="color:red;"></span>'+
	                '</a>'+
	            '</div>';
					}
			}
			}

			$("#chat").html(html);
			}
			else
			{
			newUserChat();
			}
		},
		statusCode : {
			500 : function(result) {
				
			}
		}
	});
}

function newUserChat(){
	$.ajax({
		method : "GET",
		url : 'newUserChat.chat',
		datatype : 'json',
		success : function(result) {
			if(result!="")
			{
			var html = "";
			var d = $.parseJSON(result);
			for ( var i = 0; i < d.length; i++) {
				var res = d[i].pharmacyName.split(" ");
				
				var name ="";
				for (var j = 0; j < res.length; j++) {
					name = name+"&nbsp"+res[j];
				}
			html = html +'<div class="sidebar-name"> '+
            '<a href=javascript:register_popup("'+name+'",'+d[i].id+',3);>'+
               ' <img width="30" height="30" src='+d[i].image+' />'+
                '<span>'+d[i].pharmacyName+'</span><span class="glyphicon glyphicon-one-fine-dot" style="color:red;"></span>'+
            '</a>'+
        '</div>';
			}

			$("#chat").html(html);
			}
		},
		statusCode : {
			500 : function(result) {
				
			}
		}
	});
}

function getMessages(chatId){
	$.ajax({
		method : "GET",
		url : 'getMessages.chat',
		datatype : 'json',
		data: { 
			chatId : chatId			
		},
		success : function(result) {
			if(result!="")
			{
			
			var html = "<ul>";
			var d = $.parseJSON(result);
			for ( var i = 0; i < d.length; i++) {
				chatId = d[i].chatId;
				if(d[i].replierId == pharamcyId)
				{
					html = html+'<li><span class="right">'+d[i].reply+'<br> <span style="color:grey;font-size:0.6em;padding-top:10px;background-color: #eeeeee;border-style: none;">'+d[i].creationDate+'</span></span><div class="clear"></div></li>';
					}
				else
					{
					html = html+'<li><span class="left">'+d[i].reply+'<br> <span style="color:grey;font-size:0.6em;padding-top:10px;border-style: none;">'+d[i].creationDate+'</span></span><div class="clear"></div></li>';
					}
				
			}
			html = html+'</ul>';
			$("#messages"+chatId).html(html);
			var myDiv = $("#messages"+chatId).get(0);
			myDiv.scrollTop = myDiv.scrollHeight;
			checkUserActive(chatId);
			}
    	},
		statusCode : {
			500 : function(result) {
				
			}
		}
	});
}

function sendMessage(event,chatId){
	
	if (event.keyCode === 13) {
		var message = document.getElementById("msg"+chatId).value;
		
		if(message.trim() != "")
			{
			
			
		$.ajax({
			method : "GET",
			url : 'saveMessages.chat',
			datatype : 'json',
			data: { 
				chatId : chatId,
				message : message
			},
			success : function(result) {
				if(result!="")
				{
				var html = "<ul>";
				var d = $.parseJSON(result);
    			
    			for ( var i = 0; i < d.length; i++) {
    				chatId = d[i].chatId;
    				if(d[i].replierId == pharamcyId)
    				{
    					html = html+'<li><span class="right">'+d[i].reply+'<br> <span style="color:grey;font-size:0.6em;padding-top:10px;background-color:#eeeeee;border-style: none;">'+d[i].creationDate+'</span></span><div class="clear"></div></li>';
    					}
    				else
    					{
    					html = html+'<li><span class="left">'+d[i].reply+'<br> <span style="color:grey;font-size:0.6em;padding-top:10px;border-style: none;">'+d[i].creationDate+'</span></span><div class="clear"></div></li>';
    					}
    				
    			}
    			html = html+'</ul>';
    			$("#msg"+chatId).val('');
    			
    			$("#messages"+chatId).html(html);
    			var myDiv = $("#messages"+chatId).get(0);
    			myDiv.scrollTop = myDiv.scrollHeight;
    			checkUserActive(chatId);
				}
			},
			statusCode : {
				500 : function(result) {
					
				}
			}
		});
			}
		
		
    } else {
    	getMessages(chatId);
        return;
    }
	
}	

function checkUserActive(chatId){
	
	$.ajax({
		method : "GET",
		url : 'checkUserActive.chat',
		datatype : 'json',
		data: { 
			chatId : chatId,
		},
		success : function(result) {
			
			if(result!="")
			{
				
				var FIVE_MINS = 60 * 5 * 1000; /* ms */
				var d = $.parseJSON(result);
				var olddate = new Date(d);
				if(((new Date) - olddate) < FIVE_MINS)
					{
					$("#active"+chatId).show();
					$("#notactive"+chatId).hide();
					}
				else
					{
					$("#active"+chatId).hide();
					$("#notactive"+chatId).show();
					$("#notactive"+chatId).html(d);
					}
			}
		},
		statusCode : {
			500 : function(result) {
				
			}
		}
	});	
}

</script>



</head>
<body>
	<div class="chat-box">
		<input type="checkbox" /> <label data-expanded="Close Chatbox"
			data-collapsed="Open Chatbox"></label>
			
		<div class="chat-box-content" id="chat">

		</div>
		
		<div class="chat-box-content" id="nonActivechat">

		</div>
	</div>
	<div id="c"></div>
</body>
</html>