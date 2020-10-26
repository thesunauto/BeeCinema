package vn.edu.poly.beecinema.service;

import vn.edu.poly.beecinema.entity.Phim;

import java.util.List;
import java.util.Optional;

public interface PhimService {
    List<Phim> getAllPhim();

    void savePhim(Phim loaiPhim);

    void deletePhim(String id);

    Optional<Phim> findPhimById(String id);
}
