package vn.edu.poly.beecinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.edu.poly.beecinema.entity.Loaighe;

public interface LoaigheRepository extends JpaRepository<Loaighe, String>, JpaSpecificationExecutor<Loaighe> {

}