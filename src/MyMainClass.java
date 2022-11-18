import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
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
                    System.out.println(
                            "Query1(1):" +
                            "\nQuery2(2):" +
                            "\nQuery3(3)" +
                            "\nQuery4(4)" +
                            "\nQuery3(5)" +
                            "\nQuery3(6)" +
                            "\nQuery3(7)" +
                            "\nQuery3(8)" +
                            "\nReturn(0)");
                    Scanner sc2 = new Scanner(System.in);
                    System.out.println("Enter a value :");
                    int input2 = sc2.nextInt();
                    System.out.println("User input: " + input2);
                    try {
                        switch(input2) {

                            case 1:
                                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MyCompany", "root", "LuxSux-2021");
                                System.out.println("Connected");
                                Statement statement = con.createStatement();
                                ResultSet rs = statement.executeQuery("select * from customer;");
                                statement.close();
                                while (rs.next()) {
                                    int id = rs.getInt("id");
                                    String firstName = rs.getString("first_name");
                                    String lastName = rs.getString("last_name");
                                    Date dateCreated = rs.getDate("date_created");
                                    boolean isAdmin = rs.getBoolean("is_admin");
                                    int numPoints = rs.getInt("num_points");

                                    // print the results
                                    System.out.format("%s, %s, %s, %s, %s, %s\n", id, firstName, lastName, dateCreated, isAdmin, numPoints);
                                }
                                break;
                            case 2:
                                break;
                            case 3:
                                break;
                            case 4:
                                break;
                            case 5:
                                break;
                            case 6:
                                break;
                            case 7:
                                break;
                            case 8:
                                break;
                            case 0:
                                System.out.println("Returning to Main Menu!");
                                Thread.sleep(5000);
                                break;
                        }

                    }
                    catch(Exception e){
                        System.out.println(e);
                    }
                    break;
                case 0:
                    System.out.println("Exiting! GoodBye!");
                    input=0;
            }

        }
    }
}
