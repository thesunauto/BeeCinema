package vn.edu.poly.beecinema.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vn.edu.poly.beecinema.commons.*;
import vn.edu.poly.beecinema.entity.*;
import vn.edu.poly.beecinema.service.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/dashboards")
public class DashBoardsController {
    @Autowired
    private ThongKeService thongKeService;

    @Autowired
    private PhimService phimService;

    @Autowired
    private VeService veService;

    @Autowired
    private SuatChieuService suatChieuService;

    @Autowired
    private TaikhoanService taikhoanService;

    @Autowired
    private LoaiPhimService loaiPhimService;

    @Autowired
    private DoTuoiService doTuoiService;

    @GetMapping("/index")
    public String index() {

        return "admin/dashboards/dashboards";
    }

    @PostMapping("/getve")
    public ResponseEntity getListVeOnline() {
        List<String> listve = new ArrayList<String>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
        DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("HH:mm");
        List<TKVeResponse> tkVeByPhims = new ArrayList<>();
        phimService.getAllPhim().forEach(phim -> {

        });
        for(Phim phim : phimService.getAllPhim()){
            float tong = 0;
            int slve = 0;

            for(Suatchieu suatchieu : phim.getSuatchieus()){
                slve += suatchieu.getVes().size();
                for(Ve ve : suatchieu.getVes() ){
                    tong+=ve.getSuatchieu().getDongia()+ve.getGhe().getDayghe().getGia()+ve.getGhe().getLoaighe().getGia();

                    if(ve.getSukien()!=null){
                        tong-=ve.getSukien().getGiam();
                    }

                }
            }
            tkVeByPhims.add(TKVeResponse.builder()
                    .tenphim(phim.getTen())
                    .soluongve(slve)
                    .tongtien(tong)
                    .build());
        }
        return ResponseEntity.ok().body(tkVeByPhims);
    }

    @PostMapping("/countSlPhimTheoThang")
    public ResponseEntity countSlPhimTheoThang() {
        String countSlPhimTheoThang = String.valueOf(thongKeService.countSlPhimTheoThang());
        return ResponseEntity.ok().body(countSlPhimTheoThang);
    }

    @PostMapping("/countVeTheoThang")
    public ResponseEntity countVeTheoThang() {
        String countVeTheoThang = String.valueOf(thongKeService.countVeTheoThang());
        return ResponseEntity.ok().body(countVeTheoThang);
    }

    @PostMapping("/countDoanhThuTheoThang")
    public ResponseEntity countDoanhThuTheoThang() {
        String countDoanhThuTheoThang = String.valueOf(thongKeService.countDoanhThuTheoThang());
        return ResponseEntity.ok().body(countDoanhThuTheoThang);
    }

    @PostMapping("/countSlVeChuaBanTheoThang")
    public ResponseEntity countSlVeChuaBanTheoThang() {
        String countSlVeChuaBanTheoThang = String.valueOf(thongKeService.countSlVeChuaBanTheoThang());
        return ResponseEntity.ok().body(countSlVeChuaBanTheoThang);
    }

//    @PostMapping("/listTopPhim/{startdate}|{enddate}")
//    public ResponseEntity listTopPhim(@PathVariable String startdate, @PathVariable String enddate) {
//        List<String> listUser = new ArrayList<String>();
//        List<Object[]> tkVeByPhims = thongKeService.listTopPhim(startdate,enddate);
//        return ResponseEntity.ok().body(tkVeByPhims);
//    }

