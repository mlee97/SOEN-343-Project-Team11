package smarthomesimulator;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import smarthomesimulator.model.Simulator;

public class EditFormController {
    @GetMapping({"/editForm"})
    public String editForm(@ModelAttribute("simulator") Simulator simulator, ModelMap model) {
        model.addAttribute("RoomList", Simulator.roomsOfHouse);
        return "editForm";
    }
}
