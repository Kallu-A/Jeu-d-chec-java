package jeu;

import java.util.HashMap;

/** Classe dictionnaire contient la traducion de colonne a entier  */
public class Dictionnaire {
    
    public static HashMap<Character, Integer> dic = creationMap();

    private static HashMap<Character, Integer> creationMap(){
        HashMap<Character, Integer> hmap = new HashMap<Character, Integer>();
        hmap.put('A', 0);
        hmap.put('B', 1);
        hmap.put('C', 2);
        hmap.put('D', 3);
        hmap.put('E', 4);
        hmap.put('F', 5);
        hmap.put('G', 6);
        hmap.put('H', 7);
        return hmap;
    }

}
