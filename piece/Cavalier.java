package piece;

import jeu.Plateau;

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
        short ligne, colonne, ligneAr, ligneDep, colonneAr, colonneDep;
        ligneDep = move.getLigneDep();
        ligneAr = move.getLigneAr();
        colonneAr = move.getColonneAr();
        colonneDep = move.getColonneDep();
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
