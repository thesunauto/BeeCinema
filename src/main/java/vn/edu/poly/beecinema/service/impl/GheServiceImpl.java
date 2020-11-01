package vn.edu.poly.beecinema.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.poly.beecinema.entity.Ghe;
import vn.edu.poly.beecinema.repository.DaygheRepository;
import vn.edu.poly.beecinema.repository.GheRepository;
import vn.edu.poly.beecinema.repository.PhongRepository;
import vn.edu.poly.beecinema.service.GheService;


import java.util.List;
import java.util.Optional;

@Service
public class GheServiceImpl implements GheService {
    @Autowired private GheRepository GheRepository;
    @Autowired private PhongRepository phongRepository;
    @Autowired private DaygheRepository daygheRepository;
    @Override
    public List<Ghe> findByPhongAndDayGhe(String idphong, String idDayGhe) {
        return GheRepository.findByPhongAndDayghe(phongRepository.getOne(idphong),daygheRepository.getOne(idDayGhe));
    }

    @Override
    public List<Ghe> getAllGhe() {
        return (List<Ghe>) GheRepository.findAll();
    }

    @Override
    public void saveGhe(Ghe ghe) {
        GheRepository.save(ghe);
    }

    @Override
    public void deleteGhe(Integer id) {
        GheRepository.deleteById(id);
    }

    @Override
    public Optional<Ghe> findGheById(Integer id) {
        return GheRepository.findById(id);
    }
}
