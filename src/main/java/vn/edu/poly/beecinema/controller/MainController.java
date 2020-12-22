package vn.edu.poly.beecinema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.poly.beecinema.commons.VeResponse;
import vn.edu.poly.beecinema.entity.Phim;
import vn.edu.poly.beecinema.entity.Quyen;
import vn.edu.poly.beecinema.entity.Sukien;
import vn.edu.poly.beecinema.entity.Taikhoan;
import vn.edu.poly.beecinema.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired private QuyenService quyenService;
    @Autowired private TaikhoanService taikhoanService;
    @Autowired private PhimService phimService;
    @Autowired private SukienService suKienService;
    @Autowired private SuatChieuService suatChieuService;
    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;
    @Autowired
    public MainController(InMemoryUserDetailsManager inMemoryUserDetailsManager) {
        this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
    }


    @GetMapping("/datve/{id}")
    public String datghe(Authentication authentication,HttpSession httpSession, Model model, @PathVariable String id){
        String trang = setLayout(authentication);
        model.addAttribute("trang", trang);
        if(httpSession.getAttribute("veresponse")==null){
            httpSession.setAttribute("veresponse", new ArrayList<VeResponse>());
        }

        List<VeResponse> veResponses = (List<VeResponse>) httpSession.getAttribute("veresponse");
        httpSession.setAttribute("veresponse",veResponses);
        model.addAttribute("film", phimService.findPhimById(id));
        model.addAttribute("suatchieu", suatChieuService.getAllSuatChieuByPhimAndToday(id));
        List<LocalDateTime> localDateTimes = new ArrayList<>();
        for(int i = 0; i<8;i++){
            localDateTimes.add(LocalDateTime.now().plusDays(i));
        }
        model.addAttribute("LichPhim", localDateTimes);
        return "client/datve";
    }

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

    public String setLayout(Authentication authentication) {
        String page = "client/layout";
        if (authentication != null && authentication.isAuthenticated()){
            page = "client/layout_da_dang_nhap";
        }
        return page;
    }

    @RequestMapping("/")
    public String userHomePage(Authentication authentication, Model model) {

        List <Phim> phim = phimService.getAllPhim();
        List <Phim> phim_sap_chieu =  new ArrayList<Phim>();
        List <Phim> phim_dang_chieu =  new ArrayList<Phim>();
        for ( Phim pm : phim) {
            if(LocalDateTime.now().isBefore(pm.getNgaybatdau())){
                phim_sap_chieu.add(pm);
            }
            else if(LocalDateTime.now().isBefore(pm.getNgayketthuc())){
                phim_dang_chieu.add(pm);
            }
        }
        model.addAttribute("phimDangChieu", phim_dang_chieu);
        model.addAttribute("phimSapchieu", phim_sap_chieu);
        List <Sukien> suKien = suKienService.getAllSukien();
        model.addAttribute("suKien", suKien);
        model.addAttribute("authentication", authentication);
        model.addAttribute("phim", phim);
        String trang = setLayout(authentication);
        model.addAttribute("trang", trang);
        if(authentication!=null){
            if(authentication.isAuthenticated()){
                if(authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_USER"))){
                    return "client/UserHomePage";
                }
                if(authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"))){
                    return "redirect:/admin/user/show-user";
                }
                if(authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_EMP"))){
                    return "redirect:/employee/chonphim";
                }
            }
        }
        return "client/UserHomePage";
    }

    @RequestMapping("/login")
    public String loginPage(HttpServletRequest request, Model model, Authentication authentication) {
        String referrer = request.getHeader("Referer");
        request.getSession().setAttribute("url_prior_login", referrer);
        String trang = setLayout(authentication);
        model.addAttribute("trang", trang);
        return "client/SignIn";
    }
    @RequestMapping("/loginfail")
    public String loginfPage(Model model, Authentication authentication) {
        String trang = setLayout(authentication);
        model.addAttribute("trang", trang);
        model.addAttribute("message","toastr.error('Tài khoản mật khẩu không đúng', '', {positionClass: 'md-toast-top-right'});$('#toast-container').attr('class','md-toast-top-right');");
        return "client/SignIn";
    }
//    @RequestMapping("/signup")
//    public String signUpPage(Model model) {
//        return "client/SignUp";
//    }

    @RequestMapping("/signup")
    public String signUpPage(Model model, Authentication authentication){
        String trang = setLayout(authentication);
        model.addAttribute("trang", trang);
        model.addAttribute("taikhoan", new Taikhoan());
        return "client/SignUp";
    }

    @PostMapping("/signup")
    public String saveSignUpPage(@ModelAttribute("taikhoan") Taikhoan taikhoan,
                                 @ModelAttribute("username") String idTaikhoan, @ModelAttribute("email") String email,
                                 Model model, Authentication authentication){

//        Taikhoan checkMail = taikhoanService.findByEmail(email);
//        if(taikhoanService.findTaikhoanById(idTaikhoan).isPresent()){
//            model.addAttribute("messages", "trungid");
//        }else if(checkMail != null){
//            model.addAttribute("messages", "trungemail");
        if(taikhoanService.findByEmail(email).size()>0){
            model.addAttribute("messages", "trungemail");
            String trang = setLayout(authentication);
            model.addAttribute("trang", trang);
            return "client/SignUp";
        }

        if(taikhoanService.findTaikhoanById(idTaikhoan).orElse(null)!=null){
            model.addAttribute("messages", "trungid");
            String trang = setLayout(authentication);
            model.addAttribute("trang", trang);
            return "client/SignUp";
        }

        Quyen quyen = quyenService.getQuyenById("3");
        taikhoan.setQuyen(quyen);
        taikhoan.setMota(null);
        taikhoan.setDiachi(null);
        taikhoan.setTrangthai(0);
        taikhoan.setResetPasswordToken(null);
        taikhoan.setNgaytao(LocalDateTime.now());
        taikhoan.setHinhanh("null");
        taikhoanService.saveTaikhoan(taikhoan);
        inMemoryUserDetailsManager.createUser(User.withDefaultPasswordEncoder()
                .username(taikhoan.getUsername())
                .password(taikhoan.getMatkhau())
                .roles(taikhoan.getQuyen().getTen())
                .build());
        model.addAttribute("messages", "thanhcong");

        String trang = setLayout(authentication);
        model.addAttribute("trang", trang);
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

    @PostMapping("/changePassword")
    public String changepassword(@RequestParam String path,@RequestParam String pass,Authentication authentication){
       Taikhoan taikhoan = taikhoanService.findTaikhoanById(authentication.getName()).get();
       taikhoanService.updatePassword(taikhoan,pass);
        inMemoryUserDetailsManager.deleteUser(taikhoan.getUsername());
        inMemoryUserDetailsManager.createUser(User.withDefaultPasswordEncoder().username(taikhoan.getUsername()).password(pass).roles(taikhoan.getQuyen().getTen()).build());
        return "redirect:/"+path;
    }
}
