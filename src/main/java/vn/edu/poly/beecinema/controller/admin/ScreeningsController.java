package vn.edu.poly.beecinema.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.edu.poly.beecinema.entity.Phim;
import vn.edu.poly.beecinema.entity.Suatchieu;
import vn.edu.poly.beecinema.entity.Sukien;
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
    public String showScreenings(Model model){
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
        Page<Suatchieu> page = suatChieuService.listAll(currentPage, sortField, sortDir, keyword);
        long totalItem = page.getTotalElements();
        int totalPages = page.getTotalPages();
        List<Suatchieu> suatChieu = page.getContent();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItem", totalItem);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("suatChieu", suatChieu);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("keyword", keyword);
        model.addAttribute("messages", messages);
        return "admin/screenings/show-screenings";
    }

    @RequestMapping("/add-screenings")
    public String addScreenings(Model model){
        model.addAttribute("suatChieu", new Suatchieu());
        return "admin/screenings/add-screenings";
    }

    @GetMapping(value = "/edit")
    public String editScreenings(Model model, @RequestParam("id") Integer suatChieuId){
        Optional<Suatchieu> suatChieuEdit = suatChieuService.findSuatChieuById(suatChieuId);
        suatChieuEdit.ifPresent(suatChieu -> model.addAttribute("suatChieu", suatChieu));
        return "admin/screenings/update-screenings";
    }


    @PostMapping("/add-screenings")
<<<<<<< HEAD
    public String saveScreenings(@Valid @ModelAttribute("suatChieu") Suatchieu suatChieu , BindingResult bindingResult,
                                 @ModelAttribute("id") Integer idSuatchieu,
                                Model model, Authentication authentication){
        if(bindingResult.hasErrors()){

        }else if(suatChieuService.findSuatChieuById(idSuatchieu).isPresent()){
            model.addAttribute("messages", "trungid");
        }else{
=======
    public String saveScreenings(@Valid @ModelAttribute("suatChieu") Suatchieu suatChieu, BindingResult bindingResult,
                                Model model, Authentication authentication){
         if(bindingResult.hasErrors()){

         }else{
>>>>>>> origin/master
            suatChieu.setPhim(phimService.findPhimById(suatChieu.getPhim().getId()).get());
            suatChieu.setPhong(phongService.findPhongById(suatChieu.getPhong().getId()).get());
            suatChieu.setKhunggio(khungGioService.findKhungGioById(suatChieu.getKhunggio().getId()).get());
            suatChieu.setNgaytao(LocalDate.now());
            suatChieu.setTaikhoan(taiKhoanService.findTaikhoanById(authentication.getName()).get());
            suatChieuService.saveSuatChieu(suatChieu);
<<<<<<< HEAD
        return listByPage(model, 1, "id", "asc", null, "themThanhCong");
=======
            return listByPage(model, 1, "id", "asc", null, "themThanhCong");
>>>>>>> origin/master
        }
        return "admin/screenings/add-screenings";
    }

    @PostMapping(value = "/edit")
<<<<<<< HEAD
    public String updateScreenings(@Valid @ModelAttribute("khungGio") Suatchieu suatChieu ,BindingResult bindingResult,
=======
    public String updateScreenings(@Valid @ModelAttribute("khungGio") Suatchieu suatChieu, BindingResult bindingResult,
>>>>>>> origin/master
                                   Model model,  Authentication authentication){
        if(bindingResult.hasErrors()){

        }else{
            suatChieuService.saveSuatChieu(suatChieu);
            return listByPage(model, 1, "id", "asc", null, "suaThanhCong");
        }
        return "admin/screenings/update-screenings";
    }


    @RequestMapping(value = "/delete" )
    public String deleteScreenings(@PathVariable("id") Integer suatChieuId, Model model) {
        suatChieuService.deleteSuatChieu(suatChieuId);
<<<<<<< HEAD
        List<Suatchieu> suatChieu = suatChieuService.getAllSuatChieu();
        model.addAttribute("suatChieu", suatChieu);
=======
>>>>>>> origin/master
        return listByPage(model, 1, "id", "asc", null, "xoaThanhCong");
    }



}

