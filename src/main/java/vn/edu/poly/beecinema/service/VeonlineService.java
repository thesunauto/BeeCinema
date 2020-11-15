package vn.edu.poly.beecinema.service;

import vn.edu.poly.beecinema.entity.Veonline;

import java.util.List;

public interface VeonlineService {
    List<Veonline> findAllByIdSuatchieu(Integer idsuatchieu);
    void insert(Integer idsuatchieu,Integer idghe);
    List<Veonline> findAllByToday();
}
