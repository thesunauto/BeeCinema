package vn.edu.poly.beecinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.edu.poly.beecinema.entity.Loaiphim;

public interface LoaiphimRepository extends JpaRepository<Loaiphim, String>, JpaSpecificationExecutor<Loaiphim> {

}