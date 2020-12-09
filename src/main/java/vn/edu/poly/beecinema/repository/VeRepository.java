package vn.edu.poly.beecinema.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import vn.edu.poly.beecinema.entity.*;

import java.time.LocalDate;
import java.util.List;


public interface VeRepository extends JpaRepository<Ve, String>, JpaSpecificationExecutor<Ve>, PagingAndSortingRepository<Ve, String> {
    Ve findByGheAndSuatchieu(Ghe ghe, Suatchieu suatchieu);
    @Query("SELECT p FROM Ve p WHERE CONCAT(p.suatchieu.phim.ten, ' ',p.suatchieu.ngaychieu, ' ', p.suatchieu.khunggio.batdau, p.suatchieu.khunggio.ketthuc,' ',p.suatchieu.phong.ten, ' ',p.sukien.ten, ' ', p.taikhoan.ten, ' ', p.suatchieu.dongia) LIKE %?1%")
    public Page<Ve> findAll(String keyword, Pageable pageable);
    List<Ve> findAllBySuatchieu(Suatchieu suatchieu);
    List<Ve> findAllByTrangthai(Integer trangthai);
    List<Ve> getVeByVeID(VeID veID);
    List<Ve> findAllByTrangthaiOrderByNgaytaoDesc(Integer trangthai);


//    Thong ke
    List<Ve> findAllByTrangthaiAndSuatchieuNgaychieu(Integer trangthai, LocalDate ngaychieu);

    @Query(value = "select count(v.idsuatchieu) from ve v where v.ngaytao BETWEEN DATE_SUB(NOW(), INTERVAL 1 MONTH) AND NOW() and v.trangthai = 0", nativeQuery = true)
    public Long countVeTheoThang();

    @Query(value = "select sum((s.dongia)) as 'so ve', count(v.idsuatchieu)\n" +
            "from ve v, suatchieu s, phim p\n" +
            "where v.idsuatchieu = s.id\n" +
            "and\ts.idphim = p.id\n" +
            "and v.ngaytao BETWEEN DATE_SUB(NOW(), INTERVAL 1 MONTH) AND NOW() and v.trangthai = 0", nativeQuery = true)
    public Long countDoanhThuTheoThang();

}