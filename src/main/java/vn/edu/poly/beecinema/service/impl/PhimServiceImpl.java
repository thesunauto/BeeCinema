package vn.edu.poly.beecinema.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.poly.beecinema.entity.Phim;
import vn.edu.poly.beecinema.repository.PhimRepository;
import vn.edu.poly.beecinema.service.PhimService;

import java.util.List;
import java.util.Optional;

@Service
public class PhimServiceImpl implements PhimService {
    @Autowired private PhimRepository phimRepository;
    @Override
    public List<Phim> getAllPhim() {
        return (List<Phim>) phimRepository.findAll();
    }

    @Override
    public void savePhim(Phim phim) {
        phimRepository.save(phim);
    }

    @Override
    public void deletePhim(String id) {
        phimRepository.deleteById(id);
    }

    @Override
    public Optional<Phim> findPhimById(String id) {
        return phimRepository.findById(id);
    }
}
