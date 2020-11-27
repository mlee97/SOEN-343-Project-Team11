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

/*function displaySimulator() {
    var checkBox = document.getElementById("simSwitch");
    var simulator = document.getElementById("simulator");

    if (checkBox.checked == true) {
        simulator.style.visibility = "visible";
    }
    if (checkBox.checked == false) {
        simulator.style.visibility = "hidden";
    }
}*/
function redirectEditForm() {
    window.location.href = "/editForm";
}

function redirectDashboard() {
    window.location.href = "/dashboard";
}

async function getDashboard(e){
    const reponse = await fetch("/dashboard", {method: "GET"});
    console.log(reponse);
}

async function editContext(e){
    e.preventDefault();
    let object = {};
    const json = new FormData(e.target);
    json.forEach((value, key) => object[key] = value);
    let data = JSON.stringify(object);

    const reponse = await fetch("/dashboard/context", {method: "POST", body: data, headers: {
            "Content-Type": "application/json",
        }});
    console.log(reponse);
}

async function editProfile(e){
    e.preventDefault();
    let object = {};
    const json = new FormData(e.target);
    json.forEach((value, key) => object[key] = value);
    let data = JSON.stringify(object);

    const reponse = await fetch("/dashboard/addProfileDashboard", {method: "POST", body: data, headers: {
        "Content-Type": "application/json",
    }});
    console.log(reponse);
}

async function changePrivacySettings(e){
    e.preventDefault();
    let object = {};
    const json = new FormData(e.target);
    json.forEach((value, key) => object[key] = value);
    let data = JSON.stringify(object);

    const reponse = await fetch("/dashboard/shp", {method: "POST", body: data, headers: {
            "Content-Type": "application/json",
        }});
    console.log(reponse);
    }
function activateAwayMode(){
    console.log('/dashboard');
    window.location = '/dashboard/awayMode';
}

async function openWindow(e, room){
    e.preventDefault();

    const response = await fetch("/dashboard/openWindows", {method:'POST', body: room});

}
async function closeWindow(e, room){
    e.preventDefault();
    const response = await fetch("/dashboard/closeWindows", {method:'POST', body: room});
}

async function openDoors(e, room){
    e.preventDefault();

    const response = await fetch("/dashboard/openDoors", {method:'POST', body: room});

    console.log(response);

}
async function closeDoors(e, room){
    e.preventDefault();
    const response = await fetch("/dashboard/closeDoors", {method:'POST', body: room});

    console.log(response);
}

async function onLights(e, room){
    e.preventDefault();

    const response = await fetch("/dashboard/onLights", {method:'POST', body: room});

    console.log(response);

}
async function offLights(e, room){
    e.preventDefault();
    const response = await fetch("/dashboard/offLights", {method:'POST', body: room});

    console.log(response);
}