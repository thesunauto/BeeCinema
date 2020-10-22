package vn.edu.poly.beecinema.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Table(name = "loaighe")
@Entity
@Data
public class Loaighe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "ten", nullable = false)
    private String ten;

    @Column(name = "gia", nullable = false)
    private Float gia;

    @Column(name = "ngaytao", nullable = false)
    private LocalDateTime ngaytao;

    @Column(name = "trangthai", nullable = false)
    private Integer trangthai;

}
