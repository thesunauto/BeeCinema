package vn.edu.poly.beecinema.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.poly.beecinema.entity.Suatchieu;
import vn.edu.poly.beecinema.repository.SuatchieuRepository;
import vn.edu.poly.beecinema.service.SuatChieuService;

import java.util.Optional;

@Service
public class SuatChieuServiceImpl implements SuatChieuService {
    @Autowired
    SuatchieuRepository suatchieuRepository;

    @Override
    public Suatchieu findById(Integer id) {
        return Optional.ofNullable(id).map(integer -> suatchieuRepository.getOne(id)).orElse(null);
    }
}
