package vn.edu.poly.beecinema.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/screenings")
public class ScreeningsController {
    @RequestMapping("/show-screenings")
    public String showscreenings(Model model){
        return "admin/screenings/show-screenings";
    }

    @RequestMapping("/add-screenings")
    public String addscreenings(Model model){
        return "admin/screenings/add-screenings";
    }

    @RequestMapping("/update-screenings")
    public String updatescreenings(Model model){
        return "admin/screenings/update-screenings";
    }





}

