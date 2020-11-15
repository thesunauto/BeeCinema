package vn.edu.poly.beecinema.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.poly.beecinema.entity.*;
import vn.edu.poly.beecinema.repository.GheRepository;
import vn.edu.poly.beecinema.repository.SuatchieuRepository;
import vn.edu.poly.beecinema.repository.TaikhoanRepository;
import vn.edu.poly.beecinema.repository.VeRepository;
import vn.edu.poly.beecinema.service.SukienService;
import vn.edu.poly.beecinema.service.VeService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VeServiceImpl implements VeService {
    @Autowired
    private VeRepository veRepository;
    @Autowired private SuatchieuRepository suatchieuRepository;
    @Autowired private GheRepository gheRepository;
    @Autowired private TaikhoanRepository taikhoanRepository;
    @Autowired private SukienService sukienService;
    @Override
    public Boolean IsExists(Integer idSuatChieu, Integer idGhe) {
        return veRepository.findByGheAndSuatchieu(gheRepository.findById(idGhe).orElse(null),suatchieuRepository.findById(idSuatChieu).orElse(null))!=null;
    }

    @Override
    public List<Ve> findAllByIdSuatchieu(Integer idsuatchieu) {
        return veRepository.findAllBySuatchieu(suatchieuRepository.getOne(idsuatchieu));
    }

    @Override
    public void insert(Integer idsuatchieu,Integer idghe, String idsukien, String username) {
        Sukien sukien = idsukien.equals("noevent")?null:sukienService.findSukienById(idsukien).get();
        Suatchieu suatchieu = suatchieuRepository.findById(idsuatchieu).get();
        Ghe ghe = gheRepository.findById(idghe).get();
        veRepository.save(Ve.builder()
                .veID(VeID.builder().idghe(idghe).idsuatchieu(idsuatchieu).build())
                .trangthai(0)
                .sukien(sukien)
                .ngaytao(LocalDateTime.now())
                .taikhoan(taikhoanRepository.findById(username).get())
                .build());
    }


}
