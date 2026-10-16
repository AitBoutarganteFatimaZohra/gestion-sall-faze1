# Gestion des Salles - Projet JPA/Hibernate (Faz)

Application Java JPA/Hibernate permettant la gestion des salles et de leurs utilisateurs, dans un contexte sans EJB (gestion manuelle des transactions).

## 📋 Description

Ce projet a été réalisé dans le cadre d'un TP sur la persistance Java avec JPA et Hibernate. Il met en œuvre :

- Une architecture en couches (Entités, DAO, Service, Utilitaire)
- Un DAO générique (`DaoFaz1`) pour les opérations CRUD
- Des DAO spécifiques (`SalleFazDao`, `Us1Dao`) avec requêtes JPQL personnalisées
- Un orchestrateur de services (`OrchestrateurFaz`)
- Une classe utilitaire pour la gestion de la persistance (`FazPersistance`)
- Des tests unitaires avec JUnit 4

## 🏗️ Architecture du projet
