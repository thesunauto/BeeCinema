package vn.edu.poly.beecinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.edu.poly.beecinema.entity.Khunggio;

public interface KhunggioRepository extends JpaRepository<Khunggio, String>, JpaSpecificationExecutor<Khunggio> {

}