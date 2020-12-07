package smarthomesimulator;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import smarthomesimulator.model.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;


@RestController
@RequestMapping(value="/dashboard")
public class DashboardController extends SmartHomeController{
    @GetMapping()
    public Simulator dashboard(@Validated @ModelAttribute("simulator") final Simulator simulator, ModelMap model) {
        Simulator sim = simulatorMap.get(0);
        model.addAttribute("RoomList", Simulator.roomsOfHouse);
        return sim;
    }

    @PostMapping(value="/context")
    public Simulator submitDashboard(@Validated @RequestBody Simulator simulator){

        Simulator sim = simulatorMap.get(0);

        sim.setDate(simulator.getDate());
        sim.setTime(simulator.getTime());
        sim.setTempOut(simulator.getTempOut());
        sim.getcOut().setMessage("Modified Context Parameters\n");
        simulatorMap.put(0,sim);

        return sim;
    }

    @PostMapping(value="/shp")
    public Simulator submitSHP(@Validated @RequestBody final SHP shp, ModelMap model) {

        Simulator sim = simulatorMap.get(0);
        model.addAttribute("selectRoom",shp.getShpRoom());
        model.addAttribute("startTime",shp.getStartTime());
        model.addAttribute("endTime",shp.getEndTime());
        model.addAttribute("alertTime",shp.getAlertTime());
        model.addAttribute("lightsSHP",shp.getLightsSHP());
        sim.getcOut().setMessage("Modified SHP settings\n");
        simulatorMap.put(0,sim);
        return sim;
    }

    @PostMapping(value={"/addProfileDashboard"})
    public Simulator submitProfile(@Validated @RequestBody Profile profile) {

        Simulator sim = simulatorMap.get(0);
        String name = profile.getName();
        Profile.Role role = profile.getRole();
        String location = profile.getLocation();
        Profile prof = new Profile(name, role, location);
        sim.addProfile(prof);
        sim.getcOut().setMessage("The user " + name + " has been added\n");
        simulatorMap.put(0,sim);

        return sim;
    }

    @PostMapping(value={"/removeProfileDashboard"})
    public Simulator deleteProfile(@Validated @RequestBody String profileName) {

        Simulator sim = simulatorMap.get(0);
        sim.removeProfile(sim.getProfile(profileName));
        sim.getcOut().setMessage("The user " + profileName + " has been deleted\n");
        simulatorMap.put(0,sim);

        return sim;
    }

    @PostMapping(value={"/awayMode"})
    public boolean setAwayMode(){

        Simulator sim = simulatorMap.get(0);
        if(!sim.isAwayMode()) {
            for (Room room : sim.roomsOfHouse) {
                closeAllDoors(room.getRoomName());
                closeAllWindows(room.getRoomName());
            }
        }
        sim.setAwayMode(!sim.isAwayMode());
        if(sim.isAwayMode()) {
            for(Room room : sim.roomsOfHouse){
                closeAllDoors(room.getRoomName());
                closeAllWindows(room.getRoomName());
            }
            sim.getcOut().setMessage("Away mode is active\n");
        }
        else{
            sim.getcOut().setMessage("Away mode has been disabled\n");
        }
        simulatorMap.put(0,sim);
        return sim.isAwayMode();
    }

    @PostMapping(value="/openWindows")
    public boolean openAllWindows(@RequestBody String roomName){
        Simulator sim = simulatorMap.get(0);
        try{
            if(!sim.isAwayMode()) {
                sim.getRoom(roomName).setOpenWindows(sim.getRoom(roomName).getClosedWindows());
                sim.getcOut().setMessage("All windows in "+roomName+" have been opened\n");
            }
            else
                sim.getcOut().setMessage("Away mode is active: windows could not be opened\n");
            simulatorMap.put(0,sim);
        }catch(Exception E) {
            System.out.println("Null Values");
        }
        return sim.isAwayMode();
    }

