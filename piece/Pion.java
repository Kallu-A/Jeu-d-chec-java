package piece;

import jeu.Plateau;
import move.Coord;
import move.Move;

public class Pion extends Piece {

    private boolean jamaisJouer =true;

    public Pion(short couleur){
        super(couleur);
        if (couleur == PieceEtat.NOIR) super.setEtat(PieceEtat.PIONNOIR);
        else super.setEtat(PieceEtat.PIONBLANC);
    }
    
    public void setJouer(){
        this.jamaisJouer = false;
    }

    public boolean getJamaisJouer(){
        return this.jamaisJouer;
    }

    @Override
    public boolean coupPossible(Plateau plateau, Move move){
        Pion pion = (Pion) plateau.getPiece(move.to.ligne, move.to.colonne );
        short couleur = pion.getCouleur();
        boolean jamaisJouer = pion.getJamaisJouer();
        return ( coupCalculPossible(plateau, move, couleur, jamaisJouer) );
    }

    /** calcul des coups possibles du pion */
    private boolean coupCalculPossible(Plateau plateau, Move move, short couleur, boolean jamaisJouer){
        short direction;
        Coord to = move.to;
        Coord from = move.from; 
        if (couleur == PieceEtat.NOIR) direction = 1;
        else direction = -1;
        //test de la colonne
        if (from.colonne == to.colonne ){
            //calcul des possiblités

           //test pour le premier coup de 1case                  
            if ( plateau.getPiece(to.ligne, to.colonne).isPlaceLibre( plateau.getPiece( (short) (to.ligne + direction), to.colonne) ) ){
            if (from.colonne == to.colonne && from.ligne-to.ligne == direction) return true; 
            if ( plateau.getPiece(to.ligne, to.colonne).isPlaceLibre( plateau.getPiece( (short) (to.ligne + 2 * direction), to.colonne) ) ){
                if (jamaisJouer && from.colonne == to.colonne && to.ligne+ (2* direction) == from.ligne) return true;
            } 
            }
        } else {
        //cas ou on mange test si la colonne est bien adjacente 
            if ( (to.colonne-1 == from.colonne || to.colonne+1 == from.colonne) && to.ligne+ direction == from.ligne 
                    && plateau.getPiece(to.ligne, to.colonne).isMangeable( plateau.getPiece(from.ligne, from.colonne) ) ) return true;
                    //si pas la même colonne est pas une pièce a manger alors faux
                return false;
        }
        return false;
    }
}
