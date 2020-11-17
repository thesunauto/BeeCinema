package vn.edu.poly.beecinema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.poly.beecinema.commons.VeResponse;
import vn.edu.poly.beecinema.service.PhimService;
import vn.edu.poly.beecinema.service.SuatChieuService;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired private PhimService phimService;
    @Autowired private SuatChieuService suatChieuService;
    @GetMapping("/chonphim")
    public String chonphim(){
        return "employee/chonPhim";
    }
    @GetMapping("/lichsu")
    public String chonGhe(){
        return "employee/lichsu";
    }
    @GetMapping("/xacnhanve")
    public String xacNhanVe(Model model){model.addAttribute("localTime", LocalTime.now());
        return "employee/xacnhanve";
    }

    @GetMapping("/datghe/{id}")
    public String datghe(HttpSession httpSession,Model model, @PathVariable String id){
        if(httpSession.getAttribute("veresponse")==null){
            httpSession.setAttribute("veresponse", new ArrayList<VeResponse>());
        }
        List<VeResponse> veResponses = (List<VeResponse>) httpSession.getAttribute("veresponse");
        httpSession.setAttribute("veresponse",veResponses);
        model.addAttribute("film", phimService.findPhimById(id));
        model.addAttribute("suatchieu", suatChieuService.getAllSuatChieuByPhimAndToday(id));
        return "employee/datghe";
    }


}
