package vn.edu.poly.beecinema.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/comment")
public class CommentController {
    @RequestMapping("/show-comment")
    public String showComment(Model model){

        return "admin/comment/show-comment";
    }

    @RequestMapping("/add-comment")
    public String addComment(Model model){

        return "admin/comment/add-comment";
    }

    @RequestMapping("/update-comment")
    public String updateComment(Model model){

        return "admin/comment/update-comment";
    }





}
