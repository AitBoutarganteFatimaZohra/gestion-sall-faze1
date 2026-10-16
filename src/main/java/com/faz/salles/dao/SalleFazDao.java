package com.faz.salles.dao;

import com.faz.salles.modele.SalleFaz;
import javax.persistence.EntityManager;
import java.util.List;

public class SalleFazDao extends DaoFaz1<SalleFaz> {

    public SalleFazDao(EntityManager gestionnaireEntite) {
        super(SalleFaz.class, gestionnaireEntite);
    }

    // Recherche les salles ayant un indice de confort minimum
    public List<SalleFaz> rechercherParConfortMin(int confortMin) {
        return gestionnaireEntite.createQuery(
                        "SELECT s FROM SalleFaz s WHERE s.indiceConfort >= :confortMin", SalleFaz.class)
                .setParameter("confortMin", confortMin)
                .getResultList();
    }

    // NOUVELLE REQUÊTE JPQL : Rechercher les salles avec un confort strictement supérieur
    public List<SalleFaz> rechercherParConfortSuperieur(int seuil) {
        return gestionnaireEntite.createQuery(
                        "SELECT s FROM SalleFaz s WHERE s.indiceConfort > :seuil", SalleFaz.class)
                .setParameter("seuil", seuil)
                .getResultList();
    }
}