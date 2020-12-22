package vn.edu.poly.beecinema.commons;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TKVeOnlineResponse {
    String tenphim;
    Integer soluongve;
    Float tongtien;
    String theloai;
    String dotuoi;
    String idphim;
    String khunggio;
    String ngaychieu;
    String phong;
    String sukien;
    String tenghe;
    String khachhang;
    String ngaymua;


}
