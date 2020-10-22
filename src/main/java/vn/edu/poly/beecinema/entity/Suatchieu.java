package vn.edu.poly.beecinema.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "suatchieu")
@Data
public class Suatchieu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "idphim", nullable = false)
    private String idphim;

    @Column(name = "idphong", nullable = false)
    private String idphong;

    @Column(name = "idnhanvien", nullable = false)
    private String idnhanvien;

    @Column(name = "idkhunggio", nullable = false)
    private String idkhunggio;

    @Column(name = "ngaytao", nullable = false)
    private LocalDate ngaytao;

    @Column(name = "ngaychieu", nullable = false)
    private LocalDate ngaychieu;

    @Column(name = "dongia", nullable = false)
    private Float dongia;

    @Column(name = "phuthuyonline", nullable = false)
    private Integer phuthuyonline;

    @Column(name = "trangthai", nullable = false)
    private Integer trangthai;

}
