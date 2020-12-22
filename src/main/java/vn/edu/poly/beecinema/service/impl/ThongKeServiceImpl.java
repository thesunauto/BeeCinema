package vn.edu.poly.beecinema.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.poly.beecinema.entity.Suatchieu;
import vn.edu.poly.beecinema.entity.Ve;
import vn.edu.poly.beecinema.repository.*;
import vn.edu.poly.beecinema.service.SukienService;
import vn.edu.poly.beecinema.service.ThongKeService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ThongKeServiceImpl implements ThongKeService {
    @Autowired
    private VeRepository veRepository;
    @Autowired private SuatchieuRepository suatchieuRepository;
    @Autowired private GheRepository gheRepository;
    @Autowired private TaikhoanRepository taikhoanRepository;
    @Autowired private SukienService sukienService;
    @Autowired private PhimRepository phimRepository;
    @Autowired private LoaiPhimRepository loaiPhimRepository;
    @Autowired private DoTuoiRepository doTuoiRepository;

    @Override
    public List<Ve> findAllVeByToDay() {
//        veRepository.findAllByTrangthaiAndSuatchieuNgaychieu(0,LocalDate.now());
        return veRepository.findAllByTrangthaiAndSuatchieuNgaychieu(0,LocalDate.now());
    }

//    @Override
//    public List<Suatchieu> findAllByTrangthaiAndNgaychieu() {
//        return suatchieuRepository.findAllByTrangthaiAndNgaychieu_MonthValue(0, 12);
//    }

    @Override
    public List<Suatchieu> findAllByTrangthai() {
        return suatchieuRepository.findAllByTrangthai(0);
    }

    @Override
    public Long countSlPhimTheoThang() {
        return suatchieuRepository.countSlPhimTheoThang();
    }

    @Override
    public Long countVeTheoThang() {
        return veRepository.countVeTheoThang();
    }

    @Override
    public Long countDoanhThuTheoThang() { return veRepository.countDoanhThuTheoThang(); }

    @Override
    public Long countSlVeChuaBanTheoThang() { return suatchieuRepository.countSlVeChuaBanTheoThang(); }

    @Override
    public List<Object[]> listTopPhim(String startdate, String enddate) {
        return veRepository.listTopPhim(startdate,enddate);
    }

    @Override
    public List<Suatchieu> findAllByPhimAndDate(String idPhim, LocalDate batdau, LocalDate ketthuc) {
        try {
            List<Suatchieu> suatchieus = suatchieuRepository.findAllByPhim(phimRepository.findById(idPhim).get());

            for(Iterator<Suatchieu> sc = suatchieus.iterator(); sc.hasNext();){
                Suatchieu ss = sc.next();
                if (ss.getNgaychieu().compareTo(batdau)<0||ss.getNgaychieu().compareTo(ketthuc)>0) {
                    sc.remove();
                }
            }
            return suatchieus;
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<Suatchieu> findAllByDate(LocalDate batdau, LocalDate ketthuc) {
        try {
            List<Suatchieu> suatchieus = suatchieuRepository.findAll();

            for(Iterator<Suatchieu> sc = suatchieus.iterator(); sc.hasNext();){
                Suatchieu ss = sc.next();
                if (ss.getNgaychieu().compareTo(batdau)<0||ss.getNgaychieu().compareTo(ketthuc)>0) {
                    sc.remove();
                }
            }
            return suatchieus;
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<Suatchieu> findAllByLoaiPhimAndDate(String idLoaiPhim, LocalDate batdau, LocalDate ketthuc) {
        try {
            List<Suatchieu> suatchieus = suatchieuRepository.findAllByPhimLoaiphim(loaiPhimRepository.findById(idLoaiPhim).get());

            for(Iterator<Suatchieu> sc = suatchieus.iterator(); sc.hasNext();){
                Suatchieu ss = sc.next();
                if (ss.getNgaychieu().compareTo(batdau)<0||ss.getNgaychieu().compareTo(ketthuc)>0) {
                    sc.remove();
                }
            }
            return suatchieus;
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<Suatchieu> findAllByDoTuoiAndDate(String idDoTuoi, LocalDate batdau, LocalDate ketthuc) {
        try {
            List<Suatchieu> suatchieus = suatchieuRepository.findAllByPhimDotuoi(doTuoiRepository.findById(idDoTuoi).get());

            for(Iterator<Suatchieu> sc = suatchieus.iterator(); sc.hasNext();){
                Suatchieu ss = sc.next();
                if (ss.getNgaychieu().compareTo(batdau)<0||ss.getNgaychieu().compareTo(ketthuc)>0) {
                    sc.remove();
                }
            }
            return suatchieus;
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }








}
