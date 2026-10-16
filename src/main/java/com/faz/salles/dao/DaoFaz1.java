package com.faz.salles.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class DaoFaz1<T> {

    private final Class<T> typeEntite;
    protected EntityManager gestionnaireEntite;

    public DaoFaz1(Class<T> typeEntite, EntityManager gestionnaireEntite) {
        this.typeEntite = typeEntite;
        this.gestionnaireEntite = gestionnaireEntite;
    }

    public void enregistrer(T entite) {
        EntityTransaction transaction = gestionnaireEntite.getTransaction();
        try {
            transaction.begin();
            gestionnaireEntite.persist(entite);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("[DAO] Erreur lors de l'enregistrement de " + typeEntite.getSimpleName() + " : " + ex.getMessage());
            throw ex;
        }
    }

    public T rechercherParId(Object identifiant) {
        try {
            return gestionnaireEntite.find(typeEntite, identifiant);
        } catch (Exception ex) {
            System.err.println("[DAO] Erreur lors de la recherche de " + typeEntite.getSimpleName() + " avec id=" + identifiant + " : " + ex.getMessage());
            throw ex;
        }
    }

    public T modifier(T entite) {
        EntityTransaction transaction = gestionnaireEntite.getTransaction();
        try {
            transaction.begin();
            T entiteFusionnee = gestionnaireEntite.merge(entite);
            transaction.commit();
            return entiteFusionnee;
        } catch (Exception ex) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("[DAO] Erreur lors de la modification de " + typeEntite.getSimpleName() + " : " + ex.getMessage());
            throw ex;
        }
    }

    public void supprimer(T entite) {
        EntityTransaction transaction = gestionnaireEntite.getTransaction();
        try {
            transaction.begin();
            gestionnaireEntite.remove(gestionnaireEntite.contains(entite) ? entite : gestionnaireEntite.merge(entite));
            transaction.commit();
        } catch (Exception ex) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("[DAO] Erreur lors de la suppression de " + typeEntite.getSimpleName() + " : " + ex.getMessage());
            throw ex;
        }
    }

    public List<T> listerTout() {
        try {
            return gestionnaireEntite.createQuery("SELECT e FROM " + typeEntite.getSimpleName() + " e", typeEntite).getResultList();
        } catch (Exception ex) {
            System.err.println("[DAO] Erreur lors de la liste de " + typeEntite.getSimpleName() + " : " + ex.getMessage());
            throw ex;
        }
    }
}