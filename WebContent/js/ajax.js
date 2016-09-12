
function search()
{
	var xmlhttp;

	var text = document.form.text.value;

	var len = document.getElementsByName("type").length;
	var c = "";

	for (var i = 0; i <len; i++) {
		if (document.form.type[i].checked) {
			c = document.form.type[i].value;
		}
	}

	if(text=="")
		{
		document.getElementById("search").innerHTML="";
		}
	else
		{
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange=function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			document.getElementById("search").innerHTML=xmlhttp.responseText;
		}
	};
	xmlhttp.open("GET","search.search?q="+c+"&p="+text,true);
	xmlhttp.send();
		}
}


function searchadmin()
{
	var xmlhttp;

	var text = document.form.text.value;

	var len = document.getElementsByName("type").length;
	var c = "";

	for (var i = 0; i <len; i++) {
		if (document.form.type[i].checked) {
			c = document.form.type[i].value;
		}
	}

	if(text=="")
		{
		document.getElementById("search").innerHTML="";
		}
	else
		{
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange=function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			document.getElementById("search").innerHTML=xmlhttp.responseText;
		}
	};
	xmlhttp.open("GET","search.admin?type="+c+"&text="+text,true);
	xmlhttp.send();
		}
}


function add(id)
{
	document.getElementById(id).innerHTML="Connection Request Sent";
	var xmlhttp;

	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange=function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			document.getElementById(id).innerHTML=xmlhttp.responseText;
		}
	};
	xmlhttp.open("POST","add.search?q="+id,true);
	xmlhttp.send();
}

function removeConnection(id)
{
	var xmlhttp;

	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange=function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			location.reload(true);
		}
	};
	xmlhttp.open("POST","remove.search?q="+id,true);
	xmlhttp.send();
}


function addp(id)
{
	var xmlhttp;
	document.getElementById(id).innerHTML="Successfully Added";
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange=function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			document.getElementById(id).innerHTML=xmlhttp.responseText;
		}
	};
	xmlhttp.open("POST","addp.search?q="+id,true);
	xmlhttp.send();
}

function addDoctor(id)
{
	var xmlhttp;
	document.getElementById(id).innerHTML="Connection Request Sent";
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange=function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			document.getElementById(id).innerHTML=xmlhttp.responseText;
		}
	};
	xmlhttp.open("POST","addDoctor.search?q="+id,true);
	xmlhttp.send();
}



function removeDoctor(id)
{
	var xmlhttp;

	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange=function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			location.reload(true);
		}
	};
	xmlhttp.open("POST","removeDoctor.search?q="+id,true);
	xmlhttp.send();
}


function removep(id)
{
	var xmlhttp;

	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange=function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			location.reload(true);
		}
	};
	xmlhttp.open("POST","removep.search?q="+id,true);
	xmlhttp.send();
}


function accepted(id)
{
	var xmlhttp;

	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange=function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			location.reload(true);
		}
	};
	xmlhttp.open("POST","accept.search?q="+id,true);
	xmlhttp.send();
}

function decline(id)
{
	var xmlhttp;

	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange=function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			location.reload(true);
		}
	};
	xmlhttp.open("POST","decline.search?q="+id,true);
	xmlhttp.send();
}

function acceptDoctor(id)
{
	var xmlhttp;

	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange=function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			location.reload(true);
		}
	};
	xmlhttp.open("POST","acceptDoctor.search?q="+id,true);
	xmlhttp.send();
}

function declineDoctor(id)
{
	var xmlhttp;

	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange=function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			location.reload(true);
		}
	};
	xmlhttp.open("POST","declineDoctor.search?q="+id,true);
	xmlhttp.send();
}



