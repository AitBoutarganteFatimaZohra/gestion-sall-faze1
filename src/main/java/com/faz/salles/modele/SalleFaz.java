package com.faz.salles.modele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
public class SalleFaz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String referenceInterne;

    @Column(nullable = false)
    private int indiceConfort;

    @Column(nullable = false)
    private double superficieM2;

    @Column(nullable = false)
    private boolean estClimatisee;

    @Column(nullable = false)
    private String niveauSecurite;

    @Column(nullable = false)
    private String dateDerniereInspection;

    // Constructeurs
    public SalleFaz() {}

    public SalleFaz(String referenceInterne, int indiceConfort, double superficieM2,
                    boolean estClimatisee, String niveauSecurite, String dateDerniereInspection) {
        this.referenceInterne = referenceInterne;
        this.indiceConfort = indiceConfort;
        this.superficieM2 = superficieM2;
        this.estClimatisee = estClimatisee;
        this.niveauSecurite = niveauSecurite;
        this.dateDerniereInspection = dateDerniereInspection;
    }

    // Getters et Setters personnalisés
    public Long recupererId() {
        return id;
    }

    public String recupererReferenceInterne() {
        return referenceInterne;
    }

    public void definirReferenceInterne(String referenceInterne) {
        this.referenceInterne = referenceInterne;
    }

    public int recupererIndiceConfort() {
        return indiceConfort;
    }

    public void definirIndiceConfort(int indiceConfort) {
        this.indiceConfort = indiceConfort;
    }

    public double recupererSuperficieM2() {
        return superficieM2;
    }

    public void definirSuperficieM2(double superficieM2) {
        this.superficieM2 = superficieM2;
    }

    public boolean isEstClimatisee() {
        return estClimatisee;
    }

    public void setEstClimatisee(boolean estClimatisee) {
        this.estClimatisee = estClimatisee;
    }

    public String recupererNiveauSecurite() {
        return niveauSecurite;
    }

    public void definirNiveauSecurite(String niveauSecurite) {
        this.niveauSecurite = niveauSecurite;
    }

    public String recupererDateDerniereInspection() {
        return dateDerniereInspection;
    }

    public void definirDateDerniereInspection(String dateDerniereInspection) {
        this.dateDerniereInspection = dateDerniereInspection;
    }

    @Override
    public String toString() {
        return "SalleFaz [id=" + id +
                ", referenceInterne=" + referenceInterne +
                ", indiceConfort=" + indiceConfort +
                ", superficieM2=" + superficieM2 +
                ", estClimatisee=" + estClimatisee +
                ", niveauSecurite=" + niveauSecurite +
                ", dateDerniereInspection=" + dateDerniereInspection + "]";
    }
}