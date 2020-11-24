package vn.edu.poly.beecinema.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

@Table(name = "loaighe")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Loaighe implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Vui lòng nhập ID")
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @NotBlank(message = "Vui lòng nhập Loại Ghế")
    @Column(name = "ten", nullable = false)
    private String ten;

    @NotNull(message = "Vui lòng nhập Giá")
    @Column(name = "gia", nullable = false)
    private Float gia;

    @Column(name = "ngaytao", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime ngaytao;

    @Column(name = "trangthai", nullable = false)
    private Integer trangthai;

    @OneToMany(mappedBy = "loaighe",cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Collection<Ghe> ghes;
}
