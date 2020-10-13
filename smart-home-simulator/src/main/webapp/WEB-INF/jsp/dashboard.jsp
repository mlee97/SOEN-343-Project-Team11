<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Dashboard - Smart Home Simulator</title>
        <link href="/css/dashboard.css" rel="stylesheet">
    </head>
    <body>
        <script src="/js/main.js"></script>
        <div class="container">
            <div class="header">Smart Home Simulator</div>
            <div class="dashboard">
                <div class="profile">
                    <fieldset>
                        <legend>Simulation</legend>
                        <label class="switch">
                          <input type="checkbox">
                          <span class="slider round"></span>
                        </label>
                        <img src="undefined_profile.png" alt="ProfilePic" class="profilePic">
                    </fieldset>
                </div>
                <div class="simulator">
                  <div class="modules">

                    <button class="tab" onclick="openModule(event, 'SHS')">SHS</button>
                    <button class="tab" onclick="openModule(event, 'SHC')">SHC</button>
                    <button class="tab" onclick="openModule(event, 'SHP')">SHP</button>
                    <button class="tab" onclick="openModule(event, 'SHH')">SHH</button>
                    <button class="tab" onclick="openModule(event, '+')">+</button>

                    <div class="params">

                      <div id="SHS" class="tabcontent"><br/>
                        <h3>SHS</h3>
                        <p>Smart Home Simulator.</p>
                      </div>

                      <div id="SHC" class="tabcontent"><br/>
                        <h3>SHC</h3>
                        <p>Smart Home Core Functionality.</p>
                      </div>

                      <div id="SHP" class="tabcontent"><br/>
                        <h3>SHP</h3>
                        <p>Smart Home Security.</p>
                      </div>

                      <div id="SHH" class="tabcontent"><br/>
                        <h3>SHH</h3>
                        <p>Smart Home Heating.</p>
                      </div>
                    </div>

                    <div class="houseLayout">
                    </div>
                    <div class="consoleOutput">
                      <textarea id="consoleOutput" rows=10 placeholder="Console output will appear here"></textarea>
                    </div>


                  </div>
                </div>
            </div>
        </div>

        <script src="js/dashboard.js"></script>
    </body>
</html>
