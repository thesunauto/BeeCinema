package vn.edu.poly.beecinema.service;

import vn.edu.poly.beecinema.entity.Ghe;


import java.util.List;
import java.util.Optional;

public interface GheService {
    List<Ghe> findByPhongAndDayGhe(String idphong,String idDayGhe);

    List<Ghe> getAllGhe();

    void saveGhe(Ghe ghe);

    void deleteGhe(Integer id);

    Optional<Ghe> findGheById(Integer id);
}
