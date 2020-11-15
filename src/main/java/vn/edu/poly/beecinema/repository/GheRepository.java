package vn.edu.poly.beecinema.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import vn.edu.poly.beecinema.entity.Dayghe;
import vn.edu.poly.beecinema.entity.Ghe;
import vn.edu.poly.beecinema.entity.LoaiPhim;
import vn.edu.poly.beecinema.entity.Phong;

import java.util.List;

public interface GheRepository extends JpaRepository<Ghe, Integer>, JpaSpecificationExecutor<Ghe>, PagingAndSortingRepository<Ghe, Integer> {
    List<Ghe> findByPhong(Phong phong);
    List<Ghe> findByPhongAndDayghe(Phong phong, Dayghe dayghe);
    List<Ghe> findByPhongAndDaygheOrderByCol(Phong phong, Dayghe dayghe);
    @Query("SELECT p FROM Ghe p WHERE CONCAT(p.id, ' ',p.phong.ten, ' ', p.col,' ', p.dayghe.ten, ' ',p.loaighe.ten) LIKE %?1%")
    public Page<Ghe> findAll(String keyword, Pageable pageable);
}