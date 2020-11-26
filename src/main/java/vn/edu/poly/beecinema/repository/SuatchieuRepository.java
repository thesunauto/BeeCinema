package vn.edu.poly.beecinema.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.edu.poly.beecinema.entity.LoaiPhim;
import vn.edu.poly.beecinema.entity.Phim;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import vn.edu.poly.beecinema.entity.Suatchieu;
import vn.edu.poly.beecinema.entity.Sukien;

import java.time.LocalDate;
import java.util.List;

public interface SuatchieuRepository extends JpaRepository<Suatchieu, Integer>, JpaSpecificationExecutor<Suatchieu> , PagingAndSortingRepository<Suatchieu, Integer> {
    List<Suatchieu> findAllByTrangthai(Integer trangthai);
    List<Suatchieu> findAllByPhimAndTrangthai(Phim phim,Integer trangthai);
    @Query("SELECT p FROM Suatchieu p WHERE CONCAT(p.id, ' ',p.phim.ten, ' ', p.phong.ten, ' ', p.ngaychieu, ' ', p.khunggio.batdau, ' ', p.khunggio.ketthuc ) LIKE %?1%")
    public Page<Suatchieu> findAll(String keyword, Pageable pageable);
    List<Suatchieu> findAllByNgaychieuAndTrangthai(LocalDate ngaychieu,Integer trangthai);

}