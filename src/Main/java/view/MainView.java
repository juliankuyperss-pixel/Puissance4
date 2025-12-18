package view;

import business.*;
import java.io.IOException;
import java.util.Scanner;

public class MainView {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Bienvenue au jeu Puissance4 !");

        // --- 1. PROPOSER DE CHARGER LA PARTIE ---
        Puissance4 jeu = demarrerJeu();

        while (!jeu.gameIsOver()) {
            display(jeu.getPartie());

            Joueur joueurCourant = jeu.getPartie().getJoueurCourant();
            System.out.println("Tour du joueur : " + joueurCourant.getNom());

            // On ajoute l'option -2 pour sauvegarder
            System.out.println("Entrez colonne (0-" + (Grille.NB_COLONNES - 1) + "), -1 (Abandon), -2 (Sauvegarder & Quitter) :");

            try {
                if (!scanner.hasNextInt()) {
                    System.out.println("Erreur : Veuillez entrer un nombre entier.");
                    scanner.next();
                    continue;
                }
                int choix = scanner.nextInt();

                if (choix == -1) {
                    jeu.abandonner();
                } else if (choix == -2) {
                    // --- 2. GESTION DE LA SAUVEGARDE ---
                    try {
                        SauvegardeManager.sauvegarder(jeu.getPartie());
                        System.out.println("Partie sauvegardée avec succès ! A bientôt.");
                        return; // On arrête le programme proprement
                    } catch (IOException e) {
                        System.out.println("ERREUR lors de la sauvegarde : " + e.getMessage());
                    }
                } else {
                    jeu.jouer(choix);
                }

            } catch (Puissance4Exception e) {
                System.out.println("COUP IMPOSSIBLE : " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("ERREUR : " + e.getMessage());
            }
        }

        // Fin de partie standard
        display(jeu.getPartie());
        // ... (ton code de fin de partie reste identique)
        if (jeu.getPartie().isParAbandon()) {
            System.out.println("La partie s'est terminée par un ABANDON.");
        }
        Joueur gagnant = jeu.getPartie().getGagnant();
        if (gagnant != null) {
            System.out.println("LE GAGNANT EST : " + gagnant.getNom() + " !!!");
        } else {
            System.out.println("MATCH NUL ! La grille est pleine.");
        }

        scanner.close();
    }

    // --- NOUVELLE MÉTHODE POUR GÉRER LE MENU DE DÉPART ---
    private static Puissance4 demarrerJeu() {
        System.out.println("1. Nouvelle Partie");
        System.out.println("2. Charger la dernière partie");
        System.out.print("Votre choix : ");

        int choix = 0;
        if (scanner.hasNextInt()) {
            choix = scanner.nextInt();
        } else {
            scanner.next(); // vider le buffer
        }

        if (choix == 2) {
            try {
                Partie partieSauvegardee = SauvegardeManager.charger();
                if (partieSauvegardee != null) {
                    System.out.println("Partie chargée !");
                    // On utilise le constructeur que tu m'as montré dans ton image !
                    return new Puissance4(partieSauvegardee);
                } else {
                    System.out.println("Aucune sauvegarde trouvée. Création d'une nouvelle partie.");
                }
            } catch (Exception e) {
                System.out.println("Erreur au chargement (fichier corrompu ?). Nouvelle partie lancée.");
            }
        }
        return new Puissance4(); // Nouvelle partie par défaut
    }

    public static void display(Partie partie) {
        // ... (Ta méthode display reste identique, ne change rien ici)
        Grille grille = partie.getGrille();
        Jeton[][] plateau = grille.getJetons();

        System.out.println("\n 0 1 2 3 4 5 6");
        System.out.println("---------------");


        for (int i = 0; i < Grille.NB_LIGNES; i++) {
            System.out.print("|");
            for (int j = 0; j < Grille.NB_COLONNES; j++) {
                Jeton jeton = plateau[i][j];
                if (jeton == null) {
                    System.out.print(".");
                } else {

                    if (jeton.getCouleur() == Couleur.ROUGE) {
                        System.out.print("R");
                    } else {
                        System.out.print("J");
                    }
                }
                System.out.print("|");
            }
            System.out.println();
        }
        System.out.println("---------------\n");
    }
}