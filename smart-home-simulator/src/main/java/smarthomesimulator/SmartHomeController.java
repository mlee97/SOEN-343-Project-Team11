package smarthomesimulator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import smarthomesimulator.layout.ParseLayout;
import smarthomesimulator.model.Profile;
import smarthomesimulator.model.Simulator;
import smarthomesimulator.model.SHP;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class SmartHomeController {

    static Map<Integer, Simulator> simulatorMap = new HashMap<>();
    
    @RequestMapping(value = {"/addSimulator", "/", "/init"}, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView showForm() {
        return new ModelAndView("SimulatorHome", "simulator", new Simulator());
    }

    @RequestMapping(value = "/simulator", method = {RequestMethod.GET, RequestMethod.POST})
    public String submit(@Validated @ModelAttribute("simulator") Simulator simulator, @Validated  @ModelAttribute("profile") Profile profile, @Validated  @ModelAttribute("shp") SHP shp,
                         BindingResult result, ModelMap model, @RequestParam("file") String fileName) {
        if (result.hasErrors()) {
            return "error";
        }
        simulator.setFileName(fileName);
        try {
			ParseLayout.parse(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
        model.addAttribute("date", simulator.getDate());
        model.addAttribute("time", simulator.getTime());
        model.addAttribute("tempOut", simulator.getTempOut());
        model.addAttribute("defaultTempIn", simulator.getDefaultTempIn());
        model.addAttribute("fileName", simulator.getFileName());
        model.addAttribute("RoomList", simulator.roomsOfHouse);
        model.addAttribute("name",profile.getName());
        model.addAttribute("role", Profile.Role.values());
        model.addAttribute("location",profile.getLocation());
        model.addAttribute("startTime",shp.getStartTime());
        model.addAttribute("endTime",shp.getEndTime());
        model.addAttribute("alertTime",shp.getAlertTime());
        model.addAttribute("lightsSHP",shp.getLightsSHP());

        simulatorMap.put(0, simulator);
        return "dashboard";
    }


//    @RequestMapping(value = "/dashboard", method = {RequestMethod.GET, RequestMethod.POST})
//    public String submitProfile(@Validated @ModelAttribute("profile") final Profile profile,
//                         final BindingResult result, final ModelMap model) {
//        if (result.hasErrors()) {
//            return "error";
//        }
//
//        Simulator sim = simulatorMap.get(0);
//        sim.addProfile(profile);
//        simulatorMap.put((long) 0, sim);
//
//        return "dashboard";
//    }


}
