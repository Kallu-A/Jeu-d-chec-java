package move;


/** coordonée composé de deux valeur */
public class Coord {

    public short ligne ,colonne ;

    public Coord(short ligne, short colonne){
        this.ligne=ligne;
        this.colonne=colonne;
    }

    public Coord(){
        this.ligne = -1;
        this.colonne = -1;
    }

    
}
