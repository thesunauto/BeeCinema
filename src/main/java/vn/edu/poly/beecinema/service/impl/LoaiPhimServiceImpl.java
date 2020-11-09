package vn.edu.poly.beecinema.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.edu.poly.beecinema.entity.LoaiPhim;
import vn.edu.poly.beecinema.repository.LoaiPhimRepository;
import vn.edu.poly.beecinema.service.LoaiPhimService;

import java.util.List;
import java.util.Optional;
@Service
public class LoaiPhimServiceImpl implements LoaiPhimService {
    @Autowired private LoaiPhimRepository loaiPhimRepository;
    @Override
    public List<LoaiPhim> getAllLoaiPhim() {
        return (List<LoaiPhim>) loaiPhimRepository.findAll();
    }

    @Override
    public void saveLoaiPhim(LoaiPhim loaiPhim) { loaiPhimRepository.save(loaiPhim); }

    @Override
    public void deleteLoaiPhim(String id) {
        loaiPhimRepository.deleteById(id);
    }

    @Override
    public Optional<LoaiPhim> findLoaiPhimById(String id) {
        return loaiPhimRepository.findById(id);
    }

    @Override
    public Page<LoaiPhim> listAll(int pageNumber, String sortField, String sortDir, String keyword) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 5,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending()
        );
        if (keyword != null) {
            return loaiPhimRepository.findAll(keyword, pageable);
        }
        return  loaiPhimRepository.findAll(pageable);
    }
}
