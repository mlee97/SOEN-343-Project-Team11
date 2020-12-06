package smarthomesimulator;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import smarthomesimulator.model.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    @RequestMapping(value={"/awayMode"}, method = {RequestMethod.GET, RequestMethod.POST})
    public Simulator setAwayMode(@Validated @ModelAttribute("simulator") final Simulator simulator, @Validated @ModelAttribute("profile") final Profile profile, @Validated @ModelAttribute("shp") final SHP shp,
                              ModelMap model){

        Simulator sim = simulatorMap.get(0);
        sim.setAwayMode(!sim.isAwayMode());
        model.addAttribute("awayMode", sim.isAwayMode());

        if(sim.isAwayMode()) {
            sim.getcOut().setMessage("Away mode is active\n");
        }
        else{
            sim.getcOut().setMessage("Away mode is not active\n");
        }
        simulatorMap.put(0,sim);
        return sim;
    }

    @PostMapping(value="/openWindows")
    public Room openAllWindows(@Validated @ModelAttribute("profile") final Profile profile, @Validated @ModelAttribute("simulator") final Simulator simulator,
                               @Validated @ModelAttribute("shp") final SHP shp, ModelMap model, @RequestBody String roomName){
        try{
            simulator.getRoom(roomName).setOpenWindows(Simulator.getRoom(roomName).getClosedWindows());
        }catch(Exception E) {
            System.out.println("Null Values");
        }
        return simulator.getRoom(roomName);
    }

    @PostMapping(value="/closeWindows")
    public Room closeAllWindows(@Validated @ModelAttribute("profile") final Profile profile, @Validated @ModelAttribute("simulator") final Simulator simulator,
                                  @Validated @ModelAttribute("shp") final SHP shp,ModelMap model, @RequestBody String roomName){
        try{
            Simulator.getRoom(roomName).setClosedWindows(Simulator.getRoom(roomName).getOpenWindows());
        }catch(Exception E) {
            System.out.println("Null Values");
        }
        return Simulator.getRoom(roomName);
    }

    @PostMapping(value="/openDoors")
    public Room openAllDoors(@Validated @ModelAttribute("profile") final Profile profile, @Validated @ModelAttribute("simulator") final Simulator simulator,
                                 @Validated @ModelAttribute("shp") final SHP shp,ModelMap model, @RequestBody String roomName){
        try{
            Simulator.getRoom(roomName).setOpenDoors(Simulator.getRoom(roomName).getClosedDoors());
        }catch(Exception E) {
            System.out.println("Null Values");
        }
        return simulator.getRoom(roomName);
    }

    @PostMapping(value="/closeDoors")
    public Room closeAllDoors(@Validated @ModelAttribute("profile") final Profile profile, @Validated @ModelAttribute("simulator") final Simulator simulator,
                                  @Validated @ModelAttribute("shp") final SHP shp,ModelMap model, @RequestBody String roomName){
        try{
            Simulator.getRoom(roomName).setClosedDoors(Simulator.getRoom(roomName).getOpenDoors());
        }catch(Exception E) {
            System.out.println("Null Values");
        }
        return simulator.getRoom(roomName);
    }

    @PostMapping(value="/onLights")
    public Room turnOnLights(@Validated @ModelAttribute("profile") final Profile profile, @Validated @ModelAttribute("simulator") final Simulator simulator,
                                 @Validated @ModelAttribute("shp") final SHP shp, @RequestBody String roomName){
        try{
            Simulator.getRoom(roomName).setOpenLights(Simulator.getRoom(roomName).getClosedLights());
        }catch(Exception E) {
            System.out.println("Null Values");
        }
        return simulator.getRoom(roomName);
    }

    @PostMapping(value="/offLights")
    public Room turnOffLights(@Validated @ModelAttribute("profile") final Profile profile, @Validated @ModelAttribute("simulator") final Simulator simulator,
                                  @Validated @ModelAttribute("shp") final SHP shp, @RequestBody String roomName){
        try{
            Simulator.getRoom(roomName).setClosedLights(Simulator.getRoom(roomName).getOpenLights());
        }catch(Exception E) {
            System.out.println("Null Values");
        }
        return simulator.getRoom(roomName);
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

        mapper.writeValue(o, listZones);
        final byte[] data = o.toByteArray();
        return new String(data);
    }

    @GetMapping(value="/shh")
    public String getSHHZones() throws IOException {
        Simulator sim = simulatorMap.get(0);
        List<Zone> listZones = sim.getZonesOfHouse();
        final ByteArrayOutputStream o = new ByteArrayOutputStream();
        final ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(o, listZones);
        final byte[] data = o.toByteArray();
        return new String(data);
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
}
