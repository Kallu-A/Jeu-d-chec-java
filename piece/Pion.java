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
    public boolean coupPossible(Plateau plateau, short ligneDep, short colonneDep, short ligneAr, short colonneAr){
        short ligne;
        Pion pion = (Pion) plateau.plateau[ligneDep][colonneDep];
        short couleur = pion.getCouleur();
        boolean jamaisJouer = pion.getJamaisJouer();
        if ( couleur == PieceEtat.NOIR){
            //deplacement
            if (colonneAr == colonneDep ){
                for (ligne = (short) (ligneDep+1) ; ligne <= ligneAr; ligne++){
                    if (Plateau.isDansPlateau(ligne, colonneDep) ){
                        if (plateau.plateau[ligneDep][colonneDep].isPlaceLibre(plateau.plateau[ligne][colonneDep])){
                            if (jamaisJouer && ligneAr == ligne && colonneAr == colonneDep && ligneDep+2 == ligneAr) return true;
                            if (ligneAr == ligne && colonneAr == colonneDep && ligneAr-ligneDep == 1) return true; 
                        } else break;
                    } else break;
                }
            }
            //manger
            if ( (colonneDep-1 == colonneAr || colonneDep+1 == colonneAr)&& ligneDep+1 == ligneAr 
                    && plateau.plateau[ligneDep][colonneDep].isMangeable(plateau.plateau[ligneAr][colonneAr])) return true;

        } else {
            if (colonneAr == colonneDep ){
                for (ligne = (short)(ligneDep-1); ligne >=ligneAr; ligne--){
                    if (Plateau.isDansPlateau(ligne, colonneDep) ){
                        if (plateau.plateau[ligneDep][colonneDep].isPlaceLibre(plateau.plateau[ligne][colonneDep])){
                            if (jamaisJouer && ligneAr == ligne && colonneAr == colonneDep && ligneDep-2 == ligneAr) return true;
                            if (ligneAr == ligne && colonneAr == colonneDep && ligneDep-ligneAr == 1) return true;
                        }  else break;
                    } else break;
                }
            }
            if ( (colonneDep-1 == colonneAr || colonneDep+1 == colonneAr)&& ligneDep-1 == ligneAr 
            && plateau.plateau[ligneDep][colonneDep].isMangeable(plateau.plateau[ligneAr][colonneAr])) return true;
        }


        return false;
    }
}
