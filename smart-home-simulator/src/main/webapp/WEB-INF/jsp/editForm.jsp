<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:wrapper>

    <script src="/js/dashboard.js"></script>

    <h1>Edit Simulation</h1>

    <form action="">
        <p>Select Room:</p>
        <c:forEach items="${RoomList}" var="room">
        	<input type="checkbox"/>
    		<label for="${room.getRoomName()}">
    			${room.getRoomName()}
    		</label><br/> 
    	</c:forEach>

        <p>Block Window Movement</p>
        <c:forEach items="${RoomList}" var="room">
        	<input type="checkbox"/>
    		<label for="${room.getRoomName()}">
    			${room.getRoomName()}
    		</label><br/> 
    	</c:forEach>
        <input type="checkbox" id="outside" name="window" value="outside">
        <label for="outside">outside home</label><br/>

        <input type="submit" onclick="redirectDashboard()" value="Submit">
    </form>
</t:wrapper>