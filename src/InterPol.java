/**
 * @author Olof Morra
 * @author Jan Heemstra
 * Homework assignment, with comments for explanation.
 */


package interpol;

import java.util.Arrays;
import java.util.Scanner;

public class Interpol {
    final static double INTERVAL = 0.000001;
    static Scanner Scanner = new Scanner(System.in);//Add scanner object
    static double[] Coefficients; 
    static double x1;
    static double x2;
    
        
    static public void main(String[] Args) {
        query();
    }
    
    private static void query() {
        queryFactors();
        queryInterval();
    }
    
    private static void queryFactors() {
       int i = 0;
       while(Scanner.hasNextDouble())
       {
            Coefficients[i] = Scanner.nextDouble();
            i++;
       }    
    }
    
    private static void queryInterval() {
        if(Scanner.next().trim().equals("in")) {
            x1 = Scanner.nextDouble();
            x2 = Scanner.nextDouble();
        }
    }
    
    private static double f(double x) {
        // The first value does not have to be multiplied by x
        double f = Coefficients[0];
        double PoweredX = x;
        
        for(int i = 1; i < Coefficients.length; i++) {
            // Add the coefficient times the powered x to the function
            f = f + Coefficients[i]*PoweredX;
            
            // For the next coefficient;
            PoweredX = PoweredX*x;
        }
        
        return f;
    }

}