function checkUserName(id)
{
	var xmlhttp;
	var username = document.form.username.value;

	if(username.trim() == ""){
		document.form.username.value = "";
	}else
		{
		if (window.XMLHttpRequest)
		{// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp=new XMLHttpRequest();
		}
		else
		{// code for IE6, IE5
			xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange=function()
		{
			if (xmlhttp.readyState==4 && xmlhttp.status==200)
			{
				
				var result = xmlhttp.responseText;
				if(result == 0){
					
					$("#user1").html("Available");
					$("#user1").data("value",0);
					$("#user1").css({ "color": 'green'});
					
				}else{
					$("#user1").html(username+" Not Available");
					$("#user1").data("value",1);
					$("#user1").css({ "color": 'red'});
					document.form.username.value = "";
					document.form.username.focus();
					
				}	
				
			}
		};
		xmlhttp.open("GET","checkUserName.register?q="+username.trim()+"&p="+id,true);
		xmlhttp.send(null);
		}
	
}


function checkPhone()
{
	var xmlhttp;
	var phone = document.form1.mobile.value;
	var num = /^\d{10}$/;
	if(!num.test(phone))
		{
		document.form1.mobile.value = "";
		$("#phone1").show();
		$("#phone1").css({ "color": 'red'});
		$("#phone1").html(phone+" is incorrect mobile number format");
		}
	else
		{
	var c = "";
	var len = document.getElementsByName("type1").length;
	for (var i = 0; i <len; i++) {
		if (document.form1.type1[i].checked) {
			c = document.form1.type1[i].value;
		}
	}
	
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange=function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			
			var result = xmlhttp.responseText;
			
			if(result!="")
				{
				$("#phone1").show();
				$("#phone1").html(result);
				document.form1.mobile.focus();
				document.form1.mobile.value = "";
				}
			else
				{
				$("#phone1").hide();
				}
			
		}
	};
	xmlhttp.open("GET","checkPhone.register?q="+phone+"&p="+c,true);
	xmlhttp.send(null);
		}
}

function checkEmail()
{
	var xmlhttp;
	var username = document.form1.email.value;
	var c = "";
	var len = document.getElementsByName("type1").length;
	for (var i = 0; i <len; i++) {
		if (document.form1.type1[i].checked) {
			c = document.form1.type1[i].value;
		}
	}
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange=function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			var result = xmlhttp.responseText;
			
			if(result!="")
				{
				document.form1.email.focus();
				$("#mail1").show();
				$("#mail1").html(result);
				document.form1.email.value = "";
				}
			else
				{
				$("#mail1").hide();
				}
			
		}
	};
	xmlhttp.open("GET","checkEmail.register?q="+username+"&p="+c,true);
	xmlhttp.send(null);
}

function cancelOrder(id)
{
	
	var y=window.prompt("Can u provide a comment for order cancellation");
	if(y!=null)
		{
		var xmlhttp;
		if (window.XMLHttpRequest)
		{// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp=new XMLHttpRequest();
		}
		else
		{// code for IE6, IE5
			xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange=function()
		{
			if (xmlhttp.readyState==4 && xmlhttp.status==200)
			{
				location.reload(true);
			}
		};
		xmlhttp.open("POST","cancel.notification?id="+id+"&comment="+y,true);
		xmlhttp.send(null);
		
		}
	
}

function verifyOrder(id)
{
	
	var xmlhttp;
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange=function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			location.reload(true);
		}
	};
	xmlhttp.open("POST","verify.notification?q="+id,true);
	xmlhttp.send(null);
}


