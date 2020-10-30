package vn.edu.poly.beecinema.service;

import vn.edu.poly.beecinema.entity.Dayghe;

import java.util.List;
import java.util.Optional;

public interface DayGheService {
    List<Dayghe> getAllDayGhe();

    void saveDayGhe(Dayghe dayGhe);

    void deleteDayGhe(String id);

    Optional<Dayghe> findDayGheByID(String id);

}
