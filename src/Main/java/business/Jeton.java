package business;
import java.io.Serializable;
/**
 * Représente un jeton du jeu Puissance 4.
 * <p>
 * Un jeton est caractérisé par sa couleur.
 * </p>
 */
public class Jeton implements Serializable{

    private Couleur couleur;

    /**
     * Construit un nouveau jeton avec la couleur spécifiée.
     *
     * @param couleur la couleur du jeton.
     */
    public Jeton(Couleur couleur) {
        this.couleur = couleur;
    }

    /**
     * Récupère la couleur de ce jeton.
     *
     * @return la couleur du jeton.
     */
    public Couleur getCouleur() {
        return couleur;
    }

    /**
     * Retourne une représentation textuelle du jeton.
     *
     * @return une chaîne décrivant le jeton (ex: "Jeton{couleur=ROUGE}").
     */
    @Override
    public String toString() {
        return "Jeton{couleur=" + couleur + "}";
    }
}