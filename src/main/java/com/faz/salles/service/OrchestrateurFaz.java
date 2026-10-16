package com.faz.salles.service;

import com.faz.salles.dao.SalleFazDao;
import com.faz.salles.dao.Us1Dao;
import com.faz.salles.modele.SalleFaz;
import com.faz.salles.modele.Us1;
import com.faz.salles.noyau.FazPersistance;

import javax.persistence.EntityManager;
import java.util.List;

public class OrchestrateurFaz {

    private EntityManager gestionnaireEntite;
    private SalleFazDao salleDao;
    private Us1Dao us1Dao;

    public OrchestrateurFaz() {
        try {
            // On utilise la classe utilitaire pour obtenir un EntityManager
            gestionnaireEntite = FazPersistance.obtenirGestionnaire();
            salleDao = new SalleFazDao(gestionnaireEntite);
            us1Dao = new Us1Dao(gestionnaireEntite);
        } catch (Exception ex) {
            System.err.println("[SERVICE] Impossible d'initialiser l'OrchestrateurFaz : " + ex.getMessage());
            throw ex;
        }
    }

    public void fermer() {
        try {
            if (gestionnaireEntite != null && gestionnaireEntite.isOpen()) {
                gestionnaireEntite.close();
                System.out.println("[SERVICE] EntityManager fermé.");
            }
        } catch (Exception ex) {
            System.err.println("[SERVICE] Erreur lors de la fermeture de l'EntityManager : " + ex.getMessage());
        }
    }

    // ==================== SALLES ====================

    public void ajouterSalle(SalleFaz salle) {
        salleDao.enregistrer(salle);
    }

    public List<SalleFaz> listerSalles() {
        return salleDao.listerTout();
    }

    public List<SalleFaz> sallesParConfortMin(int confortMin) {
        return salleDao.rechercherParConfortMin(confortMin);
    }

    public List<SalleFaz> sallesParConfortSuperieur(int seuil) {
        return salleDao.rechercherParConfortSuperieur(seuil);
    }

    public SalleFaz trouverSalleParId(Object id) {
        return salleDao.rechercherParId(id);
    }

    public void modifierSalle(SalleFaz salle) {
        salleDao.modifier(salle);
    }

    public void supprimerSalle(SalleFaz salle) {
        salleDao.supprimer(salle);
    }

    // ==================== UTILISATEURS ====================

    public void ajouterUtilisateur(Us1 utilisateur) {
        us1Dao.enregistrer(utilisateur);
    }

    public List<Us1> listerUtilisateurs() {
        return us1Dao.listerTout();
    }

    public List<Us1> utilisateursParReferenceSalle(String reference) {
        return us1Dao.rechercherParReferenceSalle(reference);
    }

    public List<Us1> utilisateursParIdentifiantContient(String motif) {
        return us1Dao.rechercherParIdentifiantContient(motif);
    }

    public Us1 trouverUtilisateurParId(Object id) {
        return us1Dao.rechercherParId(id);
    }

    public void modifierUtilisateur(Us1 utilisateur) {
        us1Dao.modifier(utilisateur);
    }

    public void supprimerUtilisateur(Us1 utilisateur) {
        us1Dao.supprimer(utilisateur);
    }
}