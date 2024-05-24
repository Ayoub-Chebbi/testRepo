<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit User</title>
    <link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css' />">
</head>
<body>
    <div class="container">
        <h1>Edit User</h1>
        <form:form method="POST" modelAttribute="user" action="${pageContext.request.contextPath}/api/users/${user.id}/edit">
            <div class="form-group">
                <form:label path="email">Email:</form:label>
                <form:input path="email" class="form-control"/>
            </div>
            <div class="form-group">
                <form:label path="password">Password:</form:label>
                <form:input path="password" class="form-control" type="password"/>
            </div>
            <div class="form-group">
                <form:label path="roles">Roles:</form:label>
                <form:checkboxes path="roles" items="${roles}" itemLabel="name" itemValue="name"/>
            </div>
            <div class="form-group">
                <form:label path="courses">Courses:</form:label>
                <form:checkboxes path="courses" items="${courses}" itemLabel="name" itemValue="id"/>
            </div>
            <button type="submit" class="btn btn-primary">Update</button>
        </form:form>
    </div>
</body>
</html>
