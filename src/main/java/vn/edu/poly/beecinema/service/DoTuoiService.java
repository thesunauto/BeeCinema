package vn.edu.poly.beecinema.service;

import org.springframework.data.domain.Page;
import vn.edu.poly.beecinema.entity.DoTuoi;

import java.util.List;
import java.util.Optional;

public interface DoTuoiService {
    List<DoTuoi> getAllDoTuoi();

    void saveDoTuoi(DoTuoi doTuoi);

    void deleteDoTuoi(String id);

    Optional<DoTuoi> findDoTuoiById(String id);

    Page<DoTuoi> listAll(int pageNumber, String sortField, String sortDir, String keyword);
}
