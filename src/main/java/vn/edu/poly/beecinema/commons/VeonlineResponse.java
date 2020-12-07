package vn.edu.poly.beecinema.commons;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VeonlineResponse {
    private String idsuatchieughe;
    private String ngaytao;
    private String tenkhachhang;
    private String SDT;
    private String tenphim;
    private String suatchieu;
    private String ngaychieu;
    private String ghe;
    private String phong;
    private Integer trangthai;
    private String hethan;
    private LocalDateTime ngaytaoLDT;


}
