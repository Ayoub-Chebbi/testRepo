<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Course to User</title>
</head>
<body>
    <h1>Add Course to User</h1>
    <form action="/api/instructor/add-course-to-user" method="post">
        <label for="userId">Select User:</label>
        <select name="userId" id="userId">
            <c:forEach items="${users}" var="user">
                <option value="${user.id}">${user.username}</option>
            </c:forEach>
        </select>
        <br>
        <label for="courseId">Select Course:</label>
        <select name="courseId" id="courseId">
            <c:forEach items="${courses}" var="course">
                <option value="${course.id}">${course.name}</option>
            </c:forEach>
        </select>
        <br>
        <input type="submit" value="Add Course">
    </form>
</body>
</html>
