package jeu;

import piece.PieceEtat;
import terminal.KTerminal;

/** lance la partie d'échec */
public class JeuEchec extends Plateau{

    private short tour;

    public JeuEchec(){
        super();
        deroulementPartie();
    }

    /** récupere des coordonées dans la DIMENSION du plateau*/
    private short[] getCoord(String afficher){
        boolean valide;
        short[] coord = new short[2];
        do {
            System.out.println(afficher);
            valide = true;
            coord[0] = (short) (KTerminal.getValeur("Rentrer la ligne de votre case") -1);
            coord[1] = (short) (KTerminal.getValeur("Rentrer la colonne de votre case") -1);
            //test validité
            if (coord[0]>=DIMENSION || coord[0]<0) {  
                System.out.println("Erreur : la ligne de la case est incorrecte"); 
                valide = false;
            }    
            if (coord[1]>=DIMENSION || coord[1]<0) {
                System.out.println("Erreur : la colonne de la case est incorrecte");
                valide = false;
            }
            } while (!valide);
            return coord;
    }

    /** Recupere le coup et le joue si valable */
    private void jouerCoup(){
        short couleur = getCouleurTour();
        String couleurString;
        if (couleur == PieceEtat.NOIR) couleurString = "noir";
        else couleurString = "blanc";
        short[] coordDepart = new short[2];
        short[] coordArriver = new short[2];
        do {

            //récuperer coordonées de départ et d'arriver 
            this.afficherPlateau();
            coordDepart = getCoord("Rentrer les coordonées de votre pièce");
            coordArriver = getCoord("Rentrer les coordonées de la case ciblé");
            KTerminal.effacer();
            //test différentes possibilités
            if (PieceEtat.VIDE == this.plateau[ coordDepart[0] ][ coordDepart[1] ].getCouleur()) { 
                //Case choisi sans pièce
                System.out.println("Vous ne pouvez pas jouer une case vide");
            } else {
                if (couleur != this.plateau[ coordDepart[0] ][ coordDepart[1] ].getCouleur()){
                    //Pas au tour de la couleur la de jouer
                    System.out.println("Impossible c'est aux : "+couleurString+" de jouer");
                } else {
                    //test si les pièces de départ et arriver sont de même couleur ou non 
                    if (!this.plateau[ coordDepart[0] ][ coordDepart[1] ].isPlaceAcessible(
                        this.plateau[ coordArriver[0] ][ coordArriver[1] ])) System.out.println("Impossible de retirer une pièce de même couleur");
                    else {
                        //test si le coup est possible ou non par la pièce
                        if (! this.plateau[coordDepart[0]][coordDepart[1]].coupPossible(
                                this,coordDepart[0], coordDepart[1], coordArriver[0], coordArriver[1])) System.out.println("La piêce ne peut pas faire ca");
                        else break;
                        //si on arrive ici les conditions sont toutes bonnes et on break le while
                    }
                }
            }
        } while (true);
        //une fois ici le coup est valable et jouer
        this.deplacerPiece(coordDepart[0], coordDepart[1], coordArriver[0], coordArriver[1]);
    }

    /** fait derouler la partie */
    private void deroulementPartie(){
        resetTour();
        KTerminal.effacer();
        do{
            this.jouerCoup();
        } while (true);
    }

    /** incrémente le tour  */
    private void tourIncrementer(){
        this.tour++;
    }

    /** getter du tour */
    private int getTour(){
        return this.tour;
    }

    /** reset du tour pour une nouvelle partie */
    private void resetTour(){
        this.tour =-1;
    }

    /** récupere la couleur du joueur qui doit jouer */
    private short getCouleurTour(){
        tourIncrementer();
        if (getTour()%2 == 0) return PieceEtat.BLANC;
        return PieceEtat.NOIR;
    }
    public static void main(String[] args){
        JeuEchec jeuEchec = new JeuEchec();
        jeuEchec.toString();
    }
}
