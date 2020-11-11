package vn.edu.poly.beecinema.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Data
@Table(name = "sukien")
@NoArgsConstructor
@AllArgsConstructor
public class Sukien implements Serializable {

    @NotBlank(message = "Vui lòng nhập ID")
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @ManyToOne
    @JoinColumn(name = "idnhanvien", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Taikhoan taikhoan;

    @NotBlank(message = "Vui lòng nhập Tên sự kiện")
    @Column(name = "ten", nullable = false)
    private String ten;

    @Column(name = "hinhanh", nullable = false)
    private String hinhanh;

    @Column(name = "mota", nullable = false)
    private String mota;

    @NotNull(message = "Vui lòng nhập Giảm giá")
    @Column(name = "giam", nullable = false)
    private Float giam;

    @NotNull(message = "Vui lòng chọn Ngày bắt đầu")
    @Column(name = "ngaybatdau", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime ngaybatdau;

    @NotNull(message = "Vui lòng chọn Ngày kết thúc")
    @Column(name = "ngayketthuc", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime ngayketthuc;

    @Column(name = "trangthai", nullable = false)
    private Integer trangthai;

    @OneToMany(mappedBy = "sukien",cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Ve> ves;

    @OneToMany(mappedBy = "sukien",cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Veonline> veonlines;
}
