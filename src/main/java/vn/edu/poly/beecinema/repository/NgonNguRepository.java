package vn.edu.poly.beecinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.edu.poly.beecinema.entity.Ngonngu;

public interface NgonNguRepository extends JpaRepository<Ngonngu, String>, JpaSpecificationExecutor<Ngonngu> {

}