package vn.edu.poly.beecinema.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.poly.beecinema.entity.Quyen;
import vn.edu.poly.beecinema.service.QuyenService;

import java.util.List;

@Controller
@RequestMapping("/admin/permission")
public class PermissionController {
    @Autowired private QuyenService quyenService;

    @GetMapping("/show-permission")
    public String showPermission(Model model){
        List<Quyen> quyen = quyenService.getAllQuyen();
        model.addAttribute("quyen", quyen);
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
