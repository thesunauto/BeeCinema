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
import vn.edu.poly.beecinema.entity.Ghe;
import vn.edu.poly.beecinema.entity.Loaighe;
import vn.edu.poly.beecinema.entity.Phong;
import vn.edu.poly.beecinema.entity.Sukien;
import vn.edu.poly.beecinema.service.PhongService;
import vn.edu.poly.beecinema.service.TaikhoanService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/admin/room")
public class RoomController {
    @Autowired private PhongService phongService;
    @Autowired private TaikhoanService taikhoanService;

    @GetMapping("/show-room")
    public String showRoom(Model model){
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
        Page<Phong> page = phongService.listAll(currentPage, sortField, sortDir, keyword);
        long totalItem = page.getTotalElements();
        int totalPages = page.getTotalPages();
        List<Phong> phong = page.getContent();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItem", totalItem);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("phong", phong);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("keyword", keyword);
        model.addAttribute("messages", messages);
        return "admin/room/show-room";
    }

    @GetMapping("/add-room")
    public String addRoom(Model model){
        Phong phong = new Phong();
        model.addAttribute("room", phong);
        return "admin/room/add-room";
    }

    @PostMapping("/add-room")
    public String saveRoom(@Valid @ModelAttribute(value = "room") Phong phong,
                           BindingResult bindingResult, @ModelAttribute("id") String idPhong,
                           Authentication authentication, Model model){
        if(bindingResult.hasErrors()){

        }else if(phongService.findPhongById(idPhong).isPresent()){
            model.addAttribute("messages", "trungid");
        }else{
            phong.setNgaytao(LocalDateTime.now());
            phong.setTaikhoan(taikhoanService.findTaikhoanById(authentication.getName()).get());
            phongService.savePhong(phong);
            return listByPage(model, 1, "id", "asc", null, "themThanhCong");
        }
        return "admin/room/add-room";
    }
    @GetMapping("/update-room/{id}")
    public String findRoom(Model model, @PathVariable(value = "id") String id){
        Phong phong =  phongService.findPhongById(id).get();
        model.addAttribute("room",phong);
        return "admin/room/update-room";
    }

    @PostMapping("/update-room")
    public String updateRoom(@Valid @ModelAttribute(value = "room") Phong phong,
                             BindingResult bindingResult,
                             Authentication authentication, Model model){
        if(bindingResult.hasErrors()){

        }else{
            phongService.savePhong(phong);
            return listByPage(model, 1, "id", "asc", null, "suaThanhCong");
        }
        return "admin/room/update-room";
    }

    @GetMapping("/delete-room/{id}")
    public  String deleteroom (@PathVariable(value = "id") String id, Model model){
        phongService.deletePhong(id);
        return listByPage(model, 1, "id", "asc", null, "xoaThanhCong");
    }




}
