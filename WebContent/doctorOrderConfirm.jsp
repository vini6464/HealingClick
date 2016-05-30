<%@ include file="doctorHeader.jsp"%>
<%@ page import="java.util.*" %>
<%@ page import="java.security.*" %>

<%!
public boolean empty(String s)
	{
		if(s== null || s.trim().equals(""))
			return true;
		else
			return false;
	}
%>
<%!
	public String hashCal(String type,String str){
		byte[] hashseq=str.getBytes();
		StringBuffer hexString = new StringBuffer();
		try{
		MessageDigest algorithm = MessageDigest.getInstance(type);
		algorithm.reset();
		algorithm.update(hashseq);
		byte messageDigest[] = algorithm.digest();
            
		

		for (int i=0;i<messageDigest.length;i++) {
			String hex=Integer.toHexString(0xFF & messageDigest[i]);
			if(hex.length()==1) hexString.append("0");
			hexString.append(hex);
		}
			
		}catch(NoSuchAlgorithmException nsae){ }
		
		return hexString.toString();


	}
%>
<% 	
	String merchant_key="zTy2XBus";
	String salt="02lTcPKRef";
	String action1 ="";
	String base_url="https://test.payu.in";
	int error=0;
	String hashString="";
	
 

	
	Enumeration paramNames = request.getParameterNames();
	Map<String,String> params= new HashMap<String,String>();
    	while(paramNames.hasMoreElements()) 
	{
      		String paramName = (String)paramNames.nextElement();
      
      		String paramValue = request.getParameter(paramName);

		params.put(paramName,paramValue);
	}
	String txnid ="";
	if(empty(params.get("txnid"))){
		Random rand = new Random();
		String rndm = Integer.toString(rand.nextInt())+(System.currentTimeMillis() / 1000L);
		txnid=hashCal("SHA-256",rndm).substring(0,20);
	}
	else
		txnid=params.get("txnid");
    String  udf2 = txnid;
	String txn="abcd";
	String hash="";
	String hashSequence = "key|txnid|amount|productinfo|firstname|email|udf1|udf2|udf3|udf4|udf5|udf6|udf7|udf8|udf9|udf10";
	if(empty(params.get("hash")) && params.size()>0)
	{
		if( empty(params.get("key"))
			|| empty(params.get("txnid"))
			|| empty(params.get("amount"))
			|| empty(params.get("firstname"))
			|| empty(params.get("email"))
			|| empty(params.get("phone"))
			|| empty(params.get("productinfo"))
			|| empty(params.get("surl"))
			|| empty(params.get("furl"))
			|| empty(params.get("service_provider"))
	)
			
			error=1;
		else{
			String[] hashVarSeq=hashSequence.split("\\|");
			
			for(String part : hashVarSeq)
			{
				hashString= (empty(params.get(part)))?hashString.concat(""):hashString.concat(params.get(part));
				hashString=hashString.concat("|");
			}
			hashString=hashString.concat(salt);
			

			 hash=hashCal("SHA-512",hashString);
			 System.out.println("in calc:"+hash);
			action1=base_url.concat("/_payment");
		}
	}
	else if(!empty(params.get("hash")))
	{
		System.out.println(hash);
		hash=params.get("hash");
		action1=base_url.concat("/_payment");
	}
		

%>
<script>
var hash='<%= hash %>';
function submitPayuForm() {
	
	if (hash == '')
		return;

      var payuForm = document.forms.payuForm;
      payuForm.submit();
    }
