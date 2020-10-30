package vn.edu.poly.beecinema.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "veonline")
@IdClass(VeonlineID.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Veonline implements Serializable {

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
    @JoinColumn(name = "idthanhvien", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Taikhoan taikhoan;

    @ManyToOne
    @JoinColumn(name = "idsukien")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Sukien sukien;

    @Column(name = "trangthai", nullable = false)
    private String trangthai;

}
