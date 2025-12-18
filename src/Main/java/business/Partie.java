package business;

import java.util.Random;
import java.util.Arrays; // Import nécessaire pour le toString des tableaux

/**
 * Représente une partie (une session) du jeu Puissance 4.
 * <p>
 * Cette classe agit comme un conteneur de données (Model). Elle stocke la grille,
 * les deux joueurs, le joueur dont c'est le tour, et l'état actuel de la partie
 * (finie, gagnée, abandonnée).
 * </p>
 */
public class Partie {

    private Grille grille;
    private Joueur[] joueurs;
    private Joueur joueurCourant;
    private boolean partieFinie;
    private boolean parAbandon;
    private Joueur gagnant;

    /**
     * Construit une nouvelle partie prête à démarrer.
     * <p>
     * - Initialise une grille vide.<br>
     * - Crée les deux joueurs (JAUNE et ROUGE).<br>
     * - Tire au sort le joueur qui commence.<br>
     * - Initialise les indicateurs de fin de partie à faux.
     * </p>
     */
    public Partie() {
        this.grille = new Grille();

        this.joueurs = new Joueur[2];
        this.joueurs[0] = new Joueur(Couleur.JAUNE);
        this.joueurs[1] = new Joueur(Couleur.ROUGE);

        // Tirage au sort du premier joueur
        Random rand = new Random();
        int index = rand.nextInt(2); // soit 0, soit 1
        this.joueurCourant = this.joueurs[index];

        this.partieFinie = false;
        this.parAbandon = false;
        this.gagnant = null;
    }

    /**
     * Récupère la grille de jeu associée à cette partie.
     *
     * @return l'objet Grille.
     */
    public Grille getGrille() {
        return grille;
    }

    /**
     * Récupère le tableau contenant les deux joueurs.
     *
     * @return un tableau de Joueur de taille 2.
     */
    public Joueur[] getJoueurs() {
        return joueurs;
    }

    /**
     * Récupère le joueur dont c'est actuellement le tour.
     *
     * @return le Joueur courant.
     */
    public Joueur getJoueurCourant() {
        return joueurCourant;
    }

    /**
     * Vérifie si la partie est terminée.
     *
     * @return true si la partie est finie (victoire, nul ou abandon), false sinon.
     */
    public boolean isPartieFinie() {
        return partieFinie;
    }

    /**
     * Récupère le joueur qui a gagné la partie.
     *
     * @return le Joueur gagnant, ou null si la partie n'est pas finie ou s'il y a match nul.
     */
    public Joueur getGagnant() {
        return gagnant;
    }

    /**
     * Vérifie si la partie s'est terminée par un abandon.
     *
     * @return true si un joueur a abandonné, false sinon.
     */
    public boolean isParAbandon() {
        return parAbandon;
    }

    /**
     * Définit le joueur dont c'est le tour.
     *
     * @param joueurCourant le joueur qui doit jouer.
     */
    public void setJoueurCourant(Joueur joueurCourant) {
        this.joueurCourant = joueurCourant;
    }

    /**
     * Met à jour l'état de fin de partie.
     *
     * @param partieFinie true pour indiquer que la partie est terminée.
     */
    public void setPartieFinie(boolean partieFinie) {
        this.partieFinie = partieFinie;
    }

    /**
     * Désigne le gagnant de la partie.
     *
     * @param gagnant le joueur qui a remporté la victoire.
     */
    public void setGagnant(Joueur gagnant) {
        this.gagnant = gagnant;
    }

    /**
     * Indique si la partie a été abandonnée.
     *
     * @param parAbandon true si la victoire est due à un abandon.
     */
    public void setParAbandon(boolean parAbandon) {
        this.parAbandon = parAbandon;
    }

    /**
     * Retourne une représentation textuelle de l'état actuel de la partie.
     *
     * @return une chaîne décrivant l'état de la partie (joueur courant, fini?, gagnant?).
     */
    @Override
    public String toString() {
        return "Partie{" +
                "joueurCourant=" + joueurCourant +
                ", partieFinie=" + partieFinie +
                ", gagnant=" + gagnant +
                ", parAbandon=" + parAbandon +
                '}';
    }
}