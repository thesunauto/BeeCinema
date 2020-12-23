package vn.edu.poly.beecinema.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.poly.beecinema.entity.Sukien;
import vn.edu.poly.beecinema.service.SukienService;
import vn.edu.poly.beecinema.service.TaikhoanService;

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
@RequestMapping("/admin/event")
public class EventController {
    @Autowired private SukienService sukienService;
    @Autowired private TaikhoanService taiKhoanService;

    @GetMapping("/show-event")
    public String showEvent(Model model){
        String keyword = null;
        String messages = null;
        return listByPage(model, 1, "id", "asc", keyword, messages);
    }

    @GetMapping("/page/{pageNumber}")
    public String listByPage(Model model ,
                             @PathVariable("pageNumber") int currentPage,
                             @Param("sortField") String sortField,
                             @Param("sortDir") String sortDir,
                             @Param("keyword") String keyword,
                             String messages) {
        Page<Sukien> page = sukienService.listAll(currentPage, sortField, sortDir, keyword);
        long totalItem = page.getTotalElements();
        int totalPages = page.getTotalPages();
        List<Sukien> sukiens = page.getContent();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItem", totalItem);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("sukiens", sukiens);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("keyword", keyword);
        model.addAttribute("messages", messages);
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
                            @ModelAttribute("id") String idSukien, @RequestParam("images") MultipartFile images,
                            Model model, Authentication authentication){
        if(bindingResult.hasErrors()){

        }else if(sukienService.findSukienById(idSukien).isPresent()){
            model.addAttribute("messages", "trungid");
        }else{
            sukien.setHinhanh("null");
            sukien.setTaikhoan(taiKhoanService.findTaikhoanById(authentication.getName()).get());
            if(!images.isEmpty()) {
                Path path = Paths.get("uploads/");
                try {
                    InputStream inputStream = images.getInputStream();
                    Files.copy(inputStream, path.resolve(images.getOriginalFilename()),
                            StandardCopyOption.REPLACE_EXISTING);
                    sukien.setHinhanh(images.getOriginalFilename().toLowerCase());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            sukienService.saveSukien(sukien);
            return listByPage(model, 1, "id", "asc", null, "themThanhCong");
        }
        return "admin/event/add-event";
    }

    @PostMapping(value = "/edit")
    public String updateEvent(@Valid @ModelAttribute("sukien") Sukien sukien ,
                                  BindingResult bindingResult, @RequestParam("images") MultipartFile images,
                                 Model model,  Authentication authentication){
        if(bindingResult.hasErrors()){

        }else{
            if(!images.isEmpty()) {
                Path path = Paths.get("uploads/");
                try {
                    InputStream inputStream = images.getInputStream();
                    Files.copy(inputStream, path.resolve(images.getOriginalFilename()),
                            StandardCopyOption.REPLACE_EXISTING);
                    sukien.setHinhanh(images.getOriginalFilename().toLowerCase());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            sukienService.saveSukien(sukien);
            return listByPage(model, 1, "id", "asc", null, "suaThanhCong");
        }
        return "admin/event/update-event";
    }

    @RequestMapping(value = "/delete" )
    public String deleteEvent(@RequestParam("id") String sukienId, Model model) {
        Sukien sukien = sukienService.findSukienById(sukienId).get();
        if(sukien.getVeonlines().isEmpty()&&sukien.getVes().isEmpty()){
            sukienService.deleteSukien(sukienId);
            return listByPage(model, 1, "id", "asc", null, "xoaThanhCong");
        }else {
            return listByPage(model, 1, "id", "asc", null, "dacove");
        }

    }




}
