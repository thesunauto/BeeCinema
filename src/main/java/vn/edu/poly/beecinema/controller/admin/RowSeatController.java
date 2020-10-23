package vn.edu.poly.beecinema.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/row-seat")
public class RowSeatController {
    @RequestMapping("/show-row-seat")
    public String showrowseat(Model model){
        return "admin/row-seat/show-row-seat";
    }

    @RequestMapping("/add-row-seat")
    public String addrowseat(Model model){
        return "admin/row-seat/add-row-seat";
    }

    @RequestMapping("/update-row-seat")
    public String updaterowseat(Model model){
        return "admin/row-seat/update-row-seat";
    }





}

