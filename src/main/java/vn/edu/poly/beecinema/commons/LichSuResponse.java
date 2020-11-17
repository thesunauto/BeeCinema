package vn.edu.poly.beecinema.commons;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LichSuResponse {
    private String suatchieu;
    private String ghe;
    private String sukien;
    private String loaigiaodich;
    private String nguoitao;
}
