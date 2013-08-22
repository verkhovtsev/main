<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head><title>User management</title></head>
<body>
<a href="<c:url value="/j_spring_security_logout"/>">Logout</a>

	<table id="studentsTable" border="5" cellspacing="10">
    	<tr>
    		<th></th>
			<th>First name</th>
			<th>Last name</th>
			<th>Group</th>
			<th>Date of Admission</th>
		</tr>
		<c:forEach var="student" items="${listOfStudents}">
    	<tr>
    		<td><input type="checkbox" name="id" id="id" value="${student.id}"></td>
			<td width="150">${student.firstName}</td>
			<td width="150">${student.lastName}</td>
			<td width="150">${student.group}</td>
			<td width="150"><f:formatDate value="${student.dateOfAdmission}" pattern="dd.MM.yyyy"/></td>
		</tr>
		</c:forEach>
	    </table>

	
    	<spring:url value="/studentAdd.html" var="addUrl"/>
		<a href="${addUrl}">Add</a>
	
<c:if test="${requestScope.error != null}">
    <script>
    	alert('${requestScope.error}');
    </script>
</c:if>
</body>
</html>