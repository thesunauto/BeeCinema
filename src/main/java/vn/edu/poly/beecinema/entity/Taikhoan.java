package vn.edu.poly.beecinema.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "taikhoan")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Taikhoan implements Serializable {

    @Id
    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "matkhau", nullable = false)
    private String matkhau;

    @Column(name = "ten", nullable = false)
    private String ten;

    @Column(name = "gioitinh")
    private Integer gioitinh;

    @Column(name = "ngaysinh")
    private LocalDate ngaysinh;

    @Column(name = "diachi")
    private String diachi;

    @Column(name = "sodienthoai")
    private String sodienthoai;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "idquyen", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Quyen quyen;

    @Column(name = "ngaytao", nullable = false)
    private LocalDateTime ngaytao;

    @Column(name = "hinhanh")
    private String hinhanh;

    @Column(name = "mota")
    private String mota;

    @Column(name = "trangthai", nullable = false)
    private Integer trangthai;

    @OneToMany(mappedBy = "taikhoan",cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<DoTuoi> dotuois;

    @OneToMany(mappedBy = "taikhoan",cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Binhluan> binhluans;


    @OneToMany(mappedBy = "taikhoan",cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Ghe> ghes;

    @OneToMany(mappedBy = "taikhoan",cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Khunggio> khunggios;

    @OneToMany(mappedBy = "taikhoan",cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<LoaiPhim> loaiphims;

    @OneToMany(mappedBy = "taikhoan",cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<NgonNgu> ngonngus;

    @OneToMany(mappedBy = "taikhoan",cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Phim> phims;

    @OneToMany(mappedBy = "taikhoan",cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Phong> phongs;

    @OneToMany(mappedBy = "taikhoan",cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Suatchieu> suatchieus;

    @OneToMany(mappedBy = "taikhoan",cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Sukien> sukiens;

    @OneToMany(mappedBy = "taikhoan",cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Ve> ves;

    @OneToMany(mappedBy = "taikhoan",cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Veonline> veonlines;
}
