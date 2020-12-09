package vn.edu.poly.beecinema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.poly.beecinema.entity.Phim;
import vn.edu.poly.beecinema.entity.Sukien;
import vn.edu.poly.beecinema.entity.Taikhoan;
import vn.edu.poly.beecinema.service.PhimService;
import vn.edu.poly.beecinema.service.SukienService;
import vn.edu.poly.beecinema.service.TaikhoanService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private TaikhoanService taikhoanService;
    @Autowired
    private PhimService phimService;
    @Autowired
    private SukienService suKienService;

    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;
    @Autowired
    public ClientController(InMemoryUserDetailsManager inMemoryUserDetailsManager) {
        this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
    }

    public String setLayout(Authentication authentication) {
        String page = "client/layout";
        if (authentication != null && authentication.isAuthenticated()){
            page = "client/layout_da_dang_nhap";
        }
        return page;
    }

    @GetMapping("/manager-ticket")
    public String ticketManager(Model model, Authentication authentication){
        String trang = setLayout(authentication);
        model.addAttribute("trang", trang);
        return "client/ticketManager";
    }

    @GetMapping("/detail-film/{id}")
    public String detailfilm(Model model, Authentication authentication, @PathVariable(value = "id") String id){
        Phim phim = phimService.findPhimById(id).get();
        String trang = setLayout(authentication);
        model.addAttribute("trang", trang);
        model.addAttribute("phim", phim);
        return "client/detail-film";
    }
    @GetMapping("/list-film")
    public String listfilm(Model model, Authentication authentication){
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
        System.out.println(phim_sap_chieu);
        String trang = setLayout(authentication);
        model.addAttribute("trang", trang);
        model.addAttribute("phimDangChieu", phim_dang_chieu);
        model.addAttribute("phimSapchieu", phim_sap_chieu);
        model.addAttribute("phim", phim);
        return "client/list-film";
    }
    @GetMapping("/select-seat")
    public String selectseat(Authentication authentication, Model model){
        String trang = setLayout(authentication);
        model.addAttribute("trang", trang);
        return "client/select-seat";
    }
    @GetMapping("/about-us")
    public String aboutus(Authentication authentication, Model model){
        String trang = setLayout(authentication);
        model.addAttribute("trang", trang);
        return "client/about-us";
    }
    @GetMapping("/contact-us")
    public String contactus(Authentication authentication, Model model){
        String trang = setLayout(authentication);
        model.addAttribute("trang", trang);
        return "client/contact-us";
    }
    @GetMapping("/list-event")
    public String envent(Model model, Authentication authentication){
        List <Sukien> suKien = suKienService.getAllSukien();
        String trang = setLayout(authentication);
        model.addAttribute("trang", trang);
        model.addAttribute("suKien", suKien);
        return "client/list-event";
    }
    @GetMapping(value = "/profile-client")
    public String editMovieType(Model model, Authentication authentication){
        String trang = setLayout(authentication);
        model.addAttribute("trang", trang);
        Optional<Taikhoan> taiKhoanEdit = taikhoanService.findTaikhoanById(taikhoanService.findTaikhoanById(authentication.getName()).get().getUsername());
        taiKhoanEdit.ifPresent(taikhoan -> model.addAttribute("taikhoan", taikhoan));
        return "client/Profile";
    }
    @PostMapping(value = "/profile-client")
    public String updateMovieType(@Valid @ModelAttribute("taikhoan") Taikhoan taikhoan, BindingResult bindingResult,
                                  Model model, Authentication authentication, @RequestParam("images") MultipartFile images){

        taikhoan.setMatkhau(taikhoanService.findTaikhoanById(authentication.getName()).get().getMatkhau());
        taikhoan.setNgaytao(taikhoanService.findTaikhoanById(authentication.getName()).get().getNgaytao());
        taikhoan.setQuyen(taikhoanService.findTaikhoanById(authentication.getName()).get().getQuyen());
        taikhoan.setHinhanh(taikhoanService.findTaikhoanById(authentication.getName()).get().getHinhanh());
        taikhoan.setTrangthai(taikhoanService.findTaikhoanById(authentication.getName()).get().getTrangthai());
        if(!images.isEmpty()){
            Path path = Paths.get("uploads/");
            try{
                InputStream inputStream = images.getInputStream();
                Files.copy(inputStream, path.resolve(images.getOriginalFilename()),
                        StandardCopyOption.REPLACE_EXISTING);
                taikhoan.setHinhanh(images.getOriginalFilename().toLowerCase());
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        taikhoanService.saveTaikhoan(taikhoan);
        String trang = setLayout(authentication);
        model.addAttribute("trang", trang);
        model.addAttribute("messages", "thanhcong");
        return "client/Profile";
    }

    @GetMapping("/change-pass")
    public String changePassword(Model model, Authentication authentication) {

        String trang = setLayout(authentication);
        model.addAttribute("trang", trang);
        return "client/change-password";
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
        String trang = setLayout(authentication);
        model.addAttribute("trang", trang);
        return "client/change-password";
    }

}