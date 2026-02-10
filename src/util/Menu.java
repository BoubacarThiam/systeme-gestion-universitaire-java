package util;

import model.Etudiant;
import model.Matiere;
import model.Note;
import service.EtudiantService;
import service.MatiereService;
import service.NoteService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Classe gérant l'affichage et l'interaction avec les menus console
 * @author Votre Nom
 * @version 1.0
 */
public class Menu {
    
    private Scanner scanner;
    private EtudiantService etudiantService;
    private MatiereService matiereService;
    private NoteService noteService;
    
    /**
     * Constructeur avec injection des services
     */
    public Menu(EtudiantService etudiantService, MatiereService matiereService, NoteService noteService) {
        this.scanner = new Scanner(System.in);
        this.etudiantService = etudiantService;
        this.matiereService = matiereService;
        this.noteService = noteService;
    }
    
    /**
     * Affiche l'en-tête du système
     */
    public void afficherEntete() {
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║   SYSTÈME DE GESTION UNIVERSITAIRE - SGU      ║");
        System.out.println("║   Version 1.0 - Développé en Java SE          ║");
        System.out.println("╚════════════════════════════════════════════════╝");
    }
    
    /**
     * Affiche le menu principal et gère les choix
     */
    public void afficherMenuPrincipal() {
        boolean continuer = true;
        
        while (continuer) {
            try {
                System.out.println("\n┌─────────────── MENU PRINCIPAL ───────────────┐");
                System.out.println("│  1. Gestion des Étudiants                    │");
                System.out.println("│  2. Gestion des Matières                     │");
                System.out.println("│  3. Gestion des Notes                        │");
                System.out.println("│  4. Statistiques et Classements              │");
                System.out.println("│  5. Sauvegarde des Données                   │");
                System.out.println("│  0. Quitter                                  │");
                System.out.println("└──────────────────────────────────────────────┘");
                System.out.print("Votre choix : ");
                
                int choix = scanner.nextInt();
                scanner.nextLine(); // Consommer le retour à la ligne
                
                switch (choix) {
                    case 1:
                        menuEtudiants();
                        break;
                    case 2:
                        menuMatieres();
                        break;
                    case 3:
                        menuNotes();
                        break;
                    case 4:
                        menuStatistiques();
                        break;
                    case 5:
                        sauvegarderDonnees();
                        break;
                    case 0:
                        continuer = quitterApplication();
                        break;
                    default:
                        System.out.println("Choix invalide. Veuillez réessayer.");
                }
                
            } catch (InputMismatchException e) {
                System.out.println("Erreur : Veuillez entrer un nombre valide.");
                scanner.nextLine(); // Vider le buffer
            }
        }
    }
    
    // ==================== MENU ÉTUDIANTS ====================
    
    /**
     * Affiche le menu de gestion des étudiants
     */
    private void menuEtudiants() {
        boolean retour = false;
        
        while (!retour) {
            try {
                System.out.println("\n┌────────── GESTION DES ÉTUDIANTS ─────────┐");
                System.out.println("│  1. Ajouter un étudiant                  │");
                System.out.println("│  2. Modifier un étudiant                 │");
                System.out.println("│  3. Supprimer un étudiant                │");
                System.out.println("│  4. Lister tous les étudiants            │");
                System.out.println("│  5. Rechercher un étudiant               │");
                System.out.println("│  0. Retour au menu principal             │");
                System.out.println("└──────────────────────────────────────────┘");
                System.out.print("Votre choix : ");
                
                int choix = scanner.nextInt();
                scanner.nextLine();
                
                switch (choix) {
                    case 1:
                        ajouterEtudiant();
                        break;
                    case 2:
                        modifierEtudiant();
                        break;
                    case 3:
                        supprimerEtudiant();
                        break;
                    case 4:
                        listerEtudiants();
                        break;
                    case 5:
                        rechercherEtudiant();
                        break;
                    case 0:
                        retour = true;
                        break;
                    default:
                        System.out.println(" Choix invalide.");
                }
                
            } catch (InputMismatchException e) {
                System.out.println(" Erreur : Veuillez entrer un nombre valide.");
                scanner.nextLine();
            }
        }
    }
    
