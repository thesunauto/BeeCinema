package vn.edu.poly.beecinema.service;

import org.springframework.data.domain.Page;
import vn.edu.poly.beecinema.entity.LoaiPhim;

import java.util.List;
import java.util.Optional;

public interface LoaiPhimService {
    List<LoaiPhim> getAllLoaiPhim();

    void saveLoaiPhim(LoaiPhim loaiPhim);

    void deleteLoaiPhim(String id);

    Optional<LoaiPhim> findLoaiPhimById(String id);

    Page<LoaiPhim> listAll(int pageNumber, String sortField, String sortDir, String keyword);
}
