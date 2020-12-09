package vn.edu.poly.beecinema.controller;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.poly.beecinema.commons.VeResponse;
import vn.edu.poly.beecinema.entity.Taikhoan;
import vn.edu.poly.beecinema.entity.Ve;
import vn.edu.poly.beecinema.entity.VeID;
import vn.edu.poly.beecinema.service.PhimService;
import vn.edu.poly.beecinema.service.SuatChieuService;
import vn.edu.poly.beecinema.service.TaikhoanService;
import vn.edu.poly.beecinema.service.VeService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalTime;
import java.util.*;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired private PhimService phimService;
    @Autowired private SuatChieuService suatChieuService;
    @Autowired private VeService veService;
@Autowired private TaikhoanService taikhoanService;

    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;
    @Autowired
    public EmployeeController(InMemoryUserDetailsManager inMemoryUserDetailsManager) {
        this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
    }


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

    @GetMapping("/xuatve/{idsuatchieu}|{idghe}")
    public void xuatVePDF(HttpServletResponse response, @PathVariable Integer idsuatchieu, @PathVariable Integer idghe) throws FileNotFoundException, JRException, IOException {
        List<Ve> ve = veService.findByVeID(new VeID(idsuatchieu,idghe));
        File file = ResourceUtils.getFile("classpath:ve.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(ve);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Java Techie");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", String.format("inline; filename=\"ve.pdf\""));
        OutputStream out = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, out);

//        return "employee/datghe";
    }

    @GetMapping(value = "/update-profile")
    public String editProfileType(Model model, Authentication authentication){
        Optional<Taikhoan> taiKhoanEdit = taikhoanService.findTaikhoanById(taikhoanService.findTaikhoanById(authentication.getName()).get().getUsername());
        taiKhoanEdit.ifPresent(taikhoan -> model.addAttribute("taikhoan", taikhoan));
        return "employee/profile";
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
        return "employee/profile";
    }

    @GetMapping("/change-pass")
    public String changePassword(Model model, Authentication authentication) {
        return "employee/change-password-emp";
    }
    @PostMapping("/change-pass")
    public String changePassword(
            @RequestParam("password") String password,
            @RequestParam("newpassword") String newpassword,
            @RequestParam("confirmnewpassword") String confirmpassword,
            Model model, Authentication authentication) {
        Taikhoan taikhoan = taikhoanService.findTaikhoanByUsername(taikhoanService.findTaikhoanById(authentication.getName()).get().getUsername());
        if(!password.equals(taikhoanService.findTaikhoanById(authentication.getName()).get().getMatkhau())){
            model.addAttribute("messages", "saimatkhau");
        }
        else if(!newpassword.equals(confirmpassword)){
            model.addAttribute("messages", "matkhaukhongkhop");
        }
        else{
            taikhoanService.updatePassword(taikhoan, newpassword);
            inMemoryUserDetailsManager.deleteUser(taikhoan.getUsername());
            inMemoryUserDetailsManager.createUser(User.withDefaultPasswordEncoder().username(taikhoan.getUsername()).password(taikhoan.getMatkhau()).roles(taikhoan.getQuyen().getTen()).build());
            model.addAttribute("messages", "thanhcong");
        }

        return "employee/change-password-emp";
    }



}
