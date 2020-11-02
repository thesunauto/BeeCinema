package vn.edu.poly.beecinema.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.poly.beecinema.entity.Ghe;
import vn.edu.poly.beecinema.entity.Phong;
import vn.edu.poly.beecinema.service.PhongService;
import vn.edu.poly.beecinema.service.TaikhoanService;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/admin/room")
public class RoomController {
    @Autowired private PhongService phongService;
    @Autowired private TaikhoanService taikhoanService;
    @GetMapping("/show-room")
    public String showRoom(Model model){
        List<Phong> phong = phongService.getAllPhong();
        model.addAttribute("phong", phong);
        return "admin/room/show-room";
    }

    @GetMapping("/add-room")
    public String addRoom(Model model){
        Phong phong = new Phong();
        model.addAttribute("room", phong);
        return "admin/room/add-room";
    }

    @PostMapping("/add-room")
    public String saveRoom(Authentication authentication, Model model, @ModelAttribute(value = "room") Phong phong){
        phong.setTaikhoan(taikhoanService.findTaikhoanById(authentication.getName()).get());
        phong.setNgaytao(LocalDateTime.now());
        phongService.savePhong(phong);
        return "redirect:/admin/room/show-room";
    }

    @RequestMapping("/update-room")
    public String updateRoom(Model model){
        return "admin/room/update-room";
    }

    @GetMapping("/delete-room/{id}")
    public  String deleteroom (@PathVariable(value = "id") String id){
        phongService.deletePhong(id);
        return  "redirect:/admin/room/show-room";
    }




}
