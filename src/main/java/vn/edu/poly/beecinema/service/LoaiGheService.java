package vn.edu.poly.beecinema.service;

import org.springframework.beans.factory.annotation.Autowired;
import vn.edu.poly.beecinema.entity.Loaighe;

import java.util.List;
import java.util.Optional;


public interface LoaiGheService {
    List<Loaighe> getAllLoaiGhe();

    void saveLoaiGhe(Loaighe loaiGhe);

    void deleteLoaiGhe(String id);

    Optional<Loaighe> findLoaiGheById(String id);

}
