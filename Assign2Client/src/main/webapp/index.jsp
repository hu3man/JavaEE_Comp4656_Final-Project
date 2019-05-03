<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Employee Admin Tool</title>
		<link rel="stylesheet" href="admin.css" type="text/css" title="no title" charset="utf-8" />
	</head>
	<body>
		<div class="mdi">
			<div class="columns">
				<div class="column leftcol">
					<!--  Employee Tables  -->
					<jsp:include flush="true" page="employees.jsp"/>
					<jsp:include flush="true" page="logout.jsp"/>

				</div>
				<div class="column rightcol">
					<!--   Add Pages -->
					<c:if test="${requestScope.adminAuthorization == true}">
						<jsp:include flush="true" page="addEmployees.jsp" />
					</c:if>

					<jsp:include flush="true" page="findEmployee.jsp" />

					<c:if test="${requestScope.adminAuthorization}">
						<jsp:include flush="true" page="deleteEmployee.jsp" />
					</c:if>
				</div>
				<div class="clear">&#160;</div>
			</div>
		</div>
		</div>
	</body>
</html>