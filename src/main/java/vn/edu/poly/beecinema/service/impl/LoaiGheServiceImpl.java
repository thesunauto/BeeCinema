package vn.edu.poly.beecinema.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.edu.poly.beecinema.entity.Dayghe;
import vn.edu.poly.beecinema.entity.Loaighe;
import vn.edu.poly.beecinema.repository.LoaigheRepository;
import vn.edu.poly.beecinema.service.LoaiGheService;

import java.util.List;
import java.util.Optional;

@Service
public class LoaiGheServiceImpl implements LoaiGheService {
    @Autowired private LoaigheRepository loaigheRepository;
    @Override
    public List<Loaighe> getAllLoaiGhe() {
        return (List<Loaighe>) loaigheRepository.findAll();
    }

    @Override
    public void saveLoaiGhe(Loaighe loaiGhe) {
        loaigheRepository.save(loaiGhe);
    }

    @Override
    public void deleteLoaiGhe(String id) {
        loaigheRepository.deleteById(id);
    }

    @Override
    public Optional<Loaighe> findLoaiGheById(String id) {
        return loaigheRepository.findById(id);
    }

    @Override
    public Page<Loaighe> listAll(int pageNumber, String sortField, String sortDir, String keyword) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 5,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending()
        );
        if (keyword != null) {
            return loaigheRepository.findAll(keyword, pageable);
        }
        return  loaigheRepository.findAll(pageable);
    }
}
