package vn.edu.poly.beecinema.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GheResponse {
    private Integer id;
    private Integer col;
    private String idphong;
    private String idDay;
    private String tenDay;
    private String idLoaiGhe;
    private Integer trangthai;
    /**
     * @trangthai = 0 ghế sẳn sàng
     * @trangthai = 1 ghế đã có vé
     * @trangthai = 2 ghế mình đang chọn
     * @trangthai = 3 ghế người khác đang chọn
     * @trangthai = 4 ghế không thể chọn là đường đi hay gì đó
     *
     */
}