    /**
     * Ajoute un nouvel étudiant
     */
    private void ajouterEtudiant() {
        System.out.println("\n=== AJOUT D'UN ÉTUDIANT ===");
        
        String id = etudiantService.genererNouvelId();
        System.out.println("ID généré automatiquement : " + id);
        
        System.out.print("Nom : ");
        String nom = scanner.nextLine();
        
        System.out.print("Prénom : ");
        String prenom = scanner.nextLine();
        
        System.out.print("Email : ");
        String email = scanner.nextLine();
        
        System.out.print("Date de naissance (JJ/MM/AAAA) : ");
        String dateNaissance = scanner.nextLine();
        
        Etudiant etudiant = new Etudiant(id, nom, prenom, email, dateNaissance);
        
        if (etudiantService.ajouterEtudiant(etudiant)) {
            System.out.println(" Étudiant ajouté avec succès !");
        } else {
            System.out.println(" Erreur lors de l'ajout de l'étudiant.");
        }
    }
    
    /**
     * Modifie un étudiant existant
     */
    private void modifierEtudiant() {
        System.out.println("\n=== MODIFICATION D'UN ÉTUDIANT ===");
        
        System.out.print("ID de l'étudiant à modifier : ");
        String id = scanner.nextLine();
        
        Etudiant etudiant = etudiantService.rechercherEtudiant(id);
        
        if (etudiant == null) {
            System.out.println(" Étudiant non trouvé.");
            return;
        }
        
        System.out.println("Étudiant trouvé : " + etudiant);
        System.out.println("\nNouvelles informations (Entrée pour conserver) :");
        
        System.out.print("Nouveau nom [" + etudiant.getNom() + "] : ");
        String nom = scanner.nextLine();
        if (nom.isEmpty()) nom = etudiant.getNom();
        
        System.out.print("Nouveau prénom [" + etudiant.getPrenom() + "] : ");
        String prenom = scanner.nextLine();
        if (prenom.isEmpty()) prenom = etudiant.getPrenom();
        
        System.out.print("Nouvel email [" + etudiant.getEmail() + "] : ");
        String email = scanner.nextLine();
        if (email.isEmpty()) email = etudiant.getEmail();
        
        if (etudiantService.modifierEtudiant(id, nom, prenom, email)) {
            System.out.println(" Étudiant modifié avec succès !");
        } else {
            System.out.println("Erreur lors de la modification.");
        }
    }
    
    /**
     * Supprime un étudiant
     */
    private void supprimerEtudiant() {
        System.out.println("\n=== SUPPRESSION D'UN ÉTUDIANT ===");
        
        System.out.print("ID de l'étudiant à supprimer : ");
        String id = scanner.nextLine();
        
        Etudiant etudiant = etudiantService.rechercherEtudiant(id);
        
        if (etudiant == null) {
            System.out.println(" Étudiant non trouvé.");
            return;
        }
        
        System.out.println("Étudiant trouvé : " + etudiant);
        System.out.print("Confirmer la suppression ? (O/N) : ");
        String confirmation = scanner.nextLine();
        
        if (confirmation.equalsIgnoreCase("O")) {
            if (etudiantService.supprimerEtudiant(id)) {
                System.out.println(" Étudiant supprimé avec succès !");
            } else {
                System.out.println(" Erreur lors de la suppression.");
            }
        } else {
            System.out.println("ℹ Suppression annulée.");
        }
    }
    
    /**
     * Liste tous les étudiants
     */
    private void listerEtudiants() {
        System.out.println("\n=== LISTE DES ÉTUDIANTS ===");
        
        List<Etudiant> etudiants = etudiantService.listerEtudiants();
        
        if (etudiants.isEmpty()) {
            System.out.println("ℹ Aucun étudiant enregistré.");
            return;
        }
        
        System.out.println("Nombre total : " + etudiants.size());
        System.out.println("─".repeat(80));
        
        for (Etudiant etudiant : etudiants) {
            System.out.println(etudiant);
        }
        
        System.out.println("─".repeat(80));
    }
    
