var dashboardContext;
var conOut;
var shhTab;
var shhRoom;
var shhZone;


window.onload = async function () {
    const response = await fetch("/dashboard", {method: "GET"});
    let responseData = await response.json();
    console.log(responseData);
    dashboardContext = new Vue({
        el: "#dashboardContextContent",
        data: {date: responseData.date, time: responseData.time, layout: responseData.fileName, tempOut: responseData.tempOut, location: 'Placeholder'}

    });
    conOut = new Vue({
        el: "#consoleOut",
        data: {cOut: ''}
    });

    loadSHHTab();

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

    console.log(evt.target);
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

function displayLayout() {
    var checkBox = document.getElementById("simSwitch");
    let layout = document.getElementsByClassName("houseSimulatorOnOff")[0];

    if (checkBox.checked == true) {
        layout.style.display = "block";
    }
    if (checkBox.checked == false) {
        layout.style.display = "none";
    }
}

function redirectEditForm() {
    window.location.href = "/editForm";
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
    displayConsoleOut();
}

async function editProfile(e){
    e.preventDefault();
    let object = {};
    const json = new FormData(e.target);
    json.forEach((value, key) => object[key] = value);
    let data = JSON.stringify(object);

    const response = await fetch("/dashboard/addProfileDashboard", {method: "POST", body: data, headers: {
        "Content-Type": "application/json",
    }});
    let responseData = await response.json();
    console.log(responseData);
    displayConsoleOut();
}

async function changePrivacySettings(e){
    e.preventDefault();
    let object = {};
    const json = new FormData(e.target);
    json.forEach((value, key) => object[key] = value);
    let data = JSON.stringify(object);
    console.log(data);
    const response = await fetch("/dashboard/shp", {method: "POST", body: data, headers: {
            "Content-Type": "application/json",
        }});
    let responseData = await response.json();
    console.log(responseData);
    displayConsoleOut();
    }

function activateAwayMode(){
    console.log('/dashboard');
    window.location = '/dashboard/awayMode';
    displayConsoleOut();
}

async function openWindow(e, room){
    e.preventDefault();

    const response = await fetch("/dashboard/openWindows", {method:'POST', body: room});
    let responseData = await response.json();
    console.log(responseData);
}
async function closeWindow(e, room){
    e.preventDefault();
    const response = await fetch("/dashboard/closeWindows", {method:'POST', body: room});
    let responseData = await response.json();
    console.log(responseData);
}

async function openDoors(e, room){
    e.preventDefault();

    const response = await fetch("/dashboard/openDoors", {method:'POST', body: room});

    let responseData = await response.json();
    console.log(responseData);
}
async function closeDoors(e, room){
    e.preventDefault();
    const response = await fetch("/dashboard/closeDoors", {method:'POST', body: room});

    let responseData = await response.json();
    console.log(responseData);}

async function onLights(e, room){
    e.preventDefault();

    const response = await fetch("/dashboard/onLights", {method:'POST', body: room});

    let responseData = await response.json();
    console.log(responseData);
}
async function offLights(e, room){
    e.preventDefault();
    const response = await fetch("/dashboard/offLights", {method:'POST', body: room});

    let responseData = await response.json();
    console.log(responseData);
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
                return;
            }
        }
    });
    shhRoom = new Vue({
        el: '#shhRoomTemperatures',
        data: {
            rooms: shhRoomResponseData,
            zones: shhTabResponseData,
            roomSelectedZone: "-1",
        },
        methods:{
            async onRoomSelected(event, roomName) {
                let object = {name: roomName, zoneID: event.target.value}
                const data = JSON.stringify(object);
                const response = await fetch("/dashboard/shhRoomChangeZone", {method: "POST", body: data, headers: {
                        "Content-Type": "application/json",
                    }});
            },
            async overrideTemperature(roomName, event){
                let object = {name: roomName, temp: event.target.value}
                const data = JSON.stringify(object);
                const response = await fetch("/dashboard/shhOverrideRoomTemperature", {method: "POST", body: data, headers: {
                        "Content-Type": "application/json",
                    }});
                console.log(response);
            },
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
}