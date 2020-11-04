package vn.edu.poly.beecinema.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.edu.poly.beecinema.entity.LoaiPhim;
import vn.edu.poly.beecinema.entity.NgonNgu;
import vn.edu.poly.beecinema.service.LoaiPhimService;
import vn.edu.poly.beecinema.service.NgonNguService;
import vn.edu.poly.beecinema.service.TaikhoanService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/language")
public class LanguageController {
    @Autowired private NgonNguService ngonNguService;
    @Autowired private TaikhoanService taikhoanService;

    @GetMapping("/show-language")
    public String showLanguage(Model model){
        List<NgonNgu> ngonNgu = ngonNguService.getAllNgonNgu();
        model.addAttribute("ngonNgu", ngonNgu);
        return "admin/language/show-language";
    }

    @RequestMapping("/add-language")
    public String addLanguage(Model model){
    model.addAttribute("ngonNgu" , new NgonNgu());
        return "admin/language/add-language";
    }

    @RequestMapping(value = "/edit")
    public String editLannguage (Model model , @RequestParam ("id") String laguageID){
        Optional<NgonNgu> ngonNguEdit = ngonNguService.findNgonNguById(laguageID);
        ngonNguEdit .ifPresent(ngonNgu -> model.addAttribute("ngonNgu" , ngonNgu));
          return "admin/language/update-language";
    }
    @PostMapping ("/add-language")
    public String saveLanguage (@Valid @ModelAttribute ("ngonNgu") NgonNgu ngonNgu , BindingResult bindingResult,
                                @ModelAttribute ("id") String idNgonNgu,
                                Model model ,  Authentication authentication){
        if (bindingResult.hasErrors()){

        }else if(ngonNguService.findNgonNguById(idNgonNgu).isPresent()){
            model.addAttribute("messages" , "TrungID");
        }else {
            ngonNgu.setNgaytao(LocalDateTime.now());
            ngonNgu.setTaikhoan(taikhoanService.findTaikhoanById(authentication.getName()).get());
            ngonNguService.saveNgonNgu(ngonNgu);
            model.addAttribute("messages", "ThanhCong");
        }
        return "admin/language/add-language";
    }

    @PostMapping (value = "/edit")
    public String updateLanguage (@Valid @ModelAttribute ("ngonNgu") NgonNgu ngonNgu,
                                    BindingResult bindingResult , Model model , Authentication authentication){
        if (bindingResult.hasErrors()){

        }else{
            ngonNguService.saveNgonNgu(ngonNgu);
            model.addAttribute("messages" , "ThanhCong");
        }
        return "admin/language/update-language";
    }

    @RequestMapping (value = "/delete")
    public  String deleteUser (@RequestParam("id") String ngonNguID, Model model ){
        ngonNguService.deleteNgonNgu(ngonNguID);
        List<NgonNgu> ngonNgu = ngonNguService.getAllNgonNgu();
        model.addAttribute("ngonNgu" , ngonNgu);
        model.addAttribute("messages" , "ThanhCong");
        return "admin/language/show-language";
    }

}
