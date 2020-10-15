function openModule(evt, modName) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tab");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(modName).style.display = "block";
    evt.currentTarget.className += " active";
}

function displaySimulator(){
	var checkBox = document.getElementById("simSwitch");
	var simulator = document.getElementById("simulator");

	if (checkBox.checked == true){
		simulator.style.visibility = "visible";
	}
	if (checkBox.checked == false){
		simulator.style.visibility = "hidden";
	}
}

function getCurrentDate(){
    var dt = new Date();
        month = '' + (dt.getMonth() + 1),
        day = '' + dt.getDate(),
        year = dt.getFullYear();

        if (month.length < 2)
            month = '0' + month;
        if (day.length < 2)
            day = '0' + day;

    document.getElementById("displayDate").innerHTML = [year, month, day].join('-');
}

function getCurrentTime(){
    var dt = new Date();
    document.getElementById("displayTime").innerHTML = (("0"+dt.getHours()).slice(-2)) +":"+ (("0"+dt.getMinutes()).slice(-2));
}

function redirectEditForm(){
    window.location.href = "/editForm";
}
function redirectDashboard(){
    window.location.href = "/dashboard";
}

function getNewTime(){
    var dt = document.getElementById("inTime").value;
    document.getElementById("displayTime").innerHTML = dt;
}

function getNewDate(){
    var dt = document.getElementById("inDate").value;
    document.getElementById("displayDate").innerHTML = dt;
}