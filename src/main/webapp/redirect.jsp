<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<body>
<h1>Home Page</h1>
<p>
Anyone can view this page.
</p>
<p>
If you're logged in, you can <a href="studentsList.html">list of users</a>.
</p>
<p>
Your principal object is....: <%= request.getUserPrincipal() %>
</p>

</body>
</html>