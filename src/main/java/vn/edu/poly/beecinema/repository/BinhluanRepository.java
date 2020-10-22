package vn.edu.poly.beecinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.edu.poly.beecinema.entity.Binhluan;

public interface BinhluanRepository extends JpaRepository<Binhluan, Integer>, JpaSpecificationExecutor<Binhluan> {

}