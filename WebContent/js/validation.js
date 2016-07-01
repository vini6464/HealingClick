function doctor()
{
	var alpha = /^[a-z]+$/i;
	var alphas = /^[a-z.]+$/i;
	var number = /^\d{10}$/;
	var alphanumeric=/^[a-z0-9., /#:()]+$/i;
	var user1=/^[a-z0-9]+$/i;

	var pin=/^[0-9]{6}/;

	if(!user1.test(document.form.username.value))
	{
		$("#user").show();
		document.form.username.focus();
		var span = document.getElementById("user");
		var txt = document.createTextNode("Please Enter only Alphabets and Numbers");
		span.innerText = txt.textContent;

		return false;
	}
	else
	{
		$("#user").hide();
	}



	if(document.form.gender.value == 0)
	{
		$("#gender").show();
		document.form.gender.focus();
		var span = document.getElementById("gender");
		var txt = document.createTextNode("Please select Gender");
		span.innerText = txt.textContent;

		return false;
	}
	else
	{
		$("#gender").hide();
	}

	

	if(document.form.bloodgroup.value == 0)
	{
		$("#bloodgroup").show();
		document.form.bloodgroup.focus();
		var span = document.getElementById("bloodgroup");
		var txt = document.createTextNode("Please select Bloodgroup");
		span.innerText = txt.textContent;

		return false;


	}
	else
	{
		$("#bloodgroup").hide();
	}


	if(!alphanumeric.test(document.form.qualification.value))
	{
		$("#qualification").show();
		document.form.qualification.focus();
		var span = document.getElementById("qualification");
		var txt = document.createTextNode("Please Enter only Alphabets");
		span.innerText = txt.textContent;

		return false;

	}
	else
	{
		$("#qualification").hide();
	}


	if(!alphanumeric.test(document.form.speciality.value))
	{
		$("#speciality").show();
		document.form.speciality.focus();
		var span = document.getElementById("speciality");
		var txt = document.createTextNode("Please Enter only Alphabets");
		span.innerText = txt.textContent;

		return false;
	}
	else
	{
		$("#speciality").hide();
	}


	if(!alphanumeric.test(document.form.worklocation.value))
	{
		$("#worklocation").show();
		document.form.worklocation.focus();
		var span = document.getElementById("worklocation");
		var txt = document.createTextNode("Please Enter only Alphabets and Numbers");
		span.innerText = txt.textContent;

		return false;
	}
	else
	{
		$("#worklocation").hide();
	}


	if(!alphanumeric.test(document.form.address1.value))
	{
		$("#address1").show();
		document.form.address1.focus();
		var span = document.getElementById("address1");
		var txt = document.createTextNode("Please Enter only Alphabets and Numbers");
		span.innerText = txt.textContent;

		return false;
	}
	else
	{
		$("#address1").hide();
	}


	if(!alphanumeric.test(document.form.address2.value))
	{
		$("#address2").show();
		document.form.address2.focus();
		var span = document.getElementById("address2");
		var txt = document.createTextNode("Please Enter only Alphabets and Numbers");
		span.innerText = txt.textContent;

		return false;


	}
	else
	{
		$("#address2").hide();
	}


	if(!alphanumeric.test(document.form.landmark.value))
	{
		$("#landmark").show();
		document.form.landmark.focus();
		var span = document.getElementById("landmark");
		var txt = document.createTextNode("Please Enter only Alphabets and Numbers");
		span.innerText = txt.textContent;

		return false;
	}
	else
	{
		$("#landmark").hide();
	}

	if(document.form.country.value == 0)
	{
		$("#country1").show();
		document.form.country.focus();
		var span = document.getElementById("country1");
		var txt = document.createTextNode("Please Select Country");
		span.innerText = txt.textContent;

		return false;


	}
	else
	{
		$("#country1").hide();
	}
	
	if(document.form.state.value == 0)
	{
		$("#state1").show();
		document.form.state.focus();
		var span = document.getElementById("state1");
		var txt = document.createTextNode("Please Select State");
		span.innerText = txt.textContent;

		return false;


	}
	else
	{
		$("#state1").hide();
	}
	
	
	if(!alphas.test(document.form.city.value))
	{
		$("#city1").show();
		document.form.city.focus();
		var span = document.getElementById("city1");
		var txt = document.createTextNode("Please Select City");
		span.innerText = txt.textContent;

		return false;


	}
	else
	{
		$("#city1").hide();
	}

	if(!pin.test(document.form.pincode.value))
	{
		$("#pincode").show();
		document.form.pincode.focus();
		var span = document.getElementById("pincode");
		var txt = document.createTextNode("Please Enter only 6 Digits");
		span.innerText = txt.textContent;

		return false;

	}
	else
	{
		$("#pincode").hide();
	}


	/*if(!number.test(document.form.landline.value))
	{
		$("#landline").show();
		var span = document.getElementById("landline");
		var txt = document.createTextNode("Please Enter only 10 Digits");
		span.innerText = txt.textContent;

		return false;

	}
	else
	{
		$("#landline").hide();
	}*/


	return true;

}

function postdoctor()
{
	var alpha = /^[a-z]+$/i;
	var alphas = /^[a-z.]+$/i;
	var number = /^\d{10}$/;
	var alphanumeric=/^[a-z0-9., /#:()]+$/i;


	var pin=/[0-9]{6}/;


	if(!alpha.test(document.form.firstname.value))
	{
		$("#firstname").show();
		document.form.firstname.focus();
		var span = document.getElementById("firstname");
		var txt = document.createTextNode("Please Enter only Alphabets");
		span.innerText = txt.textContent;

		return false;

	}
	else
	{
		$("#firstname").hide();
	}

	if(!alpha.test(document.form.lastname.value))
	{
		$("#lastname").show();
		document.form.lastname.focus();
		var span = document.getElementById("lastname");
		var txt = document.createTextNode("Please Enter only Alphabets");
		span.innerText = txt.textContent;

		return false;

	}
	else
	{
		$("#lastname").hide();
	}


	if(document.form.gender.value == 0)
	{
		$("#gender").show();
		document.form.gender.focus();
		var span = document.getElementById("gender");
		var txt = document.createTextNode("Please select Gender");
		span.innerText = txt.textContent;

		return false;
	}
	else
	{
		$("#gender").hide();
	}

	if(document.form.bloodgroup.value == 0)
	{
		$("#bloodgroup").show();
		document.form.bloodgroup.focus();
		var span = document.getElementById("bloodgroup");
		var txt = document.createTextNode("Please select Bloodgroup");
		span.innerText = txt.textContent;

		return false;
	}
	else
	{
		$("#bloodgroup").hide();
	}

	if(!alphanumeric.test(document.form.qualification.value))
	{
		$("#qualification").show();
		document.form.qualification.focus();
		var span = document.getElementById("qualification");
		var txt = document.createTextNode("Please Enter only Alphabets");
		span.innerText = txt.textContent;

		return false;

	}
	else
	{
		$("#qualification").hide();
	}

	if(!alphanumeric.test(document.form.speciality.value))
	{
		$("#speciality").show();
		document.form.speciality.focus();
		var span = document.getElementById("speciality");
		var txt = document.createTextNode("Please Enter only Alphabets");
		span.innerText = txt.textContent;

		return false;
	}
	else
	{
		$("#speciality").hide();
	}

	if(!alphanumeric.test(document.form.worklocation.value))
	{
		$("#worklocation").show();
		document.form.worklocation.focus();
		var span = document.getElementById("worklocation");
		var txt = document.createTextNode("Please Enter only Alphabets and Numbers");
		span.innerText = txt.textContent;

		return false;
	}
	else
	{
		$("#worklocation").hide();
	}

	if(!alphanumeric.test(document.form.address1.value))
	{
		$("#address1").show();
		document.form.address1.focus();
		var span = document.getElementById("address1");
		var txt = document.createTextNode("Please Enter only Alphabets and Numbers");
		span.innerText = txt.textContent;

		return false;
	}
	else
	{
		$("#address1").hide();
	}

	if(!alphanumeric.test(document.form.address2.value))
	{
		$("#address2").show();
		document.form.address2.focus();
		var span = document.getElementById("address2");
		var txt = document.createTextNode("Please Enter only Alphabets and Numbers");
		span.innerText = txt.textContent;

		return false;


	}
	else
	{
		$("#address2").hide();
	}

	if(!alphanumeric.test(document.form.landmark.value))
	{
		$("#landmark").show();
		document.form.landmark.focus();
		var span = document.getElementById("landmark");
		var txt = document.createTextNode("Please Enter only Alphabets and Numbers");
		span.innerText = txt.textContent;

		return false;
	}
	else
	{
		$("#landmark").hide();
	}

	if(document.form.country.value == 0)
	{
		$("#country1").show();
		document.form.country.focus();
		var span = document.getElementById("country1");
		var txt = document.createTextNode("Please Select Country");
		span.innerText = txt.textContent;

		return false;


	}
	else
	{
		$("#country1").hide();
	}
	
	if(document.form.state.value == 0)
	{
		$("#state1").show();
		document.form.state.focus();
		var span = document.getElementById("state1");
		var txt = document.createTextNode("Please Select State");
		span.innerText = txt.textContent;

		return false;


	}
	else
	{
		$("#state1").hide();
	}
	
	
	if(!alphas.test(document.form.city.value))
	{
		$("#city1").show();
		document.form.city.focus();
		var span = document.getElementById("city1");
		var txt = document.createTextNode("Please Select City");
		span.innerText = txt.textContent;

		return false;


	}
	else
	{
		$("#city1").hide();
	}

	if(!pin.test(document.form.pincode.value))
	{
		$("#pincode").show();
		document.form.pincode.focus();
		var span = document.getElementById("pincode");
		var txt = document.createTextNode("Please Enter only 6 Digits");
		span.innerText = txt.textContent;

		return false;

	}
	else
	{
		$("#pincode").hide();
	}


	if(!alphanumeric.test(document.form.aboutme.value))
	{
		$("#aboutme").show();
		document.form.aboutme.focus();
		var span = document.getElementById("aboutme");
		var txt = document.createTextNode("Please Enter only Alphabets and Numbers");
		span.innerText = txt.textContent;

		return false;

	}
	else
	{
		$("#aboutme").hide();
	}

	return true;

}

function patient()
{
	var alpha = /^[a-z]+$/i;
	var alphas = /^[a-z.]+$/i;
	var number = /[0-9]{10}/;
	var alphanumeric=/^[a-z0-9., /#:()]+$/i;
	var user1=/^[a-z0-9]+$/i;
	var dob1 =/^([0-9]{2})\/([0-9]{2})\/([0-9]{4})$/;
	var pin=/[0-9]{6}/;


	if(!user1.test(document.form.username.value))
	{
		$("#user").show();
		document.form.username.focus();
		var span = document.getElementById("user");
		var txt = document.createTextNode("Please Enter only Alphabets and Numbers");
		span.innerText = txt.textContent;

		return false;
	}
	else
	{
		$("#user").hide();
	}
	
	if(document.form.gender.value == 0)
	{
		$("#gender").show();
		document.form.gender.focus();
		var span = document.getElementById("gender");
		var txt = document.createTextNode("Please select Gender");
		span.innerText = txt.textContent;

		return false;
	}
	else
	{
		$("#gender").hide();
	}

	/*if(document.form.bloodgroup.value == 0)
	{
		var span = document.getElementById("bloodgroup");
		var txt = document.createTextNode("Please select Bloodgroup");
		span.innerText = txt.textContent;

        return false;
	}*/

	if(!alphanumeric.test(document.form.address1.value))
	{
		$("#address1").show();
		document.form.address1.focus();
		var span = document.getElementById("address1");
		var txt = document.createTextNode("Please Enter only Alphabets and Numbers");
		span.innerText = txt.textContent;

		return false;
	}
	else
	{
		$("#address1").hide();
	}

	if(!alphanumeric.test(document.form.address2.value))
	{
		$("#address2").show();
		document.form.address2.focus();
		var span = document.getElementById("address2");
		var txt = document.createTextNode("Please Enter only Alphabets and Numbers");
		span.innerText = txt.textContent;

		return false;


	}
	else
	{
		$("#address2").hide();
	}

	if(!alphanumeric.test(document.form.landmark.value))
	{
		$("#landmark").show();
		document.form.landmark.focus();
		var span = document.getElementById("landmark");
		var txt = document.createTextNode("Please Enter only Alphabets and Numbers");
		span.innerText = txt.textContent;

		return false;
	}
	else
	{
		$("#landmark").hide();
	}

	if(document.form.country.value == 0)
	{
		$("#country1").show();
		document.form.country.focus();
		var span = document.getElementById("country1");
		var txt = document.createTextNode("Please Select Country");
		span.innerText = txt.textContent;

		return false;


	}
	else
	{
		$("#country1").hide();
	}
	
	if(document.form.state.value == 0)
	{
		$("#state1").show();
		document.form.state.focus();
		var span = document.getElementById("state1");
		var txt = document.createTextNode("Please Select State");
		span.innerText = txt.textContent;

		return false;


	}
	else
	{
		$("#state1").hide();
	}
	
	
	if(!alphas.test(document.form.city.value))
	{
		$("#city1").show();
		document.form.city.focus();
		var span = document.getElementById("city1");
		var txt = document.createTextNode("Please Select City");
		span.innerText = txt.textContent;

		return false;


	}
	else
	{
		$("#city1").hide();
	}

	if(!pin.test(document.form.pincode.value))
	{
		$("#pincode").show();
		document.form.pincode.focus();
		var span = document.getElementById("pincode");
		var txt = document.createTextNode("Please Enter only 6 Digits");
		span.innerText = txt.textContent;

		return false;

	}
	else
	{
		$("#pincode").hide();
	}


	/*if(!number.test(document.form.contact1.value))
	{
		$("#contact1").show();
		var span = document.getElementById("contact1");
		var txt = document.createTextNode("Please Enter only 10 Digits");
		span.innerText = txt.textContent;

		return false;

	}
	else
	{
		$("#contact1").hide();
	}

	if(!number.test(document.form.contact2.value))
	{
		$("#contact2").show();
		var span = document.getElementById("contact2");
		var txt = document.createTextNode("Please Enter only 10 Digits");
		span.innerText = txt.textContent;

		return false;

	}
	else
	{
		$("#contact2").hide();
	}*/

	/*if(!alphanumeric.test(document.form.aboutme.value))
	{
		var span = document.getElementById("aboutme");
		var txt = document.createTextNode("Please Enter only Alphabets and Numbers");
		span.innerText = txt.textContent;

        return false;

	}*/

	return true;

}

function postpatient()
{
	var alpha = /^[a-z]+$/i;
	var alphas = /[a-zA-Z.,]+/;
	var number = /^\d{10}$/;
	var alphanumeric=/^[a-z0-9., /#:()]+$/i;
	var user=/^[a-z0-9]+$/i;
	var dob1 =/^([0-9]{2})\/([0-9]{2})\/([0-9]{4})$/;
	var pin=/[0-9]{6}/;


	if(!alpha.test(document.form.firstname.value))
	{
		$("#firstname").show();
		document.form.firstname.focus();
		var span = document.getElementById("firstname");
		var txt = document.createTextNode("Please Enter only Alphabets");
		span.innerText = txt.textContent;

		return false;

	}
	else
	{
		$("#firstname").hide();
	}

	if(!alpha.test(document.form.lastname.value))
	{
		$("#lastname").show();
		document.form.lastname.focus();
		var span = document.getElementById("lastname");
		var txt = document.createTextNode("Please Enter only Alphabets");
		span.innerText = txt.textContent;
		return false;

	}
	else
	{
		$("#lastname").hide();
	}


	if(document.form.gender.value == 0)
	{
		$("#gender").show();
		document.form.gender.focus();
		var span = document.getElementById("gender");
		var txt = document.createTextNode("Please select Gender");
		span.innerText = txt.textContent;

		return false;
	}
	else
	{
		$("#gender").hide();
	}

	if(document.form.bloodgroup.value == 0)
	{
		$("#bloodgroup").show();
		document.form.bloodgroup.focus();
		var span = document.getElementById("bloodgroup");
		var txt = document.createTextNode("Please select Bloodgroup");
		span.innerText = txt.textContent;

		return false;
	}
	else
	{
		$("#bloodgroup").hide();
	}



	if(!alphanumeric.test(document.form.address1.value))
	{
		$("#address1").show();
		document.form.address1.focus();
		var span = document.getElementById("address1");
		var txt = document.createTextNode("Please Enter only Alphabets and Numbers");
		span.innerText = txt.textContent;

		return false;
	}
	else
	{
		$("#address1").hide();
	}

	if(!alphanumeric.test(document.form.address2.value))
	{
		$("#address2").show();
		document.form.address2.focus();
		var span = document.getElementById("address2");
		var txt = document.createTextNode("Please Enter only Alphabets and Numbers");
		span.innerText = txt.textContent;

		return false;


	}
	else
	{
		$("#address2").hide();
	}

	if(!alphanumeric.test(document.form.landmark.value))
	{
		$("#landmark").show();
		document.form.landmark.focus();
		var span = document.getElementById("landmark");
		var txt = document.createTextNode("Please Enter only Alphabets and Numbers");
		span.innerText = txt.textContent;

		return false;
	}
	else
	{
		$("#landmark").hide();
	}

	if(document.form.country.value == 0)
	{
		$("#country1").show();
		document.form.country.focus();
		var span = document.getElementById("country1");
		var txt = document.createTextNode("Please Select Country");
		span.innerText = txt.textContent;

		return false;


	}
	else
	{
		$("#country1").hide();
	}
	
	if(document.form.state.value == 0)
	{
		$("#state1").show();
		document.form.state.focus();
		var span = document.getElementById("state1");
		var txt = document.createTextNode("Please Select State");
		span.innerText = txt.textContent;

		return false;


	}
	else
	{
		$("#state1").hide();
	}
	
	
	if(!alphas.test(document.form.city.value))
	{
		$("#city1").show();
		document.form.city.focus();
		var span = document.getElementById("city1");
		var txt = document.createTextNode("Please Select City");
		span.innerText = txt.textContent;

		return false;


	}
	else
	{
		$("#city1").hide();
	}

	if(!pin.test(document.form.pincode.value))
	{
		$("#pincode").show();
		document.form.pincode.focus();
		var span = document.getElementById("pincode");
		var txt = document.createTextNode("Please Enter only 6 Digits");
		span.innerText = txt.textContent;

		return false;

	}
	else
	{
		$("#pincode").hide();
	}

	if(!number.test(document.form.contact1.value))
	{
		$("#contact1").show();
		document.form.contact1.focus();
		var span = document.getElementById("contact1");
		var txt = document.createTextNode("Please Enter only 10 Digits");
		span.innerText = txt.textContent;

		return false;

	}
	else
	{
		$("#contact1").hide();
	}

	if(!number.test(document.form.contact2.value))
	{
		$("#contact2").show();
		document.form.contact2.focus();
		var span = document.getElementById("contact2");
		var txt = document.createTextNode("Please Enter only 10 Digits");
		span.innerText = txt.textContent;

		return false;

	}
	else
	{
		$("#contact2").hide();
	}

	if(!alphanumeric.test(document.form.aboutme.value))
	{
		$("#aboutme").show();
		document.form.aboutme.focus();
		var span = document.getElementById("aboutme");
		var txt = document.createTextNode("Please Enter only Alphabets and Numbers");
		span.innerText = txt.textContent;

		return false;

	}
	else
	{
		$("#aboutme").hide();
	}

	return true;

}

function pharmacy1()
{

	var alpha = /^[a-z]+$/i;
	var alphas = /^[a-z., ]+$/i;
	var number = /^\d{10}$/;
	var alphanumeric=/^[a-z0-9., /#:()]+$/i;
	var user1=/^[a-z0-9]+$/i;
	var dob =/[0-9]{2}[0-9]{2}[0-9]{4}/;
	var pin=/[0-9]{6}/;


	if(!user1.test(document.form.username.value))
	{
		$("#user").show();
		document.form.username.focus();
		var span = document.getElementById("user");
		var txt = document.createTextNode("Please Enter only Alphabets and Numbers");
		span.innerText = txt.textContent;

		return false;
	}
	else
	{
		$("#user").hide();
	}

	/*if(document.form.gender.value == 0)
	{
		var span = document.getElementById("gender");
		var txt = document.createTextNode("Please select Gender");
		span.innerText = txt.textContent;

        return false;
	}

	if(document.form.bloodgroup.value == 0)
	{
		var span = document.getElementById("bloodgroup");
		var txt = document.createTextNode("Please select Bloodgroup");
		span.innerText = txt.textContent;

        return false;
	}*/

	if(!alphas.test(document.form.pharmacy.value))
	{
		$("#pharmacy").show();
		document.form.pharmacy.focus();
		var span = document.getElementById("pharmacy");
		var txt = document.createTextNode("Please Enter only Alphabets");
		span.innerText = txt.textContent;

		return false;

	}
	else
	{
		$("#pharmacy").hide();
	}


	if(!alphanumeric.test(document.form.address1.value))
	{
		$("#address1").show();
		document.form.address1.focus();
		var span = document.getElementById("address1");
		var txt = document.createTextNode("Please Enter only Alphabets and Numbers");
		span.innerText = txt.textContent;

		return false;
	}
	else
	{
		$("#address1").hide();
	}

	if(!alphanumeric.test(document.form.address2.value))
	{
		$("#address2").show();
		document.form.address2.focus();
		var span = document.getElementById("address2");
		var txt = document.createTextNode("Please Enter only Alphabets and Numbers");
		span.innerText = txt.textContent;

		return false;


	}
	else
	{
		$("#address2").hide();
	}

	if(!alphanumeric.test(document.form.landmark.value))
	{
		$("#landmark").show();
		document.form.landmark.focus();
		var span = document.getElementById("landmark");
		var txt = document.createTextNode("Please Enter only Alphabets and Numbers");
		span.innerText = txt.textContent;

		return false;
	}
	else
	{
		$("#landmark").hide();
	}

	if(document.form.country.value == 0)
	{
		$("#country1").show();
		document.form.country.focus();
		var span = document.getElementById("country1");
		var txt = document.createTextNode("Please Select Country");
		span.innerText = txt.textContent;

		return false;


	}
	else
	{
		$("#country1").hide();
	}
	
	if(document.form.state.value == 0)
	{
		$("#state1").show();
		document.form.state.focus();
		var span = document.getElementById("state1");
		var txt = document.createTextNode("Please Select State");
		span.innerText = txt.textContent;

		return false;


	}
	else
	{
		$("#state1").hide();
	}
	
	
	if(!alphas.test(document.form.city.value))
	{
		$("#city1").show();
		document.form.city.focus();
		var span = document.getElementById("city1");
		var txt = document.createTextNode("Please Select City");
		span.innerText = txt.textContent;

		return false;


	}
	else
	{
		$("#city1").hide();
	}

	if(!pin.test(document.form.pincode.value))
	{
		$("#pincode").show();
		document.form.pincode.focus();
		var span = document.getElementById("pincode");
		var txt = document.createTextNode("Please Enter only 6 Digits");
		span.innerText = txt.textContent;

		return false;

	}
	else
	{
		$("#pincode").hide();
	}

	/*if(!number.test(document.form.landline.value))
	{
		$("#landline").show();
		var span = document.getElementById("landline");
		var txt = document.createTextNode("Please Enter only 10 Digits");
		span.innerText = txt.textContent;

		return false;

	}
	else
	{
		$("#landline").hide();
	}*/

	if(!alphas.test(document.form.licensedto.value))
	{
		$("#licensedto").show();
		document.form.licensedto.focus();
		var span = document.getElementById("licensedto");
		var txt = document.createTextNode("Please Enter only Alphabets");
		span.innerText = txt.textContent;

		return false;
	}
	else
	{
		$("#licensedto").hide();
	}

	if(!alphas.test(document.form.proprietorname.value))
	{
		$("#proprietorname").show();
		document.form.proprietorname.focus();
		var span = document.getElementById("proprietorname");
		var txt = document.createTextNode("Please Enter only Alphabets");
		span.innerText = txt.textContent;

		return false;

	}
	else
	{
		$("#proprietorname").hide();
	}

	if(!alphanumeric.test(document.form.proprietoraddress.value))
	{
		$("#proprietoraddress").show();
		document.form.proprietoraddress.focus();
		var span = document.getElementById("proprietoraddress");
		var txt = document.createTextNode("Please Enter only Alphabets and Numbers");
		span.innerText = txt.textContent;
		;
		return false;

	}
	else
	{
		$("#proprietoraddress").hide();
	}

/*	if(!number.test(document.form.proprietornumber.value))
	{
		$("#proprietornumber").show();
		var span = document.getElementById("proprietornumber");
		var txt = document.createTextNode("Please Enter only 10 Digits");
		span.innerText = txt.textContent;

		return false;


	}
	else
	{
		$("#proprietornumber").hide();
	}*/

	/*if(!alphanumeric.test(document.form.aboutme.value))
	{
		var span = document.getElementById("aboutme");
		var txt = document.createTextNode("Please Enter only Alphabets and Numbers");
		span.innerText = txt.textContent;

        return false;

	}*/

	return true;	
}

function postpharmacy()
{
	var alpha = /^[a-z]+$/i;
	var alphas = /^[a-z., ]+$/i;
	var number = /^\d{10}$/;
	var alphanumeric=/^[a-z0-9., /#:()]+$/i;
	var user=/^[a-z0-9]+$/i;
	var dob =/[0-9]{2}[0-9]{2}[0-9]{4}/;
	var pin=/[0-9]{6}/;



	if(!alphas.test(document.form.pharmacy.value))
	{
		$("#pharmacy").show();
		document.form.pharmacy.focus();
		var span = document.getElementById("pharmacy");
		var txt = document.createTextNode("Please Enter only Alphabets");
		span.innerText = txt.textContent;

		return false;

	}
	else
	{
		$("#pharmacy").hide();
	}



	if(!alphanumeric.test(document.form.address1.value))
	{
		$("#address1").show();
		document.form.address1.focus();
		var span = document.getElementById("address1");
		var txt = document.createTextNode("Please Enter only Alphabets and Numbers");
		span.innerText = txt.textContent;
		document.form.address1.focus() ;
		return false;
	}
	else
	{
		$("#address1").hide();
	}

	if(!alphanumeric.test(document.form.address2.value))
	{
		$("#address2").show();
		document.form.address2.focus();
		var span = document.getElementById("address2");
		var txt = document.createTextNode("Please Enter only Alphabets and Numbers");
		span.innerText = txt.textContent;

		return false;


	}
	else
	{
		$("#address2").hide();
	}

	if(!alphanumeric.test(document.form.landmark.value))
	{
		$("#landmark").show();
		document.form.landmark.focus();
		var span = document.getElementById("landmark");
		var txt = document.createTextNode("Please Enter only Alphabets and Numbers");
		span.innerText = txt.textContent;

		return false;
	}
	else
	{
		$("#landmark").hide();
	}

	if(document.form.country.value == 0)
	{
		
		$("#country1").show();
		document.form.country.focus();
		var span = document.getElementById("country1");
		var txt = document.createTextNode("Please Select Country");
		span.innerText = txt.textContent;

		return false;


	}
	else
	{
		$("#country1").hide();
	}
	
	if(document.form.state.value == 0)
	{
		$("#state1").show();
		document.form.state.focus();
		var span = document.getElementById("state1");
		var txt = document.createTextNode("Please Select State");
		span.innerText = txt.textContent;

		return false;


	}
	else
	{
		$("#state1").hide();
	}
	
	
	if(!alphas.test(document.form.city.value))
	{
		$("#city1").show();
		document.form.city.focus();
		var span = document.getElementById("city1");
		var txt = document.createTextNode("Please Select City");
		span.innerText = txt.textContent;

		return false;


	}
	else
	{
		$("#city1").hide();
	}

	if(!pin.test(document.form.pincode.value))
	{
		$("#pincode").show();
		document.form.pincode.focus();
		var span = document.getElementById("pincode");
		var txt = document.createTextNode("Please Enter only 6 Digits");
		span.innerText = txt.textContent;

		return false;

	}
	else
	{
		$("#pincode").hide();
	}

	if(!alphanumeric.test(document.form.aboutme.value))
	{
		$("#aboutme").show();
		document.form.aboutme.focus();
		var span = document.getElementById("aboutme");
		var txt = document.createTextNode("Please Enter only Alphabets and Numbers");
		span.innerText = txt.textContent;

		return false;

	}
	else
	{
		$("#aboutme").hide();
	}

	return true;

}


function home()
{
	var alpha = /^[a-z]+$/i;

	var number = /^\d{10}$/;
	var mail = /\S+@\S+\.\S+/;

	var dob1 =/^([0-9]{2})\/([0-9]{2})\/([0-9]{4})$/;



	if(!alpha.test(document.form1.firstname.value))
	{
		$("#firstname").show();
		document.form1.firstname.focus();
		var span = document.getElementById("firstname");
		var txt = document.createTextNode("Please Enter only Alphabets");
		span.innerText = txt.textContent;
		return false;
	}
	else
	{
		$("#firstname").hide();
	}

	if(!alpha.test(document.form1.lastname.value))
	{
		$("#lastname").show();
		document.form1.lastname.focus();
		var span = document.getElementById("lastname");
		var txt = document.createTextNode("Please Enter only Alphabets");
		span.innerText = txt.textContent;

		return false;

	}
	else
	{
		$("#lastname").hide();
	}

	if(!mail.test(document.form1.email.value))
	{
		$("#mail").show();
		document.form1.email.focus();
		var span = document.getElementById("mail");
		var txt = document.createTextNode("Please Enter Email Properly");
		span.innerText = txt.textContent;

		return false;

	}
	else
	{
		$("#mail").hide();
	}

	if(!number.test(document.form1.mobile.value))
	{
		$("#phone").show();
		document.form1.mobile.focus();
		var span = document.getElementById("phone");
		var txt = document.createTextNode("Please Enter only 10 Digits");
		span.innerText = txt.textContent;

		return false;

	}
	else
	{
		$("#phone").hide();
	}

	if ($("#mail1").is(":visible")) {
		return false;
	}

	if ($("#phone1").is(":visible")) {
		return false;
	}

	return true;

}


function checkReset()
{
	

	var num  =/^\d+$/;
	var number = /^\d{10}$/;
	var mail = /\S+@\S+\.\S+/;

	

	
	
	if(num.test(document.reset.resetText.value))
	{
	
		if(!number.test(document.reset.resetText.value))
		{
			$("#reset").show();
			var span = document.getElementById("reset");
			var txt = document.createTextNode("Please Enter only 10 Digits for Mobile Number");
			span.innerText = txt.textContent;

			return false;

		}
		else
			{
			$("#reset").hide();
			}
	}
	
	if(!num.test(document.reset.resetText.value))
	{
	
		if(!mail.test(document.reset.resetText.value))
		{
			$("#reset").show();
			var span = document.getElementById("reset");
			var txt = document.createTextNode("Please Enter Email Properly");
			span.innerText = txt.textContent;

			return false;

		}
		else
			{
			$("#reset").hide();
			}
	}
	

	return true;

}


function checkName(type)
{
	var alpha = /^[a-z]+$/i;

	if(type==1)
	{
		if(!alpha.test(document.form1.firstname.value))
		{		
			$("#firstname").show();
			
			var span = document.getElementById("firstname");
			var txt = document.createTextNode("Please Enter only Alphabets");
			span.innerText = txt.textContent;

		}
		else
		{
			$("#firstname").hide();
		}
	}


	if(type==2)
	{
		if(!alpha.test(document.form1.lastname.value))
		{		
			$("#lastname").show();
			
			var span = document.getElementById("lastname");
			var txt = document.createTextNode("Please Enter only Alphabets");
			span.innerText = txt.textContent;

		}
		else
		{
			$("#lastname").hide();
		}
	}


}


