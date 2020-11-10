package vn.edu.poly.beecinema.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import vn.edu.poly.beecinema.entity.Sukien;
import vn.edu.poly.beecinema.entity.Taikhoan;

//public interface TaikhoanRepository extends JpaRepository<Taikhoan, String>, JpaSpecificationExecutor<Taikhoan> {
//
//}
public interface TaikhoanRepository extends PagingAndSortingRepository<Taikhoan, String>, JpaSpecificationExecutor<Taikhoan> {
    @Query("SELECT p FROM Taikhoan p WHERE CONCAT(p.username, ' ',p.ten) LIKE %?1%")
    public Page<Taikhoan> findAll(String keyword, Pageable pageable);
}