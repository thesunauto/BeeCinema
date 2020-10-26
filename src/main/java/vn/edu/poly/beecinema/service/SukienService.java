package vn.edu.poly.beecinema.service;

import vn.edu.poly.beecinema.entity.Sukien;

import java.util.List;
import java.util.Optional;

public interface SukienService {
    List<Sukien> getAllSukien();

    void saveSukien(Sukien sukien);

    void deleteSukien(String id);

    Optional<Sukien> findSukienById(String id);
}