    @PostMapping(value="/closeWindows")
    public Room closeAllWindows(@RequestBody String roomName){
        Simulator sim = simulatorMap.get(0);
        try{
            sim.getRoom(roomName).setClosedWindows(sim.getRoom(roomName).getOpenWindows());
            sim.getcOut().setMessage("All windows "+roomName+" have been closed\n");
            simulatorMap.put(0,sim);
        }catch(Exception E) {
            System.out.println("Null Values");
        }
        return sim.getRoom(roomName);
    }
    
    @PostMapping(value = "/blockWindows")
    public Room blockAllWindows(@Validated @ModelAttribute("profile") final Profile profile,
            @Validated @ModelAttribute("simulator") final Simulator simulator,
            @Validated @ModelAttribute("shp") final SHP shp, ModelMap model, @RequestBody String roomName) {

        Simulator sim = simulatorMap.get(0);
        try {
            Simulator.getRoom(roomName).setBlockedWindows(Simulator.getRoom(roomName).getWindows().size());
            sim.getcOut().setMessage("All windows have been blocked\n");
            simulatorMap.put(0, sim);
        } catch (Exception E) {
            System.out.println("Null Values");
        }
        return sim.getRoom(roomName);
    }

    @PostMapping(value = "/unblockWindow")
    public Room unblockAllWindows(@Validated @ModelAttribute("profile") final Profile profile,
            @Validated @ModelAttribute("simulator") final Simulator simulator,
            @Validated @ModelAttribute("shp") final SHP shp, ModelMap model, @RequestBody String roomName) {
        
        Simulator sim = simulatorMap.get(0);
        try {
            Simulator.getRoom(roomName).setUnblockedWindows(Simulator.getRoom(roomName).getWindows().size());
            sim.getcOut().setMessage("All windows have been unblocked\n");
            simulatorMap.put(0, sim);
        } catch (Exception E) {
            System.out.println("Null Values");
        }
        return sim.getRoom(roomName);
    }

    @PostMapping(value="/openDoors")
    public boolean openAllDoors(@RequestBody String roomName){
        Simulator sim = simulatorMap.get(0);

        try{
            if(!sim.isAwayMode()){
                sim.getRoom(roomName).setOpenDoors(sim.getRoom(roomName).getClosedDoors());
                sim.getcOut().setMessage("All doors in "+roomName+" have been opened\n");
            }
            else
                sim.getcOut().setMessage("Away mode is active: doors could not be opened\n");
            simulatorMap.put(0,sim);
        }catch(Exception E) {
            System.out.println("Null Values");
        }
        return sim.isAwayMode();
    }

    @PostMapping(value="/closeDoors")
    public Room closeAllDoors(@RequestBody String roomName){
        Simulator sim = simulatorMap.get(0);
        try{
            sim.getRoom(roomName).setClosedDoors(sim.getRoom(roomName).getOpenDoors());
            simulatorMap.put(0,sim);
            sim.getcOut().setMessage("All windows in "+roomName+" have been closed\n");
        }catch(Exception E) {
            System.out.println("Null Values");
        }
        return sim.getRoom(roomName);
    }

    @PostMapping(value = "/blockDoors")
    public Room blockAllDoors(@Validated @ModelAttribute("profile") final Profile profile,
            @Validated @ModelAttribute("simulator") final Simulator simulator,
            @Validated @ModelAttribute("shp") final SHP shp, ModelMap model, @RequestBody String roomName) {

        Simulator sim = simulatorMap.get(0);
        try {
            Simulator.getRoom(roomName).setBlockedDoors(Simulator.getRoom(roomName).getDoors().size());
            sim.getcOut().setMessage("All doors have been blocked\n");
            simulatorMap.put(0, sim);
        } catch (Exception E) {
            System.out.println("Null Values");
        }
        return sim.getRoom(roomName);
    }

