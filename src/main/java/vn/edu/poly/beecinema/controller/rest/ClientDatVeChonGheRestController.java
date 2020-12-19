package vn.edu.poly.beecinema.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.poly.beecinema.commons.*;
import vn.edu.poly.beecinema.config.HttpSessionConfig;
import vn.edu.poly.beecinema.entity.*;
import vn.edu.poly.beecinema.repository.VeonlineRepository;
import vn.edu.poly.beecinema.service.*;
import vn.edu.poly.beecinema.storage.StorageService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/datve")
public class ClientDatVeChonGheRestController {
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
    private VeonlineRepository veonlineRepository;
    @Autowired
    private VeonlineService veonlineService;
    @Autowired
    private SukienService sukienService;
    @Autowired
    private TaikhoanService taikhoanService;
    @Autowired
    private JavaMailSender mailSender;

    public ClientDatVeChonGheRestController(StorageService storageService) {
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
    public ResponseEntity loadGhe(@PathVariable Integer idsuatchieu) {
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
    public ResponseEntity gettrangthaighe(HttpSessionConfig httpSessionConfig, HttpSession httpSession, @PathVariable Integer idsuatchieu) {
        List<GheResponse> gheResponses = new ArrayList<>();
        if (idsuatchieu != -1) {
            List<VeResponse> veResponses = new ArrayList<>();
            List<VeResponse> veResponsesCurent = (List<VeResponse>) httpSession.getAttribute("veresponse");
            List<HttpSession> httpSessions = httpSessionConfig.getActiveSessions();
            if (!httpSessions.isEmpty()) {
                httpSessions.forEach(session -> {
                    if (session != null) {
                        try {
                            if (session.getId() != httpSession.getId()) {
                                if (session.getAttribute("veresponse") != null) {
                                    veResponses.addAll((List<VeResponse>) session.getAttribute("veresponse"));
                                }
                            }

                        } catch (Exception e) {
                        }
                    }
                });
            }


            veService.findAllByIdSuatchieu(idsuatchieu).forEach(ve -> {
                gheResponses.add(GheResponse.builder().id(ve.getGhe().getId()).trangthai(1).build());
            });
            veonlineService.findAllByIdSuatchieu(idsuatchieu).forEach(veon -> {


                gheResponses.add(GheResponse.builder().id(veon.getGhe().getId()).trangthai(1).build());


            });

            for (VeResponse ve1 : veResponses) {
                if (ve1.getIdsuatchieu().equals(idsuatchieu)) {
                    gheResponses.add(GheResponse.builder()
                            .id(ve1.getIdghe())
                            .trangthai(3)
                            .build());
                }
            }
            for (VeResponse ve1 : veResponsesCurent) {
                if (ve1.getIdsuatchieu().equals(idsuatchieu)) {
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
    public ResponseEntity getSuatChieu(@PathVariable Integer idsuatchieu) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        Suatchieu suatchieu = suatChieuService.findById(idsuatchieu);
        return ResponseEntity.ok().body(SuatChieuResponse.builder()
                .id(suatchieu.getId())
                .idphim(suatchieu.getPhim().getTen())
                .batdau(suatchieu.getKhunggio().getBatdau().format(dateTimeFormatter))
                .ketthuc(suatchieu.getKhunggio().getKetthuc().format(dateTimeFormatter))
                .dongia(Double.valueOf(suatchieu.getDongia()))
                .build());
    }


    @PostMapping("/getGheChoosen")
    public ResponseEntity getGheChoosen(HttpSession session) {
        List<VeResponse> veResponsesCurent = new ArrayList<>();
        if (session.getAttribute("veresponse") != null) {
            veResponsesCurent = (List<VeResponse>) session.getAttribute("veresponse");
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
    public ResponseEntity getTotal(HttpSession session, @RequestParam(value = "idsukien", required = false) String idsukien) {
        float tongcong = 0;
        float giam = (idsukien.equals("noevent")) ? 0 : sukienService.findSukienById(idsukien).get().getGiam();
        List<VeResponse> veResponsesCurent = (List<VeResponse>) session.getAttribute("veresponse");
        List<Ghe> ghes = new ArrayList<>();
        Suatchieu suatchieu = suatChieuService.findById(veResponsesCurent.get(0).getIdsuatchieu());
        for (VeResponse veResponse : veResponsesCurent) {
            Ghe ghe = gheService.findGheById(veResponse.getIdghe()).get();
            tongcong += suatchieu.getDongia().floatValue() + ghe.getDayghe().getGia().floatValue() + ghe.getLoaighe().getGia().floatValue() - giam;
        }
        return ResponseEntity.ok().body(tongcong);
    }

    @PostMapping("/saveTicket")
    public ResponseEntity saveTicket(Authentication authentication, HttpSession session, @RequestParam(value = "idsukien", required = false) String idsukien) {


        try {
            List<VeResponse> veResponsesCurent = (List<VeResponse>) session.getAttribute("veresponse");
            if ((veResponsesCurent.size() + veonlineRepository.findAllBySuatchieuAndTaikhoan(suatChieuService.findById(veResponsesCurent.get(0).getIdsuatchieu()), taikhoanService.findTaikhoanById(authentication.getName()).get()).size()) <= 4) {
                for (VeResponse veResponse : veResponsesCurent) {
                    if (!veonlineService.insert(veResponsesCurent.get(0).getIdsuatchieu(), veResponse.getIdghe(), idsukien, authentication.getName())) {
                        session.setAttribute("veresponse", new ArrayList<VeResponse>());
                        return ResponseEntity.ok().body(false);
                    }
                }
                session.setAttribute("veresponse", new ArrayList<VeResponse>());
                return ResponseEntity.ok().body(true);
            } else {
                session.setAttribute("veresponse", new ArrayList<VeResponse>());
                return ResponseEntity.ok().body(false);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        session.setAttribute("veresponse", new ArrayList<VeResponse>());
        return ResponseEntity.ok().body(false);
    }

    @GetMapping("/clearsession")
    public void clearSession(HttpSession session) {
        session.setAttribute("veresponse", new ArrayList<VeResponse>());
    }

    @GetMapping("/close")
    public void killSession(HttpSession session) {
        session.removeAttribute("veresponse");
    }

    @PostMapping("/getSuatChieuByPhimAndDate/{idphim}/{date}")
    public ResponseEntity getSuatChieuByDate(@PathVariable String idphim, @PathVariable String date) {
        List<SuatChieuResponse> suatChieuResponses = new ArrayList<>();
        suatChieuService.findAllByPhimAndDate(idphim, LocalDateTime.parse(date).toLocalDate()).forEach(suatchieu -> {
            if (LocalDateTime.of(suatchieu.getNgaychieu(), suatchieu.getKhunggio().getBatdau()).minusMinutes(suatchieu.getPhuthuyonline()).compareTo(LocalDateTime.now()) > 0) {
                suatChieuResponses.add(SuatChieuResponse.builder()
                        .id(suatchieu.getId())
                        .batdau(suatchieu.getKhunggio().getBatdau().toString())
                        .ketthuc(suatchieu.getKhunggio().getKetthuc().toString())
                        .dongia(suatchieu.getDongia().doubleValue())
                        .idphim(suatchieu.getPhim().getId())
                        .idphong(suatchieu.getPhong().getId())
                        .tenphong(suatchieu.getPhong().getTen())
                        .build());
            }
        });
        return ResponseEntity.ok().body(suatChieuResponses);
    }

    @PostMapping("/getVeonlineUntil/{idsuatchieu}")
    public ResponseEntity getVeonlineUntil(Authentication authentication, @PathVariable Integer idsuatchieu) {
        return ResponseEntity.ok().body(veonlineRepository.findAllBySuatchieuAndTaikhoan(suatChieuService.findById(idsuatchieu), taikhoanService.findTaikhoanById(authentication.getName()).get()).size());
    }

    @PostMapping("/listTicketManage/{page}")
    public ResponseEntity listTicketManage(Authentication authentication,@PathVariable Integer page){
        List<VeonlineResponse> veonlineResponses = new ArrayList<>();
        veonlineService.getListByUser(taikhoanService.findTaikhoanById(authentication.getName()).get(),page,10).forEach(veonline -> {
            veonlineResponses.add(VeonlineResponse.builder()
                    .idsuatchieughe(veonline.getSuatchieu().getId()+"-"+veonline.getGhe().getId())
                    .ghe(veonline.getGhe().getDayghe().getTen()+"-"+veonline.getGhe().getCol())
                    .suatchieu(veonline.getSuatchieu().getKhunggio().getBatdau().format(DateTimeFormatter.ofPattern("HH:mm"))+"-"+veonline.getSuatchieu().getKhunggio().getKetthuc().format(DateTimeFormatter.ofPattern("HH:mm")))
                    .phong(veonline.getSuatchieu().getPhong().getTen())
                    .ngaychieu(veonline.getSuatchieu().getNgaychieu().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                    .ngaytao(veonline.getNgaytao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                    .hethan(LocalDateTime.of(veonline.getSuatchieu().getNgaychieu(),veonline.getSuatchieu().getKhunggio().getBatdau()).minusMinutes(veonline.getSuatchieu().getPhuthuyonline()).format(DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy")))
                    .tenphim(veonline.getSuatchieu().getPhim().getTen())
                    .trangthai((veonline.getTrangthai()==0&&(LocalDateTime.of(veonline.getSuatchieu().getNgaychieu(),veonline.getSuatchieu().getKhunggio().getBatdau()).minusMinutes(veonline.getSuatchieu().getPhuthuyonline()).compareTo(LocalDateTime.now())<0))?2:(veonline.getTrangthai()==1)?1:0)
                    .build());
        });


        return ResponseEntity.ok().body(veonlineResponses);
    }

    @PostMapping("/listTicketManageSize")
    public ResponseEntity listTicketManageSize(Authentication authentication){
        return ResponseEntity.ok().body(veonlineService.getListByUser(taikhoanService.findTaikhoanById(authentication.getName()).get()).size());
    }
    @PostMapping("/deleteTicketOnline/{id}")
    public ResponseEntity deleteTicketOnline(Authentication authentication,@PathVariable String id){
        try {
            Integer idghe = Integer.valueOf(id.substring(id.indexOf("-")+1));
            Integer idsuatchieu = Integer.valueOf(id.substring(0,id.indexOf("-")));
            veonlineService.delete(VeonlineID.builder().idsuatchieu(idsuatchieu).idghe(idghe).build());
            return ResponseEntity.ok().build();
        }catch (Exception e){
            e.printStackTrace();
        }

       return ResponseEntity.notFound().build();
    }
    @GetMapping("/sendMailTicket/{idsuatchieu}|{idghe}")
    public void sendMailTicket(@PathVariable Integer idsuatchieu, @PathVariable Integer idghe,
                           Authentication authentication, Model model){
        Veonline veonline = veonlineService.findByVeonlineID(new VeonlineID(idsuatchieu,idghe));
        String email = veonline.getTaikhoan().getEmail();
        String tenphim = veonline.getSuatchieu().getPhim().getTen();
        String ngaychieu = String.valueOf(veonline.getSuatchieu().getNgaychieu());
        String khunggio = veonline.getSuatchieu().getKhunggio().getBatdau() + " - " + veonline.getSuatchieu().getKhunggio().getKetthuc();
        String ghe = veonline.getGhe().getDayghe().getTen() +  String.valueOf(veonline.getGhe().getCol());
        try {
            sendMailTicket(email, tenphim, ngaychieu, khunggio, ghe);
        } catch (UnsupportedEncodingException | MessagingException e) {
            model.addAttribute("error", "Lỗi khi gửi email");
        }
    }
    private void sendMailTicket(String email, String tenphim, String ngaychieu, String khunggio, String ghe)
            throws UnsupportedEncodingException, MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("contact@Beecinema.com", "Beecinema");
        helper.setTo(email);

        String subject = "Đặt Vé Thành Công - BeeCinema";
        String content = "<p>Cảm ơn bạn đã đặt vé tại BeeCinema</p>"
                + "<p>Phim: "+ tenphim  + " </p>"
                + "<p>Thời gian: " + ngaychieu + " " + khunggio +" </p>"
                + "<p>Ghế: "+ ghe  + " </p>"
                + "<p>Lưu ý: xin vui lòng đến trước 30p để lấy vé, nếu sau 30p bạn không lấy vé sẽ tự động hủy. </p>"
                + "<p>Xin cảm ơn !</p>";

        helper.setSubject(subject);
        helper.setText(content, true);

        mailSender.send(message);
    }
}
