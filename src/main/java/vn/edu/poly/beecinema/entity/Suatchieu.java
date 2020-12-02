package vn.edu.poly.beecinema.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name = "suatchieu")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Suatchieu implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idphim", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Phim phim;

    @ManyToOne
    @JoinColumn(name = "idphong", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Phong phong;

    @ManyToOne
    @JoinColumn(name = "idnhanvien", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Taikhoan taikhoan;

    @ManyToOne
    @JoinColumn(name = "idkhunggio", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Khunggio khunggio;

    @Column(name = "ngaytao", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate ngaytao;

    @NotNull(message = "Vui lòng chọn Ngày chiếu")
    @Column(name = "ngaychieu", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate ngaychieu;

    @NotNull(message = "Vui lòng nhập Đơn giá")
    @Column(name = "dongia", nullable = false)
    private Float dongia;

    @NotNull(message = "Vui lòng nhập Phút hủy online")
    @Column(name = "phuthuyonline", nullable = false)
    private Integer phuthuyonline;

    @Column(name = "trangthai", nullable = false)
    private Integer trangthai;

    @OneToMany(mappedBy = "suatchieu",cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Collection<Ve> ves;

    @OneToMany(mappedBy = "suatchieu",cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Collection<Veonline> veonlines;
}
