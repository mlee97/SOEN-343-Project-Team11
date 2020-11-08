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
      	<form  method="POST" action="/confirmEdit">
      		<label >How many doors to open</label>
      		<select name="selectedDoors">
      			<c:forEach items="${currentDoors}" var="door">
      				<option><c:out value="${currentDoors.indexOf(door)}" /></option>
      			</c:forEach>
      		</select>
      		<br/>
      		<label >How many windows to open</label>
      		<select name="selectedWindows">
      			<c:forEach items="${currentWindows}" var="window">
      				<option><c:out value="${currentWindows.indexOf(window)}" /></option>
      			</c:forEach>
      		</select>
      		<br/>
      		<label >How many lights to open</label>
      		<select name="selectedLights">
      			<c:forEach items="${currentLights}" var="light">
      				<option><c:out value="${currentLights.indexOf(light)}" /></option>
      			</c:forEach>
      		</select>
      		<br/>
      		<input type="submit" value="Submit">
      	</form>
    </body>
</html>