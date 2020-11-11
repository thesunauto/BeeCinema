package vn.edu.poly.beecinema.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import vn.edu.poly.beecinema.entity.LoaiPhim;
import vn.edu.poly.beecinema.entity.NgonNgu;

public interface NgonNguRepository extends JpaRepository<NgonNgu, String>, JpaSpecificationExecutor<NgonNgu> {
    @Query("SELECT p FROM NgonNgu p WHERE CONCAT(p.id, ' ',p.ten) LIKE %?1%")
    public Page<NgonNgu> findAll(String keyword, Pageable pageable);
}