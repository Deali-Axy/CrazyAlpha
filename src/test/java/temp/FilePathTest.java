package temp;

import java.io.File;
import java.io.IOException;

public class FilePathTest {
    public static void main(String... args) {
        try {
            File file = new File("resources/images/test.txt");
            boolean sucess = file.createNewFile();
            System.out.println(sucess);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
