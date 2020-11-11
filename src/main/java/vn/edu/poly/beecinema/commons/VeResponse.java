package vn.edu.poly.beecinema.commons;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import vn.edu.poly.beecinema.entity.Ghe;
import vn.edu.poly.beecinema.service.GheService;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VeResponse {
    @Autowired private GheService gheService;
    private Integer idsuatchieu;
    private Integer idghe;
    private Integer idsukien;
    private String idnhanvien;
    private Integer stt;
    private GheResponse gheResponse;
}
