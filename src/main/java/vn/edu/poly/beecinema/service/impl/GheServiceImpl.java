package vn.edu.poly.beecinema.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.edu.poly.beecinema.entity.Ghe;
import vn.edu.poly.beecinema.entity.LoaiPhim;
import vn.edu.poly.beecinema.repository.DaygheRepository;
import vn.edu.poly.beecinema.repository.GheRepository;
import vn.edu.poly.beecinema.repository.PhongRepository;
import vn.edu.poly.beecinema.service.GheService;


import java.util.List;
import java.util.Optional;

@Service
public class GheServiceImpl implements GheService {
    @Autowired private GheRepository GheRepository;
    @Autowired private PhongRepository phongRepository;
    @Autowired private DaygheRepository daygheRepository;
    @Override
    public List<Ghe> findByPhongAndDayGhe(String idphong, String idDayGhe) {
        return GheRepository.findByPhongAndDaygheOrderByCol(phongRepository.getOne(idphong),daygheRepository.getOne(idDayGhe));
    }



    @Override
    public List<Ghe> getAllGhe() {
        return (List<Ghe>) GheRepository.findAll();
    }

    @Override
    public void saveGhe(Ghe ghe) {
        GheRepository.save(ghe);
    }

    @Override
    public void deleteGhe(Integer id) {
        GheRepository.deleteById(id);
    }

    @Override
    public Optional<Ghe> findGheById(Integer id) {
        return GheRepository.findById(id);
    }

    @Override
    public Page<Ghe> listAll(int pageNumber, String sortField, String sortDir, String keyword) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 5,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending()
        );
        if (keyword != null) {
            return GheRepository.findAll(keyword, pageable);
        }
        return  GheRepository.findAll(pageable);
    }
}
