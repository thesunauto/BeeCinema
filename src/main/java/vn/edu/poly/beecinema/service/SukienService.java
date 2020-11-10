package vn.edu.poly.beecinema.service;

import org.springframework.data.domain.Page;
import vn.edu.poly.beecinema.entity.Sukien;

import java.util.List;
import java.util.Optional;

public interface SukienService {
    List<Sukien> getAllSukien();

    void saveSukien(Sukien sukien);

    void deleteSukien(String id);

    Optional<Sukien> findSukienById(String id);

    Page<Sukien> listAll(int pageNumber, String sortField, String sortDir, String keyword);
}
