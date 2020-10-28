package vn.edu.poly.beecinema.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.poly.beecinema.entity.Loaighe;
import vn.edu.poly.beecinema.service.GheService;
import vn.edu.poly.beecinema.service.LoaiGheService;

import java.util.List;

@Controller
@RequestMapping("/admin/seat-type")
public class SeatTypeController {
    @Autowired private LoaiGheService loaiGheService;
    @GetMapping("/show-seat-type")
    public String showtypeseat(Model model){
        List<Loaighe> loaiGhe = loaiGheService.getAllLoaiGhe();
        model.addAttribute("loaiGhe", loaiGhe);
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

