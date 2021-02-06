package jeu;

import move.Coord;
import move.Move;
import piece.Piece;
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
    private Coord getCoord(String afficher){
        boolean valide;
        Coord coord = new Coord();
        do {
            System.out.println(afficher);
            valide = true;
            coord.ligne = (short) (KTerminal.getValeur("Rentrer la ligne de votre case") -1);
            coord.colonne = (short) (KTerminal.getValeur("Rentrer la colonne de votre case") -1);
            //test validité
            if (coord.ligne>=DIMENSION || coord.ligne<0) {  
                System.out.println("Erreur : la ligne de la case est incorrecte"); 
                valide = false;
            }    
            if (coord.colonne>=DIMENSION || coord.colonne<0) {
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
        Coord depart = new Coord();
        Coord arriver = new Coord();
        do {

            //récuperer coordonées de départ et d'arriver 
            this.afficherPlateau();
            depart = getCoord("Rentrer les coordonées de votre pièce");
            arriver = getCoord("Rentrer les coordonées de la case ciblé");
            KTerminal.effacer();
            //test différentes possibilités
            if (PieceEtat.VIDE == this.plateau[ depart.ligne ][ depart.colonne ].getCouleur()) { 
                //Case choisi sans pièce
                System.out.println("Vous ne pouvez pas jouer une case vide");
            } else {
                if (couleur != this.plateau[ depart.ligne ][ depart.colonne ].getCouleur()){
                    //Pas au tour de la couleur la de jouer
                    System.out.println("Impossible c'est aux : "+couleurString+" de jouer");
                } else {
                    //test si les pièces de départ et arriver sont de même couleur ou non 
                    if (!this.plateau[ depart.ligne ][ depart.colonne ].isPlaceAcessible(
                        this.plateau[ arriver.ligne ][ arriver.colonne ])) System.out.println("Impossible de retirer une pièce de même couleur");
                    else {
                        //test si le coup est possible ou non par la pièce
                        if (! this.plateau[depart.ligne][depart.colonne].coupPossible( this,
                             new Move(depart, arriver) ) )  System.out.println("La piêce ne peut pas faire ca");
                        else  {
                            if (!isEchec(new Move(depart, arriver), couleur)) break;
                            else System.out.println("Votre roi ne peut pas être échec a la fin de votre coup");
                        }

                        //si on arrive ici les conditions sont toutes bonnes et on break le while
                    }
                }
            }
        } while (true);
        //une fois ici le coup est valable et se fait
        this.deplacerPiece(depart, arriver);
    }

    /** vérifie si le roi est en echec avec un coup*/
    private boolean isEchec(Move coup,short couleur){
        Coord depart = coup.to;
        Coord arriver = coup.from;
        boolean echec;
        Piece tampon = this.plateau[arriver.ligne][arriver.colonne] ;
        //fait le coup
        this.plateau[arriver.ligne][arriver.colonne] = this.plateau[depart.ligne][depart.colonne];
        this.plateau[depart.ligne][depart.colonne] = new Piece();
        //test si le roi echec
        if (isEchec(couleur)) echec = true;
        else echec = false;
        //defait le coup
        this.plateau[depart.ligne][depart.colonne] = this.plateau[arriver.ligne][arriver.colonne];
        this.plateau[arriver.ligne][arriver.colonne] = tampon;
        return echec;

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
