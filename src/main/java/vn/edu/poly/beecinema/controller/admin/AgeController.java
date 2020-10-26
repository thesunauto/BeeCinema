package vn.edu.poly.beecinema.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.poly.beecinema.entity.DoTuoi;
import vn.edu.poly.beecinema.service.DoTuoiService;

import java.util.List;

@Controller
@RequestMapping("/admin/age")
public class AgeController {
    @Autowired private DoTuoiService doTuoiService;

    @GetMapping("/show-age")
    public String showAge(Model model){
        List<DoTuoi> doTuoi = doTuoiService.getAllDoTuoi();
        model.addAttribute("doTuoi", doTuoi);
        return "admin/age/show-age";
    }

    @RequestMapping("/add-age")
    public String addAge(Model model){

        return "admin/age/add-age";
    }

    @RequestMapping("/update-age")
    public String updateAge(Model model){

        return "admin/age/update-age";
    }





}
