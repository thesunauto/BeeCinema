package vn.edu.poly.beecinema.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "ve")
@IdClass(VeID.class)
@NoArgsConstructor
@AllArgsConstructor
public class Ve implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "idsuatchieu", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Suatchieu suatchieu;

    @Id
    @ManyToOne
    @JoinColumn(name = "idghe", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Ghe ghe;

    @ManyToOne
    @JoinColumn(name = "idsukien")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Sukien sukien;

    @ManyToOne
    @JoinColumn(name = "idnhanvien", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Taikhoan taikhoan;

    @Column(name = "trangthai", nullable = false)
    private Integer trangthai;

}
