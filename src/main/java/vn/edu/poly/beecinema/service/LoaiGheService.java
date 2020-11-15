package vn.edu.poly.beecinema.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import vn.edu.poly.beecinema.entity.Dayghe;
import vn.edu.poly.beecinema.entity.Loaighe;

import java.util.List;
import java.util.Optional;


public interface LoaiGheService {
    List<Loaighe> getAllLoaiGhe();

    void saveLoaiGhe(Loaighe loaiGhe);

    void deleteLoaiGhe(String id);

    Optional<Loaighe> findLoaiGheById(String id);

    Page<Loaighe> listAll(int pageNumber, String sortField, String sortDir, String keyword);

}
