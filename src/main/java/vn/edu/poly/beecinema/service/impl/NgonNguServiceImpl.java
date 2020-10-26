package vn.edu.poly.beecinema.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.poly.beecinema.entity.NgonNgu;
import vn.edu.poly.beecinema.repository.NgonNguRepository;
import vn.edu.poly.beecinema.service.NgonNguService;

import java.util.List;
import java.util.Optional;

@Service
public class NgonNguServiceImpl implements NgonNguService {
    @Autowired private NgonNguRepository ngonNguRepository;
    @Override
    public List<NgonNgu> getAllNgonNgu() {  return (List<NgonNgu>) ngonNguRepository.findAll();
    }

    @Override
    public void saveNgonNgu(NgonNgu ngonNgu) {
        ngonNguRepository.save(ngonNgu);
    }

    @Override
    public void deleteNgonNgu(String id) { ngonNguRepository.deleteById(id);
    }

    @Override
    public Optional<NgonNgu> findNgonNguById(String id) {
        return ngonNguRepository.findById(id);
    }
}
