package vn.edu.poly.beecinema.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.poly.beecinema.commons.LichSuResponse;
import vn.edu.poly.beecinema.commons.UserResponse;
import vn.edu.poly.beecinema.commons.VeonlineResponse;
import vn.edu.poly.beecinema.entity.Taikhoan;
import vn.edu.poly.beecinema.entity.Veonline;
import vn.edu.poly.beecinema.entity.VeonlineID;
import vn.edu.poly.beecinema.service.TaikhoanService;
import vn.edu.poly.beecinema.service.VeService;
import vn.edu.poly.beecinema.service.VeonlineService;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/api/employee")
public class EmployeeXacNhanVeOnlineRestController {
    @Autowired
    private VeonlineService veonlineService;
    @Autowired
    private VeService veService;
    @Autowired
    private TaikhoanService taikhoanService;

    @PostMapping("/getlistveonline")
    public ResponseEntity getListVeOnline() {
        List<VeonlineResponse> veonlineResponses = new ArrayList<>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
        DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("HH:mm");
        veonlineService.findAllByToday().forEach(veonline -> {
            veonlineResponses.add(VeonlineResponse.builder()
                    .idsuatchieughe(String.valueOf(veonline.getSuatchieu().getId() + "|" + String.valueOf(veonline.getGhe().getId())))
                    .ngaytao(veonline.getNgaytao().getHour() + ":" + veonline.getNgaytao().getMinute() + " - " + veonline.getNgaytao().getDayOfMonth() + '/' + veonline.getNgaytao().getMonthValue())
                    .SDT(veonline.getTaikhoan().getSodienthoai())
                    .suatchieu(veonline.getSuatchieu().getKhunggio().getBatdau().format(dateTimeFormatter2) + " - " + veonline.getSuatchieu().getKhunggio().getKetthuc().format(dateTimeFormatter2))
                    .tenkhachhang(veonline.getTaikhoan().getTen())
                    .tenphim(veonline.getSuatchieu().getPhim().getTen())
                    .hethan((veonline.getSuatchieu().getKhunggio().getBatdau().minusMinutes(veonline.getSuatchieu().getPhuthuyonline()).getHour()) + ":" + (veonline.getSuatchieu().getKhunggio().getBatdau().minusMinutes(veonline.getSuatchieu().getPhuthuyonline()).getMinute()) + " - " + veonline.getSuatchieu().getNgaychieu().getDayOfMonth() + '/' + veonline.getSuatchieu().getNgaychieu().getMonthValue())
                    .trangthai(veonlineService.getStt(veonline))
                    .build());
        });
        return ResponseEntity.ok().body(veonlineResponses);
    }

    @PostMapping("/xacnhanve{idsuatchieu}|{idghe}")
    public ResponseEntity xacnhanve(Authentication authentication, @PathVariable Integer idsuatchieu, @PathVariable Integer idghe) {
        List<Integer> veon2 = new ArrayList<>();
        veon2.add(idghe);
        veon2.add(idsuatchieu); try {
            Veonline veonline = veonlineService.findByVeonlineID(new VeonlineID(idsuatchieu, idghe));
            veonline.setTrangthai(1);
            veonlineService.save(veonline);


            veService.insert(veonline.getSuatchieu().getId(), veonline.getGhe().getId(), veonline.getSukien() == null ? "noevent" : veonline.getSukien().getId(), authentication.getName());
            return ResponseEntity.ok().body(veon2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(veon2);
    }

    @PostMapping("/getHistory")
    public ResponseEntity getHistory() {




        List<LichSuResponse> lichSuResponses = new ArrayList<>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        List<Veonline> veonlines = veonlineService.findAllByToday();
        veService.findAllByToDay().forEach(ve -> {
            AtomicReference<String> pp = new AtomicReference<>("");
            veonlines.forEach(veonline -> {
                if (veonline.getVeonlineID().getIdghe().equals(ve.getVeID().getIdghe()) && ve.getVeID().getIdsuatchieu().equals(veonline.getVeonlineID().getIdsuatchieu())) {
                    pp.set("Xác nhận vé");
                }
            });

            lichSuResponses.add(LichSuResponse.builder()
                    .ghe(ve.getGhe().getDayghe().getTen() + " - " + ve.getGhe().getCol())
                    .suatchieu(ve.getSuatchieu().getKhunggio().getBatdau().format(dateTimeFormatter) + " - " + ve.getSuatchieu().getKhunggio().getKetthuc().format(dateTimeFormatter) + " | " + ve.getSuatchieu().getPhong().getTen() + " | " + ve.getSuatchieu().getPhim().getTen())
                    .sukien(ve.getSukien() == null ? "Không có" : ve.getSukien().getTen())
                    .nguoitao(ve.getTaikhoan().getTen())
                    .loaigiaodich(pp.get().equals("") ? "Bán vé" : pp.get())
                    .build());
        });

        return ResponseEntity.ok().body(lichSuResponses);
    }

    @PostMapping("/getuser")
    public ResponseEntity getUser(Authentication authentication) {
        Taikhoan tk = taikhoanService.findTaikhoanById(authentication.getName()).get();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return ResponseEntity.ok().body(UserResponse.builder()
                .email(tk.getEmail())
                .gioitinh(tk.getGioitinh())
                .image(tk.getHinhanh())
                .ngaysinh(new SimpleDateFormat("dd/MM/yyyy").format(tk.getNgaysinh()==null?new Date(2000,01,01) :tk.getNgaysinh()))
                .password(tk.getMatkhau())
                .sdt(tk.getSodienthoai())
                .ten(tk.getTen())
                .username(tk.getUsername())
                .build());
    }
}
