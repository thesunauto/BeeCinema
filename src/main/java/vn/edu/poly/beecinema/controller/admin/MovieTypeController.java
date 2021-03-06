package vn.edu.poly.beecinema.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.edu.poly.beecinema.entity.LoaiPhim;
import vn.edu.poly.beecinema.service.LoaiPhimService;
import vn.edu.poly.beecinema.service.TaikhoanService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/movie-type")
public class MovieTypeController {
    @Autowired private LoaiPhimService loaiPhimService;
    @Autowired private TaikhoanService taiKhoanService;

    @GetMapping("/show-movie-type")
    public String showMovieType(Model model){
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
        Page<LoaiPhim> page = loaiPhimService.listAll(currentPage, sortField, sortDir, keyword);
        long totalItem = page.getTotalElements();
        int totalPages = page.getTotalPages();
        List<LoaiPhim> loaiPhim = new ArrayList<>();
        for(LoaiPhim loaiPhim1: page.getContent()){
            loaiPhim1.setMota((loaiPhim1.getMota().length()>60)?loaiPhim1.getMota().substring(0,60)+"...":loaiPhim1.getMota());
            loaiPhim.add(loaiPhim1);
        }
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItem", totalItem);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("loaiPhim", loaiPhim);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("keyword", keyword);
        model.addAttribute("messages", messages);
        return "admin/movie-type/show-movie-type";
    }

    @RequestMapping("/add-movie-type")
    public String addMovieType(Model model){
        model.addAttribute("loaiPhim", new LoaiPhim());
        return "admin/movie-type/add-movie-type";
    }

    @GetMapping(value = "/edit")
    public String editMovieType(Model model, @RequestParam("id") String loaiPhimId){
        Optional<LoaiPhim> loaiPhimEdit = loaiPhimService.findLoaiPhimById(loaiPhimId);
        loaiPhimEdit.ifPresent(loaiPhim -> model.addAttribute("loaiPhim", loaiPhim));
        return "admin/movie-type/update-movie-type";
    }

    @PostMapping("/add-movie-type")
    public String saveMovieType(@Valid @ModelAttribute("loaiPhim") LoaiPhim loaiPhim, BindingResult bindingResult,
                                @ModelAttribute("id") String idLoaiPhim,
                                 Model model,  Authentication authentication){
        if(bindingResult.hasErrors()){

        }else if(loaiPhimService.findLoaiPhimById(idLoaiPhim).isPresent()){
            model.addAttribute("messages", "trungid");
        }else{
            loaiPhim.setNgaytao(LocalDateTime.now());
            loaiPhim.setTaikhoan(taiKhoanService.findTaikhoanById(authentication.getName()).get());
            loaiPhim.setTrangthai(0);
            loaiPhimService.saveLoaiPhim(loaiPhim);
            return listByPage(model, 1, "id", "asc", null, "themThanhCong");
        }
        return "admin/movie-type/add-movie-type";
    }

    @PostMapping(value = "/edit")
    public String updateMovieType(@Valid @ModelAttribute("loaiPhim") LoaiPhim loaiPhim ,
                                  BindingResult bindingResult, Model model,  Authentication authentication){
        if(bindingResult.hasErrors()){

        }else{
            loaiPhim.setTrangthai(0);
            loaiPhimService.saveLoaiPhim(loaiPhim);
            return listByPage(model, 1, "id", "asc", null, "suaThanhCong");
        }
        return "admin/movie-type/update-movie-type";
    }

    @RequestMapping(value = "/delete" )
    public String deleteUser(@RequestParam("id") String loaiPhimId, Model model) {
        LoaiPhim loaiPhim = loaiPhimService.findLoaiPhimById(loaiPhimId).get();
        if(loaiPhim.getPhims().isEmpty()){
            loaiPhimService.deleteLoaiPhim(loaiPhimId);
            return listByPage(model, 1, "id", "asc", null, "xoaThanhCong");
        }else {
            return listByPage(model, 1, "id", "asc", null, "dachuaphim");
        }




    }







}
