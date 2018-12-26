package game.Engine.Utils;

import java.io.File;
import java.nio.file.Path;

public class PathEx {
    public static String getFileName(Path path) {
        File file = new File(path.toString());
        String fullName = file.getName();
        return fullName.substring(0, fullName.lastIndexOf("."));
    }

    public static String getFileName(String fullName) {
        return fullName.substring(0, fullName.lastIndexOf("."));
    }

    public static String getExtName(Path path) {
        File file = new File(path.toString());
        String fullName = file.getName();
        return fullName.substring(fullName.lastIndexOf(".") + 1);
    }
}
