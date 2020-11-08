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
      	<c:out value="${roomStatus}"></c:out><br/><br/><br/>
      		<c:if test="${currentRoom.getClosedDoors() >= 1 }">
      		<label >How many doors to open</label>
      		<select name="selectedDoors">
      			<option>0</option>
      			<c:forEach items="${currentDoors}" end="${currentDoors.size()-currentRoom.getOpenDoors()}" var="door">
      				<option><c:out value="${currentDoors.indexOf(door)+1}" /></option>
      			</c:forEach>
      		</select>
      		</c:if>
      		<br/>
      		<c:if test="${currentRoom.getClosedWindows() >= 1}">
      		<label >How many windows to open</label>
      		<select name="selectedWindows">
      			<option>0</option>
      			<c:forEach items="${currentWindows}" end="${currentWindows.size()-currentRoom.getOpenWindows()}" var="window">
      				<option><c:out value="${currentWindows.indexOf(window)+1}" /></option>
      			</c:forEach>
      		</select>
      		</c:if>
      		<br/>
      		<c:if test="${currentRoom.getClosedLights() >= 1 }">
      		<label >How many lights to open</label>
      		<select name="selectedLights">
      			<option>0</option>
      			<c:forEach items="${currentLights}" end="${currentLights.size()-currentRoom.getOpenLights()}" var="light">
      				<option><c:out value="${currentLights.indexOf(light)+1}" /></option>
      			</c:forEach>
      		</select>
      		</c:if>
      		<br/><br/><br/><br/>
      		<c:if test="${currentRoom.getOpenWindows() >=1 && currentRoom.getBlockedWindows() < currentWindows.size() }">
      		<label >How many windows to block</label>
      		<select name="blockedWindows">
      			<option>0</option>
      			<c:forEach items="${currentWindows}"  
      				end="${currentWindows.size()-currentRoom.getClosedWindows()-currentRoom.getBlockedWindows()}"
      			 	var="window">
      				<option><c:out value="${currentWindows.indexOf(window)+1}" /></option>
      			</c:forEach>
      		</select>
      		</c:if>
      		<br/>
      		<c:if test="${currentRoom.getOpenDoors() >=1 && currentRoom.getBlockedDoors() < currentDoors.size() }">
      		<label >How many doors to block</label>
      		<select name="blockedDoors">
      			<option>0</option>
      			<c:forEach items="${currentDoors}" 
      			end="${currentDoors.size()-currentRoom.getClosedDoors()-currentRoom.getBlockedDoors()}"
      			var="door">
      				<option><c:out value="${currentDoors.indexOf(door)+1}" /></option>
      			</c:forEach>
      		</select>
      		</c:if>
      		<br/><br/><br/><br/>
      		<c:if test="${currentRoom.getOpenDoors() >= 1 && currentRoom.getBlockedDoors() < currentDoors.size()}">
      		<label >How many doors to close</label>
      		<select name="closedDoors">
      			<option>0</option>
      			<c:forEach items="${currentDoors}"
      			end="${currentDoors.size()-currentRoom.getClosedDoors()-currentRoom.getBlockedDoors()}" 
      			var="door">
      				<option><c:out value="${currentDoors.indexOf(door)+1}" /></option>
      			</c:forEach>
      		</select>
      		</c:if>
      		<br/>
      		<c:if test="${currentRoom.getOpenWindows() >= 1 && currentRoom.getBlockedWindows() < currentWindows.size()})">
      		<label >How many windows to close</label>
      		<select name="closedWindows">
      			<option>0</option>
      			<c:forEach items="${currentWindows}" 
      			end="${currentWindows.size()-currentRoom.getClosedWindows()-currentRoom.getBlockedWindows()}"
      			var="window">
      				<option><c:out value="${currentWindows.indexOf(window)+1}" /></option>
      			</c:forEach>
      		</select>
      		</c:if>
      		<br/>
      		<c:if test="${currentRoom.getOpenLights() >= 1 }">
      		<label >How many lights to close</label>
      		<select name="closedLights">
      			<option>0</option>
      			<c:forEach items="${currentLights}" 
      			end="${currentLights.size()-currentRoom.getClosedLights()}"
      			var="light">
      				<option><c:out value="${currentLights.indexOf(light)+1}" /></option>
      			</c:forEach>
      		</select>
      		</c:if>
      		<br/>
      		<c:if test="${currentRoom.getBlockedDoors() >= 1}">
      		<label >How many doors to unblock</label>
      		<select name="unblockedDoors">
      			<option>0</option>
      			<c:forEach items="${currentDoors}" 
					end="${currentRoom.getBlockedDoors()}"      			
      				var="door">
      				<option><c:out value="${currentDoors.indexOf(door)+1}" /></option>
      			</c:forEach>
      		</select>
      		</c:if>
      		<c:if test="${currentRoom.getBlockedWindows() >= 1}">
      		<label >How many windows to unblock</label>
      		<select name="unblockedWindows">
      			<option>0</option>
      			<c:forEach items="${currentWindows}" 
      			end="${currentRoom.getBlockedWindows()}"
      			var="window">
      				<option><c:out value="${currentWindows.indexOf(window)+1}" /></option>
      			</c:forEach>
      		</select>
      		<br/>
      		</c:if>
      		<input type="submit" value="Submit">
      	</form>
    </body>
</html>