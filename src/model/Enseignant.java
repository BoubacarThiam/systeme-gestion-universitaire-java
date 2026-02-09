package model;


public class Enseignant {
    
    private String id;
    private String nom;
    private String prenom;
    private String email;
    private String specialite;
    
    /**
     * Constructeur complet
     */
    public Enseignant (String id, String nom, String prenom, String email, String specialite) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.specialite = specialite;
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
    
    public String getSpecialite() {
        return specialite;
    }
    
    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }
    
    /**
     * Retourne le nom complet de l'enseignant
     */
    public String getNomComplet() {
        return prenom + " " + nom;
    }
    
    /**
     * Format pour la sauvegarde dans fichier
     */
    public String toFileFormat() {
        return id + ";" + nom + ";" + prenom + ";" + email + ";" + specialite;
    }
    
    @Override
    public String toString() {
        return String.format("ID: %s | Nom: %s | Spécialité: %s | Email: %s", 
                           id, getNomComplet(), specialite, email);
    }
}