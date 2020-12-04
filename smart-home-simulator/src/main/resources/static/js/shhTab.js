
var shhTab;
var shhRoom;
var shhZone;


window.onload = function() {
    loadSHHTab();
}

async function loadSHHTab(){
    const response1 = await fetch("/dashboard/shh", {method: "GET"});
    let shhTabResponseData = await response1.json();

    const response2 = await fetch("/dashboard/shhRooms", {method: "GET"});
    let shhRoomResponseData = await response2.json();

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
            selectedZone: {}
        },
        methods:{
            onRoomSelected(event) {
                let selectedIndex = event.target.value
                this.selectedZone =  this.zones[selectedIndex]
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