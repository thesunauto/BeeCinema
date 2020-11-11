package vn.edu.poly.beecinema.service;

import org.springframework.data.domain.Page;
import vn.edu.poly.beecinema.entity.LoaiPhim;
import vn.edu.poly.beecinema.entity.NgonNgu;

import java.util.List;
import java.util.Optional;

public interface NgonNguService {
    List<NgonNgu> getAllNgonNgu();

    void saveNgonNgu(NgonNgu ngonNgu);

    void deleteNgonNgu(String id);

    Optional<NgonNgu> findNgonNguById(String id);

    Page<NgonNgu> listAll(int pageNumber, String sortField, String sortDir, String keyword);
}
