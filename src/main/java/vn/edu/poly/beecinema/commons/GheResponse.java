package vn.edu.poly.beecinema.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GheResponse {
    private String ten;
    private String idphong;
    private String idDay;
    private String idLoaiGhe;
    private Integer trangthai;
}
