var dashboardContext;
window.onload = async function () {
    const response = await fetch("/dashboard", {method: "GET"});
    let responseData = await response.json();
    console.log(responseData);
    dashboardContext = new Vue({
        el: "#dashboardContextContent",
        data: {date: responseData.date, time: responseData.time, layout: responseData.fileName, tempOut: responseData.tempOut, location: 'Placeholder'}
    })
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
    let layout = document.getElementsByClassName("houseLayout")[0];

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
    console.log(responseData);
    }

function activateAwayMode(){
    console.log('/dashboard');
    window.location = '/dashboard/awayMode';
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


