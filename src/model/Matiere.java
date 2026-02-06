package model;


 
public class Matiere {
    
    private String code;
    private String nom;
    private int coefficient;
    private String enseignantId;
    
    /**
     * Constructeur complet
     */
    public Matiere(String code, String nom, int coefficient, String enseignantId) {
        this.code = code;
        this.nom = nom;
        this.coefficient = coefficient;
        this.enseignantId = enseignantId;
    }
    
    /**
     * Constructeur sans enseignant
     */
    public Matiere(String code, String nom, int coefficient) {
        this(code, nom, coefficient, null);
    }
    
    // Getters et Setters
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public int getCoefficient() {
        return coefficient;
    }
    
    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }
    
    public String getEnseignantId() {
        return enseignantId;
    }
    
    public void setEnseignantId(String enseignantId) {
        this.enseignantId = enseignantId;
    }
    
    /**
     * Format pour la sauvegarde dans fichier
     */
    public String toFileFormat() {
        String enseignant = (enseignantId != null) ? enseignantId : "NONE";
        return code + ";" + nom + ";" + coefficient + ";" + enseignant;
    }
    
    @Override
    public String toString() {
        return String.format("Code: %s | Matière: %s | Coefficient: %d | Enseignant ID: %s", 
                           code, nom, coefficient, 
                           (enseignantId != null ? enseignantId : "Non assigné"));
    }
}