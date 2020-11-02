package vn.edu.poly.beecinema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.poly.beecinema.service.QuyenService;

import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class MainController {
    @Autowired
    private QuyenService quyenService;

    @RequestMapping("/becinema")
    public String userHomePage(Model model, Authentication authentication) {
        String path="";
        System.out.println(authentication.getAuthorities());
        boolean hasUSERRole = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_USER"));
        boolean hasADMINRole = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
        boolean hasEMPRole = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_EMP"));
        if(hasADMINRole){path= "redirect:/admin/user/show-user";};
        if(hasEMPRole) {path=  "redirect:/employee/chonphim";};
        if(hasUSERRole){path=  "redirect:/";};
        return path;
    }

    @RequestMapping("/")
    public String userHomePage(Model model) {
        return "client/UserHomePage";
    }

    @RequestMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("message","");
        return "client/SignIn";
    }
    @RequestMapping("/loginfail")
    public String loginfPage(Model model) {
        model.addAttribute("message","toastr.error('Tài khoản mật khẩu không đúng', '', {positionClass: 'md-toast-top-right'});$('#toast-container').attr('class','md-toast-top-right');");
        return "client/SignIn";
    }
    @RequestMapping("/signup")
    public String signUpPage(Model model) {
        return "client/SignUp";
    }

    @RequestMapping("/index")
    public String index(Model model) {
        return "index";
    }
}
