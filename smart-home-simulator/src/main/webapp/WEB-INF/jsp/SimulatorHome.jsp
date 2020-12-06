<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page import="smarthomesimulator.model.Simulator" %>
<%@page import="smarthomesimulator.model.Profile" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:wrapper>
    <div class="container">
        <h3>Welcome, Enter The Simulator Details</h3>
        <form:form method="POST" action="${pageContext.request.contextPath}/simulator" modelAttribute="simulator">
            <div>
                <div class="form-group">
                    <form:label for="date" path="date">Date</form:label>
                    <form:input class="form-control" type="date" id="date" name="date" path="date"/>
                </div>
                <div class="form-group">
                    <form:label for="time" path="time">Time</form:label>
                    <form:input class="form-control" type="time" id="time" name="time" path="time"/>
                </div>
                <div class="form-group">
                    <form:label for="tempOut" path="tempOut">Outside Temperature</form:label>
                    <form:input class="form-control" type="number" step="0.01" id="tempOut" name="tempOut" path="tempOut"/>
                </div>
                <div class="form-group">
                    <form:label for="defaultTempIn" path="defaultTempIn">Default Indoor Room Temperature</form:label>
                    <form:input class="form-control" type="number" step="0.01" id="defaultTempIn" name="defaultTempIn" path="defaultTempIn"/>
                </div>
                <div class="form-group">
                    <form:label for="defaultSummerTemp" path="defaultSummerTemp">Default Indoor Away Summer Room Temperature</form:label>
                    <form:input class="form-control" type="number" step="0.01" id="defaultSummerTemp" name="defaultSummerTemp" path="defaultSummerTemp"/>
                </div>
                <div class="form-group">
                    <form:label for="defaultWinterTemp" path="defaultWinterTemp">Default Indoor Away Winter Room Temperature</form:label>
                    <form:input class="form-control" type="number" step="0.01" id="defaultWinterTemp" name="defaultWinterTemp" path="defaultWinterTemp"/>
                </div>
                <div class="form-group">
                    <form action="${pageContext.request.contextPath}/simulator" method="POST" >
                        <input type="file" value="Choose file" name="file">
                    </form>
                </div>
                <button class="btn btn-primary" type="submit">Submit</button>
            </div>
        </form:form>
    </div>
</t:wrapper>