package vn.edu.poly.beecinema.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.poly.beecinema.entity.Dayghe;
import vn.edu.poly.beecinema.entity.Ghe;
import vn.edu.poly.beecinema.entity.Loaighe;
import vn.edu.poly.beecinema.service.GheService;
import vn.edu.poly.beecinema.service.LoaiGheService;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/admin/seat-type")
public class SeatTypeController {
    @Autowired private LoaiGheService loaiGheService;

    @GetMapping("/show-seat-type")
    public String showTypeSeat(Model model){
        String keyword = null;
        return listByPage(model, 1, "id", "asc", keyword);
    }

    @GetMapping("/page/{pageNumber}")
    public String listByPage(Model model ,
                             @PathVariable("pageNumber") int currentPage,
                             @Param("sortField") String sortField,
                             @Param("sortDir") String sortDir,
                             @Param("keyword") String keyword) {
        Page<Loaighe> page = loaiGheService.listAll(currentPage, sortField, sortDir, keyword);
        long totalItem = page.getTotalElements();
        int totalPages = page.getTotalPages();
        List<Loaighe> loaiGhe = page.getContent();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItem", totalItem);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("loaiGhe", loaiGhe);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("keyword", keyword);
        return "admin/seat-type/show-seat-type";
    }

    @GetMapping("/add-seat-type")
    public String addseattype(Model model){
        Loaighe loaighe = new Loaighe();
        model.addAttribute("seatType",loaighe);
        return "admin/seat-type/add-seat-type";
    }

    @PostMapping("/add-seat-type")
    public String saveseat(Model model, @ModelAttribute(value = "seatType") Loaighe loaighe){
        loaighe.setNgaytao(LocalDateTime.now());
        loaiGheService.saveLoaiGhe(loaighe);
        model.addAttribute("messages" , "ThemThanhCong");
        model.addAttribute("loaiGhe", loaiGheService.getAllLoaiGhe());
        return "admin/seat-type/show-seat-type";
    }
    @GetMapping("/update-seat-type/{id}")
    public String findSeatType(Model model, @PathVariable(value = "id") String id){
        Loaighe loaighe =  loaiGheService.findLoaiGheById(id).get();
        model.addAttribute("seatType",loaighe);
        return "admin/seat-type/update-seat-type";
    }

    @PostMapping("/update-seat-type")
    public String updateSeatType(Model model, @ModelAttribute(value = "seatType") Loaighe loaighe){
        loaiGheService.saveLoaiGhe(loaighe);
        return "redirect:/admin/seat-type/show-seat-type";
    }
    @GetMapping("/delete-seat-type/{id}")
    public  String deleteSeatType (@PathVariable(value = "id") String id, Model model){
        loaiGheService.deleteLoaiGhe(id);
        model.addAttribute("messages" , "XoaThanhCong");
        model.addAttribute("loaiGhe", loaiGheService.getAllLoaiGhe());
        return "admin/seat-type/show-seat-type";
    }




}

