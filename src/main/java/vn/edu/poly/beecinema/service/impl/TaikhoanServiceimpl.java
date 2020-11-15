package vn.edu.poly.beecinema.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.edu.poly.beecinema.entity.Taikhoan;
import vn.edu.poly.beecinema.repository.TaikhoanRepository;
import vn.edu.poly.beecinema.service.TaikhoanService;

import java.util.List;
import java.util.Optional;

@Service
public class TaikhoanServiceimpl implements TaikhoanService {
    @Autowired private TaikhoanRepository taikhoanRepository;
    @Override
    public List<Taikhoan> getAllTaikhoan() {
        return (List<Taikhoan>) taikhoanRepository.findAll();
    }

    @Override
    public void saveTaikhoan(Taikhoan taikhoan) {
        taikhoanRepository.save(taikhoan);
    }

    @Override
        public void deleteTaikhoan(String id) {
        taikhoanRepository.deleteById(id);
    }

    @Override
    public Optional<Taikhoan> findTaikhoanById(String id) {
        return taikhoanRepository.findById(id);
    }

    @Override
    public Page<Taikhoan> listAll(int pageNumber, String sortField, String sortDir, String keyword) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 3,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending()
        );
        if (keyword != null) {
            return taikhoanRepository.findAll(keyword, pageable);
        }
        return  taikhoanRepository.findAll(pageable);
    }
}
