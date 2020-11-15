package vn.edu.poly.beecinema.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.poly.beecinema.entity.*;
import vn.edu.poly.beecinema.repository.GheRepository;
import vn.edu.poly.beecinema.repository.SuatchieuRepository;
import vn.edu.poly.beecinema.repository.TaikhoanRepository;
import vn.edu.poly.beecinema.repository.VeonlineRepository;
import vn.edu.poly.beecinema.service.VeonlineService;

import java.util.List;

@Service
public class VeonlineServiceImpl implements VeonlineService {
    @Autowired
   private VeonlineRepository veonlineRepository;
    @Autowired
    private SuatchieuRepository suatchieuRepository;
    @Autowired
    private GheRepository gheRepository;
    @Autowired private TaikhoanRepository taikhoanRepository;

    @Override
    public List<Veonline> findAllByIdSuatchieu(Integer idsuatchieu) {
        return veonlineRepository.findAllBySuatchieu(suatchieuRepository.getOne(idsuatchieu));
    }

    @Override
    public void insert(Integer idsuatchieu,Integer idghe) {
        Suatchieu suatchieu = suatchieuRepository.findById(idsuatchieu).get();
        Ghe ghe = gheRepository.findById(idghe).get();
        veonlineRepository.save(Veonline.builder()
                .veonlineID(VeonlineID.builder().idghe(idghe).idsuatchieu(idsuatchieu).build())
                .trangthai(0)
                .taikhoan(taikhoanRepository.findById("nhanpt").get())
                .build());
    }
}
