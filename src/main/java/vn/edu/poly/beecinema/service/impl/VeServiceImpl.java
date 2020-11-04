package vn.edu.poly.beecinema.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.poly.beecinema.entity.Ve;
import vn.edu.poly.beecinema.repository.GheRepository;
import vn.edu.poly.beecinema.repository.SuatchieuRepository;
import vn.edu.poly.beecinema.repository.VeRepository;
import vn.edu.poly.beecinema.service.VeService;

@Service
public class VeServiceImpl implements VeService {
    @Autowired
    private VeRepository veRepository;
    @Autowired private SuatchieuRepository suatchieuRepository;
    @Autowired private GheRepository gheRepository;
    @Override
    public Boolean IsExists(Integer idSuatChieu, Integer idGhe) {
        return veRepository.findByGheAndSuatchieu(gheRepository.findById(idGhe).orElse(null),suatchieuRepository.findById(idSuatChieu).orElse(null))!=null;
    }
}
