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
<body>
<div class="container">
    <div class="profile">
        <fieldset>
            <legend>Simulation</legend>
            <label class="switch">
                <input type="checkbox" id="simSwitch" onclick="displaySimulator()">s
                <span class="slider round"></span>
            </label>
            <img src="img/undefined_profile.png" alt="ProfilePic" class="profilePic">
            <button id="editBtn">edit</button>
        </fieldset>
    </div>
    <div id="simulator">
        <div class="modules">
            <div class="row justify-content-start">
                <div class="col-lg-12">
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
                <div class="col-lg-5">
                    <div class="params">

                        <div id="SHC" class="tabcontent"><br/>
                            <h3>SHC</h3>
                            <p>Smart Home Core Functionality.</p>
                        </div>
                        <div id="SHS" class="tabcontent"><br/>
                            <div id="editProfile">
                                <h3>Add/Remove User Profiles</h3>
                                <input type="text"></input>
                            </div>
                            <div id="editTime">
                                <h3>Edit Time</h3>
                                <input type="time" id="timeTime" name="time"></type>
                            </div>
                            <div id="editDate">
                                <h3>Edit Date</h3>
                                <input type="date" id="timeDate" name="time"></type>
                            </div>
                        </div>

                        <div id="SHH" class="tabcontent"><br/>
                            <h3>SHH</h3>
                            <p>Smart Home Heating.</p>
                        </div>
                    </div>
                </div>

                <div class="col-lg-7" style="text-align:center">
                    <div class="houseLayout">

                    </div>
                    <span>House Layout</span>
                </div>


            </div>

            <div class="row">
                <div class="consoleOutput col-lg-12">
                        <textarea id="consoleOutput" rows=10 placeholder="Console output will appear here"
                                  readonly></textarea>
                </div>
            </div>

        </div>
    </div>
</div>
</body>
</html>

