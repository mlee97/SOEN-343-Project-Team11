var dashboardContext;
var houseParameters;
var conOut;
var shhTab;
var shhRoom;
var shhZone;
var shhSeason;
var hours = 0;
var minutes = 0;
var displayClock;
var defaultInTemp = 0.00;
var outTemp = 0.00;
var inTemp = 0.00;

window.onload = async function () {

    const response = await fetch("/dashboard", {method: "GET"});
    let responseData = await response.json();

    const house = await fetch("/dashboard/HouseParameters", {method:'POST'});
    let houseData = await house.json();
    
    dashboardContext = new Vue({
        el: "#dashboardContextContent",
        data: {date: responseData.date, time: responseData.time, layout: responseData.fileName, tempOut: responseData.tempOut, defaultTempIn: responseData.defaultTempIn, location: 'Placeholder'}

    });

    houseParameters = new Vue({
        el:"#house-layout",
        data: {
            roomList:{},
            profileList:{},
            showProfiles: false
        }
    });

    conOut = new Vue({
        el: "#consoleOut",
        data: {cOut: ''}
    });

    shhSeason = new Vue({
        el:"#shhSeasonTemperature",
        data: {summerTemp: responseData.defaultSummerTemp, winterTemp: responseData.defaultWinterTemp},
        methods:{
            async shhChangeSeasonTemperature(e){
                e.preventDefault();
                let object = {summerTemp: this.summerTemp, winterTemp: this.winterTemp}
                const data = JSON.stringify(object);
                await fetch("/dashboard/shhChangeSeasonalTemperature", {method: "POST", body: data, headers: {
                        "Content-Type": "application/json",
                }});
                displayConsoleOut();
            }
        }
    });

    let shpAwayMode = new Vue({
        el: "#shpAwayMode",
        data: {awayMode: responseData.awayMode},
        methods: {
            activateAwayMode: async function () {
                await fetch("/dashboard/awayMode", {method: 'POST'});
                await displayConsoleOut();

            }

        }
    });
    initHouse(houseData);
    loadSHHTab();
    retrieveTime();
    retrieveTemp();
}


function openModule(evt, modName) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabContent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tab btn btn-outline-primary");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(modName).style.display = "block";
    evt.currentTarget.className += " active";
}

