package vn.edu.poly.beecinema.service;

import vn.edu.poly.beecinema.entity.Khunggio;
import vn.edu.poly.beecinema.entity.LoaiPhim;

import java.util.List;
import java.util.Optional;

public interface KhungGioService {
    List<Khunggio> getAllKhungGio();

    void saveKhungGio(Khunggio khungGio);

    void deleteKhungGio(String id);

    Optional<Khunggio> findKhungGioById(String id);
}
