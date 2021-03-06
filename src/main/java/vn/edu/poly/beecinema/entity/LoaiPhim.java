package vn.edu.poly.beecinema.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.EqualsAndHashCode;
import lombok.ToString;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "loaiphim")
@Data
public class LoaiPhim implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Vui lòng nhập ID")
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @NotBlank(message = "Vui lòng nhập Tên")
    @Column(name = "ten", nullable = false)
    private String ten;

    @Column(name = "mota")
    private String mota;

    @ManyToOne
    @JoinColumn(name = "idnhanvien", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Taikhoan taikhoan;

    @Column(name = "ngaytao", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime ngaytao;

    @Column(name = "trangthai", nullable = false)
    private Integer trangthai;

    @OneToMany(mappedBy = "loaiphim",cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Phim> phims;
}
