<%@ page import="pl.coderslab.taskscheduler.model.TaskStatusOptions" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Task View</title>

    <style>

        .error {
            color: #ff0000;
        }
    </style>

</head>
    <body>
    
        <h2><c:out value='${task.getTitle()}'/></h2>
        <p><c:out value='${task.getContent()}'/></p>
        <p><c:out value='${task.getCreated()}'/></p>
        <p>Status: <c:out value='${task.getStatus()}'/></p>
        <p> Type: <c:out value='${task.getType()}'/> | Priority: <c:out value='${task.getPriority()}'/> | Severity: <c:out value='${task.getPriority()}'/></p>
        <c:if test='${task.getStatus()!= "CLOSED"}'>
        <a href="<c:url value='/edit-${task.getId()}-task' />">Edit</a>
        <a href="<c:url value='/delete-${task.getId()}-task' />">Delete</a>
        </c:if>
        <c:if test='${task.getStatus()== "NEW" }'>
        <a href="<c:url value='/assign-${task.getId()}-task' />">Assign</a>
        </c:if>
        <c:if test='${task.getStatus()== "ACTIVE" }'>
            <a href="<c:url value='/set-${task.getId()}-taskN' />">Set NEW</a>
            <a href="<c:url value='/set-${task.getId()}-taskR' />">Set RESOLVED</a>
        </c:if>
        <c:if test='${task.getStatus()== "RESOLVED" }'>
            <a href="<c:url value='/set-${task.getId()}-taskA' />">Set ACTIVE</a>
            <a href="<c:url value='/set-${task.getId()}-taskC' />">Set CLOSED</a>
        </c:if>

    </body>
</html>
