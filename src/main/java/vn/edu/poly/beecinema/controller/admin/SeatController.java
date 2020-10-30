package vn.edu.poly.beecinema.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.poly.beecinema.entity.Ghe;
import vn.edu.poly.beecinema.service.GheService;

import java.util.List;

@Controller
@RequestMapping("/admin/seat")
public class SeatController {
    @Autowired private GheService gheService;

    @GetMapping("/show-seat")
    public String showseat(Model model){
        List<Ghe> ghe = gheService.getAllGhe();
        model.addAttribute("ghe", ghe);
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

