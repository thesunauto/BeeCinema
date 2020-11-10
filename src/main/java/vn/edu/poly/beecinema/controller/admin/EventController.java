package vn.edu.poly.beecinema.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.edu.poly.beecinema.entity.Sukien;
import vn.edu.poly.beecinema.service.SukienService;
import vn.edu.poly.beecinema.service.TaikhoanService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/event")
public class EventController {
    @Autowired private SukienService sukienService;
    @Autowired private TaikhoanService taiKhoanService;

    @GetMapping("/show-event")
    public String showEvent(Model model){
        List<Sukien> sukiens = sukienService.getAllSukien();
        model.addAttribute("sukiens", sukiens);
        return "admin/event/show-event";
    }

    @RequestMapping("/add-event")
    public String addEvent(Model model){
        model.addAttribute("sukien", new Sukien());
        return "admin/event/add-event";
    }

    @GetMapping(value = "/edit")
    public String editEvent(Model model, @RequestParam("id") String sukienId){
        Optional<Sukien> sukienEdit = sukienService.findSukienById(sukienId);
        sukienEdit.ifPresent(sukien -> model.addAttribute("sukien", sukien));
        return "admin/event/update-event";
    }

    @PostMapping("/add-event")
    public String saveEvent(@Valid @ModelAttribute("sukien") Sukien sukien, BindingResult bindingResult,
                            @ModelAttribute("id") String idSukien,
                            Model model, Authentication authentication){
        if(bindingResult.hasErrors()){

        }else if(sukienService.findSukienById(idSukien).isPresent()){
            model.addAttribute("messages", "trungid");
        }else{
//            sukien.setNgaybatdau(LocalDateTime.now());
//            sukien.setNgayketthuc(LocalDateTime.now());
            sukien.setHinhanh("a.jpg");
            sukien.setTaikhoan(taiKhoanService.findTaikhoanById(authentication.getName()).get());
            sukienService.saveSukien(sukien);
            model.addAttribute("messages", "thanhcong");
        }
        return "admin/event/add-event";
    }

    @PostMapping(value = "/edit")
    public String updateEvent(@Valid @ModelAttribute("sukien") Sukien sukien ,
                                  BindingResult bindingResult, Model model,  Authentication authentication){
        if(bindingResult.hasErrors()){

        }else{
            sukien.setHinhanh("a.jpg");
            sukienService.saveSukien(sukien);
            model.addAttribute("messages", "thanhcong");
        }
        return "admin/event/update-event";
    }

    @RequestMapping(value = "/delete" )
    public String deleteEvent(@RequestParam("id") String sukienId, Model model) {
        sukienService.deleteSukien(sukienId);
        List<Sukien> sukiens = sukienService.getAllSukien();
        model.addAttribute("sukiens", sukiens);
        model.addAttribute("messages", "thanhcong");
        return "admin/event/show-event";
    }




}
