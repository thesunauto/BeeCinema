package vn.edu.poly.beecinema.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "khunggio")
@Data
public class Khunggio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "idnhanvien", nullable = false)
    private String idnhanvien;

    @Column(name = "batdau", nullable = false)
    private LocalDateTime batdau;

    @Column(name = "ketthuc", nullable = false)
    private LocalDateTime ketthuc;

    @Column(name = "trangthai", nullable = false)
    private Integer trangthai;

}
