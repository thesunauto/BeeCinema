package vn.edu.poly.beecinema.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class VeID implements Serializable {
    @Column(name = "idsuatchieu")
    private Integer idsuatchieu;
    @Column(name = "idghe")
    private Integer idghe;
}
