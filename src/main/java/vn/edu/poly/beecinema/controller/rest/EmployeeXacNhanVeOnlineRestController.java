package vn.edu.poly.beecinema.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import vn.edu.poly.beecinema.commons.LichSuResponse;
import vn.edu.poly.beecinema.commons.UserResponse;
import vn.edu.poly.beecinema.commons.VeonlineResponse;
import vn.edu.poly.beecinema.entity.Taikhoan;
import vn.edu.poly.beecinema.entity.Ve;
import vn.edu.poly.beecinema.entity.Veonline;
import vn.edu.poly.beecinema.entity.VeonlineID;
import vn.edu.poly.beecinema.service.TaikhoanService;
import vn.edu.poly.beecinema.service.VeService;
import vn.edu.poly.beecinema.service.VeonlineService;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
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
    public ResponseEntity getListVeOnline(@RequestParam String key) {
        List<VeonlineResponse> veonlineResponses = new ArrayList<>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
        DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("HH:mm");
        for (Veonline veonline : veonlineService.findAllByToday()) {
            if (veonlineResponses.size() == 50) {
                break;
            }
            boolean check = false;
            if (key.equals("")) {
                check = true;
            } else {
                if (veonline.getTaikhoan().getUsername().contains(key)
                        || veonline.getTaikhoan().getSodienthoai().contains(key)
                        || veonline.getTaikhoan().getTen().contains(key)
                        || veonline.getTaikhoan().getEmail().contains(key)
                        || veonline.getSuatchieu().getKhunggio().getBatdau().format(DateTimeFormatter.ofPattern("HH:mm")).contains(key)
                        || veonline.getSuatchieu().getPhim().getTen().contains(key)
                        || veonline.getSuatchieu().getPhong().getTen().contains(key)
                ) {
                    check = true;
                }
            }


            if (check) {
                veonlineResponses.add(VeonlineResponse.builder()
                        .idsuatchieughe(String.valueOf(veonline.getSuatchieu().getId() + "|" + String.valueOf(veonline.getGhe().getId())))
                        .ngaytaoLDT(veonline.getNgaytao())
                        .ngaytao(veonline.getNgaytao().getHour() + ":" + veonline.getNgaytao().getMinute() + " - " + veonline.getNgaytao().getDayOfMonth() + '/' + veonline.getNgaytao().getMonthValue())
                        .SDT(veonline.getTaikhoan().getSodienthoai())
                        .suatchieu(veonline.getSuatchieu().getKhunggio().getBatdau().format(dateTimeFormatter2) + " - " + veonline.getSuatchieu().getKhunggio().getKetthuc().format(dateTimeFormatter2))
                        .tenkhachhang(veonline.getTaikhoan().getTen())
                        .tenphim(veonline.getSuatchieu().getPhim().getTen())
                        .hethan((veonline.getSuatchieu().getKhunggio().getBatdau().minusMinutes(veonline.getSuatchieu().getPhuthuyonline()).getHour()) + ":" + (veonline.getSuatchieu().getKhunggio().getBatdau().minusMinutes(veonline.getSuatchieu().getPhuthuyonline()).getMinute()) + " - " + veonline.getSuatchieu().getNgaychieu().getDayOfMonth() + '/' + veonline.getSuatchieu().getNgaychieu().getMonthValue())
                        .trangthai(veonlineService.getStt(veonline))
                        .build());
            }
        }
        ;
        Collections.sort(veonlineResponses, new Comparator<VeonlineResponse>() {
            @Override
            public int compare(VeonlineResponse o1, VeonlineResponse o2) {
                return o2.getNgaytaoLDT().compareTo(o1.getNgaytaoLDT());
            }
        });
        return ResponseEntity.ok().body(veonlineResponses);
    }

    @PostMapping("/xacnhanve{idsuatchieu}|{idghe}")
    public ResponseEntity xacnhanve(Authentication authentication, @PathVariable Integer idsuatchieu, @PathVariable Integer idghe) {
        List<Integer> veon2 = new ArrayList<>();
        veon2.add(idghe);
        veon2.add(idsuatchieu);
        try {
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
    public ResponseEntity getHistory(@RequestParam String key) {


        List<LichSuResponse> lichSuResponses = new ArrayList<>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        List<Veonline> veonlines = veonlineService.findAllByToday();
        for(Ve ve : veService.findAllByToDay()){
            if(lichSuResponses.size()==50){
                break;
            }
            boolean ck = false;
            if (key.equals("")) {
                ck = true;
            } else {
                if ((ve.getGhe().getDayghe().getTen()+"-"+String.valueOf(ve.getGhe().getCol())).contains(key)
                        ||ve.getGhe().getDayghe().getTen().contains(key)
                        || String.valueOf(ve.getGhe().getCol()).contains(key)
                        || ve.getSuatchieu().getPhong().getTen().contains(key)
                        || ve.getTaikhoan().getTen().contains(key)
                        || ve.getTaikhoan().getEmail().contains(key)
                        || ve.getTaikhoan().getSodienthoai().contains(key)
                        || ve.getSuatchieu().getPhim().getTen().contains(key)
                        || ve.getSuatchieu().getKhunggio().getBatdau().format(DateTimeFormatter.ofPattern("HH:mm")).contains(key)
                ) {
                    ck = true;
                }
            }

            if (ck) {
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
            }


        };

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
                .ngaysinh(new SimpleDateFormat("dd/MM/yyyy").format(tk.getNgaysinh() == null ? new Date(2000, 01, 01) : tk.getNgaysinh()))
                .password(tk.getMatkhau())
                .sdt(tk.getSodienthoai())
                .ten(tk.getTen())
                .username(tk.getUsername())
                .build());
    }
}
