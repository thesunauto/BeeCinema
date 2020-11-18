package vn.edu.poly.beecinema.service;

import org.springframework.data.domain.Page;
import vn.edu.poly.beecinema.entity.Binhluan;

import java.util.List;
import java.util.Optional;

public interface BinhluanService {
    List<Binhluan> getAllBinhluan();

    void saveBinhluan(Binhluan binhluan);

    void deleteBinhluan(Integer id);

    Optional<Binhluan> findBinhluanById(Integer id);

    Page<Binhluan> listAll(int pageNumber, String sortField, String sortDir, String keyword);

}
