package model;

import java.util.ArrayList;
import java.util.List;
import java.util.List;

public class Etudiant {
    
    // Attributs privés
    private String id;
    private String nom;
    private String prenom;
    private String email;
    private String dateNaissance;
    private List<Note> notes;
    
    /**
     * Constructeur complet
     */
    public Etudiant(String id, String nom, String prenom, String email, String dateNaissance) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.notes = new ArrayList<>();
    }
    
    /**
     * Constructeur pour la lecture depuis fichier
     */
    public Etudiant(String id, String nom, String prenom, String email) {
        this(id, nom, prenom, email, "");
    }
    
    // Getters et Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getPrenom() {
        return prenom;
    }
    
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getDateNaissance() {
        return dateNaissance;
    }
    
    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    
    public List<Note> getNotes() {
        return notes;
    }
    
    public void ajouterNote(Note note) {
        this.notes.add(note);
    }
    
    /**
     * Calcule la moyenne générale de l'étudiant
     * @return la moyenne ou 0 si aucune note
     */
    public double calculerMoyenne() {
        if (notes.isEmpty()) {
            return 0.0;
        }
        
        double somme = 0;
        for (Note note : notes) {
            somme += note.getValeur();
        }
        
        return somme / notes.size();
    }
    
    /**
     * Retourne le nom complet de l'étudiant
     */
    public String getNomComplet() {
        return prenom + " " + nom;
    }
    
    /**
     * Format pour la sauvegarde dans fichier
     */
    public String toFileFormat() {
        return id + ";" + nom + ";" + prenom + ";" + email + ";" + dateNaissance;
    }
    
    @Override
    public String toString() {
        return String.format("ID: %s | Nom: %s | Email: %s | Moyenne: %.2f", 
                           id, getNomComplet(), email, calculerMoyenne());
    }
}