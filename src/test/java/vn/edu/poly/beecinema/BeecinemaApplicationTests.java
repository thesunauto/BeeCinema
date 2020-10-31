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

@SpringBootTest
class BeecinemaApplicationTests {
    @Autowired
    private DayGheService dayGheService;
    @Autowired
    private GheRepository gheRepository;
@Autowired
   private KhunggioRepository khunggioRepository;
@Autowired
  private  DaygheRepository daygheRepository;
    @Test
    void contextLoads() {
        for (Dayghe ve: dayGheService.findDayGheByPhong("1")
             ) {
            System.out.println(ve.getId() + " - " + ve.getTen());
        }

    }

}