    @PostMapping("/listTopPhim")
    public ResponseEntity getlistPhimThongKe(@RequestParam String startdate, @RequestParam String enddate){
        LocalDate batdau = LocalDate.parse(startdate,DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate ketthuc = LocalDate.parse(enddate,DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<TKPhimResponse> tkVes = new ArrayList<>();
        for(Phim phim:phimService.getAllPhim()){
            TKPhimResponse tkVe = new TKPhimResponse();
            tkVe.setIdphim(phim.getId());
            tkVe.setTenphim(phim.getTen());
            int sove = 0;
            float tong = 0;
            List<Suatchieu> suatchieu = thongKeService.findAllByPhimAndDate(phim.getId(),batdau,ketthuc);

            for(Suatchieu suatchieu1 : suatchieu){

                for(Ve ve:suatchieu1.getVes()){
                    sove++;
                    tong+=ve.getSuatchieu().getDongia()+ve.getGhe().getDayghe().getGia()+ve.getGhe().getLoaighe().getGia();
                    if(ve.getSukien()!=null){
                        tong-=ve.getSukien().getGiam();
                    }
                }
            }
            tkVe.setSoluongve(sove);
            tkVe.setTongtien(tong);
            tkVes.add(tkVe);
        }
        return ResponseEntity.ok().body(tkVes);
    }

    @PostMapping("/listTopVe/{startdate}|{enddate}")
    public ResponseEntity getListVeThongKe(@PathVariable String startdate, @PathVariable String enddate){
        LocalDate batdau = LocalDate.parse(startdate,DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate ketthuc = LocalDate.parse(enddate,DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        List<TKVeResponse> tkVe2s = new ArrayList<>();
        for(Suatchieu suatchieu : thongKeService.findAllByDate(batdau,ketthuc)){
            for(Ve ve : suatchieu.getVes()){
                float sukien = 0;
                String sukienName = "";
                if(ve.getSukien()!=null){
                    sukienName+=ve.getSukien().getTen();
                    sukien +=ve.getSukien().getGiam();
                }
                tkVe2s.add(TKVeResponse.builder()
                        .tenphim(ve.getSuatchieu().getPhim().getTen())
                        .khunggio(ve.getSuatchieu().getKhunggio().getBatdau().format(dateTimeFormatter)+" - "+ve.getSuatchieu().getKhunggio().getKetthuc().format(dateTimeFormatter))
                        .tongtien(ve.getSuatchieu().getDongia()+ve.getGhe().getLoaighe().getGia()+ve.getGhe().getDayghe().getGia()-sukien)
                        .ngaychieu(ve.getSuatchieu().getNgaychieu().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                        .phong(ve.getSuatchieu().getPhong().getTen())
                        .sukien(sukienName)
                        .tenghe(ve.getGhe().getDayghe().getTen()+ve.getGhe().getCol())
                        .nhanvien(ve.getTaikhoan().getTen())
                        .ngaymua(ve.getNgaytao().toString())
                        .theloai(ve.getSuatchieu().getPhim().getLoaiphim().getTen())
                        .dotuoi(ve.getSuatchieu().getPhim().getDotuoi().getTen())
                        .build());
            }
        }
        return ResponseEntity.ok().body(tkVe2s);
    }

    @PostMapping("/listTopVeOnline/{startdate}|{enddate}")
    public ResponseEntity getListVeOnlineThongKe(@PathVariable String startdate, @PathVariable String enddate){
        LocalDate batdau = LocalDate.parse(startdate,DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate ketthuc = LocalDate.parse(enddate,DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        List<TKVeOnlineResponse> veOnlines = new ArrayList<>();
        for(Suatchieu suatchieu : thongKeService.findAllByDate(batdau,ketthuc)){
            for(Veonline veOnline : suatchieu.getVeonlines()){
                float sukien = 0;
                String sukienName = "";
                if(veOnline.getSukien()!=null){
                    sukienName+=veOnline.getSukien().getTen();
                    sukien +=veOnline.getSukien().getGiam();
                }
                veOnlines.add(TKVeOnlineResponse.builder()
                        .tenphim(veOnline.getSuatchieu().getPhim().getTen())
                        .khunggio(veOnline.getSuatchieu().getKhunggio().getBatdau().format(dateTimeFormatter)+" - "+veOnline.getSuatchieu().getKhunggio().getKetthuc().format(dateTimeFormatter))
                        .tongtien(veOnline.getSuatchieu().getDongia()+veOnline.getGhe().getLoaighe().getGia()+veOnline.getGhe().getDayghe().getGia()-sukien)
                        .ngaychieu(veOnline.getSuatchieu().getNgaychieu().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                        .phong(veOnline.getSuatchieu().getPhong().getTen())
                        .sukien(sukienName)
                        .tenghe(veOnline.getGhe().getDayghe().getTen()+veOnline.getGhe().getCol())
                        .khachhang(veOnline.getTaikhoan().getTen())
                        .ngaymua(veOnline.getNgaytao().toString())
                        .theloai(veOnline.getSuatchieu().getPhim().getLoaiphim().getTen())
                        .dotuoi(veOnline.getSuatchieu().getPhim().getDotuoi().getTen())
                        .build());
            }
        }
        return ResponseEntity.ok().body(veOnlines);
    }

    @PostMapping("/listTopLoaiPhim")
    public ResponseEntity getlistLoaiPhimThongKe(@RequestParam String startdate, @RequestParam String enddate){
        LocalDate batdau = LocalDate.parse(startdate,DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate ketthuc = LocalDate.parse(enddate,DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<TKLoaiPhimResponse> tkVes = new ArrayList<>();
        for(LoaiPhim loaiPhim:loaiPhimService.getAllLoaiPhim()){
            TKLoaiPhimResponse tkVe = new TKLoaiPhimResponse();
            tkVe.setIdloaiphim(loaiPhim.getId());
            tkVe.setTenloaiphim(loaiPhim.getTen());
            int sove = 0;
            float tong = 0;
            List<Suatchieu> suatchieu = thongKeService.findAllByLoaiPhimAndDate(loaiPhim.getId(),batdau,ketthuc);

            for(Suatchieu suatchieu1 : suatchieu){

                for(Ve ve:suatchieu1.getVes()){
                    sove++;
                    tong+=ve.getSuatchieu().getDongia()+ve.getGhe().getDayghe().getGia()+ve.getGhe().getLoaighe().getGia();
                    if(ve.getSukien()!=null){
                        tong-=ve.getSukien().getGiam();
                    }
                }
            }
            tkVe.setSoluongve(sove);
            tkVe.setTongtien(tong);
            tkVes.add(tkVe);
        }
        return ResponseEntity.ok().body(tkVes);
    }

    @PostMapping("/listTopDoTuoi")
    public ResponseEntity getlistDoTuoiThongKe(@RequestParam String startdate, @RequestParam String enddate){
        LocalDate batdau = LocalDate.parse(startdate,DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate ketthuc = LocalDate.parse(enddate,DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<TKDoTuoiResponse> tkVes = new ArrayList<>();
        for(DoTuoi doTuoi:doTuoiService.getAllDoTuoi()){
            TKDoTuoiResponse tkVe = new TKDoTuoiResponse();
            tkVe.setIddotuoi(doTuoi.getId());
            tkVe.setTendotuoi(doTuoi.getTen());
            int sove = 0;
            float tong = 0;
            List<Suatchieu> suatchieu = thongKeService.findAllByDoTuoiAndDate(doTuoi.getId(),batdau,ketthuc);

            for(Suatchieu suatchieu1 : suatchieu){

                for(Ve ve:suatchieu1.getVes()){
                    sove++;
                    tong+=ve.getSuatchieu().getDongia()+ve.getGhe().getDayghe().getGia()+ve.getGhe().getLoaighe().getGia();
                    if(ve.getSukien()!=null){
                        tong-=ve.getSukien().getGiam();
                    }
                }
            }
            tkVe.setSoluongve(sove);
            tkVe.setTongtien(tong);
            tkVes.add(tkVe);
        }
        return ResponseEntity.ok().body(tkVes);
    }

    @PostMapping("/listTaiKhoanThongKe/{startdate}|{enddate}")
    public ResponseEntity getListTaiKhoanThongKe(@PathVariable String startdate, @PathVariable String enddate) {
        LocalDate batdau = LocalDate.parse(startdate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate ketthuc = LocalDate.parse(enddate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<TKTaiKhoanResponse> tkTaiKhoans = new ArrayList<>();
        for (Taikhoan taiKhoan : taikhoanService.getAllTaikhoanByQuyen()) {
            TKTaiKhoanResponse tkTaiKhoan = new TKTaiKhoanResponse();
            tkTaiKhoan.setIdtaikhoan(taiKhoan.getUsername());
            tkTaiKhoan.setTentaikhoan(taiKhoan.getTen());
            int sove = 0;
            float tong = 0;
            List<Veonline> veons = new ArrayList<>(taiKhoan.getVeonlines());
            List<Ve> ves = new ArrayList<>(taiKhoan.getVes());
            for (Veonline veonline : veons) {
                if (veonline.getTrangthai() == 1  &&  batdau.isBefore(veonline.getSuatchieu().getNgaychieu()) && ketthuc.isAfter(veonline.getSuatchieu().getNgaychieu())) {
                    sove++;
                    tong += veonline.getSuatchieu().getDongia() + veonline.getGhe().getDayghe().getGia() + veonline.getGhe().getLoaighe().getGia();
                    tong -= veonline.getSukien() == null ? 0 : veonline.getSukien().getGiam();
                }
            }
            tkTaiKhoan.setSoluongve(sove);
            tkTaiKhoan.setTongtien(tong);
            tkTaiKhoans.add(tkTaiKhoan);
        }
        return ResponseEntity.ok().body(tkTaiKhoans);
    }

}
