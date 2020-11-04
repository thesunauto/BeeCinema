package vn.edu.poly.beecinema.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.edu.poly.beecinema.entity.Suatchieu;
import vn.edu.poly.beecinema.service.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/screenings")
public class ScreeningsController {
    @Autowired private SuatChieuService suatChieuService;
    @Autowired private TaikhoanService taiKhoanService;
    @Autowired private PhimService phimService;
    @Autowired private PhongService phongService;
    @Autowired private KhungGioService khungGioService;




    @GetMapping("/show-screenings")
    public String showMovieType(Model model){
        List<Suatchieu> suatChieu = suatChieuService.getAllSuatChieu();
        model.addAttribute("suatChieu", suatChieu);

        return "admin/screenings/show-screenings";
    }

    @RequestMapping("/add-screenings")
    public String addMovieType(Model model){
        model.addAttribute("suatChieu", new Suatchieu());
        return "admin/screenings/add-screenings";
    }

    @GetMapping(value = "/edit")
    public String editMovieType(Model model, @PathVariable("id") Integer suatChieuId){
        Optional<Suatchieu> suatChieuEdit = suatChieuService.findSuatChieuById(suatChieuId);
        suatChieuEdit.ifPresent(suatChieu -> model.addAttribute("suatChieu", suatChieu));
        return "admin/screenings/update-screenings";
    }

    @PostMapping("/add-screenings")
    public String saveMovieType(@ModelAttribute("suatChieu") Suatchieu suatChieu , BindingResult bindingResult,

                                Model model, Authentication authentication){
//        if(bindingResult.hasErrors()){
//
//        }else if(suatChieuService.findSuatChieuById(suatChieuID).isPresent()){
//            model.addAttribute("messages", "trungid");
//        }else{
            suatChieu.setPhim(phimService.findPhimById(suatChieu.getPhim().getId()).get());
            suatChieu.setPhong(phongService.findPhongById(suatChieu.getPhong().getId()).get());
            suatChieu.setKhunggio(khungGioService.findKhungGioById(suatChieu.getKhunggio().getId()).get());
            suatChieu.setNgaytao(LocalDate.now());
            suatChieu.setTaikhoan(taiKhoanService.findTaikhoanById(authentication.getName()).get());
            suatChieuService.saveSuatChieu(suatChieu);
            model.addAttribute("messages", "thanhcong");
//        }
        return "admin/screenings/add-screenings";
    }

    @PostMapping(value = "/edit")
    public String updateMovieType(@Valid @ModelAttribute("khungGio") Suatchieu suatChieu ,
                                  BindingResult bindingResult, Model model,  Authentication authentication){
        if(bindingResult.hasErrors()){

        }else{
            suatChieuService.saveSuatChieu(suatChieu);
            model.addAttribute("messages", "thanhcong");
        }
        return "admin/screenings/update-screenings";
    }

    @RequestMapping(value = "/delete" )
    public String deleteUser(@PathVariable("id") Integer suatChieuId, Model model) {
        suatChieuService.deleteSuatChieu(suatChieuId);
        List<Suatchieu> suatChieu = suatChieuService.getAllSuatChieu();
        model.addAttribute("suatChieu", suatChieu);
        model.addAttribute("messages", "thanhcong");
        return "admin/screenings/show-screenings";
    }



}

