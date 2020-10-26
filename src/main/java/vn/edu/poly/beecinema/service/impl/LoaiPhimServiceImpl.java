package vn.edu.poly.beecinema.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
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
    public void saveLoaiPhim(LoaiPhim loaiPhim) {
        loaiPhimRepository.save(loaiPhim);
    }

    @Override
    public void deleteLoaiPhim(String id) {
        loaiPhimRepository.deleteById(id);
    }

    @Override
    public Optional<LoaiPhim> findLoaiPhimById(String id) {
        return loaiPhimRepository.findById(id);
    }
}
