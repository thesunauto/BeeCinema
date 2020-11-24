package vn.edu.poly.beecinema.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.edu.poly.beecinema.entity.LoaiPhim;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.poly.beecinema.entity.NgonNgu;
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
        String keyword = null;
        return listByPage(model, 1, "id", "asc", keyword, null);
    }

    @GetMapping("/page/{pageNumber}")
    public String listByPage(Model model ,
                             @PathVariable("pageNumber") int currentPage,
                             @Param("sortField") String sortField,
                             @Param("sortDir") String sortDir,
                             @Param("keyword") String keyword,
                             String messages) {
        Page<NgonNgu> page = ngonNguService.listAll(currentPage, sortField, sortDir, keyword);
        long totalItem = page.getTotalElements();
        int totalPages = page.getTotalPages();
        List<NgonNgu> ngonNgu = page.getContent();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItem", totalItem);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("ngonNgu", ngonNgu);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("keyword", keyword);
        model.addAttribute("messages", messages);
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
            return listByPage(model, 1, "id", "asc", null, "themThanhCong");
        }
        return "admin/language/add-language";
    }

    @PostMapping (value = "/edit")
    public String updateLanguage (@Valid @ModelAttribute ("ngonNgu") NgonNgu ngonNgu,
                                    BindingResult bindingResult , Model model , Authentication authentication){
        if (bindingResult.hasErrors()){

        }else{
            ngonNguService.saveNgonNgu(ngonNgu);
            return listByPage(model, 1, "id", "asc", null, "suaThanhCong");
        }
        return "admin/language/update-language";
    }

    @RequestMapping (value = "/delete")
    public  String deleteUser (@RequestParam("id") String ngonNguID, Model model ){
        ngonNguService.deleteNgonNgu(ngonNguID);
<<<<<<< HEAD
        List<NgonNgu> ngonNgu = ngonNguService.getAllNgonNgu();
        model.addAttribute("ngonNgu" , ngonNgu);
=======
>>>>>>> origin/master
        return listByPage(model, 1, "id", "asc", null, "xoaThanhCong");
    }

}
