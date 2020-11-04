package vn.edu.poly.beecinema.service;

import vn.edu.poly.beecinema.entity.Binhluan;

import java.util.List;
import java.util.Optional;

public interface BinhluanService {
    List<Binhluan> getAllBinhluan();

    void saveBinhluan(Binhluan binhluan);

    void deleteBinhluan(Integer id);

    Optional<Binhluan> findBinhluanById(Integer id);


}
