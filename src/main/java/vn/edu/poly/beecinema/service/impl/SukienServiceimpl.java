package vn.edu.poly.beecinema.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.edu.poly.beecinema.entity.Sukien;
import vn.edu.poly.beecinema.repository.SukienRepository;
import vn.edu.poly.beecinema.service.SukienService;

import java.util.List;
import java.util.Optional;

@Service
public class SukienServiceimpl implements SukienService {
    @Autowired private SukienRepository sukienRepository;
    @Override
    public List<Sukien> getAllSukien() {
        return (List<Sukien>) sukienRepository.findAll();
    }

    @Override
    public void saveSukien(Sukien sukien) {
        sukienRepository.save(sukien);
    }

    @Override
    public void deleteSukien(String id) {
        sukienRepository.deleteById(id);
    }

    @Override
    public Optional<Sukien> findSukienById(String id) {
        return sukienRepository.findById(id);
    }

    @Override
    public Page<Sukien> listAll(int pageNumber, String sortField, String sortDir, String keyword) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 5,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending()
        );
        if (keyword != null) {
            return sukienRepository.findAll(keyword, pageable);
        }
        return  sukienRepository.findAll(pageable);
    }
}
