package vn.edu.poly.beecinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.edu.poly.beecinema.entity.Phim;

public interface PhimRepository extends JpaRepository<Phim, String>, JpaSpecificationExecutor<Phim> {

}