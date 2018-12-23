package temp;

import game.Engine.Utils.PathEx;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathTest {
    public static void main(String... args) {
        Path path = Paths.get("resources/map");
        try (DirectoryStream<Path> children = Files.newDirectoryStream(path)) {
            for (Path child : children) {
                File file = new File(child.toString());
                if (file.isFile()) {
                    System.out.println(child.toString());
                    System.out.println(child.getFileName());
                    String fullName = file.getName();
                    String fileName = fullName.substring(0, fullName.lastIndexOf("."));
                    System.out.println(fileName);
                    System.out.println(PathEx.getFileName(child));
                    System.out.println(PathEx.getExtName(child));
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
