package vn.edu.poly.beecinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.edu.poly.beecinema.entity.Veonline;

public interface VeonlineRepository extends JpaRepository<Veonline, String>, JpaSpecificationExecutor<Veonline> {

}