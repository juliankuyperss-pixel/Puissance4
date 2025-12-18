package view;

import business.*;
import java.util.Scanner;

/**
 * Vue principale du jeu Puissance 4 (Interface Console).
 * <p>
 * Cette classe contient le point d'entrée du programme (main) et gère
 * les interactions avec l'utilisateur (affichage de la grille, lecture des coups, affichage du gagnant).
 * </p>
 */
public class MainView {

    private static Scanner scanner = new Scanner(System.in);

    /**
     * Point d'entrée principal de l'application.
     * <p>
     * Cette méthode initialise une nouvelle partie, lance la boucle de jeu (tour par tour),
     * gère les saisies utilisateur (choix de colonne ou abandon) et attrape les erreurs.
     * Enfin, elle affiche le résultat final (Gagnant, Match Nul ou Abandon).
     * </p>
     *
     * @param args les arguments de la ligne de commande (non utilisés ici).
     */
    public static void main(String[] args) {
        Puissance4 jeu = new Puissance4();

        System.out.println("Bienvenue au jeu Puissance4 !");

        while (!jeu.gameIsOver()) {
            display(jeu.getPartie());

            Joueur jouerCourant = jeu.getPartie().getJoueurCourant();
            System.out.println("Tour du joueur :" + jouerCourant.getNom());

            System.out.println("Entrez le numéro de colonne (0 à " + (Grille.NB_COLONNES - 1) + ") ou -1 pour abandonner :");

            try {
                if (!scanner.hasNextInt()) {
                    System.out.println("Erreur : Veuillez entrer un nombre entier.");
                    scanner.next(); // Vide le tampon du scanner
                    continue;
                }
                int choix = scanner.nextInt();

                if (choix == -1) {
                    jeu.abandonner();
                } else {
                    jeu.jouer(choix);
                }

            } catch (Puissance4Exception e) {
                System.out.println("COUP IMPOSSIBLE : " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("ERREUR : " + e.getMessage());
            }
        }

        // Affichage final
        display(jeu.getPartie());

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

    /**
     * Affiche l'état visuel de la grille dans la console.
     * <p>
     * Cette méthode parcourt le plateau de jeu et affiche les jetons sous forme de caractères :
     * <ul>
     * <li>'R' pour un jeton ROUGE</li>
     * <li>'J' pour un jeton JAUNE</li>
     * <li>'.' pour une case vide (null)</li>
     * </ul>
     * Elle affiche également les indices de colonnes pour guider le joueur.
     * </p>
     *
     * @param partie l'objet Partie contenant la grille à afficher.
     */
    public static void display(Partie partie) {
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