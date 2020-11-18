package vn.edu.poly.beecinema.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "veonline")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Veonline implements Serializable {

    @EmbeddedId
    private  VeonlineID veonlineID;

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
    @JoinColumn(name = "idthanhvien", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Taikhoan taikhoan;

    @ManyToOne
    @JoinColumn(name = "idsukien")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Sukien sukien;

    @Column(name = "ngaytao", nullable = false)
    private LocalDateTime ngaytao;

    @Column(name = "trangthai", nullable = false)
    private Integer trangthai;

}
