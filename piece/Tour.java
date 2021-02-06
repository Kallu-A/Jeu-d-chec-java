package piece;

import jeu.Plateau;
import move.Coord;
import move.Move;

public class Tour extends Piece {

    public static final short[][] VECTEUR_TOUR = { {1, 0}, {0, 1}, {-1, 0}, {0, -1} };
    
    public Tour(short couleur){
        super(couleur);
        if (couleur == PieceEtat.NOIR) super.setEtat(PieceEtat.TOURNOIR);
        else super.setEtat(PieceEtat.TOURBLANC);
    }

    @Override
    public boolean coupPossible(Plateau plateau, Move move){
        short[][] toutVecteur = VECTEUR_TOUR;
        short ligne, colonne ,i;
        Coord to = move.to;
        Coord from = move.from;
        //test des vecteurs
        for (short[]  vecteur : toutVecteur) {
            i=1;
            while (true){
                //postion de la tour avec le vecteur
                ligne = (short) (to.ligne + i * vecteur[0]);
                colonne = (short) (to.colonne + i * vecteur[1]);

                if (Plateau.isDansPlateau(ligne, colonne) ){
                    //si la place est libre 
                    if ( plateau.getPiece(to.ligne, to.colonne).isPlaceLibre( plateau.getPiece(ligne, colonne) ) ){
                        //si coordonées correrspondent avec celle d'arriver alors coup vrai
                        if (from.ligne == ligne && from.colonne == colonne) return true;
                    } else {
                        //test si la piece est mangeable et math avec l'arriver 
                        if (plateau.getPiece(to.ligne, to.colonne).isMangeable( plateau.getPiece(from.ligne, from.colonne) )
                        && from.ligne == ligne && from.colonne == colonne) return true;
                        //Si pas mangeable ou match pas on break le vecteur la 
                        break;
                    }
                } else break; 
                i++;
            }
        }
        return false;
    }
}
