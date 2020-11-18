package vn.edu.poly.beecinema.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import vn.edu.poly.beecinema.entity.LoaiPhim;
import vn.edu.poly.beecinema.entity.Loaighe;

public interface LoaigheRepository extends JpaRepository<Loaighe, String>, JpaSpecificationExecutor<Loaighe> , PagingAndSortingRepository<Loaighe, String> {
    @Query("SELECT p FROM Loaighe p WHERE CONCAT(p.id, ' ',p.ten, ' ', p.gia) LIKE %?1%")
    public Page<Loaighe> findAll(String keyword, Pageable pageable);
}