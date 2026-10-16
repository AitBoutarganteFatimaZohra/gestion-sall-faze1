package com.faz.salles.noyau;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FazPersistance {

    // Instance unique de EntityManagerFactory (créée au démarrage)
    private static final EntityManagerFactory FABRIQUE =
            Persistence.createEntityManagerFactory("UniteSallesFaz");

    // Constructeur privé pour empêcher l'instanciation de cette classe
    private FazPersistance() {}

    // Fournit un nouvel EntityManager à chaque appel (durée de vie courte)
    public static EntityManager obtenirGestionnaire() {
        return FABRIQUE.createEntityManager();
    }

    // Ferme l'EntityManagerFactory (à appeler à l'arrêt de l'application)
    public static void fermerFabrique() {
        if (FABRIQUE != null && FABRIQUE.isOpen()) {
            FABRIQUE.close();
            System.out.println("[FAZ] Fabrique de persistance fermée.");
        }
    }
}