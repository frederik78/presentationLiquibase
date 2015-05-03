package com.techsolcom.dto.carnet;



import javax.persistence.*;

@Entity
@Table(name = "adressecontact_act")
public class AdresseContact {

    @Id
    @Column(name = "act_id", unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "act_libelle")
    private String libelle;

    @ManyToOne
    @JoinColumn(name = "psn_personne_id")
    private Personne personne;

    @OneToOne
    @JoinColumn(name = "tct_typecontact_id")
    private TypeContact typeContact;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public TypeContact getTypeContact() {
        return typeContact;
    }

    public void setTypeContact(TypeContact typeContact) {
        this.typeContact = typeContact;
    }
}
