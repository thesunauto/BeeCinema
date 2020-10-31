package vn.edu.poly.beecinema.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.poly.beecinema.commons.PhimResponse;
import vn.edu.poly.beecinema.service.PhimService;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeRestController {
    @Autowired private PhimService phimService;
    @GetMapping("/list-film-showing")
    public ResponseEntity listFilmShowing(){
        List<PhimResponse> phimResponses = new ArrayList<>();
        phimService.getAllPhim().forEach(value->{
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
}
