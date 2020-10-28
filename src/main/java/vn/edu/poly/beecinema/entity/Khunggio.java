package vn.edu.poly.beecinema.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;

@Entity
@Table(name = "khunggio")
@Data
public class Khunggio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @ManyToOne
    @JoinColumn(name = "idnhanvien", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Taikhoan taikhoan;

    @Column(name = "batdau", nullable = false)
    private LocalTime batdau;

    @Column(name = "ketthuc", nullable = false)
    private LocalTime ketthuc;

    @Column(name = "trangthai", nullable = false)
    private Integer trangthai;

    @OneToMany(mappedBy = "khunggio",cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Suatchieu> suatchieus;
}
