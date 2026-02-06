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
SystemeGestionUniversitaire/
│
├── src/
│   ├── model/                    # Couche Modèle (Entités)
│   │   ├── Etudiant.java        # Classe représentant un étudiant
│   │   ├── Enseignant.java      # Classe représentant un enseignant
│   │   ├── Matiere.java         # Classe représentant une matière
│   │   └── Note.java            # Classe représentant une note
│   │
│   ├── service/                  # Couche Service (Logique métier)
│   │   ├── EtudiantService.java # Gestion des étudiants
│   │   ├── MatiereService.java  # Gestion des matières
│   │   └── NoteService.java     # Gestion des notes
│   │
│   ├── util/                     # Couche Utilitaire
│   │   ├── Menu.java            # Gestion des menus console
│   │   └── GestionFichier.java  # Persistance des données
│   │
│   └── main/                     # Point d'entrée
│       └── MainApp.java         # Classe principale
│
├── data/                         # Dossier de données
│   ├── etudiants.txt            # Fichier des étudiants
│   ├── matieres.txt             # Fichier des matières
│   ├── notes.txt                # Fichier des notes
│   └── enseignants.txt          # Fichier des enseignants
│
└── README.md                     # Documentation du projet
```

## Installation et Exécution

### Prérequis

- Java JDK 8 ou supérieur installé
- Eclipse IDE (ou tout autre IDE Java)
- Git (optionnel)

### Installation avec Eclipse

#### Méthode 1 : Création manuelle

1. **Créer un nouveau projet Java dans Eclipse**
```
   File > New > Java Project
   Nom du projet : SystemeGestionUniversitaire
   JRE : Sélectionner Java SE 8 ou supérieur
```

2. **Créer la structure des packages**
   - Clic droit sur `src` > New > Package
   - Créer les packages : `model`, `service`, `util`, `main`

3. **Ajouter les fichiers source**
   - Copier chaque fichier .java dans son package respectif
   - Eclipse compilera automatiquement

4. **Créer le dossier data**
   - Clic droit sur le projet > New > Folder
   - Nom : `data`

#### Méthode 2 : Import depuis GitHub (si vous avez cloné)

1. **Cloner le repository**
```bash
   git clone https://github.com/votre-username/systeme-gestion-universitaire.git
```

2. **Importer dans Eclipse**
```
   File > Import > General > Existing Projects into Workspace
   Sélectionner le dossier cloné
```

### Exécution du Programme

1. **Ouvrir la classe MainApp.java**
   - Naviguer vers `src/main/MainApp.java`

2. **Exécuter l'application**
   - Clic droit sur `MainApp.java`
   - Sélectionner `Run As > Java Application`

3. **Interface Console**
   - Le programme s'exécutera dans la console Eclipse
   - Suivez les instructions du menu interactif

##  Guide d'Utilisation

### Premier Lancement

Au premier lancement, le système :
1. Crée automatiquement le dossier `data/`
2. Initialise des données de test (3 étudiants, 3 matières, quelques notes)
3. Affiche le menu principal

### Navigation dans les Menus
```
╔════════════════════════════════════════════════╗
║   SYSTÈME DE GESTION UNIVERSITAIRE - SGU      ║
║   Version 1.0 - Développé en Java SE          ║
╚════════════════════════════════════════════════╝

┌─────────────── MENU PRINCIPAL ───────────────┐
│  1. Gestion des Étudiants                    │
│  2. Gestion des Matières                     │
│  3. Gestion des Notes                        │
│  4. Statistiques et Classements              │
│  5. Sauvegarde des Données                   │
│  0. Quitter                                  │
└──────────────────────────────────────────────┘
```

### Exemples d'Utilisation

#### Ajouter un Étudiant
```
1. Menu Principal > 1. Gestion des Étudiants
2. Choisir > 1. Ajouter un étudiant
3. Remplir les informations demandées :
   - Nom : Diop
   - Prénom : Amadou
   - Email : amadou.diop@univ.sn
   - Date de naissance : 15/03/2002
