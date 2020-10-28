package vn.edu.poly.beecinema.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "quyen")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quyen implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "ten", nullable = false)
    private String ten;

    @OneToMany(mappedBy = "quyen",cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Taikhoan> taikhoans;
}
