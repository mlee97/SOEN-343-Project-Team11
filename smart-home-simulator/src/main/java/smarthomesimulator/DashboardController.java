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

@Controller
@RequestMapping(value="/dashboard")
public class DashboardController extends SmartHomeController{
    @GetMapping()
    public ModelAndView dashboard(@Validated @ModelAttribute("simulator") final Simulator simulator) {
        return new ModelAndView("dashboard", "simulator", simulator);
    }

    @PostMapping(value="/context")
    public ResponseEntity<Simulator> submitDashboard(@Validated @RequestBody Simulator simulator){

        Simulator sim = simulatorMap.get(0);

        sim.setDate(simulator.getDate());
        sim.setTime(simulator.getTime());
        sim.setTempOut(simulator.getTempOut());
        simulatorMap.put(0,sim);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value="/shp")
    public ResponseEntity<Simulator> submitSHP(@Validated @RequestBody final SHP shp, ModelMap model) {

        Simulator sim = simulatorMap.get(0);
        model.addAttribute("selectRoom",shp.getShpRoom());
        model.addAttribute("startTime",shp.getStartTime());
        model.addAttribute("endTime",shp.getEndTime());
        model.addAttribute("alertTime",shp.getAlertTime());
        model.addAttribute("lightsSHP",shp.getLightsSHP());
        simulatorMap.put(0,sim);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value={"/addProfileDashboard"})
    public ResponseEntity<Simulator> submitProfile(@Validated @RequestBody Profile profile) {

        Simulator sim = simulatorMap.get(0);
        String name = profile.getName();
        Profile.Role role = profile.getRole();
        String location = profile.getLocation();
        Profile prof = new Profile(name, role, location);
        sim.addProfile(prof);
        simulatorMap.put(0,sim);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
