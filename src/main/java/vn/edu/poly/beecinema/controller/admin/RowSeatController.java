package vn.edu.poly.beecinema.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.poly.beecinema.entity.Dayghe;
import vn.edu.poly.beecinema.entity.Ghe;
import vn.edu.poly.beecinema.service.DayGheService;
import vn.edu.poly.beecinema.service.TaikhoanService;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/admin/row-seat")
public class RowSeatController {
    @Autowired private DayGheService dayGheService;
    @Autowired private TaikhoanService taikhoanService;
    @GetMapping("/show-row-seat")
    public String showrowseat(Model model){
        List<Dayghe> dayGhe = dayGheService.getAllDayGhe();
        model.addAttribute("dayGhe", dayGhe);
        return "admin/row-seat/show-row-seat";
    }

    @GetMapping("/add-row-seat")
    public String addrowseat(Model model){
        Dayghe dayghe = new Dayghe();
        model.addAttribute("rowSeat",dayghe);
        return "admin/row-seat/add-row-seat";
    }

    @PostMapping("/add-row-seat")
    public String saverowseat(Authentication authentication, Model model, @ModelAttribute(value = "rowSeat") Dayghe DayGhe){
        DayGhe.setNgaytao(LocalDateTime.now());
        dayGheService.saveDayGhe(DayGhe);
        return "redirect:/admin/row-seat/show-row-seat";
    }

    @RequestMapping("/update-row-seat")
    public String updaterowseat(Model model){
        return "admin/row-seat/update-row-seat";
    }

    @GetMapping("/delete-row-seat/{id}")
    public  String deleteRowSeat (@PathVariable(value = "id") String id){
        dayGheService.deleteDayGhe(id);
        return "redirect:/admin/row-seat/show-row-seat";
    }




}

