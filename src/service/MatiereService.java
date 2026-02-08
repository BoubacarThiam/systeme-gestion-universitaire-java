package service;

import model.Matiere;
import java.util.ArrayList;
import java.util.List;

/**
 * Service de gestion des matières
 * Contient toute la logique métier relative aux matières
 */
public class MatiereService {
    
    private List<Matiere> matieres;
    
    /**
     * Constructeur initialisant la liste de matières
     */
    public MatiereService() {
        this.matieres = new ArrayList<>();
    }
    
    /**
     * Ajoute une matière à la liste
     * matiere la matière à ajouter
     * return true si l'ajout a réussi
     */
    public boolean ajouterMatiere(Matiere matiere) {
        if (matiere == null || rechercherMatiere(matiere.getCode()) != null) {
            return false;
        }
        return matieres.add(matiere);
    }
    
    /**
     * Recherche une matière par son code
     * @param code le code de la matière
     * @return la matière trouvée ou null
     */
    public Matiere rechercherMatiere(String code) {
        for (Matiere matiere : matieres) {
            if (matiere.getCode().equals(code)) {
                return matiere;
            }
        }
        return null;
    }
    
    /**
     * Associe un enseignant à une matière
     * @param codeMatiere le code de la matière
     * @param enseignantId l'ID de l'enseignant
     * @return true si l'association a réussi
     */
    public boolean associerEnseignant(String codeMatiere, String enseignantId) {
        Matiere matiere = rechercherMatiere(codeMatiere);
        if (matiere == null) {
            return false;
        }
        
        matiere.setEnseignantId(enseignantId);
        return true;
    }
    
    /**
     * Modifie une matière existante
     * @param code le code de la matière
     * @param nom nouveau nom
     * @param coefficient nouveau coefficient
     * @return true si la modification a réussi
     */
    public boolean modifierMatiere(String code, String nom, int coefficient) {
        Matiere matiere = rechercherMatiere(code);
        if (matiere == null) {
            return false;
        }
        
        matiere.setNom(nom);
        matiere.setCoefficient(coefficient);
        return true;
    }
    
    /**
     * Supprime une matière de la liste
     * @param code le code de la matière à supprimer
     * @return true si la suppression a réussi
     */
    public boolean supprimerMatiere(String code) {
        Matiere matiere = rechercherMatiere(code);
        if (matiere == null) {
            return false;
        }
        return matieres.remove(matiere);
    }
    
    /**
     * Retourne la liste de toutes les matières
     * @return liste des matières
     */
    public List<Matiere> listerMatieres() {
        return new ArrayList<>(matieres);
    }
    
    /**
     * Retourne les matières d'un enseignant spécifique
     * @param enseignantId l'ID de l'enseignant
     * @return liste des matières de l'enseignant
     */
    public List<Matiere> listerMatieresParEnseignant(String enseignantId) {
        List<Matiere> resultat = new ArrayList<>();
        for (Matiere matiere : matieres) {
            if (enseignantId.equals(matiere.getEnseignantId())) {
                resultat.add(matiere);
            }
        }
        return resultat;
    }
    
    /**
     * Compte le nombre total de matières
     * @return nombre de matières
     */
    public int compterMatieres() {
        return matieres.size();
    }
    
    /**
     * Vide la liste des matières
     */
    public void viderListe() {
        matieres.clear();
    }
}