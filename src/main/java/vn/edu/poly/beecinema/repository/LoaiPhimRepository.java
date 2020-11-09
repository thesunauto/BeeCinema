package vn.edu.poly.beecinema.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import vn.edu.poly.beecinema.entity.LoaiPhim;

public interface LoaiPhimRepository extends PagingAndSortingRepository<LoaiPhim, String>, JpaSpecificationExecutor<LoaiPhim> {
    @Query("SELECT p FROM LoaiPhim p WHERE CONCAT(p.id, ' ',p.ten) LIKE %?1%")
    public Page<LoaiPhim> findAll(String keyword, Pageable pageable);
}