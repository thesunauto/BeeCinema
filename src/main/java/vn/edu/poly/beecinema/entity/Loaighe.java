package vn.edu.poly.beecinema.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

@Table(name = "loaighe")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Loaighe implements Serializable {

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

    @OneToMany(mappedBy = "loaighe",cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Collection<Ghe> ghes;
}
