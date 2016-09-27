/*
Names: Bryan Hewing
       Thomas Robert
       Andrew Wright
Date: 9/14/16
File:Pizza.java
 */
package pizza;

import java.util.Scanner;

public class Pizza {

    public static void main(String[] args) {
        //Scanner
        Scanner input = new Scanner(System.in);

        //Constants
        final int MAX_TOPPINGS = 8;
        final int TOTAL_TOPPINGS = 15;
        final int[] MAX_Q_TOPPINGS = {1, 1, 2, 2, 2, 2, 4, 2, 2, 4, 4, 1, 4, 4, 2};
        final String[] NAMES = {"Crust - regular",
                                "Crust - gluten-free",
                                "Red Sauce",
                                "Pizza Cheese",
                                "Diced Onion",
                                "Diced green pepper",
                                "Pepperoni",
                                "Sliced mushroom",
                                "Diced jalapenos",
                                "Sardines",
                                "Pineapple Chunks",
                                "Tofu",
                                "Ham Chunks",
                                "Dry red pepper",
                                "Dried basil"};
        final String[] MEASURE = {"each",
                                  "each",
                                  "1/4 cup",
                                  "1/4 cup",
                                  "1/8 cup",
                                  "1/8 cup",
                                  "2 pieces",
                                  "1/8 cup",
                                  "1/8 cup",
                                  "each",
                                  "2 pieces",
                                  "1/4 cup",
                                  "4 pieces",
                                  "generous sprinkle",
                                  "generous sprinkle"};
        final int[] GROSS = {8, 9, 10, 11};
        final int GROSS_MAX = 4;
        
        //Variables
        int count = 0;
        int topping;
        boolean again = true;
        int amount = 0;
        
        //Array
        int[] haveTopping = new int[TOTAL_TOPPINGS];
            for (int i=0; i < TOTAL_TOPPINGS; i++)
                haveTopping[i] = 0;
        
        //Printing Menu
        System.out.println("Item #\tCategory\t\tMeasure\t\tMaximum Quantity");
        for (int i =0; i < TOTAL_TOPPINGS; i++)
        {
            switch (i) 
            {
                case 0:
                    System.out.print("1a");
                    break;
                case 1:
                    System.out.print("1b");
                    break;
                default:
                    System.out.print(i);
                    break;
            }
            System.out.print("\t" + NAMES[i] + "\t");
            if (i != 1 && i !=5 && i!=10)
                System.out.print("\t");
            if (i == 11)
                System.out.print("\t");
            System.out.print(MEASURE[i]);
            if (i!=6 && i!=10 && i<12)
                System.out.print("\t\t\t");
            else if (i<13)
                System.out.print("\t\t");
            else
                System.out.print("\t");
            System.out.println(MAX_Q_TOPPINGS[i]);
        }
        
        //Getting Order
        
        //required crust
        do
            {
                System.out.print("\nSelect type of crust (1a/1b): ");
                topping = choiceToInt(input.next());
                if (topping > 1)
                {
                    System.out.println("Invalid Option!");
                }
                                
            } while (topping > 1);
        count++;
        haveTopping[topping]+=1;
        
        //automatically adding cheese and sauce
        System.out.println(MEASURE[2] + " of " + NAMES[2] + " is being added but you can always add more.");
        haveTopping[2]+=1;
        count++;
        System.out.println(MEASURE[3] + " of " + NAMES[3] + " is being added but you can always add more.");
        haveTopping[3]+=1;
        count++;
        
        while (count < MAX_TOPPINGS && again)
        {
            //Getting Topping Choice
            //If already have max then ask again
            
            do
            {
                System.out.print("\nEnter topping: ");
                topping = choiceToInt(input.next());
                if (topping>=TOTAL_TOPPINGS)
                {
                    System.out.println("Invalid Option");
                }
                else if (haveTopping[topping] == MAX_Q_TOPPINGS[topping])
                    System.out.println("You have the maximum amount of " + NAMES[topping]);
                else if (topping <2)
                    System.out.println("You already have a crust!");
                else if (gross(topping, GROSS_MAX, GROSS))
                {
                    System.out.println("Unfortunaltey we are out " + NAMES[topping]);
                }
                
            } while (topping>=TOTAL_TOPPINGS ||haveTopping[topping] == MAX_Q_TOPPINGS[topping] || topping<2 || (gross(topping, GROSS_MAX, GROSS)));
            
            //Getting Amount of Topping
            //If amount want exceeds can have ask again
            do
            {
                System.out.println("You already have " + haveTopping[topping] + " " +MEASURE[topping] + " of "+ NAMES[topping] + ".");
                System.out.println("You can have " + (MAX_Q_TOPPINGS[topping] - haveTopping[topping]) + " more.");
                System.out.print("Enter amount of " + NAMES[topping]+": ");
                amount = input.nextInt();
                if (haveTopping[topping]+amount > MAX_Q_TOPPINGS[topping])
                    System.out.println("\nYou can't have more than " + MAX_Q_TOPPINGS[topping] + " " + NAMES[topping]);
            } while(haveTopping[topping]+amount > MAX_Q_TOPPINGS[topping]);
            haveTopping[topping]+=amount;
            count++;
            //Asking if to add more
            if (count < MAX_TOPPINGS)
            {
                System.out.print("Add More? (true/false): ");
                again = input.nextBoolean();
            }
        }
        
        //Printing Results
        System.out.println();
        System.out.println("Ingredients:");
        for (int i =0; i<TOTAL_TOPPINGS; i++)
        {
            if (haveTopping[i]!=0)
            {
                System.out.println(haveTopping[i] + " " + MEASURE[i] + "\t" + NAMES[i]);
            }
        }
        System.out.println("\n1. Place down crust.");
        System.out.println("2. Evenly spread Red Sauce on crust.");
        System.out.println("3. Evenly spread Pizza Cheese on crust.");
        System.out.println("4. Evenly spread out remaining Ingredients on top of Pizza Cheese.");
        System.out.println("5. Pizza is to be appropriately cooked in oven until crust is cooked and topping is fully warmed.");
        System.out.println("6. Serve and Enjoy!");
        
        
        
        
        
    }
    
    private static boolean gross(int topping, final int grossSize, int[] gross)
    {
        boolean foundGross = false;
        for (int i = 0; i <grossSize; i++)
        {
            if (gross[i] == topping)
                foundGross = true;
        }
        return foundGross;
            
    }
    
    private static int choiceToInt(String choice)
    {
        int choiceInt;
        if (choice.equalsIgnoreCase("1a"))
            choiceInt = 0;
        else if (choice.equalsIgnoreCase("1b"))
            choiceInt = 1;
        else
            choiceInt = Integer.parseInt(choice);
        return choiceInt;
    }
    
}
