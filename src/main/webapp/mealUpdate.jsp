<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
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
<h2>Update meal</h2>

<jsp:useBean id="userMeal" scope="request" type="ru.javawebinar.topjava.model.UserMeal"/>
<form class="form-horizontal" role="form" method="post" action="${pageContext.servletContext.contextPath}/meals">
    <input type="hidden" name="id" value="<c:out value="${userMeal.id}"/>">
    <div class="form-group">
        <label for="userDate" class="col-sm-2 control-label">Date</label>
        <div class="col-sm-10">
            <input type="text" name="dateTime" id="userDate" value="<javatime:format value="${userMeal.dateTime}"
                pattern="yyyy-MM-dd HH:mm"/>" placeholder="Date" class="form-control" required>
        </div>
    </div>
    <div class="form-group">
        <label for="description" class="col-sm-2 control-label">Description</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="description" name="description" value="${userMeal.description}"
                   placeholder="description" required>
        </div>
    </div>
    <div class="form-group">
        <label for="calories" class="col-sm-2 control-label">Calories</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="calories" name="calories" value="${userMeal.calories}"
                   placeholder="2000" required>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">Save</button>
        </div>
    </div>
</form>

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