4. L'ID est généré automatiquement (ex: ETU0001)
```

#### Ajouter une Note
```
1. Menu Principal > 3. Gestion des Notes
2. Choisir > 1. Ajouter une note
3. Entrer :
   - ID étudiant : ETU0001
   - Code matière : INFO101
   - Note : 15.5
   - Type : Exam
```

#### Voir le Classement
```
1. Menu Principal > 4. Statistiques et Classements
2. Choisir > 1. Classement général des étudiants
3. Le tableau affiche tous les étudiants triés par moyenne
```

## Concepts POO Implémentés

### Encapsulation
- Tous les attributs sont `private`
- Accès contrôlé via getters/setters
- Validation des données dans les setters

### Abstraction
- Séparation claire entre Model, Service, et View (Menu)
- Interfaces de services bien définies

### Modularité
- Chaque classe a une responsabilité unique (Single Responsibility)
- Code réutilisable et maintenable

### Gestion des Exceptions
- Try-catch pour les opérations de fichiers
- Validation des entrées utilisateur
- Messages d'erreur clairs et explicites

## Personnalisation et Extension

### Ajouter un Nouveau Champ à Etudiant
```java
// Dans Etudiant.java
private String numeroTelephone;

public String getNumeroTelephone() {
    return numeroTelephone;
}

public void setNumeroTelephone(String numeroTelephone) {
    this.numeroTelephone = numeroTelephone;
}
```

### Créer un Nouveau Type de Rapport
```java
// Dans EtudiantService.java
public List getEtudiantsEnDifficulte() {
    return etudiants.stream()
        .filter(e -> e.calculerMoyenne() < 10)
        .collect(Collectors.toList());
}
```

## Format des Fichiers de Données

### etudiants.txt
```
ETU0001;Diop;Amadou;amadou.diop@univ.sn;15/03/2002
ETU0002;Ndiaye;Fatou;fatou.ndiaye@univ.sn;22/07/2003
```

### matieres.txt
```
INFO101;Programmation Java;3;NONE
MATH101;Mathématiques Discrètes;4;ENS001
```

### notes.txt
```
ETU0001;INFO101;15.5;;Exam
ETU0001;MATH101;14.0;;DS
```




## Auteur

**BOUBACAR Thiam**
- GitHub: [@BoubacarThiam](https://github.com/Boubacarthiam005@icloud.com)
- Email: Boubacarthiam005@icloud.com


##  Remerciements

- Oracle pour la documentation Java
- La communauté Stack Overflow
- Mes formateurs en informatique

##  Captures d'Écran

### Menu Principal
```
╔════════════════════════════════════════════════╗
║   SYSTÈME DE GESTION UNIVERSITAIRE - SGU      ║
║   Version 1.0 - Développé en Java SE          ║
╚════════════════════════════════════════════════╝

 Bienvenue dans le Système de Gestion Universitaire
───────────────────────────────────────────────────────
Étudiants chargés : 3
Matières chargées : 3
Notes chargées : 5
───────────────────────────────────────────────────────
```

### Classement des Étudiants
```
=== CLASSEMENT GÉNÉRAL DES ÉTUDIANTS ===
────────────────────────────────────────────────────────────────────────────────
Rang   ID         Nom Complet               Email                          Moyenne
────────────────────────────────────────────────────────────────────────────────
1      ETU0002    Fatou Ndiaye              fatou.ndiaye@univ.sn          16.75
2      ETU0001    Amadou Diop               amadou.diop@univ.sn           14.75
3      ETU0003    Moussa Seck               moussa.seck@univ.sn           12.00
────────────────────────────────────────────────────────────────────────────────
```

## Ressources Utiles

- [Documentation Java SE](https://docs.oracle.com/javase/8/docs/)
- [Eclipse IDE](https://www.eclipse.org/)
- [Git Documentation](https://git-scm.com/doc)

---

**Si ce projet vous a été utile, n'hésitez pas à lui donner une étoile sur GitHub !**
```

```