package smarthomesimulator;

import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import smarthomesimulator.SmartHomeController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public String submitDashboard(@Validated @ModelAttribute("simulator") final Simulator simulator, @Validated @ModelAttribute("profile") final Profile profile,
                                  @Validated @ModelAttribute("shp") final SHP shp, ModelMap model) {

        Simulator sim = simulatorMap.get(0);
        model.addAttribute("date", simulator.getDate());
        model.addAttribute("time", simulator.getTime());
        model.addAttribute("tempOut", simulator.getTempOut());

        sim.setDate(simulator.getDate());
        sim.setTime(simulator.getTime());
        sim.setTempOut(simulator.getTempOut());
        simulatorMap.put(0,sim);

        return "dashboard";
    }

    @PostMapping(value="/shp")
    public String submitSHP(@Validated @ModelAttribute("simulator") final Simulator simulator, @Validated @ModelAttribute("profile") final Profile profile,
                            @Validated @ModelAttribute("shp") final SHP shp, ModelMap model) {

        Simulator sim = simulatorMap.get(0);

        model.addAttribute("selectRoom",shp.getShpRoom());
        model.addAttribute("startTime",shp.getStartTime());
        model.addAttribute("endTime",shp.getEndTime());
        model.addAttribute("alertTime",shp.getAlertTime());
        model.addAttribute("lightsSHP",shp.getLightsSHP());
        simulatorMap.put(0,sim);

        return "dashboard";
    }

//    @GetMapping(value = {"/addProfileDashboard"})
//    public ModelAndView newProfileDashboard() {
//        return new ModelAndView("dashboard", "profile", new Profile());
//    }

    @PostMapping(value={"/addProfileDashboard"})
    public String submitProfile(@Validated @ModelAttribute("profile") final Profile profile, @Validated @ModelAttribute("simulator") final Simulator simulator,
                                @Validated @ModelAttribute("shp") final SHP shp) {

        Simulator sim = simulatorMap.get(0);
        String name = profile.getName();
        Profile.Role role = profile.getRole();
        String location = profile.getLocation();
        Profile prof = new Profile(name, role, location);
        sim.addProfile(prof);
        simulatorMap.put(0,sim);

        return "dashboard";
    }
}
