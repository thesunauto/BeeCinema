package vn.edu.poly.beecinema.service;

import org.springframework.data.domain.Page;
import vn.edu.poly.beecinema.entity.Ve;
import vn.edu.poly.beecinema.entity.VeID;

import java.util.List;

public interface ThongKeService {
    List<Ve> findAllVeByToDay();


}
