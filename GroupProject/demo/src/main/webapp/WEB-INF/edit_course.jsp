<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Edit Course</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Custom CSS -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="container mt-5">
    <h1 class="text-center">Edit Course Form</h1>

    <div class="row">
        <div class="col-md-6">
            <form:form action="${pageContext.request.contextPath}/api/courses/${course.id}/edit" method="post" modelAttribute="course">
                <div class="form-group">
                    <form:errors path="name" class="text-danger"/>
                    <form:input class="form-control" path="name" value="${course.name}" placeholder="Course Name"/>
                </div>
                <div class="form-group">
                    <form:errors path="description" class="text-danger"/>
                    <form:textarea class="form-control" path="description" placeholder="Description"></form:textarea>
                </div>
                <button type="submit" class="btn btn-primary">Update Course</button>
            </form:form>
        </div>
    </div>
</div>

</body>
</html>
