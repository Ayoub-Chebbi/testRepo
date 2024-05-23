<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Course to User</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Custom CSS -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="container mt-5">
    <h1 class="text-center">Add Course to User</h1>
    <div class="row">
        <div class="col-md-6 offset-md-3">
            <form:form action="${pageContext.request.contextPath}/user/addCourse" method="post" modelAttribute="courseAssignment">
                <div class="form-group">
                    <label for="userId">Select User:</label>
                    <form:select class="form-control" path="userId">
                        <form:option value="" label="Select User" />
                        <c:forEach var="user" items="${users}">
                            <form:option value="${user.id}" label="${user.username}" />
                        </c:forEach>
                    </form:select>
                </div>
                <div class="form-group">
                    <label for="courseId">Select Course:</label>
                    <form:select class="form-control" path="courseId">
                        <form:option value="" label="Select Course" />
                        <c:forEach var="course" items="${courses}">
                            <form:option value="${course.id}" label="${course.name}" />
                        </c:forEach>
                    </form:select>
                </div>
                <button type="submit" class="btn btn-primary">Add Course to User</button>
            </form:form>
        </div>
    </div>
</div>

</body>
</html>
