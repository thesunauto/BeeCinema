package vn.edu.poly.beecinema.service;

import vn.edu.poly.beecinema.entity.Khunggio;
import vn.edu.poly.beecinema.entity.Suatchieu;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface SuatChieuService {
    Suatchieu findById(Integer id);

    List<Suatchieu> getAllSuatChieu();

    List<Suatchieu> getAllSuatChieuByPhim(String idphim);

    List<Suatchieu> getAllSuatChieuByPhimAndToday(String idphim);

    void saveSuatChieu(Suatchieu suatChieu);

    void deleteSuatChieu(Integer id);

    Optional<Suatchieu> findSuatChieuById(Integer id);


}
