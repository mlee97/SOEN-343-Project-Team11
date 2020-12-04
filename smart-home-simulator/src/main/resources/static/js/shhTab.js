
var shhTab;
var shhZone;
window.onload = function() {
    getSHHZones()
    shhZone = new Vue({
        el: '#shhZone',
        data:{
            name: '',
            setting: false,
            temperature: 0
        }
    });
}

async function getSHHZones(){
    const response = await fetch("/dashboard/shh", {method: "GET"});
    console.log(response);
    let responseData = await response.json();
    console.log(responseData);
    shhTab = new Vue({
        el: "#shhVariables",
        data: {selected: "null", zones: responseData, selectedZone:{}},
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
}