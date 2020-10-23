package vn.edu.poly.beecinema.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/room")
public class RoomController {
    @RequestMapping("/show-room")
    public String showRoom(Model model){
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
