package vn.edu.poly.beecinema.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.edu.poly.beecinema.entity.Khunggio;
import vn.edu.poly.beecinema.entity.Phim;
import vn.edu.poly.beecinema.entity.Suatchieu;
import vn.edu.poly.beecinema.entity.Sukien;
import vn.edu.poly.beecinema.service.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/screenings")
public class ScreeningsController {
    @Autowired
    private SuatChieuService suatChieuService;
    @Autowired
    private TaikhoanService taiKhoanService;
    @Autowired
    private PhimService phimService;
    @Autowired
    private PhongService phongService;
    @Autowired
    private KhungGioService khungGioService;

    @GetMapping("/show-screenings-v2")
    public String showScreeningsV2(Model model) {
        return "admin/screenings/show-screenings-v2";
    }

    @GetMapping("/show-screenings")
    public String showScreenings(Model model) {
        String keyword = null;
        return listByPage(model, 1, "id", "asc", keyword, null);
    }

    @GetMapping("/page/{pageNumber}")
    public String listByPage(Model model,
                             @PathVariable("pageNumber") int currentPage,
                             @Param("sortField") String sortField,
                             @Param("sortDir") String sortDir,
                             @Param("keyword") String keyword,
                             String messages) {
        Page<Suatchieu> page = suatChieuService.listAll(currentPage, sortField, sortDir, keyword);
        long totalItem = page.getTotalElements();
        int totalPages = page.getTotalPages();
        List<Suatchieu> suatChieu = page.getContent();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItem", totalItem);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("suatChieu", suatChieu);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("keyword", keyword);
        model.addAttribute("messages", messages);
        return "admin/screenings/show-screenings";
    }

    @RequestMapping("/add-screenings")
    public String addScreenings(Model model) {
        System.out.println("-G-  /add-screenings");
        model.addAttribute("suatChieu", new Suatchieu());
        return "admin/screenings/add-screenings";
    }

    @GetMapping(value = "/edit")
    public String editScreenings(Model model, @RequestParam("id") Integer suatChieuId) {
        Optional<Suatchieu> suatChieuEdit = suatChieuService.findSuatChieuById(suatChieuId);
        suatChieuEdit.ifPresent(suatChieu -> model.addAttribute("suatChieu", suatChieu));
        return "admin/screenings/update-screenings";
    }


    @PostMapping("/add-screenings")
    public String saveScreenings(@Valid @ModelAttribute("suatChieu") Suatchieu suatChieu, BindingResult bindingResult,
                                 Model model, Authentication authentication) {
        if (bindingResult.hasErrors()) {
        } else {
            suatChieu.setPhim(phimService.findPhimById(suatChieu.getPhim().getId()).get());
            suatChieu.setPhong(phongService.findPhongById(suatChieu.getPhong().getId()).get());
            suatChieu.setKhunggio(khungGioService.findKhungGioById(suatChieu.getKhunggio().getId()).get());
            suatChieu.setNgaytao(LocalDate.now());
            suatChieu.setTaikhoan(taiKhoanService.findTaikhoanById(authentication.getName()).get());


            List<Suatchieu> suatchieus = suatChieuService.findAllByPhimAndDate(suatChieu.getPhim().getId(), suatChieu.getNgaychieu());
            System.out.println("Star check lenght: " + suatchieus.size());
            for (Suatchieu sc1 : suatchieus) {
                if (sc1.getPhong().getId().equals(suatChieu.getPhong().getId())) {
                    if (!(suatChieu.getKhunggio().getKetthuc().compareTo(sc1.getKhunggio().getBatdau()) <= 0
                            || suatChieu.getKhunggio().getBatdau().compareTo(sc1.getKhunggio().getKetthuc()) >= 0)
                    ) {

                        model.addAttribute("mess", "toastr.error('Khung giờ này đã xung đột với suất chiếu khác của phòng này', '', {positionClass: 'md-toast-top-right'});$('#toast-container').attr('class','md-toast-top-right');");
                        return "admin/screenings/add-screenings";
                    }
                }

            }

            suatChieuService.saveSuatChieu(suatChieu);
            return listByPage(model, 1, "id", "asc", null, "themThanhCong");
        }
        return "admin/screenings/add-screenings";
    }

    @PostMapping(value = "/edit")
    public String updateScreenings(@Valid @ModelAttribute("khungGio") Suatchieu suatChieu, BindingResult bindingResult,
                                   Model model, Authentication authentication) {
        if (bindingResult.hasErrors()) {

        } else {
            suatChieuService.saveSuatChieu(suatChieu);
            return listByPage(model, 1, "id", "asc", null, "suaThanhCong");
        }
        return "admin/screenings/update-screenings";
    }


    @RequestMapping(value = "/delete")
    public String deleteScreenings(@PathVariable("id") Integer suatChieuId, Model model) {
        suatChieuService.deleteSuatChieu(suatChieuId);
        return listByPage(model, 1, "id", "asc", null, "xoaThanhCong");
    }
}

