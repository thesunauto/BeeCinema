package vn.edu.poly.beecinema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.edu.poly.beecinema.entity.Phim;
import vn.edu.poly.beecinema.entity.Sukien;
import vn.edu.poly.beecinema.entity.Taikhoan;
import vn.edu.poly.beecinema.service.PhimService;
import vn.edu.poly.beecinema.service.QuyenService;
import vn.edu.poly.beecinema.service.SukienService;
import vn.edu.poly.beecinema.service.TaikhoanService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class MainController {

    @Autowired private QuyenService quyenService;
    @Autowired private TaikhoanService taikhoanService;
    @Autowired private PhimService phimService;
    @Autowired private SukienService suKienService;


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
        List <Phim> phim = phimService.getAllPhim();
        List <Sukien> suKien = suKienService.getAllSukien();
        model.addAttribute("suKien", suKien);
        model.addAttribute("phim", phim);
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
//    @RequestMapping("/signup")
//    public String signUpPage(Model model) {
//        return "client/SignUp";
//    }

    @RequestMapping("/signup")
    public String signUpPage(Model model){
        model.addAttribute("taikhoan", new Taikhoan());
        return "client/SignUp";
    }

    @PostMapping("/signup")
    public String saveSignUpPage(@Valid @ModelAttribute("taikhoan") Taikhoan taikhoan,BindingResult bindingResult,
                                 @ModelAttribute("id") String idTaikhoan,
                                 Model model, Authentication authentication){
        if(bindingResult.hasErrors()){

        }else if(taikhoanService.findTaikhoanById(idTaikhoan).isPresent()){
            model.addAttribute("messages", "trungid");
        }else{
//            taikhoan.setGioitinh(null);
//            taikhoan.setTrangthai(null);
//            taikhoan.setNgaysinh(null);
//            taikhoan.setDiachi(null);
//            taikhoan.setSodienthoai(null);
//            taikhoan.setMota(null);
//            taikhoan.setQuyen(null);
            taikhoan.setNgaytao(LocalDateTime.now());
            taikhoan.setHinhanh("a.jpg");
            taikhoanService.saveTaikhoan(taikhoan);
            model.addAttribute("messages", "thanhcong");
        }
        return "client/SignUp";
    }

    @RequestMapping("/index")
    public String index(Model model) {
        return "index";
    }

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Authentication authentication) {
        return authentication.getName();
    }
}
