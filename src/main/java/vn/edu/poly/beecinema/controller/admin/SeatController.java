package vn.edu.poly.beecinema.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.poly.beecinema.entity.Dayghe;
import vn.edu.poly.beecinema.entity.Ghe;
import vn.edu.poly.beecinema.service.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/seat")
public class SeatController {
    @Autowired private GheService gheService;
    @Autowired private PhongService phongService;
    @Autowired private DayGheService dayGheService;
    @Autowired private LoaiGheService loaiGheService;
    @Autowired private TaikhoanService taikhoanService;
    @GetMapping("/show-seat")
    public String showseat(Model model){
        List<Ghe> ghe = gheService.getAllGhe();
        model.addAttribute("ghe", ghe);
        return "admin/seat/show-seat";
    }

    @GetMapping("/add-seat")
    public String addseat(Model model){
        Ghe ghe = new Ghe();
        model.addAttribute("seat",ghe);
        return "admin/seat/add-seat";
    }

    @PostMapping("/add-seat")
    public String saveseat(Authentication authentication, Model model, @ModelAttribute(value = "seat") Ghe ghe){
        ghe.setTaikhoan(taikhoanService.findTaikhoanById(authentication.getName()).get());
        ghe.setPhong(phongService.findPhongById(ghe.getPhong().getId()).get());
        ghe.setDayghe(dayGheService.findDayGheByID(ghe.getDayghe().getId()).get());
        ghe.setLoaighe(loaiGheService.findLoaiGheById(ghe.getLoaighe().getId()).get());
        ghe.setNgaytao(LocalDateTime.now());
        gheService.saveGhe(ghe);
        System.out.println(ghe);
        return "redirect:/admin/seat/show-seat";
    }

//    @GetMapping("/update-seat/{id}")
//    public String findseat(Model model, @PathVariable(value = "id") int id){
//        List<Ghe> ghe =  gheService.findGheById(id);
//        model.addAttribute("seat",ghe);
//        return "admin/seat/update-seat";
//    }
//
//    @PostMapping("/update-seat")
//    public String updateseat(Authentication authentication, Model model, @ModelAttribute(value = "seat") Ghe ghe){
//
//        ghe.setTaikhoan(taikhoanService.findTaikhoanById(authentication.getName()).get());
//        ghe.setPhong(phongService.findPhongById(ghe.getPhong().getId()).get());
//        ghe.setDayghe(dayGheService.findDayGheByID(ghe.getDayghe().getId()).get());
//        ghe.setLoaighe(loaiGheService.findLoaiGheById(ghe.getLoaighe().getId()).get());
//        ghe.setNgaytao(LocalDateTime.now());
//        gheService.saveGhe(ghe);
//        System.out.println(ghe);
//        return "redirect:/admin/seat/show-seat";
//    }

    @GetMapping("/delete-seat/{id}")
    public  String deleteSeat (@PathVariable(value = "id") int id){
        gheService.deleteGhe(id);
        return "redirect:/admin/seat/show-seat";
    }




}

