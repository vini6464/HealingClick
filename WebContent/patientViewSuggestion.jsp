<%@ include file="patientHeader.jsp" %>

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

 <script src="js/bootstrap.min.js"></script>
  <script>
 $(document).ready(function() {
	 var insert = "${insert}";
		if(insert == 1)
			{
				window.history.pushState("", "", 'getSuggestion.suggestion?id=${post.id}');
			}
	 });
 
 </script>
	<div class="container">
	<div class="row clearfix">
	
	<div class="col-xs-12 col-sm-12 col-md-2 column"></div>
	
		<div class="col-md-8 column">
			<div class="panel panel-default">
					${error}
					<div class="panel-body">		
	<div class="row clearfix">
	    
		<!-- div class="col-xs-2 col-sm-2 col-md-1 column">
			<img class="img-thumbnail"alt="140x140" src="image/d.jpg" / style="width:90px;height:80px;">
		</div-->
		
		
		<div class="col-xs-12 col-sm-12 col-md-12 column">
			<h5 class="post_name">
				<a href="#" style="text-decoration:none;">${post.name}</a></h5>
				<hr class="post-hr"><h5 class="pull-right"><small>To</small>
				<a class="post_by"
									href="patientViewDoctor.profile?id=${post.doctorId}">
									Dr. ${post.doctorName}</a></h5>
				
			
		</div>
		
	    
	</div>
					</h3>
				</div>
				
				<p class="post_content">	${post.content} <br>
					
					<c:if test="${not empty post.forumImage}">
										<img alt="300x200" height=300 width=300 src="${post.forumImage}" />
										
									</c:if>
					
					</p>
				
				<div class="panel-footer">
				<div class="row">
				
				<div class="col-xs-12 col-sm-12 col-md-12 column">
			<div class="pull-right">
					<span class="glyphicon glyphicon-pencil"  style="padding-left:10px;"></span>  ${post.comments} Replies 	
                </div>
				 
		  </div>
				</div>
			</div>
			
			<form action="saveComment.suggestion?id=${post.id}" method="post">
		<div class="row clearfix getpost_writecomment">
		<div class="col-md-9 column">
		<div class="form-group">
					 <label for="exampleInputPassword1">Write Your Comment:</label><textarea rows="3" cols="10" class="form-control" id="exampleInputPassword1" name="comment"/> </textarea>
				</div>
				
					 <input type="submit" class="btn btn-xs writepost_button" style="width:100px;" value="Submit">
                </div>
				 
		
		
		<div class="col-md-3 column">
		</div>
	</div>
	</form>
	<hr class="getpost-hr">
			<c:forEach var="comment" items="${post.comment}">
			<div class="row clearfix getpost_commentby">
	    
		<div class="col-xs-12 col-sm-12 col-md-12 column-active">
			<h5 class="post_by">
			
			<c:if test="${comment.patientId ne 0}">
			${comment.patientName} <small>&nbsp;&nbsp;&nbsp;&nbsp;Commented As&nbsp;&nbsp;&nbsp;&nbsp; </small> ${comment.comment}</c:if>
				
				
				
				
				<c:if test="${comment.doctorId ne 0}">
				<c:if test="${comment.fees eq 0}">
				<a class="post_by" href="patientViewDoctor.profile?id=${comment.doctorId}">
						Dr . ${comment.doctorName}			</a><small>&nbsp;&nbsp;&nbsp;&nbsp;Commented As&nbsp;&nbsp;&nbsp;&nbsp; </small> ${comment.comment}
						</c:if>
					<c:if test="${comment.fees ne 0}">
					<c:if test="${not empty comment.transactionId}">
					
					<a class="post_by" href="patientViewDoctor.profile?id=${comment.doctorId}">
						Dr . ${comment.doctorName}			</a><small>&nbsp;&nbsp;&nbsp;&nbsp;Commented As&nbsp;&nbsp;&nbsp;&nbsp; </small> ${comment.comment} (${comment.fees}.Rs is paid as fees)
					
					</c:if>
					<c:if test="${empty comment.transactionId}">
					<form action="payuform.jsp" method="post" name="payuForm">
					Pay ${comment.fees}.Rs to view this comment
					
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
													<td><input name="amount" value="${comment.fees}" /></td>
													<td>First Name:</td>
													<td><input name="firstname" id="firstname" value="${patient.firstName}" /></td>
												</tr>
												<tr>
													<td>Email:</td>
													<td><input name="email" id="email" value="${patient.emailId}" /></td>
													<td>Phone:</td>
													<td><input name="phone" value="${patient.mobile}" /></td>
												</tr>
												<tr>
													<td>Product Info:</td>
													<td colspan="3"><input name="productinfo"
														value="Queries" size="64" /></td>
												</tr>
												<tr>
													<td>Success URI:</td>
													<td colspan="3"><input name="surl" value="http://healingclick.com/success.suggestion?id=${post.id}&cId=${comment.id}&tId=<%=txnid%>" size="64" /></td>
												</tr>
												<tr>
													<td>Failure URI:</td>
													<td colspan="3"><input name="furl" value="http://healingclick.com/fail.suggestion?id=${post.id}" size="64" /></td>
												</tr>
												
												
											</table>
												
												<div class="col-xs-3 col-sm-3 col-md-3 column">
												
												<input class="btn btn-info btn-block btn-sm" type="submit"
													style="width: %;" value="Pay Now">
													
											</div>	
										</form>
					
					</c:if>
					
					</c:if>
						
						
						</c:if>
						
						
						
				</h5>
				<hr class="getpost-hr">
				
			
		</div>	
		</div>
		</c:forEach>
		
	</div>
</div>
	
	
	
	<div class="col-xs-12 col-sm-12 col-md-2 column"></div>
	</div>

  </body>
</html>