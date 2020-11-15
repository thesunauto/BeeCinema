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
import vn.edu.poly.beecinema.entity.LoaiPhim;
import vn.edu.poly.beecinema.service.DayGheService;
import vn.edu.poly.beecinema.service.TaikhoanService;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/admin/row-seat")
public class RowSeatController {
    @Autowired private DayGheService dayGheService;
    @Autowired private TaikhoanService taikhoanService;

    @GetMapping("/show-row-seat")
    public String showRowSeat(Model model){
        String keyword = null;
        return listByPage(model, 1, "id", "asc", keyword);
    }

    @GetMapping("/page/{pageNumber}")
    public String listByPage(Model model ,
                             @PathVariable("pageNumber") int currentPage,
                             @Param("sortField") String sortField,
                             @Param("sortDir") String sortDir,
                             @Param("keyword") String keyword) {
        Page<Dayghe> page = dayGheService.listAll(currentPage, sortField, sortDir, keyword);
        long totalItem = page.getTotalElements();
        int totalPages = page.getTotalPages();
        List<Dayghe> dayGhe = page.getContent();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItem", totalItem);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("dayGhe", dayGhe);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("keyword", keyword);
        return "admin/row-seat/show-row-seat";
    }

    @GetMapping("/add-row-seat")
    public String addrowseat(Model model){
        Dayghe dayghe = new Dayghe();
        model.addAttribute("rowSeat",dayghe);
        return "admin/row-seat/add-row-seat";
    }

    @PostMapping("/add-row-seat")
    public String saverowseat(Authentication authentication, Model model, @ModelAttribute(value = "rowSeat") Dayghe DayGhe){
        DayGhe.setNgaytao(LocalDateTime.now());
        dayGheService.saveDayGhe(DayGhe);
        model.addAttribute("messages" , "ThemThanhCong");
        model.addAttribute("dayGhe", dayGheService.getAllDayGhe());
        return "admin/row-seat/show-row-seat";
    }
    @GetMapping("/update-row-seat/{id}")
    public String findrowseat(Model model, @PathVariable(value = "id") String id){
        Dayghe dayghe = dayGheService.findDayGheByID(id).get();
        model.addAttribute("rowSeat",dayghe);
        return "admin/row-seat/update-row-seat";
    }

    @PostMapping("/update-row-seat")
    public String updaterowseat(Model model, @ModelAttribute(value = "rowSeat") Dayghe DayGhe){
        dayGheService.saveDayGhe(DayGhe);
        return "redirect:/admin/row-seat/show-row-seat";
    }


    @GetMapping("/delete-row-seat/{id}")
    public  String deleteRowSeat (@PathVariable(value = "id") String id, Model model){
        dayGheService.deleteDayGhe(id);
        model.addAttribute("messages" , "XoaThanhCong");
        model.addAttribute("dayGhe", dayGheService.getAllDayGhe());
        return "admin/row-seat/show-row-seat";
    }




}

