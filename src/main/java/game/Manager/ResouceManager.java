package game.Manager;

import game.Engine.Annotation.GameManager;
import game.Engine.Utils.PathEx;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.text.Font;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

@GameManager
public class ResouceManager {
    private Logger logger = LogManager.getLogger(ResouceManager.class);

    private HashMap<String, String> fonts = new HashMap<>();
    private HashMap<String, String> images = new HashMap<>();
    private HashMap<String, String> controls = new HashMap<>();
    private HashMap<String, String> music = new HashMap<>();

    public ResouceManager() {
        loadResources("fonts", fonts);
        loadResources("images", images);
        loadResources("controls", controls);
        loadResources("music", music);
    }

    private void loadResources(String resourceType, HashMap<String, String> container) {
        URL url = getClass().getClassLoader().getResource(resourceType);
        if (url == null) {
            logger.error("{} resources is not exist!", resourceType);
            return;
        }
        Path path = Paths.get(url.getPath());
        try (DirectoryStream<Path> children = Files.newDirectoryStream(path)) {
            for (Path child : children) {
                container.put(PathEx.getFileName(child), resourceType + "/" + child.getFileName().toString());
            }
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }

    private String getResource(String resourceType, String resourceName) {
        switch (resourceType) {
            case "fonts":
                if (fonts.containsKey(resourceName))
                    return fonts.get(resourceName);
                break;
            case "images":
                if (images.containsKey(resourceName))
                    return images.get(resourceName);
                break;
            case "controls":
                if (controls.containsKey(resourceName))
                    return controls.get(resourceName);
                break;
            default:
                logger.error("Resource Type {] is not exist!", resourceType);
        }
        return null;
    }

    public String[] getAllMaps() {
        URL mapURL = getClass().getClassLoader().getResource("maps");
        if (mapURL == null) {
            logger.error("map path is not exist!");
            return null;
        }
        Path path = Paths.get(mapURL.getPath());
        ArrayList<String> mapList = new ArrayList<>();
        try (DirectoryStream<Path> children = Files.newDirectoryStream(path)) {
            for (Path child : children) {
                mapList.add(child.toString());
            }
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
        return mapList.toArray(new String[0]);
    }

    public Font getFont(String fontFamily, double fontSize) {
        if (fonts.containsKey(fontFamily)) {
            String fontPath = fonts.get(fontFamily);
            InputStream stream = getClass().getClassLoader().getResourceAsStream(fontPath);
            return Font.loadFont(stream, fontSize);
        } else {
            logger.error("font {} is not exist!", fontFamily);
            return Font.getDefault();
        }
    }

    public Image getImage(String imageName) {
        if (images.containsKey(imageName)) {
            String imagePath = images.get(imageName);
            InputStream stream = getClass().getClassLoader().getResourceAsStream(imagePath);
            if (stream == null) {
                logger.error("create InputStream {} failed.", imagePath);
                return null;
            }
            return new Image(stream);
        } else {
            logger.error("image {} is not exist!", imageName);
            return null;
        }
    }

    public Image getControl(String controlName) {
        if (controls.containsKey(controlName)) {
            String controlPath = controls.get(controlName);
            InputStream stream = getClass().getClassLoader().getResourceAsStream(controlPath);
            if (stream == null) {
                logger.error("create InputStream {} failed.", controlPath);
                return null;
            }
            return new Image(stream);
        } else {
            logger.error("control {} is not exist!", controlName);
            return null;
        }
    }

    public Media getMusic(String musicName) {
        if (music.containsKey(musicName)) {
            String musicPath = music.get(musicName);
//            InputStream stream=getClass().getClassLoader().getResourceAsStream(musicPath);
            return new Media("file:" + musicPath);
        } else
            return null;
    }

    private String getFontPath(String fontName) {
        if (fonts.containsKey(fontName))
            return "file:" + fonts.get(fontName);
        else {
            logger.error("font {} is not exist!", fontName);
            return null;
        }
    }
}
