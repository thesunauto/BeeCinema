package vn.edu.poly.beecinema.service;

import org.springframework.data.domain.Page;
import vn.edu.poly.beecinema.entity.LoaiPhim;
import vn.edu.poly.beecinema.entity.Ve;

import java.util.List;

public interface VeService {
    Boolean IsExists(Integer idSuatChieu, Integer idGhe);
    Page<Ve> listAll(int pageNumber, String sortField, String sortDir, String keyword);
    List<Ve> findAllByIdSuatchieu(Integer idsuatchieu);
    void insert(Integer idsuatchieu,Integer idghe, String idsukien, String username);
    List<Ve> findAllByToDay();

}
