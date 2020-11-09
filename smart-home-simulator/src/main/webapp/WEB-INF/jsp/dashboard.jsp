<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page import="smarthomesimulator.model.Simulator" %>
<%@page import="smarthomesimulator.model.Profile" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<t:wrapper>
    <div class="container-fluid p-3 dashboard">
        <div class="row">
            <div class="profile col-2">
                <fieldset>
                    <legend>Simulation</legend>
                    <label class="switch">
                        <input type="checkbox" id="simSwitch" onclick="displaySimulator()">s
                        <span class="slider round"></span>
                    </label>
                    <img src="/img/undefined_profile.png" alt="ProfilePic" class="profilePic">
                    <button id="editBtn" onclick="redirectEditForm()">edit</button>                              
                    <p>Date: ${simulator.getDate()}</p>
                    <p>Time: ${simulator.getTime()}</p>
                    <p>House Layout: ${simulator.getFileName()}</p>
                    <p>Location: Outside</p>
                    <p>Temperature: ${simulator.getTempOut()} &#176;C</p>
                </fieldset>
            </div>

            <div id="simulator" class="col-10 border border-dark">
                <div class="modules">
                    <div class="row justify-content-start">
                        <div class="col-12">
                            <button class="tab btn btn-outline-dark" onclick="openModule(event, 'SHS')">SHS</button>
                            &nbsp;
                            <button class="tab btn btn-outline-dark" onclick="openModule(event, 'SHC')">SHC</button>
                            &nbsp;
                            <button class="tab btn btn-outline-dark" onclick="openModule(event, 'SHP')">SHP</button>
                            &nbsp;
                            <button class="tab btn btn-outline-dark" onclick="openModule(event, 'SHH')">SHH</button>
                            &nbsp;
                            <button class="tab btn btn-outline-dark" onclick="openModule(event, '+')">+</button>
                        </div>
                    </div>
                    <div class="row justify-content-center">
                        <div class="col-5">
                            <div class="params p-1 border border-dark">
                                <div id="SHS" class="tabContent"><br/>
                                <table>
                                    <form:form method="POST" action="/dashboard/context" modelAttribute="simulator">
                                        <tr>
                                            <td><form:label path="date">Edit Date (YYYY-MM-DD): </form:label></td>
                                            <td><form:input path="date"/></td>
                                        </tr>
                                        <tr>
                                            <td><form:label path="time">Edit Time:</form:label></td>
                                            <td><form:input path="time"/></td>
                                        </tr>
                                        <tr>
                                            <td><form:label path="tempOut">Edit Temperature:</form:label></td>
                                            <td><form:input path="tempOut" /></td>
                                        </tr>
                                        <tr>
                                            <td><input class="btn btn-outline-dark" type="submit" value="Submit"/></td>
                                        </tr>
                                    </form:form>
                                </table>
                                    <div>
                                        <h4>Enter new profile: </h4>
                                        <table>
                                            <form:form method="POST" action="/dashboard/addProfileDashboard" modelAttribute="profile">
                                                <tr>
                                                    <td><form:label path="name">Person's Name: </form:label></td>
                                                    <td><form:input path="name"/></td>
                                                </tr>
                                                <tr>
                                                    <td><form:label path="role">Role</form:label></td>
                                                    <td><form:select path="role" >
                                                        <form:options/>
                                                    </form:select></td>
                                                </tr>
                                                <tr>
                                                    <td><form:label path="location">Location:</form:label></td>
                                                    <td><form:input path="location" /></td>
                                                </tr>
                                                <tr>
                                                    <td><input href="/dashboard" class="btn btn-outline-dark" type="submit" value="Submit"/></td>
                                                </tr>
                                            </form:form>
                                        </table>
                                    </div>

                                    <div>
                                        <form:form method = "GET" action = "/printProfiles">
                                            <table>
                                                <tr>
                                                    <td>
                                                        <input type = "submit" value = "printProfiles"/>
                                                    </td>
                                                </tr>
                                            </table>
                                        </form:form>
                                    </div>

                                </div>
                                <div id="SHC" class="tabContent"><br/>
                                    <h4>Controls</h4>
                                    <div class="btn-group" role="group" id="shcGroup">
                                        <button class="shcTab btn btn-outline-dark" id="windowsTab" onclick="shcModule(event, 'windowContent')">Windows</button>
                                        <button class="shcTab btn btn-outline-dark" id="doorsTab" onclick="shcModule(event, 'doorsContent')">Doors</button>
                                        <button class="shcTab btn btn-outline-dark" id="lightsTab" onclick="shcModule(event, 'lightsContent')">Lights</button>
                                    </div>
                                    <table class="table shcTabContent" id="windowContent" style="display: none">
                                        <tr>
                                            <th scope="col">Room</th>
                                            <th scope="col"></th>
                                        </tr>
                                        <c:forEach items="${RoomList}" var="room">
                                            <tr>
                                                <th scope="row">
                                                        ${room.getRoomName()}
                                                </th>
                                                <td>
                                                    <button type="button" class="btn btn-dark" onclick="openWindow(event, '${room.getRoomName()}')">Open</button>
                                                    <button type="button" class="btn btn-outline-dark" onclick="closeWindow(event, '${room.getRoomName()}')">Close</button>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                    <table class="table shcTabContent" id="doorsContent" style="display: none">
                                        <tr>
                                            <th scope="col">Room</th>
                                            <th scope="col"></th>
                                        </tr>
                                            <form:form >
                                                <c:forEach items="${RoomList}" var="room">
                                        <tr>
                                                        <th scope="row">
                                                                ${room.getRoomName()}
                                                        </th>
                                                        <td >
                                                            <button type="button" class="btn btn-dark" onclick="openDoors(event, '${room.getRoomName()}')">Open</button>
                                                            <button type="button" class="btn btn-outline-dark" onclick="closeDoors(event, '${room.getRoomName()}')">Close</button>
                                                        </td> </tr>
                                                </c:forEach>
                                            </form:form>
                                    </table>
                                    <table class="table shcTabContent" id="lightsContent" style="display: none">
                                        <tr>
                                            <th scope="col">Room</th>
                                            <th scope="col"></th>
                                        </tr>
                                            <form:form >
                                                <c:forEach items="${RoomList}" var="room">
                                                <tr>
                                                <th scope="row">
                                                        ${room.getRoomName()}
                                                </th>
                                                <td >
                                                    <button type="button" class="btn btn-light" onclick="onLights(event, '${room.getRoomName()}')">On</button>
                                                    <button type="button" class="btn btn-outline-dark" onclick="offLights(event, '${room.getRoomName()}')">Off</button>
                                                </td>
                                                </tr>
                                                </c:forEach>
                                            </form:form>
                                    </table>
                                    <h4>Lights AUTO MODE</h4>
                                    <label class="switch">
                                        <input type="checkbox" onclick="activateAwayMode()" value="${awayMode}">
                                        <span class="slider round"></span>
                                    </label>
                                </div>
                                <div id="SHP" class="tabContent"><br/>
                                    <table>
                                        <form:form method="POST" action="/dashboard/shp" modelAttribute="shp">
                                             <tr>
                                                <td>Set away:</td>
                                                <td>
                                                    <label class="switch">
                                                      <input type="checkbox">
                                                      <span class="slider"></span>
                                                    </label>
                                                </td>
                                             <tr/>
                                             <tr>
                                                <td>Select Room:</td>
                                                <td>
                                                     <form:select path="shpRoom">
                                                        <c:forEach var="room" items="${RoomList}">
                                                            <form:option value="${room.getRoomName()}"/>
                                                        </c:forEach>
                                                     </form:select>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td><form:label path="startTime">Start Time:</form:label></td>
                                                <td><form:input path="startTime"/></td>
                                            </tr>
                                            <tr>
                                                <td><form:label path="endTime">End Time:</form:label></td>
                                                <td><form:input path="endTime" /></td>
                                            </tr>
                                            <tr>
                                                <td><form:label path="alertTime">Alert Time (in minutes):</form:label></td>
                                                <td><form:input path="alertTime" /></td>
                                            </tr>
                                            <tr>
                                                <td><form:label path="lightsSHP">Set lights:</form:label></td>
                                                <td><form:checkbox path="lightsSHP" value="Set Lights:"/></td>
                                            </tr>
                                            <tr>
                                                <td><input class="btn btn-outline-dark" type="submit" value="Submit"/></td>
                                            </tr>
                                        </form:form>
                                    </table>
                                </div>
                                <div id="SHH" class="tabContent"><br/>
                                    <h3>SHH</h3>
                                    <p>Smart Home Heating.</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-7" style="text-align:center">
                            <div class="houseLayout border border-dark">
                            	<script>
                            	function redirectPage(pageName){
                            	    window.location.href=pageName;
                            	}
                            	</script>
                            	<div class="house">
        							<c:forEach items="${RoomList}" var="room">
    									<button class="rooms" onclick=redirectPage("${room.getRoomName()}/editRoom")>
    									<img src="${room.canEnter() ? 'img/doorOpen.jpg': 'img/doorClosed.jpg'}" class="${ room.findDoors() ? 'doors' : 'display:none' }"/>
    									<img src="${room.isBright() ? 'img/lightsOn.png': 'img/lightsOff.png'}" class="${ room.findLights() ? 'lights' : 'display:none' }"/>
    									<img src="${room.isWindy() ? 'img/windowsOpen.jpg': 'img/windowsClosed.jpg'}" class="${ room.findWindows() ? 'windows' : 'display:none' }"/>
    										<c:out value="${room.getRoomName()}"/> 
    									</button>
									</c:forEach>
    							</div>
                            </div>
                            <span>House Layout</span>
                        </div>
                    </div>
                    <div class="row">
                        <div class="consoleOutput col-12 overflow-auto">
                        <textarea id="consoleOutput" rows=5 placeholder="Console output will appear here"
                                  readonly></textarea>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</t:wrapper>



