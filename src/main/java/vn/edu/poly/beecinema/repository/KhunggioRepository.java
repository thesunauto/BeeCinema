package vn.edu.poly.beecinema.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import vn.edu.poly.beecinema.entity.Khunggio;
import vn.edu.poly.beecinema.entity.Sukien;

//public interface KhunggioRepository extends JpaRepository<Khunggio, String>, JpaSpecificationExecutor<Khunggio> {
//
//}

public interface KhunggioRepository extends PagingAndSortingRepository<Khunggio, String>, JpaSpecificationExecutor<Khunggio> {
    @Query("SELECT p FROM Khunggio p WHERE CONCAT(p.id, ' ',p.batdau, '' ,p.ketthuc) LIKE %?1%")
    public Page<Khunggio> findAll(String keyword, Pageable pageable);
}