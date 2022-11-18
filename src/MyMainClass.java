import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class MyMainClass {
    public static void main(String[] args) {

        int input=3;
        while (input!=0) {
            System.out.println("Food Service DB");
            System.out.println("Are you a entering a new Customer(1):\nLooking up information(2):\nExit (0)");
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter a value :");
            input = sc.nextInt();
            System.out.println("User input: " + input);
            switch (input) {
                case 1:
                    break;
                case 2:
                    break;
                case 0:
                    System.out.println("Exiting! GoodBye!");
                    input=0;
            }

        }
            }
}
