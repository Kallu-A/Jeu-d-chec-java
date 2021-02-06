package piece;

import jeu.Plateau;
import move.Coord;
import move.Move;

public class Reine extends Piece {
       
    public Reine(short couleur){
        super(couleur);
        if (couleur == PieceEtat.NOIR) super.setEtat(PieceEtat.REINENOIR);
        else super.setEtat(PieceEtat.REINEBLANC);
    }
    
    @Override
    public boolean coupPossible(Plateau plateau, Move move){
        //même vecteur que le roi mais sans limite de distance
        short[][] toutVecteur = Roi.VECTEUR_ROI;
        short ligne, colonne ,i;
        Coord to = move.to;
        Coord from = move.from;
        //calcul des vecteurs
        for (short[]  vecteur : toutVecteur) {
            i=1;
            while (true){
                ligne = (short) (to.ligne + i * vecteur[0]);
                colonne = (short) (to.colonne + i * vecteur[1]);
                if (Plateau.isDansPlateau(ligne, colonne) ){
                    // test si la place est libre 
                    if ( plateau.getPiece(to.ligne, to.colonne).isPlaceLibre( plateau.getPiece(ligne, colonne) ) ){
                        //si la position du vecteur rejoins celle de l'arriver alors possible
                        if (from.ligne == ligne && from.colonne == colonne) return true;
                    } else {
                        //si il y'a une piece test si elle est mangeable et si le vecteur rejoins l'arriver si oui alors vrai sinon break;
                        if (plateau.getPiece(to.ligne, to.colonne).isMangeable(plateau.getPiece(from.ligne, from.colonne) )
                            && from.ligne == ligne && from.colonne == colonne) return true;
                        break;
                    }
                } else break; 
                i++;
            }
        }
        return false;
    }
}

