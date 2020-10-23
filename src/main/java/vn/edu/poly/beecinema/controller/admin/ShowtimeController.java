package vn.edu.poly.beecinema.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/showtime")
public class ShowtimeController {
    @RequestMapping("/show-showtime")
    public String showshowtime(Model model){
        return "admin/showtime/show-showtime";
    }

    @RequestMapping("/add-showtime")
    public String addshowtime(Model model){
        return "admin/showtime/add-showtime";
    }

    @RequestMapping("/update-showtime")
    public String updateshowtime(Model model){
        return "admin/showtime/update-showtime";
    }





}

