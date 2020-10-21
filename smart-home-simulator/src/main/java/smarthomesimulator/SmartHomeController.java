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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class SmartHomeController {

    Map<Long, Simulator> simulatorMap = new HashMap<>();
    
    @RequestMapping(value = {"/addSimulator", "/", "/init"}, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView showForm() {
        return new ModelAndView("SimulatorHome", "simulator", new Simulator());
    }

    @RequestMapping(value = "/simulator", method = {RequestMethod.GET, RequestMethod.POST})
    public String submit(@Validated @ModelAttribute("simulator") final Simulator simulator,
                         final BindingResult result, final ModelMap model,@RequestParam("file") String fileName) {
        if (result.hasErrors()) {
            return "error";
        }
        simulator.setFileName(fileName);
        try {
			ParseLayout.parse(fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        model.addAttribute("date", simulator.getDate());
        model.addAttribute("time", simulator.getTime());
        model.addAttribute("tempOut", simulator.getTempOut());
        model.addAttribute("defaultTempIn", simulator.getDefaultTempIn());
        model.addAttribute("fileName", simulator.getFileName());
        model.addAttribute("RoomList", Simulator.roomsOfHouse);
        simulatorMap.put((long) 0, simulator);
        return "dashboard";
    }

    @GetMapping({"/dashboard"})
    public ModelAndView dashboard() {
        return new ModelAndView("dashboard", "simulator", simulatorMap.get(0));
    }

    @PostMapping({"/dashboard"})
    public ModelAndView submitDashboard(@Validated @ModelAttribute("simulator") Simulator simulator, ModelMap model) {

    	 model.addAttribute("date", simulator.getDate());
    	 model.addAttribute("time", simulator.getTime());
    	 model.addAttribute("fileName", simulator.getFileName());
    	 model.addAttribute("tempOut", simulator.getTempOut());

       return new ModelAndView("dashboard", "simulator", simulator);
    }

    @RequestMapping(value = {"/addProfileDashboard"}, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView submitNewProfileDashboard() {
        return new ModelAndView("dashboard", "profile", new Profile());
    }

    @RequestMapping(value = "/dashboard", method = {RequestMethod.GET, RequestMethod.POST})
    public String submitProfile(@Validated @ModelAttribute("profile") final Profile profile,
                         final BindingResult result, final ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }

        Simulator sim = simulatorMap.get(0);
        sim.addProfile(profile);
        simulatorMap.put((long) 0, sim);

        return "dashboard";
    }

    @GetMapping({"/editForm"})
    public void editForm(@ModelAttribute("simulator") Simulator simulator, ModelMap model) {
    	model.addAttribute("RoomList", Simulator.roomsOfHouse);
    }
}
