package vn.edu.poly.beecinema.service.impl;

import org.hibernate.integrator.spi.Integrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.DateUtils;
import vn.edu.poly.beecinema.commons.SuatChieuResponse;
import vn.edu.poly.beecinema.entity.Khunggio;
import vn.edu.poly.beecinema.entity.Phong;
import vn.edu.poly.beecinema.entity.Suatchieu;
import vn.edu.poly.beecinema.entity.Sukien;
import vn.edu.poly.beecinema.repository.PhimRepository;
import vn.edu.poly.beecinema.repository.SuatchieuRepository;
import vn.edu.poly.beecinema.service.PhimService;
import vn.edu.poly.beecinema.service.SuatChieuService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class SuatChieuServiceImpl implements SuatChieuService {
    @Autowired
    SuatchieuRepository suatchieuRepository;
    @Autowired
    private PhimService phimService;
    @Autowired
    private PhimRepository phimRepository;

    @Override
    public Suatchieu findById(Integer id) {
        return Optional.ofNullable(id).map(integer -> suatchieuRepository.getOne(id)).orElse(null);
    }

    @Override
    public List<Suatchieu> getAllSuatChieu() {
        return (List<Suatchieu>) suatchieuRepository.findAllByTrangthai(0);
    }

    @Override
    public List<Suatchieu> getAllSuatChieuByPhim(String idphim) {
        return suatchieuRepository.findAllByPhimAndTrangthai(phimService.findPhimById(idphim).get(), 0);
    }

    @Override
    public List<Suatchieu> getAllSuatChieuByPhimAndToday(String idphim) {
        List<Suatchieu> suatchieus = new ArrayList<>();
        suatchieuRepository.findAllByPhimAndTrangthai(phimService.findPhimById(idphim).get(), 0).forEach(suatchieu -> {
            System.out.println(LocalDateTime.of(suatchieu.getNgaychieu(), suatchieu.getKhunggio().getBatdau()).compareTo(LocalDateTime.now()));

            if (suatchieu.getNgaychieu().compareTo(LocalDate.now()) == 0) {
                if (suatchieu.getKhunggio().getBatdau().compareTo(LocalTime.now()) > 0) {
                    suatchieus.add(suatchieu);
                }
            }
        });
        return suatchieus;
    }


    @Override
    public void saveSuatChieu(Suatchieu suatChieu) {
        suatchieuRepository.save(suatChieu);
    }

    @Override
    public void deleteSuatChieu(Integer id) {
        suatchieuRepository.deleteById(id);
    }

    @Override
    public Optional<Suatchieu> findSuatChieuById(Integer id) {
        return suatchieuRepository.findById(id);
    }


    @Override
    public Page<Suatchieu> listAll(int pageNumber, String sortField, String sortDir, String keyword) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 5,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending()
        );
        if (keyword != null) {
            return suatchieuRepository.findAll(keyword, pageable);
        }
        return suatchieuRepository.findAll(pageable);
    }

    @Override
    public List<Suatchieu> findAllByPhimAndDate(String idPhim, LocalDate ngayChieu) {
        try {
            List<Suatchieu> suatchieus = suatchieuRepository.findAllByPhimAndTrangthai(phimRepository.findById(idPhim).get(), 0);

            for(Iterator<Suatchieu> sc = suatchieus.iterator(); sc.hasNext();){
                Suatchieu ss = sc.next();
                if (!ss.getNgaychieu().equals(ngayChieu)) {
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
    public List<SuatChieuResponse> findAllByPhongAndNgayChieu(Phong phong, LocalDate ngaychieu) {
        List<SuatChieuResponse> suatChieuResponses = new ArrayList<>();
        suatchieuRepository.findAllByPhongAndNgaychieuAndTrangthai(phong,ngaychieu,0).forEach(suatchieu -> {
            suatChieuResponses.add(SuatChieuResponse.builder()
                    .id(suatchieu.getId())
                    .tenphong(suatchieu.getPhong().getTen())
                    .idphong(suatchieu.getPhong().getId())
                    .idphim(suatchieu.getPhim().getId())
                    .dongia(Double.valueOf(suatchieu.getDongia()))
                    .ketthuc(suatchieu.getKhunggio().getKetthuc().format(DateTimeFormatter.ofPattern("HH:mm")))
                    .batdau(suatchieu.getKhunggio().getBatdau().format(DateTimeFormatter.ofPattern("HH:mm")))
                    .build());
        });
        return suatChieuResponses;
    }

}
