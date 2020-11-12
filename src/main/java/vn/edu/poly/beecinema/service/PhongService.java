package vn.edu.poly.beecinema.service;

import org.springframework.data.domain.Page;
import vn.edu.poly.beecinema.entity.Phong;

import java.util.List;
import java.util.Optional;

public interface PhongService {

    List<Phong> getAllPhong();

    void savePhong(Phong phong);

    void deletePhong(String id);

    Optional<Phong> findPhongById(String id);

    Page<Phong> listAll(int pageNumber, String sortField, String sortDir, String keyword);
}
