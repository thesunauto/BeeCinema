package vn.edu.poly.beecinema.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GheResponse {
    private Integer col;
    private String idphong;
    private String idDay;
    private String tenDay;
    private String idLoaiGhe;
    private Integer trangthai;
}
