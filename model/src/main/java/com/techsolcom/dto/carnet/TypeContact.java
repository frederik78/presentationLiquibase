package com.techsolcom.dto.carnet;



import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "typecontact_tct")
public class TypeContact {



    @Id
    @Column(name = "tct_id", unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "tct_nom")
    private String nom;

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

}
