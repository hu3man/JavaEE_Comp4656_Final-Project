<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="segment">
	<div class="head">
		<h2>Add Employees</h2>
	</div>
	<div>
		<form action="${pageContext.request.contextPath}/empApp" method="POST" accept-charset="utf-8">            
		    <div  class="field">
		    	<label for="idLbl">ID:</label>
		    	<div class="input">
		    		<input type="text" name="id" value="" id="id" title="ID"/>
		    	</div>
		    </div>
		    <div  class="field">
		    	<label for="fnLbl">First Name:</label>
		    	<div class="input">
		    		<input ttype="text" name="fname" value="" id="fname" title="First Name"/>
		    	</div>
		    </div>
		    <div  class="field">
		    	<label for="lnLbl">Last Name:</label>
		    	<div class="input">
		    		<input type="text" name="lname" value="" id="lname" title="Last Name"/>
		    	</div>
		    </div>
		    <div  class="field">
		    	<label for="lnLbl" >DOB:</label>
		    	<div class="input">
		    		<input type="text" name="dob" value="" id="dob" title="Date of Birth" placeholder="YYYY/MM/DD"/>
		    	</div>
		    </div>
			<div  class="submit">
				 <input type="hidden" name="action" value="service.addEmployees" />
			    <input type="submit" name="submit" value="Add Employees" id="submit"/>
			</div>
		</form>

		<c:if test="${fn:length( addResponseCode.code ) > 0}" >
			<c:set var="code" value="Result Code: ${addResponseCode.code}"/>
			<c:set var="desc" value="Description: ${addResponseCode.desc}"/>
			<c:out value="${code}"/>
			<c:out value="${desc}"/>
		</c:if>

		
	</div>
</div>