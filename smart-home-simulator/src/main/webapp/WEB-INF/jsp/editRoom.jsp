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
      	<form:form  method="POST" modelAttribute="currentRoom">
      		<form:label path="doors">How many doors to open</form:label>
      		<form:select path="doorsToOpen" items="${currentDoors}"/><br/>
      		<form:label path="windows">How many windows to open</form:label>
      		<form:select path="windowsToOpen" items="${currentWindows}"/><br/>
      		<form:label path="lights">How many lights to open</form:label>
      		<form:select path="lightsToTurnOn" items="${currentLights}"/><br/>
      		<input type="submit" value="Submit">
      	</form:form>
    </body>
</html>