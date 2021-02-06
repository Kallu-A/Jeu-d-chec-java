package piece;

import jeu.Plateau;
import move.Move;

/** Piece d'un jeu d'échec */
public class Piece {


    private short couleur;
    private String etat;

    /** Constructeur en décidant la couleur */
    public Piece(short couleur){
        if (couleur == PieceEtat.NOIR) this.couleur = PieceEtat.NOIR;
        if (couleur == PieceEtat.BLANC) this.couleur = PieceEtat.BLANC;
        this.etat = PieceEtat.ETATINIT;

    }

    /** Constructeur de piece vide */
    public Piece(){
        this.couleur = PieceEtat.VIDE;
        this.etat= PieceEtat.ETATINIT;
    }
    /** getter de la couleur */
    public  short getCouleur(){
        return this.couleur;
    }

    /** getter de l'etat */
    public String getEtat(){
        return this.etat;
    }

    /** setter de l'état */
    public void setEtat(String etat){
        this.etat=etat;
    }

    /** setter de l'etat et de la couleur */
    public void setEtatCouleur(String etat, short couleur){
        this.etat =etat;
        this.couleur = couleur;
    }

    /**  renvoie si les pieces sont de même couleur ou non */
    public boolean isPlaceAcessible(Piece pieceViser){
        return (!(this.getCouleur() == pieceViser.getCouleur()));
    }

    /** renvoie si la piece en argument est vide ou non  */
    public boolean isPlaceLibre(Piece pieceViser){
        return ( pieceViser.getCouleur() == PieceEtat.VIDE);
    }
    /** sert a l'override */
    public boolean coupPossible(Plateau plateau, Move move){
        return false;
    }

    /** renvoie si la piece en argument est mangeable par celle appelé */
    public boolean isMangeable(Piece pieceViser){
        if (this.getCouleur() == PieceEtat.BLANC && pieceViser.getCouleur() == PieceEtat.NOIR) return true;
        if (this.getCouleur() == PieceEtat.NOIR && pieceViser.getCouleur() == PieceEtat.BLANC) return true;
        return false;
    }

}