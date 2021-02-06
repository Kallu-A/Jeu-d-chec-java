package piece;

import jeu.Plateau;
import move.Coord;
import move.Move;

public class Roi extends Piece {
   
    private boolean jamaisRoque = true;
    private boolean echecMat = false;
    private boolean echec = false;

    public static final short[][] VECTEUR_ROI = { {1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1} };

    public Roi(short couleur){
        super(couleur);
        if (couleur == PieceEtat.NOIR) super.setEtat(PieceEtat.ROINOIR);
        else super.setEtat(PieceEtat.ROIBLANC);
    }

    public boolean getJamaisRoque(){
        return this.jamaisRoque;
    }

    public void setRoqueJouer(){
        this.jamaisRoque = false;
    }

    public void setEchecMat(){
        this.echecMat = true;
        this.echec = true;
    }

    public void setEchec(boolean etat){
        this.echec= etat;
    }

    public boolean getEchecMat(){
        return this.echecMat;
    }

    public boolean getEchec(){
        return this.echec;
    }

    @Override
    public boolean coupPossible(Plateau plateau, Move move){
        short[][] toutVecteur = VECTEUR_ROI;
        short ligne, colonne;
        Coord to = move.to;
        Coord from = move.from;
        //test des vecteurs
        for (short[]  vecteur : toutVecteur) {
            //coordonée du roi après le vecteur
            ligne = (short) (to.ligne + vecteur[0]);
            colonne = (short) (to.colonne + vecteur[1]);
            if (Plateau.isDansPlateau(ligne, colonne) ){
                //si est dans le plateau et pas de la même couleur alors le coup est possible
                if (plateau.getPiece(to.ligne, to.colonne).isPlaceAcessible(plateau.getPiece(ligne, colonne))){
                    if (from.ligne == ligne && from.colonne == colonne) return true;
                }
            }
        }
        return false;
    }
}
