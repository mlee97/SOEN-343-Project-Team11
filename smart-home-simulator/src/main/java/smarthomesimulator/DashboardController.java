package smarthomesimulator;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.stereotype.Controller;
import smarthomesimulator.model.SHP;
import smarthomesimulator.model.Simulator;
import smarthomesimulator.model.Profile;


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
        simulatorMap.put(0,sim);

        return sim;
    }

    @RequestMapping(value={"/awayMode"}, method = {RequestMethod.GET, RequestMethod.POST})
    public Simulator setAwayMode(@Validated @ModelAttribute("simulator") final Simulator simulator, @Validated @ModelAttribute("profile") final Profile profile, @Validated @ModelAttribute("shp") final SHP shp,
                              ModelMap model){

        Simulator sim = simulatorMap.get(0);
        sim.setAwayMode(!sim.isAwayMode());
        model.addAttribute("awayMode", sim.isAwayMode());
        simulatorMap.put(0,sim);
        return sim;
    }

    @PostMapping(value="/openWindows")
    public Simulator openAllWindows(@Validated @ModelAttribute("profile") final Profile profile, @Validated @ModelAttribute("simulator") final Simulator simulator,
                                 @Validated @ModelAttribute("shp") final SHP shp,ModelMap model, @RequestBody String roomName){
        try{
            Simulator.getRoom(roomName).setOpenWindows(Simulator.getRoom(roomName).getClosedWindows());
        }catch(Exception E) {
            System.out.println("Null Values");
        }
        return simulator;
    }

    @PostMapping(value="/closeWindows")
    public String closeAllWindows(@Validated @ModelAttribute("profile") final Profile profile, @Validated @ModelAttribute("simulator") final Simulator simulator,
                                  @Validated @ModelAttribute("shp") final SHP shp,ModelMap model, @RequestBody String roomName){
        try{
            Simulator.getRoom(roomName).setClosedWindows(Simulator.getRoom(roomName).getOpenWindows());
        }catch(Exception E) {
            System.out.println("Null Values");
        }
        return "dashboard";
    }

    @PostMapping(value="/openDoors")
    public String openAllDoors(@Validated @ModelAttribute("profile") final Profile profile, @Validated @ModelAttribute("simulator") final Simulator simulator,
                                 @Validated @ModelAttribute("shp") final SHP shp,ModelMap model, @RequestBody String roomName){
        try{
            Simulator.getRoom(roomName).setOpenDoors(Simulator.getRoom(roomName).getClosedDoors());
        }catch(Exception E) {
            System.out.println("Null Values");
        }
        return "dashboard";
    }

    @PostMapping(value="/closeDoors")
    public String closeAllDoors(@Validated @ModelAttribute("profile") final Profile profile, @Validated @ModelAttribute("simulator") final Simulator simulator,
                                  @Validated @ModelAttribute("shp") final SHP shp,ModelMap model, @RequestBody String roomName){
        try{
            Simulator.getRoom(roomName).setClosedDoors(Simulator.getRoom(roomName).getOpenDoors());
        }catch(Exception E) {
            System.out.println("Null Values");
        }
        return "dashboard";
    }

    @PostMapping(value="/onLights")
    public String turnOnLights(@Validated @ModelAttribute("profile") final Profile profile, @Validated @ModelAttribute("simulator") final Simulator simulator,
                                 @Validated @ModelAttribute("shp") final SHP shp, @RequestBody String roomName){
        try{
            Simulator.getRoom(roomName).setOpenLights(Simulator.getRoom(roomName).getClosedLights());
        }catch(Exception E) {
            System.out.println("Null Values");
        }
        return "dashboard";
    }

    @PostMapping(value="/offLights")
    public String turnOffLights(@Validated @ModelAttribute("profile") final Profile profile, @Validated @ModelAttribute("simulator") final Simulator simulator,
                                  @Validated @ModelAttribute("shp") final SHP shp, @RequestBody String roomName){
        try{
            Simulator.getRoom(roomName).setClosedLights(Simulator.getRoom(roomName).getOpenLights());
        }catch(Exception E) {
            System.out.println("Null Values");
        }
        return "dashboard";
    }

}
