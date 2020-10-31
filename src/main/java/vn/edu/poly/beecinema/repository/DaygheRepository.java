package vn.edu.poly.beecinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.edu.poly.beecinema.entity.Dayghe;
import vn.edu.poly.beecinema.entity.Ghe;

public interface DaygheRepository extends JpaRepository<Dayghe, String>, JpaSpecificationExecutor<Dayghe> {
    Dayghe findByGhes(Ghe ghe);
}