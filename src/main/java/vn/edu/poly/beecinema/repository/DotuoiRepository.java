package vn.edu.poly.beecinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.edu.poly.beecinema.entity.Dotuoi;

public interface DotuoiRepository extends JpaRepository<Dotuoi, String>, JpaSpecificationExecutor<Dotuoi> {

}