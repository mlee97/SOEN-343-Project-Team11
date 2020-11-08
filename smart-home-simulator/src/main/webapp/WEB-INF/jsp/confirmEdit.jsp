<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<html>
    <head>
        <title>Room context</title>
        
    </head>
    <body>
        <h3>${currentRoom.getRoomName()} </h3>
      	<c:out value="${currentRoom.Status()}"></c:out><br/><br/><br/>
    </body>
</html>