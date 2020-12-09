package vn.edu.poly.beecinema.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.poly.beecinema.entity.Suatchieu;
import vn.edu.poly.beecinema.entity.Ve;
import vn.edu.poly.beecinema.repository.GheRepository;
import vn.edu.poly.beecinema.repository.SuatchieuRepository;
import vn.edu.poly.beecinema.repository.TaikhoanRepository;
import vn.edu.poly.beecinema.repository.VeRepository;
import vn.edu.poly.beecinema.service.SukienService;
import vn.edu.poly.beecinema.service.ThongKeService;

import java.time.LocalDate;
import java.util.List;

@Service
public class ThongKeServiceImpl implements ThongKeService {
    @Autowired
    private VeRepository veRepository;
    @Autowired private SuatchieuRepository suatchieuRepository;
    @Autowired private GheRepository gheRepository;
    @Autowired private TaikhoanRepository taikhoanRepository;
    @Autowired private SukienService sukienService;

    @Override
    public List<Ve> findAllVeByToDay() {
//        veRepository.findAllByTrangthaiAndSuatchieuNgaychieu(0,LocalDate.now());
        return veRepository.findAllByTrangthaiAndSuatchieuNgaychieu(0,LocalDate.now());
    }

//    @Override
//    public List<Suatchieu> findAllByTrangthaiAndNgaychieu() {
//        return suatchieuRepository.findAllByTrangthaiAndNgaychieu_MonthValue(0, 12);
//    }

    @Override
    public List<Suatchieu> findAllByTrangthai() {
        return suatchieuRepository.findAllByTrangthai(0);
    }

    @Override
    public Long countSlPhimTheoThang() {
        return suatchieuRepository.countSlPhimTheoThang();
    }

    @Override
    public Long countVeTheoThang() {
        return veRepository.countVeTheoThang();
    }

    @Override
    public Long countDoanhThuTheoThang() {
        return veRepository.countDoanhThuTheoThang();
    }


}
