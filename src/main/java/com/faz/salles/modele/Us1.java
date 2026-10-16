package com.faz.salles.modele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
public class Us1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String identifiantConnexion;

    @Column(nullable = false, unique = true)
    private String adresseContact;

    @Column(nullable = false)
    private int niveauAccreditation;

    @Column(nullable = false)
    private String dateAffiliation;

    @ManyToOne
    @JoinColumn(name = "salle_affectee_id")
    private SalleFaz salleAffectee;

    // Constructeurs
    public Us1() {}

    public Us1(String identifiantConnexion, String adresseContact, int niveauAccreditation,
               String dateAffiliation, SalleFaz salleAffectee) {
        this.identifiantConnexion = identifiantConnexion;
        this.adresseContact = adresseContact;
        this.niveauAccreditation = niveauAccreditation;
        this.dateAffiliation = dateAffiliation;
        this.salleAffectee = salleAffectee;
    }

    // Getters et Setters (style personnalisé comme SalleFaz)
    public Long recupererId() {
        return id;
    }

    public String recupererIdentifiantConnexion() {
        return identifiantConnexion;
    }

    public void definirIdentifiantConnexion(String identifiantConnexion) {
        this.identifiantConnexion = identifiantConnexion;
    }

    public String recupererAdresseContact() {
        return adresseContact;
    }

    public void definirAdresseContact(String adresseContact) {
        this.adresseContact = adresseContact;
    }

    public int recupererNiveauAccreditation() {
        return niveauAccreditation;
    }

    public void definirNiveauAccreditation(int niveauAccreditation) {
        this.niveauAccreditation = niveauAccreditation;
    }

    public String recupererDateAffiliation() {
        return dateAffiliation;
    }

    public void definirDateAffiliation(String dateAffiliation) {
        this.dateAffiliation = dateAffiliation;
    }

    public SalleFaz recupererSalleAffectee() {
        return salleAffectee;
    }

    public void definirSalleAffectee(SalleFaz salleAffectee) {
        this.salleAffectee = salleAffectee;
    }

    @Override
    public String toString() {
        return "Us1 [id=" + id +
                ", identifiantConnexion=" + identifiantConnexion +
                ", adresseContact=" + adresseContact +
                ", niveauAccreditation=" + niveauAccreditation +
                ", dateAffiliation=" + dateAffiliation +
                ", salleAffectee=" + (salleAffectee != null ? salleAffectee.recupererReferenceInterne() : "aucune") + "]";
    }
}