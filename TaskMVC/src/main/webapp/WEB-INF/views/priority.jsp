<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>Tasks by Priority</title>
</head>
<body>

<h1>Tasks with Priority: ${param.priority}</h1>

<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Completed</th>
        <th>Priority</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach var="task" items="${prioritytasks}">
        <tr>
            <td>${task.id}</td>
            <td>${task.title}</td>
            <td>${task.completed}</td>
            <td>${task.priority}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>