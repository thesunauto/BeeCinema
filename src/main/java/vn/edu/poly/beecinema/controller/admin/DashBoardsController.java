package vn.edu.poly.beecinema.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.poly.beecinema.commons.TKVeByPhim;
import vn.edu.poly.beecinema.commons.VeonlineResponse;
import vn.edu.poly.beecinema.entity.Phim;
import vn.edu.poly.beecinema.entity.Suatchieu;
import vn.edu.poly.beecinema.entity.Ve;
import vn.edu.poly.beecinema.service.PhimService;
import vn.edu.poly.beecinema.service.ThongKeService;
import vn.edu.poly.beecinema.service.VeService;

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

    @GetMapping("/index")
    public String index() {

        return "admin/dashboards/dashboards";
    }

    @PostMapping("/getve")
    public ResponseEntity getListVeOnline() {
        List<String> listve = new ArrayList<String>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
        DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("HH:mm");
        List<TKVeByPhim> tkVeByPhims = new ArrayList<>();
        phimService.getAllPhim().forEach(phim -> {

        });
        for(Phim phim : phimService.getAllPhim()){
            int tong = 0;
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
            tkVeByPhims.add(TKVeByPhim.builder()
                    .tenphim(phim.getTen())
                    .soluongve(String.valueOf(slve))
                    .tongtien(String.valueOf(tong))
                    .build());
        }
        return ResponseEntity.ok().body(tkVeByPhims);
    }
}