    @PostMapping(value = "/unblockDoors")
    public Room unblockAllDoors(@Validated @ModelAttribute("profile") final Profile profile,
            @Validated @ModelAttribute("simulator") final Simulator simulator,
            @Validated @ModelAttribute("shp") final SHP shp, ModelMap model, @RequestBody String roomName) {

        Simulator sim = simulatorMap.get(0);
        try {
            Simulator.getRoom(roomName).setUnblockedDoors(Simulator.getRoom(roomName).getDoors().size());
            sim.getcOut().setMessage("All doors have been unblocked\n");
            simulatorMap.put(0, sim);
        } catch (Exception E) {
            System.out.println("Null Values");
        }
        return sim.getRoom(roomName);
    }

    @PostMapping(value="/onLights")
    public Room turnOnLights(@RequestBody String roomName){
        Simulator sim = simulatorMap.get(0);
        try{
            sim.getRoom(roomName).setOpenLights(sim.getRoom(roomName).getClosedLights());
            simulatorMap.put(0,sim);
            sim.getcOut().setMessage("All lights in "+roomName+ " have been turned on\n");
        }catch(Exception E) {
            System.out.println("Null Values");
        }
        return sim.getRoom(roomName);
    }

    @PostMapping(value="/offLights")
    public Room turnOffLights(@RequestBody String roomName){
        Simulator sim = simulatorMap.get(0);
        try{
            sim.getRoom(roomName).setClosedLights(sim.getRoom(roomName).getOpenLights());
            sim.getcOut().setMessage("All lights in "+roomName+" have been turned off\n");
            simulatorMap.put(0,sim);
        }catch(Exception E) {
            System.out.println("Null Values");
        }
        return sim.getRoom(roomName);
    }

    @PostMapping(value="/shhChangeZone")
    public String changeSHHZone(@Validated @RequestBody Zone zone) throws IOException{
        Simulator sim = simulatorMap.get(0);
        // Temp while I figure out a better way
        for(Zone z :sim.getZonesOfHouse()){
            if(z.getName() == zone.getName()){
                z.setSetting(zone.isSetting());
                z.setTemperature(zone.getTemperature());
                simulatorMap.put(0,sim);
                break;
            }
        }

        sim.getcOut().setMessage("The zone " + zone.name + " has been modified." + "\n");

        return getJsonString(sim);
    }

    private String getJsonString(Simulator sim) throws IOException {
        List<Zone> listZones = sim.getZonesOfHouse();
        final ByteArrayOutputStream o = new ByteArrayOutputStream();
        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(o, listZones);
        final byte[] data = o.toByteArray();
        return new String(data);
    }

    @PostMapping(value="/shhAddZone")
    public String addSHHZone(@Validated @RequestBody Zone zone) throws IOException {
        Simulator sim = simulatorMap.get(0);
        List<Zone> listZones = sim.getZonesOfHouse();
        if(!listZones.stream().anyMatch(o -> o.getName().equals(zone.name))){
            //Console log but no console yet
            sim.zonesOfHouse.add(zone);
            simulatorMap.put(0,sim);
        }else{
            System.out.println("Zone name already exists");
        }
        listZones = sim.getZonesOfHouse();
        final ByteArrayOutputStream o = new ByteArrayOutputStream();
        final ObjectMapper mapper = new ObjectMapper();

        sim.getcOut().setMessage(zone.name + " has been added as a zone." + "\n");

        mapper.writeValue(o, listZones);
        final byte[] data = o.toByteArray();
        return new String(data);
    }

    @GetMapping(value="/shh")
    public String getSHHZones() throws IOException {
        Simulator sim = simulatorMap.get(0);
        return getJsonString(sim);
    }

    @PostMapping(value="/shhOverrideRoomTemperature")
    public Room overrideRoomTemperature(@Validated @RequestBody Map<String, String> json){
        Simulator sim = simulatorMap.get(0);
        if(sim.getRoom(json.get("name")).getTemperature() != Double.parseDouble(json.get("temp"))){
            sim.getRoom(json.get("name")).setTemperature(Double.parseDouble(json.get("temp")));
            if(sim.getRoom(json.get("name")).getZone() != null){
                sim.getRoom(json.get("name")).setOverridden(true);
            }
        }
        simulatorMap.put(0, sim);
        return sim.getRoom(json.get("name"));
    }

