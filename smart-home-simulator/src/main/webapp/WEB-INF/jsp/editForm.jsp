<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:wrapper>

        <script src="/js/dashboard.js"></script>

        <h1>Edit Simulation</h1>

        <form action="">
            <p>Select Room:</p>
            <input type="radio" id="kitchen" name="room" value="kitchen">
            <label for="kitchen">kitchen</label><br/>
            <input type="radio" id="living_room" name="room" value="living_room">
            <label for="living_room">living room</label><br/>
            <input type="radio" id="master_bedroom" name="room" value="master_bedroom">
            <label for="master_bedroom">master bedroom</label><br/>
            <input type="radio" id="bathroom" name="room" value="bathroom">
            <label for="bathroom">bathroom</label><br/>
            <input type="radio" id="closet" name="room" value="closet">
            <label for="closet">closet</label><br/>
            <input type="radio" id="outside" name="room" value="outside">
            <label for="outside">outside home</label><br/>

            <p>Block Window Movement</p>
            <input type="checkbox" id="kitchen" name="window" value="kitchen">
            <label for="kitchen">kitchen</label><br/>
            <input type="checkbox" id="living_room" name="window" value="living_room">
            <label for="living_room">living room</label><br/>
            <input type="checkbox" id="master_bedroom" name="window" value="master_bedroom">
            <label for="master_bedroom">master bedroom</label><br/>
            <input type="checkbox" id="bathroom" name="window" value="bathroom">
            <label for="bathroom">bathroom</label><br/>
            <input type="checkbox" id="closet" name="window" value="closet">
            <label for="closet">closet</label><br/>
            <input type="checkbox" id="outside" name="window" value="outside">
            <label for="outside">outside home</label><br/>

            <input type="submit" onclick="redirectDashboard()" value="Submit">
        </form>
</t:wrapper>