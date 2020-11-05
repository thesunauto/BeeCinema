package vn.edu.poly.beecinema.entity;

import lombok.*;

import javax.persistence.*;
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

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "ten", nullable = false)
    private String ten;

    @Column(name = "gia", nullable = false)
    private Float gia;

    @Column(name = "ngaytao", nullable = false)
    private LocalDateTime ngaytao;

    @Column(name = "trangthai", nullable = false)
    private Integer trangthai;

    @OneToMany(mappedBy = "dayghe",cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Ghe> ghes;
}
