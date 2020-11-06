<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page import="smarthomesimulator.model.Simulator,java.util.regex.*,smarthomesimulator.model.Room,smarthomesimulator.model.Door,java.util.ArrayList" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<html>
    <head>
        <title>Room context</title>
        
    </head>
    <body>
        <h3>Room is ${currentRoom} </h3>
      	<form>
      		<label for="doors">How many doors to open</label>
      		<select>
      		<c:forEach items="${currentDoors}" var="door">
        		<option> ${currentDoors.indexOf(door)} </option>
    		</c:forEach>
      		</select>
      	</form>
    </body>
</html>