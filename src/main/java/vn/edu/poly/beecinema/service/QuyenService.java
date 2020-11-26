package vn.edu.poly.beecinema.service;

import vn.edu.poly.beecinema.entity.Quyen;


import java.util.List;
import java.util.Optional;

public interface QuyenService {
    List<Quyen> findAll(Integer limit);

    List<Quyen> getAllQuyen();

    void saveQuyen(Quyen quyen);

    void deleteQuyen(String id);

    Optional<Quyen> findQuyenById(String id);

    Quyen getQuyenById(String id);

}
