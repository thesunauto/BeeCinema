package vn.edu.poly.beecinema.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.edu.poly.beecinema.entity.Dayghe;
import vn.edu.poly.beecinema.entity.Ghe;
import vn.edu.poly.beecinema.entity.LoaiPhim;
import vn.edu.poly.beecinema.service.*;

import javax.validation.Valid;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/seat")
public class SeatController {
    @Autowired private GheService gheService;
    @Autowired private PhongService phongService;
    @Autowired private DayGheService dayGheService;
    @Autowired private LoaiGheService loaiGheService;
    @Autowired private TaikhoanService taikhoanService;

    @GetMapping("/show-seat")
    public String showSeat(Model model){
        String keyword = null;
        return listByPage(model, 1, "id", "asc", "1", null);
    }

    @GetMapping("/page/{pageNumber}")
    public String listByPage(Model model ,
                             @PathVariable("pageNumber") int currentPage,
                             @Param("sortField") String sortField,
                             @Param("sortDir") String sortDir,
                             @Param("keyword") String keyword,
                             String messages) {
        Page<Ghe> page = gheService.listAll(currentPage, sortField, sortDir, keyword);
        long totalItem = page.getTotalElements();
        int totalPages = page.getTotalPages();
        List<Ghe> ghe = page.getContent();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItem", totalItem);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("ghe", ghe);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("keyword", keyword);
        model.addAttribute("messages", messages);
        return "admin/seat/show-seat";
    }

    @GetMapping("/add-seat")
    public String addSeat(Model model){
        Ghe ghe = new Ghe();
        model.addAttribute("seat",ghe);
        return "admin/seat/add-seat";
    }

    @PostMapping("/add-seat")
    public String saveSeat(@Valid @ModelAttribute(value = "seat") Ghe ghe, BindingResult bindingResult,
                           Authentication authentication, Model model){
        if(bindingResult.hasErrors()){

        }else{
            ghe.setTaikhoan(taikhoanService.findTaikhoanById(authentication.getName()).get());
            ghe.setPhong(phongService.findPhongById(ghe.getPhong().getId()).get());
            ghe.setDayghe(dayGheService.findDayGheByID(ghe.getDayghe().getId()).get());
            ghe.setLoaighe(loaiGheService.findLoaiGheById(ghe.getLoaighe().getId()).get());
            ghe.setNgaytao(LocalDateTime.now());
            gheService.saveGhe(ghe);
            return listByPage(model, 1, "id", "asc", "1", "themThanhCong");
        }
        return "admin/seat/add-seat";
    }

    @GetMapping("/update-seat/{id}")
    public String findSeat(Model model, @PathVariable(value = "id") int id){
        Ghe ghe =  gheService.findGheById(id).get();
        model.addAttribute("seat",ghe);
        return "admin/seat/update-seat";
    }

    @PostMapping("/update-seat")
    public String updateSeat(@Valid @ModelAttribute(value = "seat") Ghe ghe, BindingResult bindingResult,
                             Authentication authentication, Model model){
        if(bindingResult.hasErrors()){

        }else{
            gheService.saveGhe(ghe);
            return listByPage(model, 1, "id", "asc", "1", "suaThanhCong");
        }
        return "admin/seat/update-seat";
    }

    @GetMapping("/delete-seat/{id}")
    public  String deleteSeat (@PathVariable(value = "id") int id,  Model model){
        gheService.deleteGhe(id);
        return listByPage(model, 1, "id", "asc", "1", "xoaThanhCong");
    }

}

