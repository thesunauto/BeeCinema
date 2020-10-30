package vn.edu.poly.beecinema.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.poly.beecinema.entity.Dayghe;
import vn.edu.poly.beecinema.service.DayGheService;

import java.util.List;

@Controller
@RequestMapping("/admin/row-seat")
public class RowSeatController {
    @Autowired private DayGheService dayGheService;
    @GetMapping("/show-row-seat")
    public String showrowseat(Model model){
        List<Dayghe> dayGhe = dayGheService.getAllDayGhe();
        model.addAttribute("dayGhe", dayGhe);
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

