package vn.edu.poly.beecinema.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.poly.beecinema.commons.VeonlineResponse;
import vn.edu.poly.beecinema.entity.Veonline;
import vn.edu.poly.beecinema.entity.VeonlineID;
import vn.edu.poly.beecinema.service.VeService;
import vn.edu.poly.beecinema.service.VeonlineService;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeXacNhanVeOnlineRestController {
    @Autowired private VeonlineService veonlineService;
@Autowired private VeService veService;
    @PostMapping("/getlistveonline")
    public ResponseEntity getListVeOnline(){
        List<VeonlineResponse> veonlineResponses = new ArrayList<>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
        DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("HH:mm");
        veonlineService.findAllByToday().forEach(veonline -> {
            veonlineResponses.add(VeonlineResponse.builder()
                    .idsuatchieughe(String.valueOf(veonline.getSuatchieu().getId()+"|"+String.valueOf(veonline.getGhe().getId())))
                    .ngaytao(veonline.getNgaytao().getHour()+":"+veonline.getNgaytao().getMinute()+" - "+veonline.getNgaytao().getDayOfMonth()+'/'+veonline.getNgaytao().getMonthValue())
                    .SDT(veonline.getTaikhoan().getSodienthoai())
                    .suatchieu(veonline.getSuatchieu().getKhunggio().getBatdau().format(dateTimeFormatter2)+" - "+veonline.getSuatchieu().getKhunggio().getKetthuc().format(dateTimeFormatter2))
                    .tenkhachhang(veonline.getTaikhoan().getTen())
                    .tenphim(veonline.getSuatchieu().getPhim().getTen())
                    .hethan((veonline.getSuatchieu().getKhunggio().getBatdau().minusMinutes(veonline.getSuatchieu().getPhuthuyonline()).getHour())+":"+(veonline.getSuatchieu().getKhunggio().getBatdau().minusMinutes(veonline.getSuatchieu().getPhuthuyonline()).getMinute())+" - "+veonline.getSuatchieu().getNgaychieu().getDayOfMonth()+'/'+veonline.getSuatchieu().getNgaychieu().getMonthValue())
                    .trangthai(veonlineService.getStt(veonline))
                    .build());
        });
        return ResponseEntity.ok().body(veonlineResponses);
    }

    @PostMapping("/xacnhanve{idsuatchieu}|{idghe}")
    public ResponseEntity xacnhanve(Authentication authentication, @PathVariable Integer idsuatchieu, @PathVariable Integer idghe){
      try {
          Veonline veonline = veonlineService.findByVeonlineID(new VeonlineID(idsuatchieu,idghe));
          veonline.setTrangthai(1);
          veonlineService.save(veonline);
          veService.insert(veonline.getSuatchieu().getId(),veonline.getGhe().getId(),veonline.getSukien()==null?"noevent":veonline.getSukien().getId(),authentication.getName());
          return ResponseEntity.ok().body(true);
      }catch (Exception e){
          e.printStackTrace();
      }
        return ResponseEntity.ok().body(false);
    }
}
