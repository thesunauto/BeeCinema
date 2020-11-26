package vn.edu.poly.beecinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.edu.poly.beecinema.entity.Quyen;

public interface QuyenRepository extends JpaRepository<Quyen, String>, JpaSpecificationExecutor<Quyen> {
    Quyen getQuyenById(String id);

}