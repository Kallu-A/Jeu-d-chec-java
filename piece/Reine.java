package piece;

import jeu.Plateau;

public class Reine extends Piece {
       
    public Reine(short couleur){
        super(couleur);
        if (couleur == PieceEtat.NOIR) super.setEtat(PieceEtat.REINENOIR);
        else super.setEtat(PieceEtat.REINEBLANC);
    }
    
    @Override
    public boolean coupPossible(Plateau plateau, Move move){
        //même vecteur que le roi mais sans limite de distance
        short[][] toutVecteur = Roi.getVecteurDeplacement();
        short ligne, colonne ,i, ligneAr, ligneDep, colonneAr, colonneDep;
        ligneDep = move.getLigneDep();
        ligneAr = move.getLigneAr();
        colonneAr = move.getColonneAr();
        colonneDep = move.getColonneDep();
        //calcul des vecteurs
        for (short[]  vecteur : toutVecteur) {
            i=1;
            while (true){
                ligne = (short) (ligneDep + i * vecteur[0]);
                colonne = (short) (colonneDep + i * vecteur[1]);
                if (Plateau.isDansPlateau(ligne, colonne) ){
                    // test si la place est libre 
                    if ( plateau.getPiece(ligneDep, colonneDep).isPlaceLibre( plateau.getPiece(ligne, colonne) ) ){
                        //si la position du vecteur rejoins celle de l'arriver alors possible
                        if (ligneAr == ligne && colonneAr == colonne) return true;
                    } else {
                        //si il y'a une piece test si elle est mangeable et si le vecteur rejoins l'arriver si oui alors vrai sinon break;
                        if (plateau.getPiece(ligneDep, colonneDep).isMangeable(plateau.getPiece(ligneAr, colonneAr) )
                            && ligneAr == ligne && colonneAr == colonne) return true;
                        break;
                    }
                } else break; 
                i++;
            }
        }
        return false;
    }
}

