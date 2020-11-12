package vn.edu.poly.beecinema.service;

import vn.edu.poly.beecinema.entity.Ve;

import java.util.List;

public interface VeService {
    Boolean IsExists(Integer idSuatChieu, Integer idGhe);
    List<Ve> findAllByIdSuatchieu(Integer idsuatchieu);
}
