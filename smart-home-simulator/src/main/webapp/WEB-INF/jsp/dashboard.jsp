<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Dashboard - Smart Home Simulator</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/dashboard.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/dashboard.js"></script>
    <script src="/js/main.js"></script>

</head>
<body onload="getCurrentDate(), getCurrentTime()">
<div class="container-flex p-3 dashboard">
    <div class="row">
        <div class="profile col-2">
            <fieldset>
                <legend>Simulation</legend>
                <label class="switch">
                    <input type="checkbox" id="simSwitch" onclick="displaySimulator()">s
                    <span class="slider round"></span>
                </label>
                <img src="img/undefined_profile.png" alt="ProfilePic" class="profilePic">
                <button id="editBtn" onclick="redirectEditForm()">edit</button>
                <p>Date: <span id="displayDate"></span></p>
                <p>Time: <span id="displayTime"></span></p>
                <p>Location: location</p>
                <p>TEMPERATURE IS QUITE COLD</p>
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
                            <div id="SHC" class="tabContent"><br/>
                                <h3>SHC</h3>
                                <p>Smart Home Core Functionality.</p>
                            </div>
                            <div id="SHS" class="tabContent">
                                <fieldset>
                                    <div class="m-1">
                                        <label for="addUser">Add/Remove User Profiles</label>
                                        <input id="addUser" type="text"></input>
                                    </div>

                                    <div class="m-1"><label for="inTime">Edit Time</label>
                                        <input type="time" id="inTime" name="time"></div>

                                    <div class="m-1">
                                        <label for="inTime">Edit Date</label>
                                        <input type="date" id="inDate" name="date"></type>
                                    </div>

                                    <input class="btn btn-primary m-1" type="submit" value="Save"
                                           onclick="setNewDate();setNewTime()"/>
                                </fieldset>
                            </div>
                            <div id="SHH" class="tabContent"><br/>
                                <h3>SHH</h3>
                                <p>Smart Home Heating.</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-7" style="text-align:center">
                        <div class="houseLayout border border-dark">
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
</body>
</html>

