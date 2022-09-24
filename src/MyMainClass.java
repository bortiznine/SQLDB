import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MyMainClass {
    public static void main(String[] args) {

        String name;
        float GPA;
        int age;
        String address;
//        CheckFile checkFile = new CheckFile();


        try {
            FileInputStream file = new FileInputStream("students.txt");
            System.out.println("Student Info:");
            int counter=0;
            while ((counter= file.read())!=-1){
                System.out.print((char) counter);
            }
        } catch (Exception e) {
            System.out.println("No file exist!");
        }

    }
}
