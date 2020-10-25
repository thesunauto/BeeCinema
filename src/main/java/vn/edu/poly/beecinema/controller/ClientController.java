package vn.edu.poly.beecinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/client")
public class ClientController {
    @GetMapping("/detail-film")
    public String detailfilm(){
        return "client/detail-film";
    }
    @GetMapping("/list-film")
    public String listfilm(){
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
    public String envent(){
        return "client/list-event";
    }
}