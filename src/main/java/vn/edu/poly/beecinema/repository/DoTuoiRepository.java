package vn.edu.poly.beecinema.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import vn.edu.poly.beecinema.entity.DoTuoi;

//public interface DoTuoiRepository extends JpaRepository<DoTuoi, String>, JpaSpecificationExecutor<DoTuoi> {
//
//}
public interface DoTuoiRepository extends PagingAndSortingRepository<DoTuoi, String>, JpaSpecificationExecutor<DoTuoi> {
    @Query("SELECT p FROM DoTuoi p WHERE CONCAT(p.id, ' ',p.ten) LIKE %?1%")
    public Page<DoTuoi> findAll(String keyword, Pageable pageable);
}