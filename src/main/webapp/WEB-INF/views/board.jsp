<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <%--<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css"/>--%>
    <link rel='stylesheet' href='<c:url value="/resources/css/style.css" />' type='text/css' media='all' />
    <title>TaskScheduler</title>
</head>
<body>
<div class="header">
    <div class="headercontent1">
        <form id="leftform" action="<c:url value='/newTask' />">
            <input type="submit" value="Add New Task" />
        </form>
    </div>
    <div class="headercontent2">
        <h1>TaskScheduler</h1>
    </div>
    <div class="headercontent3">
        <form id="rightform" action="<c:url value='/newUser' />">
            <input type="submit" value="Add New User" />
        </form>
    </div>
</div>
<div class="container">
    <div class="content">
        <h1>NEW</h1>
        <c:forEach items="${newTasks}" var="task">
            <div class="news">
                <div id="${task.getType()}"><p></p></div>
                <div id="taskcontent">
                    <h2><a href="<c:url value='/view-${task.getId()}-task' />">${task.getTitle().toUpperCase()}</a></h2>
                    <div class="szczegoly">
                        Created, on <c:out value="${task.getCreated()}"/>
                    </div>
                    <div class="wiecej">
                        Type: ${task.getType()} | Priority: ${task.getPriority()} | Severity: ${task.getSeverity()}
                        <br/>
                        <a href="<c:url value='/assign-${task.getId()}-task' />">ASSIGN TASK TO THE USER</a>
                    </div>
                    <div class="tresc">
                        <img src="resources/images/admin.png" alt="" height="30" width="30">
                        <c:out value="${task.getContent()}"/>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <div class="content">
        <h1>ACTIVE</h1>
        <c:forEach items="${activeTasks}" var="task">
            <div class="news">
                <div id="${task.getType()}"><p></p></div>
                <div id="taskcontent">
                    <h2><a href="<c:url value='/view-${task.getId()}-task' />">${task.getTitle().toUpperCase()}</a></h2>
                    <div class="szczegoly">
                        Created, activated on <c:out value="${task.getActivated()}"/>
                    </div>
                    <div class="wiecej">
                        Type: ${task.getType()} | Priority: ${task.getPriority()} | Severity: ${task.getSeverity()}
                        <br/>
                        Assigned to: ${task.getUser().getName()} ${task.getUser().getSurname()} |
                        <a href="<c:url value='/assign-${task.getId()}-task' />">CHANGE</a>
                    </div>
                    <div class="tresc">
                        <img src="<c:out value='${task.getUser().getAvatar_url()}'/>" alt="" height="30" width="30">
                        <c:out value="${task.getContent()}"/>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <div class="content">
        <h1>RESOLVED</h1>
        <c:forEach items="${resolvedTasks}" var="task">
            <div class="news">
                <div id="${task.getType()}"><p></p></div>
                <div id="taskcontent">
                    <h2><a href="<c:url value='/view-${task.getId()}-task' />">${task.getTitle().toUpperCase()}</a></h2>
                    <div class="szczegoly">
                        Created, resolved on <c:out value="${task.getResolved()}"/>
                    </div>
                    <div class="wiecej">
                        Type: ${task.getType()} | Priority: ${task.getPriority()} | Severity: ${task.getSeverity()}
                        <br/>
                        Resolved by: ${task.getUser().getName()} ${task.getUser().getSurname()} |
                        <a href="<c:url value='/assign-${task.getId()}-task' />">CHANGE</a>
                    </div>
                    <div class="tresc">
                        <img src="<c:out value='${task.getUser().getAvatar_url()}'/>" alt="" height="30" width="30">
                        <c:out value="${task.getContent()}"/>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <div class="content">
        <h1>CLOSED</h1>
        <c:forEach items="${closedTasks}" var="task">
            <div class="news">
                <div id="${task.getType()}"><p></p></div>
                <div id="taskcontent">
                    <h2><a href="<c:url value='/view-${task.getId()}-task' />">${task.getTitle().toUpperCase()}</a></h2>
                    <div class="szczegoly">
                        Created, on <c:out value="${task.getCreated()}"/>, closed on <c:out value="${task.getClosed()}"/>
                    </div>
                    <div class="wiecej">
                        Type: ${task.getType()} | Priority: ${task.getPriority()} | Severity: ${task.getSeverity()}
                        <br/>
                        Completed by: ${task.getUser().getName()} ${task.getUser().getSurname()}
                    </div>
                    <div class="tresc">
                        <img src="<c:out value='${task.getUser().getAvatar_url()}'/>" alt="" height="30" width="30">
                        <c:out value="${task.getContent()}"/>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</body>
</html>