    /**
     * Recherche un étudiant par ID
     */
    private void rechercherEtudiant() {
        System.out.println("\n=== RECHERCHE D'UN ÉTUDIANT ===");
        
        System.out.print("ID de l'étudiant : ");
        String id = scanner.nextLine();
        
        Etudiant etudiant = etudiantService.rechercherEtudiant(id);
        
        if (etudiant != null) {
            System.out.println("\n Étudiant trouvé :");
            System.out.println(etudiant);
            
            // Afficher les notes
            List<Note> notes = noteService.rechercherNotesEtudiant(id);
            if (!notes.isEmpty()) {
                System.out.println("\nNotes :");
                for (Note note : notes) {
                    System.out.println("  - " + note);
                }
            }
        } else {
            System.out.println(" Étudiant non trouvé.");
        }
    }
    
    // ==================== MENU MATIÈRES ====================
    
    /**
     * Affiche le menu de gestion des matières
     */
    private void menuMatieres() {
        boolean retour = false;
        
        while (!retour) {
            try {
                System.out.println("\n┌────────── GESTION DES MATIÈRES ──────────┐");
                System.out.println("│  1. Ajouter une matière                  │");
                System.out.println("│  2. Modifier une matière                 │");
                System.out.println("│  3. Supprimer une matière                │");
                System.out.println("│  4. Lister toutes les matières           │");
                System.out.println("│  5. Associer un enseignant               │");
                System.out.println("│  0. Retour au menu principal             │");
                System.out.println("└──────────────────────────────────────────┘");
                System.out.print("Votre choix : ");
                
                int choix = scanner.nextInt();
                scanner.nextLine();
                
                switch (choix) {
                    case 1:
                        ajouterMatiere();
                        break;
                    case 2:
                        modifierMatiere();
                        break;
                    case 3:
                        supprimerMatiere();
                        break;
                    case 4:
                        listerMatieres();
                        break;
                    case 5:
                        associerEnseignant();
                        break;
                    case 0:
                        retour = true;
                        break;
                    default:
                        System.out.println(" Choix invalide.");
                }
                
            } catch (InputMismatchException e) {
                System.out.println(" Erreur : Veuillez entrer un nombre valide.");
                scanner.nextLine();
            }
        }
    }
    
    /**
     * Ajoute une nouvelle matière
     */
    private void ajouterMatiere() {
        System.out.println("\n=== AJOUT D'UNE MATIÈRE ===");
        
        System.out.print("Code de la matière (ex: INFO101) : ");
        String code = scanner.nextLine().toUpperCase();
        
        System.out.print("Nom de la matière : ");
        String nom = scanner.nextLine();
        
        System.out.print("Coefficient : ");
        int coefficient = scanner.nextInt();
        scanner.nextLine();
        
        Matiere matiere = new Matiere(code, nom, coefficient);
        
        if (matiereService.ajouterMatiere(matiere)) {
            System.out.println(" Matière ajoutée avec succès !");
        } else {
            System.out.println(" Erreur : Cette matière existe déjà.");
        }
    }
    
    /**
     * Modifie une matière existante
     */
    private void modifierMatiere() {
        System.out.println("\n=== MODIFICATION D'UNE MATIÈRE ===");
        
        System.out.print("Code de la matière à modifier : ");
        String code = scanner.nextLine().toUpperCase();
        
        Matiere matiere = matiereService.rechercherMatiere(code);
        
        if (matiere == null) {
            System.out.println(" Matière non trouvée.");
            return;
        }
        
        System.out.println("Matière trouvée : " + matiere);
        System.out.println("\nNouvelles informations :");
        
        System.out.print("Nouveau nom [" + matiere.getNom() + "] : ");
        String nom = scanner.nextLine();
        if (nom.isEmpty()) nom = matiere.getNom();
        
        System.out.print("Nouveau coefficient [" + matiere.getCoefficient() + "] : ");
        String coeffStr = scanner.nextLine();
        int coefficient = coeffStr.isEmpty() ? matiere.getCoefficient() : Integer.parseInt(coeffStr);
        
        if (matiereService.modifierMatiere(code, nom, coefficient)) {
            System.out.println(" Matière modifiée avec succès !");
        } else {
            System.out.println(" Erreur lors de la modification.");
        }
    }
    
