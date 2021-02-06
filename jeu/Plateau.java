package jeu;

import piece.Cavalier;
import piece.Fou;
import piece.Pion;
import piece.Reine;
import piece.Roi;
import piece.Tour;
import piece.PieceEtat;
import piece.Piece;

/** Plateau d'échec */
public class Plateau {

    public static final int DIMENSION = 8;  
    protected  Piece[][] plateau = new  Piece[DIMENSION][DIMENSION];

    public Plateau(){
        for (short ligne=0; ligne<DIMENSION; ligne++ ){
            for (short  colonne=0; colonne<DIMENSION; colonne++){
                setPiece(ligne, colonne, new Piece(PieceEtat.VIDE));
            }
        }
        plateauReset();
    }

    /** getter de piece */
    public Piece getPiece(short ligne, short colonne){
        return this.plateau[ligne][colonne];
    }

    /** setter d'une piece */
    public void setPiece(short ligne, short colonne, Piece piece){
        this.plateau[ligne][colonne] = piece;
    }
    
    /** réinitialise le plateau au valeur par défaut */
    protected void plateauReset(){

        for (short ligne=2; ligne<DIMENSION-2; ligne++ ){
            for (short colonne=0; colonne<DIMENSION; colonne++){
                setPiece(ligne, colonne, new Piece(PieceEtat.VIDE));
            }
        }

        this.plateau[0][0] = new Tour(PieceEtat.NOIR);
        this.plateau[0][1] = new Cavalier(PieceEtat.NOIR);
        this.plateau[0][2] = new Fou(PieceEtat.NOIR);
        this.plateau[0][3] = new Reine(PieceEtat.NOIR);
        this.plateau[0][4] = new Roi(PieceEtat.NOIR);
        this.plateau[0][5] = new Fou(PieceEtat.NOIR);
        this.plateau[0][6] = new Cavalier(PieceEtat.NOIR);
        this.plateau[0][7] = new Tour(PieceEtat.NOIR);

        this.plateau[1][0] = new Pion(PieceEtat.NOIR);
        this.plateau[1][1] = new Pion(PieceEtat.NOIR);
        this.plateau[1][2] = new Pion(PieceEtat.NOIR);
        this.plateau[1][3] = new Pion(PieceEtat.NOIR);
        this.plateau[1][4] = new Pion(PieceEtat.NOIR);
        this.plateau[1][5] = new Pion(PieceEtat.NOIR);
        this.plateau[1][6] = new Pion(PieceEtat.NOIR);
        this.plateau[1][7] = new Pion(PieceEtat.NOIR);

        this.plateau[7][0] = new Tour(PieceEtat.BLANC);
        this.plateau[7][1] = new Cavalier(PieceEtat.BLANC);
        this.plateau[7][2] = new Fou(PieceEtat.BLANC);
        this.plateau[7][3] = new Reine(PieceEtat.BLANC);
        this.plateau[7][4] = new Roi(PieceEtat.BLANC);
        this.plateau[7][5] = new Fou(PieceEtat.BLANC);
        this.plateau[7][6] = new Cavalier(PieceEtat.BLANC);
        this.plateau[7][7] = new Tour(PieceEtat.BLANC);

        this.plateau[6][0] = new Pion(PieceEtat.BLANC);
        this.plateau[6][1] = new Pion(PieceEtat.BLANC);
        this.plateau[6][2] = new Pion(PieceEtat.BLANC);
        this.plateau[6][3] = new Pion(PieceEtat.BLANC);
        this.plateau[6][4] = new Pion(PieceEtat.BLANC);
        this.plateau[6][5] = new Pion(PieceEtat.BLANC);
        this.plateau[6][6] = new Pion(PieceEtat.BLANC);
        this.plateau[6][7] = new Pion(PieceEtat.BLANC);

    }

    /** affiche le plateau */
    public void afficherPlateau(){
        System.out.println("    1  2  3  4  5  6  7  8");
        System.out.println("  --------------------------");
        for (short ligne=0; ligne<DIMENSION; ligne++ ){
            System.out.print(ligne+1 +" |");
            for (short colonne=0; colonne<DIMENSION; colonne++){
                if (this.plateau[ligne][colonne] instanceof Piece){
                    System.out.print(" "+ this.plateau[ligne][colonne].getEtat()+" ");
                }
            }
            System.out.println(" | "+(ligne+1));
        }
        System.out.println("   -------------------------");
        System.out.println("    1  2  3  4  5  6  7  8");
    }


    /** déplacer une piece sur le plateau */
    protected void deplacerPiece(short ligneDep, short colonneDep, short ligneAr, short colonneAr ){
        this.plateau[ligneAr][colonneAr] = this.plateau[ligneDep][colonneDep];
        this.plateau[ligneDep][colonneDep] = new Piece(PieceEtat.VIDE);
        // permet de retirer le coup de 2 cases si le pion est jouer
        if ( this.plateau[ligneAr][colonneAr] instanceof Pion) { 
            Pion pion = (Pion) this.plateau[ligneAr][colonneAr];
            pion.setJouer();
        }
    }

    /** test si le coup est dans le plateau */
    public static boolean isDansPlateau(short ligne, short colonne){
        if (ligne<DIMENSION && ligne>=0 && colonne<DIMENSION && colonne>=0) return true;
        return false;
    }

}
