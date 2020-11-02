package vn.edu.poly.beecinema.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhimResponse {
    private String id;
    private String dotuoi;
    private String ngonngu;
    private String loaiphim;
    private String ten;
    private String hinhanh;
    private String ngaybatdau;
    private String ngayketthuc;
    private Integer dodai;
    private String mota;
    private List<Integer> idsuatchieu;
}