    /**
     * Supprime une matière
     */
    private void supprimerMatiere() {
        System.out.println("\n=== SUPPRESSION D'UNE MATIÈRE ===");
        
        System.out.print("Code de la matière à supprimer : ");
        String code = scanner.nextLine().toUpperCase();
        
        Matiere matiere = matiereService.rechercherMatiere(code);
        
        if (matiere == null) {
            System.out.println(" Matière non trouvée.");
            return;
        }
        
        System.out.println("Matière trouvée : " + matiere);
        System.out.print("Confirmer la suppression ? (O/N) : ");
        String confirmation = scanner.nextLine();
        
        if (confirmation.equalsIgnoreCase("O")) {
            if (matiereService.supprimerMatiere(code)) {
                System.out.println(" Matière supprimée avec succès !");
            } else {
                System.out.println(" Erreur lors de la suppression.");
            }
        } else {
            System.out.println("ℹ Suppression annulée.");
        }
    }
    
    /**
     * Liste toutes les matières
     */
    private void listerMatieres() {
        System.out.println("\n=== LISTE DES MATIÈRES ===");
        
        List<Matiere> matieres = matiereService.listerMatieres();
        
        if (matieres.isEmpty()) {
            System.out.println("ℹ Aucune matière enregistrée.");
            return;
        }
        
        System.out.println("Nombre total : " + matieres.size());
        System.out.println("─".repeat(80));
        
        for (Matiere matiere : matieres) {
            System.out.println(matiere);
        }
        
        System.out.println("─".repeat(80));
    }
    
    /**
     * Associe un enseignant à une matière
     */
    private void associerEnseignant() {
        System.out.println("\n=== ASSOCIATION ENSEIGNANT ===");
        
        System.out.print("Code de la matière : ");
        String code = scanner.nextLine().toUpperCase();
        
        Matiere matiere = matiereService.rechercherMatiere(code);
        
        if (matiere == null) {
            System.out.println(" Matière non trouvée.");
            return;
        }
        
        System.out.println("Matière : " + matiere);
        System.out.print("ID de l'enseignant : ");
        String enseignantId = scanner.nextLine();
        
        if (matiereService.associerEnseignant(code, enseignantId)) {
            System.out.println(" Enseignant associé avec succès !");
        } else {
            System.out.println(" Erreur lors de l'association.");
        }
    }
    
    // ==================== MENU NOTES ====================
    
    /**
     * Affiche le menu de gestion des notes
     */
    private void menuNotes() {
        boolean retour = false;
        
        while (!retour) {
            try {
                System.out.println("\n┌─────────── GESTION DES NOTES ────────────┐");
                System.out.println("│  1. Ajouter une note                     │");
                System.out.println("│  2. Modifier une note                    │");
                System.out.println("│  3. Supprimer une note                   │");
                System.out.println("│  4. Afficher notes d'un étudiant         │");
                System.out.println("│  0. Retour au menu principal             │");
                System.out.println("└──────────────────────────────────────────┘");
                System.out.print("Votre choix : ");
                
                int choix = scanner.nextInt();
                scanner.nextLine();
                
                switch (choix) {
                    case 1:
                        ajouterNote();
                        break;
                    case 2:
                        modifierNote();
                        break;
                    case 3:
                        supprimerNote();
                        break;
                    case 4:
                        afficherNotesEtudiant();
                        break;
                    case 0:
                        retour = true;
                        break;
                    default:
                        System.out.println(" Choix invalide.");
                }
                
            } catch (InputMismatchException e) {
                System.out.println(" Erreur : Veuillez entrer un nombre valide.");
                scanner.nextLine();
            }
        }
    }
    
    /**
     * Ajoute une nouvelle note
     */
    private void ajouterNote() {
        System.out.println("\n=== AJOUT D'UNE NOTE ===");
        
        System.out.print("ID de l'étudiant : ");
        String etudiantId = scanner.nextLine();
        
        Etudiant etudiant = etudiantService.rechercherEtudiant(etudiantId);
        if (etudiant == null) {
            System.out.println(" Étudiant non trouvé.");
            return;
        }
        
        System.out.print("Code de la matière : ");
        String matiereCode = scanner.nextLine().toUpperCase();
        
        Matiere matiere = matiereService.rechercherMatiere(matiereCode);
        if (matiere == null) {
            System.out.println(" Matière non trouvée.");
            return;
        }
        
        System.out.print("Note (0-20) : ");
        double valeur = scanner.nextDouble();
        scanner.nextLine();
        
        if (valeur < 0 || valeur > 20) {
            System.out.println(" La note doit être entre 0 et 20.");
            return;
        }
        
        System.out.print("Type d'évaluation (Exam/DS/TP) : ");
        String type = scanner.nextLine();
        
        Note note = new Note(etudiantId, matiereCode, valeur, "", type);
        
        if (noteService.ajouterNote(note)) {
            System.out.println(" Note ajoutée avec succès !");
        } else {
            System.out.println(" Erreur lors de l'ajout de la note.");
        }
    }
    
