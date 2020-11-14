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
import vn.edu.poly.beecinema.entity.LoaiPhim;
import vn.edu.poly.beecinema.entity.Suatchieu;
import vn.edu.poly.beecinema.service.KhungGioService;
import vn.edu.poly.beecinema.service.LoaiPhimService;
import vn.edu.poly.beecinema.service.TaikhoanService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/showtime")
public class ShowtimeController {
    @Autowired private KhungGioService khungGioService;
    @Autowired private TaikhoanService taiKhoanService;

//    @GetMapping("/show-showtime")
//    public String showMovieType(Model model){
//        List<Khunggio> khungGio = khungGioService.getAllKhungGio();
//        model.addAttribute("khungGio", khungGio);
//
//        return "admin/showtime/show-showtime";
//    }

    @GetMapping("/show-showtime")
    public String showShowtime(Model model){
        String keyword = null;
        return listByPage(model, 1, "id", "asc", keyword);
    }

    @GetMapping("/page/{pageNumber}")
    public String listByPage(Model model ,
                             @PathVariable("pageNumber") int currentPage,
                             @Param("sortField") String sortField,
                             @Param("sortDir") String sortDir,
                             @Param("keyword") String keyword) {
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
        return "admin/showtime/show-showtime";
    }

    @RequestMapping("/add-showtime")
    public String addShowtime(Model model){
        model.addAttribute("khungGio", new Khunggio());
        return "admin/showtime/add-showtime";
    }

    @GetMapping(value = "/edit")
    public String editShowtime(Model model, @RequestParam("id") String khungGioId){
        Optional<Khunggio> khungGioEdit = khungGioService.findKhungGioById(khungGioId);
        khungGioEdit.ifPresent(khungGio -> model.addAttribute("khungGio", khungGio));
        return "admin/showtime/update-showtime";
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
            model.addAttribute("messages", "thanhcong");
        }
        return "admin/showtime/add-showtime";
    }

    @PostMapping(value = "/edit")
    public String updateShowtime(@Valid @ModelAttribute("khungGio") Khunggio khungGio ,
                                  BindingResult bindingResult, Model model,  Authentication authentication){
        if(bindingResult.hasErrors()){

        }else{
            khungGioService.saveKhungGio(khungGio);
            model.addAttribute("messages", "thanhcong");
        }
        return "admin/showtime/update-showtime";
    }

    @RequestMapping(value = "/delete" )
    public String deleteShowtime(@RequestParam("id") String khungGioId, Model model) {
        khungGioService.deleteKhungGio(khungGioId);
        List<Khunggio> khungGio = khungGioService.getAllKhungGio();
        model.addAttribute("khungGio", khungGio);
        model.addAttribute("messages", "thanhcong");
        return "admin/showtime/show-showtime";
    }





}

