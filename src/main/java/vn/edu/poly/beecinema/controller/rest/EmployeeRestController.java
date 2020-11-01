package vn.edu.poly.beecinema.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.poly.beecinema.commons.DayGheResponse;
import vn.edu.poly.beecinema.commons.GheResponse;
import vn.edu.poly.beecinema.commons.PhimResponse;
import vn.edu.poly.beecinema.commons.VeResponse;
import vn.edu.poly.beecinema.entity.Phim;
import vn.edu.poly.beecinema.entity.Suatchieu;
import vn.edu.poly.beecinema.service.DayGheService;
import vn.edu.poly.beecinema.service.GheService;
import vn.edu.poly.beecinema.service.PhimService;
import vn.edu.poly.beecinema.service.SuatChieuService;
import vn.edu.poly.beecinema.storage.StorageService;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeRestController {
    @Autowired
    private PhimService phimService;
    @Autowired
    private SuatChieuService suatChieuService;
    @Autowired
    private StorageService storageService;
    @Autowired
    private DayGheService dayGheService;
    @Autowired private GheService gheService;

    public EmployeeRestController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/list-film-showing")
    public ResponseEntity listFilmShowing() {
        List<PhimResponse> phimResponses = new ArrayList<>();
        phimService.getAllPhim().forEach(value -> {
            PhimResponse phim = new PhimResponse();
            List<Integer> idsuatchieu = new ArrayList<>();
            value.getSuatchieus().forEach(suatchieu -> {
                idsuatchieu.add(suatchieu.getId());
            });
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            phim.setId(value.getId());
            phim.setDotuoi(value.getDotuoi().getTen());
            phim.setNgonngu(value.getNgonngu().getTen());
            phim.setLoaiphim(value.getLoaiphim().getTen());
            phim.setTen(value.getTen());
            phim.setHinhanh(value.getHinhanh());
            phim.setNgaybatdau(value.getNgaybatdau().format(dateTimeFormatter));
            phim.setNgayketthuc(value.getNgayketthuc().format(dateTimeFormatter));
            phim.setDodai(value.getDodai());
            phim.setIdsuatchieu(idsuatchieu);
            phimResponses.add(phim);
        });
        return ResponseEntity.ok().body(phimResponses);
    }


    @GetMapping("/getone/{id}")
    public ResponseEntity getFilm(@PathVariable(value = "id") String id) {

        Phim value = phimService.findPhimById(id).get();
        PhimResponse phim = new PhimResponse();
        List<Integer> idsuatchieu = new ArrayList<>();
        value.getSuatchieus().forEach(suatchieu -> {
            idsuatchieu.add(suatchieu.getId());
        });
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        phim.setId(value.getId());
        phim.setDotuoi(value.getDotuoi().getTen());
        phim.setNgonngu(value.getNgonngu().getTen());
        phim.setLoaiphim(value.getLoaiphim().getTen());
        phim.setTen(value.getTen());
        phim.setHinhanh(value.getHinhanh());
        phim.setNgaybatdau(value.getNgaybatdau().format(dateTimeFormatter));
        phim.setNgayketthuc(value.getNgayketthuc().format(dateTimeFormatter));
        phim.setDodai(value.getDodai());
        phim.setIdsuatchieu(idsuatchieu);

        return ResponseEntity.ok().body(phim);

}

    @GetMapping("/getResourse/{filename:.+}")
    public ResponseEntity getResourseFile(@PathVariable String filename) {
        Resource resource = storageService.loadAsResource(filename);
        return ResponseEntity.ok().body(resource);
    }

    @GetMapping("/getPath/{filename:.+}")
    public ResponseEntity getPathFile(@PathVariable String filename) {
        Path resource = storageService.load(filename);
        return ResponseEntity.ok().body(resource);
    }



    @GetMapping("/getGhe/{idsuatchieu}")
    public ResponseEntity getGhe(HttpSession httpSession, @PathVariable Integer idsuatchieu){
        List<VeResponse> veResponses = (List<VeResponse>) httpSession.getAttribute("veresponse");
        System.out.println(veResponses.size());
        List<DayGheResponse> gheResponses = new ArrayList<>();
        Suatchieu suatchieu = suatChieuService.findById(idsuatchieu);
        dayGheService.findDayGheByPhong(suatchieu.getPhong().getId()).forEach(dayghe -> {
            List<GheResponse> gheResponses1 = new ArrayList<>();
            gheService.findByPhongAndDayGhe(suatchieu.getPhong().getId(),dayghe.getId()).forEach(ghe -> {
                Integer stt = ghe.getTrangthai();
                for(VeResponse veResponse : veResponses){
                    if(veResponse.getIdSuatChieu().equals(suatchieu.getId()) && veResponse.getIdGhe().equals(ghe.getId())){
                        stt = veResponse.getStt();
                    }
                }
                gheResponses1.add(new GheResponse(ghe.getCol(),ghe.getPhong().getId(),ghe.getDayghe().getId(),ghe.getDayghe().getTen(),ghe.getLoaighe().getId(),stt));
            });
            gheResponses.add(new DayGheResponse(dayghe.getId(),dayghe.getTen(),gheResponses1));
        });
        return ResponseEntity.ok().body(gheResponses);
    }

    @GetMapping("/setghefocus")
    public ResponseEntity setGheFocus(@RequestBody VeResponse veResponse){
        return ResponseEntity.ok().body(true);
    }

}
