package vn.edu.poly.beecinema.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.poly.beecinema.entity.Taikhoan;
import vn.edu.poly.beecinema.service.TaikhoanService;

@Controller
@RequestMapping("/admin")
public class ChangePasswordController {
    @Autowired
    private TaikhoanService taikhoanService;

    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;
    @Autowired
    public ChangePasswordController(InMemoryUserDetailsManager inMemoryUserDetailsManager) {
        this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
    }

    @GetMapping("/change-pass")
    public String changePassword(Model model, Authentication authentication) {
        return "admin/change-pass-admin";
    }
    @PostMapping("/change-pass")
    public String changePassword(
            @RequestParam("password") String password,
            @RequestParam("newpassword") String newpassword,
            @RequestParam("confirmnewpassword") String confirmpassword,
            Model model, Authentication authentication) {
        Taikhoan taikhoan = taikhoanService.findTaikhoanByUsername(taikhoanService.findTaikhoanById(authentication.getName()).get().getUsername());
        if(!password.equals(taikhoanService.findTaikhoanById(authentication.getName()).get().getMatkhau())){
            model.addAttribute("messages", "saimatkhau");
        }
        else if(!newpassword.equals(confirmpassword)){
            model.addAttribute("messages", "matkhaukhongkhop");
        }
        else{
            taikhoanService.updatePassword(taikhoan, newpassword);
            inMemoryUserDetailsManager.deleteUser(taikhoan.getUsername());
            inMemoryUserDetailsManager.createUser(User.withDefaultPasswordEncoder().username(taikhoan.getUsername()).password(taikhoan.getMatkhau()).roles(taikhoan.getQuyen().getTen()).build());
            model.addAttribute("messages", "thanhcong");
        }

        return "admin/change-pass-admin";
    }
}
