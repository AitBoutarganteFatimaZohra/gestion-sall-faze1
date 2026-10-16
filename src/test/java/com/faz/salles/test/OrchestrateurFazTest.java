package com.faz.salles.test;

import com.faz.salles.modele.SalleFaz;
import com.faz.salles.modele.Us1;
import com.faz.salles.service.OrchestrateurFaz;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class OrchestrateurFazTest {

    private OrchestrateurFaz orchestrateur;

    @Before
    public void setUp() {
        orchestrateur = new OrchestrateurFaz();
    }

    @After
    public void tearDown() {
        orchestrateur.fermer();
    }

    @Test
    public void testCreationEtRechercheSalle() {
        SalleFaz salle = new SalleFaz("LAB-TEST-01", 8, 25.0, true, "HAUT", "2025-01-15");
        orchestrateur.ajouterSalle(salle);

        List<SalleFaz> salles = orchestrateur.listerSalles();
        assertFalse("La liste des salles ne doit pas être vide", salles.isEmpty());
        assertEquals("LAB-TEST-01", salles.get(0).recupererReferenceInterne());
    }

    @Test
    public void testCreationUtilisateurAvecSalle() {
        SalleFaz salle = new SalleFaz("STUDIO-TEST", 6, 18.0, false, "MOYEN", "2024-11-20");
        orchestrateur.ajouterSalle(salle);

        Us1 utilisateur = new Us1("testuser2025", "test@faz.fr", 3, "2025-01-10", salle);
        orchestrateur.ajouterUtilisateur(utilisateur);

        List<Us1> utilisateurs = orchestrateur.listerUtilisateurs();
        assertFalse("La liste des utilisateurs ne doit pas être vide", utilisateurs.isEmpty());
        assertEquals("testuser2025", utilisateurs.get(0).recupererIdentifiantConnexion());
        assertEquals("STUDIO-TEST", utilisateurs.get(0).recupererSalleAffectee().recupererReferenceInterne());
    }

    @Test
    public void testRechercheUtilisateursParSalle() {
        SalleFaz salle = new SalleFaz("SALLE-GROUPE", 10, 30.0, true, "HAUT", "2025-02-01");
        orchestrateur.ajouterSalle(salle);

        Us1 u1 = new Us1("user1faz", "user1@faz.fr", 2, "2025-01-01", salle);
        Us1 u2 = new Us1("user2faz", "user2@faz.fr", 4, "2025-01-02", salle);
        orchestrateur.ajouterUtilisateur(u1);
        orchestrateur.ajouterUtilisateur(u2);

        List<Us1> utilisateurs = orchestrateur.utilisateursParReferenceSalle("SALLE-GROUPE");
        assertEquals("Il doit y avoir 2 utilisateurs dans cette salle", 2, utilisateurs.size());
    }

    @Test
    public void testRechercheSallesParConfort() {
        SalleFaz salle1 = new SalleFaz("CONFORT-9", 5, 20.0, true, "HAUT", "2025-01-01");
        SalleFaz salle2 = new SalleFaz("CONFORT-3", 5, 20.0, false, "BAS", "2025-01-01");
        orchestrateur.ajouterSalle(salle1);
        orchestrateur.ajouterSalle(salle2);

        List<SalleFaz> sallesConfortables = orchestrateur.sallesParConfortMin(7);
        assertEquals("Il doit y avoir 1 salle avec un confort >= 7", 1, sallesConfortables.size());
    }
}