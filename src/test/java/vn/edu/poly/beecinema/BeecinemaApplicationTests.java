package vn.edu.poly.beecinema;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.edu.poly.beecinema.entity.*;
import vn.edu.poly.beecinema.repository.DaygheRepository;
import vn.edu.poly.beecinema.repository.GheRepository;
import vn.edu.poly.beecinema.repository.KhunggioRepository;
import vn.edu.poly.beecinema.repository.VeRepository;
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
@Autowired
private VeService veService;

    @Test
    void contextLoads() {
        Suatchieu suatchieu = suatChieuService.findById(1);

        Ghe ghe = gheService.findGheById(10).get();
        Ve ve = new Ve();
        ve.setSuatchieu(suatchieu);
        ve.setGhe(ghe);
        ve.setTrangthai(0);
        ve.setTaikhoan(taikhoanService.findTaikhoanById("nhanpt").get());

        veRepository.save(ve);
    }

}
