<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Users</title>
    <link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css' />">
</head>
<body>
    <div class="container">
        <h1 class="mt-5 mb-4">All Users</h1>
        <table class="table table-bordered table-striped">
            <thead class="thead-dark">
                <tr>
                    <th>Email</th>
                    <th>Roles</th>
                    <th>Courses</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>${user.email}</td>
                        <td>
                            <c:forEach var="role" items="${user.roles}">
                                ${role}<br/>
                            </c:forEach>
                        </td>
                        <td>
                            <c:forEach var="course" items="${user.courses}">
                                ${course.name}<br/>
                            </c:forEach>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/api/users/${user.id}/edit" class="btn btn-primary mr-2">Edit</a>
                            <a href="${pageContext.request.contextPath}/api/users/${user.id}/delete" class="btn btn-danger">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
