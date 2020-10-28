package vn.edu.poly.beecinema.service;

import vn.edu.poly.beecinema.entity.Ghe;


import java.util.List;
import java.util.Optional;

public interface GheService {
    List<Ghe> getAllGhe();

    void saveGhe(Ghe ghe);

    void deleteGhe(String id);

    Optional<Ghe> findGheById(String id);
}
