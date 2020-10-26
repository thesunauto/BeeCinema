package vn.edu.poly.beecinema.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.poly.beecinema.entity.Phim;
import vn.edu.poly.beecinema.service.LoaiPhimService;
import vn.edu.poly.beecinema.service.PhimService;


import java.util.List;

@Controller
@RequestMapping("/admin/movie")
public class MovieController {
    @Autowired private PhimService phimService;

    @GetMapping("/show-movie")
    public String showMovie(Model model){
        List<Phim> phim = phimService.getAllPhim();
        model.addAttribute("phim", phim);
        return "admin/movie/show-movie";
    }

    @RequestMapping("/add-movie")
    public String addMovie(Model model){

        return "admin/movie/add-movie";
    }

    @RequestMapping("/update-movie")
    public String updateMovie(Model model){

        return "admin/movie/update-movie";
    }





}
