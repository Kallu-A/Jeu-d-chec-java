package piece;

import jeu.Plateau;

public class Tour extends Piece {
    
    public Tour(short couleur){
        super(couleur);
        if (couleur == PieceEtat.NOIR) super.setEtat(PieceEtat.TOURNOIR);
        else super.setEtat(PieceEtat.TOURBLANC);
    }

    public static short[][] getVecteurDeplacement(){
        short[][] vecteur = { {1, 0}, {0, 1}, {-1, 0}, {0, -1} };
        return  vecteur;
    }


    @Override
    public boolean coupPossible(Plateau plateau, Move move){
        short[][] toutVecteur = Tour.getVecteurDeplacement();
        short ligne, colonne ,i, ligneAr, ligneDep, colonneAr, colonneDep;
        ligneDep = move.getLigneDep();
        ligneAr = move.getLigneAr();
        colonneAr = move.getColonneAr();
        colonneDep = move.getColonneDep();

        //test des vecteurs
        for (short[]  vecteur : toutVecteur) {
            i=1;
            while (true){
                //postion de la tour avec le vecteur
                ligne = (short) (ligneDep + i * vecteur[0]);
                colonne = (short) (colonneDep + i * vecteur[1]);

                if (Plateau.isDansPlateau(ligne, colonne) ){
                    //si la place est libre 
                    if ( plateau.getPiece(ligneDep, colonneDep).isPlaceLibre( plateau.getPiece(ligne, colonne) ) ){
                        //si coordonées correrspondent avec celle d'arriver alors coup vrai
                        if (ligneAr == ligne && colonneAr == colonne) return true;
                    } else {
                        //test si la piece est mangeable et math avec l'arriver 
                        if (plateau.getPiece(ligneDep, colonneDep).isMangeable( plateau.getPiece(ligneAr, colonneAr) )
                        && ligneAr == ligne && colonneAr == colonne) return true;
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
