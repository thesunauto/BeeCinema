package vn.edu.poly.beecinema.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.edu.poly.beecinema.entity.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;
import java.util.List;

public interface SuatchieuRepository extends JpaRepository<Suatchieu, Integer>, JpaSpecificationExecutor<Suatchieu> , PagingAndSortingRepository<Suatchieu, Integer> {
    List<Suatchieu> findAllByTrangthai(Integer trangthai);
    List<Suatchieu> findAllByPhimAndTrangthai(Phim phim,Integer trangthai);
    @Query("SELECT p FROM Suatchieu p WHERE CONCAT(p.id, ' ',p.phim.ten, ' ', p.phong.ten, ' ', p.ngaychieu, ' ', p.khunggio.batdau, ' ', p.khunggio.ketthuc ) LIKE %?1% ORDER BY p.id DESC")
    Page<Suatchieu> findAll(String keyword, Pageable pageable);

    List<Suatchieu> findAllByPhongAndNgaychieuAndTrangthai(Phong phong, LocalDate ngaychieu, Integer trangthai);
    List<Suatchieu> findAllByPhongAndNgaychieu(Phong phong, LocalDate ngaychieu);

    List<Suatchieu> findAllByPhim(Phim phim);

    List<Suatchieu> findAllByPhimLoaiphim(LoaiPhim loaiphim);

    List<Suatchieu> findAllByPhimDotuoi(DoTuoi doTuoi);


    //    Thong ke

    @Query(value = "SELECT  count(distinct sc.idphim) FROM Suatchieu sc where sc.ngaychieu between month(curdate()) and curdate() and sc.trangthai = 0", nativeQuery = true)
    public Long countSlPhimTheoThang();

    @Query(value = "SELECT count(g.id) FROM Suatchieu sc, phong p, ghe g WHERE sc.idphong = p.id  AND p.id = g.idphong AND sc.ngaychieu between month(curdate()) and curdate() AND g.ngaytao <= sc.ngaytao AND sc.trangthai = 0 AND g.trangthai = 0", nativeQuery = true)
    public Long countSlVeChuaBanTheoThang();

}