package vn.edu.poly.beecinema.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "binhluan")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Binhluan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idphim",nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Phim phim;

    @ManyToOne
    @JoinColumn(name = "idthanhvien", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Taikhoan taikhoan;

    @Column(name = "ngaytao", nullable = false)
    private LocalDateTime ngaytao;

    @Column(name = "noidung", nullable = false)
    private String noidung;

    @Column(name = "trangthai", nullable = false)
    private Integer trangthai;

}
