package business;
import java.io.Serializable;
/**
 * Représente la grille de jeu du Puissance 4.
 * <p>
 * Cette classe gère le stockage des jetons dans un tableau à deux dimensions,
 * l'insertion des jetons (avec la gravité), la vérification des colonnes pleines
 * et la détection des alignements gagnants (4 à la suite).
 * </p>
 */
public class Grille implements Serializable{

    /**
     * Le nombre de lignes de la grille (standard : 6).
     */
    public static final int NB_LIGNES = 6;

    /**
     * Le nombre de colonnes de la grille (standard : 7).
     */
    public static final int NB_COLONNES = 7;

    private Jeton[][] plateauJetons;

    /**
     * Construit une grille vide.
     * <p>
     * Initialise un tableau de dimensions 6x7 avec des cases vides (null).
     * </p>
     */
    public Grille() {
        this.plateauJetons = new Jeton[NB_LIGNES][NB_COLONNES];
    }

    /**
     * Construit une grille à partir d'un tableau de jetons existant.
     * <p>
     * Ce constructeur permet de reprendre une partie ou de tester une configuration spécifique.
     * Il vérifie que le tableau fourni respecte les dimensions standards.
     * </p>
     *
     * @param plateauJetons le tableau de jetons à utiliser.
     * @throws Puissance4Exception si le tableau est null ou a de mauvaises dimensions.
     */
    public Grille(Jeton[][] plateauJetons) throws Puissance4Exception {
        if (plateauJetons == null) {
            throw new Puissance4Exception("Le tableau ne peut pas être null");
        }
        if (plateauJetons.length != NB_LIGNES || plateauJetons[0].length != NB_COLONNES) {
            throw new Puissance4Exception("Les dimensions du tableau sont incorrectes. Les dimensions attendu :"
                    + NB_LIGNES + "x" + NB_COLONNES);
        }
        this.plateauJetons = plateauJetons;
    }

    /**
     * Récupère le jeton situé à une position donnée.
     *
     * @param position la position (ligne, colonne) à consulter.
     * @return le Jeton présent à cette case, ou null si la case est vide.
     * @throws IllegalArgumentException si la position est null ou hors des limites de la grille.
     */
    public Jeton getJeton(Position position) {
        if (position == null) {
            throw new IllegalArgumentException("La position ne peu pas être null ");
        }
        int lig = position.getLigne();
        int col = position.getColonne();

        if (lig < 0 || lig >= NB_LIGNES || col < 0 || col >= NB_COLONNES) {
            throw new IllegalArgumentException("Position hors grille : " + position);
        }
        return this.plateauJetons[lig][col];
    }

    /**
     * Récupère le tableau interne des jetons.
     *
     * @return le tableau à deux dimensions représentant la grille.
     */
    public Jeton[][] getJetons() {
        return this.plateauJetons;
    }

