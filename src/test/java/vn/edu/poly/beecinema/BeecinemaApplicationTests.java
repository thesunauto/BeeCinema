package vn.edu.poly.beecinema;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.edu.poly.beecinema.entity.Dayghe;
import vn.edu.poly.beecinema.entity.Khunggio;
import vn.edu.poly.beecinema.entity.Ve;
import vn.edu.poly.beecinema.repository.DaygheRepository;
import vn.edu.poly.beecinema.repository.GheRepository;
import vn.edu.poly.beecinema.repository.KhunggioRepository;
import vn.edu.poly.beecinema.repository.VeRepository;
import vn.edu.poly.beecinema.service.DayGheService;
import vn.edu.poly.beecinema.service.VeService;
import vn.edu.poly.beecinema.service.impl.VeServiceImpl;

@SpringBootTest
class BeecinemaApplicationTests {
    @Autowired
    private DayGheService dayGheService;
    @Autowired
    private GheRepository gheRepository;
    @Autowired
    private DaygheRepository daygheRepository;
@Autowired
private VeService veService;

    @Test
    void contextLoads() {
        System.out.println(veService.IsExists(1,9));


    }

}
