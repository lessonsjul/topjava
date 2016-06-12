<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<html>
<head>
    <title>Meal List</title>
    <link href="<c:url value="css/style.css"/>" rel="stylesheet">
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h2>Meal list</h2>
<table>
    <c:forEach items="${mealList}" var="meal">
        <tr class="meal ${meal.exceed}">
            <td><javatime:format value="${meal.dateTime}" pattern="yyyy-MM-dd HH:MM" /></td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
