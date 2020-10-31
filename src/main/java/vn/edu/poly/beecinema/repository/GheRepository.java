package vn.edu.poly.beecinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.edu.poly.beecinema.entity.Ghe;
import vn.edu.poly.beecinema.entity.Phong;

import java.util.List;

public interface GheRepository extends JpaRepository<Ghe, String>, JpaSpecificationExecutor<Ghe> {
    List<Ghe> findByPhong(Phong phong);
}