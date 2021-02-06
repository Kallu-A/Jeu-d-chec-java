package piece;

import jeu.Plateau;

public class Cavalier extends Piece{

    public Cavalier(short couleur){
        super(couleur);
        if (couleur == PieceEtat.NOIR) super.setEtat(PieceEtat.CAVALIERNOIR);
        else super.setEtat(PieceEtat.CAVALIERBLANC);
    }

    public static short[][] getVecteurDeplacement(){
        short[][] vecteur = { {2, 1}, {2, -1}, {-2, 1}, {-2, -1},
                            {-1, -2}, {1, -2}, {1, 2}, {-1, 2}};
        return  vecteur;
    }

    @Override
    public boolean coupPossible(Plateau plateau, short ligneDep, short colonneDep, short ligneAr, short colonneAr){
        short[][] toutVecteur = Cavalier.getVecteurDeplacement();
        short ligne, colonne;
        //calcul des chemins possibles
        for (short[]  vecteur : toutVecteur) {
            ligne = (short) (ligneDep + vecteur[0]);
            colonne = (short) (colonneDep + vecteur[1]);
            //cordonnés du coup arriver
            if ( Plateau.isDansPlateau(ligne, colonne) ){
                //test si la piece d'arriver est accessible pour la piece de départ 
                if ( plateau.getPiece( ligneDep, colonneDep ).isPlaceAcessible( plateau.getPiece(ligne, colonne) ) ) {
                    if (ligneAr == ligne && colonneAr == colonne) return true;
                }
            }
        }
        return false;
    }
}
