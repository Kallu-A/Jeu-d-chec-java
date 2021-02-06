package terminal;

import java.util.InputMismatchException;
import java.util.Scanner;

public class KTerminal {
    
    static Scanner input = new Scanner(System.in);
    
    /** nettoie le terminal */
    public static void effacer() {  
        
        System.out.println(" ");
        System.out.print("\033[H\033[2J");  
        System.out.flush();   
        } 
    
    /** récupere un entier */
    static public int getValeur(String afficher){
        int val;
        do {
            try {
                System.out.println(afficher);
                System.out.print("--> ");
                val = input.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Recommencer, il faut une valeur numérique");
                input.next();
            }
        } while (true); 
        return val;
    }
    /** ferme le scanner  */
    static public void fermerScanner(){
        input.close();
    }
}
