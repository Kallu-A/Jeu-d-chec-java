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
    public boolean coupPossible(Plateau plateau, short ligneDep, short colonneDep, short ligneAr, short colonneAr){
        short[][] toutVecteur = Tour.getVecteurDeplacement();
        short[] positionPiece = {ligneDep, colonneDep};
        short ligne, colonne ,i;
        for (short[]  vecteur : toutVecteur) {
            i=1;
            while (true){
                ligne = (short) (positionPiece[0] + i * vecteur[0]);
                colonne = (short) (positionPiece[1] + i * vecteur[1]);
                if (Plateau.isDansPlateau(ligne, colonne) ){
                    if (plateau.plateau[positionPiece[0]][positionPiece[1]].isPlaceLibre(plateau.plateau[ligne][colonne])){
                        if (ligneAr == ligne && colonneAr == colonne) return true;
                    } else {
                        if (plateau.plateau[positionPiece[0]][positionPiece[1]].isMangeable(plateau.plateau[ligneAr][colonneAr])
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
