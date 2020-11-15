package vn.edu.poly.beecinema.service;

import org.springframework.data.domain.Page;
import vn.edu.poly.beecinema.entity.Phim;

import java.util.List;
import java.util.Optional;

public interface PhimService {
    List<Phim> getAllPhim();

    List<Phim> getAllPhimAlive();

    void savePhim(Phim loaiPhim);

    void deletePhim(String id);

    Optional<Phim> findPhimById(String id);

    Page<Phim> listAll(int pageNumber, String sortField, String sortDir, String keyword);
}
