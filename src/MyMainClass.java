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
            System.out.println("Welcome to the Food Service DB");
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
                            "Query1 (1):" +
                            "\nQuery2 (2):" +
                            "\nQuery3 (3)" +
                            "\nQuery4 (4)" +
                            "\nGet a count of all customers by name that only ordered seasonal products and dine at Baker’s Square. (5)" +
                            "\nDisplay the names and birthdays of customers in descending order that ordered Cobb salad at Olive Garden. (6)" +
                            "\nShow supply requests by date and restaurant name of all restaurants that ordered on US holidays in 2020. (7)" +
                            "\nIdentify customers by name, and date of birth that have no dine orders but have deliveries. (8)" +
                            "\nReturn (0)");
                    Scanner sc2 = new Scanner(System.in);
                    System.out.println("Enter a value :");
                    int input2 = sc2.nextInt();
                    System.out.println("User input: " + input2);
                    try {
                        switch(input2) {

                            case 1:
                                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/FoodServices", "root", "AcabJet1509$");
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
                                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/FoodServices", "root", "AcabJet1509$");
                                System.out.println("Connected");
                                statement = con.createStatement();
                                PreparedStatement p =con.prepareStatement("SELECT COUNT( C.Cname) FROM Customer AS C, Orders AS O, Grocery_Store AS G, Item AS GI, Dines AS D, Restaurant AS R WHERE C.Custid=O.Custid " +
                                        "AND G.Gid=O.Gid AND G.Gid=GI.Gid AND GI.Item_Name='seasonal products'" +
                                        " AND C.Custid = D.Custid AND R.Rid = D.Rid AND R.Rname=?; ");
                                p.setString(1, "Sunny's Steakhouse");
                                ResultSet query=p.executeQuery();
                                while (query.next()) {
                                    // print the results
                                    System.out.println(query.getInt("COUNT(C.name)"));
                                }
                                break;
                            case 6:
                                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/FoodServices", "root", "AcabJet1509$");
                                System.out.println("Connected");
                                statement = con.createStatement();
                                ResultSet rs6 = statement.executeQuery("SELECT C.Cname, C.DateOfBirth FROM Customer AS C, Dines AS D, Restaurant AS R, Dish AS DI " +
                                        "WHERE C.Custid = D.Custid AND R.Rid = D.Rid AND R.Rid = DI.Rid AND DI.Dish_Name = 'Cobb salad' ORDER BY Cname DESC, C.DateOfBirth DESC ;");

                                while (rs6.next()) {
                                    String cName = rs6.getString("C.Cname");
                                    Date dob = rs6.getDate("C.DateOfBirth");


                                    // print the results
                                    System.out.println( cName+" | " + dob);
                                }
                                statement.close();
                                con.close();
                                break;
                            case 7:
                                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/FoodServices", "root", "AcabJet1509$");
                                System.out.println("Connected");
                                statement = con.createStatement();
                                ResultSet rs7 = statement.executeQuery("SELECT S.Supply_Date,R.Rname FROM Supplies AS S, Restaurant AS R WHERE S.Rid=R.Rid AND DATE(Supply_Date) " +
                                        "in ('2020-01-01', '2020-01-17','2020-02-12','2020-02-14','2020-02/21','2020-03-17','2020-04-13','2020-05-30', '2020-07-04', '2020-09-05', '2020-11-11','2020-11-24','2020-12-25'); ");
                                statement.close();
                                while (rs7.next()) {
                                    Date supply_date = rs7.getDate("S.Supply_Date");
                                    String rname = rs7.getString("R.Rname");


                                    // print the results
                                    System.out.format("%s, %s\n", supply_date, rname);
                                }
                                break;
                            case 8:
                                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/FoodServices", "root", "AcabJet1509$");
                                System.out.println("Connected");
                                statement = con.createStatement();
                                ResultSet rs8 = statement.executeQuery("SELECT C.Cname, C.DateOfBirth FROM Customer AS C, Delivers AS D1, Dines AS D2 WHERE C.Custid=D1.Custid and C.Custid!=D2.Custid; ");
                                statement.close();
                                while (rs8.next()) {
                                    String cName = rs8.getString("C.Cname");
                                    Date dob = rs8.getDate("C.DateOfBirth");


                                    // print the results
                                    System.out.format("%s, %s\n", cName, dob);
                                }
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
