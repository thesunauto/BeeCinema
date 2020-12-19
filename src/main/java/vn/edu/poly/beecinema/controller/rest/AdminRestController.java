package vn.edu.poly.beecinema.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import vn.edu.poly.beecinema.commons.*;
import vn.edu.poly.beecinema.config.HttpSessionConfig;
import vn.edu.poly.beecinema.entity.*;
import vn.edu.poly.beecinema.repository.VeonlineRepository;
import vn.edu.poly.beecinema.service.*;
import vn.edu.poly.beecinema.storage.StorageService;

import javax.servlet.http.HttpSession;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

    @Autowired private GheService gheService;
    @Autowired private DayGheService dayGheService;

    @PostMapping("/loadghebyphong={idphong}")
    public ResponseEntity loadGhe(@PathVariable String idphong) {
        List<DayGheResponse> gheResponses = new ArrayList<>();

        dayGheService.findDayGheByPhong(idphong).forEach(dayghe -> {
            List<GheResponse> gheResponses1 = new ArrayList<>();
            gheService.findByPhongAndDayGhe(idphong, dayghe.getId()).forEach(ghe -> {
                Integer stt = ghe.getTrangthai();
                if (ghe.getTrangthai() == 1) {
                    stt = 4;
                }
                gheResponses1.add(new GheResponse(ghe.getId(), ghe.getCol(), ghe.getPhong().getId(), ghe.getDayghe().getId(), ghe.getDayghe().getTen(), ghe.getLoaighe().getId(), stt));
            });
            gheResponses.add(new DayGheResponse(dayghe.getId(), dayghe.getTen(), gheResponses1));
        });
        Collections.sort(gheResponses, new Comparator<DayGheResponse>() {
            @Override
            public int compare(DayGheResponse o1, DayGheResponse o2) {
                return o1.getTen().compareToIgnoreCase(o2.getTen());
            }
        });
        return ResponseEntity.ok().body(gheResponses);
    }

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

        suatChieu.setNgaychieu(LocalDate.parse(suatChieuResponse.getNgaychieu(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        suatChieu.setPhong(phongService.findPhongById(suatChieuResponse.getIdphong()).get());
        suatChieu.setKhunggio(khungGioService.findKhungGioById(suatChieuResponse.getIdkhunggio()).get());
        suatChieu.setNgaytao(LocalDate.now());
        suatChieu.setTaikhoan(taiKhoanService.findTaikhoanById(authentication.getName()).get());
        suatChieu.setDongia(suatChieuResponse.getDongia().floatValue());
        suatChieu.setPhuthuyonline(suatChieuResponse.getPhuthuyonline().intValue());
        suatChieu.setTrangthai(Integer.valueOf(suatChieuResponse.getTrangthai()));
        List<Suatchieu> suatchieus = suatChieuService.findAllByNgayChieuAndPhong(suatChieu.getNgaychieu(),suatChieu.getPhong());
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



    @PostMapping(value = "/delSC"
    )
    public ResponseEntity delSC(@RequestParam String idsuatchieu){
        Suatchieu suatchieu = suatChieuService.findById(Integer.valueOf(idsuatchieu));
        if(suatchieu.getVes().size()==0&&suatchieu.getVeonlines().size()==0){
            suatChieuService.deleteSuatChieu(Integer.valueOf(idsuatchieu));
            return ResponseEntity.ok().body(null);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/editSC",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity editSC(Authentication authentication, @RequestBody SuatChieuResponse suatChieuResponse,@RequestParam String idsuatchieu) {
        Suatchieu suatChieu = suatChieuService.findById(Integer.valueOf(idsuatchieu));
        if(suatChieu.getVes().isEmpty()&&suatChieu.getVeonlines().isEmpty()){
            if(!suatChieu.getKhunggio().getId().equals(suatChieuResponse.getIdkhunggio())){
                List<Suatchieu> suatchieus = suatChieuService.findAllByNgayChieuAndPhong(suatChieu.getNgaychieu(),suatChieu.getPhong());
                Khunggio kh = khungGioService.findKhungGioById(suatChieuResponse.getIdkhunggio()).get();
                for (Suatchieu sc1 : suatchieus) {
                    if (sc1.getPhong().getId().equals(suatChieuResponse.getIdphong())) {
                        if (!(kh.getKetthuc().compareTo(sc1.getKhunggio().getBatdau()) <= 0
                                || kh.getBatdau().compareTo(sc1.getKhunggio().getKetthuc()) >= 0)
                        ) {
                            return ResponseEntity.ok().body(false);
                        }
                    }

                }
            }
            suatChieu.setPhim(phimService.findPhimById(suatChieuResponse.getIdphim()).get());

            suatChieu.setNgaychieu(LocalDate.parse(suatChieuResponse.getNgaychieu(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            suatChieu.setPhong(phongService.findPhongById(suatChieuResponse.getIdphong()).get());
            suatChieu.setKhunggio(khungGioService.findKhungGioById(suatChieuResponse.getIdkhunggio()).get());
            suatChieu.setNgaytao(LocalDate.now());
            suatChieu.setTaikhoan(taiKhoanService.findTaikhoanById(authentication.getName()).get());
            suatChieu.setDongia(suatChieuResponse.getDongia().floatValue());
            suatChieu.setPhuthuyonline(suatChieuResponse.getPhuthuyonline().intValue());
            suatChieu.setTrangthai(Integer.valueOf(suatChieuResponse.getTrangthai()));


            suatChieuService.saveSuatChieu(suatChieu);
            return ResponseEntity.ok().body(true);
        }

        return ResponseEntity.ok().body(false);
    }

    @PostMapping("/getListTongTien")
    public ResponseEntity getListTongTien(@RequestParam String bd,@RequestParam String kt){
        LocalDate batdau = LocalDate.parse(bd,DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        LocalDate ketthuc = LocalDate.parse(kt,DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        List<TKVe> tkVes = new ArrayList<>();
        for(Phim phim:phimService.getAllPhim()){
            TKVe tkVe = new TKVe();
            tkVe.setIdphim(phim.getId());
            tkVe.setTenPhim(phim.getTen());
            int sove = 0;
            float tong = 0;
           List<Suatchieu> suatchieu = suatChieuService.findAllByPhimAndDate(phim.getId(),batdau,ketthuc);

            for(Suatchieu suatchieu1 : suatchieu){

                for(Ve ve:suatchieu1.getVes()){
                    sove++;
                    tong+=ve.getSuatchieu().getDongia()+ve.getGhe().getDayghe().getGia()+ve.getGhe().getLoaighe().getGia();
                    if(ve.getSukien()!=null){
                        tong-=ve.getSukien().getGiam();
                    }
                }
            }
            tkVe.setSove(sove);
            tkVe.setTongtien(tong);
            tkVes.add(tkVe);
        }
        return ResponseEntity.ok().body(tkVes);
    }

    @PostMapping("/getListVeThongKe")
    public ResponseEntity getListVeThongKe(@RequestParam String bd,@RequestParam String kt){
        LocalDate batdau = LocalDate.parse(bd,DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        LocalDate ketthuc = LocalDate.parse(kt,DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        List<TKVe2> tkVe2s = new ArrayList<>();
        for(Suatchieu suatchieu : suatChieuService.findAllByDate(batdau,ketthuc)){
            for(Ve ve : suatchieu.getVes()){
                float sukien = 0;
                String sukienName = "";
                if(ve.getSukien()!=null){
                    sukienName+=ve.getSukien().getTen();
                    sukien +=ve.getSukien().getGiam();
                }
                tkVe2s.add(TKVe2.builder()
                        .tenphim(ve.getSuatchieu().getPhim().getTen())
                        .khunggio(ve.getSuatchieu().getKhunggio().getBatdau().format(dateTimeFormatter)+" - "+ve.getSuatchieu().getKhunggio().getKetthuc().format(dateTimeFormatter))
                        .gia(ve.getSuatchieu().getDongia()+ve.getGhe().getLoaighe().getGia()+ve.getGhe().getDayghe().getGia()-sukien)
                        .ngaychieu(ve.getSuatchieu().getNgaychieu().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                        .phong(ve.getSuatchieu().getPhong().getTen())
                        .sukien(sukienName)
                        .tenghe(ve.getGhe().getDayghe().getTen()+ve.getGhe().getCol())
                        .build());
            }
        }
        return ResponseEntity.ok().body(tkVe2s);
    }
}
