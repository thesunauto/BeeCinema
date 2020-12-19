package vn.edu.poly.beecinema.commons;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TKVe2 {
    String tenphim;
    String tenghe;
    String sukien;
    String phong;
    String khunggio;
    String ngaychieu;
    Float gia;
}
