package vn.edu.poly.beecinema.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.edu.poly.beecinema.entity.Quyen;
import vn.edu.poly.beecinema.repository.QuyenRepository;
import vn.edu.poly.beecinema.service.QuyenService;

import java.util.List;
import java.util.Optional;

@Service
public class QuyenServiceImpl implements QuyenService {
    @Autowired private QuyenRepository quyenRepository;

    @Override
    public List<Quyen> findAll(Integer limit) {
        return (List<Quyen>) Optional.ofNullable(limit).map(integer -> quyenRepository.findAll(PageRequest.of(0,limit)).getContent()).orElseGet(()->quyenRepository.findAll());
    }

    @Override
    public List<Quyen> getAllQuyen() {
        return (List<Quyen>) quyenRepository.findAll();
    }

    @Override
    public void saveQuyen(Quyen doTuoi) {
        quyenRepository.save(doTuoi);
    }

    @Override
    public void deleteQuyen(String id) {
        quyenRepository.deleteById(id);
    }

    @Override
    public Optional<Quyen> findQuyenById(String id) {
        return quyenRepository.findById(id);
    }

    @Override
    public Quyen getQuyenById(String id) {
        return quyenRepository.getQuyenById(id);
    }
}
