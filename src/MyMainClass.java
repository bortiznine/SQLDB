import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class MyMainClass {
    public static void main(String[] args) {

        int input=3;
        while (input!=0) {
            System.out.println("\nWelcome to the Food Service DB");
            System.out.println("Are you a entering a new Customer(1):\nLooking up information(2):\nExit (0)");
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter a value :");
            input = sc.nextInt();
            System.out.println("User input: " + input);
            switch (input) {
                case 1:
try {
    System.out.println("Let's insert a New Customer");
    System.out.println("Please enter name:");
    Scanner sc2 = new Scanner(System.in);
    String name = sc2.nextLine();
    System.out.println("User input: " + name);

    System.out.println("Please enter address:");
    Scanner sc3 = new Scanner(System.in);
    String address = sc3.nextLine();
    System.out.println("User input: " + address);

    System.out.println("Please enter phone number Format(XXX-XXX-XXXX):");
    Scanner sc4 = new Scanner(System.in);
    String phonenum = sc4.nextLine();
    System.out.println("User input: " + phonenum);

    System.out.println("Please enter dob Format(YYYY-MM-DD):");
    Scanner sc5 = new Scanner(System.in);
    String dob = (sc5.nextLine());
    System.out.println("User input: " + dob);

    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/FoodServices", "root", "AcabJet1509$");
    PreparedStatement p =con.prepareStatement("INSERT INTO Customer (Cname, Address,Phone_Number,DateOfBirth) values (?,?,?,?) ;");
    p.setString(1, name);
    p.setString(2,address);
    p.setString(3,phonenum);
    p.setDate(4,Date.valueOf(dob));

    p.execute();
    System.out.println("Insert Completed!\n");

        // print the results
    System.out.println("Customer Name"+" | " + "Address" +" | "+ "Phone #" +" | "+"Date Of Birth");
    System.out.println(name+" | " + address +" | "+ phonenum +" | "+dob);

    p.close();
    con.close();
    Thread.sleep(3000);



}
catch(Exception e){
    System.out.println(e.getMessage());
}
                    break;
                case 2:
                    System.out.println(
                            "Display the customers (by address and birthday) that ordered soda between 2010 and 2020. (1)" +
                            "\nDisplay in alphabetical order the names, addresses, and the average number of orders from restaurants that get supplied with white bread. (2)" +
                            "\nDisplay all Grocery stores and their addresses that have margarine and have supply request in 2022 (3)" +
                            "\nDisplay top 5 Grocery stores by orders (4)" +
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
                                ResultSet rs1 = statement.executeQuery("SELECT     C.Cname, C.DateOfBirth\n" +
                                        "FROM        Customer AS C, Orders AS O, Grocery_Store AS G\n" +
                                        "WHERE        G.Gid in (select G.Gid from Grocery_Store as G,Item as GI where G.Gid=GI.Gid and GI.item_Name='soda') AND (O.Order_Date BETWEEN '2010-01-01' AND '2020-12-31') AND (C.Custid = O.Custid AND O.Gid = G.Gid );\n;");
                                System.out.println("Customer Name | Date of Birth\n");
                                while (rs1.next()) {
                                    String Cname = rs1.getString("Cname");
                                    Date DateOfBirth = rs1.getDate("DateOfBirth");

                                    // print the results
                                    System.out.format("%s, %s\n", Cname, DateOfBirth);
                                }
                                statement.close();
                                con.close();
                                Thread.sleep(3000);
                                break;
                            case 2:
                                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/FoodServices", "root", "AcabJet1509$");
                                System.out.println("Connected");
                                statement = con.createStatement();
                                ResultSet rs2 = statement.executeQuery("SELECT R.Rname, R.Address, AVG(Sid) as avg    \n" +
                                        "FROM Restaurant AS R, Supplies AS S, Grocery_Store AS G, Item AS GI  \n" +
                                        "WHERE (GI.Item_Name= 'white bread') AND (R.Rid = S.Rid AND S.Gid = G.Gid AND G.Gid = GI.Gid) \n" +
                                        "ORDER BY R.Rname , R.Address ASC;   ");
                                System.out.println("Restaurant Name | Address | Average Orders\n");
                                while (rs2.next()) {

                                    String rname = rs2.getString("R.Rname");
                                    String address = rs2.getString("R.Address");
                                    int avgval = rs2.getInt("avg");


                                    // print the results
                                    System.out.format("%s,%s,%s\n",  rname, address,avgval);
                                }
                                statement.close();
                                con.close();
                                Thread.sleep(3000);
                                break;
                            case 3:
                                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/FoodServices", "root", "AcabJet1509$");
                                System.out.println("Connected");
                                statement = con.createStatement();
                                ResultSet rs3 = statement.executeQuery(" SELECT G.Gname, G.Address  FROM Grocery_Store AS G, Supplies AS S,Item AS GI\n" +
                                        "WHERE(GI.Item_Name = 'margarine') AND YEAR(S.Supply_Date) = 2019 AND (G.Gid = S.Gid  AND G.Gid = GI.Gid);   ");
                                System.out.println("Customer Name | Address\n");
                                while (rs3.next()) {
                                    String Gname = rs3.getString("G.Gname");
                                    String Gaddress = rs3.getString("G.Address");


                                    // print the results
                                    System.out.format("%s, %s\n", Gname, Gaddress );
                                }
                                statement.close();
                                con.close();
                                Thread.sleep(3000);
                                break;
                            case 4:
                                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/FoodServices", "root", "AcabJet1509$");
                                System.out.println("Connected");
                                statement = con.createStatement();
                                ResultSet rs4 = statement.executeQuery(" SELECT G.Gname FROM Grocery_Store AS G, Orders AS O \n" +
                                        "WHERE G.Gid = O.Gid GROUP BY G.Gname Order BY Count(O.Oid) DESC LIMIT 5;   ");
                                System.out.println("Grocery Store Name\n");
                                while (rs4.next()) {
                                    String Gname = rs4.getString("G.Gname");

                                    // print the results
                                    System.out.format("\n %s ", Gname );
                                }
                                statement.close();
                                con.close();
                                Thread.sleep(3000);
                                break;
                            case 5:
                                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/FoodServices", "root", "AcabJet1509$");
                                System.out.println("Connected");
                                PreparedStatement p =con.prepareStatement("SELECT COUNT( C.Cname) as total FROM Customer AS C, Orders AS O, Grocery_Store AS G, Item AS GI, Dines AS D, Restaurant AS R WHERE C.Custid=O.Custid " +
                                        "AND G.Gid=O.Gid AND G.Gid=GI.Gid AND GI.Item_Name='seasonal products'" +
                                        " AND C.Custid = D.Custid AND R.Rid = D.Rid AND R.Rname=?; ");
                                p.setString(1, "Sunny's Steakhouse");
                                ResultSet query=p.executeQuery();
                                System.out.println("Count of Name\n");
                                while (query.next()) {
                                    // print the results
                                    int countName = query.getInt("total");
                                    System.out.println(countName);
                                }
                                p.close();
                                con.close();
                                Thread.sleep(3000);
                                break;
                            case 6:
                                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/FoodServices", "root", "AcabJet1509$");
                                System.out.println("Connected");
                                statement = con.createStatement();
                                ResultSet rs6 = statement.executeQuery("SELECT C.Cname, C.DateOfBirth FROM Customer AS C, Dines AS D, Restaurant AS R, Dish AS DI " +
                                        "WHERE C.Custid = D.Custid AND R.Rid = D.Rid AND R.Rid = DI.Rid AND DI.Dish_Name = 'Cobb salad' ORDER BY Cname DESC, C.DateOfBirth DESC ;");
                                System.out.println("Customer Name  |  Date of Birth\n");
                                while (rs6.next()) {
                                    String cName = rs6.getString("C.Cname");
                                    Date dob = rs6.getDate("C.DateOfBirth");


                                    // print the results
                                    System.out.println( cName+" | " + dob);
                                }
                                statement.close();
                                con.close();
                                Thread.sleep(3000);
                                break;
                            case 7:
                                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/FoodServices", "root", "AcabJet1509$");
                                System.out.println("Connected");
                                statement = con.createStatement();
                                ResultSet rs7 = statement.executeQuery("SELECT S.Supply_Date,R.Rname FROM Supplies AS S, Restaurant AS R WHERE S.Rid=R.Rid AND DATE(Supply_Date) " +
                                        "in ('2020-01-01', '2020-01-17','2020-02-12','2020-02-14','2020-02/21','2020-03-17','2020-04-13','2020-05-30', '2020-07-04', '2020-09-05', '2020-11-11','2020-11-24','2020-12-25'); ");
                                System.out.println("Supply Date | Restaurant Name\n");
                                while (rs7.next()) {
                                    Date supply_date = rs7.getDate("S.Supply_Date");
                                    String rname = rs7.getString("R.Rname");


                                    // print the results
                                    System.out.format("%s, %s\n", supply_date, rname);
                                }
                                statement.close();
                                con.close();
                                Thread.sleep(3000);
                                break;
                            case 8:
                                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/FoodServices", "root", "AcabJet1509$");
                                System.out.println("Connected");
                                statement = con.createStatement();
                                ResultSet rs8 = statement.executeQuery("SELECT C.Cname, C.DateOfBirth FROM Customer AS C, Delivers AS D1, Dines AS D2 WHERE C.Custid=D1.Custid and C.Custid!=D2.Custid; ");
                                System.out.println("Customer Name | Date of Birth\n");
                                while (rs8.next()) {
                                    String cName = rs8.getString("C.Cname");
                                    Date dob = rs8.getDate("C.DateOfBirth");


                                    // print the results
                                    System.out.format("%s, %s\n", cName, dob);
                                }
                                statement.close();
                                con.close();
                                Thread.sleep(3000);
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
