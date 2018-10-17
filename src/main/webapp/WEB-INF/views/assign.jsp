<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title></title>

    <style>

        .error {
            color: #ff0000;
        }
    </style>

</head>

<body>

<h2>Task assignment panel</h2>

    <form:form method="POST" modelAttribute="aModel">
    <table>
        <tr>
            <td><label for="name">Name: </label> </td>
            <td><form:input path="name"/></td>
            <td><form:errors path="name" cssClass="error"/></td>
        </tr>

        <tr>
            <td><label for="surname">Surname: </label> </td>
            <td><form:input path="surname" id="surname"/></td>
            <td><form:errors path="surname" cssClass="error"/></td>
        </tr>

        <h2><form:errors path="" cssClass="error"/></h2>

        <tr>
            <td colspan="3">
                <input type="submit" value="Assign"/>
            </td>
        </tr>
    </table>
</form:form>
<br/>
<br/>
Go back to <a href="<c:url value='/board' />">Main page</a>
</body>
</html>