</script>

	<div class="container-fluid" style="margin-top: 4%;">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="row clearfix">
					<div class="col-md-2 column"></div>
					<div class="col-md-6 column">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h5 class="panel-title">Order Details</h5>
							<c:if test="${not empty error}">
						
					<div class="col-sm-12 column ">
					
					<div class="alert alert-danger fade in">
							    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
							    <strong></strong> ${error}
  					</div>
                    </div> 	
				</c:if>
							</div>
							<div class="panel-body">
								<table class="table table-condensed">
									<tr>
										<td class="field-label col-xs-3 active"><label>
												Pharmacy Name: </label></td>
										<td class="col-md-9">${order.supplierPharmacyName}</td>
									</tr>

									<tr>
										<td class="field-label col-xs-3 active"><label>Type
												of Order:</label></td>
										<td class="col-md-9"><c:if test="${order.orderType == 1}">Normal</c:if>
											<c:if test="${order.orderType == 2}">Urgent</c:if></td>
									</tr>
								</table>

								<table class="table table-condensed">
									<tr>
										<td class="field-label col-xs-6 active"><label>Medicine
												Name </label></td>
										<td class="field-label col-xs-3 active"><label>Quantity</label>
										</td>
										<td class="field-label col-xs-3 active"><label>Cost</label>
										</td>
									</tr>
									<c:forEach var="medicine" items="${order.medicines}">
										<c:if test="${medicine.name ne null}">
											<tr>
												<td class="col-md-6 ">${medicine.name}</td>
												<td class="col-md-3 ">${medicine.quantity}</td>
												<td class="col-md-3 ">${medicine.cost}</td>
											</tr>
										</c:if>
									</c:forEach>
								</table>

								<table class="table table-condensed">
									<tr>
										<td class="field-label col-xs-3 active"><label>Total
												Cost:</label></td>
										<td class="col-md-9">${order.totalCost}Rs.(Including
											${pharmacy.deliveryCharge}Rs deliveryCharge and
											${pharmacy.discount}% discount)</td>
									</tr>
									<tr>
										<td class="field-label col-xs-3 active"><label>
												Payment Type: </label></td>
										<td class="col-md-9"><c:if test="${order.cashType == 2}">Card</c:if>
											<c:if test="${order.cashType == 1}">Cash On Delivery</c:if></td>
									</tr>
									<tr>
										<td class="field-label col-xs-3 active"><label>Delivery
												Date:</label></td>
										<td class="col-md-9">${order.deliveredOn.getDate()}/${order.deliveredOn.getMonth()+1}/${order.deliveredOn.getYear()+1900}
										</td>
									</tr>

								</table>

								<h5 class="shippingdetails">Shipping Details</h5>


								<table class="table table-condensed">
									<tr>
										<td class="field-label col-xs-3 active"><label>Name:</label>
										</td>
										<td class="col-md-9">${order.doctorName}</td>
									</tr>
									<tr>
										<td class="field-label col-xs-3 active"><label>
												Contact:</label></td>
										<td class="col-md-9">${order.phoneNumber}</td>
									</tr>
									<tr>
										<td class="field-label col-xs-3 active"><label>
												Email:</label></td>
										<td class="col-md-9">${order.email}</td>
									</tr>


									<tr>
										<td class="field-label col-xs-3 active"><label>
												Address:</label></td>
										<td class="col-md-9">${order.address1}<br>${order.address2}
										</td>
									</tr>

									<tr>
										<td class="field-label col-xs-3 active"><label>
												Landmark:</label></td>
										<td class="col-md-9">${order.landMark}</td>
									</tr>

									<tr>
										<td class="field-label col-xs-3 active"><label>City:</label>
										</td>
										<td class="col-md-9">${order.city}</td>
									</tr>
									<tr>
										<td class="field-label col-xs-3 active"><label>
												State:</label></td>
										<td class="col-md-9">${order.state}</td>
									</tr>
									<tr>
										<td class="field-label col-xs-3 active"><label>Country:</label>
										</td>
										<td class="col-md-9">${order.country}</td>
									</tr>
									<tr>
										<td class="field-label col-xs-3 active"><label>Pincode:</label>
										</td>
										<td class="col-md-9">${order.pinCode}</td>
									</tr>

								</table>
							</div>

							<c:if test="${order.cashType == 1}">
								<form action="order.order" method="post">
									<div class="panel-footer" style="background-color:;">
										<div class="row">
											<div class="col-md-6 column"></div>

											<div class="col-xs-3 col-sm-3 col-md-3 column">
												<input class="btn btn-info btn-block btn-sm" type="submit"
													style="width: %;" value="Confirm Order">
											</div>
											<div class="col-xs-3 col-sm-3 col-md-3 column">
												<a href="doctor.home" class="btn btn-info btn-block btn-sm"
													type="button" style="">Cancel</a>
											</div>
										</div>
									</div>
								</form>
							</c:if>

							<c:if test="${order.cashType == 2}">
								<div class="panel-footer">
									<div class="row">
										<div class="col-md-6 column"></div>

										<form action="payuform.jsp" method="post" name="payuForm">
											<input type="hidden" name="key" value="<%=merchant_key%>" />
											<input type="hidden" name="hash" value="<%=hash%>" /> <input
												type="hidden" name="txnid" value="<%=txnid%>" /> <input
												type="hidden" name="udf2" value="<%=txnid%>" /> <input
												type="hidden" name="service_provider" value="payu_paisa" />
											<table class="hidden"> 
												<tr>
													<td><b>Mandatory Parameters</b></td>
												</tr>
												<tr>
													<td>Amount:</td>
													<td><input name="amount" value="${order.totalCost}" /></td>
													<td>First Name:</td>
													<td><input name="firstname" id="firstname" value="${doctor.firstName}" /></td>
												</tr>
												<tr>
													<td>Email:</td>
													<td><input name="email" id="email" value="${order.email}" /></td>
													<td>Phone:</td>
													<td><input name="phone" value="${order.phoneNumber}" /></td>
												</tr>
												<tr>
													<td>Product Info:</td>
													<td colspan="3"><input name="productinfo"
														value="Medicine" size="64" /></td>
												</tr>
												<tr>
													<td>Success URI:</td>
													<td colspan="3"><input name="surl" value="http://www.healingclick.com/order.order?tId=<%=txnid%>" size="64" /></td>
												</tr>
												<tr>
													<td>Failure URI:</td>
													<td colspan="3"><input name="furl" value="http://www.healingclick.com/fail.order" size="64" /></td>
												</tr>
												
												
											</table>
												
												<div class="col-xs-3 col-sm-3 col-md-3 column">
												
												<input class="btn btn-info btn-block btn-sm" type="submit"
													style="width: %;" value="Proceed To Payment">
													
											</div>
											<div class="col-xs-3 col-sm-3 col-md-3 column">
												<a href="doctor.home" class="btn btn-info btn-block btn-sm"
													type="button" style="">Cancel</a>
											</div>
												
										</form>
										</div>
										</div>
										
							</c:if>

						</div>

					</div>
					<%@ include file="doctorAside.jsp"%>
				</div>
</body>
</html>










