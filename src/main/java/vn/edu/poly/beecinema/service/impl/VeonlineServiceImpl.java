package vn.edu.poly.beecinema.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.poly.beecinema.entity.*;
import vn.edu.poly.beecinema.repository.GheRepository;
import vn.edu.poly.beecinema.repository.SuatchieuRepository;
import vn.edu.poly.beecinema.repository.TaikhoanRepository;
import vn.edu.poly.beecinema.repository.VeonlineRepository;
import vn.edu.poly.beecinema.service.SukienService;
import vn.edu.poly.beecinema.service.VeonlineService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class VeonlineServiceImpl implements VeonlineService {
    @Autowired
    private VeonlineRepository veonlineRepository;
    @Autowired
    private SuatchieuRepository suatchieuRepository;
    @Autowired
    private GheRepository gheRepository;
    @Autowired
    private TaikhoanRepository taikhoanRepository;
    @Autowired
    private SukienService sukienService;

    @Override
    public List<Veonline> findAllByIdSuatchieu(Integer idsuatchieu) {
        return veonlineRepository.findAllBySuatchieu(suatchieuRepository.getOne(idsuatchieu));
    }

    @Override
    public Boolean insert(Integer idsuatchieu, Integer idghe, String idsukien, String username) {
        Sukien sukien = idsukien.equals("noevent") ? null : sukienService.findSukienById(idsukien).get();
        Suatchieu suatchieu = suatchieuRepository.findById(idsuatchieu).get();
        Ghe ghe = gheRepository.findById(idghe).get();
        Taikhoan taikhoan = taikhoanRepository.findById(username).get();
        if(veonlineRepository.findAllBySuatchieuAndTaikhoan(suatchieu,taikhoan).size()>3){
            return false;
        }
        veonlineRepository.save(Veonline.builder()
                .veonlineID(VeonlineID.builder().idghe(idghe).idsuatchieu(idsuatchieu).build())
                .trangthai(0)
                .sukien(sukien)
                .ngaytao(LocalDateTime.now())
                .taikhoan(taikhoan)
                .build());
        return true;
    }


    @Override
    public List<Veonline> findAllByToday() {
        List<Veonline> veonlines = new ArrayList<>();
        suatchieuRepository.findAll().forEach(suatchieu -> {

            if (suatchieu.getNgaychieu().equals(LocalDate.now())) {
                veonlines.addAll(findAllByIdSuatchieu(suatchieu.getId()));
            }
        });
        return veonlines;
    }


    /**
     * @return 0: chưa xác nhận
     * 1: đã xác nhận
     * 2: hết hạn
     */
    @Override
    public Integer getStt(Veonline veonline) {
        long phuhuyonline = veonline.getSuatchieu().getPhuthuyonline();
        phuhuyonline = suatchieuRepository.getOne(veonline.getSuatchieu().getId()).getPhuthuyonline();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime activeT = LocalDateTime.of(veonline.getSuatchieu().getNgaychieu(), veonline.getSuatchieu().getKhunggio().getBatdau());
        LocalDateTime active = activeT.minusMinutes(veonline.getSuatchieu().getPhuthuyonline().longValue());
        int trangThai = (now.compareTo(active) >= 0) ? 2 : 0;
        return (veonline.getTrangthai() == 0) ? trangThai : 1;
    }

    @Override
    public Veonline findByVeonlineID(VeonlineID veonlineID) {
        return veonlineRepository.getVeonlineByVeonlineID(veonlineID);
    }

    @Override
    public void save(Veonline veonline) {
        veonlineRepository.save(veonline);
    }
}
