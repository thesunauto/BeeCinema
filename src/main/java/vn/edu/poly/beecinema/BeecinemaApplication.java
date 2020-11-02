package vn.edu.poly.beecinema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import vn.edu.poly.beecinema.storage.StorageProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class BeecinemaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeecinemaApplication.class, args);
    }

}
