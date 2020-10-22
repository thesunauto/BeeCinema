package vn.edu.poly.beecinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.edu.poly.beecinema.entity.Ghe;

public interface GheRepository extends JpaRepository<Ghe, String>, JpaSpecificationExecutor<Ghe> {

}