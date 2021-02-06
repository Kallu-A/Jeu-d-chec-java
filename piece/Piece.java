package piece;

import jeu.Plateau;

/** Piece d'un jeu d'échec */
public class Piece {


    private short couleur;
    private String etat;

    /** Constructeur en décidant la couleur */
    public Piece(short couleur){
        if (couleur == PieceEtat.NOIR) this.couleur = PieceEtat.NOIR;
        if (couleur == PieceEtat.BLANC) this.couleur = PieceEtat.BLANC;
        if (couleur == PieceEtat.VIDE) this.couleur = PieceEtat.VIDE;
        this.etat = PieceEtat.ETATINIT;

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

    public boolean isPlaceAcessible(Piece pieceViser){
        return (!(this.getCouleur() == pieceViser.getCouleur()));
    }
    public boolean isPlaceLibre(Piece pieceViser){
        return ( pieceViser.getCouleur() == PieceEtat.VIDE);
    }
    public boolean coupPossible(Plateau plateau, short ligneDep, short colonneDep, short ligneAr, short colonneAr){
        return false;
    }

    public boolean isMangeable(Piece pieceViser){
        if (this.getCouleur() == PieceEtat.BLANC && pieceViser.getCouleur() == PieceEtat.NOIR) return true;
        if (this.getCouleur() == PieceEtat.NOIR && pieceViser.getCouleur() == PieceEtat.BLANC) return true;
        return false;
    }

}