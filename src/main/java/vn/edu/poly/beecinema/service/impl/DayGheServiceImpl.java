package vn.edu.poly.beecinema.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.poly.beecinema.entity.Dayghe;
import vn.edu.poly.beecinema.entity.Ghe;
import vn.edu.poly.beecinema.repository.DaygheRepository;
import vn.edu.poly.beecinema.repository.GheRepository;
import vn.edu.poly.beecinema.repository.PhongRepository;
import vn.edu.poly.beecinema.service.DayGheService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DayGheServiceImpl implements DayGheService {
    @Autowired private DaygheRepository dayGheRepository;
    @Autowired private PhongRepository phongRepository;
    @Autowired private GheRepository gheRepository;
    @Override
    public List<Dayghe> getAllDayGhe() {
        return (List<Dayghe>) dayGheRepository.findAll();
    }

    @Override
    public List<Dayghe> findDayGheByPhong(String idPhong) {
        List<Dayghe> dayghes = new ArrayList<>();
        gheRepository.findByPhong(phongRepository.findById(idPhong).get()).forEach(ghe -> {
            boolean ck = true;
            Ghe ghe1 = gheRepository.getOne(ghe.getId());
            Dayghe dayghe = dayGheRepository.findByGhes(ghe1);
            for (Dayghe dayghea:dayghes) {
                if(dayghea.getId().equals(dayghe.getId())){
                    ck = false;
                    break;
                }
            }
            if(ck){
                dayghes.add(ghe.getDayghe());
            }
        });
        return dayghes;
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
