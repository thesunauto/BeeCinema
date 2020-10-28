package vn.edu.poly.beecinema.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.poly.beecinema.entity.Ghe;
import vn.edu.poly.beecinema.repository.GheRepository;
import vn.edu.poly.beecinema.service.GheService;


import java.util.List;
import java.util.Optional;

@Service
public class GheServiceImpl implements GheService {
    @Autowired private GheRepository GheRepository;
    @Override
    public List<Ghe> getAllGhe() {
        return (List<Ghe>) GheRepository.findAll();
    }

    @Override
    public void saveGhe(Ghe ghe) {
        GheRepository.save(ghe);
    }

    @Override
    public void deleteGhe(String id) {
        GheRepository.deleteById(id);
    }

    @Override
    public Optional<Ghe> findGheById(String id) {
        return GheRepository.findById(id);
    }
}
