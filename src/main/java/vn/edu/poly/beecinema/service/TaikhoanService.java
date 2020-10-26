package vn.edu.poly.beecinema.service;

import vn.edu.poly.beecinema.entity.Taikhoan;

import java.util.List;
import java.util.Optional;

public interface TaikhoanService {
    List<Taikhoan> getAllTaikhoan();

    void saveTaikhoan(Taikhoan taikhoan);

    void deleteTaikhoan(String id);

    Optional<Taikhoan> findTaikhoanById(String id);
}
