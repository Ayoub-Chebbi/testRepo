<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Student Courses</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/styles.css' />">
</head>
<body>
    <h1>My Courses</h1>
    <table>
        <thead>
            <tr>
                <th>Course Name</th>
                <th>Description</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="course" items="${courses}">
                <tr>
                    <td><c:out value="${course.name}" /></td>
                    <td><c:out value="${course.description}" /></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <h2>Edit My Information</h2>
    <form action="<c:url value='/student/update' />" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <div>
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" value="${user.username}" required />
        </div>
        <div>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="${user.email}" required />
        </div>
        <div>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required />
        </div>
        <button type="submit">Update</button>
    </form>
</body>
</html>
