package vn.edu.poly.beecinema.commons;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TKDoTuoiResponse {
    String tendotuoi;
    Integer soluongve;
    Float tongtien;
    String iddotuoi;


}
