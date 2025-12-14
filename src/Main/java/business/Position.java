package business;

public class Position {
    private int ligne;
    private int colonne;

    Position(int ligne, int colonne){
        this.ligne = ligne;
        this.colonne = colonne;
    }
    public int getLigne(){
        return ligne;
    }
    public int getColonne(){
        return colonne;
    }
    public String toString(){
        return "Position{ligne" + ligne + "colonne=" + colonne + "}";
    }
    @Override
    public boolean equals(Object o){
        if (o == null ) return false;
        // si l'objet n'est pas une Position ( C'est une grille ou string)
        // est ce que l'objet o est bien une position??
        if (o instanceof Position) {
            Position autrePosition = (Position) o;
            return this.ligne == autrePosition.ligne && this.colonne == autrePosition.colonne;
        }
        return false;
    }
}
