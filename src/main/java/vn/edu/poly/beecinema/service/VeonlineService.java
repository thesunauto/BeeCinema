package vn.edu.poly.beecinema.service;

import vn.edu.poly.beecinema.entity.Veonline;
import vn.edu.poly.beecinema.entity.VeonlineID;

import java.util.List;

public interface VeonlineService {
    List<Veonline> findAllByIdSuatchieu(Integer idsuatchieu);
    Boolean insert(Integer idsuatchieu,Integer idghe, String idsukien, String username);
    List<Veonline> findAllByToday();
    Integer getStt(Veonline veonline);
    Veonline findByVeonlineID(VeonlineID veonlineID);
    void save(Veonline veonline);
}
