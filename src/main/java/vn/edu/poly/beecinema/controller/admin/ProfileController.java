package vn.edu.poly.beecinema.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/profile")
public class ProfileController {
    @GetMapping("/update-profile")
    public String  updateProfile(){
        return "admin/profile/update-profile";
    }
}
