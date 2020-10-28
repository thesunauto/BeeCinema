package vn.edu.poly.beecinema.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.poly.beecinema.entity.Dayghe;
import vn.edu.poly.beecinema.repository.DaygheRepository;
import vn.edu.poly.beecinema.service.DayGheService;

import java.util.List;
import java.util.Optional;

@Service
public class DayGheServiceImpl implements DayGheService {
    @Autowired private DaygheRepository dayGheRepository;
    @Override
    public List<Dayghe> getAllDayGhe() {
        return (List<Dayghe>) dayGheRepository.findAll();
    }

    @Override
    public void saveDayGhe(Dayghe dayGhe) {
        dayGheRepository.save(dayGhe);
    }

    @Override
    public void deleteDayGhe(String id) {
        dayGheRepository.deleteById(id);
    }

    @Override
    public Optional<Dayghe> findDayGheByID(String id) {
        return dayGheRepository.findById(id);
    }
}
