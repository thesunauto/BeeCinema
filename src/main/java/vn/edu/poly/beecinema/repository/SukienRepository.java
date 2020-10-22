package vn.edu.poly.beecinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.edu.poly.beecinema.entity.Sukien;

public interface SukienRepository extends JpaRepository<Sukien, String>, JpaSpecificationExecutor<Sukien> {

}