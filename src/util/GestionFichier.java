package util;

import model.Etudiant;
import model.Enseignant;
import model.Matiere;
import model.Note;
import service.EtudiantService;
import service.MatiereService;
import service.NoteService;

import java.io.*;
import java.util.List;

/**
 * Classe utilitaire pour la gestion de la persistance des données
 * Sauvegarde et chargement depuis des fichiers texte.
 */
public class GestionFichier {
    
    // Chemins des fichiers de données
    private static final String DOSSIER_DATA = "data/";
    private static final String FICHIER_ETUDIANTS = DOSSIER_DATA + "etudiants.txt";
    private static final String FICHIER_MATIERES = DOSSIER_DATA + "matieres.txt";
    private static final String FICHIER_NOTES = DOSSIER_DATA + "notes.txt";
    private static final String FICHIER_ENSEIGNANTS = DOSSIER_DATA + "enseignants.txt";
    
    /**
     * Crée le dossier data s'il n'existe pas.
     */
    public static void initialiserDossierData() {
        File dossier = new File(DOSSIER_DATA);
        if (!dossier.exists()) {
            dossier.mkdirs();
            System.out.println("✓ Dossier 'data' créé avec succès.");
        }
    }
    
    // ==================== SAUVEGARDE ====================
    
    /**
     * Sauvegarde tous les étudiants dans le fichier
     * @param etudiantService le service contenant les étudiants
     */
    public static void sauvegarderEtudiants(EtudiantService etudiantService) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FICHIER_ETUDIANTS))) {
            List <Etudiant> etudiants = etudiantService.listerEtudiants();
            
            for (Etudiant etudiant : etudiants) {
                writer.write(etudiant.toFileFormat());
                writer.newLine();
            }
            
            System.out.println("✓ " + etudiants.size() + " étudiant(s) sauvegardé(s).");
            
        } catch (IOException e) {
            System.err.println("✗ Erreur lors de la sauvegarde des étudiants : " + e.getMessage());
        }
    }
    
    /**
     * Sauvegarde toutes les matières dans le fichier
     * @param matiereService le service contenant les matières.
     */
    public static void sauvegarderMatieres(MatiereService matiereService) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FICHIER_MATIERES))) {
            List <Matiere> matieres = matiereService.listerMatieres();
            
            for (Matiere matiere : matieres) {
                writer.write(matiere.toFileFormat());
                writer.newLine();
            }
            
            System.out.println("✓ " + matieres.size() + " matière(s) sauvegardée(s).");
            
        } catch (IOException e) {
            System.err.println("✗ Erreur lors de la sauvegarde des matières : " + e.getMessage());
        }
    }
    
    /**
     * Sauvegarde toutes les notes dans le fichier
     * @param noteService le service contenant les notes.
     */
    public static void sauvegarderNotes(NoteService noteService) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FICHIER_NOTES))) {
            List <Note> notes = noteService.listerToutesLesNotes();
            
            for (Note note : notes) {
                writer.write(note.toFileFormat());
                writer.newLine();
            }
            
            System.out.println("✓ " + notes.size() + " note(s) sauvegardée(s).");
            
        } catch (IOException e) {
            System.err.println("✗ Erreur lors de la sauvegarde des notes : " + e.getMessage());
        }
    }
    
    /**
     * Sauvegarde toutes les données du système
     */
    public static void sauvegarderTout(EtudiantService etudiantService, 
                                       MatiereService matiereService, 
                                       NoteService noteService) {
        System.out.println("\n=== SAUVEGARDE DES DONNÉES ===");
        initialiserDossierData();
        sauvegarderEtudiants(etudiantService);
        sauvegarderMatieres(matiereService);
        sauvegarderNotes(noteService);
        System.out.println("==============================\n");
    }
    
    // ==================== CHARGEMENT ====================
    
    /**
     * Charge tous les étudiants depuis le fichier
     * @param etudiantService le service où charger les étudiants
     */
    public static void chargerEtudiants(EtudiantService etudiantService) {
        File fichier = new File(FICHIER_ETUDIANTS);
        
        if (!fichier.exists()) {
            System.out.println("ℹ Aucun fichier d'étudiants trouvé.");
            return;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(fichier))) {
            String ligne;
            int compteur = 0;
            
            while ((ligne = reader.readLine()) != null) 
            {
                if (ligne.trim().isEmpty()) continue;
                
                String[] donnees = ligne.split(";");
                if (donnees.length >= 4) {
                    String id = donnees[0];
                    String nom = donnees[1];
                    String prenom = donnees[2];
                    String email = donnees[3];
                    String dateNaissance = (donnees.length > 4) ? donnees[4] : "";
                    
                    Etudiant etudiant = new Etudiant(id, nom, prenom, email, dateNaissance);
                    if (etudiantService.ajouterEtudiant(etudiant)) {
                        compteur++;
                    }
                }
            }
            
            System.out.println("✓ " + compteur + " étudiant(s) chargé(s).");
            
        } catch (IOException e) {
            System.err.println("✗ Erreur lors du chargement des étudiants  : " + e.getMessage());
        }
    }
    
    /**
     * Charge toutes les matières depuis le fichier
     * @param matiereService le service où charger les matières
     */
    public static void chargerMatieres(MatiereService matiereService) {
        File fichier = new File (FICHIER_MATIERES);
        
        if (!fichier.exists()) {
            System.out.println("ℹ Aucun fichier de matières trouvé");
            return;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(fichier))) {
            String ligne;
            int compteur = 0;
            
            while ((ligne = reader.readLine()) != null) {
                if (ligne.trim().isEmpty()) continue;
                
                String[] donnees = ligne.split(";");
                if (donnees.length >= 3) {
                    String code = donnees[0];
                    String nom = donnees[1];
                    int coefficient = Integer.parseInt(donnees[2]);
                    String enseignantId = (donnees.length > 3 && !donnees[3].equals("NONE")) 
                                          ? donnees[3] : null;
                    
                    Matiere matiere = new Matiere(code, nom, coefficient, enseignantId);
                    if (matiereService.ajouterMatiere(matiere)) {
                        compteur++;
                    }
                }
            }
            
            System.out.println("✓ " + compteur + " matière(s) chargée(s).");
            
        } catch (IOException e) {
            System.err.println("✗ Erreur lors du chargement des matières : " + e.getMessage());
        }
    }
    
    /**
     * Charge toutes les notes depuis le fichier
     * @param noteService le service où charger les notes
     */
    public static void chargerNotes(NoteService noteService) {
        File fichier = new File(FICHIER_NOTES);
        
        if (!fichier.exists()) {
            System.out.println("ℹ Aucun fichier de notes trouvé.");
            return;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(fichier))) {
            String ligne;
            int compteur = 0;
            
            while ((ligne = reader.readLine()) != null) {
                if (ligne.trim().isEmpty()) continue;
                
                String[] donnees = ligne.split(";");
                if (donnees.length >= 3) {
                    String etudiantId = donnees[0];
                    String matiereCode = donnees[1];
                    double valeur = Double.parseDouble(donnees[2]);
                    String dateEval = (donnees.length > 3) ? donnees[3] : "";
                    String typeEval = (donnees.length > 4) ? donnees[4] : "Exam";
                    
                    Note note = new Note(etudiantId, matiereCode, valeur, dateEval, typeEval);
                    if (noteService.ajouterNote(note)) {
                        compteur++;
                    }
                }
            }
            
            System.out.println("✓ " + compteur + " note(s) chargée(s).");
            
        } catch (IOException e) {
            System.err.println("✗ Erreur lors du chargement des notes : " + e.getMessage());
        }
    }
    
    /**
     * Charge toutes les données du système
     */
    public static void chargerTout(EtudiantService etudiantService, 
                                   MatiereService matiereService, 
                                   NoteService noteService) {
        System.out.println("\n=== CHARGEMENT DES DONNÉES ===");
        initialiserDossierData();
        chargerEtudiants(etudiantService);
        chargerMatieres(matiereService);
        chargerNotes(noteService);
        System.out.println("==============================\n");
    }
    
    /**
     * Initialise le système avec des données de test (première utilisation)
     */
    public static void initialiserDonneesTest(EtudiantService etudiantService, 
                                             MatiereService matiereService, 
                                             NoteService noteService) {
        
        // Vérifier si des données existent déjà
        if (etudiantService.compterEtudiants() > 0) {
            return;
        }
        
        System.out.println("\n=== INITIALISATION DES DONNÉES DE TEST ===");
        
        // Ajouter quelques étudiants
        etudiantService.ajouterEtudiant(new Etudiant("ETU0001", "Diop", "Amadou", "amadou.diop@univ.sn", "15/03/2002"));
        etudiantService.ajouterEtudiant(new Etudiant("ETU0002", "Ndiaye", "Fatou", "fatou.ndiaye@univ.sn", "22/07/2003"));
        etudiantService.ajouterEtudiant(new Etudiant("ETU0003", "Seck", "Moussa", "moussa.seck@univ.sn", "10/11/2002"));
        
        // Ajouter quelques matières
        matiereService.ajouterMatiere(new Matiere("INFO101", "Programmation Java", 3));
        matiereService.ajouterMatiere(new Matiere("MATH101", "Mathématiques Discrètes", 4));
        matiereService.ajouterMatiere(new Matiere("WEB101", "Développement Web", 2));
        
        // Ajouter quelques notes
        noteService.ajouterNote(new Note("ETU0001", "INFO101", 15.5));
        noteService.ajouterNote(new Note("ETU0001", "MATH101", 14.0));
        noteService.ajouterNote(new Note("ETU0002", "INFO101", 17.0));
        noteService.ajouterNote(new Note("ETU0002", "MATH101", 16.5));
        noteService.ajouterNote(new Note("ETU0003", "INFO101", 12.0));
        
        System.out.println("✓ Données de test initialisées avec succès.");
        System.out.println("=========================================\n");
    }
}