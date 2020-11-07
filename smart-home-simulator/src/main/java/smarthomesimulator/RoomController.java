package smarthomesimulator;


import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import smarthomesimulator.model.Door;
import smarthomesimulator.model.Profile;
import smarthomesimulator.model.Room;
import smarthomesimulator.model.Simulator;

@Controller
public class RoomController {
	
	@RequestMapping(value = {"/{.*}/editRoom"})
    public String showRoom(ModelMap model, HttpServletRequest request) {
    	
    	String currentRoom =  request.getRequestURL().toString() + "?" + request.getQueryString(); 
		Pattern roomPattern = Pattern.compile("(/.*?/)");
		Matcher roomMatch = roomPattern.matcher(currentRoom);
		roomMatch.find();
		currentRoom = roomMatch.group(1).replaceAll("\\/", "");
		model.addAttribute("currentRoom",Simulator.getRoom(currentRoom,Simulator.roomsOfHouse));
		model.addAttribute("currentDoors",Simulator.getRoom(currentRoom,Simulator.roomsOfHouse).getDoors());
		model.addAttribute("currentWindows",Simulator.getRoom(currentRoom,Simulator.roomsOfHouse).getWindows());
		model.addAttribute("currentLights",Simulator.getRoom(currentRoom,Simulator.roomsOfHouse).getLights());
        return "editRoom";
    }
	
	@RequestMapping(value = {"/{.*}/editRoom"}, method= {RequestMethod.POST})
	public void confirmEdit(@Validated @ModelAttribute("currentRoom") Room currentRoom, @RequestParam("lights") int lights) {
		System.out.println("You selected "+lights);
		
	}
}
