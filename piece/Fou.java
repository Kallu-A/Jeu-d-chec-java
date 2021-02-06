package piece;

import jeu.Plateau;

public class Fou extends Piece {

    public static final short[][] VECTEUR_FOU = { {1, 1}, {1, -1}, {-1, 1}, {-1, -1} };

    public Fou(short couleur){
        super(couleur);
        if (couleur == PieceEtat.NOIR) super.setEtat(PieceEtat.FOUNOIR);
        else super.setEtat(PieceEtat.FOUBLANC);
    }

    @Override
    public boolean coupPossible(Plateau plateau, Move move){
        short[][] toutVecteur = VECTEUR_FOU;
        short ligne, colonne ,i, ligneAr, ligneDep, colonneAr, colonneDep;
        ligneDep = move.getLigneDep();
        ligneAr = move.getLigneAr();
        colonneAr = move.getColonneAr();
        colonneDep = move.getColonneDep();
        //calcul des chemins 
        for (short[]  vecteur : toutVecteur) {
            i=1;
            //pour avoir plus qu'une case de profondeur
            while (true){
                //position obtenu en passant par le vecteur
                ligne = (short) (ligneDep + i * vecteur[0]);
                colonne = (short) (colonneDep + i * vecteur[1]);
                if (Plateau.isDansPlateau(ligne, colonne) ){
                    //si il n'y a pas de piece
                    if ( plateau.getPiece(ligneDep, colonneDep).isPlaceLibre( plateau.getPiece(ligne, colonne) ) ){
                        //les coordonées d'arriver et du chemin calculé sont égal alors c'est possible
                        if (ligneAr == ligne && colonneAr == colonne) return true;
                    } else {
                        //si la place n'est pas pas vide 
                        //test si elle est mangeable couleur opposé et coordonée du chemin correspond sinon on break
                        if ( plateau.getPiece(ligneDep, colonneDep).isMangeable( plateau.getPiece(ligneAr, colonneAr) )
                        && ligneAr == ligne && colonneAr == colonne ) return true;
                        break;
                    }
                    //si ont sort du plateau on break
                } else break; 
                i++;
            }
        }
        return false;
    }
}
