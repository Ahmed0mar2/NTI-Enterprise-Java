<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Create Task</title>
</head>

<body>

<h1>Create Task</h1>

<form:form method="post"
           action="${pageContext.request.contextPath}/tasks"
           modelAttribute="task">

    <div>
        <label>Title:</label>
        <form:input path="title"/>
        <form:errors path="title"/>
    </div>

    <div>
        <label>Priority:</label>
        <form:select path="priority">
            <form:option value="high" label="High"/>
            <form:option value="low" label="Low"/>
        </form:select>
        <form:errors path="priority"/>
    </div>

    <div>
        <label>Completed:</label>
        <form:checkbox path="completed"/>
        <form:errors path="completed"/>
    </div>

    <button type="submit">Create Task</button>

</form:form>

</body>
</html>