package vn.edu.poly.beecinema.service;

import org.springframework.data.domain.Page;
import vn.edu.poly.beecinema.entity.LoaiPhim;
import vn.edu.poly.beecinema.entity.Ve;

public interface VeService {
    Boolean IsExists(Integer idSuatChieu, Integer idGhe);
    Page<Ve> listAll(int pageNumber, String sortField, String sortDir, String keyword);
}
