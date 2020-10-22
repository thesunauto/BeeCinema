package vn.edu.poly.beecinema.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/event")
public class EventController {
    @RequestMapping("/show-event")
    public String showEvent(Model model){

        return "admin/event/show-event";
    }

    @RequestMapping("/add-event")
    public String addEvent(Model model){

        return "admin/event/add-event";
    }

    @RequestMapping("/update-event")
    public String updateEvent(Model model){

        return "admin/event/update-event";
    }





}
