package vn.edu.poly.beecinema.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/permission")
public class PermissionController {
    @RequestMapping("/show-permission")
    public String showPermission(Model model){

        return "admin/permission/show-permission";
    }

    @RequestMapping("/add-permission")
    public String addPermission(Model model){

        return "admin/permission/add-permission";
    }

    @RequestMapping("/update-permission")
    public String updatePermission(Model model){

        return "admin/permission/update-permission";
    }





}
