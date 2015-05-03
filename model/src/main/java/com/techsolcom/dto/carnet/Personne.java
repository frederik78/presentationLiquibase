package com.techsolcom.dto.carnet;



import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "personne_psn")
public class Personne {



    @Id
    @Column(name = "psn_id", unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "psn_prenom")
    private String prenom;

    @Column(name = "psn_nom")
    private String nom ;

    @Column(name = "psn_naissance")
    private Date naissance;

    @OneToMany(mappedBy = "personne")
    private List<AdresseContact> adressesContact;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getNaissance() {
        return naissance;
    }

    public void setNaissance(Date naissance) {
        this.naissance = naissance;
    }

    public List<AdresseContact> getAdressesContact() {
        return adressesContact;
    }

    public void setAdressesContact(List<AdresseContact> adressesContact) {
        this.adressesContact = adressesContact;
    }

}
