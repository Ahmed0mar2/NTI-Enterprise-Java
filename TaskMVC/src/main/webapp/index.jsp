<html>
<body>
<h2>Task 1 -- read endpoints</h2>

<ul>
    <li>
        <a href="${pageContext.request.contextPath}/tasks">
            /tasks
        </a>
        -- list all
    </li>

    <li>
        <a href="${pageContext.request.contextPath}/tasks/1">
            /tasks/1
        </a>
        -- one task by id
    </li>

    <li>
        <a href="${pageContext.request.contextPath}/tasks/search?priority=HIGH">
            /tasks/search?priority=HIGH
        </a>
        -- filter
    </li>
</ul>

<h2>Task 2 -- exception handling</h2>
<ul>
    <li>
        <a href="${pageContext.request.contextPath}/tasks/new">Create Task</a>
    </li>
</ul>
</body>
</html>
