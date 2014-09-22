/**
 * @author Olof Morra
 * @author Jan Heemstra
 * Homework assignment, with comments for explanation. 
 * This program solves polynomial equations using a numeric approach.
 * 
 * Input:
 * First you need to input coefficient 0 to n as doubles. 
 * Then, the string "in", and then the two bounds of the interval, also as doubles:
 * 
 * [X1] [X2] [X3] (...) [Xn] in [LeftBound] [RightBound]
 * 
 * Output:
 * If the interval is valid, the program will output "root: " and then the solution to the polynomial:
 * 
 * root: [Solution]
 * 
 * If the interval is not valid, the program will output:
 * 
 * unusable interval: [LeftBound] [RightBound]
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Interpol {
    final static double interval = 0.000001;//Precision of algorithm
    static Scanner scanner = new Scanner(System.in);//Add scanner object

    static ArrayList<Double> coefficients;//Coefficient that make up polynomial
    static double x1;//Left part of interval
    static double x2;//Right part of interval

    static public void main(String[] Args) {
        coefficients = new ArrayList<Double>();
        query();//Request input
        findClosest();//Compute output and print to screen
    }
    
    private static void query() {
        queryCoefficient();
        queryInterval();
    }
    
    private static void queryCoefficient() {
       while(scanner.hasNextDouble())
       {
           coefficients.add(scanner.nextDouble());//Query next coefficient
       }
    }
    
    private static void queryInterval() {
        if(scanner.next().trim().equals("in")) {
            x1 = scanner.nextDouble();//Query lower bound of domain
            x2 = scanner.nextDouble();//Query upper bound of domain
        }
    }
    
    private static void findClosest() {
        double fX1 = f(x1);//Compute f(x1) to get bound of reach
        double fX2 = f(x2);//Compute f(x2) to get bound of reach
        
      //Intercept intervals with same sign
        if ((fX1<0&&fX2<0)||(fX1>0&&fX2>0)) {
            System.out.print("unusable interval: ");
            System.out.print(round(fX1));
            System.out.print(", ");
            System.out.println(round(fX2));
            return;
        }
        
        System.out.print("root: ");
                
        while (true) {            
            double fMiddle = f((x1+x2)/2);
            
            boolean down = fX1>fX2;//Check if second point is below first
            
            if (Math.abs(f(x1))<interval) {
                //We are very close to 0. We'll neglect the difference and return x1 as the root.
                System.out.println(round(x1));
                break;
                //Now check if (x1+x2)/2 is left or right from the root.
            }else if ((fMiddle>0)!=down) {
                //We are right from the root. Therefore, we decrease our right bound.
                x2 = (x1+x2)/2;
                fX2 = f(x2);
            } else if ((fMiddle<0)!=down) {
                //We are left from the root. Therefore we decrease our left bound.
                x1 = (x1+x2)/2;
                fX1 = f(x1);
            }else {
                //if fMiddle!<0 && fMiddle!>0, fMiddle obviously is 0!
                System.out.println((x1+x2)/2);
                break;
            }
        }
    }
    
    private static double round(double x) {
        return ((double)Math.round(x*1000))/1000;//Round number to 3 decimals and return value
    }
    
    private static double f(double x) {
        // The first value does not have to be multiplied by x
        double f = coefficients.get(0);
        double poweredX = x;
        
        for(int i = 1; i < coefficients.size(); i++) {
            // Add the coefficient times the powered x to the function
            f = f + coefficients.get(i)*poweredX;
            
            // For the next coefficient
            poweredX = poweredX*x;
        }
        
        return f;
    }
}