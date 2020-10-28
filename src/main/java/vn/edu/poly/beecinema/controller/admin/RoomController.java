package vn.edu.poly.beecinema.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.poly.beecinema.entity.Phong;
import vn.edu.poly.beecinema.service.PhongService;

import java.util.List;

@Controller
@RequestMapping("/admin/room")
public class RoomController {
    @Autowired private PhongService phongService;
    @GetMapping("/show-room")
    public String showRoom(Model model){
        List<Phong> phong = phongService.getAllPhong();
        model.addAttribute("phong", phong);
        return "admin/room/show-room";
    }

    @RequestMapping("/add-room")
    public String addRoom(Model model){
        return "admin/room/add-room";
    }

    @RequestMapping("/update-room")
    public String updateRoom(Model model){
        return "admin/room/update-room";
    }





}
