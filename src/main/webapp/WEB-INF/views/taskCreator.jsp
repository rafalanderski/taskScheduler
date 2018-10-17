<%@ page import="pl.coderslab.taskscheduler.model.TaskStatusOptions" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Task Creator Form</title>

    <style>

        .error {
            color: #ff0000;
        }
    </style>

</head>
<body>
    <h2>Task creator Form</h2>

    <form:form method="POST" modelAttribute="task">

        <form:input type="hidden" path="id" id="id"/>
        <form:input type="hidden" path="status" id="status"/>
        <table>
            <tr>
                <td><label for="title">Title: </label> </td>
                <td><form:input path="title" id="title"/></td>
                <td><form:errors path="title" cssClass="error"/></td>
            </tr>

            <tr>
                <td><label for="content">Content: </label> </td>
                <td><form:input path="content" id="content"/></td>
                <td><form:errors path="content" cssClass="error"/></td>
            </tr>

            <tr>
                <td><label for="type">Type: </label> </td>
                <td>
                    <form:select path="type">
                        <form:options items="${types}"  />
                    </form:select>
                </td>

            </tr>

            <tr>
                <td><label for="priority">Priority: </label> </td>
                <td><form:input path="priority" id="priority"/></td>
                <td><form:errors path="priority" cssClass="error"/></td>
            </tr>

            <tr>
                <td><label for="severity">Severity: </label> </td>
                <td><form:input path="severity" id="severity"/></td>
                <td><form:errors path="severity" cssClass="error"/></td>
            </tr>

            <tr>
                <td colspan="3">
                    <c:choose>
                        <c:when test="${edit}">
                            <input type="submit" value="Update"/>
                        </c:when>
                        <c:otherwise>
                            <input type="submit" value="Create"/>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </table>
    </form:form>
    <br/>
    <br/>
    Go back to <a href="<c:url value='/board' />">Task board</a>

</body>
</html>
