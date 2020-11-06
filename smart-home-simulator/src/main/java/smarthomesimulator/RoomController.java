package smarthomesimulator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import smarthomesimulator.model.Simulator;

@Controller
@RequestMapping(value = {"/{.*}/editRoom"})
public class RoomController {
	
    @GetMapping
    public String showRoom(@ModelAttribute("simulator") Simulator simulator, ModelMap model, HttpServletRequest request) {
    	
    	String currentRoom =  request.getRequestURL().toString() + "?" + request.getQueryString(); 
		Pattern roomPattern = Pattern.compile("(/.*?/)");
		Matcher roomMatch = roomPattern.matcher(currentRoom);
		roomMatch.find();
		currentRoom = roomMatch.group(1).replaceAll("\\/", "");
		model.addAttribute("currentRoom",Simulator.getRoom(currentRoom,Simulator.roomsOfHouse));
		model.addAttribute("currentDoors",Simulator.getRoom(currentRoom,Simulator.roomsOfHouse).doors);
		
        return "editRoom";
    }
    
}
