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
import vn.edu.poly.beecinema.service.ThongKeService;
import vn.edu.poly.beecinema.service.VeService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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




}
