package vn.edu.poly.beecinema.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.poly.beecinema.entity.Binhluan;
import vn.edu.poly.beecinema.entity.LoaiPhim;
import vn.edu.poly.beecinema.service.BinhluanService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/comment")
public class CommentController {
    @Autowired
    private BinhluanService binhluanService;

    @GetMapping("/show-comment")
    public String showComment(Model model){
        String keyword = null;
        return listByPage(model, 1, "id", "asc", keyword);
    }

    @GetMapping("/page/{pageNumber}")
    public String listByPage(Model model ,
                             @PathVariable("pageNumber") int currentPage,
                             @Param("sortField") String sortField,
                             @Param("sortDir") String sortDir,
                             @Param("keyword") String keyword) {
        Page<Binhluan> page = binhluanService.listAll(currentPage, sortField, sortDir, keyword);
        long totalItem = page.getTotalElements();
        int totalPages = page.getTotalPages();
        List<Binhluan> binhluans = page.getContent();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItem", totalItem);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("binhluans", binhluans);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("keyword", keyword);
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

    @RequestMapping(value = "/antrangthai", method = RequestMethod.GET)
    public String anConment(@RequestParam("id") Integer binhluanId, Model model) {
       Optional<Binhluan> binhluanEdit = binhluanService.findBinhluanById(binhluanId);
//       Binhluan binhluan ;
//        binhluanEdit.ifPresent();
//        binhluanService.saveBinhluan(binhluan);
        return "redirect:/";
    }




}
