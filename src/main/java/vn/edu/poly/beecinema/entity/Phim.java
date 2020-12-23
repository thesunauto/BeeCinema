package vn.edu.poly.beecinema.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "phim")
@Data
public class Phim implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Vui lòng nhập ID")
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

    @NotBlank(message = "Vui lòng nhập Tên phim")
    @Column(name = "ten", nullable = false)
    private String ten;

    @Column(name = "hinhanh")
    private String hinhanh;

    @NotNull(message = "Vui lòng chọn Ngày bắt đầu")
    @Column(name = "ngaybatdau", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime ngaybatdau;

    @NotNull(message = "Vui lòng chọn Ngày bắt đầu")
    @Column(name = "ngayketthuc", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime ngayketthuc;

    @NotNull(message = "Vui lòng nhập Độ dài phim")
    @Column(name = "dodai", nullable = false)
    private Integer dodai;

    @Column(name = "mota")
    private String mota;

    @Column(name = "linktrailer")
    private String linktrailer;

    @Column(name = "trangthai", nullable = false)
    private Integer trangthai;


    @OneToMany(mappedBy = "phim",cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Suatchieu> suatchieus;
}
