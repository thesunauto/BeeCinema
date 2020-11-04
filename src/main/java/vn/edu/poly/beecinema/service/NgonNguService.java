package vn.edu.poly.beecinema.service;

import vn.edu.poly.beecinema.entity.NgonNgu;

import java.util.List;
import java.util.Optional;

public interface NgonNguService {
    List<NgonNgu> getAllNgonNgu();

    void saveNgonNgu(NgonNgu ngonNgu);

    void deleteNgonNgu(String id);

    Optional<NgonNgu> findNgonNguById(String id);
}
