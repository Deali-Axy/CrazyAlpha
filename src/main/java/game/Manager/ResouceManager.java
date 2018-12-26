package game.Manager;

import game.Engine.Annotation.GameManager;
import game.Engine.Utils.PathEx;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.text.Font;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

@GameManager
public class ResouceManager {
    private Logger logger = LogManager.getLogger(ResouceManager.class);

    private HashMap<String, String> fonts = new HashMap<>();
    private HashMap<String, String> images = new HashMap<>();
    private HashMap<String, String> controls = new HashMap<>();
    private HashMap<String, String> media = new HashMap<>();
    private HashMap<String, String> maps = new HashMap<>();

    private boolean inJar = false;
    private String jarPath;

    public ResouceManager() {
        jarPath = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        if (jarPath.endsWith(".jar")) {
            inJar = true;
            logger.info("running in jar");
        } else {
            inJar = false;
            logger.debug("不在jar包里面");
        }

        loadResources("fonts", fonts);
        loadResources("images", images);
        loadResources("controls", controls);
        loadResources("media", media);
        loadResources("maps", maps);
    }

    private void loadResources(String resourceType, HashMap<String, String> container) {
        if (inJar) {
            loadResourcesFromInner(resourceType, container);
        } else {
            loadResourcesFromOuter(resourceType, container);
        }
    }

    /**
     * 从jar文件内部读取资源
     *
     * @param resourceType
     * @param container
     */
    private void loadResourcesFromInner(String resourceType, HashMap<String, String> container) {
        try {
            String path = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
            JarFile localJarFile = new JarFile(new File(path));

            Enumeration<JarEntry> entries = localJarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry jarEntry = entries.nextElement();
                String innerPath = jarEntry.getName();
                if (innerPath.startsWith(resourceType) && !innerPath.endsWith("/")) {
                    File resFile = new File(innerPath);
                    container.put(PathEx.getFileName(resFile.getName()), innerPath);
                }
            }
        } catch (IOException ex) {
            logger.error("loadResourcesFromInner error: {}", ex.getMessage());
        }
    }

    /**
     * 从外部的资源文件夹读取资源
     *
     * @param resourceType
     * @param container
     */
    private void loadResourcesFromOuter(String resourceType, HashMap<String, String> container) {
        URL url = getClass().getClassLoader().getResource(resourceType);
        if (url == null) {
            logger.error("{} resources is not exist!", resourceType);
            return;
        }

        try {
            File resourceDir = new File(url.toURI());
            File[] resources = resourceDir.listFiles();
            if (resources == null) {
                logger.error("there is no resource in resource type '{}'", resourceType);
                return;
            }
            for (File res : resources) {
                container.put(PathEx.getFileName(res.getName()), resourceType + "/" + res.getName());
            }
        } catch (URISyntaxException ex) {
            logger.error("load resources error: {}", ex.getMessage());
        }
    }

    /**
     * 获取资源文件的相对路径
     *
     * @param resourceType 资源类型
     * @param resourceName 资源名称
     * @return 返回资源文件的相对路径
     */
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

    /**
     * 获取所有地图
     *
     * @return 地图相对路径数组
     */
    @Deprecated
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
            logger.error("get all maps error: {}", ex.getMessage());
        }
        return mapList.toArray(new String[0]);
    }

    /**
     * 获取所有地图的图像
     *
     * @return Image类型的地图
     */
    public Image[] getAllMapsImage() {
        ArrayList<Image> images = new ArrayList<>();
        for (String imagePath : maps.values()) {
            InputStream stream = getResourceStream(imagePath);
            if (stream == null) {
                logger.error("create InputStream {} failed.", imagePath);
                return null;
            }
            images.add(new Image(stream));
        }
        return images.toArray(new Image[0]);
    }

    public InputStream getResourceStream(String resourcePath) {
        InputStream stream = getClass().getClassLoader().getResourceAsStream(resourcePath);
        if (stream == null) {
            logger.error("create InputStream {} failed.", resourcePath);
            return null;
        }
        return stream;
    }

    public Font getFont(String fontFamily, double fontSize) {
        if (fonts.containsKey(fontFamily)) {
            String fontPath = fonts.get(fontFamily);
            InputStream stream = getResourceStream(fontPath);
            if (stream != null)
                return Font.loadFont(stream, fontSize);
        } else {
            logger.error("font {} is not exist!", fontFamily);
        }
        return Font.getDefault();
    }

    public Image getImage(String imageName) {
        if (images.containsKey(imageName)) {
            String imagePath = images.get(imageName);
            InputStream stream = getResourceStream(imagePath);
            if (stream != null)
                return new Image(stream);
        } else {
            logger.error("image {} is not exist!", imageName);
        }
        return null;
    }

    public Image getControl(String controlName) {
        if (controls.containsKey(controlName)) {
            String controlPath = controls.get(controlName);
            InputStream stream = getResourceStream(controlPath);
            if (stream != null)
                return new Image(stream);
        } else {
            logger.error("control {} is not exist!", controlName);
        }
        return null;
    }

    /**
     * 获取音频资源
     *
     * @param musicName 名称
     * @return Media对象
     */
    public Media getMedia(String musicName) {
        if (media.containsKey(musicName)) {
            String relativePath = media.get(musicName);
            URL musicRes = getClass().getClassLoader().getResource(relativePath);
            if (musicRes == null) {
                logger.error("cannot get media resource {}", relativePath);
                return null;
            }
            if (inJar) {
                String path = String.format("jar:file:%s!/%s", jarPath, relativePath);
                logger.debug("the Fuck media path: {}", path);
                return new Media(path);
            } else
                return new Media("file:" + musicRes.getPath());
        } else
            return null;
    }

    /**
     * 获取字体资源的URL
     *
     * @param fontName 字体名称
     * @return URL格式的font资源
     */
    @Deprecated
    private String getFontURL(String fontName) {
        if (fonts.containsKey(fontName))
            return "file:" + fonts.get(fontName);
        else {
            logger.error("font {} is not exist!", fontName);
            return null;
        }
    }
}
