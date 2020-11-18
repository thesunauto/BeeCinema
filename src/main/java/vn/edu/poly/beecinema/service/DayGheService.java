package vn.edu.poly.beecinema.service;

import org.springframework.data.domain.Page;
import vn.edu.poly.beecinema.entity.Binhluan;
import vn.edu.poly.beecinema.entity.Dayghe;

import java.util.List;
import java.util.Optional;

public interface DayGheService {
    List<Dayghe> getAllDayGhe();

    List<Dayghe> findDayGheByPhong(String idPhong);

    void saveDayGhe(Dayghe dayGhe);

    void deleteDayGhe(String id);

    Optional<Dayghe> findDayGheByID(String id);

    Page<Dayghe> listAll(int pageNumber, String sortField, String sortDir, String keyword);

}
