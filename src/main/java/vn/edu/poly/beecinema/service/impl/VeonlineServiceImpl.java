package vn.edu.poly.beecinema.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.poly.beecinema.entity.*;
import vn.edu.poly.beecinema.repository.GheRepository;
import vn.edu.poly.beecinema.repository.SuatchieuRepository;
import vn.edu.poly.beecinema.repository.TaikhoanRepository;
import vn.edu.poly.beecinema.repository.VeonlineRepository;
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

    @Override
    public List<Veonline> findAllByIdSuatchieu(Integer idsuatchieu) {
        return veonlineRepository.findAllBySuatchieu(suatchieuRepository.getOne(idsuatchieu));
    }



    @Override
    public void insert(Integer idsuatchieu, Integer idghe) {
        Suatchieu suatchieu = suatchieuRepository.findById(idsuatchieu).get();
        Ghe ghe = gheRepository.findById(idghe).get();
        veonlineRepository.save(Veonline.builder()
                .veonlineID(VeonlineID.builder().idghe(idghe).idsuatchieu(idsuatchieu).build())
                .trangthai(0)
                .ngaytao(LocalDateTime.now())
                .taikhoan(taikhoanRepository.findById("nhanpt").get())
                .build());
    }

    @Override
    public List<Veonline> findAllByToday() {
        List<Veonline> veonlines = new ArrayList<>();
        suatchieuRepository.findAll().forEach(suatchieu -> {

            if (suatchieu.getNgaychieu().equals(LocalDate.now())) {
                veonlines.addAll(findAllByIdSuatchieu(suatchieu.getId()));
            }
        });
        veonlines.forEach(veonline -> System.out.println(veonline));
        return veonlines;
    }


    /**
     * @return 0: chưa xác nhận
     *         1: đã xác nhận
     *         2: hết hạn
     */
    @Override
    public Integer getStt(Veonline veonline) {
        int phuhuyonline = veonline.getSuatchieu().getPhuthuyonline();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime active = LocalDateTime.of(veonline.getSuatchieu().getNgaychieu(), veonline.getSuatchieu().getKhunggio().getBatdau());
        active.minusMinutes(phuhuyonline);
        int trangThai = (now.compareTo(active)>0)?2:0;
        return (veonline.getTrangthai()==0)?trangThai:1;
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
