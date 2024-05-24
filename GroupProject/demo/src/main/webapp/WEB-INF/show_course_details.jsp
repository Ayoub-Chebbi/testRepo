<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Course Details</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<!-- Custom CSS -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="container mt-5">
    <h1 class="text-center">${course.name}</h1>
    <p class="text-center">${course.description}</p>
    <hr>
    
    <h3 class="text-center">Students Enrolled:</h3>
    <ul>
        <c:forEach items="${course.users}" var="user">
            <li>${user.username}</li>
        </c:forEach>
    </ul>
    <hr>
    <form action="${pageContext.request.contextPath}/api/courses/${course.id}/addUser" method="post">
        <a href="/api/instructor/add-course-to-user">Enroll a student in a Course</a>
    </form>
</div>

</body>
</html>
