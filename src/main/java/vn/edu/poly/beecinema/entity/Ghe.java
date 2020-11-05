package vn.edu.poly.beecinema.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ghe")
public class Ghe implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idphong", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Phong phong;

    @ManyToOne
    @JoinColumn(name = "iddayghe", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Dayghe dayghe;

    @Column(name = "col", nullable = false)
    private Integer col;

    @ManyToOne
    @JoinColumn(name = "idloaighe", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Loaighe loaighe;

    @ManyToOne
    @JoinColumn(name = "idnhanvien", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Taikhoan taikhoan;

    @Column(name = "ngaytao", nullable = false)
    private LocalDateTime ngaytao;

    @Column(name = "trangthai", nullable = false)
    private Integer trangthai;

    @OneToMany(mappedBy = "ghe",cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Ve> ves;

    @OneToMany(mappedBy = "ghe",cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Veonline> veonlines;
}
