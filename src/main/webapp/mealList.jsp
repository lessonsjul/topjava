<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>User list</title>

    <!-- Bootstrap -->
    <link href="${pageContext.servletContext.contextPath}/css/bootstrap.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/style.css"/>
</head>
<body>
<h2><a href="${pageContext.servletContext.contextPath}/index.html">Home</a></h2>
<h2>Meal list</h2>


<div class="container">
    <a class="btn btn-info btn-ms add" href="meals?action=create">Добавить еду</a>

    <table class="table table-striped">
        <tr style="border-bottom: 1px solid black">
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>
        </tr>
        <c:forEach items="${mealList}" var="meal">
            <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.UserMealWithExceed" />
            <tr style="color: ${meal.exceed ? 'red' : 'green'};">
                <td>
                    <%--<fmt:parseDate value="${meal.dateTime}" pattern="y-M-dd'T'H:m" var="parseDate"/>--%>
                    <%--<fmt:formatDate value="${parseDate}" pattern="yyyy-MM-dd HH:mm" />--%>

                    <%=TimeUtil.toString(meal.getDateTime())%>
                    <%--<javatime:format value='${meal.dateTime}' pattern="yyyy-MM-dd HH:mm"/>--%>
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a class="btn btn-primary" href="meals?action=edit&id=${meal.id}">Edit</a></td>
                <%--<td><a class="btn btn-primary" href="meals/edit?id=${meal.id}">Edit</a></td>--%>
                <%--<td><a class="btn btn-danger" href="meals/delete?id=${meal.id}">Delete</a></td>--%>
                <td><a class="btn btn-danger" href="meals?action=delete&id=${meal.id}">Delete</a></td>
            </tr>
        </c:forEach>

    </table>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${pageContext.servletContext.contextPath}/js/bootstrap.js"></script>

<%--DatePicker Dependencies--%>
<link rel="stylesheet" type="text/css" href="<c:url value='/datetimepicker/jquery.datetimepicker.css'/>" />
<script src="<c:url value="/datetimepicker/jquery.js"/>"></script>
<script src="<c:url value="/datetimepicker/jquery.datetimepicker.full.min.js"/>"></script>
<script>
    jQuery('#userDate').datetimepicker({
        format: 'Y-m-d H:i'
    });
</script>
</body>
</html>