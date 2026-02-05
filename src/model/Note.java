package model;


 
public class Note {
    
    private String etudiantId;
    private String matiereCode;
    private double valeur;
    private String dateEvaluation;
    private String typeEvaluation; // "DS", "Exam", "TP", etc.
    
    /**
     * Constructeur complet
     */
    public Note(String etudiantId, String matiereCode, double valeur, 
                String dateEvaluation, String typeEvaluation) {
        this.etudiantId = etudiantId;
        this.matiereCode = matiereCode;
        this.valeur = valeur;
        this.dateEvaluation = dateEvaluation;
        this.typeEvaluation = typeEvaluation;
    }
    
    /**
     * Constructeur simplifié
     */
    public Note(String etudiantId, String matiereCode, double valeur) {
        this(etudiantId, matiereCode, valeur, "", "Exam");
    }
    
    // Getters et Setters
    public String getEtudiantId() {
        return etudiantId;
    }
    
    public void setEtudiantId(String etudiantId) {
        this.etudiantId = etudiantId;
    }
    
    public String getMatiereCode() {
        return matiereCode;
    }
    
    public void setMatiereCode(String matiereCode) {
        this.matiereCode = matiereCode;
    }
    
    public double getValeur() {
        return valeur;
    }
    
    public void setValeur(double valeur) {
        this.valeur = valeur;
    }
    
    public String getDateEvaluation() {
        return dateEvaluation;
    }
    
    public void setDateEvaluation(String dateEvaluation) {
        this.dateEvaluation = dateEvaluation;
    }
    
    public String getTypeEvaluation() {
        return typeEvaluation;
    }
    
    public void setTypeEvaluation(String typeEvaluation) {
        this.typeEvaluation = typeEvaluation;
    }
    
    /**
     * Format pour la sauvegarde dans fichier
     */
    public String toFileFormat() {
        return etudiantId + ";" + matiereCode + ";" + valeur + ";" + 
               dateEvaluation + ";" + typeEvaluation;
    }
    
    @Override
    public String toString() {
        return String.format("Matière: %s | Note: %.2f/20 | Type: %s", 
                           matiereCode, valeur, typeEvaluation);
    }
}