package piece;

import jeu.Plateau;

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
        Pion pion = (Pion) plateau.getPiece(move.getLigneDep(), move.getColonneDep() );
        short couleur = pion.getCouleur();
        boolean jamaisJouer = pion.getJamaisJouer();
        return ( coupCalculPossible(plateau, move, couleur, jamaisJouer) );
    }

    /** calcul des coups possibles du pion */
    private boolean coupCalculPossible(Plateau plateau, Move move, short couleur, boolean jamaisJouer){
        short ligneAr, ligneDep, colonneAr, colonneDep, ligne, direction;
        ligneDep = move.getLigneDep();
        ligneAr = move.getLigneAr();
        colonneAr = move.getColonneAr();
        colonneDep = move.getColonneDep();
        if (couleur == PieceEtat.NOIR) direction = 1;
        else direction = -1;
        //test de la colonne
        if (colonneAr == colonneDep ){
            //calcul des possiblités
            for (ligne = (short) (ligneDep+direction) ; ligne <= ligneAr; ligne++){

                if (Plateau.isDansPlateau(ligne, colonneDep) ){
                    //si la place est libre sinon break
                    if ( plateau.getPiece(ligneDep, colonneDep).isPlaceLibre( plateau.getPiece(ligne, colonneDep) ) ){
                        //test pour le premier coup de 2case
                        if (jamaisJouer && ligneAr == ligne && colonneAr == colonneDep && ligneDep+ (2* direction) == ligneAr) return true;
                        //test pour un coup unique
                        if (ligneAr == ligne && colonneAr == colonneDep && ligneAr-ligneDep == direction) return true; 
                    } else break;
                } else break;
            }
        } else {
        //cas ou on mange test si la colonne est bien adjacente 
            if ( (colonneDep-1 == colonneAr || colonneDep+1 == colonneAr) && ligneDep+ direction == ligneAr 
                    && plateau.getPiece(ligneDep, colonneDep).isMangeable( plateau.getPiece(ligneAr, colonneAr) ) ) return true;
                    //si pas la même colonne est pas une pièce a manger alors faux
                return false;
        }
        return false;
    }
}
