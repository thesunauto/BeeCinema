package vn.edu.poly.beecinema.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.poly.beecinema.entity.Quyen;
import vn.edu.poly.beecinema.repository.QuyenRepository;
import org.springframework.data.domain.PageRequest;
import java.util.List;
import java.util.Optional;

@Service
public class QuyenService {
    @Autowired private QuyenRepository quyenRepository;

    public List<Quyen> findAll(Integer limit){
        return Optional.ofNullable(limit).map(integer -> quyenRepository.findAll(PageRequest.of(0,limit)).getContent()).orElseGet(()->quyenRepository.findAll());
    }

}
