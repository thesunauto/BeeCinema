package vn.edu.poly.beecinema.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.poly.beecinema.entity.Ngonngu;
import vn.edu.poly.beecinema.repository.NgonNguRepository;
import vn.edu.poly.beecinema.service.NgonNguService;

import java.util.List;
import java.util.Optional;

@Service
public class NgonNguServiceImpl implements NgonNguService {
    @Autowired private NgonNguRepository ngonNguRepository;
    @Override
    public List<Ngonngu> getAllNgonNgu() {  return (List<Ngonngu>) ngonNguRepository.findAll();
    }

    @Override
    public void saveNgonNgu(Ngonngu ngonNgu) {
        ngonNguRepository.save(ngonNgu);
    }

    @Override
    public void deleteNgonNgu(String id) { ngonNguRepository.deleteById(id);
    }

    @Override
    public Optional<Ngonngu> findNgonNguById(String id) {
        return ngonNguRepository.findById(id);
    }
}
