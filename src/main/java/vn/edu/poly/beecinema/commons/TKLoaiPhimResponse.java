package vn.edu.poly.beecinema.commons;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TKLoaiPhimResponse {
    String tenloaiphim;
    Integer soluongve;
    Float tongtien;
    String idloaiphim;


}
