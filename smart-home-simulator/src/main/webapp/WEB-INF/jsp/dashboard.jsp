<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page import="smarthomesimulator.model.Simulator" %>
<%@page import="smarthomesimulator.model.Profile" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:wrapper>
    <script src="/js/dashboard.js"></script>

    <div class="container-fluid p-3 dashboard">
        <div class="row">
            <div class="profile col-1 pt-1 pb-1 rounded" id="dashboardContextContent">
                    <h4 class="mb-2 text-center">Simulation</h4>
                    <label class="switch d-block m-auto">
                        <input type="checkbox" id="simSwitch" onclick="displayLayout()">s
                        <span class="slider round"></span>
                    </label>
                    <img src="/img/undefined_profile.png" alt="ProfilePic" class="profilePic d-block m-auto">
                    <button class="btn btn-primary d-block ml-auto mr-auto mt-2 mb-1" id="editBtn" onclick="redirectEditForm()">edit</button>
                    <p class="d-block">Date: {{ date }}</p>
                    <p class="d-block">Time: {{ time }}</p>
                    <p class="d-block">House Layout: {{ layout }}</p>
                    <p class="d-block">Location: {{ location }}</p>
                    <p class="d-block">Temperature: {{ tempOut }} &#176;C</p>
                    <p class="d-block">cout: {{ cOut }} &#176;C</p>
            </div>

            <div class="col-4 justify-content-center pl-4 pr-4">
                <div class="border rounded">
                    <div class="row justify-content-center mt-1 ml-5 mr-5">
                        <div class="btn-group" role="group">
                            <button class="tab btn btn-outline-primary" onclick="openModule(event, 'SHS')">SHS</button>
                            <button class="tab btn btn-outline-primary" onclick="openModule(event, 'SHC')">SHC</button>
                            <button class="tab btn btn-outline-primary" onclick="openModule(event, 'SHP')">SHP</button>
                            <button class="tab btn btn-outline-primary" onclick="openModule(event, 'SHH')">SHH</button>
                            <button class="tab btn btn-outline-primary" onclick="openModule(event, '+')">+</button>
                        </div>
                    </div>
                    <div class="row params p-1 overflow-auto mt-1">
                        <div id="SHS" class="w-100 tabContent ml-4 mr-4">
                            <form onsubmit="editContext(event)">
                                <h5>Parameters</h5>
                                <div class="form-row">
                                    <div class="col">
                                        <label for="date">Edit Date: </label>
                                        <input class="form-control" type="date" id="date" name="date"/>
                                    </div>
                                    <div class="col">
                                        <label for="time">Edit Time:</label>
                                        <input class="form-control" type="time" id="time" name="time"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="tempOut">Edit Temperature:</label>
                                    <input class="form-control" type="number" step="0.01" id="tempOut" name="tempOut"/>
                                </div>
                                <button class="btn btn-outline-dark" type="submit">Submit</button>
                            </form>
                            <form onsubmit="editProfile(event)">
                                <h5>Profile: </h5>
                                <div class="form-group">
                                    <label for="name">Person's Name: </label>
                                    <input class="form-control" type="text" id="name" name="name"/>
                                </div>

                                <div class="form-group">
                                    <label for="role">Role</label>
                                    <select class="form-control" id="role" name="role">
                                        <option value="CHILD">Child</option>
                                        <option value="GUEST">Guest</option>
                                        <option value="PARENT">Parent</option>
                                        <option value="STRANGER">Stranger</option>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <label for="location">Location:</label>
                                    <input class="form-control" id="location" name="location"/>
                                </div>

                                <button class="btn btn-outline-dark" type="submit">Submit</button>
                            </form>
                        </div>
                        <div id="SHC" class="w-100 tabContent ml-4 mr-4"><br/>
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
                        <div id="SHP" class="w-100 tabContent ml-4 mr-4">
                            <form onsubmit="changePrivacySettings(event)">
                                Set away:
                                <label class="switch">
                                    <input type="checkbox">
                                    <span class="slider round"></span>
                                </label>
                                <div class="form-group">
                                    <label for="shpRoom">Select Room:</label>
                                    <select class="form-control" id="shpRoom" name="shpRoom">
                                        <c:forEach var="room" items="${RoomList}">
                                            <option value="${room.getRoomName()}">${room.getRoomName()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="startTime">Start Time:</label>
                                    <input class="form-control" id="startTime" name="startTime" />
                                </div>

                                <div class="form-group">
                                    <label for="endTime">End Time:</label>
                                    <input class="form-control" id="endTime" name="endTime" />
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

                                <div class="form-group">
                                    <label for="alertTime">Alert Time (in minutes):</label>
                                    <input class="form-control" id="alertTime" name="alertTime" />
                                </div>

                                <div class="form-check">
                                    <input type="checkbox" class="form-check-input" id="lightsSHP" name="lightsSHP" value=""/>
                                    <label class="form-check-label" for="lightsSHP">Set lights</label>
                                </div>

                                <button class="btn btn-outline-dark" type="submit">Submit</button>
                            </form>
                        </div>
                        <div id="SHH" class="w-100 tabContent ml-4 mr-4"><br/>
                            <p>Smart Home Heating.</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-7 justify-content-center ">
                <div style="text-align:center">
                    <div class="houseLayout border rounded p-1">
                        <div class="w-100 h-100 overflow-hidden">
                            <c:forEach items="${RoomList}" var="room">
                                <button class="rooms">
                                    <img src="${room.canEnter() ? 'img/doorOpen.jpg': 'img/doorClosed.jpg'}"
                                         class="${ room.findDoors() ? 'doors' : 'display:none' }"/>
                                    <img src="${room.isBright() ? 'img/lightsOn.png': 'img/lightsOff.png'}"
                                         class="${ room.findLights() ? 'lights' : 'display:none' }"/>
                                    <img src="${room.isWindy() ? 'img/windowsOpen.jpg': 'img/windowsClosed.jpg'}"
                                         class="${ room.findWindows() ? 'windows' : 'display:none' }"/>
                                    <c:out value="${room.getRoomName()}"/>
                                </button>
                            </c:forEach>
                        </div>
                    </div>
                    <span>House Layout</span>
                </div>
            </div>


        </div>
        <div class="row justify-content-center">
            <div class="consoleOutput overflow-auto w-75 p-3" id="consoleOut">
                        <textarea class="p-1 border rounded" id="consoleOutput" rows="7" placeholder="Console output will appear here"
                                  readonly>Date: {{ cOut }}</textarea>
            </div>
        </div>
    </div>

</t:wrapper>



