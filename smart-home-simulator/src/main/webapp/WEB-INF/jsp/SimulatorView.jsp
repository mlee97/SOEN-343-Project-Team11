<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="smarthomesimulator.layout.ParseLayout,java.util.ArrayList,smarthomesimulator.model.Room" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<t:wrapper>

    	<h2>Submitted Simulation Information, Used For Debugging</h2>
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
    <br />
</t:wrapper>