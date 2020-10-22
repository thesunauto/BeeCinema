package vn.edu.poly.beecinema.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/age")
public class AgeController {
    @RequestMapping("/show-age")
    public String showAge(Model model){

        return "admin/age/show-age";
    }

    @RequestMapping("/add-age")
    public String addAge(Model model){

        return "admin/age/add-age";
    }

    @RequestMapping("/update-age")
    public String updateAge(Model model){

        return "admin/age/update-age";
    }





}
