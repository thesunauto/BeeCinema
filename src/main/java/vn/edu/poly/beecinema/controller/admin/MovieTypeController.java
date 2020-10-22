package vn.edu.poly.beecinema.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/movie-type")
public class MovieTypeController {
    @RequestMapping("/show-movie-type")
    public String showMovieType(Model model){

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
