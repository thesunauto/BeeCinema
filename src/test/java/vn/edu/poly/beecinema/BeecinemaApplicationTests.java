package vn.edu.poly.beecinema;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.edu.poly.beecinema.entity.Khunggio;
import vn.edu.poly.beecinema.entity.Ve;
import vn.edu.poly.beecinema.repository.KhunggioRepository;
import vn.edu.poly.beecinema.repository.VeRepository;

@SpringBootTest
class BeecinemaApplicationTests {
    @Autowired
    VeRepository veRepository;
@Autowired
    KhunggioRepository khunggioRepository;
    @Test
    void contextLoads() {
        for (Khunggio ve: khunggioRepository.findAll()
             ) {
            System.out.println(ve.getBatdau() + " - " + ve.getKetthuc());
        }
    }

}
