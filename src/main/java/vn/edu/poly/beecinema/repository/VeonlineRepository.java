package vn.edu.poly.beecinema.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.edu.poly.beecinema.entity.*;

import java.util.List;

public interface VeonlineRepository extends JpaRepository<Veonline, String>, JpaSpecificationExecutor<Veonline> {
    List<Veonline> findAllBySuatchieu(Suatchieu suatchieu);
    Veonline getVeonlineByVeonlineID(VeonlineID veonlineID);
    List<Veonline> findAllBySuatchieuAndTaikhoan(Suatchieu suatchieu,Taikhoan taikhoan);
    List<Veonline> findAllByOrderByNgaytaoDesc();
    Page<Veonline> findAllByTaikhoanOrderByNgaytaoDesc(Taikhoan taikhoan,Pageable pageable);
    List<Veonline> findAllByTaikhoan(Taikhoan taikhoan);
}