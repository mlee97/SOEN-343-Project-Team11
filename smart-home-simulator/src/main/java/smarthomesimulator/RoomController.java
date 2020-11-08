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
import org.springframework.web.servlet.ModelAndView;

import smarthomesimulator.model.Door;
import smarthomesimulator.model.Profile;
import smarthomesimulator.model.Room;
import smarthomesimulator.model.Simulator;

@Controller
public class RoomController {
	
	@RequestMapping(value = {"/{.*}/editRoom"})
    public ModelAndView showRoom(ModelMap model, HttpServletRequest request) {
    	
    	String selectedRoomName =  request.getRequestURL().toString() + "?" + request.getQueryString(); 
		Pattern roomPattern = Pattern.compile("(/.*?/)");
		Matcher roomMatch = roomPattern.matcher(selectedRoomName);
		roomMatch.find();
		selectedRoomName = roomMatch.group(1).replaceAll("\\/", "");
		model.addAttribute("currentRoom",Simulator.getRoom(selectedRoomName,Simulator.roomsOfHouse));
		model.addAttribute("currentLights",Simulator.getRoom(selectedRoomName,Simulator.roomsOfHouse).getLights());
		model.addAttribute("currentWindows",Simulator.getRoom(selectedRoomName,Simulator.roomsOfHouse).getWindows());
		model.addAttribute("currentDoors",Simulator.getRoom(selectedRoomName,Simulator.roomsOfHouse).getDoors());
		return new ModelAndView("editRoom");
    }
	
	@RequestMapping(value = {"/confirmEdit"}, method= {RequestMethod.GET,RequestMethod.POST})
	public void confirmEdit(ModelMap model, @RequestParam("selectedLights")String lights, 
			@RequestParam("selectedWindows")String windows, @RequestParam("selectedDoors")String doors, @Validated @ModelAttribute("simulator") Simulator simulator, @Validated  @ModelAttribute("profile") Profile profile ) {
		
		System.out.println("You selected to open "+lights+" lights, "+windows+" windows, and "+doors+" doors ");
		
		
	}
}
