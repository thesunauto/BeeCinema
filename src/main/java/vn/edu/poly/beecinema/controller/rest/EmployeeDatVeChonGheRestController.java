package vn.edu.poly.beecinema.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.WebSession;
import vn.edu.poly.beecinema.commons.*;
import vn.edu.poly.beecinema.config.HttpSessionConfig;
import vn.edu.poly.beecinema.entity.*;
import vn.edu.poly.beecinema.service.*;
import vn.edu.poly.beecinema.storage.StorageService;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeDatVeChonGheRestController {
    @Autowired
    private PhimService phimService;
    @Autowired
    private SuatChieuService suatChieuService;
    @Autowired
    private StorageService storageService;
    @Autowired
    private DayGheService dayGheService;
    @Autowired
    private GheService gheService;
    @Autowired
    private VeService veService;
    @Autowired
    private VeonlineService veonlineService;
    @Autowired
    private  SukienService sukienService;
@Autowired private TaikhoanService taikhoanService;
    public EmployeeDatVeChonGheRestController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/list-film-showing")
    public ResponseEntity listFilmShowing() {
        List<PhimResponse> phimResponses = new ArrayList<>();
        phimService.getAllPhimAlive().forEach(value -> {
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

    @PostMapping("/setghefocus")
    @ResponseBody
    public ResponseEntity setGheFocus(HttpSession httpSession, @RequestBody VeResponse veResponse) {

        if (httpSession.getAttribute("veresponse") == null) {
            httpSession.setAttribute("veresponse", new ArrayList<VeResponse>());
        }
        List<VeResponse> veResponses = (List<VeResponse>) httpSession.getAttribute("veresponse");



        for (int i = 0; i < veResponses.size(); i++) {
            if (veResponse.getIdsuatchieu().equals(veResponses.get(i).getIdsuatchieu())) {
                if (veResponse.getIdghe().equals(veResponses.get(i).getIdghe())) {
                    veResponses.remove(i);
                    httpSession.setAttribute("veresponse", veResponses);
                    return ResponseEntity.ok().body(veResponses);
                }
            }
        }
        veResponses.add(veResponse);
        httpSession.setAttribute("veresponse", veResponses);
        return ResponseEntity.ok().body(veResponses);
    }

    @PostMapping("/loadghebysuatchieu={idsuatchieu}")
    public ResponseEntity loadGhe(@PathVariable Integer idsuatchieu){
        List<DayGheResponse> gheResponses = new ArrayList<>();
        Suatchieu suatchieu = suatChieuService.findById(idsuatchieu);
        dayGheService.findDayGheByPhong(suatchieu.getPhong().getId()).forEach(dayghe -> {
            List<GheResponse> gheResponses1 = new ArrayList<>();
            gheService.findByPhongAndDayGhe(suatchieu.getPhong().getId(), dayghe.getId()).forEach(ghe -> {
                Integer stt = ghe.getTrangthai();
                if (ghe.getTrangthai() == 1) {
                    stt = 4;
                }
                gheResponses1.add(new GheResponse(ghe.getId(), ghe.getCol(), ghe.getPhong().getId(), ghe.getDayghe().getId(), ghe.getDayghe().getTen(), ghe.getLoaighe().getId(), stt));
            });
            gheResponses.add(new DayGheResponse(dayghe.getId(), dayghe.getTen(), gheResponses1));
        });
        return ResponseEntity.ok().body(gheResponses);
    }

    @PostMapping("/gettrangthaighebysuatchieu={idsuatchieu}")
    public ResponseEntity gettrangthaighe(HttpSessionConfig httpSessionConfig, HttpSession httpSession, @PathVariable Integer idsuatchieu){
        List<GheResponse> gheResponses = new ArrayList<>();
        if(idsuatchieu!=-1){
            List<VeResponse> veResponses = new ArrayList<>();
            List<VeResponse> veResponsesCurent = (List<VeResponse>) httpSession.getAttribute("veresponse");
            List<HttpSession> httpSessions = httpSessionConfig.getActiveSessions();
            if (!httpSessions.isEmpty()) {
                httpSessions.forEach(session -> {
                    if(session!=null){
                        try{
                            if(session.getId()!=httpSession.getId()){
                                if (session.getAttribute("veresponse") != null) {
                                    veResponses.addAll((List<VeResponse>) session.getAttribute("veresponse"));
                                }
                            }

                        }catch (Exception e){}
                    }
                });
            }



            veService.findAllByIdSuatchieu(idsuatchieu).forEach(ve -> {
                gheResponses.add(GheResponse.builder().id(ve.getGhe().getId()).trangthai(1).build());
            });
            veonlineService.findAllByIdSuatchieu(idsuatchieu).forEach(veon -> {
//                LocalTime timehuy = veon.getSuatchieu().getKhunggio().getBatdau();
//                int phuthuy = veon.getSuatchieu().getPhuthuyonline();
//                int hour = phuthuy/60;
//                int min = phuthuy%60;
//                LocalTime localTime = LocalTime.of(timehuy.getHour()-hour,timehuy.getMinute()-min);

                if(veonlineService.getStt(veon)==0){
                    gheResponses.add(GheResponse.builder().id(veon.getGhe().getId()).trangthai(1).build());
                }

            });

            for(VeResponse ve1 : veResponses){
                if(ve1.getIdsuatchieu().equals(idsuatchieu)){
                    gheResponses.add(GheResponse.builder()
                            .id(ve1.getIdghe())
                            .trangthai(3)
                            .build());
                }
            }
            for(VeResponse ve1 : veResponsesCurent){
                if(ve1.getIdsuatchieu().equals(idsuatchieu)){
                    gheResponses.add(GheResponse.builder()
                            .id(ve1.getIdghe())
                            .trangthai(2)
                            .build());
                }
            }
        }
        return ResponseEntity.ok().body(gheResponses);
    }

    @PostMapping("/getSuatChieu/{idsuatchieu}")
    public ResponseEntity getSuatChieu(@PathVariable Integer idsuatchieu){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        Suatchieu suatchieu =suatChieuService.findById(idsuatchieu);
        return ResponseEntity.ok().body(SuatChieuResponse.builder()
                .id(suatchieu.getId())
                .idphim(suatchieu.getPhim().getTen())
                .batdau(suatchieu.getKhunggio().getBatdau().format(dateTimeFormatter))
                .ketthuc(suatchieu.getKhunggio().getKetthuc().format(dateTimeFormatter))
                .dongia(Double.valueOf(suatchieu.getDongia()))
                .build());
    }


    @PostMapping("/getGheChoosen")
    public ResponseEntity getGheChoosen(HttpSession session){
        List<VeResponse> veResponsesCurent = new ArrayList<>();
        if(session.getAttribute("veresponse")!=null){
            veResponsesCurent =(List<VeResponse>) session.getAttribute("veresponse");
            veResponsesCurent.forEach(veResponse -> {
                Ghe ghe = gheService.findGheById(veResponse.getIdghe()).get();
                veResponse.setGheResponse(GheResponse.builder()
                        .id(ghe.getId())
                        .col(ghe.getCol())
                        .tenDay(ghe.getDayghe().getTen())
                        .build());
            });
        }
        return ResponseEntity.ok().body(veResponsesCurent);
    }

    @PostMapping("/getTotal")
    public ResponseEntity getTotal(HttpSession session,@RequestParam(value = "idsukien",required = false) String idsukien){
        float tongcong = 0;
        float giam = (idsukien.equals("noevent"))?0:sukienService.findSukienById(idsukien).get().getGiam();
        List<VeResponse> veResponsesCurent =(List<VeResponse>) session.getAttribute("veresponse");
        List<Ghe> ghes = new ArrayList<>();
        Suatchieu suatchieu = suatChieuService.findById(veResponsesCurent.get(0).getIdsuatchieu());
        for(VeResponse veResponse : veResponsesCurent){
            Ghe ghe = gheService.findGheById(veResponse.getIdghe()).get();
            tongcong += suatchieu.getDongia().floatValue()+ghe.getDayghe().getGia().floatValue()+ghe.getLoaighe().getGia().floatValue()-giam;
        }
        return ResponseEntity.ok().body(tongcong);
    }

    @PostMapping("/saveTicket")
    public ResponseEntity saveTicket(Authentication authentication,HttpSession session, @RequestParam(value = "idsukien",required = false)  String idsukien){
        List<String[]> veon2 = new ArrayList<>();
        try{

            List<VeResponse> veResponsesCurent =(List<VeResponse>) session.getAttribute("veresponse");

            for (VeResponse veResponse:veResponsesCurent) {
                String[] ob = new String[]{veResponse.getIdghe().toString(),veResponse.getIdsuatchieu().toString()};
                veon2.add(ob);
                veService.insert(veResponsesCurent.get(0).getIdsuatchieu(),veResponse.getIdghe(),idsukien,authentication.getName());
            } session.setAttribute("veresponse",new ArrayList<VeResponse>());
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return ResponseEntity.ok().body(veon2);
    }

    @GetMapping("/clearsession")
    public void clearSession(HttpSession session) {
        session.setAttribute("veresponse",new ArrayList<VeResponse>());
    }

    @GetMapping("/close")
    public void killSession(HttpSession session) {
        session.removeAttribute("veresponse");
    }
}
