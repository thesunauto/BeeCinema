package vn.edu.poly.beecinema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.poly.beecinema.service.QuyenService;

@Controller
public class MainController {
    @Autowired
    private QuyenService quyenService;

    @RequestMapping("/")
    public String userHomePage(Model model) {
        return "client/UserHomePage";
    }

    @RequestMapping("/login")
    public String loginPage(Model model) {
        return "client/SignIn";
    }
}