    @PostMapping(value="/shhRoomChangeZone")
    public Room changeRoomZone(@Validated @RequestBody Map<String, String> json){
        Simulator sim = simulatorMap.get(0);
        if(Integer.parseInt(json.get("zoneID")) == -1){
            sim.getRoom(json.get("name")).setZone(null);
            sim.getRoom(json.get("name")).setOverridden(false);
        }
        else{
            sim.getRoom(json.get("name")).setZone(sim.zonesOfHouse.get(Integer.parseInt(json.get("zoneID"))));
            sim.getRoom(json.get("name")).setTemperature(sim.zonesOfHouse.get(Integer.parseInt(json.get("zoneID"))).getTemperature());
            sim.getRoom(json.get("name")).setOverridden(false);
        }
        simulatorMap.put(0,sim);
        return sim.getRoom(json.get("name"));
    }

    @PostMapping(value="/resetTemperature")
    public Room resetTemperature(@Validated @RequestBody Map<String, String> json){
        Simulator sim = simulatorMap.get(0);
        sim.getRoom(json.get("name")).setTemperature(sim.getRoom(json.get("name")).getZone().getTemperature());
        sim.getRoom(json.get("name")).setOverridden(false);

        simulatorMap.put(0,sim);
        return sim.getRoom(json.get("name"));
    }

    @GetMapping(value="/shhRooms")
    public List<Room> getSHHRooms(){
        Simulator sim = simulatorMap.get(0);
        return sim.roomsOfHouse;
    }
    @PostMapping(value="/shhChangeSeasonalTemperature")
    public void changeSeasonalTemperature(@Validated @RequestBody Map<String, String> json){
        Simulator sim = simulatorMap.get(0);
        sim.setDefaultSummerTemp(Double.parseDouble(json.get("summerTemp")));
        sim.setDefaultWinterTemp(Double.parseDouble(json.get("winterTemp")));

        sim.getcOut().setMessage("Summer Temperature set to: " + Double.parseDouble(json.get("summerTemp")) + "\n");
        sim.getcOut().setMessage("Winter Temperature set to: " + Double.parseDouble(json.get("winterTemp"))+ "\n");

        simulatorMap.put(0,sim);
        return;
    }
    @PostMapping(value="/consoleOutput")
    public String displayConsoleOutput(){
        String messages = "";
        try {
            Simulator sim = simulatorMap.get(0);

            for (int i = 0; i < sim.getcOut().getSize(); i++){
                messages += sim.getcOut().getMessage(i);
            }

        }catch(Exception E) {
            System.out.println("Null Values");
        }
        return messages;
    }
    @PostMapping(value="/HouseParameters")
    public ArrayList<Room> houseStatus(@Validated @ModelAttribute("profile") final Profile profile, @Validated @ModelAttribute("simulator") final Simulator simulator){

        Simulator sim = simulatorMap.get(0);
        return sim.roomsOfHouse;
    }

    @PostMapping(value="/getProfiles")
    public ArrayList<Profile> getProfiles(@Validated @ModelAttribute("profile") final Profile profile, @Validated @ModelAttribute("simulator") final Simulator simulator){

        Simulator sim = simulatorMap.get(0);
        return sim.profilesOfHouse;
    }


    @PostMapping(value="/getTime")
    public String getTime(){
        String time = "";
        try {
            Simulator sim = simulatorMap.get(0);

            time = sim.getTime();

        }catch(Exception E) {
            System.out.println("Null Values");
        }
        return time;
    }


    @PostMapping(value="/getTemps")
    public Simulator getTemps(){
        Simulator sim = simulatorMap.get(0);
        return sim;
    }
}