function deliverOrder(id)
{
	
	var xmlhttp;
	var r=confirm("Are You Sure You Have Delivered ");
	if (r == true) {
		if (window.XMLHttpRequest)
		{// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp=new XMLHttpRequest();
		}
		else
		{// code for IE6, IE5
			xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange=function()
		{
			if (xmlhttp.readyState==4 && xmlhttp.status==200)
			{
				location.reload(true);
			}
		};
		xmlhttp.open("POST","deliver.notification?q="+id,true);
		xmlhttp.send(null);
	   
	} else {
		document.getElementById(id).innerHTML="<input type=button value=Delivered onclick=deliverOrder("+id+")>";
	}
	
}

function changeImage()
{	
	$("#changeImage").modal();	
}

function doLike(id)
{
	
	var xmlhttp;

	
		if (window.XMLHttpRequest)
		{// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp=new XMLHttpRequest();
		}
		else
		{// code for IE6, IE5
			xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange=function()
		{
			if (xmlhttp.readyState==4 && xmlhttp.status==200)
			{
				document.getElementById("likeValue").innerHTML=xmlhttp.responseText;
				$("#like").data("value",1);
				$('#like').css({ "color": 'aqua'});
			}
		};
		xmlhttp.open("GET","like.notification?q="+id,true);
		xmlhttp.send(null);
	
}

function notLike(id)
{
	var xmlhttp;
	
		if (window.XMLHttpRequest)
		{// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp=new XMLHttpRequest();
		}
		else
		{// code for IE6, IE5
			xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange=function()
		{
			if (xmlhttp.readyState==4 && xmlhttp.status==200)
			{
				document.getElementById("likeValue").innerHTML=xmlhttp.responseText;
				$("#like").data("value",0);
				$('#like').css({ "color": ''});
			}
		};
		xmlhttp.open("GET","unLike.notification?q="+id,true);
		xmlhttp.send(null);
	   
} 
	

function checkPassword()
{
	
	var xmlhttp;
	var text = document.form.oldpassword.value;
	if (window.XMLHttpRequest)
		{// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp=new XMLHttpRequest();
		}
		else
		{// code for IE6, IE5
			xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange=function()
		{
			if (xmlhttp.readyState==4 && xmlhttp.status==200)
			{
				document.getElementById("pass").innerHTML=xmlhttp.responseText;
			}
		};
		xmlhttp.open("GET","checkpassword.login?text="+text,true);
		xmlhttp.send(null);
	   
}
	
function comparepassword()
{
	
		var text1 = document.form.newpassword.value;
		var text2 = document.form.confirmpassword.value;
		
		if(text1!=text2)
			{
			document.getElementById("match").innerHTML="Password Doesnot Match";
			return false;
			}
		
		return true;

	   
}

function adminsearch()
{
	
	var xmlhttp;

	var text = document.form.text.value;

	var len = document.getElementsByName("type").length;
	var c = "";

	for (var i = 0; i <len; i++) {
		if (document.form.type[i].checked) {
			c = document.form.type[i].value;
		}
	}

	if(text=="")
		{
		document.getElementById("search").innerHTML="";
		}
	else
		{
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange=function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			document.getElementById("search").innerHTML=xmlhttp.responseText;
		}
	};
	xmlhttp.open("GET","search.admin?type="+c+"&text="+text,true);
	xmlhttp.send();
		}
}

function getcount()
{
	
	var text = document.getElementById("type").value;
	
	
	var xmlhttp;
	
	if (window.XMLHttpRequest)
		{// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp=new XMLHttpRequest();
		}
		else
		{// code for IE6, IE5
			xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange=function()
		{
			if (xmlhttp.readyState==4 && xmlhttp.status==200)
			{
				document.getElementById("count").innerHTML=xmlhttp.responseText;
			}
		};
		xmlhttp.open("GET","getCount.admin?id="+text,true);
		xmlhttp.send(null);
	   
}


function activate(id)
{
	
	var xmlhttp;
	
	if (window.XMLHttpRequest)
		{// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp=new XMLHttpRequest();
		}
		else
		{// code for IE6, IE5
			xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange=function()
		{
			if (xmlhttp.readyState==4 && xmlhttp.status==200)
			{
				document.getElementById(id).innerHTML=xmlhttp.responseText;
			}
		};
		xmlhttp.open("GET","activateDoctor.admin?id="+id,true);
		xmlhttp.send(null);
	   
}

function activatePharmacy(id)
{
	
	var xmlhttp;
	
	if (window.XMLHttpRequest)
		{// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp=new XMLHttpRequest();
		}
		else
		{// code for IE6, IE5
			xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange=function()
		{
			if (xmlhttp.readyState==4 && xmlhttp.status==200)
			{
				document.getElementById(id).innerHTML=xmlhttp.responseText;
			}
		};
		xmlhttp.open("GET","activatePharmacy.admin?id="+id,true);
		xmlhttp.send(null);
	   
}

function getMorningTime(id)
{
	if(document.getElementById('morning'+id).checked)
		{
		$("#mt"+id).show();
		}
	else
		{
		$("#mt"+id).hide();
		}
}

function getAfternoonTime(id)
{
	if(document.getElementById('afternoon'+id).checked)
		{
		$("#at"+id).show();
		}
	else
		{
		$("#at"+id).hide();
		}
}

function getNightTime(id)
{
	if(document.getElementById('night'+id).checked)
		{
		$("#nt"+id).show();
		}
	else
		{
		$("#nt"+id).hide();
		}
}

function checkPrescriptionForm()
{
	if ($("#errorbp").is(":visible")) {
		var span = document.getElementById("errorbp");
		var txt = document.createTextNode("Please Enter Correct BP Values");
		span.innerText = txt.textContent;
		return false;
	}
	else
		{
		return true;
		}
	
}
function checkBP(age)
{
	
	
	var bpRegex = /^\d{1,3}\/\d{1,3}$/;
	var bp = document.form1.bp.value;
	
	
	
	if(bpRegex.test(bp))
	{
		var res = bp.split("/");
		
		var bp1 = res[0];
		var bp2 = res[1];
		
		
		var message="";
		
		if(age <= 365)
		{
			if(bp1 < 75 || bp2< 50)
			{
				message="HYPOTENSION";
			}
			else
				if(bp1 > 110 || bp2 > 75)
				{
					message="HYPERTENSION";
				}
				else
				{
					message="NORMAL";
				}	
		}
		
		if(age>365 && age<=1825)
		{
			if(bp1 < 80 || bp2< 55)
			{
				message="HYPOTENSION";
			}
			else
				if(bp1 > 110 || bp2 > 79)
				{
					message="HYPERTENSION";
				}
				else
				{
					message="NORMAL";
				}	
		}
		
		if(age>1825 && age<=4745)
		{
			if(bp1 < 90 || bp2< 60)
			{
				message="HYPOTENSION";
			}
			else
				if(bp1 > 115 || bp2 > 80)
				{
					message="HYPERTENSION";
				}
				else
				{
					message="NORMAL";
				}	
		}
		if(age>4745 && age<=6935)
		{
			if(bp1 < 105 || bp2< 73)
			{
				message="HYPOTENSION";
			}
			else
				if(bp1 > 120 || bp2 > 81)
				{
					message="HYPERTENSION";
				}
				else
				{
					message="NORMAL";
				}	
		}
		
		if(age>6935 && age<=8760)
		{
			if(bp1 < 108 || bp2< 75)
			{
				message="HYPOTENSION";
			}
			else
				if(bp1 > 132 || bp2 > 83)
				{
					message="HYPERTENSION";
				}
				else
				{
					message="NORMAL";
				}	
		}
		
		if(age>8760 && age<=10585)
		{
			if(bp1 < 109 || bp2< 76)
			{
				message="HYPOTENSION";
			}
			else
				if(bp1 > 133 || bp2 > 84)
				{
					message="HYPERTENSION";
				}
				else
				{
					message="NORMAL";
				}	
		}
		
		if(age>10585 && age<=12410)
		{
			if(bp1 < 110 || bp2< 77)
			{
				message="HYPOTENSION";
			}
			else
				if(bp1 > 134 || bp2 > 85)
				{
					message="HYPERTENSION";
				}
				else
				{
					message="NORMAL";
				}	
		}
		
		
		if(age>12410 && age<=14235)
		{
			if(bp1 < 111 || bp2< 78)
			{
				message="HYPOTENSION";
			}
			else
				if(bp1 > 135 || bp2 > 86)
				{
					message="HYPERTENSION";
				}
				else
				{
					message="NORMAL";
				}	
		}
		
		if(age>14235 && age<=16060)
		{
			if(bp1 < 112 || bp2< 79)
			{
				message="HYPOTENSION";
			}
			else
				if(bp1 > 137 || bp2 > 87)
				{
					message="HYPERTENSION";
				}
				else
				{
					message="NORMAL";
				}	
		}
		
		if(age>16060 && age<=17885)
		{
			if(bp1 < 115 || bp2< 80)
			{
				message="HYPOTENSION";
			}
			else
				if(bp1 > 139 || bp2 > 88)
				{
					message="HYPERTENSION";
				}
				else
				{
					message="NORMAL";
				}	
		}
		
		if(age>17885 && age<=19710)
		{
			if(bp1 < 116 || bp2< 81)
			{
				message="HYPOTENSION";
			}
			else
				if(bp1 > 142 || bp2 > 89)
				{
					message="HYPERTENSION";
				}
				else
				{
					message="NORMAL";
				}	
		}
		
		if(age>19710 && age<=21535)
		{
			if(bp1 < 118 || bp2< 82)
			{
				message="HYPOTENSION";
			}
			else
				if(bp1 > 144 || bp2 > 90)
				{
					message="HYPERTENSION";
				}
				else
				{
					message="NORMAL";
				}	
		}
		
		if(age>21535)
		{
			if(bp1 < 121 || bp2< 83)
			{
				message="HYPOTENSION";
			}
			else
				if(bp1 > 147 || bp2 > 91)
				{
					message="HYPERTENSION";
				}
				else
				{
					message="NORMAL";
				}	
		}
		
		
		$("#bp").html(message+"<input class=hidden type=text name=bpstatus value="+message+">");
		$("#bp").show();
		$("#errorbp").hide();
		
	}
	else
		{
		    $("#bp").hide();
		    $("#errorbp").show();
		    $("#errorbp").text("Incorrect Format of BP Values");
		}
	
	if(bp == "")
	{
		$("#errorbp").hide();
	}
}

function checkCholesterol(age)
{
	var bp = document.form1.cholesterol.value;
	var message="";
	if(bp>0 && bp<=200)
	{
		message="Desirable";
		$("#cholesterol").html(message+"<input class=hidden type=text name=cholesterolstatus value="+message+">");
	}
	if(bp>200)
	{
		message="Risk";
		$("#cholesterol").html(message+"<input class=hidden type=text name=cholesterolstatus value="+message+">");
	}
	
}

function checkSugar(age)
{
	var bp = document.form1.sugar.value;
	var message="";
	if(bp>0 && bp<100)
	{
		message="Normal";
		$("#sugar").html(message+"<input class=hidden type=text name=sugarstatus value="+message+">");
	}
	if(bp>=100 && bp<126)
	{
		message="Risk";
		$("#sugar").html(message+"<input class=hidden type=text name=sugarstatus value="+message+">");
	}
	if(bp>=126)
	{
		message="Diabetes";
		$("#sugar").html(message+"<input class=hidden type=text name=sugarstatus value="+message+">");
	}
	
	
}

function Vaccine(id)
{
	
	var vaccine = document.getElementsByName("vaccine"+id);
	var c = "";

	for (var i = 0; i <vaccine.length; i++) {
		if (vaccine[i].checked) {
			c = vaccine[i].value;
		}
	}
	if(c==1)
		{
		saveVaccine(id);
		}
	
	if(c==2)
		{
		removeVaccine(id);
		}
}

function saveVaccine(id)
{
	
	var xmlhttp;
	
	if (window.XMLHttpRequest)
		{// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp=new XMLHttpRequest();
		}
		else
		{// code for IE6, IE5
			xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange=function()
		{
			if (xmlhttp.readyState==4 && xmlhttp.status==200)
			{
				
			}
		};
		xmlhttp.open("GET","saveVaccine.patient?id="+id,true);
		xmlhttp.send(null);
	   
}

function removeVaccine(id)
{
	
	var xmlhttp;
	
	if (window.XMLHttpRequest)
		{// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp=new XMLHttpRequest();
		}
		else
		{// code for IE6, IE5
			xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange=function()
		{
			if (xmlhttp.readyState==4 && xmlhttp.status==200)
			{
				
			}
		};
		xmlhttp.open("GET","removeVaccine.patient?id="+id,true);
		xmlhttp.send(null);
	   
}

function resendOTP()
{
	$.ajax({
		method : "GET",
		url : 'resendOTP.notification',
		data: { 
			mobile : mobile	
		},
		success : function(result) {
			$("#errorotp").show();
			var span = document.getElementById("errorotp");
			var txt = document.createTextNode("New OTP has been Send to "+mobile);
			span.innerText = txt.textContent;
		},
		statusCode : {
			500 : function(result) {
				
			}
		}
	});	   
}

function checkOTP()
{
	
	var otp = document.getElementById("resetOTP").value;
	
	$.ajax({
		method : "GET",
		url : 'checkOTP.notification',
		data: { 
			mobile : mobile,
			otp : otp
		},
		success : function(result) {
			if(result==1){
				window.location = "changePassword.notification?mobile="+mobile+"&type="+type;
			}
			else
				{
				$("#errorotp").show();
				var span = document.getElementById("errorotp");
				var txt = document.createTextNode("Sorry OTP Does Not Match ");
				span.innerText = txt.textContent;
				}
		},
		statusCode : {
			500 : function(result) {
				
			}
		}
	});	   
}



var r= 0;

function resetPassword()
{
	
	if(r==0){
		r=1;
		var mobEmail = document.getElementById("resetText").value;
		var len = document.getElementsByName("resetType").length;

		for (var i = 0; i <len; i++) {
			if (document.reset.resetType[i].checked) {
				type = document.reset.resetType[i].value;
			}
		}
		
		var num  =/^\d+$/;
		
		var text = "";
		if(num.test(document.reset.resetText.value))
		{
			text = 1;
		}
		else
		{
			text = 2;
		}
		
		$.ajax({
			method : "GET",
			url : 'resetPassword.notification',
			data: { 
				mobEmail : mobEmail	,
				type : type,
				text : text
			},
			success : function(result) {
				r=0;
				if(result == 1)
				{
					var msg = "";
					if(text == 1)
					{
						msg = "This "+mobEmail+" Mobile Number is Not Registered as ";
						if(type==1)
						{
							msg=msg+"Doctor";
						}
						if(type==2)
						{
							msg=msg+"Patient";
						}
						if(type==3)
						{
							msg=msg+"Pharmacy";
						}
						
						
					}
					
					if(text == 2)
					{
						msg = "This "+mobEmail+" Email is Not Registered as ";
						if(type==1)
						{
							msg=msg+"Doctor";
						}
						if(type==2)
						{
							msg=msg+"Patient";
						}
						if(type==3)
						{
							msg=msg+"Pharmacy";
						}
						
						
					}
					
					$("#reset").show();
					var span = document.getElementById("reset");
					var txt = document.createTextNode(msg);
					span.innerText = txt.textContent;
					
				}
				
				if(result==2)
				{
					mobile = mobEmail;
					$("#changePassword").modal('hide');	
					var span = document.getElementById("mobilo");
					var txt = document.createTextNode(mobile);
					span.innerText = txt.textContent;
					$("#checkOTP").modal();	
				}


				if(result==3)
				{
					$("#resetBody").html("<span class='error-message' id='reset' style='color:black;'>Please check your Email Id: "+mobEmail+" for password reset Link </span>");
				}
				
				
	    	},
			statusCode : {
				500 : function(result) {
					
				}
			}
		});
	}
	
	   
}

function addVaccine()
{
	
	var vaccineName = document.getElementById("vaccineName").value;
	var dueDate = document.getElementById("dueDate").value;
	var dueTime = document.getElementById("dueTime").value;
	$.ajax({
		method : "POST",
		url : 'addVaccine.patient',
		data: { 
			vaccineName : vaccineName,
			dueDate : dueDate,
			dueTime : dueTime
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

function VaccineStatus(patientId,id)
{
	
	var vaccine = document.getElementsByName("vaccine"+patientId+id);
	var c = "";

	for (var i = 0; i <vaccine.length; i++) {
		if (vaccine[i].checked) {
			c = vaccine[i].value;
		}
	}
	if(c==1)
		{
		statusTrue(id);
		}
	
	if(c==2)
		{
		statusFalse(id);
		}
}

function statusTrue(id)
{
	
	var xmlhttp;
	
	if (window.XMLHttpRequest)
		{// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp=new XMLHttpRequest();
		}
		else
		{// code for IE6, IE5
			xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange=function()
		{
			if (xmlhttp.readyState==4 && xmlhttp.status==200)
			{
				
			}
		};
		xmlhttp.open("GET","statusTrue.patient?id="+id,true);
		xmlhttp.send(null);
	   
}

function statusFalse(id)
{
	
	var xmlhttp;
	
	if (window.XMLHttpRequest)
		{// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp=new XMLHttpRequest();
		}
		else
		{// code for IE6, IE5
			xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange=function()
		{
			if (xmlhttp.readyState==4 && xmlhttp.status==200)
			{
				
			}
		};
		xmlhttp.open("GET","statusFalse.patient?id="+id,true);
		xmlhttp.send(null);
	   
}

function deleteVaccine(id)
{
	
	var xmlhttp;
	
	if (window.XMLHttpRequest)
		{// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp=new XMLHttpRequest();
		}
		else
		{// code for IE6, IE5
			xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange=function()
		{
			if (xmlhttp.readyState==4 && xmlhttp.status==200)
			{
				location.reload(true);
			}
		};
		xmlhttp.open("GET","deleteVaccine.patient?id="+id,true);
		xmlhttp.send(null);
	   
}

function detectMobile() {
	
	if( navigator.userAgent.match(/Android/i)
			 || navigator.userAgent.match(/webOS/i)
			 || navigator.userAgent.match(/iPhone/i)
			 || navigator.userAgent.match(/iPad/i)
			 || navigator.userAgent.match(/iPod/i)
			 || navigator.userAgent.match(/BlackBerry/i)
			 || navigator.userAgent.match(/Windows Phone/i)
			 ){
			    return true;
			  }
			 else {
			    return false;
			  }
	}


function addQuestion()
{
	
	var content = document.getElementById("content").value;
	
	$.ajax({
		method : "POST",
		url : 'saveSupport.notification',
		data: { 
			content : content
			
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

