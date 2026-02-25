package main;

import service.EtudiantService;
import service.MatiereService;
import service.NoteService;
import util.GestionFichier;
import util.Menu;

/**
 * Classe principale du Système de Gestion Universitaire.
 * Point d'entrée de l'application.
 
 */
public class MainApp {
    
    public static void main(String[] args) {
        
        // Initialisation des services
        EtudiantService etudiantService = new EtudiantService();
        MatiereService matiereService = new MatiereService();
        NoteService noteService = new NoteService(etudiantService);
        
        // Chargement des données depuis les fichiers
        GestionFichier.chargerTout(etudiantService, matiereService, noteService);
        
        // Initialiser des données de test si c'est la première utilisation
        GestionFichier.initialiserDonneesTest(etudiantService, matiereService, noteService);
        
        // Création et lancement du menu
        Menu menu = new Menu(etudiantService, matiereService, noteService);
        
        // Affichage de l'en-tête
        menu.afficherEntete();
        
        // Message de bienvenue
        System.out.println("\n Bienvenue dans le Système de Gestion Universitaire");
        System.out.println("─".repeat(55));
        System.out.println("Étudiants chargés  : " + etudiantService.compterEtudiants());
        System.out.println("Matières chargées  : " + matiereService.compterMatieres());
        System.out.println("Notes chargées   : " + noteService.compterNotes());
        System.out.println("─".repeat(55));
        
        // Lancement du menu principal.
        menu.afficherMenuPrincipal();
    }
}