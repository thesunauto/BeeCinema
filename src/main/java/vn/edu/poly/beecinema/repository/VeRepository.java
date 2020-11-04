package vn.edu.poly.beecinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.edu.poly.beecinema.entity.Ghe;
import vn.edu.poly.beecinema.entity.Suatchieu;
import vn.edu.poly.beecinema.entity.Ve;

public interface VeRepository extends JpaRepository<Ve, String>, JpaSpecificationExecutor<Ve> {
    Ve findByGheAndSuatchieu(Ghe ghe, Suatchieu suatchieu);
}