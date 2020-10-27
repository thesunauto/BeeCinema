package vn.edu.poly.beecinema.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.poly.beecinema.entity.Ngonngu;
import vn.edu.poly.beecinema.service.NgonNguService;

import java.util.List;

@Controller
@RequestMapping("/admin/language")
public class LanguageController {
    @Autowired private NgonNguService ngonNguService;

    @GetMapping("/show-language")
    public String showLanguage(Model model){
        List<Ngonngu> ngonNgu = ngonNguService.getAllNgonNgu();
        model.addAttribute("ngonNgu", ngonNgu);
        return "admin/language/show-language";
    }

    @RequestMapping("/add-language")
    public String addLanguage(Model model){

        return "admin/language/add-language";
    }

    @RequestMapping("/update-language")
    public String updateLanguage(Model model){

        return "admin/language/update-language";
    }





}
