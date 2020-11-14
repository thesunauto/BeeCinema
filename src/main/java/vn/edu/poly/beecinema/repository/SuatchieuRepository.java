package vn.edu.poly.beecinema.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import vn.edu.poly.beecinema.entity.Suatchieu;
import vn.edu.poly.beecinema.entity.Sukien;

//public interface SuatchieuRepository extends JpaRepository<Suatchieu, Integer>, JpaSpecificationExecutor<Suatchieu> {
//
//}

public interface SuatchieuRepository extends PagingAndSortingRepository<Suatchieu, Integer>, JpaSpecificationExecutor<Suatchieu> {
    @Query("SELECT p FROM Suatchieu p WHERE CONCAT(p.id, ' ',p.phim.ten) LIKE %?1%")
    public Page<Suatchieu> findAll(String keyword, Pageable pageable);

    Suatchieu getOne(Integer id);
}