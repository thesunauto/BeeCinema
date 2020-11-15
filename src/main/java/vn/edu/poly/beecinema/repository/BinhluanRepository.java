package vn.edu.poly.beecinema.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import vn.edu.poly.beecinema.entity.Binhluan;
import vn.edu.poly.beecinema.entity.LoaiPhim;

public interface BinhluanRepository extends JpaRepository<Binhluan, Integer>, JpaSpecificationExecutor<Binhluan> {
    @Query("SELECT p FROM Binhluan p WHERE CONCAT(p.id, ' ',p.phim.ten, ' ', p.taikhoan) LIKE %?1%")
    public Page<Binhluan> findAll(String keyword, Pageable pageable);
}