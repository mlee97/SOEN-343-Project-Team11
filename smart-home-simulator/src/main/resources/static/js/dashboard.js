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