    /**
     * Vérifie si une colonne spécifique est pleine.
     * <p>
     * Une colonne est considérée pleine si la case tout en haut (ligne 0) contient un jeton.
     * </p>
     *
     * @param numColonne l'indice de la colonne à vérifier.
     * @return true si la colonne est pleine, false sinon.
     * @throws IllegalArgumentException si le numéro de colonne n'existe pas.
     */
    public boolean isFullColonne(int numColonne) {
        if (numColonne < 0 || numColonne >= NB_COLONNES) {
            throw new IllegalArgumentException("Numéro de colonne invalide : " + numColonne);
        }
        if (this.plateauJetons[0][numColonne] != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Insère un jeton dans une colonne en respectant la gravité.
     * <p>
     * Le jeton "tombe" jusqu'à la position libre la plus basse dans la colonne choisie.
     * </p>
     *
     * @param jeton le jeton à insérer.
     * @param col   l'indice de la colonne où lâcher le jeton.
     * @return l'indice de la ligne où le jeton s'est arrêté.
     * @throws IllegalArgumentException si la colonne est invalide.
     * @throws Puissance4Exception      si la colonne est déjà pleine.
     */
    public int insererJeton(Jeton jeton, int col) throws Puissance4Exception {
        if (col < 0 || col >= NB_COLONNES) {
            throw new IllegalArgumentException("Colonne invalide");
        }
        if (isFullColonne(col)) {
            throw new Puissance4Exception("La colonne " + col + " est pleine !");
        }
        for (int i = NB_LIGNES - 1; i >= 0; i--) {
            if (this.plateauJetons[i][col] == null) {
                this.plateauJetons[i][col] = jeton;
                return i;

            }
        }
        throw new Puissance4Exception("Erreur critique : insertion impossible ");
    }

    /**
     * Vérifie si la grille entière est remplie (Match Nul).
     *
     * @return true si toutes les colonnes sont pleines, false s'il reste de la place.
     */
    public boolean isFullGrille() {
        for (int col = 0; col < NB_COLONNES; col = col + 1) {

            if (this.plateauJetons[0][col] == null) {

                return false;
            }
        }
        return true;
    }

    /**
     * Vérifie si un alignement de 4 jetons (ou plus) a été réalisé autour d'une position.
     * <p>
     * Cette méthode vérifie les 4 directions : Horizontale, Verticale et les deux Diagonales.
     * </p>
     *
     * @param position la position du dernier jeton posé (point de départ de la vérification).
     * @return true si un alignement de 4 est trouvé, false sinon.
     * @throws IllegalArgumentException si la position est invalide.
     */
    public boolean alignementRealise(Position position) {
        if (position == null) {
            throw new IllegalArgumentException("La position ne peut pas être null");
        }
        int l = position.getLigne();
        int c = position.getColonne();
        if (l < 0 || l >= NB_LIGNES || c < 0 || c >= NB_COLONNES) {
            throw new IllegalArgumentException("Position hors limites : " + position);
        }
        if (alignementHorizontal(position) >= 4) return true;
        if (alignementVertical(position) >= 4) return true;
        if (alignementDiagonal1(position) >= 4) return true;
        if (alignementDiagonal2(position) >= 4) return true;

        return false;
    }

    /**
     * Retourne une représentation textuelle simple de la grille.
     *
     * @return une chaîne décrivant la taille de la grille.
     */
    @Override
    public String toString() {
        return "Grille{lignes=" + NB_LIGNES + ", colonnes=" + NB_COLONNES + "}";
    }

    // --- MÉTHODES PRIVÉES (Non documentées dans la Javadoc publique) ---

    private int alignementHorizontal(Position position) {
        int ligne = position.getLigne();
        int colonne = position.getColonne();
        if (this.plateauJetons[ligne][colonne] == null) {
            return 0;
        }
        Couleur couleur = this.plateauJetons[ligne][colonne].getCouleur();
        int nbAlignes = 1;
        boolean memeCouleur;
        int colCourante;

        colCourante = colonne;
        memeCouleur = true;

        while (memeCouleur && colCourante > 0) {
            colCourante = colCourante - 1;

            if (this.plateauJetons[ligne][colCourante] != null && this.plateauJetons[ligne][colCourante].getCouleur() == couleur) {
                memeCouleur = true;

            } else {
                memeCouleur = false;
            }
            if (memeCouleur) {
                nbAlignes++;
            }
        }
        colCourante = colonne;
        memeCouleur = true;


        while (memeCouleur && colCourante < NB_COLONNES - 1) {
            colCourante = colCourante + 1;

            if (this.plateauJetons[ligne][colCourante] != null &&
                    this.plateauJetons[ligne][colCourante].getCouleur() == couleur) {
                memeCouleur = true;
            } else {
                memeCouleur = false;
            }

            if (memeCouleur) {
                nbAlignes++;
            }
        }
        return nbAlignes;
    }

    private int alignementVertical(Position position) {
        int ligne = position.getLigne();
        int colonne = position.getColonne();
        if (this.plateauJetons[ligne][colonne] == null) {
            return 0;
        }
        Couleur couleur = this.plateauJetons[ligne][colonne].getCouleur();
        int nbAlignes = 1;
        boolean memeCouleur;
        int ligneCourante;

        ligneCourante = ligne;
        memeCouleur = true;

        while (memeCouleur && ligneCourante > 0) {
            ligneCourante = ligneCourante - 1;

            if (this.plateauJetons[ligneCourante][colonne] != null && this.plateauJetons[ligneCourante][colonne].getCouleur() == couleur) {
                memeCouleur = true;

            } else {
                memeCouleur = false;
            }
            if (memeCouleur) {
                nbAlignes++;
            }
        }
        ligneCourante = ligne;
        memeCouleur = true;

        while (memeCouleur && ligneCourante < this.plateauJetons.length - 1) {
            ligneCourante = ligneCourante + 1;

            if (this.plateauJetons[ligneCourante][colonne] != null && this.plateauJetons[ligneCourante][colonne].getCouleur() == couleur) {
                memeCouleur = true;

            } else {
                memeCouleur = false;
            }
            if (memeCouleur) {
                nbAlignes++;
            }
        }
        return nbAlignes;
    }

    private int alignementDiagonal1(Position position) {
        int ligne = position.getLigne();
        int colonne = position.getColonne();
        if (this.plateauJetons[ligne][colonne] == null) {
            return 0;
        }
        Couleur couleur = this.plateauJetons[ligne][colonne].getCouleur();
        int nbAlignes = 1;
        boolean memeCouleur = true;
        int ligneCourante = ligne;
        int colonneCourante = colonne;

        while (memeCouleur && ligneCourante > 0 && colonneCourante > 0) {
            ligneCourante = ligneCourante - 1;   // On monte
            colonneCourante = colonneCourante - 1; // On va a gauche

            if (this.plateauJetons[ligneCourante][colonneCourante] != null &&
                    this.plateauJetons[ligneCourante][colonneCourante].getCouleur() == couleur) {
                nbAlignes++;
            } else {
                memeCouleur = false;
            }
        }
        memeCouleur = true;
        ligneCourante = ligne;
        colonneCourante = colonne;

        while (memeCouleur && ligneCourante < this.plateauJetons.length - 1
                && colonneCourante < this.plateauJetons[0].length - 1) {
            ligneCourante++;   // On descend
            colonneCourante++; // On va a droite

            if (this.plateauJetons[ligneCourante][colonneCourante] != null &&
                    this.plateauJetons[ligneCourante][colonneCourante].getCouleur() == couleur) {
                nbAlignes++;
            } else {
                memeCouleur = false;
            }
        }
        return nbAlignes;

    }

    private int alignementDiagonal2(Position position) {
        int ligne = position.getLigne();
        int colonne = position.getColonne();
        if (this.plateauJetons[ligne][colonne] == null) {
            return 0;
        }
        Couleur couleur = this.plateauJetons[ligne][colonne].getCouleur();
        int nbAlignes = 1;
        boolean memeCouleur;
        int ligneCourante;
        int colonneCourante;

        memeCouleur = true;
        ligneCourante = ligne;
        colonneCourante = colonne;


        while (memeCouleur && ligneCourante > 0 && colonneCourante < this.plateauJetons[0].length - 1) {
            ligneCourante--;   // On monte
            colonneCourante++; // On va à droite

            if (this.plateauJetons[ligneCourante][colonneCourante] != null &&
                    this.plateauJetons[ligneCourante][colonneCourante].getCouleur() == couleur) {
                nbAlignes++;
            } else {
                memeCouleur = false;
            }
        }

        memeCouleur = true;
        ligneCourante = ligne;
        colonneCourante = colonne;


        while (memeCouleur && ligneCourante < this.plateauJetons.length - 1 && colonneCourante > 0) {
            ligneCourante++;   // On descend
            colonneCourante--; // On va à gauche

            if (this.plateauJetons[ligneCourante][colonneCourante] != null &&
                    this.plateauJetons[ligneCourante][colonneCourante].getCouleur() == couleur) {
                nbAlignes++;
            } else {
                memeCouleur = false;
            }
        }
        return nbAlignes;
    }
}