package vn.edu.poly.beecinema.service;

import vn.edu.poly.beecinema.entity.Phong;

import java.util.List;
import java.util.Optional;

public interface PhongService {

    List<Phong> getAllPhong();

    void savePhong(Phong phong);

    void deletePhong(String id);

    Optional<Phong> findPhongById(String id);
}
