package com.techsolcom.dto.carnet;



import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pays_pay")
public class Pays {



    @Id
    @Column(name = "pay_id", unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @Column(name = "pay_nom")
    private String nom ;

    @Column(name = "pay_code")
    private String code;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
