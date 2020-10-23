package vn.edu.poly.beecinema.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/seat")
public class SeatController {
    @RequestMapping("/show-seat")
    public String showseat(Model model){
        return "admin/seat/show-seat";
    }

    @RequestMapping("/add-seat")
    public String addseat(Model model){
        return "admin/seat/add-seat";
    }

    @RequestMapping("/update-seat")
    public String updateseat(Model model){
        return "admin/seat/update-seat";
    }





}

