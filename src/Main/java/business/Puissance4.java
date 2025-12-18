package business;

/**
 * Classe principale de contrôle du jeu Puissance 4 (Contrôleur).
 * <p>
 * Cette classe contient la logique métier du jeu : elle gère le déroulement des tours,
 * l'insertion des jetons, la détection de victoire, l'abandon et l'alternance des joueurs.
 * Elle manipule l'objet {@link Partie} qui contient les données.
 * </p>
 */
public class Puissance4 {

    private Partie puissance4;

    /**
     * Construit un contrôleur de jeu à partir d'une partie existante.
     * <p>
     * Ce constructeur est utile pour les tests ou pour reprendre une partie.
     * </p>
     *
     * @param puissance4 l'objet Partie à contrôler.
     */
    public Puissance4(Partie puissance4) {
        this.puissance4 = puissance4;
    }

    /**
     * Construit un nouveau jeu Puissance 4 (nouvelle partie standard).
     * <p>
     * Initialise une nouvelle partie vide prête à être jouée.
     * </p>
     */
    public Puissance4() {
        this.puissance4 = new Partie();
    }

    /**
     * Vérifie si la partie est terminée.
     *
     * @return true si la partie est finie (victoire, nul ou abandon), false sinon.
     */
    public boolean gameIsOver() {
        return this.puissance4.isPartieFinie();
    }

    /**
     * Joue un jeton dans la colonne spécifiée pour le joueur courant.
     * <p>
     * Cette méthode effectue plusieurs actions :
     * <ul>
     * <li>Vérifie si la partie est déjà finie (si oui, ne fait rien).</li>
     * <li>Insère le jeton dans la grille.</li>
     * <li>Vérifie s'il y a victoire (alignement de 4).</li>
     * <li>Vérifie s'il y a match nul (grille pleine).</li>
     * <li>Passe la main au joueur suivant si la partie continue.</li>
     * </ul>
     * </p>
     *
     * @param numColonne l'indice de la colonne où jouer (0 à 6).
     * @throws Puissance4Exception si la colonne est pleine ou invalide.
     */
    public void jouer(int numColonne) throws Puissance4Exception {
        if (this.gameIsOver()) {
            return;
        }
        Joueur joueurActuel = this.puissance4.getJoueurCourant();
        Jeton jeton = new Jeton(joueurActuel.getNom());

        int ligne = this.puissance4.getGrille().insererJeton(jeton, numColonne);

        Position pos = new Position(ligne, numColonne);
        if (this.puissance4.getGrille().alignementRealise(pos)) {
            this.puissance4.setPartieFinie(true);
            this.puissance4.setGagnant(joueurActuel);
        } else if (this.puissance4.getGrille().isFullGrille()) {
            this.puissance4.setPartieFinie(true);
            this.puissance4.setGagnant(null);
        } else {
            changerJoueur();
        }
    }

    /**
     * Permet au joueur courant d'abandonner la partie.
     * <p>
     * La partie est déclarée finie, marquée comme abandonnée,
     * et la victoire est attribuée à l'adversaire.
     * </p>
     */
    public void abandonner() {
        this.puissance4.setPartieFinie(true);
        this.puissance4.setParAbandon(true);

        changerJoueur();
        this.puissance4.setGagnant(this.puissance4.getJoueurCourant());
    }

    private void changerJoueur() {
        Joueur[] lesJoueurs = this.puissance4.getJoueurs();
        Joueur courant = this.puissance4.getJoueurCourant();

        if (courant == lesJoueurs[0]) {
            this.puissance4.setJoueurCourant(lesJoueurs[1]);
        } else {
            this.puissance4.setJoueurCourant(lesJoueurs[0]);
        }
    }

    /**
     * Récupère l'objet Partie géré par ce contrôleur.
     *
     * @return l'objet Partie contenant les données du jeu.
     */
    public Partie getPartie() {
        return this.puissance4;
    }

    /**
     * Retourne une représentation textuelle du contrôleur.
     *
     * @return une chaîne décrivant l'état interne (la partie).
     */
    @Override
    public String toString() {
        return "Puissance4{partie=" + puissance4 + "}";
    }
}