    /**
     * Modifie une note existante
     */
    private void modifierNote() {
        System.out.println("\n=== MODIFICATION D'UNE NOTE ===");
        
        System.out.print("ID de l'étudiant : ");
        String etudiantId = scanner.nextLine();
        
        System.out.print("Code de la matière : ");
        String matiereCode = scanner.nextLine().toUpperCase();
        
        System.out.print("Nouvelle note (0-20) : ");
        double valeur = scanner.nextDouble();
        scanner.nextLine();
        
        if (noteService.modifierNote(etudiantId, matiereCode, valeur)) {
            System.out.println(" Note modifiée avec succès !");
        } else {
            System.out.println(" Note non trouvée ou valeur invalide.");
        }
    }
    
    /**
     * Supprime une note
     */
    private void supprimerNote() {
        System.out.println("\n=== SUPPRESSION D'UNE NOTE ===");
        
        System.out.print("ID de l'étudiant : ");
        String etudiantId = scanner.nextLine();
        
        System.out.print("Code de la matière : ");
        String matiereCode = scanner.nextLine().toUpperCase();
        
        System.out.print("Confirmer la suppression ? (O/N) : ");
        String confirmation = scanner.nextLine();
        
        if (confirmation.equalsIgnoreCase("O")) {
            if (noteService.supprimerNote(etudiantId, matiereCode)) {
                System.out.println(" Note supprimée avec succès !");
            } else {
                System.out.println(" Note non trouvée.");
            }
        } else {
            System.out.println("ℹ Suppression annulée.");
        }
    }
    
    /**
     * Affiche toutes les notes d'un étudiant
     */
    private void afficherNotesEtudiant() {
        System.out.println("\n=== NOTES D'UN ÉTUDIANT ===");
        
        System.out.print("ID de l'étudiant : ");
        String etudiantId = scanner.nextLine();
        
        Etudiant etudiant = etudiantService.rechercherEtudiant(etudiantId);
        
        if (etudiant == null) {
            System.out.println(" Étudiant non trouvé.");
            return;
        }
        
        System.out.println("\nÉtudiant : " + etudiant.getNomComplet());
        
        List<Note> notes = noteService.rechercherNotesEtudiant(etudiantId);
        
        if (notes.isEmpty()) {
            System.out.println("ℹ Aucune note enregistrée pour cet étudiant.");
            return;
        }
        
        System.out.println("─".repeat(60));
        for (Note note : notes) {
            System.out.println(note);
        }
        System.out.println("─".repeat(60));
        System.out.printf("Moyenne générale : %.2f/20\n", etudiant.calculerMoyenne());
    }
    
    // ==================== MENU STATISTIQUES ====================
    
    /**
     * Affiche le menu des statistiques
     */
    private void menuStatistiques() {
        boolean retour = false;
        
        while (!retour) {
            try {
                System.out.println("\n┌────── STATISTIQUES & CLASSEMENTS ────────┐");
                System.out.println("│  1. Classement général des étudiants    │");
                System.out.println("│  2. Moyenne générale de la classe       │");
                System.out.println("│  3. Statistiques détaillées             │");
                System.out.println("│  0. Retour au menu principal            │");
                System.out.println("└─────────────────────────────────────────┘");
                System.out.print("Votre choix : ");
                
                int choix = scanner.nextInt();
                scanner.nextLine();
                
                switch (choix) {
                    case 1:
                        afficherClassement();
                        break;
                    case 2:
                        afficherMoyenneGenerale();
                        break;
                    case 3:
                        afficherStatistiques();
                        break;
                    case 0:
                        retour = true;
                        break;
                    default:
                        System.out.println(" Choix invalide.");
                }
                
            } catch (InputMismatchException e) {
                System.out.println(" Erreur : Veuillez entrer un nombre valide.");
                scanner.nextLine();
            }
        }
    }
    
