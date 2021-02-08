package jeu;

import move.Coord;
import move.Move;
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
                setPiece(ligne, colonne, new Piece());
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
                setPiece(ligne, colonne, new Piece());
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
        System.out.println("    A  B  C  D  E  F  G  H");
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
        System.out.println("    A  B  C  D  E  F  G  H");
    }


    /** déplacer une piece sur le plateau */
    protected void deplacerPiece(Coord depart, Coord arriver ){
        this.plateau[arriver.ligne][arriver.colonne] = this.plateau[depart.ligne][depart.colonne];
        this.plateau[depart.ligne][depart.colonne] = new Piece();
        // permet de retirer le coup de 2 cases si le pion est jouer
        if ( this.plateau[arriver.ligne][arriver.colonne] instanceof Pion) { 
            Pion pion = (Pion) this.plateau[arriver.ligne][arriver.colonne];
            pion.setJouer();
        }
    }

    /** test si le coup est dans le plateau */
    public static boolean isDansPlateau(short ligne, short colonne){
        if (ligne<DIMENSION && ligne>=0 && colonne<DIMENSION && colonne>=0) return true;
        return false;
    }

    /** récupere la position d'un roi d'une couleur */
    protected Coord getRoiInfo(short couleur){
        boolean trouver = false;
        Coord roi = new Coord(); //evite le warning pas initialiser
        //parcours le plateau pour chercher le roi de la couleur
        for (short ligne=0; ligne<DIMENSION; ligne++ ){
            for (short  colonne=0; colonne<DIMENSION; colonne++){
                if (plateau[ligne][colonne] instanceof Roi ) {
                    if (couleur == plateau[ligne][colonne].getCouleur()){
                        roi = new Coord(ligne, colonne);
                        trouver = true;
                    }
                }
            }
        }
        //si pas trouver pas de roi donc quitter
        if (!trouver) {
            System.out.println("Erreur : il manque un roi");
            return null;
        }
        return roi;

    }

    /** renvoie si le roi d'une couleur est en echec ou non */
    protected boolean isEchec(short couleur){
        Coord roi = getRoiInfo(couleur);
        if (roi == null) {System.out.println("roi null"); return false;}
        if (estMenacer(roi)) return true;
        return false;
    }

    /**regarde si la piece est menacer par une des piece adverse */
    protected boolean estMenacer(Coord piece){
        Piece pieceTempo;
        Piece pieceMenacer = plateau[piece.ligne][piece.colonne];

        for (short ligne=0; ligne<DIMENSION; ligne++ ){
            for (short  colonne=0; colonne<DIMENSION; colonne++){
                pieceTempo = plateau[ligne][colonne];
                //si la piece ne peut pas  manger  la piece menacer alors on break
                if (pieceTempo.isMangeable(pieceMenacer)) {
                    if ( pieceTempo.coupPossible(this, new Move(new Coord(ligne,colonne), piece ) ) ) return true;
                }
                //test si la pieceMenacer est dans les coups possibles de piece tempo
            }
        }  return false;  
    }

    //regarde si la piece est menacer 
    protected boolean estMenacer(Move move){
        return (plateau[move.to.ligne][move.to.colonne].coupPossible(this, move ));
    }
}
