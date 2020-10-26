package vn.edu.poly.beecinema.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.poly.beecinema.entity.Binhluan;
import vn.edu.poly.beecinema.entity.Sukien;
import vn.edu.poly.beecinema.service.SukienService;

import java.util.List;

@Controller
@RequestMapping("/admin/event")
public class EventController {
    @Autowired
    private SukienService sukienService;
    @RequestMapping("/show-event")
    public String showEvent(Model model){
        List<Sukien> sukiens = sukienService.getAllSukien();

        model.addAttribute("sukiens", sukiens);
        return "admin/event/show-event";
    }

    @RequestMapping("/add-event")
    public String addEvent(Model model){
        model.addAttribute("sukien", new Sukien());
        return "admin/event/add-event";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String saveEvent(Sukien sukien) {
        sukienService.saveSukien(sukien);
        return "redirect:/";
    }

    @RequestMapping("/update-event")
    public String updateEvent(Model model){

        return "admin/event/update-event";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteSukien(@RequestParam("id") String sukienId, Model model) {
        sukienService.deleteSukien(sukienId);
        return "redirect:/";
    }




}
