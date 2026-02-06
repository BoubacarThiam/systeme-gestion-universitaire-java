# Système de Gestion Universitaire (SGU)

![Java](https://img.shields.io/badge/Java-SE%208%2B-orange)
![License](https://img.shields.io/badge/License-MIT-green)
![Status](https://img.shields.io/badge/Status-Production-blue)

##  Description

Le **Système de Gestion Universitaire** est une application console professionnelle développée en Java SE qui permet de gérer efficacement les données d'une institution universitaire. Elle offre des fonctionnalités complètes pour la gestion des étudiants, des matières, des notes, ainsi que le calcul automatique des moyennes et l'affichage des classements.

## Fonctionnalités Principales

###  Gestion des Étudiants
- Ajouter un nouvel étudiant avec génération automatique d'ID
- Modifier les informations d'un étudiant existant
- Supprimer un étudiant avec confirmation
- Lister tous les étudiants enregistrés
-  Rechercher un étudiant par son ID

###  Gestion des Matières
- Ajouter une nouvelle matière avec code et coefficient
- ✏Modifier les informations d'une matière
-  Supprimer une matière
-  Lister toutes les matières disponibles
-  Associer un enseignant à une matière

### Gestion des Notes
- Ajouter une note pour un étudiant dans une matière
- Modifier une note existante
- Supprimer une note
- Afficher toutes les notes d'un étudiant
-  Validation des notes (entre 0 et 20)

### Statistiques et Classements
- Classement général des étudiants par moyenne décroissante
- Calcul de la moyenne générale de la classe
-  Statistiques détaillées du système
-Identification du meilleur étudiant

###  Persistance des Données
-  Sauvegarde automatique dans des fichiers texte
-  Chargement automatique au démarrage
-  Export/Import des données
- Structure de fichiers organisée

##  Technologies Utilisées

- **Langage** : Java SE 8+
- **IDE Compatible** : Eclipse, IntelliJ IDEA, NetBeans
- **Paradigme** : Programmation Orientée Objet (POO)
- **Persistance** : Fichiers texte (.txt)
- **Interface** : Console interactive

## Structure du Projet