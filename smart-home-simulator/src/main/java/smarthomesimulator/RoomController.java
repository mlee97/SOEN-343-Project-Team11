package smarthomesimulator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import smarthomesimulator.model.Simulator;

@Controller
public class RoomController {
	
	
	@RequestMapping(value = {"/{.*}/editRoom"})
    public ModelAndView showRoom(ModelMap model, HttpServletRequest request) {
    	
    	String selectedRoomName =  request.getRequestURL().toString() + "?" + request.getQueryString(); 
		Pattern roomPattern = Pattern.compile("8080/(.*?)/");
		Matcher roomMatch = roomPattern.matcher(selectedRoomName);
		roomMatch.find();
		selectedRoomName = roomMatch.group(1);
		model.addAttribute("currentRoom",Simulator.getRoom(selectedRoomName));
		model.addAttribute("currentLights",Simulator.getRoom(selectedRoomName).getLights());
		model.addAttribute("currentWindows",Simulator.getRoom(selectedRoomName).getWindows());
		model.addAttribute("currentDoors",Simulator.getRoom(selectedRoomName).getDoors());
		model.addAttribute("roomStatus", Simulator.getRoom(selectedRoomName).Status());
		return new ModelAndView("editRoom");
    }
	
	@RequestMapping(value = {"/confirmEdit"}, method= {RequestMethod.GET,RequestMethod.POST})
	public  ModelAndView confirmEdit(ModelMap model, HttpServletRequest request, 
			@RequestParam("selectedLights")String lights, @RequestParam("selectedWindows")String windows, 
			@RequestParam("selectedDoors")String doors, @RequestParam(name="blockedDoors", required=false)String blockedDoors, 
			@RequestParam(name="blockedWindows", required=false)String blockedWindows, @RequestParam(name="closedLights", required=false)String closedLights, 
			@RequestParam(name="closedWindows", required=false)String closedWindows, @RequestParam(name="closedDoors", required=false)String closedDoors) {
			
		
			String selectedRoomName =  request.getRequestURL().toString() + "?" + request.getQueryString(); 
			Pattern roomPattern = Pattern.compile("(/.*?/)");
			Matcher roomMatch = roomPattern.matcher(selectedRoomName);
			roomMatch.find();
			selectedRoomName = roomMatch.group(1).replaceAll("\\/", "");
			
			try {
				
			Simulator.getRoom(selectedRoomName).setOpenLights(Integer.parseInt(lights));
			Simulator.getRoom(selectedRoomName).setOpenDoors(Integer.parseInt(doors));
			Simulator.getRoom(selectedRoomName).setOpenWindows(Integer.parseInt(windows));

			}catch(Exception E) {
				System.out.println("Null Values");
			}
			
			model.addAttribute("currentRoom",Simulator.getRoom(selectedRoomName));
			return new ModelAndView("confirmEdit");
			
		
	}
	
}
