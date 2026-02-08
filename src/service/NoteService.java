package service;

import model.Note;
import model.Etudiant;
import java.util.ArrayList;
import java.util.List;

/**
 * Service de gestion des notes
 * Contient toute la logique métier relative aux notes.
 */
public class NoteService {
    
    private List<Note> notes;
    private EtudiantService etudiantService;
    
    /**
     * Constructeur avec injection du service étudiant
     */
    public NoteService(EtudiantService etudiantService) {
        this.notes = new ArrayList<>();
        this.etudiantService = etudiantService;
    }
    
    /**
     * Ajoute une note et l'associe à l'étudiant concerné
     * @param note la note à ajouter
     * @return true si l'ajout a réussi
     */
    public boolean ajouterNote(Note note) {
        if (note == null || note.getValeur() < 0 || note.getValeur() > 20) {
            return false;
        }
        
        // Ajouter la note à la liste globale
        if (notes.add(note)) {
            // Associer la note à l'étudiant
            Etudiant etudiant = etudiantService.rechercherEtudiant(note.getEtudiantId());
            if (etudiant != null) {
                etudiant.ajouterNote(note);
            }
            return true;
        }
        return false;
    }
    
    /**
     * Recherche toutes les notes d'un étudiant
     * @param etudiantId l'ID de l'étudiant
     * @return liste des notes de l'étudiant
     */
    public List<Note> rechercherNotesEtudiant(String etudiantId) {
        List<Note> resultat = new ArrayList<>();
        for (Note note : notes) {
            if (note.getEtudiantId().equals(etudiantId)) {
                resultat.add(note);
            }
        }
        return resultat;
    }
    
    /**
     * Recherche toutes les notes d'une matière
     * @param matiereCode le code de la matière
     * @return liste des notes de la matière
     */
    public List<Note> rechercherNotesMatiere(String matiereCode) {
        List<Note> resultat = new ArrayList<>();
        for (Note note : notes) {
            if (note.getMatiereCode().equals(matiereCode)) {
                resultat.add(note);
            }
        }
        return resultat;
    }
    
    /**
     * Modifie la valeur d'une note
     * @param etudiantId l'ID de l'étudiant
     * @param matiereCode le code de la matière
     * @param nouvelleValeur la nouvelle valeur de la note
     * @return true si la modification a réussi
     */
    public boolean modifierNote(String etudiantId, String matiereCode, double nouvelleValeur) {
        if (nouvelleValeur < 0 || nouvelleValeur > 20) {
            return false;
        }
        
        for (Note note : notes) {
            if (note.getEtudiantId().equals(etudiantId) && 
                note.getMatiereCode().equals(matiereCode)) {
                note.setValeur(nouvelleValeur);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Supprime une note
     * @param etudiantId l'ID de l'étudiant
     * @param matiereCode le code de la matière
     * @return true si la suppression a réussi
     */
    public boolean supprimerNote(String etudiantId, String matiereCode) {
        Note noteASupprimer = null;
        
        for (Note note : notes) {
            if (note.getEtudiantId().equals(etudiantId) && 
                note.getMatiereCode().equals(matiereCode)) {
                noteASupprimer = note;
                break;
            }
        }
        
        if (noteASupprimer != null) {
            notes.remove(noteASupprimer);
            
            // Retirer aussi de l'étudiant
            Etudiant etudiant = etudiantService.rechercherEtudiant(etudiantId);
            if (etudiant != null) {
                etudiant.getNotes().remove(noteASupprimer);
            }
            return true;
        }
        return false;
    }
    
    /**
     * Retourne toutes les notes du système
     * @return liste de toutes les notes
     */
    public List<Note> listerToutesLesNotes() {
        return new ArrayList<>(notes);
    }
    
    /**
     * Calcule la moyenne d'un étudiant pour une matière
     * @param etudiantId l'ID de l'étudiant
     * @param matiereCode le code de la matière
     * @return la moyenne ou 0 si aucune note
     */
    public double calculerMoyenneMatiere(String etudiantId, String matiereCode) {
        List<Note> notesMatiere = new ArrayList<>();
        
        for (Note note : notes) {
            if (note.getEtudiantId().equals(etudiantId) && 
                note.getMatiereCode().equals(matiereCode)) {
                notesMatiere.add(note);
            }
        }
        
        if (notesMatiere.isEmpty()) {
            return 0.0;
        }
        
        double somme = 0;
        for (Note note : notesMatiere) {
            somme += note.getValeur();
        }
        
        return somme / notesMatiere.size();
    }
    
    /**
     * Compte le nombre total de notes
     * @return nombre de notes
     */
    public int compterNotes() {
        return notes.size();
    }
    
    /**
     * Vide la liste des notes
     */
    public void viderListe() {
        notes.clear();
    }
}