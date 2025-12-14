package business;

public class Grille {
    public static final int NB_LIGNES = 6;
    public static final int NB_COLONNES = 7;
    private Jeton[][] plateauJetons;

    public Grille(){
        this.plateauJetons = new Jeton[NB_LIGNES][NB_COLONNES];
    }

    public Grille(Jeton[][] plateauJetons) throws Puissance4Exception{
        if (plateauJetons == null){
            throw new Puissance4Exception ("Le tableau ne peut pas être null");
        }
        if (plateauJetons.length !=NB_LIGNES || plateauJetons[0] !=NB_COLONNES){
            throw new Puissance4Exception("Les dimensions du tableau sont incorrectes. Les dimensions attendu :"
            + NB_LIGNES + "x" + NB_COLONNES);
        }
        this.plateauJetons = plateauJetons;
    }

    public getJeton(Position position){
        if (position == null) {
            throw new IllegalArgumentException("La position ne peu pas être null ");
        }
        int lig = position.getLigne();
        int col = position.getColonne();

        if (lig < 0 || lig >= NB_LIGNES || col < 0 || col >= NB_COLONNES){
            throw new IllegalArgumentException("Position hors grille : " + position);
        }
        return this.plateauJetons[lig][col];
    }
    public boolean isFullColonne(int numColonne){
        if (numColonne < 0 || numColonne >= NB_COLONNES) {
            throw new IllegalArgumentException("Numéro de colonne invalide : " + numColonne);
        }
        if (this.plateauJetons[0][numColonne] != null) {
            return true;
        } else {
            return false;
        }
    }
    public int insererJeton(Jeton jeton, int col) throws Puissance4Exception {
        if ( col < 0 || col >= NB_COLONNES){
            throw new IllegalArgumentException ("Colonne invalide");
        }
        if (isFullColonne(col)) {
            throw new Puissance4Exception("La colonne " + col + " est pleine !");
        }
    }
}