    /**
     * Affiche le classement des étudiants
     */
    private void afficherClassement() {
        System.out.println("\n=== CLASSEMENT GÉNÉRAL DES ÉTUDIANTS ===");
        
        List<Etudiant> classement = etudiantService.obtenirClassement();
        
        if (classement.isEmpty()) {
            System.out.println("ℹ Aucun étudiant enregistré.");
            return;
        }
        
        System.out.println("─".repeat(80));
        System.out.printf("%-6s %-10s %-25s %-30s %-10s\n", 
                         "Rang", "ID", "Nom Complet", "Email", "Moyenne");
        System.out.println("─".repeat(80));
        
        int rang = 1;
        for (Etudiant etudiant : classement) {
            System.out.printf("%-6d %-10s %-25s %-30s %-10.2f\n",
                             rang++,
                             etudiant.getId(),
                             etudiant.getNomComplet(),
                             etudiant.getEmail(),
                             etudiant.calculerMoyenne());
        }
        
        System.out.println("─".repeat(80));
    }
    
    /**
     * Affiche la moyenne générale de la classe
     */
    private void afficherMoyenneGenerale() {
        System.out.println("\n=== MOYENNE GÉNÉRALE DE LA CLASSE ===");
        
        double moyenne = etudiantService.calculerMoyenneGenerale();
        int nbEtudiants = etudiantService.compterEtudiants();
        
        System.out.println("─".repeat(50));
        System.out.printf("Nombre d'étudiants : %d\n", nbEtudiants);
        System.out.printf("Moyenne générale : %.2f/20\n", moyenne);
        System.out.println("─".repeat(50));
        
        if (moyenne >= 14) {
            System.out.println(" Niveau : Excellent");
        } else if (moyenne >= 12) {
            System.out.println(" Niveau : Bien");
        } else if (moyenne >= 10) {
            System.out.println(" Niveau : Passable");
        } else {
            System.out.println(" Niveau : Insuffisant");
        }
    }
    
    /**
     * Affiche des statistiques détaillées
     */
    private void afficherStatistiques() {
        System.out.println("\n=== STATISTIQUES DÉTAILLÉES ===");
        
        int nbEtudiants = etudiantService.compterEtudiants();
        int nbMatieres = matiereService.compterMatieres();
        int nbNotes = noteService.compterNotes();
        double moyenneGenerale = etudiantService.calculerMoyenneGenerale();
        
        System.out.println("─".repeat(50));
        System.out.printf(" Nombre d'étudiants : %d\n", nbEtudiants);
        System.out.printf(" Nombre de matières : %d\n", nbMatieres);
        System.out.printf(" Nombre de notes : %d\n", nbNotes);
        System.out.printf(" Moyenne générale : %.2f/20\n", moyenneGenerale);
        System.out.println("─".repeat(50));
        
        // Meilleur étudiant
        if (nbEtudiants > 0) {
            List<Etudiant> classement = etudiantService.obtenirClassement();
            Etudiant meilleur = classement.get(0);
            System.out.println("\n Meilleur étudiant :");
            System.out.printf("   %s - Moyenne: %.2f/20\n", 
                             meilleur.getNomComplet(), meilleur.calculerMoyenne());
        }
    }
    
    // ==================== UTILITAIRES ====================
    
    /**
     * Sauvegarde toutes les données
     */
    private void sauvegarderDonnees() {
        System.out.println("\n Sauvegarde en cours...");
        GestionFichier.sauvegarderTout(etudiantService, matiereService, noteService);
        System.out.println(" Données sauvegardées avec succès !");
    }
    
    /**
     * Quitte l'application
     */
    private boolean quitterApplication() {
        System.out.print("\n⚠  Voulez-vous sauvegarder avant de quitter ? (O/N) : ");
        String reponse = scanner.nextLine();
        
        if (reponse.equalsIgnoreCase("O")) {
            sauvegarderDonnees();
        }
        
        System.out.println("\n Merci d'avoir utilisé le Système de Gestion Universitaire !");
        System.out.println("À bientôt !\n");
        
        scanner.close();
        return false;
    }
}