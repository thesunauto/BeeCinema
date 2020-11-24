package vn.edu.poly.beecinema.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.edu.poly.beecinema.entity.Phim;
import vn.edu.poly.beecinema.repository.PhimRepository;
import vn.edu.poly.beecinema.service.PhimService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PhimServiceImpl implements PhimService {
    @Autowired private PhimRepository phimRepository;
    @Override
    public List<Phim> getAllPhim() {
        return (List<Phim>) phimRepository.findAll();
    }

    @Override
    public List<Phim> getAllPhimAlive() {

       List<Phim> phims = new ArrayList<>();
        phimRepository.findAllByTrangthai(0).forEach(phim -> {
           if(LocalDateTime.now().compareTo(phim.getNgaybatdau())>=0 && LocalDateTime.now().compareTo(phim.getNgayketthuc())<=0){
               phims.add(phim);
           }
       });
        return phims;
    }

    @Override
    public void savePhim(Phim phim) {
        phimRepository.save(phim);
    }

    @Override
    public void deletePhim(String id) {
        phimRepository.deleteById(id);
    }

    @Override
    public Optional<Phim> findPhimById(String id) {
        return phimRepository.findById(id);
    }

    @Override
    public Page<Phim> listAll(int pageNumber, String sortField, String sortDir, String keyword) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 3,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending()
        );
        if (keyword != null) {
            return phimRepository.findAll(keyword, pageable);
        }
        return  phimRepository.findAll(pageable);
    }
}
