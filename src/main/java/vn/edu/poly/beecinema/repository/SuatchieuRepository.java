package vn.edu.poly.beecinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.edu.poly.beecinema.entity.Phim;
import vn.edu.poly.beecinema.entity.Suatchieu;

import java.util.List;

public interface SuatchieuRepository extends JpaRepository<Suatchieu, Integer>, JpaSpecificationExecutor<Suatchieu> {
    List<Suatchieu> findAllByTrangthai(Integer trangthai);
    List<Suatchieu> findAllByPhimAndTrangthai(Phim phim,Integer trangthai);
}