package vn.edu.poly.beecinema.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.edu.poly.beecinema.entity.DoTuoi;
import vn.edu.poly.beecinema.entity.Taikhoan;
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

//    @GetMapping("/show-age")
//    public String showAge(Model model){
//        List<DoTuoi> doTuoi = doTuoiService.getAllDoTuoi();
//        model.addAttribute("doTuoi", doTuoi);
//        return "admin/age/show-age";
//    }

    @GetMapping("/show-age")
    public String showAge(Model model){
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
        Page<DoTuoi> page = doTuoiService.listAll(currentPage, sortField, sortDir, keyword);
        long totalItem = page.getTotalElements();
        int totalPages = page.getTotalPages();
        List<DoTuoi> doTuoi = page.getContent();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItem", totalItem);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("doTuoi", doTuoi);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("keyword", keyword);
        model.addAttribute("messages", messages);
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
            return listByPage(model, 1, "id", "asc", null, "themThanhCong");
        }
        return "/admin/age/add-age";
    }

    @PostMapping (value = "/edit")
    public String updateAge(@Valid @ModelAttribute ("doTuoi") DoTuoi doTuoi,
                            BindingResult bindingResult, Model model, Authentication authentication){
        if (bindingResult.hasErrors()){

        }else {
            doTuoiService.saveDoTuoi(doTuoi);
            return listByPage(model, 1, "id", "asc", null, "suaThanhCong");
        }
        return "/admin/age/update-age";
    }

    @RequestMapping (value = "/delete")
    public String deleteUser (@RequestParam("id") String doTuoiID, Model model){
        doTuoiService.deleteDoTuoi(doTuoiID);
        return listByPage(model, 1, "id", "asc", null, "xoaThanhCong");
    }
}
