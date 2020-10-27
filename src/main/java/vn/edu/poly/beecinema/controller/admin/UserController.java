package vn.edu.poly.beecinema.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.poly.beecinema.entity.DoTuoi;
import vn.edu.poly.beecinema.entity.Taikhoan;
import vn.edu.poly.beecinema.service.TaikhoanService;

import java.util.List;

@Controller
@RequestMapping("/admin/user")
public class UserController {
    @Autowired
    private TaikhoanService taikhoanService;

    @GetMapping("/show-user")
    public String showUser(Model model){
        List<Taikhoan> taikhoan = taikhoanService.getAllTaikhoan();
        model.addAttribute("taikhoans", taikhoan);
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
