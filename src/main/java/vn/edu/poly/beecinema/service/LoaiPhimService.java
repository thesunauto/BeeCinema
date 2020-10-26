package vn.edu.poly.beecinema.service;

import vn.edu.poly.beecinema.entity.LoaiPhim;

import java.util.List;
import java.util.Optional;

public interface LoaiPhimService {
    List<LoaiPhim> getAllLoaiPhim();

    void saveLoaiPhim(LoaiPhim loaiPhim);

    void deleteLoaiPhim(String id);

    Optional<LoaiPhim> findLoaiPhimById(String id);
}
