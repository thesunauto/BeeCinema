package vn.edu.poly.beecinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.edu.poly.beecinema.entity.Suatchieu;
import vn.edu.poly.beecinema.entity.Veonline;

import java.util.List;

public interface VeonlineRepository extends JpaRepository<Veonline, String>, JpaSpecificationExecutor<Veonline> {
    List<Veonline> findAllBySuatchieuAndTrangthai(Suatchieu suatchieu,Integer trangthai);
}