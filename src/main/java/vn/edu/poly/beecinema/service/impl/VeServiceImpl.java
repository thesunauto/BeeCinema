package vn.edu.poly.beecinema.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.edu.poly.beecinema.entity.*;
import vn.edu.poly.beecinema.repository.GheRepository;
import vn.edu.poly.beecinema.repository.SuatchieuRepository;
import vn.edu.poly.beecinema.repository.TaikhoanRepository;
import vn.edu.poly.beecinema.repository.VeRepository;
import vn.edu.poly.beecinema.service.SukienService;
import vn.edu.poly.beecinema.service.VeService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    public Page<Ve> listAll(int pageNumber, String sortField, String sortDir, String keyword) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 5,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending()
        );
        if (keyword != null) {
            return veRepository.findAll(keyword, pageable);
        }
        return  veRepository.findAll(pageable);
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

    @Override
    public List<Ve> findAllByToDay() {
        List<Ve>ves = new ArrayList<>();
        veRepository.findAllByTrangthaiOrderByNgaytaoDesc(0).forEach(ve -> {
            if(ve.getNgaytao().toLocalDate().equals(LocalDate.now())){
                ves.add(ve);
            }
        });
        return ves;
    }

    @Override
    public List<Ve> findByVeID(VeID veID) {
        return veRepository.getVeByVeID(veID);
    }


}
