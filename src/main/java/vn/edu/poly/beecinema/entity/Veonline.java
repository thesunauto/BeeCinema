package vn.edu.poly.beecinema.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "veonline")
@Data
@IdClass(VeonlineID.class)
public class Veonline implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "idsuatchieu", nullable = false)
    private Integer idsuatchieu;

    @Id
    @Column(name = "idghe", nullable = false)
    private String idghe;

    @Column(name = "idthanhvien", nullable = false)
    private String idthanhvien;

    @Column(name = "idsukien")
    private String idsukien;

    @Column(name = "trangthai", nullable = false)
    private String trangthai;

}
