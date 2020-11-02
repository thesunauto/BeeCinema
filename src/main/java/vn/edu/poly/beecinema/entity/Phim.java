package vn.edu.poly.beecinema.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "phim")
@Data
public class Phim implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @ManyToOne
    @JoinColumn(name = "iddotuoi", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private DoTuoi dotuoi;

    @ManyToOne
    @JoinColumn(name = "idngonngu", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private NgonNgu ngonngu;

    @ManyToOne
    @JoinColumn(name = "idloaiphim", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private LoaiPhim loaiphim;

    @ManyToOne
    @JoinColumn(name = "idnhanvien", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Taikhoan taikhoan;

    @Column(name = "ten", nullable = false)
    private String ten;

    @Column(name = "hinhanh")
    private String hinhanh;

    @Column(name = "ngaybatdau", nullable = false)
    private LocalDateTime ngaybatdau;

    @Column(name = "ngayketthuc", nullable = false)
    private LocalDateTime ngayketthuc;

    @Column(name = "dodai", nullable = false)
    private Integer dodai;

    @Column(name = "mota")
    private String mota;

    @Column(name = "trangthai", nullable = false)
    private Integer trangthai;

    @OneToMany(mappedBy = "phim",cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Binhluan> binhluans;

    @OneToMany(mappedBy = "phim",cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Suatchieu> suatchieus;
}
