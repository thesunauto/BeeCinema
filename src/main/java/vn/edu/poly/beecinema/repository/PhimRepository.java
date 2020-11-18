package vn.edu.poly.beecinema.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import vn.edu.poly.beecinema.entity.Phim;

import java.util.List;

public interface PhimRepository extends PagingAndSortingRepository<Phim, String>, JpaSpecificationExecutor<Phim> {
    @Query("SELECT p FROM Phim p WHERE CONCAT(p.id, ' ',p.ten, ' ', p.loaiphim.ten, ' ', p.dotuoi.ten, ' ', p.ngonngu.ten,' ',p.ngaybatdau, ' ', p.ngayketthuc) LIKE %?1%")
    public Page<Phim> findAll(String keyword, Pageable pageable);

    List<Phim> findAllByTrangthai(Integer trangthai);
}