function shcModule(evt, id){
    
    let tabcontent = document.getElementsByClassName("shcTabContent");
    for (let i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    let tablinks = document.getElementsByClassName("shcTab");
    for (let i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(id).style.display = "";
    evt.currentTarget.className += " active";
}

function displayProfiles(){
    houseParameters.showProfiles = !houseParameters.showProfiles;
}

function displayLayout() {
    
    let checkBox = document.getElementById("simSwitch")
    let layout = document.getElementById("simulatorSwitchedOnOrOffStopChangingThis")
    
    if (checkBox.checked == true) {
        layout.style.display = "block";
    }
    if (checkBox.checked == false) {
        layout.style.display = "none";
    }
}

function redirectDashboard() {
    window.location.href = "/dashboard";
}

async function editContext(e){
    e.preventDefault();
    let object = {};
    const json = new FormData(e.target);
    json.forEach((value, key) => object[key] = value);
    const data = JSON.stringify(object)
    const response = await fetch("/dashboard/context", {method: "POST", body: data, headers: {
        "Content-Type": "application/json",
        }});
        let responseData = await response.json();
        dashboardContext.date = responseData.date;
    dashboardContext.time= responseData.time;
    dashboardContext.layout= responseData.fileName;
    dashboardContext.tempOut= responseData.tempOut;
    dashboardContext.defaultTempIn = responseData.defaultTempIn;
    updateTime();
    await displayConsoleOut();
}

async function editProfile(e){
    e.preventDefault();

    let profile = {};

    const json = new FormData(e.target);
    json.forEach((value, key) => profile[key] = value);
    let data = JSON.stringify(profile);

    Vue.set(houseParameters.profileList, profile.name, profile);
    
    const response = await fetch("/dashboard/addProfileDashboard", {method: "POST", body: data, headers: {
        "Content-Type": "application/json",
    }});
    let responseData = await response.json();
    await displayConsoleOut();
    isHere(profile.location);
}

async function changePrivacySettings(e){
    e.preventDefault();
    let object = {};
    const json = new FormData(e.target);
    json.forEach((value, key) => object[key] = value);
    let data = JSON.stringify(object);
    const response = await fetch("/dashboard/shp", {method: "POST", body: data, headers: {
        "Content-Type": "application/json",
    }});
    let responseData = await response.json();
    await displayConsoleOut();
    }
    

async function openWindow(e, room){
    e.preventDefault();
    
    const response = await fetch("/dashboard/openWindows", {method:'POST', body: room});
    let responseData = await response.json();
    houseParameters.roomList[responseData.roomName].isWindy = true;
    await displayConsoleOut();
}
async function closeWindow(e, room){
    e.preventDefault();

    const response = await fetch("/dashboard/closeWindows", {method:'POST', body: room});
    let responseData = await response.json();
    houseParameters.roomList[responseData.roomName].isWindy = false;
    await displayConsoleOut();
}

async function openDoors(e, room){
    e.preventDefault();
    
    const response = await fetch("/dashboard/openDoors", {method:'POST', body: room});
    let responseData = await response.json();
    houseParameters.roomList[responseData.roomName].isEnterable = true;
    await displayConsoleOut();
}
async function closeDoors(e, room){
    e.preventDefault();

    const response = await fetch("/dashboard/closeDoors", {method:'POST', body: room});
    let responseData = await response.json();
    houseParameters.roomList[responseData.roomName].isEnterable = false;
    await displayConsoleOut();
}

async function onLights(e, room){
    e.preventDefault();

    const response = await fetch("/dashboard/onLights", {method:'POST', body: room});
    let responseData = await response.json();
    houseParameters.roomList[responseData.roomName].isBright = true;
    await displayConsoleOut();
}
async function offLights(e, room){
    e.preventDefault();

    const response = await fetch("/dashboard/offLights", {method:'POST', body: room});
    let responseData = await response.json();
    houseParameters.roomList[responseData.roomName].isBright = false;
    await displayConsoleOut();
}


async function displayConsoleOut(){
    
    const response = await fetch("/dashboard/consoleOutput", {method: "POST"});
    const string = await response.text();
    conOut.cOut = string;
    
}


async function loadSHHTab(){
    const getSHH = await fetch("/dashboard/shh", {method: "GET"});
    let shhTabResponseData = await getSHH.json();
    
    const getRooms = await fetch("/dashboard/shhRooms", {method: "GET"});
    let shhRoomResponseData = await getRooms.json();
    
    let overriddenRooms =[];
    for (let room in shhRoomResponseData){
        overriddenRooms.push(room.overridden);
    }
    
    shhTab = new Vue({
        el: "#shhVariables",
        data: {selected: "null", zones: shhTabResponseData, selectedZone:{}},
        methods:{
            onSelected(event) {
                let selectedIndex = event.target.value
                this.selectedZone =  this.zones[selectedIndex]
            },
            async changeZone(e){
                e.preventDefault();
                const data = JSON.stringify(this.selectedZone);
                const response = await fetch("/dashboard/shhChangeZone", {method: "POST", body: data, headers: {
                        "Content-Type": "application/json",
                    }});
                let responseData = await response.json();
                this.zones = responseData;
                displayConsoleOut();
                return;
            }
        }
    });
    shhRoom = new Vue({
        el: '#shhRoomTemperatures',
        data(){ return {
            rooms: shhRoomResponseData,
            roomsOverridden: overriddenRooms,
            zones: shhTabResponseData,
            roomSelectedZone: "-1",}
        },
        methods:{
            onRoomSelected: async function(event,index, roomName) {
                let object = {name: roomName, zoneID: event.target.value}
                const data = JSON.stringify(object);
                const response = await fetch("/dashboard/shhRoomChangeZone", {method: "POST", body: data, headers: {
                        "Content-Type": "application/json",
                }});
                let responseData = await response.json();

                Vue.set(shhRoom.rooms, index, responseData);
                displayConsoleOut();

            },
            overrideTemperature: async function(roomName, index, event){
                let object = {name: roomName, temp: event.target.value}
                const data = JSON.stringify(object);
                const response = await fetch("/dashboard/shhOverrideRoomTemperature", {method: "POST", body: data, headers: {
                        "Content-Type": "application/json",
                    }});
                let responseData = await response.json();
                Vue.set(shhRoom.rooms, index, responseData);
                await displayConsoleOut();

            },
            resetTemperature: async function(roomName, index){
                let object = {name: roomName}
                const data = JSON.stringify(object);
                const response = await fetch("/dashboard/resetTemperature", {method: "POST", body: data, headers: {
                        "Content-Type": "application/json",
                    }});
                let responseData = await response.json();
                Vue.set(shhRoom.rooms, index, responseData);
                await displayConsoleOut();

            }

        }
    });

    shhZone = new Vue({
        el: '#shhZone',
        data:{
            name: '',
            setting: false,
            temperature: 0
        }
    });
}

async function addZone(e){
    e.preventDefault();
    let object = {name: shhZone.name, setting: shhZone.setting, temperature: shhZone.temperature};

    let data = JSON.stringify(object);
    
    const response = await fetch("/dashboard/shhAddZone", {method: "POST", body: data, headers: {
            "Content-Type": "application/json",
        }});
        let responseData = await response.json();
        shhTab.zones = responseData;
        shhRoom.zones = responseData;
        displayConsoleOut();
    }

    
    
initHouse = (houseData) => {
    
    for ( i = 0; i < Object.keys(houseData).length; i++){

        let room = {};
            
        let roomName = houseData[i].roomName;
        room.name = roomName;
        room.hasSomebody = false;
    
        if(houseData[i].closedDoors > 0 || houseData[i].openDoors > 0 || houseData[i].blockedDoors > 0){
            room.hasDoors = true;
        } else room.hasDoors = false;
            
        if(houseData[i].openDoors > 0 || houseData[i].blockedDoors > 0 ){
            room.isEnterable = true;
        }else room.isEnterable = false;
    
        if(houseData[i].closedWindows > 0 || houseData[i].openWindows > 0 || houseData[i].blockedWindows > 0){
            room.hasWindows = true;
        } else room.hasWindows = false;
    
        if(houseData[i].openWindows > 0 || houseData[i].blockedWindows > 0){
            room.isWindy = true;
        }else room.isWindy = false;
    
        if(houseData[i].closedLights > 0 || houseData[i].openLights > 0){
            room.hasLights = true;
        } else room.hasLights = false;
    
        if(houseData[i].openLights > 0 ){
            room.isBright = true;
        }else room.isBright = false;
    
        Vue.set(houseParameters.roomList, roomName, room);
            
    }        
}

function isHere(location){
    for(const room in houseParameters.roomList){
        if(location == room){
            houseParameters.roomList[room].hasSomebody = true;
        }
    }
}

async function retrieveTime(){

    const response = await fetch("/dashboard/getTime", {method: "POST"});
    const inTime = await response.text();
    return inTime;
}

retrieveTime().then(inTime => {    var hrs_mins = inTime.split(':');
                                   hours = parseInt(hrs_mins[0]);
                                   minutes = parseInt(hrs_mins[1]);
                                   displayClock = new custom_Clock();
                                   displayClock.run();} )

function custom_Clock(){
    this.hrs = hours;
    this.mins = minutes;
    this.secs = 0;
}
custom_Clock.prototype.run = function (){ setInterval(this.update.bind(this), 1000);};

custom_Clock.prototype.update = function ()
  {
    this.updateTime(1);

    if (this.mins < 10 && this.secs < 10) {
        time = this.hrs + ":0" + this.mins + ":0" + this.secs;
    }
    else if (this.mins < 10) {
        time = this.hrs + ":0" + this.mins + ":" + this.secs;
    }
    else if (this.secs < 10) {
        time = this.hrs + ":" + this.mins + ":0" + this.secs;
    }
    else{
        time = this.hrs + ":" + this.mins + ":" + this.secs;
    }
    document.getElementById("clock").innerText = "Time: " + time;
  };
custom_Clock.prototype.updateTime = function (seconds)
  {
     this.secs+= seconds;
     if (this.secs >= 60)
      {
          this.mins++;
          this.secs= 0;
      }
     if (this.mins >= 60)
      {
          this.hrs++;
          this.mins=0;
      }
     if (this.hrs >= 24)
      {
        this.hrs = 0;
      }
};

async function updateTime(){

    const response = await fetch("/dashboard/getTime", {method: "POST"});
    const inTime = await response.text();
    var hrs_mins = inTime.split(':');
    hours = parseInt(hrs_mins[0]);
    minutes = parseInt(hrs_mins[1]);
    displayClock.hrs = hours;
    displayClock.mins = minutes;
    displayClock.secs = 0;
}

async function retrieveTemp(){

    const response = await fetch("/dashboard/getTemps", {method: "POST"});
    const responseData = await response.json();
    let defaultInTemp = responseData.defaultTempIn;
    let outTemp = responseData.tempOut;
    let inTemp = responseData.tempIn;
    return await Promise.resolve([defaultInTemp, outTemp, inTemp]);
}

retrieveTemp().then((value) => {  defaultInTemp = value[0];
                                   outTemp = value[1];
                                   inTemp = value[2];
                                   console.log(outTemp);
                                   monitor = new monitor_Temp();
                                   monitor.run();} )

function monitor_Temp(){
    this.defaultInTmp = defaultInTemp;
    this.outTmp = outTemp;
    this.inTmp = inTemp;
}
monitor_Temp.prototype.run = function (){ setInterval(this.update.bind(this), 1000);};

monitor_Temp.prototype.update = function ()
  {

    if (this.inTmp < (defaultInTemp - 0.25)) {
        this.inTmp = this.inTmp + 0.1;
    }
    else if (this.inTmp > (defaultInTemp + 0.25)) {
        this.inTmp = this.inTmp - 0.1;
    }
    else {
       //Do nothing
    }

    document.getElementById("inTemp").innerText = "Inside Temperature: " + this.inTmp.toFixed(2) + " \u00B0C";
  };




