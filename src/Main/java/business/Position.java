package business;

/**
 * Représente une position (coordonnées ligne, colonne) sur la grille.
 * <p>
 * Cette classe permet de manipuler les coordonnées des cases du jeu.
 * </p>
 */
public class Position {

    private int ligne;
    private int colonne;

    /**
     * Construit une position avec les coordonnées données.
     *
     * @param ligne   l'indice de la ligne (doit être positif).
     * @param colonne l'indice de la colonne (doit être positif).
     * @throws IllegalArgumentException si une coordonnée est négative.
     */
    public Position(int ligne, int colonne) {
        if (ligne < 0 || colonne < 0) {
            throw new IllegalArgumentException("Position négative impossible");
        }
        this.ligne = ligne;
        this.colonne = colonne;
    }

    /**
     * Récupère l'indice de la ligne.
     *
     * @return l'indice de la ligne.
     */
    public int getLigne() {
        return ligne;
    }

    /**
     * Récupère l'indice de la colonne.
     *
     * @return l'indice de la colonne.
     */
    public int getColonne() {
        return colonne;
    }

    /**
     * Vérifie l'égalité entre deux positions.
     *
     * @param o l'objet à comparer.
     * @return true si les positions sont identiques (même ligne, même colonne).
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o instanceof Position) {
            Position autrePosition = (Position) o;
            return this.ligne == autrePosition.ligne && this.colonne == autrePosition.colonne;
        }
        return false;
    }

    /**
     * Retourne une représentation textuelle de la position.
     *
     * @return une chaîne décrivant la position (ex: "Position{ligne=2, colonne=3}").
     */
    @Override
    public String toString() {
        return "Position{ligne=" + ligne + ", colonne=" + colonne + "}";
    }
}