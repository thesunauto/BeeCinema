package vn.edu.poly.beecinema.service;

import vn.edu.poly.beecinema.entity.Khunggio;
import vn.edu.poly.beecinema.entity.Suatchieu;

import java.util.List;
import java.util.Optional;

public interface SuatChieuService {
    Suatchieu findById(Integer id);

    List<Suatchieu> getAllSuatChieu();

    void saveSuatChieu(Suatchieu suatChieu);

    void deleteSuatChieu(Integer id);

    Optional<Suatchieu> findSuatChieuById(Integer id);
}
