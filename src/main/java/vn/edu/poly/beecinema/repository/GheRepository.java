package vn.edu.poly.beecinema.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import vn.edu.poly.beecinema.entity.Dayghe;
import vn.edu.poly.beecinema.entity.Ghe;
import vn.edu.poly.beecinema.entity.LoaiPhim;
import vn.edu.poly.beecinema.entity.Phong;

import java.util.List;

public interface GheRepository extends JpaRepository<Ghe, Integer>, JpaSpecificationExecutor<Ghe>, PagingAndSortingRepository<Ghe, Integer> {
    List<Ghe> findByPhong(Phong phong);
    List<Ghe> findByPhongAndDayghe(Phong phong, Dayghe dayghe);
    List<Ghe> findByPhongAndDaygheOrderByCol(Phong phong, Dayghe dayghe);
    @Query("SELECT p FROM Ghe p WHERE p.phong.id = :keyword ")
    public Page<Ghe> findAll(@Param("keyword") String keyword, Pageable pageable);

}