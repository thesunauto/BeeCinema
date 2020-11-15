package vn.edu.poly.beecinema.service;

import org.springframework.data.domain.Page;
import vn.edu.poly.beecinema.entity.Ghe;
import vn.edu.poly.beecinema.entity.LoaiPhim;


import java.util.List;
import java.util.Optional;

public interface GheService {
    List<Ghe> findByPhongAndDayGhe(String idphong,String idDayGhe);

    List<Ghe> getAllGhe();

    void saveGhe(Ghe ghe);

    void deleteGhe(Integer id);

    Optional<Ghe> findGheById(Integer id);

    Page<Ghe> listAll(int pageNumber, String sortField, String sortDir, String keyword);
}
