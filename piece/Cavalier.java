package piece;

import jeu.Plateau;
import move.Coord;
import move.Move;

public class Cavalier extends Piece{

    public static final short[][] VECTEUR_CAVALIER = { {2, 1}, {2, -1}, {-2, 1}, {-2, -1},
                                                     {-1, -2}, {1, -2}, {1, 2}, {-1, 2}};

    public Cavalier(short couleur){
        super(couleur);
        if (couleur == PieceEtat.NOIR) super.setEtat(PieceEtat.CAVALIERNOIR);
        else super.setEtat(PieceEtat.CAVALIERBLANC);
    }

    @Override
    public boolean coupPossible(Plateau plateau, Move move){
        short[][] toutVecteur = VECTEUR_CAVALIER;
        short ligne, colonne;
        Coord to = move.to;
        Coord from = move.from;
        //calcul des chemins possibles
        for (short[]  vecteur : toutVecteur) {
            ligne = (short) (to.ligne + vecteur[0]);
            colonne = (short) (to.colonne + vecteur[1]);
            //cordonnés du coup arriver
            if ( Plateau.isDansPlateau(ligne, colonne) ){
                //test si la piece d'arriver est accessible pour la piece de départ 
                if ( plateau.getPiece( to.ligne, to.colonne ).isPlaceAcessible( plateau.getPiece(ligne, colonne) ) ) {
                    if (from.ligne == ligne && from.colonne == colonne) return true;
                }
            }
        }
        return false;
    }
}
