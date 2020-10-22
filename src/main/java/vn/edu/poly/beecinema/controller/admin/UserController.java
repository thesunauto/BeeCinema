package vn.edu.poly.beecinema.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.poly.beecinema.service.QuyenService;

@Controller
@RequestMapping("/admin/user")
public class UserController {
    @RequestMapping("/show-user")
    public String showUser(Model model){
        return "admin/account/show-account";
    }

    @RequestMapping("/add-user")
    public String addAccount(Model model){
        return "admin/account/add-account";
    }

    @RequestMapping("/update-user")
    public String updateAccount(Model model){
        return "admin/account/update-account";
    }





}
