import com.faz.salles.modele.SalleFaz;
import com.faz.salles.modele.Us1;
import com.faz.salles.noyau.FazPersistance;
import com.faz.salles.service.OrchestrateurFaz;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("========================================");
        System.out.println("   DÉMARRAGE DU PROGRAMME FAZ-SALLES   ");
        System.out.println("========================================\n");

        OrchestrateurFaz orchestrateur = new OrchestrateurFaz();

        try {
            // ===================== CRÉATION DES SALLES =====================
            System.out.println("---- Création des salles ----");
            SalleFaz salle1 = new SalleFaz("LAB-VR-01", 9, 25.5, true, "HAUT", "2025-01-15");
            SalleFaz salle2 = new SalleFaz("STUDIO-A", 6, 18.0, false, "MOYEN", "2024-11-20");
            SalleFaz salle3 = new SalleFaz("SALLE-GROUPE", 7, 30.0, true, "HAUT", "2025-02-01");
            orchestrateur.ajouterSalle(salle1);
            orchestrateur.ajouterSalle(salle2);
            orchestrateur.ajouterSalle(salle3);
            System.out.println("3 salles créées.\n");

            // ===================== CRÉATION DES UTILISATEURS =====================
            System.out.println("---- Création des utilisateurs ----");
            Us1 user1 = new Us1("jdupont2025", "jd@faz.fr", 3, "2025-01-10", salle1);
            Us1 user2 = new Us1("mmartin2025", "mm@faz.fr", 5, "2025-01-12", salle2);
            Us1 user3 = new Us1("abennani2025", "ab@faz.fr", 4, "2025-01-14", salle3);
            orchestrateur.ajouterUtilisateur(user1);
            orchestrateur.ajouterUtilisateur(user2);
            orchestrateur.ajouterUtilisateur(user3);
            System.out.println("3 utilisateurs créés.\n");

            // ===================== LISTE DES SALLES =====================
            System.out.println("---- Liste des salles ----");
            List<SalleFaz> salles = orchestrateur.listerSalles();
            for (SalleFaz s : salles) {
                System.out.println("  " + s);
            }
            System.out.println();

            // ===================== LISTE DES UTILISATEURS =====================
            System.out.println("---- Liste des utilisateurs ----");
            List<Us1> utilisateurs = orchestrateur.listerUtilisateurs();
            for (Us1 u : utilisateurs) {
                System.out.println("  " + u);
            }
            System.out.println();

            // ===================== RECHERCHE JPQL : SALLES PAR CONFORT =====================
            System.out.println("---- Recherche : Salles avec confort >= 7 ----");
            List<SalleFaz> sallesConfort = orchestrateur.sallesParConfortMin(7);
            for (SalleFaz s : sallesConfort) {
                System.out.println("  " + s.recupererReferenceInterne() + " (confort=" + s.recupererIndiceConfort() + ")");
            }
            System.out.println();

            // ===================== RECHERCHE JPQL : UTILISATEURS PAR SALLE =====================
            System.out.println("---- Recherche : Utilisateurs dans LAB-VR-01 ----");
            List<Us1> usersLab = orchestrateur.utilisateursParReferenceSalle("LAB-VR-01");
            for (Us1 u : usersLab) {
                System.out.println("  " + u.recupererIdentifiantConnexion());
            }
            System.out.println();

            // ===================== RECHERCHE JPQL : UTILISATEURS PAR MOTIF =====================
            System.out.println("---- Recherche : Utilisateurs contenant '2025' ----");
            List<Us1> usersMotif = orchestrateur.utilisateursParIdentifiantContient("2025");
            for (Us1 u : usersMotif) {
                System.out.println("  " + u.recupererIdentifiantConnexion());
            }
            System.out.println();

            // ===================== MODIFICATION =====================
            System.out.println("---- Modification : confort de LAB-VR-01 -> 10 ----");
            salle1.definirIndiceConfort(10);
            orchestrateur.modifierSalle(salle1);
            System.out.println("  Salle modifiée : " + salle1.recupererReferenceInterne() + " (confort=" + salle1.recupererIndiceConfort() + ")\n");

            // ===================== SUPPRESSION =====================
            System.out.println("---- Suppression : utilisateur mmartin2025 ----");
            orchestrateur.supprimerUtilisateur(user2);
            System.out.println("  Utilisateur supprimé.\n");

            // ===================== LISTE FINALE =====================
            System.out.println("---- Liste finale des utilisateurs ----");
            List<Us1> usersFinaux = orchestrateur.listerUtilisateurs();
            for (Us1 u : usersFinaux) {
                System.out.println("  " + u.recupererIdentifiantConnexion());
            }

        } catch (Exception e) {
            System.err.println("\n[ERREUR] " + e.getMessage());
            e.printStackTrace();
        } finally {
            // ===================== FERMETURE DES RESSOURCES =====================
            orchestrateur.fermer();
            FazPersistance.fermerFabrique();
        }

        System.out.println("\n========================================");
        System.out.println("   FIN DU PROGRAMME FAZ-SALLES   ");
        System.out.println("========================================");
    }
}