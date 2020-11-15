package vn.edu.poly.beecinema;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.edu.poly.beecinema.entity.*;
import vn.edu.poly.beecinema.repository.*;
import vn.edu.poly.beecinema.service.*;
import vn.edu.poly.beecinema.service.impl.VeServiceImpl;

@SpringBootTest
class BeecinemaApplicationTests {
    @Autowired
    private DayGheService dayGheService;
    @Autowired
    private GheRepository gheRepository;
    @Autowired
    private GheService gheService;
    @Autowired
    private DaygheRepository daygheRepository;
    @Autowired
    private SukienService sukienService;
    @Autowired VeService getVeService;
    @Autowired private SuatChieuService suatChieuService;
    @Autowired private TaikhoanService taikhoanService;
    @Autowired private VeRepository veRepository;
@Autowired private PhimService phimService;
@Autowired private PhimRepository phimRepository;
    @Autowired private VeonlineService veonlineService;

@Autowired
private VeService veService;

    @Test
    void contextLoads() {
        veonlineService.findAllByToday().forEach(veonline -> System.out.println(veonline));
    }

}
