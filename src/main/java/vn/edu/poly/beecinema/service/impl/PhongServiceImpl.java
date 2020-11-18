package vn.edu.poly.beecinema.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.edu.poly.beecinema.entity.Phong;
import vn.edu.poly.beecinema.repository.PhongRepository;
import vn.edu.poly.beecinema.service.PhongService;

import java.util.List;
import java.util.Optional;

@Service
public class PhongServiceImpl implements PhongService {
    @Autowired private PhongRepository PhongRepository;
    @Override
    public List<Phong> getAllPhong() {
        return (List<Phong>) PhongRepository.findAll();
    }

    @Override
    public void savePhong(Phong phong) {
        PhongRepository.save(phong);
    }

    @Override
    public void deletePhong(String id) {
        PhongRepository.deleteById(id);
    }

    @Override
    public Optional<Phong> findPhongById(String id) {
        return PhongRepository.findById(id);
    }

    @Override
    public Page<Phong> listAll(int pageNumber, String sortField, String sortDir, String keyword) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 5,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending()
        );
        if (keyword != null) {
            return PhongRepository.findAll(keyword, pageable);
        }
        return  PhongRepository.findAll(pageable);
    }

}
