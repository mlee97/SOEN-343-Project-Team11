<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page import="smarthomesimulator.model.Simulator" %>
<%@page import="smarthomesimulator.model.Profile" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:wrapper>
    <script src="/js/dashboard.js"></script>
    <script src="/js/shhTab.js"></script>

    <div class="container-fluid p-3 dashboard">
        <div class="row">
            <div class="profile col-1 pt-1 pb-1 rounded" id="dashboardContextContent">
                <h4 class="mb-2 text-center">Simulation</h4>
                <label class="switch d-block m-auto">
                    <input type="checkbox" id="simSwitch" onclick="displayLayout()">
                    <span class="slider round"></span>
                </label>
                <img src="/img/undefined_profile.png" alt="ProfilePic" class="profilePic d-block m-auto">
                <p class="d-block">Date: {{ date }}</p>
                <p class="d-block"><div id="clock"></div></p>
                <p class="d-block">House Layout: {{ layout }}</p>
                <p class="d-block">Location: {{ location }}</p>
                <p class="d-block">Outside Temperature: {{ tempOut }} &#176;C</p>
                <p class="d-block">Default Indoor Temperature: {{ defaultTempIn }} &#176;C</p>
                <p class="d-block"><div id="inTemp"></div></p>
            </div>

            <div class="col-4 justify-content-center pl-4 pr-4">
                <div class="border rounded" style="padding-right:15px">
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
                            <span id="profile-parameters">
                                <button class="btn btn-primary" id="editBtn" onclick="displayProfiles('show')">Manage Profiles</button>
                                    <button v-if="showProfiles" class="btn btn-primary" id="editBtn" onclick="displayProfiles('add')">Add Profiles</button>
                                    <form v-if="addProfilesForm && showProfiles"onsubmit="addProfile(event)">
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
                                    <button v-if="showProfiles" class="btn btn-primary" id="editBtn" onclick="displayProfiles('delete')">Delete Profiles</button>
                                    <div v-if="deleteProfilesForm && showProfiles" class="profileDisplay">
                                        Profiles:
                                        <span v-if="profile" v-for="profile in profileList">
                                            Name:{{profile.name}} 
                                            Role:{{profile.role}} 
                                            Location:{{profile.location}}
                                            <button class="btn btn-danger" @click="deleteProfile(profile)">X</button>
                                        </span>
                                    </div>
                            </span>
                            <div>
                                <form:form method = "GET" action = "/printProfiles">
                                    <div class="form-group">
                                        <a href="download" value = "printProfiles/">Download Profiles</a>
                                    </div>
                                </form:form>
                            </div>
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
                                            <button type="button" class="btn btn-danger" onclick="blockWindow(event, '${room.getRoomName()}')">Block</button>
                                            <button type="button" class="btn btn-secondary" onclick="unblockWindow(event, '${room.getRoomName()}')">Unblock</button>
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
                                                    <button type="button" class="btn btn-danger" onclick="blockDoors(event,'${room.getRoomName()}')">Block</button>
                                                    <button type="button" class="btn btn-secondary" onclick="unblockDoors(event, '${room.getRoomName()}')">Unblock</button>
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
                                <input type="checkbox" >
                                <span class="slider round"></span>
                            </label>
                        </div>
                        <div id="SHP" class="w-100 tabContent ml-4 mr-4">
                            <form onsubmit="changePrivacySettings(event)">
                                Set away:
                                <label class="switch" id="shpAwayMode">
                                    <input type="checkbox" @click="activateAwayMode()" v-model="awayMode">
                                    <span class="slider round"></span>
                                </label>
                                <div class="form-group">
                                    <label for="shpRoom">Select Room:</label>
                                    <select class="form-control" id="shpRoom" name="nameRoom">
                                        <c:forEach var="room" items="${RoomList}">
                                            <option value="${room.getRoomName()}">${room.getRoomName()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="startTime">Start Time:</label>
                                    <input class="form-control" type="time" id="startTime" name="startTime" />
                                </div>

                                <div class="form-group">
                                    <label for="endTime">End Time:</label>
                                    <input class="form-control" type="time" id="endTime" name="endTime" />
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
                            <form id="shhZone" onsubmit="addZone(event)">
                                <h5>Add Zone</h5>
                                <div class="form-group">
                                    <div class="form-row">
                                        <div class="col">
                                            <label for="zoneName">Zone Name: </label>
                                            <input class="form-control" type="text" id="zoneName" v-model="name" />
                                        </div>
                                        <div class="col">
                                            <label for="zoneSetting">Zone Setting:</label>
                                            <select class="form-control" id="zoneSetting" type="boolean" v-model="setting">
                                                <option :value="false">Cooling</option>
                                                <option :value="true">Heating</option>
                                            </select>
                                        </div>
                                        <div class="col">
                                            <label for="tempOut">Zone Temperature:</label>
                                            <input class="form-control" type="number" step="0.01" id="zoneTemp" v-model="temperature"/>
                                        </div>
                                    </div>
                                </div>
                                <button class="btn btn-outline-dark" type="submit">Add</button>
                            </form>



                            <form id="shhVariables" @submit="changeZone($event)" >
                                <h5>Edit Zones</h5>
                                <div class="form-group">
                                    <label>Zones:</label>
                                    <select selected="selected" @change="onSelected($event)" class="form-control">
                                        <option :value="null">-- Select Zone --</option>
                                        <option v-for="(item, index) in zones" :value="index">
                                            {{ item.name }}
                                        </option>
                                    </select>

                                    <div class="form-row">
                                        <div class="col">
                                            <label for="zoneSetting">Zone Setting:</label>
                                            <select class="form-control" id="selectedZoneSetting" type="boolean" v-model="selectedZone.setting">
                                                <option :value="false">Cooling</option>
                                                <option :value="true">Heating</option>
                                            </select>
                                        </div>
                                        <div class="col">
                                            <label for="tempOut">Zone Temperature:</label>
                                            <input class="form-control" type="number" step="0.01" id="selectedZoneTemp" v-model="selectedZone.temperature"/>
                                        </div>
                                    </div>
                                </div>
                                <button class="btn btn-outline-dark" type="submit">Change</button>
                            </form>

                            <div id="shhRoomTemperatures">
                                <h5>Edit Room Temperatures</h5>
                                <table class="table table-hover">
                                    <thead>
                                    <tr>
                                        <th scope="col">Room</th>
                                        <th scope="col">Temperature</th>
                                        <th scope="col">Zone</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr v-for="(item, index) in rooms" :var="item.roomName">
                                        <th scope="row">
                                            {{ item.roomName }}
                                        </th>
                                        <td class="row">
                                            <div class="input-group col-8">
                                                <input class="form-control" type="number" @blur="overrideTemperature(item.roomName, index, $event)" step="0.01" id="roomTemperature" v-model="item.temperature"/>
                                                <div class="input-group-append">
                                                    <button @click="resetTemperature(item.roomName, index)" class="btn btn-outline-secondary" type="button" id="coolButton" :disabled="!item.overridden">Reset</button>
                                                </div>
                                            </div>
                                                <small id="overridden" class="text-danger" v-if="item.overridden">
                                                    Overridden
                                                </small>
                                        </td>
                                        <td>
                                            <select selected="roomSelectedZone" @change="onRoomSelected($event, index, item.roomName)" class="form-control">
                                                <option :value="-1">No Zone</option>
                                                <option v-for="(item, index) in zones" :value="index">
                                                    {{ item.name }}
                                                </option>
                                            </select>
                                        </td>

                                    </tr>
                                    </tbody>
                                </table>
                            </div>

                            <form id="shhSeasonTemperature" @submit="shhChangeSeasonTemperature($event)">
                                <h5>Set Seasonal Away Temperature</h5>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col">
                                            <label for="summer">Summer Time:</label>
                                            <input id="summer" type="number" step="0.01" class="form-control" placeholder="Temperature" v-model="summerTemp"/>
                                        </div>
                                        <div class="col">
                                            <label for="winter">Winter Time:</label>
                                            <input id="winter" type="number" step="0.01" class="form-control" placeholder="Temperature" v-model="winterTemp"/>
                                        </div>
                                    </div>
                                </div>
                                <button class="btn btn-outline-dark" type="submit">Change</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-7 justify-content-center ">
                <div style="text-align:center">
                    <div class="houseLayout border rounded p-1" id="house-layout">
                        <div id="simulatorSwitchedOnOrOffStopChangingThis">
                            <div class="roomDisplay w-100 h-100 overflow-hidden">
                                <span v-for="room in roomList">
                                    <button class="rooms">
                                        <span v-if="room.hasDoors">
                                            <img v-if="room.isEnterable == true" src="img/doorOpen.jpg" class="doors"/>
                                            <img v-if="room.isEnterable == false" src="img/doorClosed.jpg" class="doors"/>
                                        </span>
                                        <span v-if="room.hasWindows">
                                            <img v-if="room.isWindy == true" src="img/windowsOpen.jpg" class="windows"/>
                                            <img v-if="room.isWindy == false" src="img/windowsClosed.jpg" class="windows"/>
                                        </span>
                                        <span v-if="room.hasLights">
                                            <img v-if="room.isBright == true" src="img/lightsOn.png" class="lights"/>
                                            <img v-if="room.isBright == false" src="img/lightsOff.png" class="lights"/>
                                        </span>
                                        <span v-if="room.hasSomebody">
                                            <img src="img/person.jpg" class="profileList"/>
                                        </span>
                                        {{ room.name }}
                                        <div v-bind:id="room.name+'Display'">
                                            {{ room.temperature }} &#176;C
                                        </div>
                                    </button>
                                </span>
                            </div>
                        </div>
                    </div>
                    <span>House Layout</span>
                </div>
            </div>


        </div>
        <div class="row justify-content-center">
            <div class="consoleOutput overflow-auto w-75 p-3" id="consoleOut">
                        <textarea class="p-1 border rounded" id="consoleOutput" rows="7" placeholder="Console output will appear here"
                                  readonly>{{ cOut }}</textarea>
            </div>
        </div>
    </div>

</t:wrapper>



