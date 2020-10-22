package vn.edu.poly.beecinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.edu.poly.beecinema.entity.Phong;

public interface PhongRepository extends JpaRepository<Phong, String>, JpaSpecificationExecutor<Phong> {

}