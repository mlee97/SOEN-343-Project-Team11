package smarthomesimulator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import smarthomesimulator.layout.RenderLayout;
import smarthomesimulator.model.Room;
import smarthomesimulator.model.Simulator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Controller
public class SmartHomeController {

    Map<Long, Simulator> simulatorMap = new HashMap<>();

    @RequestMapping(value = {"/", "/init"}, method = RequestMethod.GET)
    public String createForm() {
        return "Form";
    }
 
    @RequestMapping(method = RequestMethod.POST)
    public void render(@RequestParam("file") String fileName) throws Exception{	
    	
    	
    	if (fileName == null) {
            System.out.println("No file found");
        } else {
           
            try {
                File file = new File(fileName); // creates a new file instance
                FileReader fr = new FileReader(file); // reads the file
                BufferedReader br = new BufferedReader(fr); // creates a buffering character input stream
                String line;

                while ((line = br.readLine()) != null) {
                    Pattern patternRoomName = Pattern.compile("RoomName:(.*?),");
                    Matcher matcherRoomName = patternRoomName.matcher(line);
                    Pattern patternWindows = Pattern.compile("Windows:(.*?),");
                    Matcher matcherWindows = patternWindows.matcher(line);
                    Pattern patternDoors = Pattern.compile("Doors:(.*?),");
                    Matcher matcherDoors = patternDoors.matcher(line);
                    Pattern patternLights = Pattern.compile("Lights:(.*?);");
                    Matcher matcherLights = patternLights.matcher(line);
                    if (matcherRoomName.find()) {
                        Room room = new Room(matcherRoomName.group(1),
                                0,
                                0,
                                0
                        );
                        if (matcherWindows.find()) {
                            room.setWindows((Integer.parseInt(matcherWindows.group(1))));

                        }
                        if (matcherDoors.find()) {
                            room.setDoors((Integer.parseInt(matcherDoors.group(1))));

                        }
                        if (matcherLights.find()) {
                            room.setLights((Integer.parseInt(matcherLights.group(1))));

                        }
                        Simulator.roomsOfHouse.add(room);

                    }

                }
                
               
            	RenderLayout.main(null); 
                br.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
   
    	
    }
    
    @RequestMapping(value = "/addSimulator", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView showForm() {
        return new ModelAndView("SimulatorHome", "simulator", new Simulator());
    }

    @RequestMapping(value = "/simulator", method = {RequestMethod.GET, RequestMethod.POST})
    public String submit(@Validated @ModelAttribute("simulator") final Simulator simulator,
                         final BindingResult result, final ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        model.addAttribute("date", simulator.getDate());
        model.addAttribute("time", simulator.getTime());
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

        return new ModelAndView("dashboard", "simulator", simulator);
    }

    @GetMapping({"/editForm"})
    public void editForm() {
    }
}
