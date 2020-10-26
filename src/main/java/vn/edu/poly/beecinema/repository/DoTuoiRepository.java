package vn.edu.poly.beecinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.edu.poly.beecinema.entity.DoTuoi;

public interface DoTuoiRepository extends JpaRepository<DoTuoi, String>, JpaSpecificationExecutor<DoTuoi> {

}