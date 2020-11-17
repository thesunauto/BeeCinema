package vn.edu.poly.beecinema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.poly.beecinema.entity.Phim;
import vn.edu.poly.beecinema.entity.Sukien;
import vn.edu.poly.beecinema.service.PhimService;
import vn.edu.poly.beecinema.service.SukienService;

import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private PhimService phimService;
    @Autowired
    private SukienService suKienService;
    @GetMapping("/detail-film/{id}")
    public String detailfilm(Model model, @PathVariable(value = "id") String id){
        Phim phim = phimService.findPhimById(id).get();
        model.addAttribute("phim", phim);
        return "client/detail-film";
    }
    @GetMapping("/list-film")
    public String listfilm(Model model){
        List <Phim> phim = phimService.getAllPhim();
        model.addAttribute("phim", phim);
        return "client/list-film";
    }
    @GetMapping("/select-seat")
    public String selectseat(){
        return "client/select-seat";
    }
    @GetMapping("/about-us")
    public String aboutus(){
        return "client/about-us";
    }
    @GetMapping("/contact-us")
    public String contactus(){
        return "client/contact-us";
    }
    @GetMapping("/list-event")
    public String envent(Model model){
        List <Sukien> suKien = suKienService.getAllSukien();
        model.addAttribute("suKien", suKien);
        return "client/list-event";
    }
}