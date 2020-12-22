package vn.edu.poly.beecinema.service;

import org.springframework.data.domain.Page;
import vn.edu.poly.beecinema.entity.Khunggio;
import vn.edu.poly.beecinema.entity.Suatchieu;
import vn.edu.poly.beecinema.entity.Sukien;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    Page<Suatchieu> listAll(int pageNumber, String sortField, String sortDir, String keyword);

    List<Suatchieu> findAllByPhimAndDate(String idPhim,LocalDate ngayChieu);


}
