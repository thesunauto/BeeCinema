package vn.edu.poly.beecinema.commons;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TKVe {
    String idphim;
    String tenPhim;
    Integer sove;
    Float tongtien;
}
