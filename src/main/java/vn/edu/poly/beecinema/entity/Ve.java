package vn.edu.poly.beecinema.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Builder
@Table(name = "ve")
@NoArgsConstructor
@AllArgsConstructor
public class Ve implements Serializable {

    @EmbeddedId
    private VeID veID;

    @ManyToOne
    @JoinColumn(name = "idsuatchieu", nullable = false,insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Suatchieu suatchieu;


    @ManyToOne
    @JoinColumn(name = "idghe", nullable = false,insertable = false, updatable = false)
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
