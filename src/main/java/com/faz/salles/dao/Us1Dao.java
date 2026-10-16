package com.faz.salles.dao;

import com.faz.salles.modele.Us1;
import javax.persistence.EntityManager;
import java.util.List;

public class Us1Dao extends DaoFaz1<Us1> {

    public Us1Dao(EntityManager gestionnaireEntite) {
        super(Us1.class, gestionnaireEntite);
    }

    // Recherche les utilisateurs affectés à une salle (par sa référence interne)
    public List<Us1> rechercherParReferenceSalle(String reference) {
        return gestionnaireEntite.createQuery(
                        "SELECT u FROM Us1 u WHERE u.salleAffectee.referenceInterne = :reference", Us1.class)
                .setParameter("reference", reference)
                .getResultList();
    }

    // NOUVELLE REQUÊTE JPQL : Rechercher les utilisateurs dont l'identifiant contient une chaîne
    public List<Us1> rechercherParIdentifiantContient(String motif) {
        return gestionnaireEntite.createQuery(
                        "SELECT u FROM Us1 u WHERE u.identifiantConnexion LIKE :motif", Us1.class)
                .setParameter("motif", "%" + motif + "%")
                .getResultList();
    }
}