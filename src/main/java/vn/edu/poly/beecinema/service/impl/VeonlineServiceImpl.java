package vn.edu.poly.beecinema.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.poly.beecinema.entity.Suatchieu;
import vn.edu.poly.beecinema.entity.Veonline;
import vn.edu.poly.beecinema.repository.SuatchieuRepository;
import vn.edu.poly.beecinema.repository.VeonlineRepository;
import vn.edu.poly.beecinema.service.VeonlineService;

import java.util.List;

@Service
public class VeonlineServiceImpl implements VeonlineService {
    @Autowired
   private VeonlineRepository veonlineRepository;
    @Autowired
    private SuatchieuRepository suatchieuRepository;
    @Override
    public List<Veonline> findAllByIdSuatchieu(Integer idsuatchieu) {
        return veonlineRepository.findAllBySuatchieu(suatchieuRepository.getOne(idsuatchieu));
    }
}
