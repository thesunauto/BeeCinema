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
@Table(name = "sukien")
public class Sukien implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "idnhanvien", nullable = false)
    private String idnhanvien;

    @Column(name = "ten", nullable = false)
    private String ten;

    @Column(name = "hinhanh", nullable = false)
    private String hinhanh;

    @Column(name = "mota", nullable = false)
    private String mota;

    @Column(name = "giam", nullable = false)
    private Float giam;

    @Column(name = "ngaybatdau", nullable = false)
    private LocalDateTime ngaybatdau;

    @Column(name = "ngayketthuc", nullable = false)
    private LocalDateTime ngayketthuc;

    @Column(name = "trangthai", nullable = false)
    private Integer trangthai;

}
