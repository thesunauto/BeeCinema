package vn.edu.poly.beecinema.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import vn.edu.poly.beecinema.entity.Dayghe;
import vn.edu.poly.beecinema.entity.Ghe;
import vn.edu.poly.beecinema.entity.LoaiPhim;

public interface DaygheRepository extends JpaRepository<Dayghe, String>, JpaSpecificationExecutor<Dayghe>, PagingAndSortingRepository<Dayghe, String> {
    Dayghe findByGhes(Ghe ghe);
    @Query("SELECT p FROM Dayghe p WHERE CONCAT(p.id, ' ',p.ten, ' ', p.gia) LIKE %?1%")
    public Page<Dayghe> findAll(String keyword, Pageable pageable);
}