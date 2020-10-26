package vn.edu.poly.beecinema.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.poly.beecinema.entity.DoTuoi;
import vn.edu.poly.beecinema.repository.DoTuoiRepository;
import vn.edu.poly.beecinema.service.DoTuoiService;

import java.util.List;
import java.util.Optional;

@Service
public class DoTuoiServiceImpl implements DoTuoiService {
    @Autowired private DoTuoiRepository doTuoiRepository;
    @Override
    public List<DoTuoi> getAllDoTuoi() {
        return (List<DoTuoi>) doTuoiRepository.findAll();
    }

    @Override
    public void saveDoTuoi(DoTuoi doTuoi) {
        doTuoiRepository.save(doTuoi);
    }

    @Override
    public void deleteDoTuoi(String id) {
        doTuoiRepository.deleteById(id);
    }

    @Override
    public Optional<DoTuoi> findDoTuoiById(String id) {
        return doTuoiRepository.findById(id);
    }
}
