package vn.edu.poly.beecinema.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.edu.poly.beecinema.entity.Binhluan;
import vn.edu.poly.beecinema.entity.LoaiPhim;
import vn.edu.poly.beecinema.repository.BinhluanRepository;
import vn.edu.poly.beecinema.service.BinhluanService;

import java.util.List;
import java.util.Optional;

@Service
public class BinhluanServiceimpl implements BinhluanService {
    @Autowired
    private BinhluanRepository binhluanRepository;

    @Override
    public List<Binhluan> getAllBinhluan() {
        return (List<Binhluan>) binhluanRepository.findAll();
    }

    @Override
    public void saveBinhluan(Binhluan binhluan) {
        binhluanRepository.save(binhluan);
    }

    @Override
    public void deleteBinhluan(Integer id) {
        binhluanRepository.deleteById(id);
    }

    @Override
    public Optional<Binhluan> findBinhluanById(Integer id) {
        return binhluanRepository.findById(id);
    }

    @Override
    public Page<Binhluan> listAll(int pageNumber, String sortField, String sortDir, String keyword) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 5,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending()
        );
        if (keyword != null) {
            return binhluanRepository.findAll(keyword, pageable);
        }
        return  binhluanRepository.findAll(pageable);
    }

}
