package vn.edu.poly.beecinema.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/seat-type")
public class SeatTypeController {
    @RequestMapping("/show-seat-type")
    public String showtypeseat(Model model){
        return "admin/seat-type/show-seat-type";
    }

    @RequestMapping("/add-seat-type")
    public String addtypeseat(Model model){
        return "admin/seat-type/add-seat-type";
    }

    @RequestMapping("/update-seat-type")
    public String updatetypeseat(Model model){
        return "admin/seat-type/update-seat-type";
    }





}

