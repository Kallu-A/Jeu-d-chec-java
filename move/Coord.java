package move;

import jeu.Dictionnaire;

/** coordonée composé de deux valeur */
public class Coord {

    public short ligne ,colonne ;
    static public final short VALEUR_INVALIDE = -1;
        static public Coord COORD_INVALIDE = new Coord();

    public Coord(short ligne, short colonne){
        this.ligne=ligne;
        this.colonne=colonne;
    }

    public Coord(){
        this.ligne = VALEUR_INVALIDE;
        this.colonne = VALEUR_INVALIDE;
    }

    /** met la coord au valeur d'un string */
    public void setStringToCoord(String stringCoord){
        int setLigne, setColonne;
        char setLigneChar = stringCoord.charAt(1);
        char setColonneChar = stringCoord.charAt(0);
        try {
            setLigne = Integer.parseInt(String.valueOf(setLigneChar));
            setColonne = Dictionnaire.dic.get(Character.toUpperCase(setColonneChar));
        } catch (NumberFormatException e){
            this.ligne = VALEUR_INVALIDE;
            this.colonne = VALEUR_INVALIDE;
            return;
        } catch (NullPointerException e){
            this.ligne = VALEUR_INVALIDE;
            this.colonne = VALEUR_INVALIDE;
            return;  
        }
        this.ligne = (short)( setLigne -1);
        this.colonne = (short)( setColonne );
    }
     
    public String toString(){
        return "Coord avec ligne ="+this.ligne + " colonne ="+this.colonne;
    }
}
