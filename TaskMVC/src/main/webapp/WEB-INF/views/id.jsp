<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Task Details</title>
</head>
<body>

<h1>Task Details</h1>

<table border="1">
    <tr>
        <th>ID</th>
        <td>${task.id}</td>
    </tr>

    <tr>
        <th>Title</th>
        <td>${task.title}</td>
    </tr>

    <tr>
        <th>Completed</th>
        <td>${task.completed}</td>
    </tr>

    <tr>
        <th>Priority</th>
        <td>${task.priority}</td>
    </tr>
</table>

</body>
</html>