<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
    <head>
        <title>Add Simulator</title>
    </head>
    <body>
        <h3>Welcome, Enter The Simulator Details</h3>
        <form:form method="POST" action="${pageContext.request.contextPath}/simulator" modelAttribute="simulator">
            <table>
                <tr>
                    <td><form:label path="date">Name</form:label></td>
                    <td><form:input path="date"/></td>
                </tr>
                <tr>
                    <td><form:label path="time">Id</form:label></td>
                    <td><form:input path="time"/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Submit"/></td>
                </tr>
            </table>
        </form:form>
    </body>
</html>