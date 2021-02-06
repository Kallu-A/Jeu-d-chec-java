package piece;

import jeu.Plateau;

public class Roi extends Piece {
   
    private boolean jamaisRoque = true;
    private boolean echecMat = false;
    private boolean echec = false;

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

    public static short[][] getVecteurDeplacement(){
        short[][] vecteur = { {1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1} };
        return  vecteur;
    }

    @Override
    public boolean coupPossible(Plateau plateau, short ligneDep, short colonneDep, short ligneAr, short colonneAr){
        short[][] toutVecteur = Roi.getVecteurDeplacement();
        short[] positionPiece = {ligneDep, colonneDep};
        short ligne, colonne;
        for (short[]  vecteur : toutVecteur) {
            ligne = (short) (positionPiece[0] + vecteur[0]);
            colonne = (short) (positionPiece[1] + vecteur[1]);
            if (Plateau.isDansPlateau(ligne, colonne) ){
                if (plateau.plateau[positionPiece[0]][positionPiece[1]].isPlaceAcessible(plateau.plateau[ligne][colonne])){
                    if (ligneAr == ligne && colonneAr == colonne) return true;
                }
            }
        }
        return false;
    }
}
