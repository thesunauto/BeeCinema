package vn.edu.poly.beecinema.service;

import org.springframework.data.domain.Page;
import vn.edu.poly.beecinema.entity.Suatchieu;
import vn.edu.poly.beecinema.entity.Ve;
import vn.edu.poly.beecinema.entity.VeID;

import java.time.LocalDate;
import java.util.List;

public interface ThongKeService {
    List<Ve> findAllVeByToDay();

//    List<Suatchieu> findAllByTrangthaiAndNgaychieu();

    List<Suatchieu> findAllByTrangthai();

    Long countSlPhimTheoThang();

    Long countVeTheoThang();

    Long countDoanhThuTheoThang();

    Long countSlVeChuaBanTheoThang();

    List<Object[]> listTopPhim(String startdate, String enddate);

    List<Suatchieu> findAllByPhimAndDate(String idPhim, LocalDate batdau, LocalDate ketthuc);

    List<Suatchieu> findAllByDate(LocalDate batdau, LocalDate ketthuc);

    List<Suatchieu> findAllByLoaiPhimAndDate(String idLoaiPhim, LocalDate batdau, LocalDate ketthuc);

    List<Suatchieu> findAllByDoTuoiAndDate(String idDoTuoi, LocalDate batdau, LocalDate ketthuc);



}
