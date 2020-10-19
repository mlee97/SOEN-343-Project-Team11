function openModule(evt, modName) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabContent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tab btn btn-outline-dark");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(modName).style.display = "block";
    evt.currentTarget.className += " active";
}

function displaySimulator() {
    var checkBox = document.getElementById("simSwitch");
    var simulator = document.getElementById("simulator");

    if (checkBox.checked == true) {
        simulator.style.visibility = "visible";
    }
    if (checkBox.checked == false) {
        simulator.style.visibility = "hidden";
    }
}

function redirectEditForm() {
    window.location.href = "/editForm";
}

function redirectDashboard() {
    window.location.href = "/dashboard";
}
