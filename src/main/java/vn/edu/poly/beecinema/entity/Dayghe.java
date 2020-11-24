package vn.edu.poly.beecinema.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "dayghe")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dayghe implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Vui lòng nhập ID")
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @NotBlank(message = "Vui lòng nhập Dãy Ghế")
    @Pattern(regexp = "[A-Z]{1}", message = "Tên Dãy Ghế phải là một chữ Hoa")
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

    @OneToMany(mappedBy = "dayghe",cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Ghe> ghes;
}
