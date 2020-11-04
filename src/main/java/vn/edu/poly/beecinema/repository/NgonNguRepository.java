package vn.edu.poly.beecinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.edu.poly.beecinema.entity.NgonNgu;

public interface NgonNguRepository extends JpaRepository<NgonNgu, String>, JpaSpecificationExecutor<NgonNgu> {

}