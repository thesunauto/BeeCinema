package vn.edu.poly.beecinema.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.edu.poly.beecinema.entity.Phim;
import vn.edu.poly.beecinema.service.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/movie")
public class MovieController {
    @Autowired private PhimService phimService;
    @Autowired private TaikhoanService taikhoanService;

//    @GetMapping("/show-movie")
//    public String showMovie(Model model){
//        List<Phim> phim = phimService.getAllPhim();
//        model.addAttribute("phim", phim);
//        return "admin/movie/show-movie";
//    }

    @GetMapping("/show-movie")
    public String showMovie(Model model){
        String keyword = null;
        return listByPage(model, 1, "id", "asc", keyword);
    }

    @GetMapping("/page/{pageNumber}")
    public String listByPage(Model model ,
                             @PathVariable("pageNumber") int currentPage,
                             @Param("sortField") String sortField,
                             @Param("sortDir") String sortDir,
                             @Param("keyword") String keyword) {
        Page<Phim> page = phimService.listAll(currentPage, sortField, sortDir, keyword);
        long totalItem = page.getTotalElements();
        int totalPages = page.getTotalPages();
        List<Phim> phim = page.getContent();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItem", totalItem);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("phim", phim);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("keyword", keyword);
        return "admin/movie/show-movie";
    }

    @RequestMapping("/add-movie")
    public String addMovie(Model model){
        model.addAttribute("phim", new Phim());
        return "admin/movie/add-movie";
    }

    @GetMapping(value = "/edit")
    public String editMovie(Model model, @RequestParam("id") String phimId){
        Optional<Phim> phimEdit = phimService.findPhimById(phimId);
        phimEdit.ifPresent(phim -> model.addAttribute("phim", phim));
        return "admin/movie/update-movie";
    }

    @PostMapping("/add-movie")
    public String saveMovie(@Valid @ModelAttribute("phim") Phim phim, BindingResult bindingResult,
                            @ModelAttribute("id") String idPhim,
                            Model model, Authentication authentication){
        if(bindingResult.hasErrors()){

        }else if(phimService.findPhimById(idPhim).isPresent()){
            model.addAttribute("messages", "trungid");
        }else{
            phim.setHinhanh("a.jpg");
            phim.setTaikhoan(taikhoanService.findTaikhoanById(authentication.getName()).get());
            phimService.savePhim(phim);
            model.addAttribute("messages", "thanhcong");
        }
        return "admin/movie/add-movie";
    }

    @PostMapping(value = "/edit")
    public String updateMovie(@Valid @ModelAttribute("phim") Phim phim ,
                              BindingResult bindingResult, Model model,  Authentication authentication){
        if(bindingResult.hasErrors()){

        }else{
            phim.setHinhanh("a.jpg");
            phimService.savePhim(phim);
            model.addAttribute("messages", "thanhcong");
        }
        return "admin/movie/update-movie";
    }

    @RequestMapping(value = "/delete" )
    public String deleteMovie(@RequestParam("id") String phimId, Model model) {
        phimService.deletePhim(phimId);
        List<Phim> phim = phimService.getAllPhim();
        model.addAttribute("phim", phim);
        model.addAttribute("messages", "thanhcong");
        return "admin/movie/show-movie";
    }



}
