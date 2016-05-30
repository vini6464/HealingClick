<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="doctorHeader.jsp" %>
<style>
.cat
{
    list-style: none;
    margin: 0;
    padding: 0;
}

.cat li
{
    margin-bottom: 10px;
    padding-bottom: 5px;
    border-bottom: 1px dotted #B3A9A9;
}

.cat li.left .cat-body
{
    margin-left: 60px;
}

.cat li.right .cat-body
{
    margin-right: 60px;
}


.cat li .cat-body p
{
    margin: 0;
    color: #777777;
}

.panel .slidedown .glyphicon, .cat .glyphicon
{
    margin-right: 5px;
}

.panel-body
{
    overflow-y: scroll;
    height: 250px;
}

::-webkit-scrollbar-track
{
    -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3);
    background-color: #F5F5F5;
}

::-webkit-scrollbar
{
    width: 12px;
    background-color: #F5F5F5;
}

::-webkit-scrollbar-thumb
{
    -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,.3);
    background-color: #555;
}

</style>
  
    <div class="container" style="margin-top: 4%;">
    <div class="row">
        <div class="col-md-5">
            <div class="panel panel-primary">
 				<div class="panel-heading">
                    <span class="glyphicon glyphicon-comment"></span> Chat
                    <div class="btn-group pull-right">
                     <a href="getMessages.mobile?chatId=${chatId}&type=${type}">
                        
                           <span class="glyphicon glyphicon-refresh"></span>
                        
                        </a>
                    </div>
                </div>
                <div class="panel-body">
                
                
                <c:if test="${not empty messages}">
                <ul class="cat">
					<c:forEach var="message" items="${messages}">
					
                 <c:if test="${message.replierId ne doctor.id}">
                
                 <li class="right clearfix"><!-- <span class="cat-img pull-right">
                            <img src="http://placehold.it/50/FA6F57/fff&text=ME" alt="User Avatar" class="img-circle" />
                        </span> -->
                            <div class="cat-body clearfix">
                                <div class="header">
                                    <small class=""><span class="glyphicon glyphicon-time"></span>${message.creationDate}</small>
                                    <strong class="pull-right primary-font">
                                    <c:if test="${type == 1}">
                                    ${otherDoctor.firstName} &nbsp; ${otherDoctor.lastName}
                                    </c:if>
                                    <c:if test="${type == 2}">
                                    ${patient.firstName} &nbsp; ${patient.lastName}
                                    </c:if>
                                     <c:if test="${type == 3}">
                                    ${pharmacy.pharmacyName}
                                    </c:if>
                                    </strong>
                                </div>
                                <p>
                                   ${message.reply}
                                </p>
                            </div>
                        </li>
                 </c:if>
                 
                  <c:if test="${message.replierId eq doctor.id}">
                 <li class="left clearfix"><!-- <span class="cat-img pull-left">
                            <img src="http://placehold.it/50/FA6F57/fff&text=ME" alt="User Avatar" class="img-circle" />
                        </span> -->
                            <div class="cat-body clearfix">
                                <div class="header">
                                 <strong class="primary-font" >You</strong>
                                    <small class="pull-right"><span class="glyphicon glyphicon-time"></span>${message.creationDate}</small>
                                   
                                </div>
                                <p>
                                   ${message.reply}
                                </p>
                            </div>
                        </li>
                       
                
                 </c:if>
                 
                 
                </c:forEach>
                </ul>
                </c:if>
                <c:if test="${empty messages}">
                No Messages to show
                </c:if>
                </div>
                <div class="panel-footer">
                    <div class="input-group">
                    <form method="post" action="saveMessages.mobile">
                        <input id="btn-input" type="text" name="message" class="form-control input-sm" placeholder="Type your message here..." />
                        <span class="input-group-btn">
                        
                            <input type="submit" class="btn btn-warning btn-sm" id="btn-chat" value="Send">
                            <input type="text" class="hidden" name="chatId"  value="${chatId}">
                            <input type="text" class="hidden" name="type"  value="${type}">
                           
                        </span>
                         </form>   
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>



				<script src="js/bootstrap.min.js"></script>
</body>
</html>
    