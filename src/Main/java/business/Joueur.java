package business;
import java.io.Serializable;
/**
 * Représente un joueur du jeu Puissance 4.
 * <p>
 * Un joueur est identifié par la couleur de ses pions.
 * </p>
 */
public class Joueur implements Serializable{

    private final Couleur nom;

    /**
     * Construit un joueur associé à une couleur donnée.
     *
     * @param nom la couleur qui sert d'identifiant au joueur.
     */
    public Joueur(Couleur nom) {
        this.nom = nom;
    }
    /**
     * Récupère la couleur (le nom) du joueur.
     *
     * @return la couleur associée au joueur.
     */
    public Couleur getNom() {
        return this.nom;
    }
    /**
     * Retourne une représentation textuelle du joueur.
     *
     * @return une chaîne décrivant le joueur (ex: "Joueur{nom=JAUNE}").
     */
    @Override
    public String toString() {
        return "Joueur{nom=" + nom + "}";
    }
}