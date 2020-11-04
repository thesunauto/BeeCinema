package vn.edu.poly.beecinema.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.poly.beecinema.entity.Khunggio;
import vn.edu.poly.beecinema.repository.KhunggioRepository;
import vn.edu.poly.beecinema.service.KhungGioService;

import java.util.List;
import java.util.Optional;

@Service
public class KhungGioServiceImpl implements KhungGioService {
    @Autowired private KhunggioRepository khungGioRepository;
    @Override
    public List<Khunggio> getAllKhungGio() {
        return (List<Khunggio>) khungGioRepository.findAll();
    }

    @Override
    public void saveKhungGio(Khunggio khungGio) { khungGioRepository.save(khungGio); }

    @Override
    public void deleteKhungGio(String id) {
        khungGioRepository.deleteById(id);
    }

    @Override
    public Optional<Khunggio> findKhungGioById(String id) {
        return khungGioRepository.findById(id);
    }
}
