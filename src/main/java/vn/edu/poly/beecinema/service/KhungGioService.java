package vn.edu.poly.beecinema.service;

import org.springframework.data.domain.Page;
import vn.edu.poly.beecinema.entity.Khunggio;
import vn.edu.poly.beecinema.entity.LoaiPhim;
import vn.edu.poly.beecinema.entity.Sukien;

import java.util.List;
import java.util.Optional;

public interface KhungGioService {
    List<Khunggio> getAllKhungGio();

    void saveKhungGio(Khunggio khungGio);

    void deleteKhungGio(String id);

    Optional<Khunggio> findKhungGioById(String id);

    Page<Khunggio> listAll(int pageNumber, String sortField, String sortDir, String keyword);
}
