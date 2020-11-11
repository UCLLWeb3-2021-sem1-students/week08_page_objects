<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta charset="UTF-8">
<title>Overview</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
<header>
<h1><span>XXX</span></h1>
<nav>
<ul>
<li><a href="Controller">Home</a></li>
<li id="actual"><a href="Controller?command=Overview">Overview</a></li>
<li><a href="signUp.jsp">Register</a></li>
</ul>
</nav>
<h2>
User Overview
</h2>

</header><main>
<table>
    <thead>
        <tr>
            <th>E-mail</th>
            <th>First Name</th>
            <th>Last Name</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="person" items="${persons}">
            <tr>
                <td><c:out value='${person.firstName}'/></td>
                <td><c:out value='${person.lastName}'/></td>
                <td><c:out value='${person.email}'/></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</main>
<footer>
&copy; Webontwikkeling 3, UC Leuven-Limburg
</footer>
</div>
</body>
</html>