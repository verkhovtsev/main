<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 

<html>
<head><title>Add</title></head>
<body>
	
	<form:form method="post" modelAttribute="student">
	<form:hidden path="id"/><br>
    	First name: 
	<form:input type="text" path="firstName" /><br>
    	Last name: 
	<form:input type="text" path="lastName" /><br>
 		Group: 
	<form:input type="text" path="group"/><br>
		Date of admission: 
	<form:input type="text" path="dateOfAdmission"/><br>
    
    <input type="submit" name="okButton" value="Ok">
    
    <spring:url value="/studentsList.html" var="studentsListUrl"/>
    <input type="button" name="cancelButton" value="Cancel" onclick="location.href='${studentsListUrl}'">
    	
</form:form>

<c:if test="${requestScope.error != null}">
    <script>
     	alert('${requestScope.error}');
    </script>
</c:if>
</body>

</html>