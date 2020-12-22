package vn.edu.poly.beecinema.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.poly.beecinema.entity.Taikhoan;
import vn.edu.poly.beecinema.service.TaikhoanService;

import javax.validation.Valid;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/user")
public class UserController {
    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;
    @Autowired
    private TaikhoanService taikhoanService;

    public UserController(InMemoryUserDetailsManager inMemoryUserDetailsManager) {
        this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
    }
    @GetMapping("/dashboard")
    public String indexdashboard() {

        return "admin/dashboards/dashboards";
    }
    @GetMapping("/show-user")
    public String showUser(Model model,Authentication authentication){
        String keyword = null;
        return listByPage(model,authentication, 1, "username", "asc", keyword, null);
    }

    @GetMapping("/page/{pageNumber}")
    public String listByPage(Model model , Authentication authentication,
                             @PathVariable("pageNumber") int currentPage,
                             @Param("sortField") String sortField,
                             @Param("sortDir") String sortDir,
                             @Param("keyword") String keyword,
                             String messages) {
        String username = authentication.getName();
        Page<Taikhoan> page = taikhoanService.listAll(username,currentPage, sortField, sortDir, keyword);
        long totalItem = page.getTotalElements();
        int totalPages = page.getTotalPages();
        List<Taikhoan> taikhoans = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItem", totalItem);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("taikhoans", taikhoans);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("keyword", keyword);
        model.addAttribute("messages", messages);
        return "admin/account/show-account";
    }

    @RequestMapping("/add-user")
    public String addUser(Model model){
        model.addAttribute("taikhoan", new Taikhoan());
        return "admin/account/add-account";
    }

    @GetMapping(value = "/edit")
    public String editUser(Model model, @RequestParam("id") String taikhoanId){
        Optional<Taikhoan> taikhoanEdit = taikhoanService.findTaikhoanById(taikhoanId);
        taikhoanEdit.ifPresent(taikhoan -> model.addAttribute("taikhoan", taikhoan));
        return "admin/account/update-account";
    }
    @PostMapping("/add-user")
    public String saveUser(@Valid @ModelAttribute("taikhoan") Taikhoan taikhoan, BindingResult bindingResult,
                           @ModelAttribute("username") String idTaikhoan, @RequestParam("images") MultipartFile images,
                           Model model, Authentication authentication){
        if(bindingResult.hasErrors()){

        }else if(taikhoanService.findTaikhoanById(idTaikhoan).isPresent()){
            model.addAttribute("messages", "trungid");
        }else{
            taikhoan.setNgaytao(LocalDateTime.now());
            taikhoan.setHinhanh("null");
            if(!images.isEmpty()) {
                Path path = Paths.get("uploads/");
                try {
                    InputStream inputStream = images.getInputStream();
                    Files.copy(inputStream, path.resolve(images.getOriginalFilename()),
                            StandardCopyOption.REPLACE_EXISTING);
                    taikhoan.setHinhanh(images.getOriginalFilename().toLowerCase());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            taikhoan.setTrangthai(0);
            taikhoanService.saveTaikhoan(taikhoan);
            inMemoryUserDetailsManager.createUser(User.withDefaultPasswordEncoder().username(taikhoan.getUsername()).password(taikhoan.getMatkhau()).roles(taikhoan.getQuyen().getTen()).build());

            return listByPage(model,authentication, 1, "username", "asc", null, "themThanhCong");
        }
        return "admin/account/add-account";
    }

    @PostMapping(value = "/edit")
    public String updateUser(@Valid @ModelAttribute("taikhoan") Taikhoan taikhoan ,Authentication authentication,
                              BindingResult bindingResult, Model model,
                             @RequestParam("images") MultipartFile images){
        taikhoan.setTrangthai(0);
        if(bindingResult.hasErrors()){

        }else{
            if(!images.isEmpty()) {
                Path path = Paths.get("uploads/");
                try {
                    InputStream inputStream = images.getInputStream();
                    Files.copy(inputStream, path.resolve(images.getOriginalFilename()),
                            StandardCopyOption.REPLACE_EXISTING);
                    taikhoan.setHinhanh(images.getOriginalFilename().toLowerCase());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            taikhoanService.saveTaikhoan(taikhoan);
            inMemoryUserDetailsManager.deleteUser(taikhoan.getUsername());
            inMemoryUserDetailsManager.createUser(User.withDefaultPasswordEncoder().username(taikhoan.getUsername()).password(taikhoan.getMatkhau()).roles(taikhoan.getQuyen().getTen()).build());

            return listByPage(model,authentication, 1, "username", "asc", null, "suaThanhCong");
        }
        return "admin/account/update-account";
    }

    @RequestMapping(value = "/delete" )
    public String deleteUser(@RequestParam("id") String taikhoanId, Model model,Authentication authentication) {
        taikhoanService.deleteTaikhoan(taikhoanId);
        return listByPage(model,authentication, 1, "username", "asc", null, "xoaThanhCong");
    }





}
