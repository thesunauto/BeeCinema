package vn.edu.poly.beecinema.service;

import org.springframework.data.domain.Page;
import vn.edu.poly.beecinema.entity.Taikhoan;

import java.util.List;
import java.util.Optional;

public interface TaikhoanService {
    List<Taikhoan> getAllTaikhoan();

    void saveTaikhoan(Taikhoan taikhoan);

    void deleteTaikhoan(String id);

    Optional<Taikhoan> findTaikhoanById(String id);

    Page<Taikhoan> listAll(int pageNumber, String sortField, String sortDir, String keyword);
}
