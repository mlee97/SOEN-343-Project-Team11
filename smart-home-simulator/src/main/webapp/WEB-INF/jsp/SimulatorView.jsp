<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="smarthomesimulator.layout.ParseLayout,java.util.ArrayList,smarthomesimulator.model.Room" %>

<html>
    <head>
        <title>Current Simulator</title>
	<style><%@include file="./Simulator.css"%></style> 
    </head>
	<body>
    	<h2>Submitted Simulation Information</h2>
    	<table>
        	<tr>
            	<td>Date :</td>
            	<td>${date}</td>
        	</tr>
        	<tr>
            	<td>Time :</td>
            	<td>${time}</td>
        	</tr>
        	<tr>
            	<td>Outside Temperature:</td>
            	<td>${tempOut}</td>
        	</tr>
        	<tr>
            	<td>Default Indoor Room Temperature :</td>
            	<td>${defaultTempIn}</td>
        	</tr>
        	<tr>
            	<td>House Layout :</td>
            	<td> ${fileName}</td>
        	</tr>
    	</table>
      List of Rooms:
    <br />
</body>
</html>