package vn.edu.poly.beecinema.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.edu.poly.beecinema.entity.Taikhoan;
import vn.edu.poly.beecinema.service.TaikhoanService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/user")
public class UserController {
    @Autowired
    private TaikhoanService taikhoanService;

    @GetMapping("/show-user")
    public String showUser(Model model){
        List<Taikhoan> taikhoan = taikhoanService.getAllTaikhoan();
        model.addAttribute("taikhoans", taikhoan);
        return "admin/account/show-account";
    }

    @RequestMapping("/add-user")
    public String addUser(Model model){
        model.addAttribute("taikhoan", new Taikhoan());
        return "admin/account/add-account";
    }

    @GetMapping(value = "/edit")
    public String editUser(Model model, @RequestParam("id") String taikhoanId){
        Optional<Taikhoan> taikhoanEdit = taikhoanService.findTaikhoanById(taikhoanId);
        taikhoanEdit.ifPresent(taikhoan -> model.addAttribute("taikhoan", taikhoan));
        return "admin/account/update-account";
    }
    @PostMapping("/add-user")
    public String saveUser(@Valid @ModelAttribute("taikhoan") Taikhoan taikhoan, BindingResult bindingResult,
                           @ModelAttribute("id") String usernameTaikhoan,
                           Model model, Authentication authentication){
        if(bindingResult.hasErrors()){

        }else if(taikhoanService.findTaikhoanById(usernameTaikhoan).isPresent()){
            model.addAttribute("messages", "trungid2");
        }else{
            taikhoan.setNgaytao(LocalDateTime.now());
            taikhoan.setHinhanh("a.jpg");
            taikhoanService.saveTaikhoan(taikhoan);
            model.addAttribute("messages", "thanhcong");
        }
        return "admin/account/add-account";
    }

    @PostMapping(value = "/edit")
    public String updateUser(@ModelAttribute("taikhoan") Taikhoan taikhoan ,
                              BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){

        }else{
            taikhoan.setHinhanh("a.jpg");
            taikhoan.setNgaytao(LocalDateTime.now());
            taikhoanService.saveTaikhoan(taikhoan);
            model.addAttribute("messages", "thanhcong");
        }
        return "admin/account/update-account";
    }

    @RequestMapping(value = "/delete" )
    public String deleteUser(@RequestParam("id") String taikhoanId, Model model) {
        taikhoanService.deleteTaikhoan(taikhoanId);
        List<Taikhoan> taikhoans = taikhoanService.getAllTaikhoan();
        model.addAttribute("taikhoans", taikhoans);
        model.addAttribute("messages", "thanhcong");
        return "admin/account/show-account";
    }





}
