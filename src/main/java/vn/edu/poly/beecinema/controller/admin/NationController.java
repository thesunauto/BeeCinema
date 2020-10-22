package vn.edu.poly.beecinema.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/nation")
public class NationController {
    @RequestMapping("/show-nation")
    public String showNation(Model model){

        return "admin/nation/show-nation";
    }

    @RequestMapping("/add-nation")
    public String addNation(Model model){

        return "admin/nation/add-nation";
    }

    @RequestMapping("/update-nation")
    public String updateNation(Model model){

        return "admin/nation/update-nation";
    }





}
