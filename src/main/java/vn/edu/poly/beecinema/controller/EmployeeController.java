package vn.edu.poly.beecinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @GetMapping("/datve")
    public String datVe(){
        return "employee/datve";
    }
    @GetMapping("/chonghe")
    public String chonGhe(){
        return "employee/chonghe";
    }
    @GetMapping("/xacnhanve")
    public String xacNhanVe(){
        return "employee/xacnhanve";
    }
}
