package vn.edu.poly.beecinema.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "taikhoan")
@Data
public class Taikhoan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "matkhau", nullable = false)
    private String matkhau;

    @Column(name = "ten", nullable = false)
    private String ten;

    @Column(name = "gioitinh")
    private Integer gioitinh;

    @Column(name = "ngaysinh")
    private LocalDate ngaysinh;

    @Column(name = "diachi")
    private String diachi;

    @Column(name = "sodienthoai")
    private String sodienthoai;

    @Column(name = "email")
    private String email;

    @Column(name = "idquyen", nullable = false)
    private String idquyen;

    @Column(name = "ngaytao", nullable = false)
    private LocalDateTime ngaytao;

    @Column(name = "hinhanh")
    private String hinhanh;

    @Column(name = "mota")
    private String mota;

    @Column(name = "trangthai", nullable = false)
    private Integer trangthai;

}
