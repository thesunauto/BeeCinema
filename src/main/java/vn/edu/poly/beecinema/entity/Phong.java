package vn.edu.poly.beecinema.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "phong")
@Data
public class Phong implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "idnhanvien", nullable = false)
    private String idnhanvien;

    @Column(name = "ten", nullable = false)
    private String ten;

    @Column(name = "ngaytao", nullable = false)
    private LocalDateTime ngaytao;

    @Column(name = "trangthai", nullable = false)
    private Integer trangthai;

}
