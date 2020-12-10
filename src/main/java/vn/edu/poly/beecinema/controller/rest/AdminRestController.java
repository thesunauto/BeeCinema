package vn.edu.poly.beecinema.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import vn.edu.poly.beecinema.commons.*;
import vn.edu.poly.beecinema.config.HttpSessionConfig;
import vn.edu.poly.beecinema.entity.Ghe;
import vn.edu.poly.beecinema.entity.Phim;
import vn.edu.poly.beecinema.entity.Suatchieu;
import vn.edu.poly.beecinema.entity.VeonlineID;
import vn.edu.poly.beecinema.repository.VeonlineRepository;
import vn.edu.poly.beecinema.service.*;
import vn.edu.poly.beecinema.storage.StorageService;

import javax.servlet.http.HttpSession;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminRestController {
    @Autowired
    private SuatChieuService suatChieuService;
    @Autowired
    private PhongService phongService;

    @Autowired
    private TaikhoanService taiKhoanService;
    @Autowired
    private PhimService phimService;

    @Autowired
    private KhungGioService khungGioService;

    @PostMapping("/listSuatChieu")
    public ResponseEntity listSuatchieu(@RequestParam String date) {
        List<PhongResponse> phongResponses = new ArrayList<>();
        phongService.getAllPhong().forEach(phong -> {
            phongResponses.add(PhongResponse.builder()
                    .id(phong.getId())
                    .ten(phong.getTen())
                    .suatChieuResponses(suatChieuService.findAllByPhongAndNgayChieu(phong, LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"))))
                    .build());
        });

        return ResponseEntity.ok().body(phongResponses);
    }

    @PostMapping(value = "/addSC",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity addSC(Authentication authentication, @RequestBody SuatChieuResponse suatChieuResponse) {
        Suatchieu suatChieu = new Suatchieu();
        suatChieu.setPhim(phimService.findPhimById(suatChieuResponse.getIdphim()).get());

        suatChieu.setNgaychieu(LocalDate.parse(suatChieuResponse.getNgaychieu(),DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        suatChieu.setPhong(phongService.findPhongById(suatChieuResponse.getIdphong()).get());
        suatChieu.setKhunggio(khungGioService.findKhungGioById(suatChieuResponse.getIdkhunggio()).get());
        suatChieu.setNgaytao(LocalDate.now());
        suatChieu.setTaikhoan(taiKhoanService.findTaikhoanById(authentication.getName()).get());
suatChieu.setDongia(suatChieuResponse.getDongia().floatValue());
suatChieu.setPhuthuyonline(suatChieuResponse.getPhuthuyonline().intValue());
suatChieu.setTrangthai(Integer.valueOf(suatChieuResponse.getTrangthai()));
        List<Suatchieu> suatchieus = suatChieuService.findAllByPhimAndDate(suatChieu.getPhim().getId(), suatChieu.getNgaychieu());
        System.out.println("Star check lenght: " + suatchieus.size());
        for (Suatchieu sc1 : suatchieus) {
            if (sc1.getPhong().getId().equals(suatChieu.getPhong().getId())) {
                if (!(suatChieu.getKhunggio().getKetthuc().compareTo(sc1.getKhunggio().getBatdau()) <= 0
                        || suatChieu.getKhunggio().getBatdau().compareTo(sc1.getKhunggio().getKetthuc()) >= 0)
                ) {
                    return ResponseEntity.ok().body(false);
                }
            }

        }

        suatChieuService.saveSuatChieu(suatChieu);
        return ResponseEntity.ok().body(true);

    }
}
