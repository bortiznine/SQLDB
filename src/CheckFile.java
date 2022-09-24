//import java.awt.*;
//import java.io.File;
//
//public class CheckFile {
//    boolean checkFile(boolean fileChecker){
//        try {
//            fileChecker=false;
//            File file = new File("students.txt");
//            if (!Desktop.isDesktopSupported()) {
//                System.out.println("This is not supported by this platform");
//            }
//            Desktop desktop = Desktop.getDesktop();
//            if (file.exists()) {
//                desktop.open(file);
//                System.out.println("File found");
//                return fileChecker=true;
//            }
//        } catch (Exception e) {
//            System.out.println("Error accrued when searching file");
//        }
//        return fileChecker;
//    }
//    }
//}
