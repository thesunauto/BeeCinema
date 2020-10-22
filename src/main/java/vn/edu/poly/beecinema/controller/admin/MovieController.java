package vn.edu.poly.beecinema.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/movie")
public class MovieController {
    @RequestMapping("/show-movie")
    public String showMovie(Model model){

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
