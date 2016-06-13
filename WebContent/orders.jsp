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
<link href="css/bootstrap.min.css" rel="stylesheet">

<link href="css/style.css" rel="stylesheet">
<script type="text/javascript" src="js/ajax.js"></script>
<script src="js/dropzone.js" type="text/javascript"></script>
<link href="css/dropzone.css" rel="stylesheet">
<link href="css/basic.css" rel="stylesheet">

<link href="datatable/jquery.dataTables.css" rel="stylesheet">
<link href="datatable/dataTables.tableTools.css" rel="stylesheet">
<script src="datatable/jquery.js" type="text/javascript"></script>
<script src="datatable/jquery.dataTables.js" type="text/javascript"></script>
<script src="datatable/dataTables.tableTools.js" type="text/javascript"></script>


<style>
  body{font-family: 'Josefin Sans', sans-serif;}
   .admin_users{padding:10px;}
    .admin_nav{background-color:white;}
</style>
<script type="text/javascript">

$(document).ready(function() {
    $('#example').DataTable( {
        dom: 'T<"clear">lfrtip',
        tableTools: {
            "sSwfPath": "datatable/copy_csv_xls_pdf.swf",
            "aButtons": [
          				{
          					"sExtends": "copy",
          					"sTitle": "Orders"
          				},
          				{
          					"sExtends": "csv",
          					"sTitle": "Orders"
          				},
          				{
          					"sExtends": "xls",
          					"sTitle": "Orders"
          				},
          				{
          					"sExtends": "pdf",
          					"sTitle": "Orders"
          				},
          				{
          					"sExtends": "print",
          					"sTitle": "Orders"
          				}
          			]
        }
    } );
    
} );

</script>
</head>
<body>
 <div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<nav class="navbar navbar-fixed-top admin_nav" role="navigation" >
				<div class="navbar-header">
					 <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button> <a class="navbar-brand" href="#">LOGO</a>
				</div>
				
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav" style="padding-top:15px;padding-bottom:10px;margin-left:30%;">
					<li><a href="home.admin" style="background-color: ;">Home</a>
					
					<li><a href="order.admin">Order</a></li>
					<li><a href="prescription.admin">Prescription</a></li>
					<li><a href="users.jsp">User</a></li>
					<li><a href="forums.jsp">Community</a></li>	
					<li><a href="complaints.admin">Complaints</a></li>
					</ul>
					
					<ul class="nav navbar-nav navbar-right">
						<li>
							<a href="logout.admin"> <span class="glyphicon glyphicon-off"></span> Log Out</a>
						</li>
					
					</ul>
				</div>
				
			</nav>
		</div>
	</div>
</div>
  
  	<div class="container-fluid" style="margin-top:7%;">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="row clearfix">
					<div class="col-xs-12 col-sm-12 col-md-12 column">
						
						  <div class="panel panel-default admin_orders">
							<form class="form" action="searchOrder.admin" name="form"  method="post" ><!-- onSubmit="return pharmacy1();" -->
					
					 
							Start Date :<input class="" id="startDate" name="startDate" type="date" data-toggle="tooltip" data-placement="top" title="Start Date" placeholder="Start Date" /><span class="error-message" id="date1"></span>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							End Date :<input class="" id="endDate" name="endDate" type="date" data-toggle="tooltip" data-placement="top" title="End Date" placeholder="End Date" /><span class="error-message" id="date2"></span>
						    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						    <datalist id="pharmacies">
										<c:forEach var="pharmacy" items="${pharmacies}">
											<option value="${pharmacy.id}">${pharmacy.pharmacyName}</option>
										</c:forEach>
									</datalist>
									
							Pharmacy Id: <input name=pharmacyId list=pharmacies>
							
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							
							Order Status : <select name="orderStatus">
							<option value="0">----Select----</option>
							<option value="4">Delivered</option>
							<option value="2">Verified and Yet to be Delivered</option>
							<option value="1">Not Verified</option>
							<option value="5">Cancelled by Customer</option>
							<option value="3">Cancelled by Seller</option>
							</select>
							
						    <br/>
						<input type=submit value="Search">
						</form>
						</div>
						
						<div class="panel panel-default">
							<div class="col-sm-4 column" style="color: red;"></div>
							<c:if test="${not empty orders}">
							<table id="example" class="display" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th>Order Id</th>
                <th>Pharmacy Name (ID)</th>
                <th>Customer Name (ID)</th>
                <th>Cost</th>
                <th>Status</th>
                <th>Created Date</th>
            </tr>
        </thead>
       <!--  <tfoot>
            <tr>
                <th>Order Id</th>
                <th>Pharmacy Id</th>
                <th>Pharmacy Name</th>
                <th>Customer Id</th>
                <th>Cost</th>
            </tr>
        </tfoot> -->
        <tbody>
        
								<c:forEach var="order" items="${orders}">
								<tr>
                <td><a href="getOrder.admin?id=${order.id}">${order.id}</a></td>
                <td><a href="getPharmacy.admin?id=${order.supplierPharmacyId}">${order.supplierPharmacyName} </a>(${order.supplierPharmacyId})</td>
                <td><c:if test="${order.patientId ne 0}">
					<a href="getPatient.admin?id=${order.patientId}">${order.patientName}</a> (${order.patientId})
				</c:if>
												<c:if test="${order.doctorId ne 0}">
					<a href="getDoctor.admin?id=${order.doctorId}">Dr.${order.doctorName}</a> (${order.doctorId})
				</c:if>
												<c:if test="${order.pharmacyId ne 0}">
					<a href="getPharmacy.admin?id=${order.pharmacyId}">${order.pharmacyName}</a> (${order.pharmacyId})
				</c:if></td>
                <td>${order.totalCost}</td>
                
                <td><c:if test="${order.status == 1}">Not Verified</c:if>
											<c:if test="${order.status == 2}">Verified and yet to be delivered</c:if>
											<c:if test="${order.status == 3}">Cancelled By Seller</c:if>
											<c:if test="${order.status == 4}">Delivered</c:if>
											<c:if test="${order.status == 5}">Cancelled By Customer</c:if></td>
                <td>${order.createdOn.getDate()}/${order.createdOn.getMonth()+1}/${order.createdOn.getYear()+1900} ${order.createdOn.getHours()}:${order.createdOn.getMinutes()}</td>
            </tr>
								
								
								</c:forEach>
								
        </tbody>
    </table>
			</c:if>			</div>
						
					</div>


			<br>
		
			
		</div>
</div>
</div>
</div>
  
  
  
  
  
 