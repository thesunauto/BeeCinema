package vn.edu.poly.beecinema.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "ghe")
public class Ghe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "idphong", nullable = false)
    private String idphong;

    @Column(name = "idloaighe", nullable = false)
    private String idloaighe;

    @Column(name = "iddayghe", nullable = false)
    private String iddayghe;

    @Column(name = "idnhanvien", nullable = false)
    private String idnhanvien;

    @Column(name = "ngaytao", nullable = false)
    private LocalDateTime ngaytao;

    @Column(name = "trangthai", nullable = false)
    private Integer trangthai;

}
