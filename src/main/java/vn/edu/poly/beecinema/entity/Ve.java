package vn.edu.poly.beecinema.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "ve")
@IdClass(VeID.class)
public class Ve implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "idsuatchieu", nullable = false)
    private Integer idsuatchieu;

    @Id
    @Column(name = "idghe", nullable = false)
    private String idghe;

    @Column(name = "idsukien", nullable = false)
    private String idsukien;

    @Column(name = "idnhanvien", nullable = false)
    private String idnhanvien;

    @Column(name = "trangthai", nullable = false)
    private Integer trangthai;

}
