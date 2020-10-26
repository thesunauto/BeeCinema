package vn.edu.poly.beecinema.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.poly.beecinema.entity.LoaiPhim;
import vn.edu.poly.beecinema.service.LoaiPhimService;

import java.util.List;

@Controller
@RequestMapping("/admin/movie-type")
public class MovieTypeController {
    @Autowired private LoaiPhimService loaiPhimService;

    @GetMapping("/show-movie-type")
    public String showMovieType(Model model){
        List<LoaiPhim> loaiPhim = loaiPhimService.getAllLoaiPhim();
        model.addAttribute("loaiPhim", loaiPhim);
        return "admin/movie-type/show-movie-type";
    }

    @RequestMapping("/add-movie-type")
    public String addMovieType(Model model){

        return "admin/movie-type/add-movie-type";
    }

    @RequestMapping("/update-movie-type")
    public String updateMovieType(Model model){

        return "admin/movie-type/update-movie-type";
    }





}
