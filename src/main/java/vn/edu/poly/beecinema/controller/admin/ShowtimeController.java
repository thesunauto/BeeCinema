package vn.edu.poly.beecinema.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.edu.poly.beecinema.entity.Khunggio;
import vn.edu.poly.beecinema.entity.LoaiPhim;
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



    @GetMapping("/show-showtime")
    public String showMovieType(Model model){
        List<Khunggio> khungGio = khungGioService.getAllKhungGio();
        model.addAttribute("khungGio", khungGio);

        return "admin/showtime/show-showtime";
    }

    @RequestMapping("/add-showtime")
    public String addMovieType(Model model){
        model.addAttribute("khungGio", new Khunggio());
        return "admin/showtime/add-showtime";
    }

    @GetMapping(value = "/edit")
    public String editMovieType(Model model, @RequestParam("id") String khungGioId){
        Optional<Khunggio> khungGioEdit = khungGioService.findKhungGioById(khungGioId);
        khungGioEdit.ifPresent(khungGio -> model.addAttribute("khungGio", khungGio));
        return "admin/showtime/update-showtime";
    }

    @PostMapping("/add-showtime")
    public String saveMovieType(@Valid @ModelAttribute("khungGio") Khunggio khungGio , BindingResult bindingResult,
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
    public String updateMovieType(@Valid @ModelAttribute("khungGio") Khunggio khungGio ,
                                  BindingResult bindingResult, Model model,  Authentication authentication){
        if(bindingResult.hasErrors()){

        }else{
            khungGioService.saveKhungGio(khungGio);
            model.addAttribute("messages", "thanhcong");
        }
        return "admin/showtime/update-showtime";
    }

    @RequestMapping(value = "/delete" )
    public String deleteUser(@RequestParam("id") String khungGioId, Model model) {
        khungGioService.deleteKhungGio(khungGioId);
        List<Khunggio> khungGio = khungGioService.getAllKhungGio();
        model.addAttribute("khungGio", khungGio);
        model.addAttribute("messages", "thanhcong");
        return "admin/showtime/show-showtime";
    }





}

