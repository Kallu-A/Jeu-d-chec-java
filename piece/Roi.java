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
    public boolean coupPossible(Plateau plateau, Move move){
        short[][] toutVecteur = Roi.getVecteurDeplacement();
        short ligne, colonne, ligneAr, ligneDep, colonneAr, colonneDep;
        ligneDep = move.getLigneDep();
        ligneAr = move.getLigneAr();
        colonneAr = move.getColonneAr();
        colonneDep = move.getColonneDep();
        //test des vecteurs
        for (short[]  vecteur : toutVecteur) {
            //coordonée du roi après le vecteur
            ligne = (short) (ligneDep + vecteur[0]);
            colonne = (short) (colonneDep + vecteur[1]);
            if (Plateau.isDansPlateau(ligne, colonne) ){
                //si est dans le plateau et pas de la même couleur alors le coup est possible
                if (plateau.getPiece(ligneDep, colonneDep).isPlaceAcessible(plateau.getPiece(ligne, colonne))){
                    if (ligneAr == ligne && colonneAr == colonne) return true;
                }
            }
        }
        return false;
    }
}
