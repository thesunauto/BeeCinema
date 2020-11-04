package vn.edu.poly.beecinema.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.edu.poly.beecinema.entity.DoTuoi;
import vn.edu.poly.beecinema.service.DoTuoiService;
import vn.edu.poly.beecinema.service.TaikhoanService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/age")
public class AgeController {
    @Autowired private DoTuoiService doTuoiService;
    @Autowired private TaikhoanService taikhoanService;
    @GetMapping("/show-age")
    public String showAge(Model model){
        List<DoTuoi> doTuoi = doTuoiService.getAllDoTuoi();
        model.addAttribute("doTuoi", doTuoi);
        return "admin/age/show-age";
    }

    @RequestMapping("/add-age")
    public String addAge(Model model){
    model. addAttribute("doTuoi",new DoTuoi());
        return "admin/age/add-age";
    }

    @GetMapping (value = "/edit")
    public String editAge(Model model , @RequestParam ("id") String doTuoiID){
        Optional<DoTuoi> DoTuoiEdit = doTuoiService.findDoTuoiById(doTuoiID);
        DoTuoiEdit.ifPresent(doTuoi -> model.addAttribute("doTuoi", doTuoi));
        return "admin/age/update-age";
    }
    @PostMapping("/add-age")
    public String saveAge(@Valid @ModelAttribute ("doTuoi") DoTuoi doTuoi , BindingResult bindingResult,
                          @ModelAttribute ("id") String idDoTuoi,
                          Model model, Authentication authentication){
        if (bindingResult.hasErrors()){

        }else if(doTuoiService.findDoTuoiById(idDoTuoi).isPresent()){
            model.addAttribute("messages" ,"TrungID" );
        }else{
            doTuoi.setNgaytao(LocalDateTime.now());
            doTuoi.setTaikhoan(taikhoanService.findTaikhoanById(authentication.getName()).get());
            doTuoiService.saveDoTuoi(doTuoi);
            model.addAttribute("messages" , "ThanhCong");
        }
        return "/admin/age/add-age";
    }

    @PostMapping (value = "/edit")
    public String updateAge(@Valid @ModelAttribute ("doTuoi") DoTuoi doTuoi,
                            BindingResult bindingResult, Model model, Authentication authentication){
        if (bindingResult.hasErrors()){

        }else {
            doTuoiService.saveDoTuoi(doTuoi);
            model.addAttribute("messages","ThanhCong");
        }
        return "/admin/age/update-age";
    }

    @RequestMapping (value = "/delete")
    public String deleteUser (@RequestParam("id") String doTuoiID, Model model){
        doTuoiService.deleteDoTuoi(doTuoiID);
        List<DoTuoi> doTuoi =  doTuoiService.getAllDoTuoi();
        model.addAttribute("doTuoi" , doTuoi);
        model.addAttribute("messages" , "thanhCong");
        return "/admin/age/show-age";
    }
}
