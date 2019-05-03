<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="segment">
	<div class="head">
		<h2>Find An Employee By ID</h2>
	</div>
	<div>
		<form action="${pageContext.request.contextPath}/empApp" method="POST" accept-charset="utf-8">  
			<div  class="field">
		    	<label for="idLbl">ID:</label>
		    	<div class="input">
		    		<input type="text" name="id" value="" id="id" title="ID"/>
		    	</div>
		    </div>
		    <div  class="submit">
				 <input type="hidden" name="action" value="service.findEmployees" />
			    <input type="submit" name="submit" value="Search" id="submit"/>
			</div>
		</form>
		
		
		<c:if test="${foundEmp ne null}" >
			<c:out value="Found" />
			<c:out value="${foundEmp.firstName}" />
			<c:out value="${foundEmp.lastName}" />
		</c:if>
		<br></br>
		<c:if test="${fn:length( findResponseCode.code ) > 0}" >
			<c:set var="code" value="Result Code: ${findResponseCode.code}"/>
			<c:set var="desc" value="Description: ${findResponseCode.desc}"/>
			<font color="red"><c:out value="${code}"/>
			<c:out value="${desc}"/></font>
		</c:if>
	</div>
	
</div>		