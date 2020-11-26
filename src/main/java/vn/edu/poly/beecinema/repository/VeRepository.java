package vn.edu.poly.beecinema.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import vn.edu.poly.beecinema.entity.*;

import java.util.List;


public interface VeRepository extends JpaRepository<Ve, String>, JpaSpecificationExecutor<Ve>, PagingAndSortingRepository<Ve, String> {
    Ve findByGheAndSuatchieu(Ghe ghe, Suatchieu suatchieu);
    @Query("SELECT p FROM Ve p WHERE CONCAT(p.suatchieu.phim.ten, ' ',p.suatchieu.ngaychieu, ' ', p.suatchieu.khunggio.batdau, p.suatchieu.khunggio.ketthuc,' ',p.suatchieu.phong.ten, ' ',p.sukien.ten, ' ', p.taikhoan.ten, ' ', p.suatchieu.dongia) LIKE %?1%")
    public Page<Ve> findAll(String keyword, Pageable pageable);
    List<Ve> findAllBySuatchieu(Suatchieu suatchieu);
    List<Ve> findAllByTrangthai(Integer trangthai);
    List<Ve> getVeByVeID(VeID veID);
    List<Ve> findAllByTrangthaiOrderByNgaytaoDesc(Integer trangthai);

}