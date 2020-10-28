package vn.edu.poly.beecinema.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class VeID implements Serializable {
    private Suatchieu suatchieu;
    private Ghe ghe;
}
