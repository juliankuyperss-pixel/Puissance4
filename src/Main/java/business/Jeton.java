package business;

/**
 * Représente le jeton du jeu Puissance4.
 * Le jeton posséde une couleur.
 */
public class Jeton {
    private Couleur couleur;

    /**
     * la couleur du jeton
     * @param Couleur la couleur du jeton a créer.
     */
    public Jeton(Couleur Couleur) {
        this.Couleur = Couleur;
    }

    public Couleur getCouleur() {
        return couleur;
    }
}
