package smarthomesimulator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import smarthomesimulator.layout.ParseLayout;
import smarthomesimulator.model.Simulator;

import java.util.HashMap;
import java.util.Map;



@Controller
public class SmartHomeController {

    Map<Long, Simulator> simulatorMap = new HashMap<>();

    @RequestMapping(value = {"/", "/init"}, method = RequestMethod.GET)
    public String createForm() {
        return "Form";
    }
    
    @RequestMapping(value = "/addSimulator", method = {RequestMethod.GET, RequestMethod.POST})
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
        model.addAttribute("date", simulator.getDate());
        model.addAttribute("time", simulator.getTime());
        model.addAttribute("tempOut", simulator.getTempOut());
        model.addAttribute("defaultTempIn", simulator.getDefaultTempIn());
        model.addAttribute("fileName", simulator.getFileName());
        simulatorMap.put((long) 0, simulator);
        return "SimulatorView";
    }

    @GetMapping({"/dashboard"})
    public ModelAndView dashboard() {
        return new ModelAndView("dashboard", "simulator", new Simulator());
    }

    @PostMapping({"/dashboard"})
    public ModelAndView submitDashboard(@Validated @ModelAttribute("simulator") Simulator simulator, ModelMap model) {
    	 model.addAttribute("date", simulator.getDate());
    	 model.addAttribute("time", simulator.getTime());
    	 model.addAttribute("fileName", simulator.getFileName());

        return new ModelAndView("dashboard", "simulator", simulator);
    }

    @GetMapping({"/editForm"})
    public void editForm() {
    }
}
