package vn.edu.poly.beecinema.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import vn.edu.poly.beecinema.entity.Phong;

//public interface PhongRepository extends JpaRepository<Phong, String>, JpaSpecificationExecutor<Phong> {
//
//}

public interface PhongRepository extends PagingAndSortingRepository<Phong, String>, JpaSpecificationExecutor<Phong> {
    @Query("SELECT p FROM Phong p WHERE CONCAT(p.id, ' ',p.ten) LIKE %?1%")
    public Page<Phong> findAll(String keyword, Pageable pageable);

    Phong getOne(String idphong);
}