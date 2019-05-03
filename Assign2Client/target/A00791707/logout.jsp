<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="segment">
	<div class="head">
		<h2>Sign Off</h2>
	</div>
	<div>
		<form action="${pageContext.request.contextPath}/logout" method="POST" accept-charset="utf-8">
		    <div  class="submit">
			    <input type="submit" name="logout" value="Sign Out" id="logout"/>
			</div>
		</form>
	</div>
</div>		