package vn.edu.poly.beecinema.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.poly.beecinema.entity.Binhluan;
import vn.edu.poly.beecinema.service.BinhluanService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/comment")
public class CommentController {
    @Autowired
    private BinhluanService binhluanService;
    @RequestMapping("/show-comment")
    public String showComment(Model model){
        List<Binhluan> binhluans = binhluanService.getAllBinhluan();

        model.addAttribute("binhluans", binhluans);
        return "admin/comment/show-comment";
    }

    @RequestMapping("/add-comment")
    public String addComment(Model model){
        model.addAttribute("binhluan", new Binhluan());
        return "admin/comment/add-comment";
    }
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String saveComment(Binhluan binhluan) {
        binhluanService.saveBinhluan(binhluan);
        return "redirect:/";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String updateComment(@RequestParam("id") Integer binhluanId, Model model){
        Optional<Binhluan> binhluanEdit = binhluanService.findBinhluanById(binhluanId);
        binhluanEdit.ifPresent(binhluan -> model.addAttribute("binhluan", binhluan));
        return "admin/comment/update-comment";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteConment(@RequestParam("id") Integer binhluanId, Model model) {
        binhluanService.deleteBinhluan(binhluanId);
        return "redirect:/";
    }




}
