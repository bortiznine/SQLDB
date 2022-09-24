import java.awt.*;
import java.io.File;

public class MyMainClass {
    public static void main(String[] args) {

        String name;
        float GPA;
        int age;
        String address;
        try {
            File file = new File("students.txt");
            if (!Desktop.isDesktopSupported()) {
                System.out.println("This is not supported by this platform");
            }
            Desktop desktop = Desktop.getDesktop();
            if (file.exists()) {
                desktop.open(file);
                System.out.println("File found");
            }
        } catch (Exception e) {
            System.out.println("Error accrued when searching file");
        }
    }
}
