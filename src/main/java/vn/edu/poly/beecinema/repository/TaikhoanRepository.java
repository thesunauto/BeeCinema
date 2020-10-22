package vn.edu.poly.beecinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.edu.poly.beecinema.entity.Taikhoan;

public interface TaikhoanRepository extends JpaRepository<Taikhoan, String>, JpaSpecificationExecutor<Taikhoan> {

}