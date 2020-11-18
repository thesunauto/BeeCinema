package vn.edu.poly.beecinema.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.poly.beecinema.entity.LoaiPhim;
import vn.edu.poly.beecinema.entity.Taikhoan;
import vn.edu.poly.beecinema.service.LoaiPhimService;
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
@RequestMapping("/admin/profile")
public class ProfileController {
    @Autowired private TaikhoanService taikhoanService;


    @GetMapping(value = "/update-profile")
    public String editProfileType(Model model, Authentication authentication){
        Optional<Taikhoan> taiKhoanEdit = taikhoanService.findTaikhoanById(taikhoanService.findTaikhoanById(authentication.getName()).get().getUsername());
        taiKhoanEdit.ifPresent(taikhoan -> model.addAttribute("taikhoan", taikhoan));
        return "admin/profile/update-profile";
    }

    @PostMapping(value = "/update-profile")
    public String updateProfileType( @ModelAttribute("taikhoan") Taikhoan taikhoan,
                                  Model model,  Authentication authentication, @RequestParam("images") MultipartFile images){

            taikhoan.setMatkhau(taikhoanService.findTaikhoanById(authentication.getName()).get().getMatkhau());
            taikhoan.setNgaytao(taikhoanService.findTaikhoanById(authentication.getName()).get().getNgaytao());
            taikhoan.setQuyen(taikhoanService.findTaikhoanById(authentication.getName()).get().getQuyen());
            taikhoan.setHinhanh(taikhoanService.findTaikhoanById(authentication.getName()).get().getHinhanh());
            taikhoan.setTrangthai(taikhoanService.findTaikhoanById(authentication.getName()).get().getTrangthai());
            if(!images.isEmpty()){
                Path path = Paths.get("uploads/");
                try{
                    InputStream inputStream = images.getInputStream();
                    Files.copy(inputStream, path.resolve(images.getOriginalFilename()),
                            StandardCopyOption.REPLACE_EXISTING);
                    taikhoan.setHinhanh(images.getOriginalFilename().toLowerCase());
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            taikhoanService.saveTaikhoan(taikhoan);
            model.addAttribute("messages", "thanhcong");
        return "admin/profile/update-profile";
    }









}
