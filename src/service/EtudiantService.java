package service;

import model.Etudiant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service de gestion des étudiants
 * Contient toute la logique métier relative aux étudiants
 */
public class EtudiantService {
    
    private List<Etudiant> etudiants;
    
    /**
     * Constructeur initialisant la liste d'étudiants
     */
    public EtudiantService() {
        this.etudiants = new ArrayList<>();
    }
    
    /**
     * Ajoute un étudiant à la liste
     * @param etudiant l'étudiant à ajouter
     * @return true si l'ajout a réussi
     */
    public boolean ajouterEtudiant(Etudiant etudiant) {
        if (etudiant == null || rechercherEtudiant(etudiant.getId()) != null) {
            return false;
        }
        return etudiants.add(etudiant);
    }
    
    /**
     * Recherche un étudiant par son ID
     * @param id l'identifiant de l'étudiant
     * @return l'étudiant trouvé ou null
     */
    public Etudiant rechercherEtudiant(String id) {
        for (Etudiant etudiant : etudiants) {
            if (etudiant.getId().equals(id)) {
                return etudiant;
            }
        }
        return null;
    }
    
    /**
     * Modifie les informations d'un étudiant
     * @param id l'ID de l'étudiant à modifier
     * @param nom nouveau nom
     * @param prenom nouveau prénom
     * @param email nouvel email
     * @return true si la modification a réussi
     */
    public boolean modifierEtudiant(String id, String nom, String prenom, String email) {
        Etudiant etudiant = rechercherEtudiant(id);
        if (etudiant == null) {
            return false;
        }
        
        etudiant.setNom(nom);
        etudiant.setPrenom(prenom);
        etudiant.setEmail(email);
        return true;
    }
    
    /**
     * Supprime un étudiant de la liste
     * @param id l'ID de l'étudiant à supprimer
     *  true si la suppression a réussi
     */
    public boolean supprimerEtudiant(String id) {
        Etudiant etudiant = rechercherEtudiant(id);
        if (etudiant == null) {
            return false;
        }
        return etudiants.remove(etudiant);
    }
    
    /**
     * Retourne la liste de tous les étudiants
     * @return liste des étudiants
     */
    public List<Etudiant> listerEtudiants() {
        return new ArrayList<>(etudiants);
    }
    
    /**
     * Retourne le classement des étudiants par moyenne décroissante
     * @return liste triée des étudiants
     */
    public List<Etudiant> obtenirClassement() {
        return etudiants.stream()
                .sorted((e1, e2) -> Double.compare(e2.calculerMoyenne(), e1.calculerMoyenne()))
                .collect(Collectors.toList());
    }
    
    /**
     * Calcule la moyenne générale de tous les étudiants
     * @return la moyenne générale
     */
    public double calculerMoyenneGenerale() {
        if (etudiants.isEmpty()) {
            return 0.0;
        }
        
        double somme = 0;
        for (Etudiant etudiant : etudiants) {
            somme += etudiant.calculerMoyenne();
        }
        
        return somme / etudiants.size();
    }
    
    
    public String genererNouvelId() {
        int maxNum = 0;
        for (Etudiant etudiant : etudiants) {
            String id = etudiant.getId();
            if (id.startsWith("ETU")) {
                try {
                    int num = Integer.parseInt(id.substring(3));
                    if (num > maxNum) {
                        maxNum = num;
                    }
                } catch (NumberFormatException e) {
                    // Ignorer les IDs malformés
                }
            }
        }
        return String.format("ETU%04d", maxNum + 1);
    }
    
    /**
     * Compte le nombre total d'étudiants
     	nombre d'étudiants
     */
    public int compterEtudiants() {
        return etudiants.size();
    }
    
    /**
     * Vide la liste des étudiants
     */
    public void viderListe() {
        etudiants.clear();
    }
}