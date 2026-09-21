<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>All Tasks</title>
</head>
<body>

<h1>All Tasks</h1>

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
    <c:forEach var="task" items="${tasks}">
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