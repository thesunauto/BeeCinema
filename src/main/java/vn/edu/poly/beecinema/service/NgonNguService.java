package vn.edu.poly.beecinema.service;

import vn.edu.poly.beecinema.entity.Ngonngu;

import java.util.List;
import java.util.Optional;

public interface NgonNguService {
    List<Ngonngu> getAllNgonNgu();

    void saveNgonNgu(Ngonngu ngonNgu);

    void deleteNgonNgu(String id);

    Optional<Ngonngu> findNgonNguById(String id);
}
