package piece;

/** un deplacement constitué de deux coordonées  */
public class Move {

    private short ligneDep;
    private short colonneDep;
    private short ligneAr;
    private short colonneAr;

    public Move(short ligneDep, short colonneDep, short ligneAr, short colonneAr){
        this.ligneDep = ligneDep;
        this.colonneDep = colonneDep;
        this.ligneAr = ligneAr;
        this.colonneAr = colonneAr;
    }

    /** getter de ligneDep */
    public short getLigneDep(){
        return this.ligneDep;
    }

    /** getter de colonneDep */
    public short getColonneDep(){
        return this.colonneDep;
    }

    /** getter de ligneAr*/
    public short getLigneAr(){
        return this.ligneAr;
    }

    /** getter de colonneAr */
    public short getColonneAr(){
        return this.colonneAr;
    }

}
