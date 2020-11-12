package vn.edu.poly.beecinema.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.poly.beecinema.entity.Khunggio;
import vn.edu.poly.beecinema.entity.Suatchieu;
import vn.edu.poly.beecinema.repository.SuatchieuRepository;
import vn.edu.poly.beecinema.service.PhimService;
import vn.edu.poly.beecinema.service.SuatChieuService;

import java.util.List;
import java.util.Optional;

@Service
public class SuatChieuServiceImpl implements SuatChieuService {
    @Autowired
    SuatchieuRepository suatchieuRepository;
@Autowired private PhimService phimService;
    @Override
    public Suatchieu findById(Integer id) {
        return Optional.ofNullable(id).map(integer -> suatchieuRepository.getOne(id)).orElse(null);
    }

    @Override
    public List<Suatchieu> getAllSuatChieu() {
        return (List<Suatchieu>) suatchieuRepository.findAllByTrangthai(0);
    }

    @Override
    public List<Suatchieu> getAllSuatChieuByPhim(String idphim) {
        return suatchieuRepository.findAllByPhimAndTrangthai(phimService.findPhimById(idphim).get(),0);
    }

    @Override
    public void saveSuatChieu(Suatchieu suatChieu) {
        suatchieuRepository.save(suatChieu);
    }

    @Override
    public void deleteSuatChieu(Integer id) {
        suatchieuRepository.deleteById(id);
    }

    @Override
    public Optional<Suatchieu> findSuatChieuById(Integer id) {
        return suatchieuRepository.findById(id);
    }


}
