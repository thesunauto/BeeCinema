package vn.edu.poly.beecinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.edu.poly.beecinema.entity.Dayghe;
import vn.edu.poly.beecinema.entity.Ghe;
import vn.edu.poly.beecinema.entity.Phong;

import java.util.List;

public interface GheRepository extends JpaRepository<Ghe, Integer>, JpaSpecificationExecutor<Ghe> {
    List<Ghe> findByPhong(Phong phong);
    List<Ghe> findByPhongAndDayghe(Phong phong, Dayghe dayghe);
    List<Ghe> findByPhongAndDaygheOrderByCol(Phong phong, Dayghe dayghe);
}