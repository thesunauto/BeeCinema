package vn.edu.poly.beecinema.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.edu.poly.beecinema.entity.Khunggio;
import vn.edu.poly.beecinema.service.KhungGioService;
import vn.edu.poly.beecinema.service.TaikhoanService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Controller
@RequestMapping("/admin/showtime")
public class ShowtimeController {
    @Autowired private KhungGioService khungGioService;
    @Autowired private TaikhoanService taiKhoanService;

    @GetMapping("/show-showtime")
    public String showShowtime(Model model){
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
        Page<Khunggio> page = khungGioService.listAll(currentPage, sortField, sortDir, keyword);
        long totalItem = page.getTotalElements();
        int totalPages = page.getTotalPages();
        List<Khunggio> khungGio = page.getContent();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItem", totalItem);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("khungGio", khungGio);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("keyword", keyword);
        model.addAttribute("messages", messages);
        return "admin/showtime/show-showtime";
    }

    @RequestMapping("/add-showtime")
    public String addShowtime(Model model){
        model.addAttribute("khungGio", new Khunggio());
        return "admin/showtime/add-showtime";
    }

    @GetMapping(value = "/edit")
    public String editShowtime(Model model, @RequestParam("id") String khungGioId){
        Khunggio khunggio = khungGioService.findKhungGioById(khungGioId).get();
        if(khunggio.getSuatchieus().isEmpty()){
            model.addAttribute("khungGio", khunggio);
            return "admin/showtime/update-showtime";
        }else{
            return listByPage(model, 1, "id", "asc", null, "khongthesua");
        }

    }

    @PostMapping("/add-showtime")
    public String saveShowtime(@Valid @ModelAttribute("khungGio") Khunggio khungGio , BindingResult bindingResult,
                                @ModelAttribute("id") String khungGioID,
                                Model model, Authentication authentication){
        if(bindingResult.hasErrors()){

        }else if(khungGioService.findKhungGioById(khungGioID).isPresent()){
            model.addAttribute("messages", "trungid");
        }else{
            khungGio.setTaikhoan(taiKhoanService.findTaikhoanById(authentication.getName()).get());
            khungGioService.saveKhungGio(khungGio);
            return listByPage(model, 1, "id", "asc", null, "themThanhCong");
        }
        return "admin/showtime/add-showtime";
    }

    @PostMapping(value = "/edit")
    public String updateShowtime(@Valid @ModelAttribute("khungGio") Khunggio khungGio ,
                                  BindingResult bindingResult, Model model,  Authentication authentication){
        if(bindingResult.hasErrors()){

        }else{
            khungGioService.saveKhungGio(khungGio);
            return listByPage(model, 1, "id", "asc", null, "suaThanhCong");
        }
        return "admin/showtime/update-showtime";
    }

    @RequestMapping(value = "/delete" )
    public String deleteShowtime(@RequestParam("id") String khungGioId, Model model) {
        Khunggio khunggio = khungGioService.findKhungGioById(khungGioId).get();
        if(khunggio.getSuatchieus().isEmpty()){
            khungGioService.deleteKhungGio(khungGioId);
            return listByPage(model, 1, "id", "asc", null, "xoaThanhCong");
        }else{
            return listByPage(model, 1, "id", "asc", null, "khongthexoa");
        }


    }





}

