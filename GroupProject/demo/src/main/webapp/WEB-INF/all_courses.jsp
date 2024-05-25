<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Courses</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<!-- Custom CSS -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="container mt-5">
    <h1 class="text-center">All Courses</h1>
    <table class="table mt-3">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Description</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${courses}" var="course">
                <tr>
                    <td>${course.id}</td>
                    <td><a href="/api/courses/${course.id}">${course.name}</a></td>
                    <td>${course.description}</td>
                    <td>
                        <a href="/api/courses/${course.id}/edit">Edit</a>
                        <a href="/api/courses/${course.id}/delete">Delete</a>
                        <a href="/api/instructor/add-course-to-user">Add a Student</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="/api/courses/new">Create A New Course</a>
    <a href="/register">Register a New User</a>
    <a href="/api/users/all">Show all users</a>
    <a href="/logout" class="btn btn-danger mt-3">Log Out</a>
</div>

</body>
</html>
