package vn.edu.poly.beecinema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
    @GetMapping("/detail-film/{id}")
    public String detailfilm(Model model, @PathVariable(value = "id") String id){
        Phim phim = phimService.findPhimById(id).get();
        model.addAttribute("phim", phim);
        return "client/detail-film";
    }
    @GetMapping("/list-film")
    public String listfilm(Model model){
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
        model.addAttribute("phimDangChieu", phim_dang_chieu);
        model.addAttribute("phimSapchieu", phim_sap_chieu);
        return "client/list-film";
    }
    @GetMapping("/select-seat")
    public String selectseat(){
        return "client/select-seat";
    }
    @GetMapping("/about-us")
    public String aboutus(){
        return "client/about-us";
    }
    @GetMapping("/contact-us")
    public String contactus(){
        return "client/contact-us";
    }
    @GetMapping("/list-event")
    public String envent(Model model){
        List <Sukien> suKien = suKienService.getAllSukien();
        model.addAttribute("suKien", suKien);
        return "client/list-event";
    }
    @GetMapping(value = "/profile-client")
    public String editMovieType(Model model, Authentication authentication){
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
        model.addAttribute("messages", "thanhcong");
        return "client/Profile";
    }
}