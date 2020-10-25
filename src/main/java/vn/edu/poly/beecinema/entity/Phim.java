package vn.edu.poly.beecinema.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "phim")
@Data
public class Phim implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "iddotuoi", nullable = false)
    private String iddotuoi;

    @Column(name = "idngonngu", nullable = false)
    private String idngonngu;

    @Column(name = "idloaiphim", nullable = false)
    private String idloaiphim;

    @Column(name = "idnhanvien", nullable = false)
    private String idnhanvien;

    @Column(name = "ten", nullable = false)
    private String ten;

    @Column(name = "hinhanh")
    private String hinhanh;

    @Column(name = "ngaybatdau", nullable = false)
    private LocalDateTime ngaybatdau;

    @Column(name = "ngayketthuc", nullable = false)
    private LocalDateTime ngayketthuc;

    @Column(name = "dodai", nullable = false)
    private Integer dodai;

    @Column(name = "mota")
    private String mota;

    @Column(name = "trangthai", nullable = false)
    private Integer trangthai;

    @Column(name = "idquocgia", nullable = false)
    private String idquocgia;

}
