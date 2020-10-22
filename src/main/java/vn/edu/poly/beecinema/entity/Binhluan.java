package vn.edu.poly.beecinema.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "binhluan")
@Data
public class Binhluan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "idphim", nullable = false)
    private String idphim;

    @Column(name = "idthanhvien", nullable = false)
    private String idthanhvien;

    @Column(name = "ngaytao", nullable = false)
    private LocalDateTime ngaytao;

    @Column(name = "noidung", nullable = false)
    private String noidung;

    @Column(name = "trangthai", nullable = false)
    private Integer trangthai;

}
